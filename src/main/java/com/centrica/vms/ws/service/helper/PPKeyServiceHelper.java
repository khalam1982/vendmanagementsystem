package com.centrica.vms.ws.service.helper;

import java.util.Date;
import java.util.Properties;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.JobExecutionContext;

import com.centrica.vms.common.Constants;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.scheduler.service.PPKeySchedulerServiceImpl;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.MeterDetails;

/**
 * PPKeyServiceHelper
 * 
 * PPkey Service Helper Class
 * 
 * @author chackram
 */
public class PPKeyServiceHelper {

	private final Logger logger = ESAPI.getLogger(PPKeyServiceHelper.class);

	private final PPKeySchedulerServiceImpl ppkSchedulerService;
	private final WSTransactionDAO transDao;
	private final VMSUtils vmsUtils;

	/**
	 * Constructor
	 */
	public PPKeyServiceHelper() {
		ppkSchedulerService = new PPKeySchedulerServiceImpl();
		transDao = new WSTransactionDAO();
		vmsUtils = new VMSUtils();
	}

	/**
	 * Constructor
	 */
	public PPKeyServiceHelper(final PPKeySchedulerServiceImpl ppkSchedulerService, final WSTransactionDAO transDao, 
			final VMSUtils vmsUtils) {
		this.ppkSchedulerService = ppkSchedulerService;
		this.transDao = transDao;
		this.vmsUtils = vmsUtils;
	}

	/**
	 * Updates Meter Details. 
	 * Updates PP Key, if it is successfully updated in Meter
	 * 
	 * @param transStatus - Status
	 * @param transaction - PPKeyTransaction
	 * @throws DBException
	 * @throws MSNNotFoundException
	 */
	public void updateMeterDetails(final Status transStatus, final PPKeyTransaction transaction) throws DBException, MSNNotFoundException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceHelper:updateMeterDetails method");
		if( transStatus != null ) {
			final MeterDetails meterDetails = transDao.getMeterDetails(transaction.getMsn());
			/** Update PP key if it is updated in HeadEnd **/
			if( Status.SC_100 == transStatus ) {
				meterDetails.setPrepayKey(transaction.getPpKey());
			}
			meterDetails.setVendTxnStatus(0);
			transDao.update(meterDetails);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceHelper:updateMeterDetails method");

	}

	/**
	 * Schedules Job to send Acknowledgement to SAP
	 * 
	 * @param transaction - PPKeyTransaction
	 * @param headEndstatus - Status
	 * @throws DBException
	 */
	public void sendAcknowledgementToSAP(final PPKeyTransaction transaction, final Status headEndstatus) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceHelper:sendAcknowledgementToSAP method");
		/** TODO Is this is needed, if VMS Status is 600. Because already an acknowledgement would have sent to SAP when cancelling the transaction **/
		final int sapStatus = Status.SC_100 == headEndstatus ? Status.SC_200.getStatus() : Status.SC_300.getStatus();
		final long ackTime = Long.parseLong(vmsUtils.loadProperties().getProperty(Constants.PPK_SAP_ACK_SCHEDULE_TIME));
		final boolean scheduleStatus = ppkSchedulerService.scheduleACKJobtoSAP(0, transaction.getTransactionId(), ackTime, 
				transaction.getMsn(), transaction.getMpxn(), sapStatus, new Date(), false);
		logger.info(Logger.EVENT_UNSPECIFIED,"schedule status returns : " + scheduleStatus);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceHelper:sendAcknowledgementToSAP method");

	}

	/**
	 * Prepares Job Details from Job Execution Context
	 * 
	 * @param jobExecutionContext - JobExecutionContext
	 */
	public void prepareJobDetails(final JobExecutionContext jobExecutionContext, final JobDetails jobDetails) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyServiceHelper:prepareJobDetails method");
		final Properties properies = vmsUtils.loadProperties();
		jobDetails.setTransactionID((String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty(Constants.JOB_TRANSACTIONID)));
		jobDetails.setMsn((String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty(Constants.JOB_MSN)));
		jobDetails.setMpxn((String)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty(Constants.JOB_MPXN)));
		jobDetails.setTimestamp((Date)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty(Constants.JOB_TIMESTAMP)));
		jobDetails.setRetryCount((Integer)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty(Constants.JOB_RETRY_COUNT)));
		jobDetails.setStatus((Integer)jobExecutionContext.getTrigger().getJobDataMap().get(properies.getProperty(Constants.JOB_STATUS)));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyServiceHelper:prepareJobDetails method");

	}

}
