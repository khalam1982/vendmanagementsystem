/**
 * VMSSchedulerServiceImplTest.java
 * Purpose: Unit test class for scheduler service implementation
 *
 * @author ramasap1
 */
package com.centrica.unittest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * Unit test method for scheduler service implementations 
 * @see TestCase
 */
public class VMSSchedulerServiceImplTest extends TestCase {
	
	private Logger logger = null;
	private Long holdingPeriod = 90000l;
	
	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	    logger = ESAPI.getLogger(getClass().getName());
	    logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}
	
	
	/**
	 * Test case to test the schedule job service
	 * @param
	 * @return
	 */
	public void testScheduleJob() throws Exception {
		String methodName = "schedule";
		sendRequest(methodName);
	}
	
	public void testScheduleAdjustJob() throws Exception {
		String methodName = "scheduleAdjust";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the unschedule job service
	 * @param
	 * @return
	 */
	public void testUnScheduleJob() throws Exception {
		String methodName = "unschedule";
		sendRequest(methodName);
	}
	
	/*
	 * Test case for scheduling HE utility for Phase 2
	 * @param
	 * @return
	 */
	public void testScheduleHEPhase2bUtility() throws Exception {
		String methodName = "scheduleHEPhase2bUtility";
		sendRequest(methodName);
	}
	

	/*
	 * Test case for scheduling HE utility for Phase 3
	 * @param
	 * @return
	 */
	public void testScheduleHEPhase3Utility() throws Exception {
		String methodName = "scheduleHEPhase3Utility";
		sendRequest(methodName);
	}
	
	/**
	 * Method to generate the transaction id
	 * @param
	 * @return
	 */
	private String generateTransactionID(){
		Random random = new Random();
		int transactionID = random.nextInt(9999);
		logger.info(Logger.EVENT_UNSPECIFIED,"transactionID is" +transactionID);
		return new Integer(transactionID).toString();
	}
	
	
	
	/**
	 * Method to send the request
	 * @param String
	 * @return
	 */
	private void sendRequest(String methodName) throws Exception {
		String transactionID = this.generateTransactionID();
		HttpURLConnection httpURLConnection = null;
		OutputStreamWriter wr = null;
		try{
			
			URL u = new URL ("http://localhost:8080/VendManagementSystem/SchedulerTest");
			httpURLConnection = (HttpURLConnection)u.openConnection();
			String message = methodName+";"+transactionID+";"+holdingPeriod;
			if(httpURLConnection != null){
				httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
				httpURLConnection.setRequestProperty( "Content-Length", new Integer (message.length()).toString());
				httpURLConnection.setDoOutput(true);
				wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
				if(wr != null){
					wr.write(message);
					wr.flush();
				}
				if(HttpURLConnection.HTTP_OK ==httpURLConnection.getResponseCode() ){
					assertTrue(true);
				}else{
					assertTrue(false);
				}
			}
		} catch(IOException ex){
		   logger.error(Logger.EVENT_FAILURE,"IO Exception");
		} finally {
			if(httpURLConnection != null)httpURLConnection.disconnect();
			if(wr != null)wr.close();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
