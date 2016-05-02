package com.centrica.vms.ws.service;

import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import LG_SMS_LIB.BreakupException;
import eu.landisgyr.lgsecure3.LicenceException;
import eu.landisgyr.lgsecure3.PaymentCode;
import eu.landisgyr.lgsecure3.PaymentCode.eCurrency;



public class VMSLGGeneratePhase3 {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	/* (non-Javadoc)
	 * Method used to generate the electric payment code for L+G Phase3
	 * @see com.centrica.vms.ws.service.IVMSLGPaymentCodeService#generateElectricPaymentCode(java.lang.String, LG_PAYMENT_CODE.PaymentCode.eCreditType, java.lang.String, int, java.lang.String)
	 */
	public String generateElectricPaymentCode(String licenseKey, String paymentKey,
			Integer creditValue, eCurrency typeOfCurrency, Date vendTimeStamp) throws BreakupException, LicenceException, Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateElectricPaymentCode method");
		String vendCode = null;
		PaymentCode paymentCode = new PaymentCode(licenseKey);
		// TODO: comment out this block once L+G API is in place
		vendCode = paymentCode.generatePaymentCode(PaymentCode.eFuel.Electricity,
				creditValue, typeOfCurrency, paymentKey, vendTimeStamp);
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend code is" + vendCode);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateElectricPaymentCode method");
		return vendCode;
	}


	/* (non-Javadoc)
	 * Method used to generate the Gas payment code for L+G Phase3
	 * @see com.centrica.vms.ws.service.IVMSLGPaymentCodeService#generateGasPaymentCode(java.lang.String, java.lang.String, LG_PAYMENT_CODE.PaymentCode.eCurrency, java.util.Date, java.lang.String)
	 */
	public String generateGasPaymentCode(String licenseKey, Integer creditValue, eCurrency typeOfCurrency, Date vendTimeStamp, String paymentKeyHex)
	throws BreakupException, LicenceException, Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering generateGasPaymentCode method");
		String vendCode = null;
		PaymentCode paymentCode = new PaymentCode(licenseKey);
		vendCode = paymentCode.generatePaymentCode(PaymentCode.eFuel.Gas, creditValue, typeOfCurrency, paymentKeyHex,
				vendTimeStamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving generateGasPaymentCode method");
		return vendCode;
	}
}
