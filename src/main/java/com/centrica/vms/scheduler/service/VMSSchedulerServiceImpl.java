/**
 * VMSSchedulerServiceImpl.java
 * Purpose: VMS Scheduler service implementation
 * @author ramasap1
 */
package com.centrica.vms.scheduler.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.naming.NamingException;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.SchedulerTransactionDAO;
import com.centrica.vms.scheduler.model.VendTxnSchedulerDetails;
import com.centrica.vms.ws.model.VendRetryDetails;
/**
 * Methods for VMS Scheduler service implementation
 */
public class VMSSchedulerServiceImpl implements VMSSchedulerService {
	
	Properties properties;
	Logger logger = ESAPI.getLogger(getClass().getName());
	private static final String VMS_USERNAME_VALUE="system";

	private final SchedulerTransactionDAO schedulerTransactionDAO;
	private final VMSScheduleServiceHelper helper;

	/**
	 * Constructor
	 */
	public VMSSchedulerServiceImpl() {
		schedulerTransactionDAO = new SchedulerTransactionDAO();
		helper = new VMSScheduleServiceHelper();
	}

	/**
	 * Constructor
	 * @param schedulerTransactionDAO - SchedulerTransactionDAO
	 * @param helper - VMSScheduleServiceHelper
	 */
	public VMSSchedulerServiceImpl(final SchedulerTransactionDAO schedulerTransactionDAO, final VMSScheduleServiceHelper helper) {
		this.schedulerTransactionDAO = schedulerTransactionDAO;
		this.helper = helper;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.service.VMSSchedulerService#scheduleJob(int, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)
	 */
	public Boolean scheduleJob(int retryCount,String transactionID,Long holdingPeriod,String pan,String vendCode,
			String paymentType,String creditValue,Date timestamp, Integer deviceType, Boolean isReschedule) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering In scheduleJob method ");
		properties = new VMSUtils().loadProperties();
		int triggerStatus;
		Boolean returnStatus = Boolean.FALSE;
		
		try{ 
			//Scheduler scheduler = new StdSchedulerFactory().getScheduler(properties.getProperty("SCHEDULER_NAME"));
			/* Retrieve the scheduler object from the jndi context -- Start */
			final Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));
			/* Retrieve the scheduler object from the jndi context -- end */
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not found or not started");
			}else{
				int newRetryCount = getNewRetryCount(transactionID);
				String triggerName = properties.getProperty("TRIGGER_PREFIX")+"_"+transactionID+"_"+retryCount+"_"+newRetryCount;
				triggerStatus = schedule(retryCount,transactionID,holdingPeriod,pan,vendCode,
						paymentType, scheduler,triggerName,creditValue,timestamp,deviceType, isReschedule, newRetryCount);
				logger.debug(Logger.EVENT_SUCCESS,"Trigger Name is : "+triggerName +"trigger Status is:"+ triggerStatus);
		        returnStatus = Boolean.TRUE;
			}
		}
		catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the scheduleJob method");
		return returnStatus;
	}

	private int getNewRetryCount(String transactionID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the getNewRetryCount method");
		int retryCount = 0;
		VendRetryDetails vendRetryCount = null;
		try {
			vendRetryCount = schedulerTransactionDAO.getVendRetryCount(transactionID);
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"Error when trying to get the retry count " + e.getMessage());
		}
		if (vendRetryCount != null) {
			retryCount = vendRetryCount.getRetryCount();
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the getNewRetryCount method");
		return retryCount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.service.VMSSchedulerService#scheduleJob(int, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)
	 */
	public Boolean scheduleAdjustJob(int retryCount,String transactionID,Long holdingPeriod,String mpxn,
			String creditValue,Date timestamp, Boolean isReschedule) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering In scheduleAdjustJob method ");
		properties = new VMSUtils().loadProperties();
		int triggerStatus;
		Boolean returnStatus = Boolean.FALSE;
		
		try{ 
			Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));
			/* Retrieve the scheduler object from the jndi context -- end */
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not found or not started");
			}else{
				String triggerName = properties.getProperty("TRIGGER_PREFIX")+"_"+transactionID+"_"+retryCount;
				triggerStatus = scheduleAdjust(retryCount,transactionID,holdingPeriod,mpxn,
						scheduler,triggerName,creditValue,timestamp,isReschedule);
		        logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is : "+triggerName +"trigger Status is:"+ triggerStatus);
		        returnStatus = Boolean.TRUE;
			}
		}
		catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the scheduleAdjustJob method");
		return returnStatus;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.service.VMSSchedulerService#unScheduleJob(java.lang.String, com.centrica.vms.model.VendTransaction.Status)
	 */
	public Boolean unScheduleJob(String transactionID,Status txnStatus) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering In unScheduleJob method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is "+transactionID);
		properties = new VMSUtils().loadProperties();
		Boolean status = Boolean.FALSE;
		try{
			//Scheduler scheduler = new StdSchedulerFactory().getScheduler(properties.getProperty("SCHEDULER_NAME"));
			/* Retrieve the scheduler object from the jndi context -- Start */
			final Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));
			/* Retrieve the scheduler object from the jndi context -- end */
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not available");
			}else{
				status = unSchedule(transactionID, scheduler, txnStatus);
			}
		}
		catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is thrown" + ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"unschedule job response status is "+ status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving In unScheduleJob method ");
		return status;
	}

    /*
     * (non-Javadoc)
     * @see com.centrica.vms.scheduler.service.VMSSchedulerService#scheduleHEUtility(java.lang.String, java.lang.String)
     */
	public Integer scheduleHEUtility(String count,String userName, int deviceType){
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleHEUtility method");
		properties = new VMSUtils().loadProperties();
		int triggerStatus = -1;
		try{ 
			/* Retrieve the scheduler object from the jndi context -- Start */
			final Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));
			/* Retrieve the scheduler object from the jndi context -- end */
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not found or not started");
			}else{
				
				String triggerName = properties.getProperty("HETRIGGER_NAME");
				String triggerGroup = properties.getProperty("HETRIGGER_GROUP");
				triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
				if(triggerStatus==-1){
					createHESimpleTrigger(count, userName, deviceType, scheduler, triggerName,triggerGroup);
					triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
				}else{
					logger.info(Logger.EVENT_UNSPECIFIED,"Scheduled job is still running trigger Status is: " +triggerStatus);
					triggerStatus = 1;
				}

		        logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is : "+triggerName +"trigger Status is:"+ triggerStatus);
			}
		}
		catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving scheduleHEUtility method");
		return triggerStatus;
	}

    /*
     * (non-Javadoc)
     * @see com.centrica.vms.scheduler.service.VMSSchedulerService#scheduleCIMUtility(java.lang.String, java.lang.String)
     */
	public Integer scheduleCIMUtility(String count,String userName){
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleCIMUtility method");
		properties = new VMSUtils().loadProperties();
		int triggerStatus = -1;
		try{ 
			Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));
			/* Retrieve the scheduler object from the jndi context -- end */
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not found or not started");
			}else{
				String triggerName = properties.getProperty("HETRIGGER_NAME");
				String triggerGroup = properties.getProperty("HETRIGGER_GROUP");
				triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
				if(triggerStatus==-1){
					createHESimpleTrigger(count, userName, DeviceTypeEnum.CIM.getDeviceType(), 
							scheduler, triggerName,triggerGroup); 
					triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
				}else{
					logger.info(Logger.EVENT_UNSPECIFIED,"Scheduled job is still running trigger Status is: " +triggerStatus);
					triggerStatus = 1;
				}

		        logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is : "+triggerName +"trigger Status is:"+ triggerStatus);
			}
		}
		catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving scheduleCIMUtility method");
		return triggerStatus;
	}

	/**
	 * Method to create the headend simple trigger
	 * @param String count
	 * @param String userName
	 * @param Scheduler
	 * @param String triggerName
	 * @param String triggerGroup
	 * @return 
	 * @throws SchedulerException
	 */
	private void createHESimpleTrigger(String count, String userName, int deviceType,
			Scheduler scheduler, String triggerName, String triggerGroup)throws SchedulerException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createHESimpleTrigger method");
		SimpleTrigger trigger = new SimpleTrigger(triggerName, triggerGroup,new Date());
		createJobDataMap(count, userName,trigger);
		String jobName = null;
		String jobGroup = null;
		if (deviceType == DeviceTypeEnum.PH2B.getDeviceType()) {
			jobName = properties.getProperty("HE_PHASE2B_JOB_NAME");
			jobGroup = properties.getProperty("HE_PHASE2B_JOB_GROUP");
		} else if (deviceType == DeviceTypeEnum.PH3.getDeviceType()) {
			jobName = properties.getProperty("HE_PHASE3_JOB_NAME");
			jobGroup = properties.getProperty("HE_PHASE3_JOB_GROUP");
		} else if (deviceType == DeviceTypeEnum.CIM.getDeviceType()) {
			jobName = properties.getProperty("HE_CIM_JOB_NAME");
			jobGroup = properties.getProperty("HE_CIM_JOB_GROUP");
		} else {
			//TODO: will not reach here...
		}
		trigger.setJobName(jobName);
		trigger.setJobGroup(jobGroup);
		scheduler.scheduleJob(trigger);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createHESimpleTrigger method");
	}

	/**
	 *  Method to create the job data map
	 * @param String count
	 * @param String userName
	 * @param SimpleTrigger
	 * @return
	 */
	private void createJobDataMap(String count, String userName,SimpleTrigger trigger) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createJobDataMap method");
		JobDataMap  jobDataMap = new JobDataMap();
		jobDataMap.put(properties.getProperty("USERNAME"),userName);
		jobDataMap.put(properties.getProperty("COUNT"), count);
		logger.info(Logger.EVENT_UNSPECIFIED,"count :" +count+"username"+userName);
		trigger.setJobDataMap(jobDataMap);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createJobDataMap method");
	}


	/**
	 * Method to schedule the trigger for the job
	 * @param int retryCount
	 * @param String transactionID
	 * @param Long holdingPeriod
	 * @param String pan
	 * @param String vendCode
	 * @param String paymentType
	 * @param Scheduler
	 * @param String triggerName
	 * @param String creditValue
	 * @param Date timestamp
	 * @return int
	 * @throws SchedulerException
	 * @throws DBException
	 */
	private int schedule(int retryCount,String transactionID,Long holdingPeriod,String pan,
			String vendCode,String paymentType,Scheduler scheduler, String triggerName,
			String creditValue,Date timestamp, Integer deviceType, Boolean isReschedule, int newRetryCount)
			throws SchedulerException,DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering schedule method");
		VendTxnSchedulerDetails vendTransactionDetails = null;
		String triggerGroup = properties.getProperty("TRIGGER_GROUP");
		SimpleTrigger trigger = createTrigger(transactionID,holdingPeriod,pan,vendCode,
				paymentType, triggerName,creditValue,timestamp, retryCount,deviceType);
		if (newRetryCount == 0 && retryCount == 0 && !isReschedule) { // Update only when it is holding pen.
			updateVendTxnDetails(Status.SC_120,triggerName,transactionID);	
		} else if (isReschedule) { //update the time stamp of holding pen status
			vendTransactionDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			Iterator<VendTxnStatus> itr = vendTransactionDetails.getTxnStatusDetails().iterator();
			if (itr != null) {
				while (itr.hasNext()) {
					VendTxnStatus vendTxnStatus = itr.next();
					if (vendTxnStatus.getStatus().equals(Status.SC_120.getStatus())) {
						vendTxnStatus.setUpdatedTimeStamp(new Date());
						schedulerTransactionDAO.update(vendTransactionDetails);
						break;
					}
				}
			}
		} else { // update the trigger name and time stamp for each retry 
			vendTransactionDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			vendTransactionDetails.setTriggerName(triggerName);
			schedulerTransactionDAO.update(vendTransactionDetails);
		}
		scheduler.scheduleJob(trigger);
		int triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger details "+triggerName+ ","+triggerGroup+","+triggerStatus);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving schedule method");
		return triggerStatus;
	}

	/**
	 * Method to schedule the trigger for the job
	 * @param int retryCount
	 * @param String transactionID
	 * @param Long holdingPeriod
	 * @param String pan
	 * @param String vendCode
	 * @param Scheduler
	 * @param String triggerName
	 * @param String creditValue
	 * @param Date timestamp
	 * @return int
	 * @throws SchedulerException
	 * @throws DBException
	 */
	private int scheduleAdjust(int retryCount,String transactionID,Long holdingPeriod,String mpxn,
			Scheduler scheduler, String triggerName,
			String creditValue,Date timestamp, Boolean isReschedule)
			throws SchedulerException,DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering schedule method");
		VendTxnSchedulerDetails vendTransactionDetails = null;
		SchedulerTransactionDAO schedulerTransactionDAO = new SchedulerTransactionDAO();
		String triggerGroup = properties.getProperty("TRIGGER_GROUP");
		SimpleTrigger trigger = createAdjustTrigger(transactionID,holdingPeriod,mpxn,
				triggerName,creditValue,timestamp, retryCount);
		/*if (retryCount == 0 && !isReschedule) { // Update only when it is holding pen.
			updateVendTxnDetails(Status.SC_120,triggerName,transactionID);	
		} else*/ 
		if (isReschedule) { //update the time stamp of holding pen status
			vendTransactionDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			Iterator<VendTxnStatus> itr = vendTransactionDetails.getTxnStatusDetails().iterator();
			if (itr != null) {
				while (itr.hasNext()) {
					VendTxnStatus vendTxnStatus = itr.next();
					if (vendTxnStatus.getStatus().equals(Status.SC_120.getStatus())) {
						vendTxnStatus.setUpdatedTimeStamp(new Date());
						schedulerTransactionDAO.update(vendTransactionDetails);
						break;
					}
				}
			}
		} else { // update the trigger name and time stamp for each retry 
			vendTransactionDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
			vendTransactionDetails.setTriggerName(triggerName);
			schedulerTransactionDAO.update(vendTransactionDetails);
		}
		scheduler.scheduleJob(trigger);
		int triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger details "+triggerName+ ","+triggerGroup+","+triggerStatus);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving schedule method");
		return triggerStatus;
	}

	/**
	 * Method to unschedule the job
	 * @param transactionID
	 * @param Scheduler
	 * @param Status
	 * @return Boolean
	 * @throws SchedulerException
	 * @throws DBException
	 */
	private Boolean unSchedule(String transactionID, Scheduler scheduler,
			Status txnStatus) throws SchedulerException,DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering unSchedule method");
		Boolean status = Boolean.FALSE;
		VendTxnSchedulerDetails vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
		String triggerName = vendTxnSchedulerDetails.getTriggerName();
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is:" + triggerName);
		if(triggerName!=null && !triggerName.equals("")){
			VendTxnStatus vendTxnStatus = new VendTxnStatus();
			String triggerGroup = properties.getProperty("TRIGGER_GROUP");
			boolean unscheduleStatus = scheduler.unscheduleJob(triggerName, triggerGroup);
			logger.info(Logger.EVENT_UNSPECIFIED,"unscheduleStatus is "+unscheduleStatus);
			if(unscheduleStatus){
				vendTxnStatus.setStatus(new VMSUtils().getVMSStatus(txnStatus.getStatus()));
				vendTxnStatus.setUpdatedTimeStamp(new Date());
				vendTxnSchedulerDetails.setUpdatedBy(VMS_USERNAME_VALUE);
				Set<VendTxnStatus> txnStatusDetails = vendTxnSchedulerDetails.getTxnStatusDetails();
				txnStatusDetails.add(vendTxnStatus);
				vendTxnSchedulerDetails.setTxnStatusDetails(txnStatusDetails);
				schedulerTransactionDAO.update(vendTxnSchedulerDetails);
				status = Boolean.TRUE;
			}
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"Trigger name is not found for the given transaction id");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving unSchedule method");
		return status;
	}
	
	/**
	 * Method to create the trigger for the job
	 * @param String transactionID
	 * @param Long holdingPeriod
	 * @param String pan
	 * @param String vendCode
	 * @param String paymentType
	 * @param String triggerName
	 * @param String creditValue
	 * @param Date time stamp
	 * @param Integer retry count
	 * @return SimpleTrigger
	 */
	private SimpleTrigger createTrigger(String transactionID,Long holdingPeriod,String pan,
			String vendCode,String paymentType,String triggerName,
			String creditValue,Date timestamp, Integer retryCount, Integer deviceType) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createTrigger method ");
		VMSUtils vmsUtils = new VMSUtils();
		Date scheduleTime = vmsUtils.getScheduleTime(holdingPeriod);
		String triggerGroup = properties.getProperty("TRIGGER_GROUP");
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Group is "+ triggerGroup);
		SimpleTrigger trigger = new SimpleTrigger(triggerName, triggerGroup, scheduleTime);
		createJobDataMap(transactionID,pan,vendCode,paymentType, trigger,creditValue,timestamp, retryCount, deviceType);
		String jobName = properties.getProperty("JOB_NAME");
		String jobGroup = properties.getProperty("JOB_GROUP");
		logger.info(Logger.EVENT_UNSPECIFIED,"Job Details "+ new Object[]{jobName,jobGroup});
		trigger.setJobName(jobName);
		trigger.setJobGroup(jobGroup);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createTrigger method ");
		return trigger;
	}

	/**
	 * Method to create the trigger for the adjust credit job
	 * @param String transactionID
	 * @param Long holdingPeriod
	 * @param String pan
	 * @param String paymentType
	 * @param String triggerName
	 * @param String creditValue
	 * @param Date time stamp
	 * @param Integer retry count
	 * @return SimpleTrigger
	 */
	private SimpleTrigger createAdjustTrigger(String transactionID,Long holdingPeriod,String mpxn,
			String triggerName, String creditValue,Date timestamp, Integer retryCount) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createTrigger method ");
		VMSUtils vmsUtils = new VMSUtils();
		Date scheduleTime = vmsUtils.getScheduleTime(holdingPeriod);
		String triggerGroup = properties.getProperty("TRIGGER_GROUP");
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Group is "+ triggerGroup);
		SimpleTrigger trigger = new SimpleTrigger(triggerName, triggerGroup, scheduleTime);
		createAdjustJobDataMap(transactionID,mpxn,trigger,creditValue,timestamp, retryCount);
		String jobName = properties.getProperty("ADJUST_JOB_NAME");
		String jobGroup = properties.getProperty("ADJUST_JOB_GROUP");
		logger.info(Logger.EVENT_UNSPECIFIED,"Job Details "+ new Object[]{jobName,jobGroup});
		trigger.setJobName(jobName);
		trigger.setJobGroup(jobGroup);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createTrigger method ");
		return trigger;
	}

  /**
 * @param transactionID
 * @param pan
 * @param mpxn
 * @param trigger
 * @param creditValue
 * @param timestamp
 * @param retryCount
 * @param deviceType
 */
