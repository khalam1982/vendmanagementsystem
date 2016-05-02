package com.centrica.vms.scheduler.job;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

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
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.PPKeySchedulerDAO;
import com.centrica.vms.scheduler.external.delegate.HeadendDelegate;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.scheduler.model.PPKeyTxnScheduler;
import com.centrica.vms.scheduler.service.PPKeySchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.service.helper.PPKeyServiceHelper;
import com.centrica.vms.ws.service.mapper.PPKeyUpdateMapper;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 *  Job to send the updated Pre payment Key to headend.
 *  Additional performs retries and logs failed attempts.
 *
 */
public class PPKeyUpdateJob implements Job {

	private static final int FAILED_TO_SEND_TO_HES = 408;
	private Logger logger = ESAPI.getLogger(getClass().getName());
	private JobDetails jobDetails = new JobDetails();

	private HeadendDelegate headEndDelegate;
	private PPKeySchedulerDAO schedulerTransactionDAO;
	private VMSUtils util;
	private final PPKeyServiceHelper svchelper;
	private final PPKeyUpdateMapper mapper;
	private PPKeySchedulerServiceImpl ppKeySchedulerServiceImpl ;
	private WSTransactionDAO wsTransactionDAO;
	private VMSTransactionDAO vmsTransactionDAO;

	public PPKeyUpdateJob() {
		this(new HeadendDelegate(), new PPKeySchedulerDAO(), new VMSUtils(), new PPKeyServiceHelper(), new PPKeyUpdateMapper(), new PPKeySchedulerServiceImpl(), 
				new WSTransactionDAO(),new VMSTransactionDAO());
	}

	public PPKeyUpdateJob(HeadendDelegate headEndDelegate,
			PPKeySchedulerDAO schedulerTransactionDAO, VMSUtils util, 
			final PPKeyServiceHelper svchelper, PPKeyUpdateMapper mapper,
			PPKeySchedulerServiceImpl ppKeySchedulerServiceImpl,
			WSTransactionDAO wsTransactionDAO, VMSTransactionDAO vmsTransactionDAO) {
				this.headEndDelegate = headEndDelegate;
				this.schedulerTransactionDAO = schedulerTransactionDAO;
				this.util = util;
				this.svchelper = svchelper;
				this.mapper = mapper;
				this.ppKeySchedulerServiceImpl = ppKeySchedulerServiceImpl;
				this.wsTransactionDAO = wsTransactionDAO;
				this.vmsTransactionDAO = vmsTransactionDAO;
	}


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering execute method");
		
