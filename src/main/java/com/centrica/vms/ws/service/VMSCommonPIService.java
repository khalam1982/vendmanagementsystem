package com.centrica.vms.ws.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.CreateVendResponseMessage;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendCode;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcome;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;
import LG_SMS_LIB.BreakupException;
import LG_SMS_LIB.BreakupException.EType;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.EPaymentKeyInvalidException;
import com.centrica.vms.exception.EPaymentKeyOutOfRangeException;
import com.centrica.vms.exception.PanNotFoundException;
import com.centrica.vms.exception.UnknownBreakupException;
import com.centrica.vms.exception.VMSInvalidPANException;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.CreditIDCompKey;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.CreateVendResponse;
import common.enterprise.uk.co.britishgas.BG_Log;

public class VMSCommonPIService implements IVMSFaultService {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	private String vendErrorMessage = "TRANSACTION FAILED";
	
	private final String ACCEPTED = "ACCEPTED";
	
	protected static final String VMS_USERNAME_VALUE="system";
	
	private final WSTransactionDAO wsTransDao;
	
	/**
	 * Constructor
	 */
	public VMSCommonPIService() {
		wsTransDao = new WSTransactionDAO();
	}
	
	/**
	 * Constructor
	 * @param wsTransDao - WSTransactionDAO
	 */
	public VMSCommonPIService(final WSTransactionDAO wsTransDao) {
		this.wsTransDao = wsTransDao;
	}
	
	/**
	 * Method to prepare the fault response
	 * @param faultCode
	 * @param errorMessage
	 * @param paymentResponse
	 * @return
	 */
	public CreateVendResponse prepareFaultResponse(
			VendOutcomeCode_type1 faultCode, String errorMessage,
			CreateVendResponse paymentResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering throwPaymentCodeFault method");
		logger.info(Logger.EVENT_UNSPECIFIED,"errorMessage " + errorMessage);
		VMSUtils vmsUtils = new VMSUtils();
		CreateVendResponseMessage createVendResponseMessage = new CreateVendResponseMessage();
		VendOutcome vendOutcome = new VendOutcome();
		vendOutcome.setVendOutcomeCode(faultCode);
		vendOutcome.setVendOutcomeDescription(vendErrorMessage);
		createVendResponseMessage.setVendOutcome(vendOutcome);
		BG_Log log = vmsUtils.getBGLogForFault(faultCode, errorMessage);
		createVendResponseMessage.setLog(log);
		paymentResponse.setPaymentResponse(createVendResponseMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Entering throwPaymentCodeFault method");
		return paymentResponse;
	}
	
	/**
	 * Method to set the credit value to the previous value.
	 * @param transactionType
	 * @param msn
	 * @param status
	 * @throws DBException
	 */
	public void rollBackCreditID(String transactionType, String msn,
			Boolean status) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering rollBackCreditID method ");
		if (status
				&& transactionType.equals(TransactionType.ElectricPurchase
						.toString())) {
			CreditIDCompKey creditIDCompKey = new CreditIDCompKey();
			creditIDCompKey.setMsn(msn);
			creditIDCompKey.setTransactionType(transactionType);
			wsTransDao.rollBackCreditValue(creditIDCompKey);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving rollBackCreditID method ");
	}
	
	/**
	 * Method to set the transaction details
	 * @param transactionType
	 * @param pan
	 * @param vendCode
	 * @param source
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param creditValue
	 * @param currencyType
	 * @param status
	 * @param paymentKey
	 * @param dateTime
	 * @param msn
	 * @return
	 */
	public VendTxnWSDetails setTransactionDetails(
			TransactionType transactionType, String pan, String vendCode,
			String source, Long holdingPeriod, String originalTransactionID,
			String transactionID, String creditValue, String currencyType,
			Status status, String paymentKey,Date actualTimeStamp,Date vendcodeTimeStamp,String msn,
			String createdBy, String updatedBy) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setTransactionDetails method");
		creditValue = getCreditValue(creditValue);
		VendTxnWSDetails transactionDetails = new VendTxnWSDetails();
		VendTxnStatus txnStatusDetail = new VendTxnStatus();
		Set<VendTxnStatus> txnStatusDetails = new HashSet<VendTxnStatus>();
		transactionDetails.setPan(pan);
		transactionDetails.setHoldingPeriod(holdingPeriod);
		transactionDetails.setSource(source);
		transactionDetails.setCreditValue(creditValue);
		transactionDetails.setVendCode(vendCode);
		if (transactionType != null) {
			transactionDetails.setTransactionType(transactionType.toString());
		} else {
			transactionDetails.setTransactionType("---------");
		}
		transactionDetails.setPpKey(paymentKey);
		transactionDetails.setActualTimeStamp(actualTimeStamp);
		transactionDetails.setVendcodeTimeStamp(vendcodeTimeStamp);
		transactionDetails.setCreatedTimeStamp(new Date());
		txnStatusDetail.setStatus(new Integer(new VMSUtils().getVMSStatus(status.getStatus())));
		txnStatusDetail.setUpdatedTimeStamp(new Date());
		txnStatusDetails.add(txnStatusDetail);
		transactionDetails.setTxnStatusDetails(txnStatusDetails);
		transactionDetails.setCreatedBy(createdBy);
		transactionDetails.setUpdatedBy(updatedBy);
		if (originalTransactionID != null) {
			transactionDetails.setOriginalTransactionID(originalTransactionID);
		}
		transactionDetails.setMsn(msn);
		transactionDetails.setTransactionID(transactionID);
		logger.info(Logger.EVENT_UNSPECIFIED,"transaction type is "
				+ transactionDetails.getTransactionType());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setTransactionDetails method");
		return transactionDetails;
	}
	
