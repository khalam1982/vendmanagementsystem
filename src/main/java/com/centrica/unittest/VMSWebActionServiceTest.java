/**
 * VMSWebActionServiceTest.java
 * Purpose: Unit test class for VMS web action service classes
 *
 * @author nagarajk
 */
package com.centrica.unittest;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;


import com.centrica.vms.form.AdjustmentRequestDetailsForm;

import com.centrica.vms.service.VMSWebActionService;



/**
 * Unit test method for VMS web action service classes
 * @see TestCase
 */
public class VMSWebActionServiceTest extends TestCase {

	private VMSWebActionService vmsWebActionService;
	private Logger logger;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		 super.setUp();
		 vmsWebActionService = new VMSWebActionService();
		 PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	     logger = ESAPI.getLogger(getClass().getName());
	     logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}
	
	/**
	 * test case to test the get credit id method for purchase
	 * @param
	 * @return
	 */
	public void testSendAdjustmentRequest(){
		AdjustmentRequestDetailsForm adjustmentRequestDetailsForm = new AdjustmentRequestDetailsForm();
		adjustmentRequestDetailsForm.setPan("1234567890123456780");
		adjustmentRequestDetailsForm.setCreditValue("28");
		adjustmentRequestDetailsForm.setPayChannel("5-7");
		adjustmentRequestDetailsForm.setCreditType("ADJUSTMENT");
		
		try{
			adjustmentRequestDetailsForm = vmsWebActionService.sendAdjustmentRequest(adjustmentRequestDetailsForm);
			logger.info(Logger.EVENT_UNSPECIFIED,"Adjust Request completed");
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"Exception is thrown");
		}
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		vmsWebActionService = null;
		super.tearDown();
	}

}