		prepareJobDetails(jobExecutionContext);
		try {
			int status = sendPPKeyToHeadend();
			if(status==FAILED_TO_SEND_TO_HES){
				retry();
			}  else {
				updatePPKeyTxnStatus(status);
				if( Status.SC_200.getStatus() != status ) {
					final PPKeyTransaction transaction = wsTransactionDAO.getPPKeyTxnDetails(jobDetails.getTransactionID());

					/** Update Meter Details - PP Key, Vend Suspend only if it is successful in HeadEnd. 
					 *  Update PP Key in case of Cancelled Status as well  **/
					svchelper.updateMeterDetails(Status.getStatus(status), transaction);

					/** Un schedule Watch Job **/
					ppKeySchedulerServiceImpl.unschedulePPKeyWatchJob(jobDetails.getTransactionID());

					/** Schedule SAP Acknowledgement Job **/
					svchelper.sendAcknowledgementToSAP(transaction, Status.getStatus(status));
				}
			}
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception :" + e.getMessage());
			throw new JobExecutionException();
		} catch (NamingException e) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception :" + e.getMessage());
			throw new JobExecutionException();
		} finally {
			logger.debug(Logger.EVENT_SUCCESS,"Leaving execute method");	
		}

	}

	/**
	 * Attempts to retry the process if the message sending the head end failed.
	 * 
	 * @throws NamingException
	 * @throws DBException
	 */
	private void retry() throws NamingException, DBException {
		VendServiceDetails vendService = getVendServiceDetails();
		
		if (!retry(vendService)) {
			// Control reaches here if all retries are exhausted or if there is a condition that prevents further retries.
			
			//Update the time stamp
			updatePPKeyTxnStatus(Status.SC_125.getStatus());
			try {
				//Capture the SOAP request
				headEndDelegate.preparePPKeySOAPEnvelope(jobDetails); 
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
			//update the VMS Messaging table when all retries failed
			updateVMSMessagingSystem(DeviceTypeEnum.PPK.getDeviceType());
		}
	}

	
	/**
	 * @return
	 * @throws NamingException
	 */
	private VendServiceDetails getVendServiceDetails() throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendServiceDetails method");
		VendServiceDetails vendService = null;
		vendService = (VendServiceDetails) util.getVendServiceDetails(DeviceTypeConstants.PPKKEY_HES_UPDATE);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendServiceDetails method");
		return vendService;
	}
	
	/**
	 *  Attempts to send the Prepayment Key to headend.
	 *  Updates the status to 570 and the timestamp (if it is a retried attempt)
	 *  
	 * @return status - the status from headend
	 * @throws DBException
	 */
	private int sendPPKeyToHeadend() throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendCodeToHeadend method");
		if (jobDetails.getRetryCount() == 0) { // call only for first time to update the status
			updatePPKeyTxnStatus(Status.SC_570.getStatus());	
		} else {
			updatePPKeyTxnStatusTimestamp();
		}
		int status = headEndDelegate.sendPPKey(jobDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendCodeToHeadend method");
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
		String msn = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_MSN"));
		String ppkey = (String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_PPKEY"));
		Date timestamp = (Date)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("PPK_JOB_TIMESTAMP"));
		Integer retryCount = (Integer)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_RETRY_COUNT"));
		Integer status = (Integer)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty("JOB_STATUS"));
		logger.info(Logger.EVENT_UNSPECIFIED,"Job details"+new Object[]{transactionID,msn,ppkey,timestamp,retryCount,status});
		jobDetails.setTransactionID(transactionID);
		jobDetails.setMsn(msn);
		jobDetails.setPpKey(ppkey);
		jobDetails.setTimestamp(timestamp);
		jobDetails.setRetryCount(retryCount);
		jobDetails.setStatus(status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareJobDetails method");
	}

	/**
	 * Update Vend status timestamp for Vend Transaction table
	 * @throws DBException
	 */
	private void updatePPKeyTxnStatusTimestamp() throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVendTxnStatusTimestamp method");
		PPKeyTxnScheduler ppKeyTxnSdlr = schedulerTransactionDAO.getPPKeyTxnSchedulerDetails(jobDetails.getTransactionID());
		mapper.updateTimestamp(ppKeyTxnSdlr);
		schedulerTransactionDAO.update(ppKeyTxnSdlr);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVendTxnStatusTimestamp method");
	}
		

	/**
	 * Update Vend Transaction Status in Vend transaction table
	 * @param status
	 * @throws DBException
	 */
	private void updatePPKeyTxnStatus(int status) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVendTxnStatus method ");
		final PPKeyTxnScheduler ppKeyTxnSdlr = schedulerTransactionDAO.getPPKeyTxnSchedulerDetails(jobDetails.getTransactionID());
		logger.error(Logger.EVENT_FAILURE,"PPKey Transaction Status in database : " + status);
		mapper.setPPKTransStatus(ppKeyTxnSdlr, Status.getStatus(status), ppKeyTxnSdlr.getTriggerName(), jobDetails.getTransactionID());
		schedulerTransactionDAO.update(ppKeyTxnSdlr);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVendTxnStatus method ");
	}
	
	/**
	 * Method to process the retry for the given transaction id
	 * @param vendServiceDetails
	 * @return true if retry scheduling is successful. False if retry exceed or re-scheduling failed.
	 */
	private Boolean retry(VendServiceDetails vendServiceDetails) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering retry method");
		Boolean status = Boolean.FALSE;
		int retryCount = jobDetails.getRetryCount();
		logger.error(Logger.EVENT_FAILURE,"Retry Count " + retryCount);
		if(retryCount< new Integer(vendServiceDetails.getNoofretries()).intValue()){
			Long retryPeriod = new Long(vendServiceDetails.getRetryPeriod(retryCount));
			logger.error(Logger.EVENT_FAILURE,"Retry Period " + retryPeriod );
			retryCount++;
			
			//check the status of transaction before we re-schedule job
			status = alreadyAcknowledged();
			if (!status) {
				
				status = ppKeySchedulerServiceImpl
						.schedulePPKeyUpdatetoHeadEnd(retryCount,
								jobDetails.getTransactionID(), retryPeriod,
								jobDetails.getMsn(), jobDetails.getPpKey(),
								jobDetails.getTimestamp(), true); //TODO Shouldn't we be sending the last parameter as true?
				logger.error(Logger.EVENT_FAILURE,"schedule status returns : " + status);
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving retry method");
		return status;
	}


	/**
	 * Checks if the transaction that we are attempting to retry is already acknowledged.
	 * 
	 * @return true if already acknowledged.
	 * @throws DBException
	 */
	private boolean alreadyAcknowledged() throws DBException {
		boolean acknowledged = false;
	
		try {
			
			PPKeyTransaction ppkeyTransaction = wsTransactionDAO.getPPKeyTxnDetails(jobDetails.getTransactionID());
			if(ppkeyTransaction!=null){
				Iterator<PPKeyTxnStatus> itr = ppkeyTransaction.getStatusDetails().iterator();
				Integer statusCode = getPPKeyTxnStatus(itr);
				// Do nothing when status is already received ACK for PPK
				if (statusCode == Status.SC_100.getStatus()) {
					acknowledged = Boolean.TRUE; //Ignore the retry process and finish the job at this point
				}
			}
		} catch (DBException e1) {
			logger.error(Logger.EVENT_FAILURE,"Database Exception when attempting to get the current status:: " + e1.getStackTrace());
			throw e1;
		}
		return acknowledged;
	}
	
	/**
	 * @param itr
	 * @return
	 */
	protected Integer getPPKeyTxnStatus(Iterator<PPKeyTxnStatus> itr) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getPPKeyTxnStatus method");
		Integer ppKeyTransactionStatus = 0;
		PPKeyTxnStatus ppKeyTxnStatus = null;
		if (itr != null) {
			if (itr.hasNext()) {
				ppKeyTxnStatus = itr.next();
				ppKeyTransactionStatus = ppKeyTxnStatus.getStatus();
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPPKeyTxnStatus method");
		return ppKeyTransactionStatus;
	}

	/**
	 * Method to update the VMS Messaging System DB table
	 * @param deviceType
	 * @throws DBException
	 */
	private void updateVMSMessagingSystem(Integer deviceType) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVMSMessagingSystem method");
		SOAPEnvelope envelope = null;
		VMSMessagingSystem messagingSystem = new VMSMessagingSystem();
		envelope = headEndDelegate.getSOAPEnvelope();
		messagingSystem.setMessageData(envelope.toString());
		messagingSystem.setDeviceTypeID(deviceType);
		vmsTransactionDAO.insert(messagingSystem);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVMSMessagingSystem method");
	}
}
