package com.centrica.vms.ws.service;

import java.util.Date;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import LG_PAYMENT_CODE.PaymentCode;
import LG_PAYMENT_CODE.PaymentCode.eCreditType;
import LG_PAYMENT_CODE.PaymentCode.eCurrency;
import LG_SMS_LIB.BreakupException;
import LG_SMS_LIB.LicenceException;

public class VMSLGGeneratePhase2B {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	/* (non-Javadoc)
	 * Method used to generate the electric payment code for L+G Phse2b
	 * @see com.centrica.vms.ws.service.IVMSLGPaymentCodeService#generateElectricPaymentCode(java.lang.String, LG_PAYMENT_CODE.PaymentCode.eCreditType, java.lang.String, int, java.lang.String)
	 */
	public String generateElectricPaymentCode(String licenseKey, eCreditType typeOfCredit, String paymentKey,
			int creditID, String creditValue) throws BreakupException, LicenceException, Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering VMSLGGeneratePhase2B:generateElectricPaymentCode method");
		String vendCode = null;
		PaymentCode paymentCode = new PaymentCode(licenseKey);
		vendCode = paymentCode.generateElectricityPaymentCode(new Integer(creditValue)
		.intValue(), typeOfCredit, paymentKey, creditID);
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend code is" + vendCode);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving VMSLGGeneratePhase2B:generateElectricPaymentCode method");
		return vendCode;
	}

	/* (non-Javadoc)
	 * Method used to generate the Gas payment code for L+G Phse2b
	 * @see com.centrica.vms.ws.service.IVMSLGPaymentCodeService#generateGasMacCode(java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */
	public char[] generateGasMacCode(String licenseKey, String creditValue, Date vendTimeStamp, String paymentKeyHex)
	throws BreakupException, LicenceException, Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering VMSLGGeneratePhase2B:generateGasMacCode method");
		char[] returnCode = null;
		PaymentCode paymentCode = new PaymentCode(licenseKey);
		returnCode = paymentCode.generateGasMacCode(new Integer(
				creditValue).intValue(), paymentKeyHex, vendTimeStamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving VMSLGGeneratePhase2B:generateGasMacCode method");
		return returnCode;
	}


	/* (non-Javadoc)
	 * Method used to generate the Gas payment code for L+G Phse2b
	 * @see com.centrica.vms.ws.service.IVMSLGPaymentCodeService#generateGasPaymentCode(java.lang.String, java.lang.String, LG_PAYMENT_CODE.PaymentCode.eCurrency, java.util.Date, java.lang.String)
	 */
	public String generateGasPaymentCode(String licenseKey, String creditValue, eCurrency typeOfCurrency, Date vendTimeStamp, String paymentKeyHex)
	throws BreakupException, LicenceException, Exception {
		logger.debug(Logger.EVENT_SUCCESS,"Entering VMSLGGeneratePhase2B:generateGasPaymentCode method");
		String vendCode = null;
		PaymentCode paymentCode = null;
		paymentCode = new PaymentCode(licenseKey);
		vendCode = paymentCode.generateGasPaymentCode(new Integer(
				creditValue).intValue(), typeOfCurrency, paymentKeyHex,
				vendTimeStamp);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving VMSLGGeneratePhase2B:generateGasPaymentCode method");
		return vendCode;
	}
}
