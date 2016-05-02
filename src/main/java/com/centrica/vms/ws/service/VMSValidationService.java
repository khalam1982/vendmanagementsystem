/**
 * VMSValidationService.java
 */
package com.centrica.vms.ws.service;

import java.util.Calendar;
import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.VMSInvalidPANException;

/**
 * @author nagarajk
 *
 */
class VMSValidationService {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	/**
	 * Method to validate the pan in request
	 * @param pan
	 * @return 
	 */
	protected Boolean validatePanInRequest(String pan) throws VMSInvalidPANException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering validatePanInRequest method");
		VMSUtils vmsUtils = new VMSUtils();
		Boolean isValidatePaymentRequest = Boolean.FALSE;
		if (!vmsUtils.isValidPanNumber(pan)) {
			logger.debug(Logger.EVENT_SUCCESS,"Leaving validatePanInRequest method ");
			throw new VMSInvalidPANException();
		}else{
			isValidatePaymentRequest = Boolean.TRUE;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validatePanInRequest method ");
		return isValidatePaymentRequest;
	}
	
	/**
	 * Method to check the valid date range
	 * @param validFrom
	 * @param validTo
	 * @return boolean
	 */
	boolean isWithinRange(Date validFrom, Date validTo) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering isWithinRange method");
		Calendar c = Calendar.getInstance();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving isWithinRange method");
		return !(c.getTime().before(validFrom) || c.getTime().after(validTo));
	} 
}
