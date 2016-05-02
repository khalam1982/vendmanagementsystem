package com.centrica.vms.ws.service;

import java.util.Calendar;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

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
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.ReverseVend;
import com.centrica.vms.ws.sap.service.ReverseVendResponse;
import common.enterprise.uk.co.britishgas.BG_Log;

public class VMSReversalService extends VMSCommonPIService{

	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	private String dbErrorMessage = "DATABASE EXCEPTION";
	
	private String unknownError = "UNKNOWN ERROR";
	
	private String reversalErrorMessage = "REVERSAL REJECTED";
	 
	protected String REJECTED = "REJECTED";
	    
	protected String ACCEPTED = "ACCEPTED";
	
	protected final WSTransactionDAO wsTransactionDAO;
	private final VMSSchedulerService vmsschedulerService;
	
	/**
	 * Constructor
	 */
	public VMSReversalService() {
		wsTransactionDAO = new WSTransactionDAO();
		vmsschedulerService = new VMSSchedulerServiceImpl();
	}
	
	/**
	 * Constructor
	 * @param wsTransactionDAO - WSTransactionDAO
	 * @param vmsschedulerService - VMSSchedulerService
	 */
	public VMSReversalService(final WSTransactionDAO wsTransactionDAO, final VMSSchedulerService vmsschedulerService) {
		this.wsTransactionDAO = wsTransactionDAO;
		this.vmsschedulerService = vmsschedulerService;
	}
	
	/**
	 * @param reverseVend
	 * @return
	 */
	public ReverseVendResponse handleReversalRequest(ReverseVend reverseVend){ 
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleReversalRequest method");
		ReverseVendResponse reverseTxnResponse = new ReverseVendResponse();
		try {
			String originalTransactionID = reverseVend.getReverseTxnRequest()
					.getOriginalVendRequestKey().getVendRequestIdentifier()
					.toString();
			String transactionID = reverseVend.getReverseTxnRequest()
					.getVendRequestKey().getVendRequestIdentifier().toString();
			logger.info(Logger.EVENT_UNSPECIFIED,"Request details"
					+ new Object[] { originalTransactionID, transactionID });
			reverseTxnResponse = processReverseTxnRequset(
					originalTransactionID, transactionID, reverseTxnResponse, fetchTimestamp(reverseVend));
		} catch (DBException ex) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown");
			prepareReversalFaultResponse(VendOutcomeCode_type1.value4,
					dbErrorMessage, reverseTxnResponse);
		} catch (Exception ex) {
			logger.error(Logger.EVENT_FAILURE,"Exception is " + ex.getMessage());
			prepareReversalFaultResponse(VendOutcomeCode_type1.value7,
					unknownError, reverseTxnResponse);
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleReversalRequest method");
		return reverseTxnResponse;
	
	}

	private Calendar fetchTimestamp(ReverseVend reverseVend) {
		return reverseVend.getReverseTxnRequest().getVendRequestTimeStamp().getVendRequestDateTime();	
	}
	
	/**
	 * Method to process the reverse transaction request
	 * @param originalTransactionID
	 * @param transactionID
	 * @param reverseTxnResponse
	 * @param date 
	 * @return
	 * @throws DBException
	 */
	private ReverseVendResponse processReverseTxnRequset(
			String originalTransactionID, String transactionID,
			ReverseVendResponse reverseTxnResponse, Calendar timestamp) throws DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processReverseTxnRequset method");
		VendTxnWSDetails vendTransactionDetails = fetchTransactionDetails(originalTransactionID);
		Boolean status = Boolean.FALSE;
		if (vendTransactionDetails != null) {
			String transactionType = vendTransactionDetails
					.getTransactionType();
			if (transactionType.equals(TransactionType.ElectricPurchase
					.toString())
					|| transactionType.equals(TransactionType.GasPurchase
							.toString())) {
				reverseTxnResponse = processReversePayment(
						originalTransactionID, transactionID, transactionType, vendTransactionDetails.getTxnStatusDetails(),
						vendTransactionDetails.getMsn(), timestamp);
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Transaction type is not expected");
				reverseTxnResponse = prepareReverseTxnResponse(
						originalTransactionID, transactionID, status, reverseTxnResponse);
			}
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Transaction is not found");
			reverseTxnResponse = prepareReverseTxnResponse(
					originalTransactionID, transactionID, status, reverseTxnResponse);
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving processReverseTxnRequset method");
		return reverseTxnResponse;
	}

