package com.centrica.vms.ws.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;
import LG_PAYMENT_CODE.PaymentCode.eCreditType;
import LG_PAYMENT_CODE.PaymentCode.eCurrency;
import LG_SMS_LIB.BreakupException;
import LG_SMS_LIB.LicenceException;

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
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.CreateVendResponse;

public class VMSAckVoidService extends VMSCommonPIService {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	protected final WSTransactionDAO wsTransactionDAO;
	private final VMSLGGeneratePhase2B phase2Service;
	private final VMSSchedulerServiceImpl vmsSchdulerService;
	
	/**
	 * Constructor
	 */
	public VMSAckVoidService() {
		wsTransactionDAO = new WSTransactionDAO();
		phase2Service = new VMSLGGeneratePhase2B();
		vmsSchdulerService = new VMSSchedulerServiceImpl();
	}
	
	/**
	 * Constructor
	 * @param wsTransactionDAO - WSTransactionDAO
	 * @param phase2Service - IVMSLGPaymentCodeService
	 * @param phase3Service - IVMSLGPaymentCodeService
	 * @param vmsSchdulerService - VMSSchedulerServiceImpl
	 */
	public VMSAckVoidService(final WSTransactionDAO wsTransactionDAO, final VMSLGGeneratePhase2B phase2Service, 
			 final VMSSchedulerServiceImpl vmsSchdulerService) {
		this.wsTransactionDAO = wsTransactionDAO;
		this.phase2Service = phase2Service;
		this.vmsSchdulerService = vmsSchdulerService;
	}
	
	/**
	 * Method that process the adjustment for the ACK received to the voided vend.
	 * @param vendTransactionDetails
	 * @param transactionID
	 * @return
	 * @throws EPaymentKeyInvalidException
	 * @throws EPaymentKeyOutOfRangeException
	 * @throws UnknownBreakupException
	 * @throws VMSInvalidPANException
	 * @throws PanNotFoundException
	 * @throws MPxNNotFoundException
	 * @throws LGLicenceException
	 * @throws DBException
	 * @throws VMSAppException
	 */
	protected Boolean processNegtiveAdjustment(
			VendTxnWSDetails vendTransactionDetails, String transactionID) 
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, 
		VMSInvalidPANException, PanNotFoundException, MPxNNotFoundException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processNegtiveAdjustment method");
		String msn = vendTransactionDetails.getMsn();
		Boolean transactionStatus = Boolean.TRUE;
		String transactionType = vendTransactionDetails.getTransactionType();
		String transactionPPKey = vendTransactionDetails.getPpKey();
		String creditValue = "-" + vendTransactionDetails.getCreditValue();
		if (transactionType.equals(TransactionType.ElectricPurchase.toString())) {
			transactionStatus = processElecticNegAdjust(creditValue, transactionPPKey, transactionID, transactionType,
					vendTransactionDetails.getSource(), vendTransactionDetails.getPan(), msn);
		} else if (transactionType.equals(TransactionType.GasPurchase
				.toString())) {
			transactionStatus = processGasNegAdjust(creditValue, transactionPPKey, transactionID, transactionType,
					vendTransactionDetails.getPan(), msn, vendTransactionDetails.getSource());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processNegtiveAdjustment method");
		return transactionStatus;

	}
	
