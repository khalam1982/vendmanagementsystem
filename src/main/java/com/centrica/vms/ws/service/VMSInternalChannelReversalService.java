package com.centrica.vms.ws.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.StaleObjectStateException;

import smartmeterprocessing.enterprise.uk.co.britishgas.ReverseVendResponseMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcome;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.VMSAppException;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.scheduler.service.VMSSchedulerService;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.ReverseVendResponse;
import common.enterprise.uk.co.britishgas.BG_Log;

/**
 * Class to process the reversal of payment for internal channels. The payments
 * are accepted for all scenarios including scenarios when the vend code is not
 * in the holding pen.
 * 
 * Reversals maybe rejected if there are any system exceptions such as API or
 * Database failures. Added for the Prepayment 2012 Project
 * 
 */
public class VMSInternalChannelReversalService extends VMSReversalService {

	private static final String REV_PREFIX = "REV_";
	private static final String VOID_PREFIX = "VOID_";
	private Logger logger = ESAPI.getLogger(getClass().getName());
	private VMSSchedulerService vmsschedulerService;

	public VMSInternalChannelReversalService() {
		vmsschedulerService = new VMSSchedulerServiceImpl();
	}

	/**
	 * Method to process the reversal of payment for internal channels.
	 * 
	 * @param originalTransactionID
	 * @param transactionID
	 * @param transactionType
	 * @param msn
	 * @return ReverseVendResponse the response.
	 * @throws DBException
	 * @throws VMSAppException
	 */
	@Override
	protected ReverseVendResponse processReversePayment(String originalTransactionID, String transactionID, String transactionType, Set<VendTxnStatus> vendTxnStatus, String msn, Calendar timestamp)
			throws DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processReversePayment method");
		
		// For internal channels, the transactionID will be same as the
		// originaltransactionID - to differentiate add a prefix.
		String reversalTxnId = REV_PREFIX + transactionID;
		String voidTxnId = VOID_PREFIX + transactionID;
		
		Status originalStatus = Status.getStatus(getVendTxnStatus(vendTxnStatus.iterator()));
		boolean successStatus = false;
		Status responseStatus = Status.SC_130;

		// Switch on the originalStatus and handle each scenario differently
		switch (originalStatus) {
		case SC_120:
			responseStatus = Status.SC_130;
			successStatus = removeFromHoldingPen(originalTransactionID,responseStatus);
			break;
		case SC_170:
			responseStatus = Status.SC_151;
			successStatus = removeFromRetryQueue(originalTransactionID, responseStatus);
			break;
		case SC_125:
			responseStatus = Status.SC_152;
			successStatus = updateOriginalTransactionDetails(fetchTransactionDetails(originalTransactionID),  responseStatus);
			break;
		case SC_300:
		case SC_310:
		case SC_320:
		case SC_330:
		case SC_340:
		case SC_350:
			responseStatus = Status.SC_130;
			successStatus = updateOriginalTransactionDetails(fetchTransactionDetails(originalTransactionID),  responseStatus);
			break;
		case SC_200:
			responseStatus = Status.SC_153;
			successStatus = updateOriginalTransactionDetails(fetchTransactionDetails(originalTransactionID),  responseStatus);
			break;
		case SC_100:
			responseStatus = Status.SC_154;
			successStatus = updateOriginalTransactionDetails(fetchTransactionDetails(originalTransactionID),  responseStatus);
			successStatus = (successStatus)? insertTransaction(originalTransactionID, reversalTxnId, Status.SC_150) : successStatus;
			successStatus = (successStatus)? triggerVoidNegativeAdjustment(voidTxnId, originalTransactionID, timestamp): successStatus;
			break;
		}

		if (successStatus) {
			successStatus = insertTransactionDetails(originalTransactionID, reversalTxnId, Status.SC_150, originalStatus, successStatus);
		} else {
			successStatus = insertTransactionDetails(originalTransactionID, reversalTxnId, Status.SC_155, originalStatus, successStatus);
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving processReversePayment method");
		return buildResponse(successStatus, responseStatus);
	}
	
