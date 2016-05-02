/**
 * 
 */
package com.centrica.vms.scheduler.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.naming.NamingException;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.MSNNotFoundException;
import com.centrica.vms.model.VendTxnStatus;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.external.delegate.HeadendDelegate;
import com.centrica.vms.scheduler.external.model.AckVendServiceDetails;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.VendRetryDetails;
import com.centrica.vms.ws.model.VendTxnWSDetails;

/**
 * @author nagarajk
 *
 */
public class HandleAckJob implements Job {
	
	private Logger logger = ESAPI.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException { 
		logger.debug(Logger.EVENT_SUCCESS,"Entering execute method");
		ArrayList<VendTxnWSDetails> vendTxnWSList;
		try {
			vendTxnWSList = getVendTransationDetails();
			Iterator <VendTxnWSDetails> itr = vendTxnWSList.iterator();
			VendTxnWSDetails vendTxnWSDetails = null;
			JobDetails jobDetails = null;
			Integer status = null;
			VendTxnStatus vendTxnStatus = null;
			Date timestamp = null;
			long ackMaxDelay = 0;
			ackMaxDelay = getAckMaxDelay();
			if (itr != null) {
				while(itr.hasNext()) {
					vendTxnWSDetails = (VendTxnWSDetails) itr.next();
					vendTxnStatus = vendTxnWSDetails.getTxnStatusDetails().iterator().next();
					status = vendTxnStatus.getStatus();
					timestamp = vendTxnStatus.getUpdatedTimeStamp();
					long msec = new Date().getTime() - timestamp.getTime();
					if (status == Status.SC_200.getStatus() && msec > ackMaxDelay) { // 24*60*60*1000
						// send vend code to head end again
						MeterDetails meterDetails = new WSTransactionDAO().getMeterDetails(vendTxnWSDetails.getMsn());
						int deviceType = meterDetails.getDeviceTypeID();
						jobDetails = prepareJobDetails(vendTxnWSDetails, deviceType);
						sendVendCodeToHeadend(jobDetails);	
					}
					vendTxnWSDetails = null;
					jobDetails = null;
					vendTxnStatus = null;
				}
			}
		} catch (Exception e) {
			throw new JobExecutionException();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving execute method");
	}

	/**
	 * @param vendTxnWSDetails
	 * @return
	 * @throws DBException 
	 * @throws MSNNotFoundException 
	 */
	private JobDetails prepareJobDetails(VendTxnWSDetails vendTxnWSDetails, int deviceType) throws MSNNotFoundException, DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareJobDetails method");
		JobDetails jobDetails = new JobDetails();
		jobDetails.setTransactionID(vendTxnWSDetails.getTransactionID());
		jobDetails.setPan(vendTxnWSDetails.getPan());
		jobDetails.setVendCode(vendTxnWSDetails.getVendCode());
		jobDetails.setPaymentType(vendTxnWSDetails.getTransactionType());
		jobDetails.setCreditValue(vendTxnWSDetails.getCreditValue());
		jobDetails.setTimestamp(vendTxnWSDetails.getVendcodeTimeStamp());
		jobDetails.setRetryCount(0);
		jobDetails.setDeviceType(deviceType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareJobDetails method");
		return jobDetails;
	}

	/**
	 * @param jobDetails
	 * @throws DBException 
	 */
	private void sendVendCodeToHeadend(JobDetails jobDetails) throws DBException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendCodeToHeadend method");
		HeadendDelegate headEndDelegate = new HeadendDelegate();
		int status = headEndDelegate.sendVendCode(jobDetails);
		if (status == 200) {
			WSTransactionDAO wsTransactionDAO = new WSTransactionDAO();
			VendTxnWSDetails vendTxnWSDetails = wsTransactionDAO.getVendTxnWSDetails(jobDetails.getTransactionID());
			String triggerName = vendTxnWSDetails.getTriggerName();
			if (isNewRetryProcess(jobDetails.getTransactionID(), wsTransactionDAO)) {
				char c = triggerName.charAt(triggerName.length()-3);
				String newRetryCount = "_" + triggerName.charAt(triggerName.length()-1);
				int retryCount = Character.getNumericValue(c);
				retryCount++;
				triggerName = triggerName.substring(0, triggerName.length() - 3) + retryCount + newRetryCount; //Modified for vend retry process
			} else {
				char c = triggerName.charAt(triggerName.length()-1);
				int retryCount = Character.getNumericValue(c);
				retryCount++;
				triggerName = triggerName.substring(0, triggerName.length() - 1) + retryCount; //Modified for vend retry process
			}
			vendTxnWSDetails.setTriggerName(triggerName);
			Set <VendTxnStatus> vendTxnStatus = vendTxnWSDetails.getTxnStatusDetails();
			Iterator <VendTxnStatus> itr = vendTxnStatus.iterator();
			if (itr != null) {
				VendTxnStatus vendStatus = (VendTxnStatus) itr.next();
				if (vendStatus.getStatus() == Status.SC_200.getStatus()) {
					vendStatus.setUpdatedTimeStamp(new Date());
					vendTxnWSDetails.setTxnStatusDetails(vendTxnStatus);
					wsTransactionDAO.update(vendTxnWSDetails);
				}
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendCodeToHeadend method");
	}

	private boolean isNewRetryProcess(String transactionID, WSTransactionDAO dao){
		logger.debug(Logger.EVENT_SUCCESS,"Entering the isNewRetryProcess method");
		VendRetryDetails vendRetryCount = null;
		boolean returnVal = false;
		try {
			vendRetryCount = dao.getVendRetryCount(transactionID);
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"Error when trying to get the retry count " + e.getMessage());
		}
		if (vendRetryCount != null && vendRetryCount.getRetryCount() != null) {
			returnVal =  true;
		} 		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving the isNewRetryProcess method");
		return returnVal;
	}


	/**
	 * @return
	 * @throws NamingException 
	 */
	private ArrayList <VendTxnWSDetails> getVendTransationDetails() throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendTransationDetails method");
		VMSTransactionDAO vmsTransactionDAO = new VMSTransactionDAO();
		int min = getAckMinWaitPeriod();
		int max = getAckMaxWaitPeriod();
		ArrayList <VendTxnWSDetails> vendTxnWSDetails = vmsTransactionDAO.getVendTransactionDetails(min, max);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendTransationDetails method");
		return vendTxnWSDetails;
	}
	
