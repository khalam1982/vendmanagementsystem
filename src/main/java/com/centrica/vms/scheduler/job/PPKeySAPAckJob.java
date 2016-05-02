package com.centrica.vms.scheduler.job;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.transport.http.HttpTransportProperties.Authenticator;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import smartmeterprocessing.vend.uk.co.britishgas.SmartMeterProcessingActionPPKeyAcknowledgmentOutServiceStub;
import smartmeterprocessing.vend.uk.co.britishgas.SmartMeterProcessingActionPPKeyAcknowledgmentOutServiceStub.UpdatePPKeyAcknowledgment;
import smartmeterprocessing.vend.uk.co.britishgas.SmartMeterProcessingActionPPKeyAcknowledgmentOutServiceStub.UpdatePPKeyAcknowledgmentMessage;

import com.centrica.vms.common.Constants;
import com.centrica.vms.common.DeviceTypeConstants;
import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.model.VendTransaction.Status;
import com.centrica.vms.scheduler.DAO.PPKeySchedulerDAO;
import com.centrica.vms.scheduler.external.model.VendServiceDetails;
import com.centrica.vms.scheduler.model.JobDetails;
import com.centrica.vms.scheduler.service.PPKeySchedulerServiceImpl;
import com.centrica.vms.ws.service.helper.PPKeyServiceHelper;

/**
 * PPKeySAPAckJob
 * 
 * Quartz Job class to send PP Key Acknowledgement to SAP
 * 
 * @author chackram
 */
public class PPKeySAPAckJob implements Job {

	private final Logger logger = ESAPI.getLogger(PPKeySAPAckJob.class);

	private final PPKeySchedulerServiceImpl ppkSchedulerService;
	private final PPKeyServiceHelper svchelper;
	private final VMSUtils util;
	private	final PPKeySchedulerDAO schedulerDao;
	private final JobDetails jobDetails;

	/**
	 * Constructor
	 */
	public PPKeySAPAckJob() {
		ppkSchedulerService = new PPKeySchedulerServiceImpl();
		svchelper = new PPKeyServiceHelper();
		util = new VMSUtils();
		schedulerDao = new PPKeySchedulerDAO();
		jobDetails = new JobDetails();
	}

	/**
	 * Constructor
	 */
	public PPKeySAPAckJob(final PPKeySchedulerServiceImpl ppkSchedulerService, final PPKeyServiceHelper svchelper, final VMSUtils vmsUtils, 
			final PPKeySchedulerDAO schedulerDao, final JobDetails jobDetails) {
		this.ppkSchedulerService = ppkSchedulerService;
		this.svchelper = svchelper;
		this.util = vmsUtils;
		this.schedulerDao = schedulerDao;
		this.jobDetails = jobDetails;
	}

	/**
	 * Executes PPKeySAPAckJob
	 * 
	 * @param jobExecutionContext - JobExecutionContext
	 * @throws JobExecutionException
	 */
	@Override
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySAPAckJob:execute method");
		try {
			final VendServiceDetails vendService = getVendServiceDetails();
			svchelper.prepareJobDetails(jobExecutionContext, jobDetails);
			final int status = sendUpdatePPKeyAcktoSAP(vendService);
			if( status == 408 ) {
				/** Schedule Trigger for Retry **/
				final boolean retryStatus = processRetry(vendService);
				if( retryStatus ) {
					/** Log the SOAP message here for VMS Utility retry **/
					final SmartMeterProcessingActionPPKeyAcknowledgmentOutServiceStub stub = new SmartMeterProcessingActionPPKeyAcknowledgmentOutServiceStub(vendService.getUrl());
					final SOAPEnvelope env = stub._getServiceClient().getLastOperationContext().getMessageContexts().get("Out").getEnvelope();
					final VMSMessagingSystem messagingSystem = new VMSMessagingSystem();
					messagingSystem.setMessageData(env.toString());
					messagingSystem.setDeviceTypeID(DeviceTypeEnum.PPK_ACK.getDeviceType());
					schedulerDao.insert(messagingSystem);
				}
			}
		} catch (DBException e) {
			logger.error(Logger.EVENT_FAILURE,"DB Exception :" + e.getMessage());
			throw new JobExecutionException();
		} catch (AxisFault e) {
			logger.error(Logger.EVENT_FAILURE,"AxisFault Exception :" + e.getMessage());
			throw new JobExecutionException();
		} catch (NamingException e) {
			logger.error(Logger.EVENT_FAILURE,"Naming Exception :" + e.getMessage());
			throw new JobExecutionException();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySAPAckJob:execute method");

	}

