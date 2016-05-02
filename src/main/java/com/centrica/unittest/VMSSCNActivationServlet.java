/**
 * VMSSCNActivationServlet.java
 * Purpose: Unit test class for Vend transactions
 *
 * @author ramasap1
 */
package com.centrica.unittest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.ws.pi.service.AsynActivationandDeactivation;
import com.centrica.vms.ws.pi.service.CustomerDetails;
import com.centrica.vms.ws.service.VMSSCNService;

/**
 * VendMgmtSysServlet.java
 * Purpose: Unit test for Vend transactions
 * @author ramasap1
 * 
 */
@SuppressWarnings("serial")
/**
 * Unit test methods for Vend transactions 
 * @see HttpServlet
 * @see Logger
 */
public class VMSSCNActivationServlet extends HttpServlet {

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
				if(method.equals("activation")){
					createSCNActivation();
				}else if (method.equals("deactivation")) {
					createSCNDeactivation();
				}else{
					//TODO: something else here
				}
				
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Array length is not expected");
			}
			
		}catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IO Exception : " + ex.getMessage());
		}
	}

	/**
	 * 
	 */
	private void createSCNDeactivation() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createSCNDeactivation method");
		String mpxn = "123456789";
		String scn = "1234567890123456789";
		Calendar validFrom = Calendar.getInstance();
		Calendar validTo = Calendar.getInstance();
		AsynActivationandDeactivation asynActivationandDeactivation = prepareSCNActivationTest(mpxn, scn, validFrom, validTo);
		new VMSSCNService().handleSCNRegistration(asynActivationandDeactivation);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createSCNDeactivation method");
	}

	/**
	 * @param mpxn
	 * @param scn
	 * @param validFrom
	 * @param validTo
	 * @return
	 */
	private AsynActivationandDeactivation prepareSCNActivationTest(
			String mpxn, String scn, Calendar validFrom, Calendar validTo) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSCNActivationTest method");
		AsynActivationandDeactivation asynActivationandDeactivation = new AsynActivationandDeactivation();
		CustomerDetails customerDetails = new CustomerDetails();
		asynActivationandDeactivation.setCustomerDetails(customerDetails);
		customerDetails.setMxpn(mpxn);
		customerDetails.setScn(scn);
		customerDetails.setValid_from(validFrom);
		customerDetails.setValid_to(validTo);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSCNActivationTest method");
		return asynActivationandDeactivation;
	}


	/**
	 * 
	 */
	private void createSCNActivation() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createSCNActivation method");
		String mpxn = "987654321";
		String scn = "1234567890987654321";
		Calendar validFrom = Calendar.getInstance();
		Calendar validTo = Calendar.getInstance();
		AsynActivationandDeactivation asynActivationandDeactivation = prepareSCNActivationTest(mpxn, scn, validFrom, validTo);
		new VMSSCNService().handleSCNRegistration(asynActivationandDeactivation);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createSCNActivation method");
		
	}
}
