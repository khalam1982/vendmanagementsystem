/**
 * LGPhase3TestServlet.java
 * Purpose: Unit test class for Vend code Phase 3 L+G
 *
 * @author nagarajk
 */
package com.centrica.unittest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.ws.service.VMSLGGeneratePhase3;

import eu.landisgyr.lgsecure3.PaymentCode.eCurrency;


@SuppressWarnings("serial")
/**
 * Unit test methods for Vend transactions 
 * @see HttpServlet
 * @see Logger
 */
public class LGPhase3TestServlet extends HttpServlet {

	private Logger logger = ESAPI.getLogger(getClass().getName());

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() {
	}

	@Override
	/*
	 * The servlet GET method
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletInputStream servletInputStream = req.getInputStream();
		logger.info(Logger.EVENT_UNSPECIFIED,"In do get method");
		System.out.println("here");
		String method;
		String array[] = new String[3];
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(servletInputStream));
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				logger.info(Logger.EVENT_UNSPECIFIED,"inputLine "+inputLine);
				array = inputLine.split(";");
			}
				
			in.close();
			if(array.length==1){
				method  = array[0];
				System.out.println("Method " + method);
				if(method.equals("phase3Electric")){
					System.out.println("AAAAAAAAAAAAAAAAAAAA");
					generateLGPhase3Electric();
				}else if(method.equals("phase3Gas")){
					System.out.println("BBBBBBBBBBBBBBBBBBB");
					generateLGPhase3Gas();
				}else{
					System.out.println("not supported");
					//TODO: NOT SUPPORTED
				}
				
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Array length is not expected");
			}
			
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"IO Exception : " + ex.getMessage());
		}
	}
	
	public void generateLGPhase3Electric() throws Exception {
		//logger.debug(Logger.EVENT_SUCCESS,"Entering testLGPhase3Electric method");
		String paymentKey = "1234567890ABCDEFFEDCBA0987654321";
		String creditValue = "1";
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
		String paymentKeyHex = "1234567890ABCDEFFEDCBA0987654321";
		String creditValue = "1";
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
