/**
 * SchedulerInitializer.java
 * Purpose: Scheduler initialization servlet
 * @author ramasap1
 */
package com.centrica.vms.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.scheduler.external.model.AckVendServiceDetails;
import com.centrica.vms.scheduler.external.model.VendTransactionCacheDetails;
import com.centrica.vms.scheduler.job.AdjustCreditJob;
import com.centrica.vms.scheduler.external.model.SCNActvServiceDetails;
import com.centrica.vms.scheduler.job.HEUtilityPhase2bJob;
import com.centrica.vms.scheduler.job.HEUtilityPhase3Job;
import com.centrica.vms.scheduler.job.HandleAckJob;
import com.centrica.vms.scheduler.job.PPKeySAPAckJob;
import com.centrica.vms.scheduler.job.PPKeyUpdateJob;
import com.centrica.vms.scheduler.job.PPKeyWatchJob;
import com.centrica.vms.scheduler.job.SCNActivationJob;
import com.centrica.vms.scheduler.job.VendAckSAPJob;
import com.centrica.vms.scheduler.job.VendCodeJob;
import com.centrica.vms.scheduler.job.VendTransactionsLoadCacheJob;



/**
 * Methods for Scheduler initialization servlet
 * @see HttpServlet
 */
public class SchedulerInitializer extends HttpServlet{

	private static final long serialVersionUID = 2304627334978609635L;

	private Logger logger = ESAPI.getLogger(getClass().getName());

	private static final String JOB_NAME = "JOB_NAME";
	private static final String JOB_GROUP = "JOB_GROUP";
	private static final String JOB_REPLACE = "JOB_REPLACE";

	private static final String HEPHASE2B_JOB_NAME = "HE_PHASE2B_JOB_NAME";
	private static final String HEPHASE2B_JOB_GROUP = "HE_PHASE2B_JOB_GROUP";

	private static final String HEPHASE3_JOB_NAME = "HE_PHASE3_JOB_NAME";
	private static final String HEPHASE3_JOB_GROUP = "HE_PHASE3_JOB_GROUP";

	private static final String HANDLE_ACK_JOB_NAME = "HANDLE_ACK_JOB_NAME";
	private static final String HANDLE_ACK_JOB_GROUP = "HANDLE_ACK_JOB_GROUP";

	private static final String ADJUST_JOB_NAME = "ADJUST_JOB_NAME";
	private static final String ADJUST_JOB_GROUP = "ADJUST_JOB_GROUP";

	private static final String LOAD_CACHE_JOB_NAME = "LOAD_CACHE_JOB_NAME";
	private static final String LOAD_CACHE_JOB_GROUP = "LOAD_CACHE_JOB_GROUP";

	private static final String ACKTRIGGER_NAME = "ACKTRIGGER_NAME";
	private static final String ACKTRIGGER_GROUP = "ACKTRIGGER_GROUP";

	private static final String CACHE_TRIGGER_NAME = "CACHE_TRIGGER_NAME";
	private static final String CACHE_TRIGGER_GROUP = "CACHE_TRIGGER_GROUP";
	
	private static final String SCN_ACTIVATE_JOB_GROUP = "SCN_ACTIVATE_JOB_GROUP";
	private static final String SCN_ACTIVATE_JOB_NAME = "SCN_ACTIVATE_JOB_NAME";

	private static final String PPK_UPDATE_JOB_GROUP = "PPK_UPDATE_JOB_GROUP";
	private static final String PPK_UPDATE_JOB_NAME = "PPK_UPDATE_JOB_NAME";

	private static final String PPK_WATCH_JOB_GROUP = "PPK_WATCH_JOB_GROUP";
	private static final String PPK_WATCH_JOB_NAME = "PPK_WATCH_JOB_NAME";
	
	private static final String PPK_SAP_ACK_JOB_GROUP = "PPK_SAP_ACK_JOB_GROUP";
	private static final String PPK_SAP_ACK_JOB_NAME = "PPK_SAP_ACK_JOB_NAME";

	private static final String JOB_DURABLE = "JOB_DURABLE";
	private static final String JOB_VOLATILE = "JOB_VOLATILE";

