package com.centrica.vms.ws.service;

//import java.util.Calendar;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import smartmeterprocessing.enterprise.uk.co.britishgas.VendOutcomeCode_type1;
/*
import smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1;
import LG_PAYMENT_CODE.PaymentCode.eCreditType;

import com.centrica.vms.common.FuelTypeEnum;
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
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails.TransactionType;*/
import com.centrica.vms.ws.sap.service.CreateVend;
import com.centrica.vms.ws.sap.service.CreateVendResponse;

class VMSAdjPaymentService extends VMSCommonPaymentService implements IVMSPaymentService {

	/**
	 * 
	 */
	private Logger logger = ESAPI.getLogger(getClass().getName());

	@Override
	public CreateVendResponse handlePaymentRequest(CreateVend createRequest,String createdBy,String updatedBy) {
		
		logger.debug(Logger.EVENT_SUCCESS,"Entering VMSAdjPaymentService:handlePaymentRequest method");
		CreateVendResponse paymentResponse = new CreateVendResponse();
		paymentResponse = prepareFaultResponse(VendOutcomeCode_type1.value7, unknownError,	paymentResponse);
		
		/*VendCreditType_type1 creditType = null;
		creditType = createRequest.getPaymentRequest().getVendAdditional().getVendCreditType();
		String creditValue = createRequest.getPaymentRequest().getVendAmount().getVendAmount().getAmount().getBG_Amount().toString();
		String pan = createRequest.getPaymentRequest().getPanNumber().getPanNumber();
		Long holdingPeriod = new Long(createRequest.getPaymentRequest().getVendAdditional().getVendHoldingPeriod());
		String source = createRequest.getPaymentRequest().getVendAdditional().getVendSource();
		String transactionID = createRequest.getPaymentRequest().getVendRequestKey().getVendRequestIdentifier().toString();
		Calendar timestamp = createRequest.getPaymentRequest().getVendRequestTimestamp().getVendRequestDateTime();
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction ID : " + transactionID);
		logger.info(Logger.EVENT_UNSPECIFIED,"PAN : " + pan);
		VMSUtils util = new VMSUtils();
		String msn = null;
		MeterDetails meterDetails = null;
		TransactionType transactionType = null;
		int value = new Integer(creditValue).intValue();
		eCreditType typeOfCredit = util.getTypeOfCredit(creditType);
		
		paymentResponse = adjustTimeToUTC(timestamp);
		String originalTransactionID = null;
		Boolean isValid = Boolean.FALSE;

		try {
			isValid = vmsValidationService.validatePanInRequest(pan);
			if (!isValid) {
				logger.info(Logger.EVENT_UNSPECIFIED,"Invalid PAN in request");
			}else{
				WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
				msn = getMSNFromPan(wstransactionDAO, pan);
				logger.info(Logger.EVENT_UNSPECIFIED,"MSN value : " + msn);
				meterDetails = wstransactionDAO.getMeterDetails(msn);
				int fuelType = meterDetails.getFuelTypeID();
				if (fuelType == FuelTypeEnum.ELECTRIC.getFuelType()){
					transactionType = util.getTransactionType(creditType, value,true);
				} else {
					transactionType = util.getTransactionType(creditType, value,false);
				}
				isValid = validatePaymentRequest(paymentResponse, holdingPeriod, source, creditType, transactionID);
				if (!isValid) {
					logger.info(Logger.EVENT_UNSPECIFIED,"Invalid payment request");
				}
				try {
					paymentResponse = processPaymentRequest(transactionType, creditValue,pan, holdingPeriod, source, 
							transactionID, timestamp, originalTransactionID, meterDetails,typeOfCredit, createdBy,updatedBy);
				} catch (EPaymentKeyInvalidException ex) {
					logger.error(Logger.EVENT_FAILURE,"EPaymentKey Invalid Exception is " + ex.getMessage());
					updateFailedVendTxn(transactionType, pan, 
							source, transactionID, 
							createdBy, updatedBy, creditValue, meterDetails.getPrepayKey(), 
							Status.SC_10, msn, holdingPeriod);
					paymentResponse = prepareFaultResponse(
							VendOutcomeCode_type1.value2, invalidEPaymentKeyMessage,
							paymentResponse);
				} catch (EPaymentKeyOutOfRangeException ex) {
					logger.error(Logger.EVENT_FAILURE,"EPaymentKey Out of Range Exception is " + invalidEPaymentOutOfRangeMessage);
					updateFailedVendTxn(transactionType, pan, 
							source, transactionID, 
							createdBy, updatedBy, creditValue, meterDetails.getPrepayKey(), 
							Status.SC_15, msn, holdingPeriod);
					paymentResponse = prepareFaultResponse(
							VendOutcomeCode_type1.value3, invalidEPaymentOutOfRangeMessage,
							paymentResponse);
				} catch (UnknownBreakupException ex) {
					logger.error(Logger.EVENT_FAILURE,"Unknown Breakup Exception is " + invalidBreakupMessage);
					updateFailedVendTxn(transactionType, pan, 
							source, transactionID, 
							createdBy, updatedBy, creditValue, meterDetails.getPrepayKey(), 
							Status.SC_30, msn, holdingPeriod);
					paymentResponse = prepareFaultResponse(
							VendOutcomeCode_type1.value6, invalidBreakupMessage,
							paymentResponse);
				} catch (LGLicenceException ex) {
					logger.error(Logger.EVENT_FAILURE,"Licence Exception is thrown");
					updateFailedVendTxn(transactionType, pan, 
							source, transactionID, 
							createdBy, updatedBy, creditValue, meterDetails.getPrepayKey(), 
							Status.SC_25, msn, holdingPeriod);
					paymentResponse = prepareFaultResponse(
							VendOutcomeCode_type1.value5, invalidLicenceMessage,
							paymentResponse);
				} catch (DBException ex) {
					logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown " + dbErrorMessage);
					updateFailedVendTxn(transactionType, pan, 
							source, transactionID, 
							createdBy, updatedBy, creditValue, meterDetails.getPrepayKey(), 
							Status.SC_20, msn, holdingPeriod);
					paymentResponse = prepareFaultResponse(VendOutcomeCode_type1.value4, dbErrorMessage,paymentResponse);
				} catch (VMSAppException ex) {
					logger.error(Logger.EVENT_FAILURE,"Exception is " + ex.getMessage());
					updateFailedVendTxn(transactionType, pan, 
							source, transactionID, 
							createdBy, updatedBy, creditValue, meterDetails.getPrepayKey(), 
							Status.SC_35, msn, holdingPeriod);
					paymentResponse = prepareFaultResponse(
							VendOutcomeCode_type1.value6, lgErrorMessage,
							paymentResponse);
				}
			}
		} catch (VMSInvalidHoldingPeriodException ex) {
			logger.error(Logger.EVENT_FAILURE,"Invalid Holding period Exception is " + ex.getMessage());
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_50, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value12, invalidHoldingPeriod,
					paymentResponse);
		} catch (VMSInvalidSourceException ex) {
			logger.error(Logger.EVENT_FAILURE,"Invalid source Exception is " + invalidSource);
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_55, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value13, invalidSource,
					paymentResponse);
		} catch (VMSInvalidPANException ex) {
			logger.error(Logger.EVENT_FAILURE,"Invalid PAN Exception is " + invalidPan);
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_40, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value10, invalidPan,
					paymentResponse);
		} catch(PanNotFoundException ex) {
			logger.error(Logger.EVENT_FAILURE,"PAN not foud Exception is " + panNotFound);
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_45, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value16, panNotFound,
					paymentResponse);
		}catch(MPxNNotFoundException ex) {
			logger.error(Logger.EVENT_FAILURE,"MPxN not found Exception is " + mpxnNotFound);
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_65, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value15, mpxnNotFound,
					paymentResponse);
		}catch(MSNNotFoundException ex){
			logger.error(Logger.EVENT_FAILURE,"MSNNotFoundException is thrown ");
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_60, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value11, msnNotFound, paymentResponse);
		} catch (DBException ex) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception is thrown " + dbErrorMessage);
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_20, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(VendOutcomeCode_type1.value4, dbErrorMessage,paymentResponse);
		} catch (VMSInvalidTransactionIdException e) {
			logger.error(Logger.EVENT_FAILURE,"Invalid TransactionID Exception is " + invalidTransactionId);
			updateFailedVendTxn(transactionType, pan, 
					source, transactionID, 
					createdBy, updatedBy, creditValue, null, 
					Status.SC_80, msn, holdingPeriod);
			paymentResponse = prepareFaultResponse(
					VendOutcomeCode_type1.value7, invalidTransactionId,
					paymentResponse);
		}*/

		logger.debug(Logger.EVENT_SUCCESS,"Leaving VMSAdjPaymentService:handlePaymentRequest method");
		return paymentResponse;
	}
}
