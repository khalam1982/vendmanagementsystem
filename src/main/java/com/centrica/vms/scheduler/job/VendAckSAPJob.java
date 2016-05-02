package com.centrica.vms.scheduler.job;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Properties;

import javax.naming.NamingException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.scheduler.external.delegate.SAPDelegate;
import com.centrica.vms.scheduler.external.model.VendAckSAPServiceDetails;
import com.centrica.vms.scheduler.model.VendAckJobDetails;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

public class VendAckSAPJob implements Job {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	private VendAckJobDetails jobDetails = new VendAckJobDetails();
	private VMSUtils util = new VMSUtils();
	SAPDelegate sapDelegate = new SAPDelegate();

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering VendAckSAPJob execute method");
		Boolean retryStatus = Boolean.FALSE;
		prepareVendAckJobDetails(jobExecutionContext);		
		int status = sapDelegate.sendVendAcknowledgement(jobDetails);
		if(status==408){
			try {
				retryStatus = processRetry(getVendServiceDetailsForAckSAP());
				//update the VMS Messaging table when all retries failed
				if (retryStatus) {
					// log the SOAP message here in messaging system table
					try {
						sapDelegate.prepareSOAPEnvelope(jobDetails);
					} catch (LoginFault e) {
						logger.error(Logger.EVENT_FAILURE,"LoginFault Exception :" + e.getMessage());
					} catch (AccessDeniedFault e) {
						logger.error(Logger.EVENT_FAILURE,"AccessDeniedFault Exception :" + e.getMessage());
					} catch (UnexpectedErrorFault e) {
						logger.error(Logger.EVENT_FAILURE,"UnexpectedErrorFault Exception :" + e.getMessage());
					} catch (AxisFault e) {
						logger.error(Logger.EVENT_FAILURE,"AxisFault Exception :" + e.getMessage());
					} catch (RemoteException e) {
						logger.error(Logger.EVENT_FAILURE,"RemoteException Exception :" + e.getMessage());
					}
					updateVMSMessagingSystem(jobDetails.getDeviceType());	
				}
			} catch (DBException e) {
				logger.error(Logger.EVENT_FAILURE,"DBException  in VendAckSAPJob execute method"+e.getMessage());
			} catch (NamingException e) {
				logger.error(Logger.EVENT_FAILURE,"Naming Exception in VendAckSAPJob execute method"+e.getMessage());
			}
		}else{
			if (status == 200) {
				logger.info(Logger.EVENT_UNSPECIFIED,"Vend Acknowledgement message successfully delivered");	
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Vend Acknowledgement message failed");
			}	
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving VendAckSAPJob execute method");
	}

	/**
	 * @param deviceType
	 * @throws DBException
	 */
	private void updateVMSMessagingSystem(Integer deviceType) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVMSMessagingSystem method");
		SOAPEnvelope envelope = null;
		VMSMessagingSystem messagingSystem = new VMSMessagingSystem();
		envelope = sapDelegate.getSOAPEnvelope();
		messagingSystem.setMessageData(envelope.toString());
		messagingSystem.setDeviceTypeID(deviceType);
		new VMSTransactionDAO().insert(messagingSystem);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVMSMessagingSystem method");
		
	}

	/**
	 * @param jobExecutionContext
	 */
	private void prepareVendAckJobDetails(JobExecutionContext jobExecutionContext) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareVendAckJobDetails method");
		Properties properies = util.loadProperties();
		String transactionID = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_TRANSACTIONID"));
		String pan = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_PAN"));
		String vendCode = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_VENDCODE"));
		String creditValue = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_CREDITVALUE"));
		Date timestamp = (Date)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_TIMESTAMP"));
		Integer retryCount = (Integer)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_RETRY_COUNT"));
		Integer status = (Integer)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_STATUS"));
		String msn = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_MSN"));
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend Ack Job details"+new Object[]{transactionID,pan,vendCode,status,timestamp});
		jobDetails.setTransactionID(transactionID);
		jobDetails.setPan(pan);
		jobDetails.setVendCode(vendCode);
		jobDetails.setCreditValue(creditValue);
		jobDetails.setTimestamp(timestamp);
		jobDetails.setRetryCount(retryCount);
		jobDetails.setStatus(status);
		jobDetails.setMsn(msn);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareVendAckJobDetails method");			
	}	
	
	/**
	 * Method to process the retry for the given transaction id
	 * @param vendAckSAPServiceDetails
	 * @return
	 */
	private Boolean processRetry(VendAckSAPServiceDetails vendServiceDetails) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processRetry for VendAckJob method");
		Boolean status = Boolean.FALSE;
		int retryCount = jobDetails.getRetryCount();
		logger.info(Logger.EVENT_UNSPECIFIED,"Retry Count " + retryCount);
		Boolean scheduleStatus = Boolean.FALSE;
		if(retryCount< new Integer(vendServiceDetails.getNoofretries()).intValue()){
			Long retryPeriod = new Long(vendServiceDetails.getRetryPeriod(retryCount));
			logger.info(Logger.EVENT_UNSPECIFIED,"Retry Period " + retryPeriod );
			retryCount++;			
			scheduleStatus = new VMSSchedulerServiceImpl().scheduleVendAcknowledgeSAPJob(retryCount,jobDetails.getTransactionID(), retryPeriod, jobDetails.getPan(),jobDetails.getMsn(),
					jobDetails.getVendCode(),jobDetails.getCreditValue(),jobDetails.getTimestamp(),jobDetails.getStatus(), false);
			logger.info(Logger.EVENT_UNSPECIFIED,"scheduleVendAcknowledgeSAPJob status returns : " + scheduleStatus);
			if(!scheduleStatus){
				status = Boolean.TRUE;
			}
		} else {
			status = Boolean.TRUE;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processRetry for VendAckJob method");
		return status;
	}
	/**
	 * @return
	 * @throws NamingException
	 */
	private VendAckSAPServiceDetails getVendServiceDetailsForAckSAP() throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendServiceDetailsForAckSAP method");
		VendAckSAPServiceDetails vendService = null;
		vendService = (VendAckSAPServiceDetails) util.getVendServiceDetails("VendAckSAPService");		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendServiceDetailsForAckSAP method");
		return vendService;
	}
}