	public VendTxnWSDetails fetchTransactionDetails(String originalTransactionID) throws DBException {
		return wsTransactionDAO
				.getVendTxnWSDetails(originalTransactionID);
	}
	
	
	/**
	 * Method to prepare the fault response
	 * @param faultCode
	 * @param errorMessage
	 * @param paymentResponse
	 * @return
	 */
	private ReverseVendResponse prepareReversalFaultResponse(
			VendOutcomeCode_type1 faultCode, String errorMessage,
			ReverseVendResponse paymentResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareReversalFaultResponse method");
		logger.info(Logger.EVENT_UNSPECIFIED,"errorMessage " + errorMessage);
		VMSUtils vmsUtils = new VMSUtils();
		ReverseVendResponseMessage reverseVendResponseMessage = new ReverseVendResponseMessage();
		VendOutcome vendOutcome = new VendOutcome();
		vendOutcome.setVendOutcomeCode(faultCode);
		vendOutcome.setVendOutcomeDescription(reversalErrorMessage);
		reverseVendResponseMessage.setVendOutcome(vendOutcome);
		BG_Log log = vmsUtils.getBGLogForFault(faultCode, errorMessage);
		reverseVendResponseMessage.setLog(log);
		paymentResponse.setReverseTxnResponse(reverseVendResponseMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareReversalFaultResponse method");
		return paymentResponse;
	}
	
	/**
	 * Method to process the reverse payment
	 * @param originalTransactionID
	 * @param transactionID
	 * @param transactionType
	 * @param msn
	 * @param timestamp 
	 * @return
	 * @throws DBException
	 */
	protected ReverseVendResponse processReversePayment(
			String originalTransactionID, String transactionID,
			String transactionType, Set<VendTxnStatus> vendTxnStatus, String msn, Calendar timestamp) throws DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processReversePayment method");
		Integer vendTransactionStatus = getVendTxnStatus(vendTxnStatus.iterator());
		Boolean status = Boolean.FALSE;
		
		//Reversal processed only when the status is in Holding Pen other reversal rejected
		if (vendTransactionStatus == Status.SC_120.getStatus()) {
			status = vmsschedulerService.unScheduleJob(
					originalTransactionID, Status.SC_130);
		}
		
		//rollBackCreditID(transactionType, msn, status); Defect ID: 1339
		ReverseVendResponse reverseTxnResponse = new ReverseVendResponse();
		logger.info(Logger.EVENT_UNSPECIFIED,"Status is " + status);
		reverseTxnResponse = prepareReverseTxnResponse(originalTransactionID,
				transactionID, status, reverseTxnResponse);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processReversePayment method");
		return reverseTxnResponse;
	}
	
	/**
	 * Method to prepare the reverse transaction response
	 * @param originalTransactionID
	 * @param transactionID
	 * @param status
	 * @param reverseTxnResponse
	 * @return
	 * @throws DBException
	 */
	protected ReverseVendResponse prepareReverseTxnResponse(
			String originalTransactionID, String transactionID, Boolean status,
			ReverseVendResponse reverseTxnResponse) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareReverseTxnResponse method");
		TransactionType transactionType = TransactionType.Reversal;
		Status responseStatus = prepareReverseTxnStatus(status,
				reverseTxnResponse);
		VendTxnWSDetails transactionDetails = setTransactionDetails(
				transactionType, null, null, null, 0L, originalTransactionID,
				transactionID, null, null, responseStatus, null,null,null,null,"system","system");
		wsTransactionDAO.insert(transactionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareReverseTxnResponse method");
		return reverseTxnResponse;
	}
	
	
	/**
	 * Method to set the reverse transaction status
	 * @param status
	 * @param reverseTxnResponse
	 * @return
	 */
	private Status prepareReverseTxnStatus(Boolean status,
			ReverseVendResponse reverseTxnResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareReverseTxnStatus method");
		Status responseStatus = Status.SC_155;
		VendOutcome vendOutcome = new VendOutcome();
		VendOutcomeCode_type1 vendOutcomeCode = VendOutcomeCode_type1.value1;
		if (status) {
			vendOutcome.setVendOutcomeCode(vendOutcomeCode);
			responseStatus = Status.SC_150;
			vendOutcome.setVendOutcomeDescription(ACCEPTED);
		} else {
			vendOutcomeCode = VendOutcomeCode_type1.value8;
			vendOutcome.setVendOutcomeCode(vendOutcomeCode);
			vendOutcome.setVendOutcomeDescription(REJECTED);
		}
		ReverseVendResponseMessage reverseVendResponseMessage = new ReverseVendResponseMessage();
		VMSUtils vmsUtils = new VMSUtils();
		BG_Log log = vmsUtils.getBGLog(vendOutcomeCode, responseStatus
				.toString());
		reverseVendResponseMessage.setLog(log);
		reverseVendResponseMessage.setVendOutcome(vendOutcome);
		reverseTxnResponse.setReverseTxnResponse(reverseVendResponseMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareReverseTxnStatus method");
		return responseStatus;
	}
}
