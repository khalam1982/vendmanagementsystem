package com.centrica.vms.scheduler.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.naming.NamingException;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.JobDataMap;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

import com.centrica.vms.common.Constants;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.PPKeySchedulerDAO;
import com.centrica.vms.scheduler.model.PPKeyTxnScheduler;
import com.centrica.vms.ws.service.mapper.PPKeyUpdateMapper;

/**
 * PPKeySchedulerServiceImpl
 * 
 * Class to schedule PP Key Update Jobs
 * 
 * @author chackram
 */
public class PPKeySchedulerServiceImpl {

	private final Logger logger = ESAPI.getLogger(PPKeySchedulerServiceImpl.class);

	private	final PPKeySchedulerDAO schedulerDao;
	private final VMSScheduleServiceHelper helper;
	private final PPKeyUpdateMapper mapper;
	private final VMSUtils vmsUtils;

	/**
	 * Constructor
	 */
	public PPKeySchedulerServiceImpl() {
		schedulerDao = new PPKeySchedulerDAO();
		helper = new VMSScheduleServiceHelper();
		mapper = new PPKeyUpdateMapper();
		vmsUtils = new VMSUtils();
	}

	/**
	 * Schedules PP Key Update Trigger to send request to HeadEnd
	 * 
	 * @param retryCount of type Integer
	 * @param transactionId of type String
	 * @param holdingPeriod of type Long
	 * @param msn of type String
	 * @param ppKey of type String
	 * @param timestamp - Date
	 * @param isReschedule of type boolean
	 * @return boolean
	 * @throws DBException
	 */
	public boolean schedulePPKeyUpdatetoHeadEnd(final int retryCount, final String transactionId, final Long holdingPeriod, final String msn, 
			final String ppKey, final Date timestamp, final boolean isReschedule) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:schedulePPKeyUpdatetoHeadEnd method");
		final Properties properties = vmsUtils.loadProperties();
		boolean returnStatus = Boolean.FALSE;
		try { 
			final Scheduler scheduler = helper.getScheduler(properties.getProperty(Constants.SCHEDULER_NAME));
			if( scheduler == null || !scheduler.isStarted() ) {
				logger.error(Logger.EVENT_FAILURE,"Scheduler is not found or not started");
			} else {
				final String triggerName = properties.getProperty(Constants.PPK_TRIGGER_PREFIX) + Constants.UNDER_SCORE + transactionId + Constants.UNDER_SCORE + retryCount;
				scheduleHEJob(retryCount, transactionId, holdingPeriod, msn, scheduler, triggerName, ppKey, timestamp, isReschedule);
				returnStatus = Boolean.TRUE;
			}
		} catch (SchedulerException ex) {
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		} catch (NamingException ex) {
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:schedulePPKeyUpdatetoHeadEnd method");
		return returnStatus;

	}

	/**
	 * Schedules PP Key Watch Job to monitor PP Key Update
	 * 
	 * @param transactionId of type String
	 * @param holdingPeriod of type boolean
	 * @param msn of type String
	 * @param timestamp - Date
	 * @param isReschedule of type boolean
	 * @return boolean
	 * @throws DBException
	 */
	public boolean schedulePPKeyWatchJob(final String transactionId, final Long holdingPeriod, final String msn, 
			final Date timestamp, final boolean isReschedule) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:schedulePPKeyWatchJob method");
		final Properties properties = vmsUtils.loadProperties();
		boolean returnStatus = Boolean.FALSE;
		try { 
			final Scheduler scheduler = helper.getScheduler(properties.getProperty(Constants.SCHEDULER_NAME));
			if( scheduler == null || !scheduler.isStarted() ) {
				logger.error(Logger.EVENT_FAILURE,"Scheduler is not found or not started");
			} else {
				final String triggerName = properties.getProperty(Constants.PPKW_TRIGGER_PREFIX) + Constants.UNDER_SCORE + transactionId;
				scheduleWatchJob(transactionId, holdingPeriod, msn, scheduler, triggerName, timestamp, isReschedule);
				returnStatus = Boolean.TRUE;
			}
		} catch (SchedulerException ex) {
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		} catch (NamingException ex) {
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:schedulePPKeyWatchJob method");
		return returnStatus;

	}

