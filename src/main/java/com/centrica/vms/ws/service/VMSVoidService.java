package com.centrica.vms.ws.service;

import java.util.Calendar;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcome;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VoidVendRequestResponseMessage;

import LG_PAYMENT_CODE.PaymentCode.eCreditType;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.ManuTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.EPaymentKeyInvalidException;
import com.centrica.vms.exception.EPaymentKeyOutOfRangeException;
import com.centrica.vms.exception.LGLicenceException;
import com.centrica.vms.exception.MPxNNotFoundException;
import com.centrica.vms.exception.PanNotFoundException;
import com.centrica.vms.exception.UnknownBreakupException;
import com.centrica.vms.exception.VMSAppException;
import com.centrica.vms.exception.VMSInvalidPANException;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.VMSSchedulerService;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.CreditIDCompKey;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.CreateVendResponse;
import com.centrica.vms.ws.sap.service.VoidVend;
import com.centrica.vms.ws.sap.service.VoidVendResponse;
import common.enterprise.uk.co.britishgas.BG_Log;

public class VMSVoidService extends VMSCommonPaymentService {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	private VMSUtils vmsUtils = new VMSUtils();
	
	private String REJECTED = "REJECTED";
    
	private String ACCEPTED = "ACCEPTED";
	
	private String voidErrorMessage = "VOID REJECTED";
	
	private final WSTransactionDAO wsTransactionDAO;
	private final VMSSchedulerService vmsschedulerService;
	
	/**
	 * Constructor
	 */
	public VMSVoidService() {
		vmsschedulerService = new VMSSchedulerServiceImpl();
		wsTransactionDAO = new WSTransactionDAO();
	}
	
	/**
	 * Constructor
	 * 
	 * @param vmsschedulerService - VMSSchedulerService
	 * @param wsTransactionDAO - WSTransactionDAO
	 */
	public VMSVoidService(final VMSSchedulerService vmsschedulerService, final WSTransactionDAO wsTransactionDAO) {
		this.vmsschedulerService = vmsschedulerService;
		this.wsTransactionDAO = wsTransactionDAO;
		
	}
	
