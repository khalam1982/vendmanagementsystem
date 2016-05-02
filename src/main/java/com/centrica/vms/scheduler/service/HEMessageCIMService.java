/**
 * HEMessageCIMService.java
 * Purpose: HEAD END CIM message service
 * @author nagarajk
 */
package com.centrica.vms.scheduler.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.axis2.databinding.ADBException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.owasp.esapi.Logger;

import sun.misc.BASE64Encoder;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.HESLookUpFailedException;
import com.centrica.vms.model.LookUpParam;
import com.centrica.vms.model.ServiceId;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.external.service.SAPService;
import com.centrica.vms.scheduler.model.UtilityJobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 * Methods for HEAD END CIM message service
 * @see IMessageServiceCommunicator
 */
public class HEMessageCIMService extends HEMessageProcessor implements IHEMessageService {

	private Logger logger = null;
	private VMSUtils vmsUtil = null;
	private SchedulerTransactionDAO schedulerTransactionDAO = null;
	URL url = null;
	
	/**
	 * Constructor to invoke the service.
	 * @param URL
	 */
	public HEMessageCIMService(URL url) {
		super(url);
		this.url = url;
		logger = super.logger;
		vmsUtil = super.vmsUtil;
		schedulerTransactionDAO = super.schedulerTransactionDAO;
	}
	
	/**
	 * Process the unsent messages triggered through VMS utility
	 * @param UtilityJobDetails
	 * @return Boolean
	 * @throws NamingException 
	 * @throws UnexpectedErrorFault 
	 * @throws AccessDeniedFault 
	 * @throws LoginFault 
	 * @throws RemoteException 
	 * @throws ADBException 
	 * @throws SOAPException 
	 * @throws DBException 
	 * @throws StaleObjectStateException
	 * @throws IOException
	 * @throws HESLookUpFailedException 
	 */
	public Boolean processUnsentMessages(UtilityJobDetails utilityJobDetails) throws ADBException, RemoteException, 
		LoginFault, AccessDeniedFault, UnexpectedErrorFault, NamingException, SOAPException, 
		StaleObjectStateException, DBException, IOException, HESLookUpFailedException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processUnsentMessages method");
		Boolean retVal = false;
		String message = null;
		int maxCount = new Integer(vmsUtil.loadProperties().getProperty("UTILITY_MAXCOUNT")).intValue();
		int totalCount = new Integer(utilityJobDetails.getCount()).intValue();
		int processedCount = 0;
		
		String header = prepareSOAPHeader();
		
		if(maxCount>totalCount){
	    	maxCount = totalCount;
	    }
		
		SAPService sapService = new SAPService();
	    while(processedCount<totalCount){
			ArrayList<VMSMessagingSystem> vmsMessagingSystemList = schedulerTransactionDAO.getUnsentMessages(
					maxCount,  DeviceTypeEnum.CIM.getDeviceType());
			if(vmsMessagingSystemList!=null && vmsMessagingSystemList.size()>0){
				logger.info(Logger.EVENT_UNSPECIFIED,"Total Vend code messages to be sent : " + vmsMessagingSystemList.size());
				for(VMSMessagingSystem vmsMessagingSystem:vmsMessagingSystemList){
					message = vmsMessagingSystem.getMessageData();	
					VendServiceDetails vendServiceDetails = (VendServiceDetails) vmsUtil.getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_CIM_PHASE_3);
					vendServiceDetails = sapService.lookUp(LookUpParam.MPXN, parseMPXN(message), ServiceId.CREDIT_ADJUSTMENT, vendServiceDetails, parseTxnId(message));
					setUrl(new URL(vendServiceDetails.getUrl()));
					message = prepareSOAPEnvelope(message, header);
					try {
						retVal = sendCIMMessage(message,vendServiceDetails );
					} finally {
						if(!retVal){
							logger.info(Logger.EVENT_UNSPECIFIED,"Failed to send "+ message);
						}
					}
					if(!retVal){
						header = prepareSOAPHeader();
					} else { //delete the entry from the DB
						schedulerTransactionDAO.delete(vmsMessagingSystem);
					}
				}
			}
			vmsMessagingSystemList = null;
			processedCount = processedCount+maxCount;
	    }
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processUnsentMessages method");
		return retVal;
	}
	
	private String parseTxnId(String message) {
		String txnIdTag = StringUtils.substringBetween(message, "MessageID");
		String txnId = StringUtils.substringBetween(txnIdTag, ">", "<");
		return txnId;
	}

	private String parseMPXN(String message) {
		String mpxnTag = StringUtils.substringBetween(message, "mRID");
		String mpxn = StringUtils.substringBetween(mpxnTag, ">", "<");
		return mpxn;
	}

	/**
	 * Prepare the SOAP session header.
	 * @return
	 * @throws SOAPException
	 */
	private String prepareSOAPHeader() throws SOAPException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSOAPHeader method");
		SOAPEnvelope soapEnvelope = null;
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        soapEnvelope = soapPart.getEnvelope();
        String header = soapEnvelope.toString();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSOAPHeader method");
		return header;
	}
	
	/**
	 * To trigger HEAD END request that are unsent
	 * @param String
	 * @return Boolean
	 * @throws IOException
	 * @throws DBException
	 * @throws NamingException 
	 */
	private Boolean sendCIMMessage(String xmlData, VendServiceDetails vendServiceDetails) throws IOException,
			DBException, NamingException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering sendCIMMessage method");
		HttpURLConnection httpURLConnection = null;
		OutputStreamWriter wr = null;
		BufferedReader reader = null;
		Boolean retVal = false;
		
		try {
			URL url = new URL(vendServiceDetails.getUrl());
			httpURLConnection = (HttpURLConnection) url.openConnection();
			if(httpURLConnection != null){
				
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setRequestProperty("Content-Type", "text/plain");
				httpURLConnection.setRequestProperty("Content-Length", new Integer(xmlData.length()).toString());
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setReadTimeout(10 * 1000); // give it 10 seconds to respond

				BASE64Encoder enc = new sun.misc.BASE64Encoder();
				String userpassword = vendServiceDetails.getUserName() + ":" + vendServiceDetails.getPassword();
				String encodedAuthorization = enc.encode(userpassword.getBytes());
				httpURLConnection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);

				wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
				wr.write(xmlData);
				wr.flush();
			
			if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
				logger.info(Logger.EVENT_UNSPECIFIED,"success!" + httpURLConnection.getResponseCode());
				retVal = true;
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Failed!" + httpURLConnection.getResponseCode());
			}
			if (retVal) { // only if the HTTP connection is successful
				reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
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
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendCIMMessage method");
		return retVal;
	}
}
