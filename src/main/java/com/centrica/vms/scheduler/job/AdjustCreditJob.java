/**
 * VendCodeJob.java
 * Purpose: Vend code job
 * @author nagarajk
 */
package com.centrica.vms.scheduler.job;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.naming.NamingException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.external.delegate.HeadendDelegate;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.scheduler.model.VendTxnSchedulerDetails;
import com.centrica.vms.scheduler.service.VMSSchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.VendTxnWSDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;


/**
 * Methods for Vend code job
 * @see Job
 */
public class AdjustCreditJob implements Job {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	private HeadendDelegate headEndDelegate = new HeadendDelegate();
	private static final String VMS_USERNAME_VALUE="system";
	private SchedulerTransactionDAO schedulerTransactionDAO = new SchedulerTransactionDAO();
	private JobDetails jobDetails = new JobDetails();
	private VMSUtils util = new VMSUtils();

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering execute method");
		Boolean retryStatus = Boolean.FALSE;
		
		prepareJobDetails(jobExecutionContext);
		
		try {
			int status = sendAdjustCreditToHeadend();
			if(status==408){
				VendServiceDetails vendService = (VendServiceDetails) util.getVendServiceDetails
					(DeviceTypeConstants.DEVICE_TYPE_CIM_PHASE_3);
				retryStatus = processRetry(vendService);
				//update the VMS Messaging table when all retries failed
				if (retryStatus) {
					// log the SOAP message here for VMS Utility retry
					try {
						headEndDelegate.prepareSOAPEnvelope(jobDetails);
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
					updateAdjustMessagingSystem();
				}
			} else if(status!=200){ // reaches when we receive any reason code
				updateVendTxnStatus(status);
			} else {
				updateVendTxnStatus(Status.SC_200.getStatus());
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"DB Exceptionn :" + e.getMessage());
			throw new JobExecutionException();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"Naming Exceptionn :" + e.getMessage());
			throw new JobExecutionException();
		} finally {
			logger.debug(Logger.EVENT_SUCCESS,"Leaving execute method");	
		}
	}
	
	/**
	 * @return
	 * @throws DBException
	 */
	private int sendAdjustCreditToHeadend() throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendAdjustCreditToHeadend method");
		if (jobDetails.getRetryCount() == 0) { // call only for first time to update the status
			updateVendTxnStatus(Status.SC_170.getStatus());	
		} else {
			updateVendTxnStatusTimestamp();
		}
		int status = headEndDelegate.sendAdjustCredit(jobDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendAdjustCreditToHeadend method");
		return status;
	}
	
	/**
	 * Method to prepare the job details
	 * @param jobExecutionContext
	 */
	private void prepareJobDetails(JobExecutionContext jobExecutionContext) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareJobDetails method");
		Properties properies = util.loadProperties();
		String transactionID = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_TRANSACTIONID"));
		String mpxn = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_MPXN"));
		String creditValue = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_CREDITVALUE"));
		Date timestamp = (Date)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_TIMESTAMP"));
		Integer retryCount = (Integer)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_RETRY_COUNT"));
		logger.info(Logger.EVENT_UNSPECIFIED,"Job details"+new Object[]{transactionID,mpxn,timestamp});
		jobDetails.setTransactionID(transactionID);
		jobDetails.setMpxn(mpxn);
		jobDetails.setCreditValue(creditValue);
		jobDetails.setTimestamp(timestamp);
		jobDetails.setRetryCount(retryCount);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareJobDetails method");
	}

	/**
	 * Update Vend status timestamp for Vend Transaction table
	 * @throws DBException
	 */
	private void updateVendTxnStatusTimestamp() throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVendTxnStatusTimestamp method");
		Iterator<VendTxnStatus> itr = null;
		VendTxnStatus vendTxnStatus = null;
		VendTxnSchedulerDetails vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(jobDetails.getTransactionID());
		itr = vendTxnSchedulerDetails.getTxnStatusDetails().iterator();
		if (itr.hasNext()) {
			vendTxnStatus = itr.next();
			vendTxnStatus.setUpdatedTimeStamp(new Date());
			vendTxnSchedulerDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		}
		schedulerTransactionDAO.update(vendTxnSchedulerDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVendTxnStatusTimestamp method");
	}
		

	/**
	 * Update Vend Transaction Status in Vend transaction table
	 * @param status
	 * @throws DBException
	 */
	private void updateVendTxnStatus(int status) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVendTxnStatus method ");
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		VendTxnSchedulerDetails vendTransactionDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(jobDetails.getTransactionID());
		Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend Transaction Status : " + status);
		vendTxnStatus.setStatus(status);
		vendTxnStatus.setUpdatedTimeStamp(new Date());
		txnStatusDetails.add(vendTxnStatus);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
		vendTransactionDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		schedulerTransactionDAO.update(vendTransactionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVendTxnStatus method ");
	}
	
	/**
	 * Method to process the retry for the given transaction id
	 * @param vendServiceDetails
	 * @return
	 */
	private Boolean processRetry(VendServiceDetails vendServiceDetails) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering processRetry method");
		Boolean status = Boolean.FALSE;
		int retryCount = jobDetails.getRetryCount();
		logger.info(Logger.EVENT_UNSPECIFIED,"Retry Count " + retryCount);
		if(retryCount< new Integer(vendServiceDetails.getNoofretries()).intValue()){
			Long retryPeriod = new Long(vendServiceDetails.getRetryPeriod(retryCount));
			logger.info(Logger.EVENT_UNSPECIFIED,"Retry Period " + retryPeriod );
			retryCount++;
			Boolean scheduleStatus = Boolean.FALSE;
			
			//check the status of transaction before we re-schedule job
			VendTxnWSDetails vendTxnWSDetails = null;
			try {
				vendTxnWSDetails = new WSTransactionDAO().getVendTxnWSDetails(jobDetails.getTransactionID());
				if(vendTxnWSDetails!=null){
					Iterator<VendTxnStatus> itr = vendTxnWSDetails.getTxnStatusDetails().iterator();
					Integer statusCode = getVendTxnStatus(itr);
					// Do nothing when status is already voided or received ACK for voided vend
					if (statusCode == Status.SC_140.getStatus() || statusCode == Status.SC_180.getStatus()
							|| statusCode == Status.SC_190.getStatus()) {
						return scheduleStatus;
					}
				}
			} catch (DBException e1) {
				// TODO Auto-generated catch block
				logger.error(Logger.EVENT_FAILURE,"DBException:: " + e1.getMessage());
				throw e1;
			}
			
			scheduleStatus = new VMSSchedulerServiceImpl().scheduleAdjustJob(retryCount,jobDetails.getTransactionID(), retryPeriod, 
					jobDetails.getMpxn(), jobDetails.getCreditValue(),jobDetails.getTimestamp(), false);
			logger.info(Logger.EVENT_UNSPECIFIED,"schedule status returns : " + scheduleStatus);
			if(!scheduleStatus){
				status = Boolean.TRUE;
			}
		}else{
			status = Boolean.TRUE;
		}
		if (status) { //Do the updated timestamp
			updateVendTxnStatus(Status.SC_125.getStatus());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving processRetry method");
		return status;
	}
	
	/**
	 * @param itr
	 * @return
	 */
	protected Integer getVendTxnStatus(Iterator<VendTxnStatus> itr) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTxnStatus method");
		Integer vendTransactionStatus = 0;
		VendTxnStatus vendTxnStatus = null;
		if (itr != null) {
			if (itr.hasNext()) {
				vendTxnStatus = itr.next();
				vendTransactionStatus = vendTxnStatus.getStatus();
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTxnStatus method");
		return vendTransactionStatus;
	}

	/**
	 * Method to update the VMS Messaging System DB table
	 * @param deviceType
	 * @throws DBException
	 */
	private void updateAdjustMessagingSystem() throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateAdjustMessagingSystem method");
		SOAPEnvelope envelope = null;
		VMSMessagingSystem messagingSystem = new VMSMessagingSystem();
		envelope = headEndDelegate.getAdjustSOAPEnvelope();
		messagingSystem.setMessageData(envelope.toString());
		messagingSystem.setDeviceTypeID(DeviceTypeEnum.CIM.getDeviceType());
		new VMSTransactionDAO().insert(messagingSystem);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateAdjustMessagingSystem method");
	}
}
