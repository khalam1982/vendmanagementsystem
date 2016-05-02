/**
 * SchedulerTransactionDAOTest.java
 * Purpose: Unit test class for scheduler DAO implementation
 *
 * @author ramasap1
 */
package com.centrica.unittest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import junit.framework.TestCase;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Unit test methods for scheduler DAO implementations 
 * @see TestCase
 * @see Logger
 */
public class SchedulerTransactionDAOTest extends TestCase {

	private Logger logger = null;
	
	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		super.setUp();
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	    logger = ESAPI.getLogger(getClass().getName());
	    logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}

	
	
	/**
	 *  Test case to get the trigger name
	 *  @param 
	 *  @return
	 */
	public void testGetTriggerName() throws Exception{
		String methodName = "triggerName";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to update the status of the transaction
	 * @param
	 * @return
	 */
	public void testUpdateStatus() throws Exception{
		String methodName = "updateStatus";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to update the vend transaction
	 * @param
	 * @return
	 */
	public void testUpdateVendTransaction() throws Exception{
		String methodName = "updateVendTransaction";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to get the status
	 * @param
	 * @return
	 */
	public void testGetStatus() throws Exception{
		String methodName = "getStatus";
		sendRequest(methodName);
		
	}
	
	/**
	 * Method to test the get unsent transactions
	 * @param
	 * @return
	 */
	public void testGetUnsentTransactions() throws Exception{
		String methodName = "getUnsentVCCount";
		sendRequest(methodName);
		
	}
	
	public void testGetUnsentAdjustmentTransactions() throws Exception {
		String methodName = "getUnsentAdjustCount";
		sendRequest(methodName);
	}
	
	/**
	 * Method to test the get unsent vend history transactions
	 * @param 
	 * @return 
	 */
	public void testGetUnsentVendHistoryTransactions() throws Exception{
		String methodName = "getUnsentVendHistoryCount";
		sendRequest(methodName);
		
	}
	
	/**
	 * Method to test the get unsent vend code transactions
	 * @param 
	 * @return 
	 */
	public void testGetUnsentVendCodeTransactions() throws Exception{
		String methodName = "getUnsentVendCount";
		sendRequest(methodName);
	}
	
	/**
	 * Method to send the request
	 * @param String
	 * @return
	 * @throws IOException
	 */
	private void sendRequest(String methodName) throws Exception{
		URL u = null;
		HttpURLConnection httpURLConnection = null;
		OutputStreamWriter wr = null;
		try{
			u = new URL ("http://localhost:8080/VendManagementSystem/VendTransactionDAOTest");
			httpURLConnection = (HttpURLConnection)u.openConnection();
			String message = methodName;
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
		}catch(IOException ex){
		   logger.error(Logger.EVENT_FAILURE,"IO Exception");
		} finally {
			if(httpURLConnection != null)httpURLConnection.disconnect();
			if(wr != null)wr.close();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 * @throws Exception
	 */
	protected void tearDown() throws Exception {
		logger = null;
		super.tearDown();
	}

}
