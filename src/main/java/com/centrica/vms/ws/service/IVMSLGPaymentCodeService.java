package com.centrica.vms.ws.service;

import java.util.Date;

import LG_PAYMENT_CODE.PaymentCode.eCreditType;
import LG_PAYMENT_CODE.PaymentCode.eCurrency;
import LG_SMS_LIB.BreakupException;
import LG_SMS_LIB.LicenceException;

public interface IVMSLGPaymentCodeService extends IVMSPaymentCodeService {
	
	/**
	 * Method used to generate the payment code for various meters
	 * @param createRequest
	 * @return
	 */
	String generateElectricPaymentCode(String licenseKey, eCreditType typeOfCredit, String paymentKey,
			int creditID, String creditValue) throws  BreakupException, LicenceException, Exception;
	
	char[] generateGasMacCode(String licenseKey, String creditValue, Date vendTimeStamp, String paymentKeyHex)
 	throws BreakupException, LicenceException, Exception;
	
	String generateGasPaymentCode(String licenseKey, String creditValue, eCurrency typeOfCurrency, Date vendTimeStamp, String paymentKeyHex)
 	throws BreakupException, LicenceException, Exception;
}
