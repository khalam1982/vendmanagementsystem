/**
 * HEMessageProcessor.java
 * Purpose: HEAD END message service
 * @author ramasap1
 */
package com.centrica.vms.scheduler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.axis2.databinding.ADBException;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.external.service.HeadendCommonService;
import com.centrica.vms.scheduler.model.VendTxnSchedulerDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.LoginResponse;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;
import com.trilliantnetworks.unity.ws.UnityServiceStub;

/**
 * Methods for HEAD END message service
 * @see IMessageServiceCommunicator
 */
public class HEMessageProcessor {

	private URL url = null;
	protected Logger logger = null;
	private UnityServiceStub unityServiceStub = null;
	private int status;
	private String transactionID = null;
	protected VMSUtils vmsUtil = null;
	protected SchedulerTransactionDAO schedulerTransactionDAO = null;
	private static final String VMS_USERNAME_VALUE="system";
	private final HeadendCommonService headendCommonService;
	
	protected HEMessageProcessor(URL url) {
		schedulerTransactionDAO = new SchedulerTransactionDAO();
		logger = ESAPI.getLogger(this.getClass());
		vmsUtil = new VMSUtils();
		this.url = url;
		this.headendCommonService = new HeadendCommonService();
	}
	
	public void setUrl(URL url) {
		this.url = url;
	}
	
	/**
	 * Constructor to invoke the service.
	 * @param URL - URL
	 * @param logger - Logger
	 * @param vmsUtil - VMSUtils
	 * @param schedulerTransactionDAO - SchedulerTransactionDAO
	 */
	public HEMessageProcessor(final URL url, final Logger logger, final VMSUtils vmsUtil, final SchedulerTransactionDAO schedulerTransactionDAO, 
			final HeadendCommonService headendCommonService) {
		this.url = url;
		this.logger = logger;
		this.vmsUtil = vmsUtil;
		this.schedulerTransactionDAO = schedulerTransactionDAO;
		this.headendCommonService = headendCommonService;
	}
	
	protected Boolean sendMessage(String xmlData) throws IOException,
	DBException {
		
		return sendMessage(xmlData, this.url);
	}
	