	/**
	 * Method to process the electric negative adjustment request
	 * @param creditValue
	 * @param paymentKeyHex
	 * @param transactionID
	 * @param source
	 * @param pan
	 * @param msn
	 * @return
	 * @throws EPaymentKeyInvalidException
	 * @throws EPaymentKeyOutOfRangeException
	 * @throws UnknownBreakupException
	 * @throws LGLicenceException
	 * @throws DBException
	 * @throws VMSAppException
	 */
	private Boolean processElecticNegAdjust(String creditValue, String paymentKeyHex,String transactionID, String transactionType, String source,
			String pan, String msn)	throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, 
			UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processElecticNegAdjust method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID : " + transactionID);
		logger.info(Logger.EVENT_UNSPECIFIED,"Credit Value is " + creditValue);
		Boolean status = Boolean.FALSE;
		CreateVendResponse electricPaymentResponse = null;
		MeterDetails meterDetails = wsTransactionDAO.getMeterDetails(msn);
		int manuType = meterDetails.getManuTypeID();
		int deviceType = meterDetails.getDeviceTypeID();
		if (manuType == ManuTypeEnum.LG.getManuType()) {
			if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
				electricPaymentResponse = generateElectricVendCode(creditValue, pan, msn, source, transactionID, 
						transactionType, paymentKeyHex, manuType, deviceType);
			} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
				String mpxn = getMPxNFromPan(wsTransactionDAO, pan);
				electricPaymentResponse = processFuelAdjustCredit(creditValue, pan, mpxn, msn, source, transactionID, 
						transactionType, paymentKeyHex, manuType);	
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Device type not supported");
				// will not reach here...
			}
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Manufacturer type not supported");
			// will not reach here...
		}
		
		if (electricPaymentResponse != null) {
			String vendOutcomeCode = electricPaymentResponse.getPaymentResponse()
				.getVendOutcome().getVendOutcomeCode().getValue();
			logger.info(Logger.EVENT_UNSPECIFIED,"Vend Outcome code " + vendOutcomeCode);	
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processElecticNegAdjust method");
		return status;
	}
	

	/**
	 * Method to generate the electric vend code
	 * @param creditValue
	 * @param pan
	 * @param msn
	 * @param source
	 * @param transactionID
	 * @param paymentKey
	 * @return
	 * @throws EPaymentKeyInvalidException
	 * @throws EPaymentKeyOutOfRangeException
	 * @throws UnknownBreakupException
	 * @throws LGLicenceException
	 * @throws DBException
	 * @throws VMSAppException
	 */
	private CreateVendResponse generateElectricVendCode (String creditValue, String pan, String msn, String source,
			String transactionID, String transactionType, String paymentKey, int manuType, int deviceType)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateElectricVendCode method");
		logger.info(Logger.EVENT_UNSPECIFIED,"creditValue,paymentKey " + creditValue+paymentKey);
		CreateVendResponse paymentResponse = new CreateVendResponse();
		String vendCode = null;
		vendCode = getPaymentVendCodeService(manuType, deviceType, creditValue, msn, paymentKey, paymentResponse);
		if (vendCode != null) {
			paymentResponse = processVendCode(transactionID, transactionType, paymentKey, vendCode, pan, msn, source, creditValue, 
					Calendar.getInstance().getTime(), deviceType);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateElectricVendCode method");
		return paymentResponse;
	}
	

	/**
	 * @param creditValue
	 * @param pan
	 * @param msn
	 * @param source
	 * @param transactionID
	 * @param transactionType
	 * @param manuType
	 * @param deviceType
	 * @return
	 * @throws DBException
	 * @throws VMSAppException
	 */
	private CreateVendResponse processFuelAdjustCredit(String creditValue, String pan, String mpxn, String msn, String source,
			String transactionID, String transactionType, String ppKey, int manuType)
			throws DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processFuelAdjustCredit method");
		logger.info(Logger.EVENT_UNSPECIFIED,"creditValue " + creditValue);
		CreateVendResponse paymentResponse = new CreateVendResponse();
		
		StringBuffer txnType = new StringBuffer();
		if (transactionType.equals(TransactionType.ElectricPurchase.toString())) {
			txnType.append(TransactionType.VoidENegativeAdjustment.toString());
		} else if (transactionType.equals(TransactionType.GasPurchase
				.toString())) {
			txnType.append(TransactionType.VoidGNegativeAdjustment.toString());
		}
		Date timestamp = Calendar.getInstance().getTime();
		updateTransactionDetails(transactionID, txnType, ppKey, "", pan, msn, source, creditValue, timestamp);
		Boolean status = vmsSchdulerService.scheduleAdjustJob(0,transactionID,
				new Long(0), mpxn, creditValue, timestamp, false);
		logger.info(Logger.EVENT_UNSPECIFIED,"Schedule job call status " + status);		
		paymentResponse = getPaymentResponse("", paymentResponse, status);
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processFuelAdjustCredit method");
		return paymentResponse;
	}
	
	/**
	 * Generate vend code using L+G Algorithm
	 * @param manuType
	 * @param deviceType
	 * @param creditValue
	 * @param msn
	 * @param paymentKey
	 * @param paymentResponse
	 * @return
	 * @throws EPaymentKeyInvalidException
	 * @throws EPaymentKeyOutOfRangeException
	 * @throws UnknownBreakupException
	 * @throws LGLicenceException
	 * @throws DBException
	 * @throws VMSAppException
	 */
	private String getPaymentVendCodeService(int manuType,
			int deviceType, String creditValue, String msn, String paymentKey, CreateVendResponse paymentResponse) 
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, 
			LGLicenceException, DBException, VMSAppException {
		String vendCode = null;
		if (manuType == ManuTypeEnum.LG.getManuType()) {
			if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
				eCreditType typeOfCredit = new VMSUtils().getTypeOfCredit(VendCreditType_type1.ADJUSTMENT);
				int creditID = getCreditID(TransactionType.VoidENegativeAdjustment, msn, wsTransactionDAO);
				String licenseKey = new VMSUtils().loadProperties().getProperty("LicenseKey");
				logger.info(Logger.EVENT_UNSPECIFIED,"Credit ID,License Key "
						+ new Object[] { creditID, licenseKey });
				try {
				vendCode = phase2Service.generateElectricPaymentCode(licenseKey, typeOfCredit,
						paymentKey, creditID, creditValue);
				} catch (BreakupException ex) {
					logger.error(Logger.EVENT_FAILURE,"BreakupException " + ex.get_ExceptionType());
					handleBreakupException(paymentResponse, ex);
				} catch (LicenceException ex) {
					throw new LGLicenceException();
				} catch (Exception ex) {
					throw new VMSAppException();
				}
			} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
				vendCode = "0";
			} else { // device type not found
				paymentResponse = prepareFaultResponse(
						VendOutcomeCode_type1.value18, deviceTypeNotFound,
						paymentResponse);
			}
		} else { // manufacturer type not found
			paymentResponse = prepareFaultResponse(
			VendOutcomeCode_type1.value17, manuTypeNotFound,
			paymentResponse);
		}
		return vendCode;
	}

	/**
	 * Method to process the gas negative adjustment request
	 * @param creditValue
	 * @param paymentKeyHex
	 * @param transactionID
	 * @param pan
	 * @param msn
	 * @param source
	 * @return
	 * @throws EPaymentKeyInvalidException
	 * @throws EPaymentKeyOutOfRangeException
	 * @throws UnknownBreakupException
	 * @throws LGLicenceException
	 * @throws DBException
	 * @throws VMSAppException
	 */
	protected Boolean processGasNegAdjust(String creditValue, String paymentKeyHex,
			String transactionID, String transactionType, String pan, String msn, String source)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processGasNegAdjust method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Credit Value is " + creditValue);
		Boolean status = Boolean.FALSE;
		CreateVendResponse paymentResponse = null;
		MeterDetails meterDetails = wsTransactionDAO.getMeterDetails(msn);
		int manuType = meterDetails.getManuTypeID();
		int deviceType = meterDetails.getDeviceTypeID();
		if (manuType == ManuTypeEnum.LG.getManuType()) {
			if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
				paymentResponse = generateGasVendCode(creditValue, pan, msn, source, transactionID, 
						transactionType, paymentKeyHex, manuType, deviceType);
			} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
				String mpxn = getMPxNFromPan(wsTransactionDAO, pan);
				paymentResponse = processFuelAdjustCredit(creditValue, pan, mpxn, msn, source, transactionID, 
						transactionType, paymentKeyHex, manuType);	
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
			logger.info(Logger.EVENT_UNSPECIFIED,"Vend Outcome code " + vendOutcomeCode);	
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processGasNegAdjust method");
		return status;
	}

	/**
	 * Method to generate the gas vend code
	 * @param creditValue
	 * @param pan
	 * @param msn
	 * @param source
	 * @param transactionID
	 * @param paymentKeyHex
	 * @return
	 * @throws EPaymentKeyInvalidException
	 * @throws EPaymentKeyOutOfRangeException
	 * @throws UnknownBreakupException
	 * @throws LGLicenceException
	 * @throws DBException
	 * @throws VMSAppException
	 */
	private CreateVendResponse generateGasVendCode(String creditValue, String pan, String msn, String source,
			String transactionID, String transactionType, String paymentKeyHex, int manuType, int deviceType)
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateGasVendCode method");
		CreateVendResponse paymentResponse = new CreateVendResponse();
		String vendCode = null;
		String originalCreditValue = creditValue;
		char[] returnCode = null;
		logger.info(Logger.EVENT_UNSPECIFIED,"device type " + deviceType);

		if (manuType == ManuTypeEnum.LG.getManuType()) {
			if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
				Date vendTimestamp = Calendar.getInstance().getTime();
				try {
					String licenseKey = new VMSUtils().loadProperties().getProperty("LicenseKey");
					eCurrency typeOfCurrency = eCurrency.Pounds;
					logger.info(Logger.EVENT_UNSPECIFIED,"License key,Currency type"
							+ new Object[] { licenseKey, typeOfCurrency });
					returnCode = phase2Service.generateGasMacCode(licenseKey, originalCreditValue, vendTimestamp, paymentKeyHex);
				} catch (BreakupException ex) {
					logger.error(Logger.EVENT_FAILURE,"BreakupException " + ex.get_ExceptionType());
					handleBreakupException(paymentResponse, ex);
				} catch (LicenceException ex) {
					throw new LGLicenceException();
				} catch (Exception ex) {
					throw new VMSAppException();
				}
				vendCode = new VMSUtils().stringToHexString(returnCode);
			} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
				vendCode = "0";
			} else { // device type not found
				paymentResponse = prepareFaultResponse(
						VendOutcomeCode_type1.value18, deviceTypeNotFound,
						paymentResponse);
			}
		} else { // manufacturer type not found
			paymentResponse = prepareFaultResponse(
			VendOutcomeCode_type1.value17, manuTypeNotFound,
			paymentResponse);
		}
		
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend code " + vendCode);

		if (vendCode != null) {
			paymentResponse = processVendCode(transactionID, transactionType, paymentKeyHex, vendCode, pan, msn, 
					source, creditValue, Calendar.getInstance().getTime(), deviceType);
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateGasVendCode method");
		return paymentResponse;
	}
	
	/**
	 * Method to process the vend code
	 * @param transactionID
	 * @param ppKey
	 * @param vendCode
	 * @param pan
	 * @param msn
	 * @param source
	 * @param creditValue
	 * @param timestamp
	 * @param deviceType
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse processVendCode(String transactionID, String transactionType, String ppKey,
			String vendCode, String pan, String msn, String source, String creditValue, Date timestamp, Integer deviceType)
	throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processVendCode method");
		StringBuffer txnType = new StringBuffer();
		if (transactionType.equals(TransactionType.ElectricPurchase.toString())) {
			txnType.append(TransactionType.VoidENegativeAdjustment.toString());
		} else if (transactionType.equals(TransactionType.GasPurchase
				.toString())) {
			txnType.append(TransactionType.VoidGNegativeAdjustment.toString());
		}
		updateTransactionDetails(transactionID, txnType, ppKey, vendCode, pan, msn, source, creditValue, timestamp);
		CreateVendResponse paymentResponse = new CreateVendResponse();
		Boolean status = vmsSchdulerService.scheduleJob(0,transactionID,
				new Long(0), pan, vendCode, txnType.toString(),	creditValue, timestamp, deviceType, false);
		logger.info(Logger.EVENT_UNSPECIFIED,"Schedule job call status " + status);		
		paymentResponse = getPaymentResponse(vendCode, paymentResponse, status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processVendCode method");
		return paymentResponse;
	}
	
	/** 
	 * Update vend transaction table
	 * @param ppKey
	 * @param vendCode
	 * @param pan
	 * @param msn
	 * @param source
	 * @param creditValue
	 * @param timestamp
	 * @throws DBException
	 */
	private void updateTransactionDetails(String transactionID, StringBuffer transactionType, String ppKey, String vendCode, String pan, 
			String msn, String source, String creditValue, Date timestamp) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateTransactionDetails method");
		VendTxnWSDetails transactionDetails = wsTransactionDAO.getVendTxnWSDetails(transactionID);
		boolean isVoidForReversal = (transactionDetails == null);
		// if the void was triggered by an internal ack handling for reversed vend
		if (isVoidForReversal) {
			transactionDetails = new VendTxnWSDetails();
			transactionDetails.setTransactionID(transactionID);
			transactionDetails.setOriginalTransactionID(removePrefix(transactionID)); 
			transactionDetails.setTxnStatusDetails(new HashSet<VendTxnStatus>());
		}
		transactionDetails.setPan(pan);
		transactionDetails.setVendCode(vendCode);
		transactionDetails.setMsn(msn);
		creditValue = getCreditValue(creditValue); // remove the negative sign
		transactionDetails.setCreditValue(creditValue);
		transactionDetails.setTransactionType(transactionType.toString());
		transactionDetails.setSource(source);
		transactionDetails.setPpKey(ppKey);
		transactionDetails.setActualTimeStamp(timestamp);
		transactionDetails.setVendcodeTimeStamp(timestamp);
		transactionDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		Set<VendTxnStatus> txnStatusDetails = transactionDetails.getTxnStatusDetails();
		vendTxnStatus.setStatus(new VMSUtils().getVMSStatus(Status.SC_115.getStatus())); // Vend code generated
		vendTxnStatus.setUpdatedTimeStamp(new Date());
		txnStatusDetails.add(vendTxnStatus);
		transactionDetails.setTxnStatusDetails(txnStatusDetails);
		
		if (isVoidForReversal) {
			wsTransactionDAO.insert(transactionDetails);
		} else {
			wsTransactionDAO.update(transactionDetails);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateTransactionDetails method");
	}

	private String removePrefix(String transactionID) {
		return StringUtils.remove(transactionID, "VOID_");
	}
}
