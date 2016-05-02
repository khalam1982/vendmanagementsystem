/**
 * VMSUtilityServiceTest.java
 * Purpose: Unit test class for VMS utility service implementation
 *
 * @author nagarajk
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
 * Unit test method for VMS utility service implementations 
 * @see TestCase
 */
public class VMSUtilityServiceTest extends TestCase {

	private Logger logger;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		 super.setUp();
		 PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
		 logger = ESAPI.getLogger(this.getClass());
		 logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}
	
	
	/**
	 * Method to send the request
	 * @param String
	 * @return
	 */
	private void sendRequest(String methodName) throws Exception {
		HttpURLConnection httpURLConnection =  null;
		OutputStreamWriter wr = null;
		try{
			URL u = new URL ("http://localhost:8080/VendManagementSystem/VendMgmtSysTest");
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
	 * Method to test processing unsent HE messages  for phase 2 meters
	 * @param
	 * @return
	 */
	public void testProcessUnsentPhase2BMessages() throws Exception{
		String methodName = "processUnsentPhase2BMessages";
		sendRequest(methodName);
	}
	
	/*
	 * Method to test processing unsent HE messages for phase 3 meters
	 * @param
	 * @return
	 */
	public void testProcessUnsentPhase3Messages() throws Exception{
		String methodName = "processUnsentPhase3Messages";
		sendRequest(methodName);
	}
	
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
