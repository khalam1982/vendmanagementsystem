/**
 * VMSServiceImplTest.java
 * Purpose: Unit test class for VMS service implementation
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
 * Unit test method for VMS service implementations 
 * @see TestCase
 */
public class VMSServiceImplTest extends TestCase {

	
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
	
	/**
	 * Test case to cancel payment request.
	 * @param
	 * @return
	 */
	public void testReversePayment() throws Exception{
		String methodName = "reverse";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to void the reverse payment request.
	 * @param
	 * @return
	 */
	public void testReverseAndVoidPayment() throws Exception{
		String methodName = "reverseAndVoid";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to the test the new status codes for void & reversal
	 */
	public void testVoidAckPaymentService() throws Exception {
		String methodName = "voidAck";
		sendRequest(methodName);
	}
	
	public void testReverseAckPaymentService()throws Exception {
		String methodName = "reverseAck";
		sendRequest(methodName);
	}

	public void testAckForReversedPayments() throws Exception {
		String methodName = "createReversalAckElectric";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to acknowledge payment delivery
	 * @param
	 * @return
	 */
	public void testAcknowledgePaymentDelivery() throws Exception {
		String methodName = "acknowledgePaymentDelivery";
		sendRequest(methodName);
	}

	/**
	 * Test case to test the generate electric payment code request
	 * @param
	 * @return
	 */
	public void testGenerateElectricPaymentCode() throws Exception{
		String methodName = "createElectric";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate electric payment code request for Phase 3
	 * @param
	 * @return
	 */
	public void testGeneratePhase3ElectricPaymentCode() throws Exception{
		String methodName = "createPhase3Electric";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate electric payment code request
	 */
	public void testGenerateGMTElectricPaymentCode() throws Exception{
		String methodName = "createGMTElectric";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate electric adjustment code request
	 * @param
	 * @return
	 */
	public void testGenerateElectricAdjustmentCode() throws Exception{
		String methodName = "adjustElectric";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate electric adjustment code request for Phase 3 meters
	 * @param
	 * @return
	 * @throws Exception 
	 */
	public void testGeneratePhase3ElectricAdjustmentCode() throws Exception{
		String methodName = "adjustPhase3Electric";
		sendRequest(methodName);
	}
	
	
	/**
	 * Test case to test validity of the pan number passing empty value
	 * @param
	 * @return
	 */
	public void testValidateAdjustmentPanEmpty() throws Exception{
		String methodName = "adjustPanEmpty";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the entry of the pan number in records
	 * @param
	 * @return
	 */
	public void testValidatePanFound() throws Exception{
		String methodName = "validatePanFound";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the entry of the msn number in records
	 * @param
	 * @return
	 */
	public void testValidateMSNFound() throws Exception{
		String methodName = "validateMSNFound";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the entry of the MPxN number in records
	 * @param
	 * @return
	 */
	public void testValidateMPxNFound() throws Exception{
		String methodName = "validateMPxNFound";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test validity of the pan number passing non numeric pan number.
	 * @param
	 * @return
	 */
	public void testValidateAdjustmentPanIsDigit() throws Exception{
		String methodName = "adjustPanIsDigit";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test validity of the pan number of wrong length.
	 * @param
	 * @return
	 */
	public void testValidateAdjustmentPanLength() throws Exception{
		String methodName = "adjustPanLength";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test validity of the pan number passing the appropriate one.
	 * @param
	 * @return
	 */
	public void testValidateAdjustmentPan() throws Exception{
		String methodName = "adjustPanValid";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test validity of the pan number passing non numeric pan number.
	 * @param
	 * @return
	 */
	public void testValidatePurchasePanIsDigit() throws Exception{
		String methodName = "validatePanIsDigit";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test validity of the pan number of wrong length.
	 * @param
	 * @return
	 */
	public void testValidatePurchasePanLength() throws Exception{
		String methodName = "validatePanLength";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test validity of the pan number passing the appropriate one.
	 * @param
	 * @return
	 */
	public void testValidatePurchasePan() throws Exception{
		String methodName = "validatePan";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate electric adjustment code request
	 * @param
	 * @return
	 */
	public void testValidateAdjustmentHoldingPeriod() throws Exception{
		String methodName = "adjustHoldingPeriod";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate electric adjustment code request
	 * @param
	 * @return
	 */
	public void testValidateAdjustmentSource() throws Exception{
		String methodName = "adjustSource";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate purchase code request
	 * @param
	 * @return
	 */
	public void testValidatePurchaseHoldingPeriod() throws Exception{
		String methodName = "validate_hp";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate purchase code request
	 * @param
	 * @return
	 */
	public void testValidatePurchaseSource() throws Exception{
		String methodName = "validateSource";
		sendRequest(methodName);
	}

	/**
	 * Test case to test the generate purchase code request
	 * @param
	 * @return
	 */
	public void testValidateTransactionId() throws Exception {
		String methodName = "validateTransactionId";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the void  payment request 
	 * @param
	 * @return
	 */
	public void testVoidPayment() throws Exception{
		String methodName = "void";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the void  payment request after holding pen 
	 * @param
	 * @return
	 */
	public void testVoidPayment_AHP() throws Exception{
		String methodName = "voidADP";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the void  payment request after holding pen for gas request
	 * @param
	 * @return
	 */
	public void testVoidPayment_AHP_Gas() throws Exception{
		String methodName = "voidADP_Gas";
		sendRequest(methodName);
	}
	
	/**
	 * Test case to test the generate gas payment code
	 * @param
	 * @return
	 */
	public void testGenerateGasPaymentCode() throws Exception{
		String methodName = "createGas";
		sendRequest(methodName);
		
	}
	
	/**
	 * Test case to test the generate gas payment code for Phase 3 meters
	 * @param
	 * @return
	 */
	public void testGeneratePhase3GasPaymentCode() throws Exception{
		String methodName = "createPhase3Gas";
		sendRequest(methodName);
		
	}
	
	/**
	 * Test case to test the generate gas adjustment code
	 * @param
	 * @return
	 */
	public void testGenerateGasAdjustmentCode() throws Exception{
		String methodName = "adjustGas";
		sendRequest(methodName);
		
	}
	
	/**
	 * @throws Exception
	 */
	public void testGenerateAdjustmentAck() throws Exception {
		String methodName = "adjustAck";
		sendRequest(methodName);
	}
	
	/**
	 * @throws Exception
	 */
	public void testGenerateVoidAdjustment() throws Exception {
		String methodName = "voidAdjustment";
		sendRequest(methodName);
	}
	
	/**
	 * @throws Exception
	 */
	public void testResendVoidAdjustmentMessage() throws Exception {
		String methodName = "resendAdjustment";
		sendRequest(methodName);
		
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
