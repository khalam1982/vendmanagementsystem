/**
 * HEUtilityhase2bJob.java
 * Purpose: Head end utility job for Phase2b meters
 * @author ramasap1
 */
package com.centrica.vms.scheduler.job;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.xml.soap.SOAPException;

import org.apache.axis2.databinding.ADBException;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.HESLookUpFailedException;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.UtilityJobDetails;
import com.centrica.vms.scheduler.service.HEMessagePhase2BService;
import com.centrica.vms.scheduler.service.IHEMessageService;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;
/**
 * Methods for Head end utility job
 * @see VMSJob
 */
public class HEUtilityPhase2bJob extends VMSJob {

	private Logger logger = ESAPI.getLogger(this.getClass());
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.job.VMSJob#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering execute method");
		VMSUtils util = new VMSUtils();
		Properties properies = util.loadProperties();
		UtilityJobDetails utilityJobDetails = prepareHEUtilityJobDetails(
				jobExecutionContext, properies);
		try  {
			VendServiceDetails vendServiceDetails = (VendServiceDetails) util.getVendServiceDetails(
				DeviceTypeConstants.DEVICE_TYPE_PHASE_2B);
			IHEMessageService vmsUtilityService = new HEMessagePhase2BService(new URL(vendServiceDetails.getUrl()));
			vmsUtilityService.processUnsentMessages(utilityJobDetails);
			vmsUtilityService = null;
		} catch (DBException dbx) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception " + dbx.getMessage());
		} catch (NamingException nx) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception " + nx.getMessage());
		} catch (MalformedURLException mex) {
			logger.error(Logger.EVENT_FAILURE,"Malformed URL Exception " + mex.getMessage());
		} catch (ADBException e) {
			logger.error(Logger.EVENT_FAILURE,"ADB Exception " + e.getMessage());
		} catch (RemoteException e) {
			logger.error(Logger.EVENT_FAILURE,"Remote Exception " + e.getMessage());
		} catch (LoginFault e) {
			logger.error(Logger.EVENT_FAILURE,"LoginFault Exception " + e.getMessage());
		} catch (AccessDeniedFault e) {
			logger.error(Logger.EVENT_FAILURE,"AccessDeniedFault Exception " + e.getMessage());
		} catch (UnexpectedErrorFault e) {
			logger.error(Logger.EVENT_FAILURE,"UnexpectedErrorFault Exception " + e.getMessage());
		} catch (SOAPException e) {
			logger.error(Logger.EVENT_FAILURE,"SOAP Exception " + e.getMessage());
		} catch (IOException e) {
			logger.error(Logger.EVENT_FAILURE,"IO Exception " + e.getMessage());
		} catch (HESLookUpFailedException e) {
			logger.error(Logger.EVENT_FAILURE,"HESLookUpFailedException " + e.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving execute method");
	}
	
	/**
	 * Method to prepare the HE utility Job details
	 * @param JobExecutionContext
	 * @param Properties
	 * @return UtilityJobDetails
	 */
	private UtilityJobDetails prepareHEUtilityJobDetails(
			JobExecutionContext jobExecutionContext, Properties properies) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareHEUtilityJobDetails method");
		UtilityJobDetails utilityJobDetails = new UtilityJobDetails();
		JobDataMap jobDataMap = jobExecutionContext.getTrigger().getJobDataMap();
		String count = jobDataMap.getString(properies.getProperty("COUNT"));
		String userName = jobDataMap.getString(properies.getProperty("USERNAME"));
		utilityJobDetails.setCount(count);
		utilityJobDetails.setUserName(userName);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareHEUtilityJobDetails method");
		return utilityJobDetails;
	}

}