	/**
	 * @return
	 * @throws NamingException
	 */
	private long getAckMaxDelay() throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAckMaxDelay method");
		long ackMaxDelay = 0;
		AckVendServiceDetails ackVendServiceDetails = null;
		ackVendServiceDetails = (AckVendServiceDetails) new VMSUtils().
			getVendServiceDetails("AckVendDelivery");	
		ackMaxDelay = ackVendServiceDetails.getAckMaxDelay() * 60 * 1000;
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAckMaxDelay method");
		return ackMaxDelay;
	}

	
	/**
	 * @return
	 * @throws NamingException
	 */
	private int getAckMinWaitPeriod() throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAckMinWaitPeriod method");
		int ackMinWaitPeriod = 0;
		AckVendServiceDetails ackVendServiceDetails = null;
		ackVendServiceDetails = (AckVendServiceDetails) new VMSUtils().
			getVendServiceDetails("AckVendDelivery");	
		ackMinWaitPeriod = ackVendServiceDetails.getAckMinWaitPeriod();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAckMinWaitPeriod method");
		return ackMinWaitPeriod;
	}
	
	/**
	 * @return
	 * @throws NamingException
	 */
	private int getAckMaxWaitPeriod() throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAckMaxWaitPeriod method");
		int ackMaxWaitPeriod = 0;
		AckVendServiceDetails ackVendServiceDetails = null;
		ackVendServiceDetails = (AckVendServiceDetails) new VMSUtils().
			getVendServiceDetails("AckVendDelivery");	
		ackMaxWaitPeriod = ackVendServiceDetails.getAckMaxWaitPeriod();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAckMaxWaitPeriod method");
		return ackMaxWaitPeriod;
	}
}
