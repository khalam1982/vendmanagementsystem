/**
 * 
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
 * @author nagarajk
 *
 */
public class LGPhase3Test extends TestCase {
	
	private Logger logger = null;

	
	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("properties/log4j.config").getPath());
	    //logger = ESAPI.getLogger(getClass().getName());
	   // logger.info(Logger.EVENT_UNSPECIFIED,"log configuration is Successful");
	}

	/**
	 * Method to send the request
	 * @param String
	 * @return	
	 */
	private void sendRequest(String methodName) throws Exception {
		HttpURLConnection httpURLConnection = null;
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

	public void testLGPhase3Electric() throws Exception {
		String methodName = "phase3Electric";
		sendRequest(methodName);
	}
	
	public void testLGPhase3Gas() throws Exception {
		String methodName = "phase3Gas";
		sendRequest(methodName);
	}
	
	/*
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