	/**
	 * Schedules SAP ACK Job
	 * 
	 * @param retryCount of type Integer
	 * @param transactionId of type String
	 * @param holdingPeriod of type Long
	 * @param msn of type String
	 * @param mpxn of type String
	 * @param status of type Integer
	 * @param timestamp - Date
	 * @param isReschedule of type boolean
	 * @return boolean
	 * @throws DBException
	 */
	public boolean scheduleACKJobtoSAP(final int retryCount, final String transactionId, final Long holdingPeriod, final String msn, 
			final String mpxn, final Integer status, final Date timestamp, final boolean isReschedule) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:scheduleACKJobtoSAP method");
		final Properties properties = vmsUtils.loadProperties();
		boolean returnStatus = Boolean.FALSE;
		try { 
			final Scheduler scheduler = helper.getScheduler(properties.getProperty(Constants.SCHEDULER_NAME));
			if( scheduler == null || !scheduler.isStarted() ) {
				logger.error(Logger.EVENT_FAILURE,"Scheduler is not found or not started");
			} else {
				final String triggerName = properties.getProperty(Constants.PPK_SAP_ACK_TRIGGER_PREFIX) + Constants.UNDER_SCORE + transactionId + Constants.UNDER_SCORE + retryCount;
				scheduleSAPACKJob(retryCount, transactionId, holdingPeriod, msn, mpxn, scheduler, triggerName, timestamp, status, isReschedule);
				returnStatus = Boolean.TRUE;
			}
		} catch (SchedulerException ex) {
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is "+ex.getMessage());
		} catch (NamingException ex) {
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:scheduleACKJobtoSAP method");
		return returnStatus;

	}

	/**
	 * Un schedules PP Key Update Job
	 * 
	 * @param transactionId of type String
	 * @return boolean
	 * @throws DBException
	 */
	public boolean unschedulePPKeyUpdatetoHeadEnd(final String transactionId) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:unschedulePPKeyUpdatetoHeadEnd method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is " + transactionId);
		final Properties properties = new VMSUtils().loadProperties();
		Boolean status = Boolean.FALSE;
		try{
			Scheduler scheduler = helper.getScheduler(properties.getProperty(Constants.SCHEDULER_NAME));
			if( scheduler == null || !scheduler.isStarted() ) {
				logger.error(Logger.EVENT_FAILURE,"Scheduler is not found or not started");
			} else {
				final PPKeyTxnScheduler ppKeyTxnSdlr = schedulerDao.getPPKeyTxnSchedulerDetails(transactionId);
				status = unSchedule(scheduler, ppKeyTxnSdlr.getTriggerName(), properties.getProperty(Constants.PPK_TRIGGER_GROUP));
			}
		} catch (SchedulerException ex) {
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is thrown" + ex.getMessage());
		} catch (NamingException ex) {
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"unschedule job response status is "+ status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:unschedulePPKeyUpdatetoHeadEnd method");
		return status;

	}

	/**
	 * Un schedules PP Key Watch Job
	 * 
	 * @param transactionId of type String
	 * @return boolean
	 * @throws DBException
	 */
	public boolean unschedulePPKeyWatchJob(final String transactionId) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:unschedulePPKeyWatchJob method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Transaction id is " + transactionId);
		final Properties properties = new VMSUtils().loadProperties();
		Boolean status = Boolean.FALSE;
		try{
			Scheduler scheduler = helper.getScheduler(properties.getProperty(Constants.SCHEDULER_NAME));
			if( scheduler == null || !scheduler.isStarted() ) {
				logger.error(Logger.EVENT_FAILURE,"Scheduler is not found or not started");
			} else {
				final String triggerName = properties.getProperty(Constants.PPKW_TRIGGER_PREFIX) + Constants.UNDER_SCORE + transactionId;
				status = unSchedule(scheduler, triggerName, properties.getProperty(Constants.PPKW_TRIGGER_GROUP));
			}
		} catch (SchedulerException ex) {
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is thrown" + ex.getMessage());
		} catch (NamingException ex) {
			logger.error(Logger.EVENT_FAILURE,"NamingException is "+ex.getMessage());
		}
		logger.info(Logger.EVENT_UNSPECIFIED,"unschedule job response status is "+ status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:unschedulePPKeyWatchJob method");
		return status;

	}