	private boolean updateOriginalTransactionDetails(VendTxnWSDetails vendTransactionDetails, Status newStatus) throws StaleObjectStateException, DBException {
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
		vendTxnStatus.setStatus(new VMSUtils().getVMSStatus(newStatus.getStatus())); 
		vendTxnStatus.setUpdatedTimeStamp(new Date());
		txnStatusDetails.add(vendTxnStatus);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
		return wsTransactionDAO.update(vendTransactionDetails);
	}

	/**
	 * Add an entry to the transaction and status history tables.
	 * 
	 * @param originalTransactionID
	 * @param reversalTxnId
	 * @param newStatus
	 * @throws DBException
	 */
	private boolean insertTransaction(String originalTransactionID, String reversalTxnId, Status newStatus) throws DBException {
		return wsTransactionDAO.insert(setTransactionDetails(TransactionType.Reversal, null, null, null, 0L, originalTransactionID, reversalTxnId, null, null, newStatus, null, null, null, null, "system",
				"system"));
	}

	/**
	 * Build the response to be returned to the internal channel.
	 * 
	 * @param successStatus
	 * @param responseStatus
	 * @return
	 */
	private ReverseVendResponse buildResponse(boolean successStatus, Status responseStatus) {
		ReverseVendResponse response = new ReverseVendResponse();
		VendOutcome vendOutcome = new VendOutcome();
		VendOutcomeCode_type1 vendOutcomeCode = VendOutcomeCode_type1.value1;
		if (successStatus) {
			vendOutcome.setVendOutcomeCode(vendOutcomeCode);
			vendOutcome.setVendOutcomeDescription(ACCEPTED);
		} else {
			vendOutcomeCode = VendOutcomeCode_type1.value8;
			vendOutcome.setVendOutcomeCode(vendOutcomeCode);
			vendOutcome.setVendOutcomeDescription(REJECTED);
		}
		ReverseVendResponseMessage reverseVendResponseMessage = new ReverseVendResponseMessage();
		VMSUtils vmsUtils = new VMSUtils();
		BG_Log log = vmsUtils.getBGLog(vendOutcomeCode, responseStatus.toString());
		reverseVendResponseMessage.setLog(log);
		reverseVendResponseMessage.setVendOutcome(vendOutcome);
		response.setReverseTxnResponse(reverseVendResponseMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareReverseTxnStatus method");
		return response;
	}

	/**
	 * Insert transaction for all reversals that donot involve void negative
	 * adjustments.
	 * 
	 * @param originalTransactionID
	 * @param reversalTxnId`
	 * @param newStatus
	 * @param originalStatus
	 * @param successStatus
	 * @throws DBException
	 */
	private boolean insertTransactionDetails(String originalTransactionID, String reversalTxnId, Status newStatus, Status originalStatus, boolean successStatus) throws DBException {
		if (originalStatus != Status.SC_100) { 
			return insertTransaction(originalTransactionID, reversalTxnId, newStatus);
		} 
		return successStatus;
	}

	/**
	 * Triggers a void negative adjustment call internally.
	 * 
	 * @param transactionID
	 * @param originalTransactionID
	 * @param timestamp
	 * @param vendTransactionDetails 
	 * @return
	 * @throws DBException
	 * @throws VMSAppException
	 */
	private boolean triggerVoidNegativeAdjustment(String voidTransactionID, String originalTransactionID, Calendar timestamp) throws DBException, VMSAppException {
		VMSVoidService vmsVoidService = new VMSVoidService();
		VendTxnWSDetails vendTransactionDetails = fetchTransactionDetails(originalTransactionID);
		return vmsVoidService.processVoidRequest(voidTransactionID, originalTransactionID, vendTransactionDetails, timestamp, vendTransactionDetails.getPan());
	}

	private boolean removeFromRetryQueue(String originalTransactionID, Status statusForOriginalTransaction) throws DBException {
		return vmsschedulerService.unScheduleJob(originalTransactionID, statusForOriginalTransaction);
	}

	private boolean removeFromHoldingPen(String originalTransactionID, Status statusForOriginalTransaction) throws DBException {
		return vmsschedulerService.unScheduleJob(originalTransactionID, statusForOriginalTransaction);
	}

}