	public VoidVendResponse handleVoidRequest(VoidVend voidRequest){
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleVoidRequest method");
		/**
		 * Duplicate TransactionId issue: 
		 * Solution: prefix the transactionId with "BG_"
		 */
		StringBuilder transactionID = new StringBuilder("BG_");
		VoidVendResponse voidTxnResponse = new VoidVendResponse();
		try {
			Boolean requestStatus = Boolean.FALSE;
			transactionID.append(voidRequest.getVoidTxnRequest()
					.getVendRequestKey().getVendRequestIdentifier().toString());
			
			String originalTransactionID = voidRequest.getVoidTxnRequest()
					.getOriginalVendRequestKey().getVendRequestIdentifier()
					.toString();
			String pan = voidRequest.getVoidTxnRequest().getVendRequestPAN().getPanNumber();
			VendTxnWSDetails vendTransactionDetails = wsTransactionDAO
					.getVendTxnWSDetails(originalTransactionID);
			logger.info(Logger.EVENT_UNSPECIFIED," Transaction ID : " + transactionID);
			requestStatus = processVoidPayment(voidRequest, transactionID
					.toString(), originalTransactionID,
					vendTransactionDetails,pan);
			voidTxnResponse = prepareVoidTxnResponse(requestStatus,
					transactionID.toString(), originalTransactionID);
		} catch (EPaymentKeyInvalidException ex) {
			logger.error(Logger.EVENT_FAILURE,"EPayment Key Invalid Exception is thrown");
			prepareVoidFaultResponse(VendOutcomeCode_type1.value2,
					invalidEPaymentKeyMessage, voidTxnResponse);
		} catch (EPaymentKeyOutOfRangeException ex) {
			logger.error(Logger.EVENT_FAILURE,"EPaymentKey Out of Range Exception is " + invalidEPaymentOutOfRangeMessage);
			voidTxnResponse = prepareVoidFaultResponse(
					VendOutcomeCode_type1.value3, invalidEPaymentOutOfRangeMessage,
					voidTxnResponse);
		} catch (UnknownBreakupException ex) {
			logger.error(Logger.EVENT_FAILURE,"Unknown Breakup Exception is " + invalidBreakupMessage);
			voidTxnResponse = prepareVoidFaultResponse(
					VendOutcomeCode_type1.value6, invalidBreakupMessage,
					voidTxnResponse);
		} catch(VMSInvalidPANException ex) {
			logger.error(Logger.EVENT_FAILURE,"Invalid PAN Exception is " + invalidPan);
			voidTxnResponse = prepareVoidFaultResponse(
					VendOutcomeCode_type1.value10, invalidPan,
					voidTxnResponse);
		} catch(PanNotFoundException ex) {
			logger.error(Logger.EVENT_FAILURE,"PAN not foud Exception is " + panNotFound);
			voidTxnResponse = prepareVoidFaultResponse(
					VendOutcomeCode_type1.value16, panNotFound,
					voidTxnResponse);
		}catch(MPxNNotFoundException ex) {
			logger.error(Logger.EVENT_FAILURE,"MPxN not found Exception is " + mpxnNotFound);
			voidTxnResponse = prepareVoidFaultResponse(
					VendOutcomeCode_type1.value15, mpxnNotFound,
					voidTxnResponse);
		} catch (LGLicenceException ex) {
			logger.error(Logger.EVENT_FAILURE,"Licence Exception is thrown");
			voidTxnResponse = prepareVoidFaultResponse(
					VendOutcomeCode_type1.value5, invalidLicenceMessage,
					voidTxnResponse);
		} catch (DBException ex) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown");
			prepareVoidFaultResponse(VendOutcomeCode_type1.value9,
					dbErrorMessage, voidTxnResponse);
		} catch (VMSAppException ex) {
			logger.error(Logger.EVENT_FAILURE,"Exception is " + ex.getMessage());
			prepareVoidFaultResponse(VendOutcomeCode_type1.value6,
					lgErrorMessage, voidTxnResponse);
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleVoidRequest method");
		return voidTxnResponse;
	
	}
	