private void createAdjustJobDataMap(String transactionID,
			String mpxn, SimpleTrigger trigger, String creditValue, Date timestamp,
			Integer retryCount) {
	    logger.debug(Logger.EVENT_SUCCESS,"Entering createAdjustJobDataMap method ");
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put(properties.getProperty("JOB_TRANSACTIONID"),	transactionID);
		jobDataMap.put(properties.getProperty("JOB_MPXN"), mpxn);
		jobDataMap.put(properties.getProperty("JOB_CREDITVALUE"), creditValue);
		jobDataMap.put(properties.getProperty("JOB_TIMESTAMP"), timestamp);
		jobDataMap.put(properties.getProperty("JOB_RETRY_COUNT"), retryCount);
		trigger.setJobDataMap(jobDataMap);
	    logger.debug(Logger.EVENT_SUCCESS,"Leaving createAdjustJobDataMap method ");
		
	}

/**
   * Method to create the job data map object
   * @param String transactionID
   * @param String pan
   * @param String vendCode
   * @param String paymentType
   * @param SimpleTrigger
   * @param String creditValue
   * @param Date time stamp
   * @param Integer retry count
   * @return 
   */
	private void createJobDataMap(String transactionID,String pan,String vendCode,String paymentType,
			SimpleTrigger trigger,String creditValue,Date timestamp, Integer retryCount, Integer deviceType) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createJobDataMap method ");
		JobDataMap  jobDataMap = new JobDataMap();
		jobDataMap.put(properties.getProperty("JOB_TRANSACTIONID"),transactionID);
		jobDataMap.put(properties.getProperty("JOB_PAN"), pan);
		jobDataMap.put(properties.getProperty("JOB_PAYMENTTYPE"),paymentType);
		jobDataMap.put(properties.getProperty("JOB_VENDCODE"), vendCode);
		jobDataMap.put(properties.getProperty("JOB_CREDITVALUE"), creditValue);
		jobDataMap.put(properties.getProperty("JOB_TIMESTAMP"), timestamp);
		jobDataMap.put(properties.getProperty("JOB_RETRY_COUNT"), retryCount);
		jobDataMap.put(properties.getProperty("JOB_DEVICE_TYPE"), deviceType);
		trigger.setJobDataMap(jobDataMap);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createJobDataMap method ");
	}
	
	/**
	 * Method to update the vend transaction details
	 * @param retryCount
	 * @param status
	 * @param triggerName
	 * @param transactionID
	 * @throws DBException
	 */
	private void updateVendTxnDetails(Status status,String triggerName,String transactionID) throws DBException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateVendTxnDetails method ");
		VendTxnStatus vendTxnStatus = new VendTxnStatus();
		VendTxnSchedulerDetails vendTransactionDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
		Set<VendTxnStatus> txnStatusDetails = vendTransactionDetails.getTxnStatusDetails();
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend Transaction Status : " + status.getStatus());
		vendTxnStatus.setStatus(new VMSUtils().getVMSStatus(status.getStatus()));
		vendTxnStatus.setUpdatedTimeStamp(new Date());
		txnStatusDetails.add(vendTxnStatus);
		vendTransactionDetails.setTxnStatusDetails(txnStatusDetails);
		vendTransactionDetails.setTriggerName(triggerName);
		vendTransactionDetails.setTransactionID(transactionID);
		vendTransactionDetails.setUpdatedBy(VMS_USERNAME_VALUE);
		schedulerTransactionDAO.update(vendTransactionDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateVendTxnDetails method ");
	}
	@Override
	public Boolean unScheduleJob(String transactionID) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering In unScheduleJob without status method ");
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is "+transactionID);
		properties = new VMSUtils().loadProperties();
		Boolean status = Boolean.FALSE;
		try{
			//Scheduler scheduler = new StdSchedulerFactory().getScheduler(properties.getProperty("SCHEDULER_NAME"));
			Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not available");
			}else{
				status = unSchedule(transactionID, scheduler);
			}
		}
		catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is thrown" + ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"unschedule job response status is "+ status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving unScheduleJob without status method ");
		return status;
	}
	private Boolean unSchedule(String transactionID, Scheduler scheduler) throws SchedulerException,DBException{
		// TODO Auto-generated method stub
		logger.debug(Logger.EVENT_SUCCESS,"Entering unSchedule without status method");
		Boolean status = Boolean.FALSE;
		SchedulerTransactionDAO schedulerTransactionDAO = new SchedulerTransactionDAO();
		VendTxnSchedulerDetails vendTxnSchedulerDetails = schedulerTransactionDAO.getVendTxnSchedulerDetails(transactionID);
		String triggerName = vendTxnSchedulerDetails.getTriggerName();
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is:" + triggerName);
		if(triggerName!=null && !triggerName.equals("")){
			String triggerGroup = properties.getProperty("TRIGGER_GROUP");
			boolean unscheduleStatus = scheduler.unscheduleJob(triggerName, triggerGroup);
			logger.info(Logger.EVENT_UNSPECIFIED,"unscheduleStatus is "+unscheduleStatus);
			status = unscheduleStatus;
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"Trigger name is not found for the given transaction id");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving unSchedule method");
		return status;
	}

	@Override
	public Boolean scheduleVendAcknowledgeSAPJob(int retryCount,
			String transactionID, Long holdingPeriod, String pan, String msn,
			String vendCode, String creditValue, Date timestamp,
			Integer status, Boolean isReschedule) {
		Boolean returnStatus = Boolean.FALSE;
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleVendAcknowledgeSAPJob method");
		try {
			/* Retrieve the scheduler object from the jndi context */
			properties = new VMSUtils().loadProperties(); 
			final Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));		
			int triggerStatus;		
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not found or not started");
			}else{
				Properties properties = new VMSUtils().loadProperties();
				String triggerName = properties.getProperty("VEND_ACK_SAP_TRIGGER")+"_"+transactionID+"_"+retryCount;
				triggerStatus = scheduleAckSAPJob(retryCount,transactionID,holdingPeriod,pan,msn,vendCode,
							 scheduler,triggerName,creditValue,timestamp,status,isReschedule);				
			    logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is : "+triggerName +"trigger Status is:"+ triggerStatus);
			    returnStatus = Boolean.TRUE;
			}
		}catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DBException is "+e.getMessage());
		}catch (NamingException e) {
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+e.getMessage());
		}catch (SchedulerException e) {
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+e.getMessage());
		}	
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the scheduleVendAcknowledgeISUJob method");
		return returnStatus;
	}
	/**
	 * Method to schedule the trigger for vend acknowledgement to ISU job
	 * @param int retryCount
	 *
	 * @throws SchedulerException
	 * @throws DBException
	 */
	private int scheduleAckSAPJob(int retryCount,String transactionID,Long holdingPeriod,String pan,
			String msn,String vendCode,Scheduler scheduler, String triggerName,
			String creditValue,Date timestamp, Integer status, Boolean isReschedule)
			throws SchedulerException,DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleAckSAPJob method");
		String triggerGroup = properties.getProperty("TRIGGER_GROUP_ACK_SAP");
		SimpleTrigger trigger = createAckSAPTrigger(transactionID,holdingPeriod,pan,msn,vendCode,
				 triggerName,creditValue,timestamp, retryCount,status);
		scheduler.scheduleJob(trigger);
		int triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger details for ACK SAP "+triggerName+ ","+triggerGroup+","+triggerStatus);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving scheduleAckSAPJob method");
		return triggerStatus;
	}

	/**
	 * Method to create the trigger for the vend ack ISU job
	 * @param String transactionID
	 * @param Long holdingPeriod
	 * @param String pan
	 * @param String vendCode
	 * @param String paymentType
	 * @param String triggerName
	 * @param String creditValue
	 * @param Date time stamp
	 * @param Integer retry count
	 * @return SimpleTrigger
	 */
	private SimpleTrigger createAckSAPTrigger(String transactionID,Long holdingPeriod,String pan,String msn,
			String vendCode,String triggerName,
			String creditValue,Date timestamp, Integer retryCount, Integer status) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createAckSAPTrigger method ");
		VMSUtils vmsUtils = new VMSUtils();
		Date scheduleTime = vmsUtils.getScheduleTime(holdingPeriod);
		String triggerGroup = properties.getProperty("VEND_ACK_SAP_TRIGGER_GROUP");
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Group is "+ triggerGroup);
		SimpleTrigger trigger = new SimpleTrigger(triggerName, triggerGroup, scheduleTime);
		createJobDataMapForAckSAP(transactionID,pan,msn,vendCode,trigger,creditValue,timestamp, retryCount, status);
		String jobName = properties.getProperty("VEND_ACK_SAP_JOB_NAME");
		String jobGroup = properties.getProperty("VEND_ACK_SAP_JOB_GROUP");
		logger.info(Logger.EVENT_UNSPECIFIED,"Vend ACK SAP Job Details "+ new Object[]{jobName,jobGroup});
		trigger.setJobName(jobName);
		trigger.setJobGroup(jobGroup);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createAckSAPTrigger method ");
		return trigger;
	}
	/**
	   * Method to create the job data map object
	   * @param String transactionID
	   * @param String pan
	   * @param String vendCode
	   * @param String paymentType
	   * @param SimpleTrigger
	   * @param String creditValue
	   * @param Date time stamp
	   * @param Integer retry count
	   * @return 
	   */
		private void createJobDataMapForAckSAP(String transactionID,String pan, String msn, String vendCode,SimpleTrigger trigger,String creditValue,Date timestamp,Integer retryCount, Integer status) {
			logger.debug(Logger.EVENT_SUCCESS,"Entering createJobDataMap method ");
			JobDataMap  jobDataMap = new JobDataMap();
			jobDataMap.put(properties.getProperty("JOB_TRANSACTIONID"),transactionID);
			jobDataMap.put(properties.getProperty("JOB_PAN"), pan);
			jobDataMap.put(properties.getProperty("JOB_VENDCODE"), vendCode);
			jobDataMap.put(properties.getProperty("JOB_CREDITVALUE"), creditValue);
			jobDataMap.put(properties.getProperty("JOB_TIMESTAMP"), timestamp);
			jobDataMap.put(properties.getProperty("JOB_RETRY_COUNT"), retryCount);
			jobDataMap.put(properties.getProperty("JOB_STATUS"), status);
			jobDataMap.put(properties.getProperty("JOB_MSN"), msn);
			jobDataMap.put(properties.getProperty("JOB_DEVICE_TYPE"), 
					DeviceTypeEnum.VEND_ACK.getDeviceType());
			trigger.setJobDataMap(jobDataMap);
			logger.debug(Logger.EVENT_SUCCESS,"Leaving createJobDataMap method ");
		}
	
	
	/*
     * (non-Javadoc)
     * @see com.centrica.vms.scheduler.service.VMSSchedulerService#scheduleSAPPIUtility(java.lang.String, java.lang.String)
     
	public Integer scheduleSAPPIUtility(String count,String userName){
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleSAPPIUtility method");
		properties = new VMSUtils().loadProperties();
		int triggerStatus = -1;
		try{ 
			 Retrieve the scheduler object from the jndi context -- Start 
			final Scheduler scheduler = helper.getScheduler(properties.getProperty("SCHEDULER_NAME"));
			 Retrieve the scheduler object from the jndi context -- end 
			if(scheduler==null || !scheduler.isStarted()){
				logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler is not found or not started");
			}else{
				String triggerName = properties.getProperty("PITRIGGER_NAME");
				String triggerGroup = properties.getProperty("PITRIGGER_GROUP");
				triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
				if(triggerStatus==-1){
					createSAPPISimpleTrigger(count, userName, scheduler, triggerName,triggerGroup);
					triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
					
				}else{
					logger.info(Logger.EVENT_UNSPECIFIED,"Scheduled job is still running trigger Status is: " +triggerStatus);
					triggerStatus = 1;
				}

		        logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is : "+triggerName +"trigger Status is:"+ triggerStatus);
			}
		}
		catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		}catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving scheduleSAPPIUtility method");
		return triggerStatus;
	}*/
	
	/**
	 * Method to create the SAP PI simple trigger
	 * @param count
	 * @param userName
	 * @param scheduler
	 * @param triggerName
	 * @param triggerGroup
	 * @throws SchedulerException
	 *//*
	private void createSAPPISimpleTrigger(String count, String userName,
			Scheduler scheduler, String triggerName, String triggerGroup)throws SchedulerException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering createSAPPISimpleTrigger method");
		SimpleTrigger trigger = new SimpleTrigger(triggerName, triggerGroup,new Date());
		createJobDataMap(count, userName,trigger);
		String jobName = properties.getProperty("PIJOB_NAME");
		String jobGroup = properties.getProperty("PIJOB_GROUP");
		trigger.setJobName(jobName);
		trigger.setJobGroup(jobGroup);
		scheduler.scheduleJob(trigger);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving createSAPPISimpleTrigger method");
	}*/

}
