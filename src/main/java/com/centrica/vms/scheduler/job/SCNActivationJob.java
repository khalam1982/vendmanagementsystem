/**
 * 
 */
package com.centrica.vms.scheduler.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import sun.misc.BASE64Encoder;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.external.model.SCNActvServiceDetails;

/**
 * @author ramasap1
 *
 */
public class SCNActivationJob implements Job {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	private ArrayList<VMSMessagingSystem> vmsMessagingSystemList;
	private SchedulerTransactionDAO schedulerTransactionDAO;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering execute method");
		schedulerTransactionDAO = new SchedulerTransactionDAO();
		try{
			sendMessage();
		}finally{
			vmsMessagingSystemList = null;
			schedulerTransactionDAO = null;
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving execute method");
	}
	
	/**
	 * To trigger SAP PI request that are received for SCN registration.
	 * @param String
	 * @return Boolean
	 * @throws IOException
	 * @throws DBException
	 */
	public void sendMessage(){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendMessage method");
		Boolean retVal = false;
		BufferedReader reader = null;
		HttpURLConnection httpURLConnection = null;
		try{
			SCNActvServiceDetails scnActvServiceDetails = (SCNActvServiceDetails)new VMSUtils().getVendServiceDetails("SCNActvResponse");
			String stringUrl=scnActvServiceDetails.getUrl();
			logger.info(Logger.EVENT_UNSPECIFIED,"URL " + scnActvServiceDetails.getUrl());
			String interfaceNamespace = stringUrl.substring(stringUrl.indexOf("interfaceNamespace="), stringUrl.length());
			interfaceNamespace = interfaceNamespace.substring(interfaceNamespace.indexOf("=")+1);
			String xmlData = prepareSoapMessage(interfaceNamespace);
			logger.info(Logger.EVENT_UNSPECIFIED,"xmlData to sendss " + xmlData );
			URL url = new URL(stringUrl);
			String username = scnActvServiceDetails.getUserName();
			logger.info(Logger.EVENT_UNSPECIFIED,"Username " + scnActvServiceDetails.getUserName());
			String password = scnActvServiceDetails.getPassword();
			//logger.info(Logger.EVENT_UNSPECIFIED,"Password " + scnActvServiceDetails.getPassword());
			httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "text/xml");
			httpURLConnection.setRequestProperty( "Content-Length", new Integer (xmlData.length()).toString());
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setRequestProperty("Authorization", "Basic " + encode(username + ":" + password));
			httpURLConnection.setReadTimeout(10*1000); // give it 1 minute to respond
			OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
			wr.write(xmlData);
			wr.flush();
			if(HttpURLConnection.HTTP_OK ==httpURLConnection.getResponseCode() ){
				logger.info(Logger.EVENT_UNSPECIFIED,"success!" + httpURLConnection.getResponseCode());
				retVal = true;
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Failed!" + httpURLConnection.getResponseCode());
			}
			reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			if(retVal){
				deleteMessages();
			}	
		}catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IOException " + ex.getMessage());
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException " + ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException " + ex.getMessage());
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"Exception " + ex.getMessage());
		}finally{
			try{
			if(reader != null)
				reader.close();
			if(httpURLConnection != null)
				httpURLConnection.disconnect();
			}catch(IOException ioe){
				logger.error(Logger.EVENT_FAILURE,"IOException " + ioe.getMessage());
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendMessage method");
	}

	private void deleteMessages() throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering deleteMessages method");
		if(vmsMessagingSystemList!=null && vmsMessagingSystemList.size()>0){
			logger.info(Logger.EVENT_UNSPECIFIED,"Total SCN Activation ACK messages to be sent : " + vmsMessagingSystemList.size());
			for(VMSMessagingSystem vmsMessagingSystem:vmsMessagingSystemList){
				schedulerTransactionDAO.delete(vmsMessagingSystem);
		  }
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving deleteMessages method");
	}
	/**
	 * To encode the user credentials.
	 * @param String
	 * @return String
	 */
	private String encode (String source) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering encode method");
		BASE64Encoder enc = new sun.misc.BASE64Encoder();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving encode method");
		return(enc.encode(source.getBytes()));
	}
	
	/**
	 *  Method to prepare the soap message
	 * @param interfaceNamespace
	 * @return
	 */
	private String prepareSoapMessage(String interfaceNamespace){
		MessageFactory factory;
		String message = "";
		SOAPEnvelope  soapEnvelope  = null;
		SOAPBodyElement smartMeterManageSCN = null;
		String ackMessage = "";
		try{
			factory = MessageFactory.newInstance();
			SOAPMessage soapMessage = factory.createMessage();
			soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
			QName bodyName = new QName(interfaceNamespace, "SmartMeterManageSCNAckn", "urn");
			smartMeterManageSCN = soapEnvelope.getBody().addBodyElement(bodyName);
			int count = schedulerTransactionDAO.getResendTransactionCount(DeviceTypeEnum.ISU.getDeviceType());
			vmsMessagingSystemList = schedulerTransactionDAO.getUnsentMessages(count, DeviceTypeEnum.ISU.getDeviceType());
			if(vmsMessagingSystemList!=null && vmsMessagingSystemList.size()>0){
				logger.info(Logger.EVENT_UNSPECIFIED,"Total SCN Activation ACK messages to be sent : " + vmsMessagingSystemList.size());
				for(VMSMessagingSystem vmsMessagingSystem:vmsMessagingSystemList){
					message = vmsMessagingSystem.getMessageData();
					smartMeterManageSCN.addChildElement(message);
			}
		 }
		 ackMessage = soapEnvelope.toString();
		 ackMessage = ackMessage.replaceAll("<<", "<");
		 ackMessage = ackMessage.replaceAll("> />", ">");
		 logger.info(Logger.EVENT_UNSPECIFIED,"soapEnvelope " + ackMessage);
		 }catch(SOAPException ex){
			logger.error(Logger.EVENT_FAILURE,"Soap Exception is thrown " + ex.getMessage());
		 }
		 return ackMessage;
	}
	
}
