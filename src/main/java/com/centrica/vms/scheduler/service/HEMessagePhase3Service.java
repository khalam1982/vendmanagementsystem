/**
 * HEMessageService.java
 * Purpose: HEAD END message service
 * @author ramasap1
 */
package com.centrica.vms.scheduler.service;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.NamingException;
import javax.xml.soap.SOAPException;

import org.apache.axis2.databinding.ADBException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.owasp.esapi.Logger;

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
import com.centrica.vms.scheduler.external.service.HeadendCommonService;
import com.centrica.vms.scheduler.external.service.SAPService;
import com.centrica.vms.scheduler.model.UtilityJobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 * Methods for HEAD END message service
 * @see IMessageServiceCommunicator
 */
public class HEMessagePhase3Service extends HEMessageProcessor implements IHEMessageService {

	private Logger logger = null;
	private VMSUtils vmsUtil = null;
	private SchedulerTransactionDAO schedulerTransactionDAO = null;
	
	/**
	 * Constructor to invoke the service.
	 * @param URL
	 */
	public HEMessagePhase3Service(URL url) {
		super(url);
		logger = super.logger;
		vmsUtil = super.vmsUtil;
		schedulerTransactionDAO = super.schedulerTransactionDAO;
	}
	
	/**
	 * Constructor to invoke the service.
	 * @param URL - URL
	 * @param logger - Logger
	 * @param vmsUtil - VMSUtils
	 * @param schedulerTransactionDAO - SchedulerTransactionDAO
	 */
	public HEMessagePhase3Service(final URL url, final Logger logger, final VMSUtils vmsUtil, 
			final SchedulerTransactionDAO schedulerTransactionDAO, final HeadendCommonService headendCommonService) {
		super(url, logger, vmsUtil, schedulerTransactionDAO, headendCommonService);
		this.logger = logger;
		this.vmsUtil = vmsUtil;
		this.schedulerTransactionDAO = schedulerTransactionDAO;
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
		final Properties properties = vmsUtil.loadProperties();
		int maxCount = new Integer(properties.getProperty("UTILITY_MAXCOUNT")).intValue();
		int totalCount = new Integer(utilityJobDetails.getCount()).intValue();
		int processedCount = 0;
		
		if(maxCount>totalCount){
	    	maxCount = totalCount;
	    }
		
		SAPService sapService = new SAPService();
	    while(processedCount<totalCount){
			ArrayList<VMSMessagingSystem> vmsMessagingSystemList = schedulerTransactionDAO.getUnsentMessages(
					maxCount,  DeviceTypeEnum.PH3.getDeviceType());
			if(vmsMessagingSystemList!=null && vmsMessagingSystemList.size()>0){
				logger.info(Logger.EVENT_UNSPECIFIED,"Total Vend code messages to be sent : " + vmsMessagingSystemList.size());
				for(VMSMessagingSystem vmsMessagingSystem:vmsMessagingSystemList){
					message = vmsMessagingSystem.getMessageData();	
					VendServiceDetails vendServiceDetails = (VendServiceDetails) vmsUtil.getVendServiceDetails(DeviceTypeConstants.DEVICE_TYPE_PHASE_3);
					vendServiceDetails = sapService.lookUp(LookUpParam.PCN, parseSCN(message), ServiceId.APPLY_VEND_CODE, vendServiceDetails, parseTxnId(message));
					setUrl(new URL(vendServiceDetails.getUrl()));
					String sessionID = loginService(DeviceTypeConstants.DEVICE_TYPE_PHASE_3, vendServiceDetails);
					String header = prepareSessionHeader(sessionID);
					
					message = prepareSOAPEnvelope(message, header);
					try {
						retVal = sendMessage(message, new URL(vendServiceDetails.getUrl()));
					} finally {
						if(!retVal){
							logger.info(Logger.EVENT_UNSPECIFIED,"Failed to send "+ message);
							logoutService(sessionID);
						}
					}
					if(!retVal){
						sessionID = loginService(DeviceTypeConstants.DEVICE_TYPE_PHASE_3, vendServiceDetails);
						header = prepareSessionHeader(sessionID);
					} else { //delete the entry from the DB
						schedulerTransactionDAO.delete(vmsMessagingSystem);
					}
					logoutService(sessionID);
				}
			}
			vmsMessagingSystemList = null;
			processedCount = processedCount+maxCount;
	    }
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processUnsentMessages method");
		return retVal;
	}

	private String parseTxnId(String message) {
		String txnIdTag = StringUtils.substringBetween(message, "transactionId");
		String txnId = StringUtils.substringBetween(txnIdTag, ">", "<");
		return txnId;
	}
	
	private String parseSCN(String message) {
		String scnTag = StringUtils.substringBetween(message, "paymentCardNumber");
		String scn = StringUtils.substringBetween(scnTag, ">", "<");
		return scn;
	}
}