	private static final String CACHING_ENABLED = "CACHING_ENABLED";
	private static final String VEND_ACK_SAP_JOB_GROUP = "VEND_ACK_SAP_JOB_GROUP";
	private static final String VEND_ACK_SAP_JOB_NAME = "VEND_ACK_SAP_JOB_NAME";


	public Scheduler scheduler;
	public Scheduler cacheScheduler;

	private Properties jobProperties = new Properties();
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(final ServletConfig config) throws ServletException {

		super.init(config); 
		logger.debug(Logger.EVENT_SUCCESS,"Entering the init method");
		try {
			startScheduler(config);			 
			startCacheScheduler(config);
		} catch(SchedulerException ex){
			logger.error(Logger.EVENT_FAILURE,"SchedulerException is thrown " + ex.getMessage());
		} catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IOException is thrown" + ex.getMessage());
		} catch(NamingException ex){
			logger.error(Logger.EVENT_FAILURE,"NamingException is thrown" + ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the init method");

	}

	private void startScheduler(final ServletConfig config) throws IOException,	SchedulerException, NamingException {

		Properties properties = new Properties();
		InputStream io = getClass().getResourceAsStream(config.getInitParameter("QUZ_CONF"));
		properties.load(io);
		SchedulerFactory sf = new StdSchedulerFactory(properties);
		scheduler = sf.getScheduler();
		scheduler.start(); 
		logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler name is "+scheduler.getSchedulerName());
		logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler instance id is "+scheduler.getSchedulerInstanceId());
		logger.info(Logger.EVENT_UNSPECIFIED,"status of the scheduler is "+scheduler.isStarted());
		//Properties jobProperties = new Properties();
		jobProperties = new VMSUtils().loadProperties();
		addJobs(jobProperties);
		/* Store the scheduler object in the jndi context -- Start */
		Context context = new InitialContext();
		// context.unbind(jobProperties.getProperty("SCHEDULER_NAME"));
		context.rebind(jobProperties.getProperty("SCHEDULER_NAME"), scheduler);
		context.close();
		/* Store the scheduler object in the jndi context  -- End */
		
		try{
		if(io !=null){
			io.close();
		}
		}catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IOException is thrown" + ex.getMessage());			
			}
		


	}


