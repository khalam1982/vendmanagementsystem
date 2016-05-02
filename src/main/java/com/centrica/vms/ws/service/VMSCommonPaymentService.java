package com.centrica.vms.ws.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;
import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;
import LG_PAYMENT_CODE.PaymentCode.eCreditType;
import LG_PAYMENT_CODE.PaymentCode.eCurrency;
import LG_SMS_LIB.BreakupException;
import LG_SMS_LIB.LicenceException;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.FuelTypeEnum;
import com.centrica.vms.common.ManuTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.EPaymentKeyInvalidException;
import com.centrica.vms.exception.EPaymentKeyOutOfRangeException;
import com.centrica.vms.exception.LGLicenceException;
import com.centrica.vms.exception.MPxNNotFoundException;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.exception.PanNotFoundException;
import com.centrica.vms.exception.UnknownBreakupException;
import com.centrica.vms.exception.VMSAppException;
import com.centrica.vms.exception.VMSInvalidHoldingPeriodException;
import com.centrica.vms.exception.VMSInvalidPANException;
import com.centrica.vms.exception.VMSInvalidSourceException;
import com.centrica.vms.exception.VMSInvalidTransactionIdException;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.service.VMSSchedulerService;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;
import com.centrica.vms.ws.sap.service.CreateVendResponse;

class VMSCommonPaymentService extends VMSCommonPIService {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	/**
	 * Method to validate the payment request (say, pan number, source and holding period)
	 * @param source
	 * @param holdingPeriod
	 * @return 
	 * @throws VMSInvalidTransactionIdException 
	 */
	protected Boolean validatePaymentRequest(CreateVendResponse paymentResponse, Long holdingPeriod, String source, 
			VendCreditType_type1 creditType, String transactionId) throws VMSInvalidHoldingPeriodException, VMSInvalidSourceException, VMSInvalidTransactionIdException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validatePaymentRequest method");
		VMSUtils vmsUtils = new VMSUtils();
		Boolean isValidatePaymentRequest = Boolean.FALSE;
		if (creditType.equals(VendCreditType_type1.ADJUSTMENT) && !vmsUtils.isValidHoldingPeriod(holdingPeriod)) {
			logger.debug(Logger.EVENT_SUCCESS,"Leaving validatePaymentRequest method ");
			throw new VMSInvalidHoldingPeriodException();
		}else if (!vmsUtils.isValidSource(source)) {
			logger.debug(Logger.EVENT_SUCCESS,"Leaving validatePaymentRequest method ");
			throw new VMSInvalidSourceException();
		}else if (creditType == VendCreditType_type1.PURCHASE && VMSUtils.internalChannel(source) && !vmsUtils.isValidTransactionId(transactionId)) {
			logger.debug(Logger.EVENT_SUCCESS,"Leaving validatePaymentRequest method ");
			throw new VMSInvalidTransactionIdException();
		}else{
			isValidatePaymentRequest = Boolean.TRUE;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validatePaymentRequest method ");
		return isValidatePaymentRequest;
	}


	/**
	 * Method to process the payment request
	 * @param creditType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param source
	 * @param transactionID
	 * @param timestamp
	 * @param originalTransactionID
	 * @param msn
	 * @return
	 * @throws DBException
	 * @throws MSNNotFoundException
	 */
	protected CreateVendResponse processPaymentRequest(
			TransactionType transactionType, String creditValue, String pan,
			Long holdingPeriod, String source, String transactionID,
			Calendar timestamp, String originalTransactionID, MeterDetails meterDetails, 
			eCreditType typeOfCredit, String createdBy,String updatedBy)
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, 
		VMSInvalidPANException, PanNotFoundException, MPxNNotFoundException,
		MSNNotFoundException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processPaymentRequest method");
		CreateVendResponse paymentResponse = new CreateVendResponse();
		int fuelType = meterDetails.getFuelTypeID();
		int manuType = meterDetails.getManuTypeID();
		int deviceType = meterDetails.getDeviceTypeID();
		String paymentKey = meterDetails.getPrepayKey();
		String msn = meterDetails.getMsn();
		logger.info(Logger.EVENT_UNSPECIFIED,"Fuel type " + fuelType);
		if (fuelType == FuelTypeEnum.ELECTRIC.getFuelType()){
			paymentResponse = generateElectricPaymentCode(transactionType, typeOfCredit,
					creditValue, pan, holdingPeriod, originalTransactionID,
					transactionID, source, paymentKey,timestamp,msn,manuType, deviceType, createdBy,updatedBy);
		} else {
			paymentResponse = generateGasPaymentCode(transactionType, typeOfCredit, creditValue,
					pan, holdingPeriod, originalTransactionID, transactionID,
					source, paymentKey, timestamp,msn,manuType, deviceType, createdBy,updatedBy);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processPaymentRequest method");
		return paymentResponse;
	}
	