	/**
	 * Un schedules job from Quartz Scheduler
	 * 
	 * @param scheduler - Scheduler
	 * @param triggerName of type String
	 * @param triggerGrp of type String
	 * @return boolean
	 * @throws SchedulerException
	 */
	private boolean unSchedule(final Scheduler scheduler, final String triggerName, final String triggerGrp) throws SchedulerException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:unSchedule method");
		Boolean status = Boolean.FALSE;
		logger.info(Logger.EVENT_UNSPECIFIED,"Trigger Name is:" + triggerName);
		if( triggerName != null && !triggerName.isEmpty() ) {
			status = scheduler.unscheduleJob(triggerName, triggerGrp);
			logger.info(Logger.EVENT_UNSPECIFIED,"unscheduleStatus is " + status);
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Trigger name is not found for the given transaction id");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving unSchedule method");
		return status;

	}

	/**
	 * Schedules
	 * @param retryCount of type Integer
	 * @param transactionId of type String
	 * @param holdingPeriod of type Long
	 * @param msn of type String
	 * @param scheduler - Scheduler
	 * @param triggerName of type String
	 * @param ppKey of type String
	 * @param timestamp - Date
	 * @param isReschedule of type boolean
	 * @return Integer
	 * @throws SchedulerException
	 * @throws DBException
	 */
	private int scheduleHEJob(final Integer retryCount, final String transactionId, final Long holdingPeriod, final String msn,
			final Scheduler scheduler, final String triggerName, final String ppKey, final Date timestamp, final boolean isReschedule)
	throws SchedulerException,DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:scheduleHEJob method");
		final Properties properties = vmsUtils.loadProperties();
		final String triggerGroup = properties.getProperty(Constants.PPK_TRIGGER_GROUP);

		final SimpleTrigger trigger = createTrigger(holdingPeriod, triggerName, Constants.PPK_TRIGGER_GROUP, Constants.PPK_UPDATE_JOB_NAME, Constants.PPK_UPDATE_JOB_GROUP);
		createPPKUpdateJobDataMap(transactionId, msn, null, trigger, ppKey, timestamp, retryCount, null);

		final PPKeyTxnScheduler ppKeyTxnSdlr = schedulerDao.getPPKeyTxnSchedulerDetails(transactionId);

		if( retryCount == 0 && !isReschedule ) {
			/** Update only when it is holding pen. **/
			mapper.setPPKTransStatus(ppKeyTxnSdlr, Status.SC_520, triggerName, transactionId);
		} else if( isReschedule ) {
			final Iterator<PPKeyTxnStatus> itr = ppKeyTxnSdlr.getStatusDetails().iterator();
			if( itr != null ) {
				while( itr.hasNext() ) {
					final PPKeyTxnStatus ppKeyTxnStatus = itr.next();
					if( ppKeyTxnStatus.getStatus().equals(Status.SC_520.getStatus()) ) {
						ppKeyTxnStatus.setUpdatedDate(new Date());
						break;
					}
				}
			}
		} else { 
			ppKeyTxnSdlr.setTriggerName(triggerName);
		}
		schedulerDao.update(ppKeyTxnSdlr);

		scheduler.scheduleJob(trigger);
		final int triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:scheduleHEJob method");
		return triggerStatus;

	}

	/**
	 * Schedules Watch Job
	 * 
	 * @param transactionId of type String
	 * @param holdingPeriod of type Long
	 * @param msn of type String
	 * @param scheduler - Scheduler
	 * @param triggerName of type String
	 * @param timestamp - Date
	 * @param isReschedule of type boolean
	 * @return Integer
	 * @throws SchedulerException
	 * @throws DBException
	 */
	private int scheduleWatchJob(final String transactionId, final Long holdingPeriod, final String msn,
			final Scheduler scheduler, final String triggerName, final Date timestamp, final boolean isReschedule) throws SchedulerException,DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:scheduleWatchJob method");
		final Properties properties = vmsUtils.loadProperties();
		final String triggerGroup = properties.getProperty(Constants.PPKW_TRIGGER_GROUP);

		final SimpleTrigger trigger = createTrigger(holdingPeriod, triggerName, Constants.PPKW_TRIGGER_GROUP, Constants.PPK_WATCH_JOB_NAME, Constants.PPK_WATCH_JOB_GROUP);
		createPPKUpdateJobDataMap(transactionId, msn, null, trigger, null, timestamp, 0, null);

		scheduler.scheduleJob(trigger);
		final int triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:scheduleWatchJob method");
		return triggerStatus;

	}

	/**
	 * Schedules SAP ACK Job
	 * 
	 * @param retryCount of type Integer
	 * @param transactionId of type String
	 * @param holdingPeriod of type Long
	 * @param msn of type String
	 * @param mpxn of type String
	 * @param scheduler - Scheduler
	 * @param triggerName of type String
	 * @param timestamp - Date
	 * @param status of type Integer
	 * @param isReschedule of type boolean
	 * @return Integer
	 * @throws SchedulerException
	 * @throws DBException
	 */
	private int scheduleSAPACKJob(final Integer retryCount, final String transactionId, final Long holdingPeriod, final String msn, final String mpxn, 
			final Scheduler scheduler, final String triggerName, final Date timestamp, final Integer status, final boolean isReschedule)
	throws SchedulerException,DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:scheduleSAPACKJob method");
		final Properties properties = vmsUtils.loadProperties();
		final String triggerGroup = properties.getProperty(Constants.PPK_SAP_ACK_TRIGGER_GROUP);
		final SimpleTrigger trigger = createTrigger(holdingPeriod, triggerName, Constants.PPK_SAP_ACK_TRIGGER_GROUP, Constants.PPK_SAP_ACK_JOB_NAME, Constants.PPK_SAP_ACK_JOB_GROUP);
		createPPKUpdateJobDataMap(transactionId, msn, mpxn, trigger, null, timestamp, retryCount, status);
		scheduler.scheduleJob(trigger);
		final int triggerStatus = scheduler.getTriggerState(triggerName, triggerGroup);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:scheduleSAPACKJob method");
		return triggerStatus;

	}

	/**
	 * Creates Trigger
	 * 
	 * @param holdingPeriod of type Long
	 * @param trgrName of type String
	 * @param trgrGrp of type String
	 * @param jobName of type String
	 * @param jobGrp of type String
	 * @return SimpleTrigger
	 */
	private SimpleTrigger createTrigger(final Long holdingPeriod, final String trgrName, final String trgrGrp, final String jobName, final String jobGrp) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:createTrigger method");
		final Date scheduleTime = vmsUtils.getScheduleTime(holdingPeriod);
		final Properties properties = vmsUtils.loadProperties();
		final String triggerGroup = properties.getProperty(trgrGrp);
		final SimpleTrigger trigger = new SimpleTrigger(trgrName, triggerGroup, scheduleTime);
		trigger.setJobName(properties.getProperty(jobName));
		trigger.setJobGroup(properties.getProperty(jobGrp));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:createTrigger method");
		return trigger;

	}

	/**
	 * Creates PP Key Update Job Data Map
	 * 
	 * @param transactionId of type String
	 * @param msn of type String
	 * @param mpxn of type String
	 * @param trigger of type String
	 * @param ppKey of type String
	 * @param timestamp - Date
	 * @param retryCount of type Integer
	 * @param status of type Integer
	 */
	private void createPPKUpdateJobDataMap(final String transactionId, final String msn, final String mpxn, final SimpleTrigger trigger, 
			final String ppKey, final Date timestamp, final Integer retryCount, final Integer status) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySchedulerServiceImpl:createPPKUpdateJobDataMap method");
		final Properties properties = vmsUtils.loadProperties();
		final JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put(properties.getProperty(Constants.JOB_TRANSACTIONID), transactionId);
		jobDataMap.put(properties.getProperty(Constants.JOB_MSN), msn);
		jobDataMap.put(properties.getProperty(Constants.JOB_TIMESTAMP), timestamp);
		jobDataMap.put(properties.getProperty(Constants.JOB_RETRY_COUNT), retryCount);

		if( mpxn != null ) {
			jobDataMap.put(properties.getProperty(Constants.JOB_MPXN), mpxn);
		}
		if( ppKey != null ) {
			jobDataMap.put(properties.getProperty(Constants.JOB_PPKEY), ppKey);
		}
		if( status != null ) {
			jobDataMap.put(properties.getProperty(Constants.JOB_STATUS), status);
		}
		trigger.setJobDataMap(jobDataMap);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySchedulerServiceImpl:createPPKUpdateJobDataMap method");

	}

}