	/**
	 * Sends PP Key Update Acknowledgement to SAP
	 * @param vendService - VendServiceDetails
	 * 
	 * @return Integer
	 */
	private int sendUpdatePPKeyAcktoSAP(final VendServiceDetails vendService) {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySAPAckJob:sendUpdatePPKeyAcktoSAP method");
		int status = Status.SC_200.getStatus();
		try {
			final SmartMeterProcessingActionPPKeyAcknowledgmentOutServiceStub stub = new SmartMeterProcessingActionPPKeyAcknowledgmentOutServiceStub(vendService.getUrl());

			final HttpTransportProperties.Authenticator basicAuthentication = new HttpTransportProperties.Authenticator();
			basicAuthentication.setUsername(vendService.getUserName());
			basicAuthentication.setPassword(vendService.getPassword());
			final List<String> authSchemes = new ArrayList<String>();
			authSchemes.add(Authenticator.BASIC);
			basicAuthentication.setAuthSchemes(authSchemes);
			stub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, basicAuthentication);
			stub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, "false");

			final UpdatePPKeyAcknowledgment acknowledgement = setUpdatePPKeyAcknowledgement();
			stub.PPKeyAcknowledgment_Async(acknowledgement);
		} catch (AxisFault e) {
			status = 408;
			logger.error(Logger.EVENT_FAILURE,"AxisFault Exception when sending PP Key acknowledgement to SAP" + e.getMessage());
		} catch (RemoteException e) {
			status = 408;
			logger.error(Logger.EVENT_FAILURE,"Remote Exception when sending PP Key acknowledgement to SAP" + e.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySAPAckJob:sendUpdatePPKeyAcktoSAP method");
		return status;

	}

	/**
	 * Sets Update PP Key Acknowledgement Request from Job Details
	 * @return UpdatePPKeyAcknowledgment
	 */
	private UpdatePPKeyAcknowledgment setUpdatePPKeyAcknowledgement() {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySAPAckJob:setUpdatePPKeyAcknowledgement method");
		final UpdatePPKeyAcknowledgment acknowledgement = new UpdatePPKeyAcknowledgment();
		final UpdatePPKeyAcknowledgmentMessage msg = new UpdatePPKeyAcknowledgmentMessage();
		msg.setDescription(jobDetails.getStatus() == 200 ? Constants.SUCCESS : Constants.PPKEY_UPDATE_FAILED);
		msg.setMPXN(jobDetails.getMpxn());
		msg.setMSN(jobDetails.getMsn());
		msg.setReasonCode(jobDetails.getStatus().toString());
		msg.setTransactionID(jobDetails.getTransactionID());
		acknowledgement.setUpdatePPKeyAcknowledgment(msg);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySAPAckJob:setUpdatePPKeyAcknowledgement method");
		return acknowledgement;

	}

	/**
	 * Processes Retry for failure communications to SAP
	 * 
	 * @param vendServiceDetails - VendServiceDetails
	 * @return boolean
	 * @throws DBException
	 */
	private boolean processRetry(final VendServiceDetails vendServiceDetails) throws DBException {

		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySAPAckJob:processRetry method");
		boolean status = Boolean.FALSE;
		int retryCount = jobDetails.getRetryCount();

		logger.info(Logger.EVENT_UNSPECIFIED,"Retry Count " + retryCount);

		if( retryCount < new Integer(vendServiceDetails.getNoofretries()).intValue() ) {
			final Long retryPeriod = new Long(vendServiceDetails.getRetryPeriod(retryCount));
			logger.info(Logger.EVENT_UNSPECIFIED,"Retry Period " + retryPeriod );
			retryCount++;
			final boolean scheduleStatus = ppkSchedulerService.scheduleACKJobtoSAP(retryCount, jobDetails.getTransactionID(), retryPeriod, 
					jobDetails.getMsn(), jobDetails.getMpxn(), jobDetails.getStatus(), jobDetails.getTimestamp(), false);
			logger.info(Logger.EVENT_UNSPECIFIED,"schedule status returns : " + scheduleStatus);
			if( !scheduleStatus ) {
				status = Boolean.TRUE;
			}
		} else {
			status = Boolean.TRUE;
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving PPKeySAPAckJob:processRetry method");
		return status;

	}

	/**
	 * Returns Vend Service Details from Context Configuration
	 * @return VendServiceDetails
	 * @throws NamingException
	 */
	private VendServiceDetails getVendServiceDetails() throws NamingException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering PPKeySAPAckJob:getVendServiceDetails method");
		return (VendServiceDetails) util.getVendServiceDetails(DeviceTypeConstants.PPKKEY_SAP_ACKNOWLEDGEMENT);
	}

}