	/**
	 * To trigger HEAD END request that are unsent
	 * @param String
	 * @return Boolean
	 * @throws IOException
	 * @throws DBException
	 */
	protected Boolean sendMessage(String xmlData, URL url) throws IOException,
			DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering sendMessage method");
		HttpURLConnection httpURLConnection = null;
		OutputStreamWriter wr = null;
		BufferedReader reader = null;
		Boolean retVal = false;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			if(httpURLConnection != null){
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setRequestProperty("Content-Type", "text/plain");
				httpURLConnection.setRequestProperty("Content-Length", new Integer(
						xmlData.length()).toString());
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setReadTimeout(10 * 1000); // give it 10 seconds
															// to respond
			
				wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
				wr.write(xmlData);
				wr.flush();
			
			if (HttpURLConnection.HTTP_OK == httpURLConnection
					.getResponseCode()) {
				logger.info(Logger.EVENT_UNSPECIFIED,"success!" + httpURLConnection.getResponseCode());
				retVal = true;
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Failed!" + httpURLConnection.getResponseCode());
			}
			if (retVal) { // only if the HTTP connection is successful
				reader = new BufferedReader(new InputStreamReader(
						httpURLConnection.getInputStream()));
				StringBuilder stringBuilder = new StringBuilder();

				String line = null;
				if(reader != null){
					while ((line = reader.readLine()) != null) {
						stringBuilder.append(line + "\n");
					}
				}
				parseResponseMessage(stringBuilder.toString());
				persistVendTransaction();
				}
			}
		} finally {
			if (wr != null)
				wr.close();
			if (reader != null)
				reader.close();
			if (httpURLConnection != null)
				httpURLConnection.disconnect();
		
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendMessage method");
		return retVal;
	}
	
	/**
	 * Method to invoke the login service and set the session header.
	 * @param 
	 * @return String
	 * @throws UnexpectedErrorFault 
	 * @throws AccessDeniedFault 
	 * @throws LoginFault 
	 * @throws RemoteException 
	 * @throws ADBException 
	 * @throws NamingException
	 * @throws DBException
	 */
	protected String loginService(String deviceType, VendServiceDetails vendServiceDetails) throws ADBException, RemoteException, LoginFault, 
	AccessDeniedFault, UnexpectedErrorFault, NamingException, DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering loginService method");
		//VendServiceDetails vendServiceDetails = null;
		String sessionID = null;
		//vendServiceDetails = (VendServiceDetails) vmsUtil.getVendServiceDetails(deviceType);
		unityServiceStub = headendCommonService.prepareUnityServiceStub(vendServiceDetails, vendServiceDetails.getUrl());
		LoginResponse loginResponse = headendCommonService.loginService(unityServiceStub,vendServiceDetails);
		sessionID = loginResponse.getResult().getSessionId();
		System.out.println("SESSION ID:" + sessionID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving loginService method");
		return sessionID;
	}

	/**
	 * Method to invoke the login service and set the session header.
	 * @param 
	 * @return String
	 * @throws UnexpectedErrorFault 
	 * @throws AccessDeniedFault 
	 * @throws LoginFault 
	 * @throws RemoteException 
	 * @throws ADBException 
	 * @throws NamingException
	 * @throws DBException
	 */
	protected String loginService(String deviceType) throws ADBException, RemoteException, LoginFault, 
	AccessDeniedFault, UnexpectedErrorFault, NamingException, DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering loginService method");
		VendServiceDetails vendServiceDetails = null;
		String sessionID = null;
		vendServiceDetails = (VendServiceDetails) vmsUtil.getVendServiceDetails(deviceType);
		unityServiceStub = headendCommonService.prepareUnityServiceStub(vendServiceDetails, url.toString());
		LoginResponse loginResponse = headendCommonService.loginService(unityServiceStub,vendServiceDetails);
		sessionID = loginResponse.getResult().getSessionId();
		System.out.println("SESSION ID:" + sessionID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving loginService method");
		return sessionID;
	}

	/**
	 * Parse the the HTTP response message to fetch the delivery status and transaction ID
	 * @param String
	 * @return 
	 */
	protected void parseResponseMessage(String message) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering parseResponseMessage method");
		System.out.println("response message : " + message);
		int start = message.indexOf("<ns4:deliveryStatus>");
		int end = message.indexOf("</ns4:deliveryStatus>");
		start = start + "<ns4:deliveryStatus>".length();
		status = Integer.parseInt(message.substring(start, end));
		
		start = message.indexOf("<ns4:transactionId>");
		end = message.indexOf("</ns4:transactionId>");
		start = start + "<ns4:transactionId>".length();
		transactionID = message.substring(start, end);
		System.out.println("status code : " + status);
		System.out.println("transaction ID : " + transactionID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving parseResponseMessage method");
	}

	/**
	 * Method to invoke the logout service.
	 * @param String
	 * @return 
	 * @throws UnexpectedErrorFault 
	 * @throws AccessDeniedFault 
	 * @throws RemoteException 
	 * @throws ADBException  
	 */
	protected void logoutService(String sessionID) throws ADBException, RemoteException, AccessDeniedFault, 
		UnexpectedErrorFault {
		logger.debug(Logger.EVENT_SUCCESS,"Entering logoutService method");
		headendCommonService.logoutService(unityServiceStub,sessionID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving logoutService method");
	}
	
	/**
	 * Prepare the SOAP session header.
	 * @param String
	 * @return String
	 * @throws SOAPException
	 */
	protected String prepareSessionHeader(String sessionID) throws SOAPException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSessionHeader method");
		SOAPEnvelope soapEnvelope = null;
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        soapEnvelope = soapPart.getEnvelope();
        constructSOAPHeader(soapEnvelope, sessionID);
        String header = soapEnvelope.toString();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSessionHeader method");
		return header;
	}
	
	/**
	 * Prepare the SOAP envelope using the SOAP body and session header.
	 * @param String
	 * @param String
	 * @return String
	 */
	protected String prepareSOAPEnvelope(String xml, String header) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSOAPEnvelope method");
		String envelope = null;
		int i = xml.indexOf("<soapenv:Body>");
		String body = xml.substring(i);
		envelope = header.replaceFirst("<soapenv:Body /></soapenv:Envelope>", body);
        System.out.println("SOAP ENVELOPE : " + envelope);
        logger.info(Logger.EVENT_UNSPECIFIED,"SOAP ENVELOPE : " + envelope);
        logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSOAPEnvelope method");
        return envelope;
	}
		
	/**
	 * Construct the SOAP session header.
	 * @param SOAPEnvelope
	 * @param String
	 * @return 
	 * @throws SOAPException
	 */
    private void constructSOAPHeader(SOAPEnvelope envelope, String sessionID) throws SOAPException {
    		logger.debug(Logger.EVENT_SUCCESS,"Entering constructSOAPHeader method");
            SOAPHeader soapHeader = envelope.getHeader();
            SOAPElement soapElement = soapHeader.addNamespaceDeclaration("wsa", "http://www.w3.org/2005/08/addressing");
            SOAPHeaderElement sessionHeaderElement = soapHeader.addHeaderElement(new QName("urn:header.ws.unity.trilliantnetworks.com", "SessionHeader"));
            SOAPElement soapElementSID = sessionHeaderElement.addChildElement(new QName("sessionId"));
            soapElementSID.addTextNode(sessionID);
            SOAPElement soapElementQuery = sessionHeaderElement.addChildElement(new QName("queryOptions"));
            soapElementQuery.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            soapElementQuery.addAttribute(new QName("xsi:nil"), "1");
            SOAPElement soapElementMeter = sessionHeaderElement.addChildElement(new QName("readMeterOptions"));
            soapElementMeter.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
            soapElementMeter.addAttribute(new QName("xsi:nil"), "1");
            SOAPElement soapElementTo = soapElement.addChildElement("To", "wsa");
            soapElementTo.addTextNode(url.toString());
            SOAPElement messageIDElement = soapElement.addChildElement("MessageID", "wsa");
            String uuid = UUID.randomUUID().toString();
            uuid = vmsUtil.removeChar(uuid, '-');
            messageIDElement.addTextNode("urn:uuid:" + uuid);
            SOAPElement actionElement = soapElement.addChildElement("Action", "wsa");
            actionElement.addTextNode("urn:applyVendCode");
            logger.debug(Logger.EVENT_SUCCESS,"Leaving constructSOAPHeader method");
    }
    
	/**
	 * Method to update the vend transaction table
	 * @param 
	 * @return 
	 * @throws DBException
	 */
	protected void persistVendTransaction() throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering persistVendTransaction method");
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		VendTxnSchedulerDetails vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
		vendTxnStatus.setUpdatedTimeStamp(new Date());
		vendTxnSchedulerDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		vendTxnStatus.setStatus(new VMSUtils().getVMSStatus(vmsUtil.getVMSStatus(status)));
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend Code transaction status is "+vmsUtil.getVMSStatus(status));
		Set<VendTxnStatus> txnStatusDetails = vendTxnSchedulerDetails.getTxnStatusDetails();
		txnStatusDetails.add(vendTxnStatus);
		vendTxnSchedulerDetails.setTxnStatusDetails(txnStatusDetails);
		schedulerTransactionDAO.update(vendTxnSchedulerDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving persistVendTransaction method");
	}	
}