	private void startCacheScheduler(final ServletConfig config) throws SchedulerException, NamingException, IOException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering the startCacheScheduler method");
		Properties jobProperties = new VMSUtils().loadProperties();
		Boolean cache_enabled = new Boolean(jobProperties.getProperty(CACHING_ENABLED));
		InputStream io = null;
		if (cache_enabled) {
			Properties properties = new Properties();
			io = getClass().getResourceAsStream(config.getInitParameter("QUZ_CACHE_CONF"));
			properties.load(io);
			SchedulerFactory sf = new StdSchedulerFactory(properties);
			cacheScheduler = sf.getScheduler();
			cacheScheduler.start();
			logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler name is " + cacheScheduler.getSchedulerName());
			logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler instance id is "
					+ cacheScheduler.getSchedulerInstanceId());
			logger.info(Logger.EVENT_UNSPECIFIED,"status of the scheduler is " + cacheScheduler.isStarted());
			// Properties jobProperties = new Properties();
			addCacheJob(jobProperties);
			/* Store the scheduler object in the jndi context -- Start */
			Context context = new InitialContext();
			// context.unbind(jobProperties.getProperty("SCHEDULER_NAME"));
			context.rebind(jobProperties.getProperty("CACHE_SCHEDULER_NAME"),
					cacheScheduler);
			context.close();
			/* Store the scheduler object in the jndi context -- End */
		} else {
			logger.info(Logger.EVENT_UNSPECIFIED,"Cache is disabled");
		}
		try{
		if(io != null){
			io.close();
		}
		}catch(IOException ex){
			logger.error(Logger.EVENT_FAILURE,"IOException is thrown" + ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the startCacheScheduler method");

	}

	/**
	 * Method to add the jobs to the scheduler
	 * @param jobProperties
	 * @throws SchedulerException
	 */
	private void addJobs(final Properties jobProperties) throws SchedulerException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering addJobs methods");

		JobDetail jobDetail = new JobDetail(jobProperties.getProperty(JOB_NAME),jobProperties.getProperty(JOB_GROUP),VendCodeJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));

		jobDetail = new JobDetail(jobProperties.getProperty(HEPHASE2B_JOB_NAME),jobProperties.getProperty(HEPHASE2B_JOB_GROUP),HEUtilityPhase2bJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));

		jobDetail = new JobDetail(jobProperties.getProperty(HEPHASE3_JOB_NAME),jobProperties.getProperty(HEPHASE3_JOB_GROUP),HEUtilityPhase3Job.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));

		// Task ID: 246 -- start
		jobDetail = new JobDetail(jobProperties.getProperty(HANDLE_ACK_JOB_NAME),jobProperties.getProperty(HANDLE_ACK_JOB_GROUP),HandleAckJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));

		jobDetail = new JobDetail(jobProperties.getProperty(ADJUST_JOB_NAME),jobProperties.getProperty(ADJUST_JOB_GROUP),AdjustCreditJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));
		
		jobDetail = new JobDetail(jobProperties.getProperty(SCN_ACTIVATE_JOB_NAME),jobProperties.getProperty(SCN_ACTIVATE_JOB_GROUP),SCNActivationJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));
		
		jobDetail = new JobDetail(jobProperties.getProperty(PPK_UPDATE_JOB_NAME),jobProperties.getProperty(PPK_UPDATE_JOB_GROUP), PPKeyUpdateJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));
		
		jobDetail = new JobDetail(jobProperties.getProperty(PPK_WATCH_JOB_NAME),jobProperties.getProperty(PPK_WATCH_JOB_GROUP), PPKeyWatchJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));
		
		jobDetail = new JobDetail(jobProperties.getProperty(PPK_SAP_ACK_JOB_NAME),jobProperties.getProperty(PPK_SAP_ACK_JOB_GROUP), PPKeySAPAckJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));
		// Task ID: 246 -- stop
		// Add job for sending acknowledgement to SAP
		jobDetail = new JobDetail(jobProperties.getProperty(VEND_ACK_SAP_JOB_NAME),jobProperties.getProperty(VEND_ACK_SAP_JOB_GROUP),VendAckSAPJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		scheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE))); 

		try {
			// Task ID: 246 & 247-- start
			createTriggers();
			createSCNActvTriggers();
			// Task ID: 246 & 247 -- stop
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"NamingException::" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"IOException::" + e.getMessage());
		}		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving addJobs methods");

	}

	private void addCacheJob(final Properties jobProperties) throws SchedulerException {

		JobDetail jobDetail = null;
		jobDetail = new JobDetail(jobProperties.getProperty(LOAD_CACHE_JOB_NAME),jobProperties.getProperty(LOAD_CACHE_JOB_GROUP),VendTransactionsLoadCacheJob.class);
		addJobAdditionalDetails(jobProperties, jobDetail);
		cacheScheduler.addJob(jobDetail, new Boolean(jobProperties.getProperty(JOB_REPLACE)));
		try {
			createTriggerForLoadingCache();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"NamingException::" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(Logger.EVENT_FAILURE,"IOException::" + e.getMessage());
		}

	}
	
	/**
	 * Method used to unschedule the job triggers
	 * @throws SchedulerException
	 */
	private void unschHandleAckQuartzJob(final Properties jobProperties) throws SchedulerException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering unschHandleAckQuartzJob method");
		if(scheduler!=null){
			String ackTriggerName  = jobProperties.getProperty(ACKTRIGGER_NAME);
			String ackTriggerGroup = jobProperties.getProperty(ACKTRIGGER_GROUP);
			boolean unschStatus = true;
			try{
				unschStatus = scheduler.unscheduleJob(ackTriggerName, ackTriggerGroup);
			}catch(SchedulerException ex){
				// Task ID: 247-- start
				Trigger trigger = scheduler.getTrigger(ackTriggerName, ackTriggerGroup);
				if(trigger==null){
					logger.info(Logger.EVENT_UNSPECIFIED,"Ack trigger has been already removed : ");
				}else{
					logger.error(Logger.EVENT_FAILURE,"Scheduler exception has been thrown : " + ex.getMessage());
					unschStatus = false;
					throw new SchedulerException("unable to unschedule the jobs " + trigger.getFullName());
				}
				// Task ID: 247-- stop
			}
			logger.info(Logger.EVENT_UNSPECIFIED,"Unschedule status " + unschStatus);
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler object is null so unable to unschedule the jobs ");
			throw new SchedulerException("Scheduler object is null so unable to unschedule the jobs ");
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving unschHandleAckQuartzJob method");
	}
	
	/**
	 * Method used to unschedule the job triggers
	 * @throws SchedulerException
	 */
	private void unschLoadCacheQuartzJob(Properties jobProperties) throws SchedulerException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering unschLoadCacheQuartzJob method");
		if(cacheScheduler!=null){
			String cacheTriggerName  = jobProperties.getProperty(CACHE_TRIGGER_NAME);
			String cacheTriggerGroup = jobProperties.getProperty(CACHE_TRIGGER_GROUP);
			boolean unschStatus = true;
			try{
				unschStatus = cacheScheduler.unscheduleJob(cacheTriggerName, cacheTriggerGroup);
			}catch(SchedulerException ex){
				// Task ID: 247-- start
				Trigger trigger = cacheScheduler.getTrigger(cacheTriggerName, cacheTriggerGroup);
				if(trigger==null){
					logger.info(Logger.EVENT_UNSPECIFIED,"Load cache trigger has been already removed : ");
				}else{
					logger.error(Logger.EVENT_FAILURE,"Load Scheduler exception has been thrown : " + ex.getMessage());
					unschStatus = false;
					throw new SchedulerException("unable to unschedule the jobs " + trigger.getFullName());
				}
				// Task ID: 247-- stop
			}
			logger.info(Logger.EVENT_UNSPECIFIED,"Unschedule Load cache status " + unschStatus);
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"Cache Scheduler object is null so unable to unschedule the jobs ");
			throw new SchedulerException("Cache Scheduler object is null so unable to unschedule the jobs ");
		}

		logger.debug(Logger.EVENT_SUCCESS,"Leaving unschLoadCacheQuartzJob method");

	}

	private void unschHandleSCNAckQuartzJob(Properties jobProperties)throws SchedulerException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering unschHandleSCNAckQuartzJob method");
		if(scheduler!=null){
			String ackTriggerName  = jobProperties.getProperty("SCNACTVTRIGGER_NAME");
			String ackTriggerGroup = jobProperties.getProperty("SCNACTVTRIGGER_GROUP");
			boolean unschStatus = true;
			try{
			     unschStatus = scheduler.unscheduleJob(ackTriggerName, ackTriggerGroup);
			}catch(SchedulerException ex){
				// Task ID: 247-- start
				Trigger trigger = scheduler.getTrigger(ackTriggerName, ackTriggerGroup);
				if(trigger==null){
					logger.info(Logger.EVENT_UNSPECIFIED,"SCN Ack trigger has been already removed : ");
				}else{
					logger.error(Logger.EVENT_FAILURE,"Scheduler exception has been thrown : " + ex.getMessage());
					unschStatus = false;
					throw new SchedulerException("unable to unschedule the jobs " + trigger.getFullName());
				}
				// Task ID: 247-- stop
			}
			logger.info(Logger.EVENT_UNSPECIFIED,"Unschedule status " + unschStatus);
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"Scheduler object is null so unable to unschedule the jobs ");
			throw new SchedulerException("Scheduler object is null so unable to unschedule the jobs ");
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving unschHandleSCNAckQuartzJob method");
	}
	/**
	 * Method used to schedule the morning job scheduler 
	 * @param configName
	 * @param tablePrefix
	 * @param cdJobRunTime
	 * @param mdJobRunTime
	 * @throws SchedulerException
	 * @throws NamingException
	 * @throws IOException
	 */

	/**
	 * Method used to schedule the morning job scheduler 
	 * @param configName
	 * @param tablePrefix
	 * @param cdJobRunTime
	 * @param mdJobRunTime
	 * @throws SchedulerException
	 * @throws NamingException
	 * @throws IOException
	 */
	private void createTriggers() throws SchedulerException, NamingException,IOException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering the startScheduler method");
		CronTrigger ct = null;
		String ackTriggerName  = jobProperties.getProperty(ACKTRIGGER_NAME);
		String ackTriggerGroup = jobProperties.getProperty(ACKTRIGGER_GROUP);
		try {
			AckVendServiceDetails ackVendServiceDetails = (AckVendServiceDetails) new VMSUtils().
			getVendServiceDetails("AckVendDelivery");
			String jobTime = ackVendServiceDetails.getAckJobTime();
			ct=new CronTrigger(ackTriggerName,ackTriggerGroup,jobTime); // Fire at scheduled time
			ct.setJobName(jobProperties.getProperty(HANDLE_ACK_JOB_NAME));
			ct.setJobGroup(jobProperties.getProperty(HANDLE_ACK_JOB_GROUP));
			scheduleCronJob(ct, ackTriggerName, ackTriggerGroup, "HANDLE ACK SERVICE");
		} catch (ParseException e) {
			logger.error(Logger.EVENT_FAILURE,"ParseException::" + e.getMessage());
		}catch(SchedulerException ex){
			// Task ID: 247-- start
			Trigger trigger = scheduler.getTrigger(ackTriggerName, ackTriggerGroup);
			if(trigger==null){
				logger.error(Logger.EVENT_FAILURE,"Scheduler exception has been thrown : " + ex.getMessage());
				logger.info(Logger.EVENT_UNSPECIFIED,"Ack trigger has not been created");
				throw ex;
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Ack trigger has already been created : " + trigger.getFullName());
			}
			// Task ID: 247-- stop
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the createACKTriggers method");

	}
	
	/**
	 * Method used to schedule the SCN activation job scheduler 
	 * @param configName
	 * @param tablePrefix
	 * @param cdJobRunTime
	 * @param mdJobRunTime
	 * @throws SchedulerException
	 * @throws NamingException
	 * @throws IOException
	 */
	private void createSCNActvTriggers() throws SchedulerException, NamingException,IOException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering the createSCNActvTriggers method");
		CronTrigger ct = null;
		String scnActvTriggerName  = jobProperties.getProperty("SCNACTVTRIGGER_NAME");
		String scnActvTriggerGroup = jobProperties.getProperty("SCNACTVTRIGGER_GROUP");
		try {
			SCNActvServiceDetails scnActvServiceDetails = (SCNActvServiceDetails) new VMSUtils().
			getVendServiceDetails("SCNActvResponse");
			String jobTime = scnActvServiceDetails.getScnActvJobTime();
			ct=new CronTrigger(scnActvTriggerName,scnActvTriggerGroup,jobTime); // Fire at scheduled time
			ct.setJobName(jobProperties.getProperty(SCN_ACTIVATE_JOB_NAME));
			ct.setJobGroup(jobProperties.getProperty(SCN_ACTIVATE_JOB_GROUP));
			scheduleCronJob(ct, scnActvTriggerName, scnActvTriggerGroup, "SCN ACTIVATION SERVICE");
		} catch (ParseException e) {
			logger.error(Logger.EVENT_FAILURE,"ParseException::" + e.getMessage());
		}catch(SchedulerException ex){
			Trigger trigger = scheduler.getTrigger(scnActvTriggerName, scnActvTriggerGroup);
			if(trigger==null){
				logger.error(Logger.EVENT_FAILURE,"Scheduler exception has been thrown : " + ex.getMessage());
				logger.info(Logger.EVENT_UNSPECIFIED,"SCN activate trigger has not been created");
				throw ex;
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"SCN activate trigger has already been created : " + trigger.getFullName());
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the createSCNActvTriggers method");
	}


	
	

	private void createTriggerForLoadingCache() throws SchedulerException, NamingException,IOException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering the createTriggerForLoadingCache method");
		CronTrigger ct = null;
		String cacheTriggerName  = jobProperties.getProperty(CACHE_TRIGGER_NAME);
		String cacheTriggerGroup = jobProperties.getProperty(CACHE_TRIGGER_GROUP);
		try {
			VendTransactionCacheDetails vendTransactionCacheDetails = (VendTransactionCacheDetails) new VMSUtils().
			getVendServiceDetails("VendTransactionsCache");
			String jobTime = vendTransactionCacheDetails.getCacheJobTime();
			logger.info(Logger.EVENT_UNSPECIFIED,"createTriggerForLoadingCache job time "+jobTime);
			ct=new CronTrigger(cacheTriggerName,cacheTriggerGroup,jobTime); // Fire at scheduled time
			String jobName = jobProperties.getProperty(LOAD_CACHE_JOB_NAME);
			String jobGroup = jobProperties.getProperty(LOAD_CACHE_JOB_GROUP);
			ct.setJobName(jobName);
			ct.setJobGroup(jobGroup);
			ct.setCronExpression(jobTime);	
			ct.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
			ct.setTimeZone(TimeZone.getTimeZone("Europe/London"));
			cacheScheduler.scheduleJob(ct); 
			//Trigger on start up
			cacheScheduler.triggerJob(jobName,jobGroup);
			// Print next cache load schedule 
			Trigger trigger = cacheScheduler.getTrigger(cacheTriggerName,cacheTriggerGroup);
			if(trigger!=null){
				logger.info(Logger.EVENT_UNSPECIFIED,"load cache trigger - "+trigger);
			}
		} catch (ParseException e) {
			logger.error(Logger.EVENT_FAILURE,"ParseException::" + e.getMessage());
		}catch(SchedulerException ex){
			// Task ID: 247-- start
			Trigger trigger = scheduler.getTrigger(cacheTriggerName,cacheTriggerGroup);
			if(trigger==null){
				logger.error(Logger.EVENT_FAILURE,"Scheduler exception has been thrown : " + ex.getMessage());
				logger.info(Logger.EVENT_UNSPECIFIED,"Cache trigger has not been created");
				throw ex;
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"Cache trigger has already been created : " + trigger.getFullName());
			}
			// Task ID: 247-- stop
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the createTriggerForLoadingCache method");

	}

	
	/**
	 * Method used to schedule the cron job
	 * @param ct
	 * @param triggerName
	 * @param triggerGroup
	 * @param errorMessage
	 * @throws SchedulerException
	 */

	private void scheduleCronJob(CronTrigger ct, String triggerName,
			String triggerGroup, String errorMessage) throws SchedulerException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleCronJob methods");
		try {
			scheduler.scheduleJob(ct);
		} catch (SchedulerException ex) {
			logger.error(Logger.EVENT_FAILURE,"SchedulerException " + ex.getMessage());
			Trigger trigger = scheduler.getTrigger(triggerName, triggerGroup);
			if (trigger == null) {
				logger.error(Logger.EVENT_FAILURE,"Scheduler exception has been thrown : "
						+ ex.getMessage());
				logger.info(Logger.EVENT_UNSPECIFIED,errorMessage + " trigger has not been created");
				throw ex;
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,errorMessage
						+ " trigger has already been created : "
						+ trigger.getFullName());
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving scheduleCronJob methods");
	}
	
	/**
	 * Method to add additional details
	 * @param jobProperties
	 * @param jobDetail
	 */
	private void addJobAdditionalDetails(Properties jobProperties,JobDetail jobDetail) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering addJobAdditionalDetails methods");
		jobDetail.setDurability(new Boolean(jobProperties.getProperty(JOB_DURABLE)));
		jobDetail.setRequestsRecovery(new Boolean(jobProperties.getProperty(JOB_VOLATILE)));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving addJobAdditionalDetails methods");
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		super.destroy();
		logger.debug(Logger.EVENT_SUCCESS,"Entering the destroy method ");
		if(scheduler !=null){
			try{
				unschHandleAckQuartzJob(jobProperties);
				unschHandleSCNAckQuartzJob(jobProperties);
				scheduler.shutdown();
				logger.info(Logger.EVENT_UNSPECIFIED,"scheduler shutdown status is"+scheduler.isShutdown());
				Boolean cache_enabled = new Boolean(jobProperties.getProperty(CACHING_ENABLED));
				if(cache_enabled){
					unschLoadCacheQuartzJob(jobProperties);
				}
				cacheScheduler.shutdown();
			}
			catch(SchedulerException ex){
				logger.error(Logger.EVENT_FAILURE,"SchedulerException is thrown : "+ex.getMessage());
			}
		}else{
			logger.info(Logger.EVENT_UNSPECIFIED,"scheduler object is null");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the destroy method ");
	}

}
