package com.centrica.vms.scheduler.job;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.PPKeyTransaction;
import com.centrica.vms.model.PPKeyTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.service.helper.PPKeyServiceHelper;
import com.centrica.vms.ws.service.mapper.PPKeyUpdateMapper;

/**
 * PPKeyWatchJob
 * 
 * Quartz Job class to Monitor and Cancel PP Key Transaction
 * 
 * @author chackram
 */
public class PPKeyWatchJob implements Job {

	private final Logger logger = ESAPI.getLogger(PPKeyWatchJob.class);

	private final Set<Integer> heStatusSet = new HashSet<Integer>();

	private final PPKeyServiceHelper svchelper;
	private final PPKeyUpdateMapper mapper;
	private final WSTransactionDAO transDao;

	/**
	 * Constructor
	 */
	public PPKeyWatchJob() {
		initializeHeadEndStatusSet();
		svchelper = new PPKeyServiceHelper();
		mapper = new PPKeyUpdateMapper();
		transDao = new WSTransactionDAO();
	}

	/**
	 * Executes PPKeyWatchJob
	 * 
	 * @param jobExecutionContext - JobExecutionContext
	 * @throws JobExecutionException
	 */
	@Override
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeyWatchJob:execute method");

		final JobDetails jobDetails = new JobDetails();
		svchelper.prepareJobDetails(jobExecutionContext, jobDetails);

		try {
			final PPKeyTransaction transaction = transDao.getPPKeyTxnDetails(jobDetails.getTransactionID());
			final Iterator<PPKeyTxnStatus> statusItr = transaction.getStatusDetails().iterator();
			final int lastStatus = statusItr != null && statusItr.hasNext() ? statusItr.next().getStatus() : 0;

			logger.info(Logger.EVENT_UNSPECIFIED,"Last Status of PP Key Update Transaction : " + lastStatus);

			/** Check whether acknowledgement is received from HeadEnd **/
			boolean isAckReceived = heStatusSet.contains(lastStatus);

			if( isAckReceived ) {
				logger.info(Logger.EVENT_UNSPECIFIED,"Acknowledgement Recevied from HeadEnd. No need to do anything, returning");
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Acknowledgement ** Not ** Recevied from HeadEnd. Cancelling Transaction");
				/** Cancel PP Key Transaction, add Cancelled Status **/
				mapper.setPPKeyTransactionStatus(Status.SC_600, transaction.getStatusDetails());
				transDao.update(transaction);

				/** Remove Vend Suspension **/
				svchelper.updateMeterDetails(Status.SC_600, transaction);

				/** Send Failure update to SAP **/
				svchelper.sendAcknowledgementToSAP(transaction, Status.SC_600);
			}
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception :" + e.getMessage());
			throw new JobExecutionException();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeyWatchJob:execute method");

	}

	/**
	 * Initialises Head End Statuses in Set
	 */
	private void initializeHeadEndStatusSet() {
		heStatusSet.add(Status.SC_100.getStatus());
		heStatusSet.add(Status.SC_110.getStatus());
		heStatusSet.add(Status.SC_300.getStatus());
		heStatusSet.add(Status.SC_310.getStatus());
		heStatusSet.add(Status.SC_320.getStatus());
		heStatusSet.add(Status.SC_330.getStatus());
		heStatusSet.add(Status.SC_340.getStatus());
		heStatusSet.add(Status.SC_350.getStatus());
		heStatusSet.add(Status.SC_360.getStatus());
		heStatusSet.add(Status.SC_370.getStatus());
		heStatusSet.add(Status.SC_380.getStatus());
		heStatusSet.add(Status.SC_400.getStatus());
		heStatusSet.add(Status.SC_410.getStatus());
	}

}