	/**
	 * Method to process the void payment request
	 * @param voidRequest
	 * @param transactionID
	 * @param originalTransactionID
	 * @param vendTransactionDetails
	 * @param pan
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private Boolean processVoidPayment(VoidVend voidRequest,
			String transactionID, 
			String originalTransactionID,
			VendTxnWSDetails vendTransactionDetails,String pan)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, 
			VMSInvalidPANException, PanNotFoundException, MPxNNotFoundException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processVoidPayment method");
		Boolean requestStatus;
		if (vendTransactionDetails != null) {
			Calendar timestamp = voidRequest.getVoidTxnRequest()
					.getVendRequestTimeStamp().getVendRequestDateTime();
			requestStatus = processVoidRequest(transactionID,
					originalTransactionID,
					vendTransactionDetails, timestamp,pan);
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Matching transaction ID is not found");
			requestStatus = Boolean.TRUE; // Check with the team
			setVoidTransaction(transactionID, originalTransactionID, Status.SC_165,
					TransactionType.Void);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processVoidPayment method");
		return requestStatus;
	}
	
	
	/**
	 * Method to prepare the void transaction response
	 * @param status
	 * @param transactionID
	 * @param originalTransactionID
	 * @return
	 */
	private VoidVendResponse prepareVoidTxnResponse(Boolean status,
			String transactionID, String originalTransactionID) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVoidTxnResponse method");
		VendOutcome vendOutcome = new VendOutcome();
		VoidVendRequestResponseMessage voidVendRequestResponseMessage = new VoidVendRequestResponseMessage();
		VendOutcomeCode_type1 vendOutcomeCode = VendOutcomeCode_type1.value1;
		String vendOutcomeDesc = REJECTED;
		if (status) {
			vendOutcomeDesc = ACCEPTED;
			vendOutcome.setVendOutcomeCode(vendOutcomeCode);
			vendOutcome.setVendOutcomeDescription(vendOutcomeDesc);
		} else {
			vendOutcomeCode = VendOutcomeCode_type1.value9;
			vendOutcome.setVendOutcomeCode(vendOutcomeCode);
			vendOutcome.setVendOutcomeDescription(vendOutcomeDesc);
		}
		voidVendRequestResponseMessage.setVendOutcome(vendOutcome);
		VoidVendResponse voidTxnResponse = new VoidVendResponse();
		BG_Log log = vmsUtils.getBGLog(vendOutcomeCode, vendOutcomeDesc
				.toString());
		voidVendRequestResponseMessage.setLog(log);
		voidTxnResponse.setVoidTxnResponse(voidVendRequestResponseMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareVoidTxnResponse method");
		return voidTxnResponse;
	}

	
	/**
	 * Method that process the void request
	 * @param transactionID
	 * @param wstransactionDAO
	 * @param originalTransactionID
	 * @param vendTransactionDetails
	 * @param vendTransactionStatus
	 * @param timestamp
	 * @param pan
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	public Boolean processVoidRequest(String transactionID,
			String originalTransactionID, VendTxnWSDetails vendTransactionDetails,
			Calendar timestamp,String pan)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, 
			VMSInvalidPANException, PanNotFoundException, MPxNNotFoundException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processVoidRequest method");
		Boolean requestStatus = Boolean.FALSE;
		Integer vendTransactionStatus = getVendTxnStatus(vendTransactionDetails.getTxnStatusDetails().iterator());
		if (vendTransactionStatus == Status.SC_100.getStatus()
				|| vendTransactionStatus==Status.SC_150.getStatus() 
				|| vendTransactionStatus==Status.SC_154.getStatus()) {
			logger.info(Logger.EVENT_UNSPECIFIED,"Status of the transaction is not expected");
			requestStatus = processVoidAdjustment(vendTransactionDetails, transactionID, timestamp, pan);
		} else if(vendTransactionStatus==Status.SC_200.getStatus()) {
			requestStatus = processVoidUnSchJob(transactionID, originalTransactionID,
					vendTransactionDetails, vendTransactionStatus, timestamp,pan);
		}else if (vendTransactionStatus==Status.SC_155.getStatus()|| 
				vendTransactionStatus==Status.SC_105.getStatus() || vendTransactionStatus==Status.SC_106.getStatus() ||
				vendTransactionStatus==Status.SC_107.getStatus() || vendTransactionStatus==Status.SC_108.getStatus() ||
				vendTransactionStatus==Status.SC_109.getStatus() || vendTransactionStatus==Status.SC_110.getStatus() ||
				vendTransactionStatus==Status.SC_300.getStatus() || vendTransactionStatus==Status.SC_310.getStatus()
			    || vendTransactionStatus==Status.SC_320.getStatus() || vendTransactionStatus==Status.SC_330.getStatus()
			    || vendTransactionStatus==Status.SC_340.getStatus() || vendTransactionStatus==Status.SC_350.getStatus()) {
			logger.info(Logger.EVENT_UNSPECIFIED,"request had been processed or previous transaction had failed");
			requestStatus = processVoidRejectedReversal(transactionID, originalTransactionID);
		}else if (vendTransactionStatus==Status.SC_135.getStatus()|| vendTransactionStatus==Status.SC_140.getStatus() 
				|| vendTransactionStatus==Status.SC_145.getStatus() ) {
			requestStatus = Boolean.TRUE;
			logger.info(Logger.EVENT_UNSPECIFIED,"original transaction is reversal or purchase and it had been voided");
		} else if(vendTransactionStatus==Status.SC_115.getStatus()){
			logger.info(Logger.EVENT_UNSPECIFIED,"transaction not able to process");
			requestStatus = voidUnSentTxn(transactionID, originalTransactionID, vendTransactionDetails, Status.SC_140.getStatus());
		} else if(vendTransactionStatus==Status.SC_125.getStatus()){
			logger.info(Logger.EVENT_UNSPECIFIED,"transaction not yet processed");
			requestStatus = voidUnSentTxn(transactionID, originalTransactionID, vendTransactionDetails, Status.SC_180.getStatus());
		} else {
			requestStatus = processVoidUnSchJob(transactionID, originalTransactionID,
					vendTransactionDetails, vendTransactionStatus, timestamp,pan);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processVoidRequest method");
		return requestStatus;
	}
	

	/**
	 * Method to set the void transaction in the database
	 * @param transactionID
	 * @param originalTransactionID
	 * @param responseStatus
	 * @param txnType
	 * @throws DBException
	 */
	private void setVoidTransaction(String transactionID, String originalTransactionID,
			Status responseStatus, TransactionType txnType) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering setVoidTransaction method ");
		if(wsTransactionDAO.getVendTxnWSDetails(transactionID)==null){ //Defect no:115 -- Start
			VendTxnWSDetails transactionDetails = setTransactionDetails(txnType,
				null, null, null, 0L, originalTransactionID, transactionID,
				null, null, responseStatus, null,null,null,null,VMS_USERNAME_VALUE,VMS_USERNAME_VALUE);
			wsTransactionDAO.insert(transactionDetails);
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"Void had been processed already");
		}//Defect no:115 -- End
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setVoidTransaction method");
	}

	
	/**
	 * Method to void the unsent transaction
	 * @param transactionID
	 * @param originalTransactionID
	 * @param vendTransactionDetails
	 * @return
	 * @throws DBException
	 */
	private Boolean voidUnSentTxn(String transactionID, String originalTransactionID,
			VendTxnWSDetails vendTransactionDetails, int status) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering voidUnSentTxn method");
		setVendTxnStatus(vendTransactionDetails, wsTransactionDAO, status);
		setVoidTransaction(transactionID, originalTransactionID, Status.SC_160,
				TransactionType.Void);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving voidUnSentTxn method");
		return Boolean.TRUE;
	}

	/**
	 * Method to process the rejected reversal request for void
	 * @param transactionID
	 * @param wstransactionDAO
	 * @param originalTransactionID
	 * @return
	 * @throws DBException
	 */
	private Boolean processVoidRejectedReversal(String transactionID, String originalTransactionID)
			throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processVoidRejectedReversal method ");
		Boolean requestStatus = Boolean.TRUE;
		logger.info(Logger.EVENT_UNSPECIFIED,"original transaction is reversal and it is in rejected status");
		if(wsTransactionDAO.getVendTxnWSDetails(transactionID)==null){
			setVoidTransaction(transactionID, originalTransactionID, Status.SC_160, TransactionType.Void);
		}else {
			logger.info(Logger.EVENT_UNSPECIFIED,"request is already actioned");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processVoidRejectedReversal method ");
		return requestStatus;
	}
	
	/**
	 * Method that process the unschedule job request to void the transaction
	 * @param transactionID
	 * @param originalTransactionID
	 * @param vendTransactionDetails
	 * @param timestamp
	 * @return
	 * @throws DBException
	 */
	private Boolean processVoidUnSchJob(String transactionID, String originalTransactionID,
			VendTxnWSDetails vendTransactionDetails, Integer vendTransactionStatus, Calendar timestamp,String pan)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, 
			VMSInvalidPANException, PanNotFoundException, MPxNNotFoundException, LGLicenceException, DBException, VMSAppException {
		
		logger.debug(Logger.EVENT_SUCCESS,"Entering processVoidUnSchJob method");
		Boolean unSchedulestatus = Boolean.FALSE;
		if (vendTransactionStatus == Status.SC_170.getStatus()) {
			unSchedulestatus = vmsschedulerService.unScheduleJob(
					originalTransactionID, Status.SC_180);	
		} else {
			unSchedulestatus = vmsschedulerService.unScheduleJob(
					originalTransactionID, Status.SC_140);	
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"Status is " + unSchedulestatus);
		if (!unSchedulestatus) {
			logger.info(Logger.EVENT_UNSPECIFIED,"unschedule request is not successful "
					+ originalTransactionID);
			unSchedulestatus = processVoidAdjustment(vendTransactionDetails, transactionID, timestamp, pan);
		} else {
			//rollBackCreditID(vendTransactionDetails.getTransactionType(), vendTransactionDetails.getMsn(), unSchedulestatus);// Task #98
			setVoidTransaction(transactionID, originalTransactionID, Status.SC_160, TransactionType.Void);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processVoidUnSchJob method");
		return unSchedulestatus;
	}
	
	/**
	 * Method that process the adjustment for void request.
	 * @param vendTransactionDetails
	 * @param transactionID
	 * @param originalTransactionID
	 * @param timestamp
	 * @param pan
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private Boolean processVoidAdjustment(
			VendTxnWSDetails vendTransactionDetails, String transactionID, Calendar timestamp, String pan) 
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, 
		VMSInvalidPANException, PanNotFoundException, MPxNNotFoundException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processVoidAdjustment method");
		String msn = null;
		int manuType = 0;
		int deviceType = 0;
		MeterDetails meterDetails = null;
		String originalTransactionID = vendTransactionDetails.getTransactionID();
		Boolean transactionStatus = Boolean.TRUE;
		String transactionType = vendTransactionDetails.getTransactionType();
		if (!transactionType.equals(TransactionType.Reversal.toString())) {
			msn = vendTransactionDetails.getMsn();
			meterDetails = wsTransactionDAO.getMeterDetails(msn);
			manuType = meterDetails.getManuTypeID();
			deviceType = meterDetails.getDeviceTypeID();
		}
		if (transactionType.equals(TransactionType.ElectricPurchase.toString())) {
			transactionStatus = processElecticNegAdjust(vendTransactionDetails, transactionID,
					originalTransactionID, timestamp,pan,msn,manuType, deviceType);
		} else if (transactionType.equals(TransactionType.GasPurchase
				.toString())) {
			transactionStatus = processGasNegAdjust(vendTransactionDetails, transactionID,
					originalTransactionID, timestamp,pan,msn,manuType, deviceType);
		} else if (transactionType.equals(TransactionType.Reversal.toString())) {
			setVoidTransaction(transactionID, originalTransactionID, Status.SC_160, TransactionType.Void);
			transactionStatus = processReverseVoid(vendTransactionDetails,transactionID,originalTransactionID);
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Transaction type is not accepted");
			setVoidTransaction(transactionID,
					originalTransactionID, Status.SC_160, TransactionType.Void);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processVoidAdjustment method");
		return transactionStatus;

	}

	/**
	 * Method to process the eletric negative adjustment request
	 * @param vendTransactionDetails
	 * @param transactionID
	 * @param originalTransactionID
	 * @param timestamp
	 * @param pan
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private Boolean processElecticNegAdjust(
			VendTxnWSDetails vendTransactionDetails, String transactionID,
			String originalTransactionID, Calendar timestamp,String pan,
			String msn, int manuType,int deviceType)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processElecticNegAdjust method");
		String creditValue = "-" + vendTransactionDetails.getCreditValue();
		logger.info(Logger.EVENT_UNSPECIFIED,"Credit Value is " + creditValue);
		Long holdingPeriod = 0l;
		Boolean status = Boolean.FALSE;
		String source = vendTransactionDetails.getSource();
		String transactionPPKey = vendTransactionDetails.getPpKey();
		VendCreditType_type1 creditType = VendCreditType_type1.ADJUSTMENT;
		TransactionType txnType = TransactionType.VoidENegativeAdjustment;
		eCreditType typeOfCredit = vmsUtils.getTypeOfCredit(creditType);
		CreateVendResponse electricPaymentResponse = null;
		if (manuType == ManuTypeEnum.LG.getManuType()) {
			if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
				electricPaymentResponse = processElectricPaymentCode(
						txnType, typeOfCredit, creditValue, pan, holdingPeriod,
						originalTransactionID, transactionID, source, transactionPPKey,
						timestamp,msn,manuType,deviceType,VMS_USERNAME_VALUE,VMS_USERNAME_VALUE);
			} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
				WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
				String mpxn = getMPxNFromPan(wstransactionDAO, pan);
				electricPaymentResponse = processFuelAdjustCredit(txnType,
						creditValue, pan, mpxn, holdingPeriod,
						originalTransactionID, transactionID, source, transactionPPKey,
						timestamp,msn,manuType,VMS_USERNAME_VALUE,VMS_USERNAME_VALUE);	
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Device type not supported");
				// will not reach here...
			}
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Manufacturer type not supported");
			// will not reach here...
		}
		
		if (electricPaymentResponse != null) {
			String vendOutcomeCode = electricPaymentResponse.getPaymentResponse().getVendOutcome().getVendOutcomeCode().getValue();
			if (vendOutcomeCode.equals(VendOutcomeCode_type1.value1.getValue())) {
				setVendTxnStatus(vendTransactionDetails, wsTransactionDAO, Status.SC_145.getStatus());
				status = Boolean.TRUE;
			}
			logger.info(Logger.EVENT_UNSPECIFIED,"Vend Outcome code " + vendOutcomeCode);
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processElecticNegAdjust method");
		return status;
	}
	
	/**
	 * Method to process the gas negative adjustment request
	 * @param vendTransactionDetails
	 * @param transactionID
	 * @param originalTransactionID
	 * @param timestamp
	 * @param pan
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private Boolean processGasNegAdjust(VendTxnWSDetails vendTransactionDetails,
			String transactionID, String originalTransactionID, Calendar timestamp,String pan,
			String msn, int manuType,int deviceType)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processGasNegAdjust method");
		String creditValue = "-" + vendTransactionDetails.getCreditValue();
		logger.info(Logger.EVENT_UNSPECIFIED,"Credit Value is " + creditValue);
		Long holdingPeriod = 0l;
		Boolean status = Boolean.FALSE;
		String source = vendTransactionDetails.getSource();
		String transactionPPKey = vendTransactionDetails.getPpKey();
		TransactionType txnType = TransactionType.VoidGNegativeAdjustment;
		CreateVendResponse paymentResponse = null;

		if (manuType == ManuTypeEnum.LG.getManuType()) {
			if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
				paymentResponse = processGasPaymentCode(txnType,
						creditValue, pan, holdingPeriod, originalTransactionID,
						transactionID, source, transactionPPKey, timestamp,msn,manuType,deviceType,VMS_USERNAME_VALUE,VMS_USERNAME_VALUE);
			} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
				WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
				String mpxn = getMPxNFromPan(wstransactionDAO, pan);
				paymentResponse = processFuelAdjustCredit(txnType,
						creditValue, pan, mpxn, holdingPeriod,
						originalTransactionID, transactionID, source, transactionPPKey,
						timestamp,msn,manuType,VMS_USERNAME_VALUE,VMS_USERNAME_VALUE);	
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Device type not supported");
				// will not reach here...
			}
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Manufacturer type not supported");
			// will not reach here...
		}
		
		if (paymentResponse != null) {
			String vendOutcomeCode = paymentResponse.getPaymentResponse().getVendOutcome().getVendOutcomeCode().getValue();
			if (vendOutcomeCode.equals(VendOutcomeCode_type1.value1.getValue())) {
				setVendTxnStatus(vendTransactionDetails, wsTransactionDAO, Status.SC_145.getStatus());
				status = Boolean.TRUE;
			}
			logger.info(Logger.EVENT_UNSPECIFIED,"Vend Outcome code " + vendOutcomeCode);
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processGasNegAdjust method");
		return status;
	}
	
	/**
	 * Method to reschedule the job
	 * @param reversalOriginalTransactionID
	 * @param creditValue
	 * @param holdingPeriod
	 * @param pan
	 * @param transactionType
	 * @param vendCode
	 * @param transactionID
	 * @param timestamp
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private Status rescheduleJob(String reversalOriginalTransactionID,
			String creditValue, Long holdingPeriod, String pan,
			String transactionType, String vendCode, String transactionID,Date timestamp,String msn,Integer deviceType)
			throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering rescheduleJob method");
		int retryCount = 0; 
		
		Boolean status = vmsschedulerService.scheduleJob(retryCount,
				reversalOriginalTransactionID, holdingPeriod,pan, vendCode,
				transactionType.toString(), creditValue,timestamp,deviceType, true);
		logger.info(Logger.EVENT_UNSPECIFIED,"Schedule job call status " + status);
		Status responseStatus = Status.SC_160;
		if (status) {
			if (transactionType.equals(TransactionType.ElectricPurchase
					.toString())) {
				incrementCreditValue(msn, transactionType);
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Transaction type is gas purchase");
			}
		} else {
			responseStatus = Status.SC_165;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving rescheduleJob method");
		return responseStatus;
	}

	
	/**
	 * Method to increment the credit value
	 * @param msn
	 * @param transactionType
	 * @throws DBException
	 */
	private void incrementCreditValue(String msn, String transactionType) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering incrementCreditValue method");
		CreditIDCompKey creditIDCompKey = new CreditIDCompKey();
		creditIDCompKey.setMsn(msn);
		creditIDCompKey.setTransactionType(transactionType);
		int creditID = wsTransactionDAO.getCreditIDDetails(creditIDCompKey);
		logger.info(Logger.EVENT_UNSPECIFIED,"Credit Value is :" + creditID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving incrementCreditValue method");
	}
	
	/**
	 * Method to process the void of reversal transaction request
	 * @param vendTransactionDetails
	 * @param transactionID
	 * @param originalTransactionID
	 * @throws DBException
	 */
	private Boolean processReverseVoid(VendTxnWSDetails vendTransactionDetails,String transactionID,
			String originalTransactionID)
			throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processReverseVoid method");
		Boolean status = Boolean.FALSE;
		VendTxnWSDetails oldVendTransactionDetails = wsTransactionDAO
				.getVendTxnWSDetails(vendTransactionDetails.getOriginalTransactionID());
		String creditValue = oldVendTransactionDetails.getCreditValue();
		Long holdingPeriod = 0l;
		String pan = oldVendTransactionDetails.getPan();
		String transactionType = oldVendTransactionDetails.getTransactionType();
		String vendCode = oldVendTransactionDetails.getVendCode();
		Date timestamp = oldVendTransactionDetails.getVendcodeTimeStamp();
		String msn = oldVendTransactionDetails.getMsn();
		MeterDetails meterDetails = wsTransactionDAO.getMeterDetails(msn);
		int deviceType = meterDetails.getDeviceTypeID();
		Status responseStatus = rescheduleJob(vendTransactionDetails.getOriginalTransactionID(),
				creditValue, holdingPeriod, pan, transactionType, vendCode,
				transactionID,timestamp,msn,deviceType);
		setVoidTransaction(transactionID, originalTransactionID, responseStatus,
				TransactionType.VoidReversal);
		if (responseStatus.equals(Status.SC_160)) {
			setVendTxnStatus(vendTransactionDetails, wsTransactionDAO, Status.SC_135.getStatus());
			status = Boolean.TRUE;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processReverseVoid method");
		return status;

	}
	

	/**
	 * Method to prepare the fault response
	 * @param faultCode
	 * @param errorMessage
	 * @param paymentResponse
	 * @return
	 */
	private VoidVendResponse prepareVoidFaultResponse(
			VendOutcomeCode_type1 faultCode, String errorMessage,
			VoidVendResponse paymentResponse) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVoidFaultResponse method");
		logger.info(Logger.EVENT_UNSPECIFIED,"errorMessage " + errorMessage);
		VoidVendRequestResponseMessage voidVendRequestResponseMessage = new VoidVendRequestResponseMessage();
		VendOutcome vendOutcome = new VendOutcome();
		vendOutcome.setVendOutcomeCode(faultCode);
		vendOutcome.setVendOutcomeDescription(voidErrorMessage);
		voidVendRequestResponseMessage.setVendOutcome(vendOutcome);
		BG_Log log = vmsUtils.getBGLogForFault(faultCode, errorMessage);
		voidVendRequestResponseMessage.setLog(log);
		paymentResponse.setVoidTxnResponse(voidVendRequestResponseMessage);
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVoidFaultResponse method");
		return paymentResponse;
	}
}
