package com.centrica.vms.ws.service.validator;

import java.util.Calendar;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.exception.VMSInvalidDateException;
import com.centrica.vms.exception.VMSInvalidPPKeyException;
import com.centrica.vms.exception.VMSInvalidTransactionIdException;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequestMessage;

/**
 * PPKeyServiceValidator
 * 
 * Class to Validate PP Key Update Request
 * 
 * @author chackram
 */
public class PPKeyServiceValidator {

	private final Logger logger = ESAPI.getLogger(PPKeyServiceValidator.class);

	private final VMSUtils utils;

	/**
	 * Constructor
	 */
	public PPKeyServiceValidator() {
		utils = new VMSUtils();
	}

	/**
	 * Validates Update PPKey Request
	 * 
	 * @param request - UpdatePPKeyRequestMessage
	 * @throws VMSInvalidTransactionIdException
	 * @throws MSNNotFoundException
	 * @throws VMSInvalidPPKeyException
	 * @throws VMSInvalidDateException
	 */
	public void validateUpdatePPKeyRequest(final UpdatePPKeyRequestMessage request) throws VMSInvalidTransactionIdException, MSNNotFoundException, 
	VMSInvalidPPKeyException, VMSInvalidDateException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceValidator:validateUpdatePPKeyRequest method");
		/** Transaction Id Validation **/
		if( !utils.isValidTransactionId(request.getPPKeyRequestIdentifier()) ) {
			throw new VMSInvalidTransactionIdException();
		}

		/** MSN Validation **/
		if( !validateMSN(request.getMsn()) ) {
			throw new MSNNotFoundException();
		}

		/** PPKey Validation **/
		if( !ValidatePPKey(request.getPPKey()) ) {
			throw new VMSInvalidPPKeyException();
		}

		/** Request Date Validation **/
		if( !validateRequestDate(request.getPPKeyRequestDateTime()) ) {
			throw new VMSInvalidDateException();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceValidator:validateUpdatePPKeyRequest method");

	}

	/**
	 * Validates MSN
	 * 
	 * @param msn of type String
	 * @return boolean
	 */
	private boolean validateMSN(final String msn) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceValidator:validateMSN method");
		boolean valid = true;
		/** Validate for Empty and in DB **/
		if( msn == null || msn.isEmpty() ) {
			valid = false;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceValidator:validateMSN method");
		return valid;

	}

	/**
	 * Validates PP Key
	 *
	 * @param ppKey of type String
	 * @return boolean
	 */
	private boolean ValidatePPKey(final String ppKey) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceValidator:ValidatePPKey method");
		boolean valid = true;
		/** Validate for Empty and Special Characters **/
		if( ppKey == null || ppKey.isEmpty() ) {
			valid = false;
		} /*else {//TODO Should be uncommented when the 32 characters length is implemented by HeadEnd
			*//** Length Validation 32 characters **//*
			valid = ppKey.length() == 32 ? true : false;
		}*/
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceValidator:ValidatePPKey method");
		return valid;

	}

	/**
	 * Validates Request Date
	 * 
	 * @param reqDate - Calendar
	 * @return boolean
	 */
	private boolean validateRequestDate(final Calendar reqDate) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceValidator:validateRequestDate method");
		boolean valid = true;
		if( reqDate == null ) {
			valid = false;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceValidator:validateRequestDate method");
		return valid;

	}

}
