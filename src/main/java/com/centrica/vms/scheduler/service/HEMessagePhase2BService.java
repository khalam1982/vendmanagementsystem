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

import javax.naming.NamingException;
import javax.xml.soap.SOAPException;

import org.apache.axis2.databinding.ADBException;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.hibernate.StaleObjectStateException;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.model.UtilityJobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 * Methods for HEAD END message service
 * @see IMessageServiceCommunicator
 */
public class HEMessagePhase2BService extends HEMessageProcessor implements IHEMessageService {

	private Logger logger = null;
	private VMSUtils vmsUtil = null;
	private SchedulerTransactionDAO schedulerTransactionDAO = null;
	
	/**
	 * Constructor to invoke the service.
	 * @param URL
	 */
	public HEMessagePhase2BService(URL url) {
		super(url);
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
	 */
	public Boolean processUnsentMessages(UtilityJobDetails utilityJobDetails) throws ADBException, RemoteException, 
		LoginFault, AccessDeniedFault, UnexpectedErrorFault, NamingException, SOAPException, 
		StaleObjectStateException, DBException, IOException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processUnsentMessages method");
		Boolean retVal = false;
		String message = null;
		int maxCount = new Integer(vmsUtil.loadProperties().getProperty("UTILITY_MAXCOUNT")).intValue();
		int totalCount = new Integer(utilityJobDetails.getCount()).intValue();
		int processedCount = 0;
		String sessionID = loginService(DeviceTypeConstants.DEVICE_TYPE_PHASE_2B);
		String header = prepareSessionHeader(sessionID);
		
		if(maxCount>totalCount){
	    	maxCount = totalCount;
	    }
		
	    while(processedCount<totalCount){
			ArrayList<VMSMessagingSystem> vmsMessagingSystemList = schedulerTransactionDAO.getUnsentMessages(
					maxCount,  DeviceTypeEnum.PH2B.getDeviceType());
			if(vmsMessagingSystemList!=null && vmsMessagingSystemList.size()>0){
				logger.info(Logger.EVENT_UNSPECIFIED,"Total Vend code messages to be sent : " + vmsMessagingSystemList.size());
				for(VMSMessagingSystem vmsMessagingSystem:vmsMessagingSystemList){
					message = vmsMessagingSystem.getMessageData();				
					message = prepareSOAPEnvelope(message, header);
					try {
						retVal = sendMessage(message);
					} finally {
						if(!retVal){
							logger.info(Logger.EVENT_UNSPECIFIED,"Failed to send "+ message);
							logoutService(sessionID);
						}
					}
					if(!retVal){
						sessionID = loginService(DeviceTypeConstants.DEVICE_TYPE_PHASE_2B);
						header = prepareSessionHeader(sessionID);
					} else { //delete the entry from the DB
						schedulerTransactionDAO.delete(vmsMessagingSystem);
					}
				}
			}
			vmsMessagingSystemList = null;
			processedCount = processedCount+maxCount;
	    }
	    logoutService(sessionID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processUnsentMessages method");
		return retVal;
	}
}
