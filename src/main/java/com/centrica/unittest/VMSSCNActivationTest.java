/**
 * VMSSCNActivationTest.java
 * Purpose: Unit test class for VMS SCN activation/de-activation implementation
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
 * Unit test method for VMS SCN activations and de-activations 
 * @see TestCase
 */
public class VMSSCNActivationTest extends TestCase {

	
	private Logger logger = null;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		 super.setUp();
		 PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	     logger = ESAPI.getLogger(getClass().getName());
	     logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}

	/**
	 * Method to send the request
	 * @param String
	 * @return	
	 */
	private void sendRequest(String methodName){
		OutputStreamWriter wr = null;
		try{
			URL u = new URL ("http://localhost:8080/VendManagementSystem/VMSSCNActivationTest");
			HttpURLConnection httpURLConnection = (HttpURLConnection)u.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			String message = methodName;
			httpURLConnection.setRequestProperty( "Content-Length", new Integer (message.length()).toString());
			httpURLConnection.setDoOutput(true);
			wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
			wr.write(message);
			wr.flush();
			if(HttpURLConnection.HTTP_OK ==httpURLConnection.getResponseCode() ){
				assertTrue(true);
			}else{
				assertTrue(false);
			}
		
		}catch(IOException ex){
		   logger.error(Logger.EVENT_FAILURE,"IO Exception");
		}finally{
			try{
			if(wr != null){
				wr.close();
			}
			}catch(IOException ioe){
				logger.error(Logger.EVENT_FAILURE,"IOException::" + ioe.getMessage());
			}
		}
	}
	
	/**
	 * Test case to activate SCN
	 * @param
	 * @return
	 */
	public void testSCNActivation(){
		String methodName = "activation";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to deactivate SCN
	 * @param
	 * @return
	 */
	public void testSCNDeactivation(){
		String methodName = "deactivation";
		sendRequest(methodName);
	}
		
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