	/**
	 * Method to get the credit value without "-".
	 * @param creditValue
	 * @return
	 */
	public String getCreditValue(String creditValue) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCreditValue method");
		if(creditValue!=null && creditValue.contains("-")){
			creditValue = creditValue.substring(1, creditValue.length());
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"creditValue : "+creditValue);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCreditValue method");
		return creditValue;
	}

	/**
	 * @param itr
	 * @return
	 */
	protected Integer getVendTxnStatus(Iterator<VendTxnStatus> itr) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTransactionStatus method");
		Integer vendTransactionStatus = 0;
		VendTxnStatus vendTxnStatus = null;
		if (itr != null) {
			if (itr.hasNext()) {
				vendTxnStatus = itr.next();
				vendTransactionStatus = vendTxnStatus.getStatus();
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTransactionStatus method");
		return vendTransactionStatus;
	}
	
	/**
	 * @param vendTransactionDetails
	 * @param wsTransactionDAO
	 * @param status
	 * @throws DBException
	 */
	protected void setVendTxnStatus(VendTxnWSDetails vendTransactionDetails, WSTransactionDAO wsTransactionDAO, int status) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVendTxnStatus method");
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		vendTxnStatus.setStatus(new VMSUtils().getVMSStatus(status));
		vendTxnStatus.setUpdatedTimeStamp(new Date());
		vendTransactionDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
		txnStatusDetails.add(vendTxnStatus);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
		wsTransactionDAO.update(vendTransactionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVendTxnStatus method");
	}
	
	/**
	 * Method to get the holding period
	 * @param holdingPeriod
	 * @param source
	 * @param wstransactionDAO
	 * @return
	 * @throws DBException
	 */
	protected Long getHoldingPeriod(Long holdingPeriod, String source,
			WSTransactionDAO wstransactionDAO) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getHoldingPeriod method");
		if (holdingPeriod == null || holdingPeriod.intValue() == -1) {
			SourceDetails sourceDetails = wstransactionDAO
			.getSourceDetails(source);
			holdingPeriod = sourceDetails.getHoldingPeriod();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getHoldingPeriod method");
		return holdingPeriod;
	}
	
	/**
	 * Method to get the credit id
	 * @param transactionType
	 * @param msn
	 * @param wstransactionDAO
	 * @return
	 * @throws DBException
	 */
	protected int getCreditID(TransactionType transactionType, String msn,
			WSTransactionDAO wstransactionDAO) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCreditID method");
		CreditIDCompKey creditIDCompKey = new CreditIDCompKey();
		creditIDCompKey.setMsn(msn);
		creditIDCompKey.setTransactionType(transactionType.toString());
		int creditID = wstransactionDAO.getCreditIDDetails(creditIDCompKey);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCreditID method");
		return creditID;
	}
	
	/**
	 * Method to handle the break up exception
	 * @param paymentResponse
	 * @param ex
	 * @return
	 */
	protected void handleBreakupException(
			CreateVendResponse paymentResponse, BreakupException ex) 
	throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleBreakupException method");
		if (EType.ePaymentKeyInvalid.equals(ex.get_ExceptionType())) {
			throw new EPaymentKeyInvalidException();
		} else if (EType.ePaymentValueOutOfRange.equals(ex.get_ExceptionType())) {
			throw new EPaymentKeyOutOfRangeException();
		} else {
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleBreakupException method");
		throw new UnknownBreakupException();
	}
	
	/**
	 * Method to get the payment response
	 * @param vendCode
	 * @param paymentResponse
	 * @param status
	 * @return
	 */
	protected CreateVendResponse getPaymentResponse(String vendCode,
			CreateVendResponse paymentResponse, Boolean status) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getPaymentResponse method");
		if (status) {
			CreateVendResponseMessage createVendResponseMessage = getCreateVendResponseMethod(vendCode);
			paymentResponse.setPaymentResponse(createVendResponseMessage);
		} else {
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value7, "job schedule failed",
					paymentResponse);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPaymentResponse method");
		return paymentResponse;
	}
	
	/**
	 * Method to create and populate the create vend response
	 * @param vendCode
	 * @return
	 */
	private CreateVendResponseMessage getCreateVendResponseMethod(
			String vendCode) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCreateVendResponseMethod method");
		VMSUtils vmsUtils = new VMSUtils();
		CreateVendResponseMessage createVendResponseMessage = new CreateVendResponseMessage();
		BG_Log log = vmsUtils.getBGLog(VendOutcomeCode_type1.value1,
		"Successfull");
		createVendResponseMessage.setLog(log);
		VendCode vendCodeResponse = new VendCode();
		vendCodeResponse.setVendCode(vendCode);
		createVendResponseMessage.setVendCode(vendCodeResponse);
		VendOutcome vendOutcome = new VendOutcome();
		vendOutcome.setVendOutcomeCode(VendOutcomeCode_type1.value1);
		vendOutcome.setVendOutcomeDescription(ACCEPTED);
		createVendResponseMessage.setVendOutcome(vendOutcome);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCreateVendResponseMethod method");
		return createVendResponseMessage;
	}
	
	/**
	 * Method used to get the timestamp to generate the vend code
	 * @param creditValue
	 * @param msn
	 * @param wstransactionDAO
	 * @param timestamp
	 * @param actualTimeStamp
	 */
	protected Date getVendCodeTimeStamp(String creditValue, String msn,
			WSTransactionDAO wstransactionDAO, Calendar timestamp,
			Date actualTimeStamp,TransactionType transactionType) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendCodeTimeStamp method");
		Date vendTimeStamp = null;
		VMSUtils util = new VMSUtils();
		Integer hour = null;
		try {
			hour = util.offsetTimeToUTC(timestamp);
			//TODO: Remove later. Added for testing purpose
			//hour = timestamp.get(Calendar.HOUR_OF_DAY);
			vendTimeStamp = wstransactionDAO.getVendTimeStamp(msn,creditValue,timestamp.get(Calendar.DAY_OF_MONTH),timestamp.get(Calendar.MONTH)+1,timestamp.get(Calendar.YEAR),hour,transactionType);
			vendTimeStamp = util.getVendTimeStamp(vendTimeStamp, actualTimeStamp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendCodeTimeStamp method");
		return vendTimeStamp;
	}
	
	/**
	 * Method to get the MPxN from PAN
	 * @param wstransactionDAO
	 * @param pan
	 * @return mpxn
	 * @throws PanNotFoundException
	 * @throws DBException
	 */
	protected String getMPxNFromPan(WSTransactionDAO wstransactionDAO, String pan) throws VMSInvalidPANException, PanNotFoundException, DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMPxNFromPan method");
		String mpxn = null;
		CustomerDetails customerDetails = wstransactionDAO.getCustomerDetails(pan);
		Date validFrom = customerDetails.getValidFrom();
		Date validTo = customerDetails.getValidTo();
		Boolean isValid = isWithinRange(validFrom, validTo);
		if (isValid) {
			mpxn = customerDetails.getMpxn();
		} else {
			throw new VMSInvalidPANException();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMPxNFromPan method");
		return mpxn;
	}
	
	/**
	 * Method to check the valid date range
	 * @param validFrom
	 * @param validTo
	 * @return boolean
	 */
	private boolean isWithinRange(Date validFrom, Date validTo) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isWithinRange method");
		Calendar c = Calendar.getInstance();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isWithinRange method");
		return !(c.getTime().before(validFrom) || c.getTime().after(validTo));
	}
}
