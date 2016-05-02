package com.centrica.unittest;

import java.util.Calendar;
import java.util.Date;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.ws.service.VMSLGGeneratePhase3;

import eu.landisgyr.lgsecure3.PaymentCode.eCurrency;


/**
 * Unit test methods for Vend transactions 
 * @see HttpServlet
 * @see Logger
 */
public class LGVendCode {
	private static Logger logger = ESAPI.getLogger(LGVendCode.class);
	public static void main(String...args) {
		LGVendCode vc = new LGVendCode();
		try {
			vc.generateLGPhase3Electric();
			vc.generateLGPhase3Gas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"Exeption::" + e.getMessage());
		}
	}
	
	public void generateLGPhase3Electric() throws Exception {
		//logger.debug(Logger.EVENT_SUCCESS,"Entering testLGPhase3Electric method");
		String paymentKey = "1234567890ABCDEFFEDCBA0987654321";
		String creditValue = "200";
		System.out.println("CCCCCCCCCCCCCCCCCC");
		eCurrency typeOfCurrency = eCurrency.Pounds;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, c.get(Calendar.HOUR)+1);
		Date vendTimeStamp = c.getTime();
		
		String licenseKey = new VMSUtils().loadProperties().getProperty("LicenseKeyPh3");
		System.out.println("License key : " + licenseKey);
		try {
			int value = new Integer(creditValue).intValue() / 100;
			String vendcode = new VMSLGGeneratePhase3().generateElectricPaymentCode(
					licenseKey, paymentKey, value, typeOfCurrency, vendTimeStamp);
			//logger.info(Logger.EVENT_UNSPECIFIED,"GENERATED VENDCODE : " + vendcode);
			System.out.println("GENERATED ELECTRIC VENDCODE : " + vendcode);
		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception : " + e.getMessage());
			//logger.error(Logger.EVENT_FAILURE,"Exception caught");
		}
		
		//logger.debug(Logger.EVENT_SUCCESS,"Leaving testLGPhase3Electric method");
	}
	
	public void generateLGPhase3Gas() {
		//logger.debug(Logger.EVENT_SUCCESS,"Entering testLGPhase3Gas method");
		String paymentKeyHex = "A2BEACEB1582C082F0FC8A4207EB778E";
		String creditValue = "500";
		System.out.println("DDDDDDDDDDDDDDDDDDDDD");
		eCurrency typeOfCurrency = eCurrency.Pounds;
		Date vendTimeStamp = Calendar.getInstance().getTime();
		String licenseKey = new VMSUtils().loadProperties().getProperty("LicenseKeyPh3");
		System.out.println("License key : " + licenseKey);
		try {
			int value = new Integer(creditValue).intValue() / 100;
			String vendcode = new VMSLGGeneratePhase3().
			generateGasPaymentCode(licenseKey, value, typeOfCurrency, vendTimeStamp, paymentKeyHex);
			//logger.info(Logger.EVENT_UNSPECIFIED,"GENERATED VENDCODE : " + vendcode);
			System.out.println("GENERATED GAS VENDCODE : " + vendcode);
		} catch (Exception e) {
			logger.error(Logger.EVENT_FAILURE,"Exception : " + e.getMessage());
			//logger.error(Logger.EVENT_FAILURE,"Exception caught");
		}
		
		//logger.debug(Logger.EVENT_SUCCESS,"Leaving testLGPhase3Gas method");
	}
}