	/**
	 * Method to get the MSN from PAN
	 * @param wstransactionDAO
	 * @param pan
	 * @return msn
	 * @throws MPxNNotFoundException
	 * @throws PanNotFoundException
	 * @throws DBException
	 */
	protected String getMSNFromPan(WSTransactionDAO wstransactionDAO, String pan)
		throws VMSInvalidPANException, PanNotFoundException, MPxNNotFoundException, DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMSNFromMPxN method");
		String mpxn = getMPxNFromPan(wstransactionDAO, pan);
		logger.info(Logger.EVENT_UNSPECIFIED,"MPxN :" + mpxn);
		String msn = getMSNFromMPxN(wstransactionDAO, mpxn);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMSNFromMPxN method");
		return msn;
	}

	/**
	 * Method to get the MSN from MPxN
	 * @param wstransactionDAO
	 * @param mpxn
	 * @return msn
	 * @throws MPxNNotFoundException
	 * @throws DBException
	 */
	private String getMSNFromMPxN(WSTransactionDAO wstransactionDAO, String mpxn) throws MPxNNotFoundException, DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMSNFromMPxN method");
		String msn =  null;
		PremiseDetails premiseDetails = wstransactionDAO.getPremiseDetails(mpxn);
		msn = premiseDetails.getMSN();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMSNFromMPxN method");
		return msn;
	}
	

	/**
	 * Method to process the electric payment code request
	 * @param creditType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param paymentKey
	 * @param timestamp
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse generateElectricPaymentCode(
			TransactionType transactionType, eCreditType typeOfCredit, String creditValue, String pan,
			Long holdingPeriod, String originalTransactionID,
			String transactionID, String source, String paymentKey,Calendar timestamp,String msn,
			int manuType, int deviceType, String createdBy,String updatedBy)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateElectricPaymentCode method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Request details"
				+ new Object[] { transactionType, creditValue, pan, holdingPeriod,
						originalTransactionID, transactionID, source,
						paymentKey,timestamp,msn });
		CreateVendResponse paymentResponse = processElectricPaymentCode(
				transactionType, typeOfCredit, creditValue, pan, holdingPeriod,
				originalTransactionID, transactionID, source, paymentKey,timestamp,msn,
				manuType, deviceType,createdBy,updatedBy);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateElectricPaymentCode method");
		return paymentResponse;
	}


	/**
	 * Method to process the gas payment code request
	 * @param creditType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param paymentKeyHex
	 * @param timestamp
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse generateGasPaymentCode(
			TransactionType transactionType, eCreditType typeOfCredit, String creditValue, String pan,
			Long holdingPeriod, String originalTransactionID,
			String transactionID, String source, String paymentKeyHex,
			Calendar timestamp,String msn,int manuType, int deviceType,String createdBy,String updatedBy)
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateGasPaymentCode method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Request details"
				+ new Object[] { transactionType, creditValue, pan, holdingPeriod,
						originalTransactionID, transactionID, paymentKeyHex,
						source, timestamp,msn });
		CreateVendResponse paymentResponse = processGasPaymentCode(
				transactionType, creditValue, pan, holdingPeriod,
				originalTransactionID, transactionID, source, paymentKeyHex,
				timestamp,msn,manuType, deviceType,createdBy,updatedBy);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateGasPaymentCode method");
		return paymentResponse;
	}

	/**
	 * Method to process the electric payment code
	 * @param transactionType
	 * @param creditType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param paymentKey
	 * @param dateTime
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	protected CreateVendResponse processElectricPaymentCode (
			TransactionType transactionType, eCreditType typeOfCredit,
			String creditValue, String pan, Long holdingPeriod,
			String originalTransactionID, String transactionID, String source,
			String paymentKey,Calendar timestamp,String msn,int manuType, int deviceType, String createdBy,String updatedBy)
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processElectricPaymentCode method");
		CreateVendResponse paymentResponse = new CreateVendResponse();
		WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
		holdingPeriod = getHoldingPeriod(holdingPeriod, source, wstransactionDAO);
		int creditID = getCreditID(transactionType, msn, wstransactionDAO);
		logger.info(Logger.EVENT_UNSPECIFIED,"Credit ID "
				+ new Object[] { creditID});
		paymentResponse = generateElectricVendCode(transactionType,
				creditValue, pan, holdingPeriod, originalTransactionID,
				transactionID, source, paymentResponse, wstransactionDAO,
				creditID, typeOfCredit, paymentKey,timestamp,msn,manuType,deviceType,createdBy,updatedBy);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processElectricPaymentCode method");
		return paymentResponse;
	}

	/**
	 * Method to process the electric adjust credit
	 * @param transactionType
	 * @param creditType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param paymentKey
	 * @param dateTime
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	protected CreateVendResponse processFuelAdjustCredit (
			TransactionType transactionType, String creditValue, String pan, String mpxn, Long holdingPeriod,
			String originalTransactionID, String transactionID, String source,
			String paymentKey,Calendar timestamp,String msn,int manuType, String createdBy,String updatedBy)
		throws DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processFuelAdjustCredit method");
		WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
		holdingPeriod = getHoldingPeriod(holdingPeriod, source, wstransactionDAO);
		CreateVendResponse paymentResponse = invokeAdjustCreditService(transactionType,
				creditValue, pan, mpxn, holdingPeriod, originalTransactionID,
				transactionID, source, wstransactionDAO,
				paymentKey,timestamp,msn,manuType,createdBy,updatedBy);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processFuelAdjustCredit method");
		return paymentResponse;
	}
	

	/**
	 * Method to generate the electric vend code
	 * @param transactionType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param paymentResponse
	 * @param wstransactionDAO
	 * @param licenseKey
	 * @param creditID
	 * @param typeOfCredit
	 * @param paymentKey
	 * @param dateTime
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse generateElectricVendCode (
			TransactionType transactionType, String creditValue, String pan,
			Long holdingPeriod, String originalTransactionID,
			String transactionID, String source,
			CreateVendResponse paymentResponse,
			WSTransactionDAO wstransactionDAO, int creditID,
			eCreditType typeOfCredit, String paymentKey,Calendar timestamp,String msn, 
			int manuType, int deviceType, String createdBy, String updatedBy)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateElectricVendCode method");
		logger.info(Logger.EVENT_UNSPECIFIED,"creditValue,typeOfCredit,paymentKey,creditID,dateTime " + creditValue+typeOfCredit+paymentKey+creditID+timestamp.getTime());
		String vendCode = null;
		Date actualTimeStamp = timestamp.getTime();
		Date vendTimeStamp = actualTimeStamp;
		VMSUtils vmsUtils = new VMSUtils();
		String licenseKey = null;
		try {
			if (manuType == ManuTypeEnum.LG.getManuType()) {
				if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
					licenseKey = vmsUtils.loadProperties().getProperty("LicenseKey");
					logger.info(Logger.EVENT_UNSPECIFIED,"License key for Ph2 device"+licenseKey);
					VMSLGGeneratePhase2B electricPhase2BPaymentService = new VMSLGGeneratePhase2B();
					vendCode = electricPhase2BPaymentService.generateElectricPaymentCode(
							licenseKey, typeOfCredit, paymentKey, creditID, creditValue);
				} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
					licenseKey = vmsUtils.loadProperties().getProperty("LicenseKeyPh3");
					logger.info(Logger.EVENT_UNSPECIFIED,"License key for Phase3 device"+licenseKey);
					VMSLGGeneratePhase3 electricPhase3PaymentService = new VMSLGGeneratePhase3();
					vendTimeStamp = getVendCodeTimeStamp(creditValue, msn, wstransactionDAO, timestamp,
							actualTimeStamp,TransactionType.ElectricPurchase);
					int value = new Integer(creditValue).intValue() / 100;
					vendCode = electricPhase3PaymentService.generateElectricPaymentCode(
							licenseKey, paymentKey, value, 
							eu.landisgyr.lgsecure3.PaymentCode.eCurrency.Pounds, vendTimeStamp);
				} else { // device type not found
					updateFailedVendTxn(transactionType, pan, 
							source, transactionID, 
							createdBy, updatedBy, creditValue,paymentKey, 
							Status.SC_75, msn, holdingPeriod);
					paymentResponse = prepareFaultResponse(
							VendOutcomeCode_type1.value18, deviceTypeNotFound,
							paymentResponse);
				}
			} else { // manufacturer type not found
				updateFailedVendTxn(transactionType, pan, 
						source, transactionID, 
						createdBy, updatedBy, creditValue,paymentKey, 
						Status.SC_70, msn, holdingPeriod);
				paymentResponse = prepareFaultResponse(
				VendOutcomeCode_type1.value17, manuTypeNotFound,
				paymentResponse);
			}
		} catch (BreakupException ex) {
			logger.error(Logger.EVENT_FAILURE,"BreakupException " + ex.get_ExceptionType());
			handleBreakupException(paymentResponse, ex);
		} catch (LicenceException ex) {
			throw new LGLicenceException();
		} catch (Exception ex) {
			throw new VMSAppException();
		}
		if (vendCode != null) {
			paymentResponse = processElectricVendCode(transactionType,
					creditValue, pan, holdingPeriod, originalTransactionID,
					transactionID, source, wstransactionDAO, vendCode,
					paymentKey,actualTimeStamp,vendTimeStamp,msn,createdBy,updatedBy, deviceType);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateElectricVendCode method");
		return paymentResponse;
	}

	
	/**
	 *  Method to process the electric vend code
	 * @param transactionType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param wstransactionDAO
	 * @param vendCode
	 * @param paymentKey
	 * @param dateTime
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse processElectricVendCode(
			TransactionType transactionType, String creditValue, String pan,
			Long holdingPeriod, String originalTransactionID,
			String transactionID, String source,
			WSTransactionDAO wstransactionDAO, String vendCode,
			String paymentKey,Date actualTimeStamp,Date vendcodeTimeStamp,String msn,String createdBy,String updatedBy, Integer deviceType) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processElectricVendCode method");
		VendTxnWSDetails transactionDetails = setTransactionDetails(
				transactionType, pan, vendCode, source, holdingPeriod,
				originalTransactionID, transactionID, creditValue, null,
				Status.SC_115, paymentKey,actualTimeStamp,vendcodeTimeStamp,msn,createdBy,updatedBy);
		CreateVendResponse paymentResponse = processVendCode(wstransactionDAO,
				transactionDetails, transactionID, holdingPeriod, vendCode,
				pan, transactionType, creditValue,actualTimeStamp, deviceType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processElectricVendCode method");
		return paymentResponse;
	}

	/**
	 * Method to process gas payment code
	 * @param transactionType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param paymentKeyHex
	 * @param timestamp
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	protected CreateVendResponse processGasPaymentCode(
			TransactionType transactionType, String creditValue, String pan,
			Long holdingPeriod, String originalTransactionID,
			String transactionID, String source, String paymentKeyHex,
			Calendar timestamp,String msn,int manuType,int deviceType,String createdBy,String updatedBy)
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processGasPaymentCode method");
		
		WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
		holdingPeriod = getHoldingPeriod(holdingPeriod, source, wstransactionDAO);
		CreateVendResponse paymentResponse = new CreateVendResponse();
		paymentResponse = generateGasVendCode(transactionType, creditValue,
				pan, holdingPeriod, source,
				originalTransactionID, transactionID, wstransactionDAO,
				paymentKeyHex, paymentResponse, timestamp,msn,manuType, deviceType,createdBy,updatedBy);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processGasPaymentCode method ");
		return paymentResponse;
	}

	/**
	 * Method to generate the gas vend code
	 * @param transactionType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param source
	 * @param typeOfCurrency
	 * @param originalTransactionID
	 * @param transactionID
	 * @param wstransactionDAO
	 * @param paymentKeyHex
	 * @param licenseKey
	 * @param paymentResponse
	 * @param timestamp
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse generateGasVendCode(
			TransactionType transactionType, String creditValue, String pan,
			Long holdingPeriod, String source, 
			String originalTransactionID, String transactionID,
			WSTransactionDAO wstransactionDAO, String paymentKeyHex,
			CreateVendResponse paymentResponse,
			Calendar timestamp,String msn,int manuType,int deviceType,String createdBy,String updatedBy)
		throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException, UnknownBreakupException, LGLicenceException, DBException, VMSAppException {
		String vendCode = null;
		String originalCreditValue = creditValue;
		Date actualTimeStamp = timestamp.getTime();
		Date vendTimeStamp = actualTimeStamp;
		char[] returnCode = null;
		logger.info(Logger.EVENT_UNSPECIFIED,"device type " + deviceType);
		
		VMSUtils vmsUtils = new VMSUtils();
		eCurrency typeOfCurrency = eCurrency.Pounds;
		eu.landisgyr.lgsecure3.PaymentCode.eCurrency typeOfCurrencyPh3 = 
			eu.landisgyr.lgsecure3.PaymentCode.eCurrency.Pounds;
		
		
		if (manuType == ManuTypeEnum.LG.getManuType()) {
			if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
				VMSLGGeneratePhase2B gasPhase2BPaymentService = new VMSLGGeneratePhase2B();	
				String licenseKey = vmsUtils.loadProperties().getProperty("LicenseKey");
				logger.info(Logger.EVENT_UNSPECIFIED,"License key,Currency type"
						+ new Object[] { licenseKey, typeOfCurrency });
				if (transactionType.toString().equals(
						TransactionType.VoidGNegativeAdjustment.toString())/* Drop 2 - KARTHIK starts here */
						|| transactionType.toString().equals(TransactionType.GNegativeAdjustment.toString())|| transactionType.toString().equals(
								TransactionType.GPositiveAdjustment.toString())) {
						/* Drop 2 - KARTHIK ends here */

						try {
							returnCode = gasPhase2BPaymentService.generateGasMacCode(licenseKey, originalCreditValue, vendTimeStamp, paymentKeyHex);
						} catch (BreakupException ex) {
							logger.error(Logger.EVENT_FAILURE,"BreakupException " + ex.get_ExceptionType());
							handleBreakupException(paymentResponse, ex);
						} catch (LicenceException ex) {
							throw new LGLicenceException();
						} catch (Exception ex) {
							throw new VMSAppException();
						}
						vendCode = new VMSUtils().stringToHexString(returnCode);
					} else {
						vendTimeStamp = getVendCodeTimeStamp(creditValue, msn, wstransactionDAO, timestamp,
							actualTimeStamp,TransactionType.GasPurchase);
						creditValue = new Integer(
							(new Integer(creditValue).intValue() / 100)).toString(); // Pence is  converted into pounds
					
						try {
							vendCode = gasPhase2BPaymentService.generateGasPaymentCode(licenseKey, creditValue, typeOfCurrency, vendTimeStamp, paymentKeyHex);
						} catch (BreakupException ex) {
							logger.error(Logger.EVENT_FAILURE,"BreakupException " + ex.get_ExceptionType());
							handleBreakupException(paymentResponse, ex);
						} catch (LicenceException ex) {
							throw new LGLicenceException();
						} catch (Exception ex) {
							logger.error(Logger.EVENT_FAILURE,"VMS Application Exception " + ex.getMessage());
							throw new VMSAppException();
						}
					}
			} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
				VMSLGGeneratePhase3 gasPhase3PaymentService = new VMSLGGeneratePhase3();

				String licenseKey = vmsUtils.loadProperties().getProperty("LicenseKeyPh3");
				logger.info(Logger.EVENT_UNSPECIFIED,"License key,Currency type"
						+ new Object[] { licenseKey, typeOfCurrencyPh3 });
				if (transactionType.toString().equals(
						TransactionType.VoidGNegativeAdjustment.toString())/* Drop 2 - KARTHIK starts here */
						|| transactionType.toString().equals(TransactionType.GNegativeAdjustment.toString())|| transactionType.toString().equals(
								TransactionType.GPositiveAdjustment.toString())) {
						vendCode = "0"; // FOR PHASE 3 NO ADJUSTMENT VEND CODE
					} else {
						vendTimeStamp = getVendCodeTimeStamp(creditValue, msn, wstransactionDAO, timestamp,
							actualTimeStamp,TransactionType.GasPurchase);
						creditValue = new Integer(
							(new Integer(creditValue).intValue() / 100)).toString(); // Pence is  converted into pounds
					
						try {
							int value = new Integer(creditValue.trim()).intValue();
							logger.info(Logger.EVENT_UNSPECIFIED,"License key for Ph3 L+G : " + licenseKey);
							logger.info(Logger.EVENT_UNSPECIFIED,"Converted to pounds value : " + value);
							vendCode = gasPhase3PaymentService.generateGasPaymentCode(licenseKey, value, 
									typeOfCurrencyPh3, vendTimeStamp, paymentKeyHex);
						} catch (BreakupException ex) {
							logger.error(Logger.EVENT_FAILURE,"BreakupException " + ex.get_ExceptionType());
							handleBreakupException(paymentResponse, ex);
						} catch (LicenceException ex) {
							throw new LGLicenceException();
						} catch (Exception ex) {
							logger.error(Logger.EVENT_FAILURE,"VMS Application Exception " + ex.getMessage());
							throw new VMSAppException();
						}
					}
			} else { // device type not found
				updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue,paymentKeyHex, 
					Status.SC_75, msn, holdingPeriod);
				paymentResponse = prepareFaultResponse(
				VendOutcomeCode_type1.value18, deviceTypeNotFound,
				paymentResponse);
			}
		} else { // manufacturer type not found
				updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue,paymentKeyHex, 
					Status.SC_70, msn, holdingPeriod);
				paymentResponse = prepareFaultResponse(
				VendOutcomeCode_type1.value17, manuTypeNotFound,
				paymentResponse);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateGasVendCode method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Date timestamp " + actualTimeStamp);
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend code " + vendCode);

		
		if (vendCode != null) {
			paymentResponse = processGasVendCode(transactionType,
					originalCreditValue, pan, holdingPeriod, source,
					typeOfCurrency, originalTransactionID, transactionID,
					wstransactionDAO, vendCode, paymentKeyHex,actualTimeStamp,
					vendTimeStamp,msn,createdBy,updatedBy,deviceType);
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateGasVendCode method");
		return paymentResponse;
	}


	/**
	 * Method to process the gas vend code
	 * @param transactionType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param source
	 * @param typeOfCurrency
	 * @param originalTransactionID
	 * @param transactionID
	 * @param wstransactionDAO
	 * @param vendCode
	 * @param paymentKeyHex
	 * @param actualTimeStamp
	 * @param vendcodeTimeStamp
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse processGasVendCode(
			TransactionType transactionType, String creditValue, String pan,
			Long holdingPeriod, String source, Object typeOfCurrency,
			String originalTransactionID, String transactionID,
			WSTransactionDAO wstransactionDAO, String vendCode,
			String paymentKeyHex,Date actualTimeStamp,Date vendcodeTimeStamp,String msn,
			String createdBy, String updatedBy, Integer deviceType) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processGasVendCode method");
		
		String currency = null;
		
		if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
			currency = ((eCurrency) typeOfCurrency).toString();
		} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
			//currency = ((eu.landisgyr.lgsecure3.PaymentCode.eCurrency) typeOfCurrency).toString();
			currency = eu.landisgyr.lgsecure3.PaymentCode.eCurrency.Pounds.toString();
		}
		
		VendTxnWSDetails transactionDetails = setTransactionDetails(
				transactionType, pan, vendCode, source, holdingPeriod,
				originalTransactionID, transactionID, creditValue,
				currency, Status.SC_115, paymentKeyHex,actualTimeStamp,vendcodeTimeStamp,msn,
				createdBy, updatedBy);
		
		CreateVendResponse paymentResponse = processVendCode(wstransactionDAO,
				transactionDetails, transactionID, holdingPeriod, vendCode,
				pan, transactionType, creditValue,vendcodeTimeStamp, deviceType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processGasVendCode method");
		return paymentResponse;
	}



	/**
	 * Method to process the vend code
	 * @param wstransactionDAO
	 * @param transactionDetails
	 * @param transactionID
	 * @param holdingPeriod
	 * @param vendCode
	 * @param pan
	 * @param transactionType
	 * @param creditValue
	 * @param timestamp
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse processVendCode(
			WSTransactionDAO wstransactionDAO,
			VendTxnWSDetails transactionDetails, String transactionID,
			Long holdingPeriod, String vendCode, String pan,
			TransactionType transactionType, String creditValue,Date timestamp, Integer deviceType)
	throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processVendCode method");
		CreateVendResponse paymentResponse = new CreateVendResponse();
		try{
			logger.info(Logger.EVENT_UNSPECIFIED,"status " + transactionDetails.getTxnStatusDetails().iterator().next().getStatus());
			wstransactionDAO.insert(transactionDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is caught to roll back the credit id");
			rollBackCreditID(transactionDetails.getTransactionType(), transactionDetails.getMsn(), true);
			throw ex;
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is " + transactionID);
		
		 VMSSchedulerService vmsschedulerService = new VMSSchedulerServiceImpl();
		
		int retryCount=0;
		Boolean status = vmsschedulerService.scheduleJob(retryCount,transactionID,
				holdingPeriod,pan, vendCode,transactionType.toString(),
				creditValue,timestamp, deviceType, false);
		logger.info(Logger.EVENT_UNSPECIFIED,"Schedule job call status " + status);		
		paymentResponse = getPaymentResponse(vendCode, paymentResponse, status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processVendCode method");
		return paymentResponse;
	}
	

	
	/**
	 * @param transactionType
	 * @param pan
	 * @param source
	 * @param transactionID
	 * @param createdBy
	 * @param updatedBy
	 * @param creditValue
	 * @param paymentKey
	 * @param status
	 * @param msn
	 * @param holdingPeriod
	 */
	protected void updateFailedVendTxn(TransactionType transactionType, String pan, 
			String source, String transactionID, 
			String createdBy, String updatedBy, String creditValue, String paymentKey, 
			Status status, String msn, Long holdingPeriod) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering VMSPurchasePaymentService:updateFailedVendTxn method");
		VendTxnWSDetails transactionDetails = setTransactionDetails(
				transactionType, pan, "---------", source, holdingPeriod,
				"", transactionID, creditValue, "",
				status, paymentKey,new Date(),null,msn,createdBy,updatedBy);
		try {
			WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
			wstransactionDAO.insert(transactionDetails);
		} catch (DBException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"DBException::" + e.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving VMSPurchasePaymentService:updateFailedVendTxn method");
	}
	
	/**
	 * @param timestamp
	 * @return
	 */
	protected CreateVendResponse adjustTimeToUTC(Calendar timestamp) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering adjustTimeToUTC method");
		CreateVendResponse paymentResponse = new CreateVendResponse();
		try {
			new VMSUtils().adjustTimeToUTC(timestamp);
		} catch (IOException ex) {
			logger.error(Logger.EVENT_FAILURE,"IO Exception is thrown");
			paymentResponse = prepareFaultResponse(VendOutcomeCode_type1.value7, unknownError,paymentResponse);
			logger.error(Logger.EVENT_FAILURE,"Exception is " + unknownError);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value7, unknownError,
					paymentResponse);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving adjustTimeToUTC method");
		return paymentResponse;
	}
	
	/**
	 * Method to generate the electric vend code
	 * @param transactionType
	 * @param creditValue
	 * @param pan
	 * @param holdingPeriod
	 * @param originalTransactionID
	 * @param transactionID
	 * @param source
	 * @param paymentResponse
	 * @param wstransactionDAO
	 * @param licenseKey
	 * @param creditID
	 * @param typeOfCredit
	 * @param paymentKey
	 * @param dateTime
	 * @param msn
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse invokeAdjustCreditService(
			TransactionType transactionType, String creditValue, String pan, String mpxn, Long holdingPeriod, 
			String originalTransactionID, String transactionID, String source, WSTransactionDAO wstransactionDAO,
			String paymentKey, Calendar timestamp, String msn, int manuType, String createdBy, String updatedBy)
			throws EPaymentKeyInvalidException, EPaymentKeyOutOfRangeException,
			UnknownBreakupException, LGLicenceException, DBException,
			VMSAppException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering invokeAdjustCreditService method");
		logger.info(Logger.EVENT_UNSPECIFIED,"creditValue,paymentKey,dateTime "
						+ creditValue + paymentKey
						+ timestamp.getTime());
		VendTxnWSDetails transactionDetails = setTransactionDetails(
				transactionType, pan, "", source, holdingPeriod,
				originalTransactionID, transactionID, creditValue, null,
				Status.SC_120, paymentKey,timestamp.getTime(),timestamp.getTime(),msn,createdBy,updatedBy);
		CreateVendResponse paymentResponse = processAdjustCredit(wstransactionDAO,
				transactionDetails, transactionID, holdingPeriod,
				mpxn, creditValue,timestamp.getTime());

		logger.debug(Logger.EVENT_SUCCESS,"Leaving invokeAdjustCreditService method");
		return paymentResponse;
	}
	
	/**
	 * Method to process the adjust credit
	 * @param wstransactionDAO
	 * @param transactionDetails
	 * @param transactionID
	 * @param holdingPeriod
	 * @param vendCode
	 * @param pan
	 * @param creditValue
	 * @param timestamp
	 * @return
	 * @throws DBException
	 */
	private CreateVendResponse processAdjustCredit(
			WSTransactionDAO wstransactionDAO,
			VendTxnWSDetails transactionDetails, String transactionID,
			Long holdingPeriod, String mpxn,
			String creditValue,Date timestamp)
	throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processAdjustCredit method");
		CreateVendResponse paymentResponse = new CreateVendResponse();
		try{
			logger.info(Logger.EVENT_UNSPECIFIED,"status " + transactionDetails.getTxnStatusDetails().iterator().next().getStatus());
			wstransactionDAO.insert(transactionDetails);
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException is caught to roll back the credit id");
			rollBackCreditID(transactionDetails.getTransactionType(), transactionDetails.getMsn(), true);
			throw ex;
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is " + transactionID);
		
		 VMSSchedulerService vmsschedulerService = new VMSSchedulerServiceImpl();
		
		int retryCount=0;
		Boolean status = vmsschedulerService.scheduleAdjustJob(retryCount,transactionID,
				holdingPeriod, mpxn, creditValue,timestamp, false);
		logger.info(Logger.EVENT_UNSPECIFIED,"Schedule job call status " + status);		
		paymentResponse = getPaymentResponse("", paymentResponse, status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processAdjustCredit method");
		return paymentResponse;
	}
}
