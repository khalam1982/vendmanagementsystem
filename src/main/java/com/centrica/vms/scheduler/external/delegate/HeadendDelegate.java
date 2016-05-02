/**
 * HeadendDelegate.java
 * Purpose: HEAD END delegate
 * @author ramasap1
 */
package com.centrica.vms.scheduler.external.delegate;

import java.rmi.RemoteException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.scheduler.external.service.HeadendService;
import com.centrica.vms.scheduler.external.service.IHeadendService;
import com.centrica.vms.scheduler.model.JobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;


/**
 * Method for HEAD END delegate
 */
public class HeadendDelegate {
	
	private Logger logger = null;
	private IHeadendService headendService = null;
	
	/*
	 * Constructor
	 */
	public HeadendDelegate() {
		logger = ESAPI.getLogger(getClass().getName());
		headendService = new HeadendService();
	}
	
	/**
	 * Method to delegate the send vend code call to the service
	 * @param JobDetails
	 * @return int
	 */
	public int sendVendCode(JobDetails jobDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendCode method");
		int status = 0;
		
		status = headendService.sendVendCode(jobDetails);
		logger.info(Logger.EVENT_UNSPECIFIED,"Status is "+status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendCode method");
		return status;
	}
	
	/**
	 * Delegate the update of PP Key to hea dend.
	 * 
	 * @param jobDetails
	 * @return status - The status from head end
	 */
	public int sendPPKey(JobDetails jobDetails) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendPPKey method");
		int status = headendService.sendPPKey(jobDetails);
		logger.error(Logger.EVENT_FAILURE,"Status is "+status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendPPKey method");
		return status;
	}

	/**
	 * Method to delegate the send adjust credit call to the service
	 * @param JobDetails
	 * @return int
	 */
	public int sendAdjustCredit(JobDetails jobDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendAdjustCredit method");
		int status = 0;
		
		status = headendService.sendAdjustCredit(jobDetails);
		logger.info(Logger.EVENT_UNSPECIFIED,"Status is "+status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendAdjustCredit method");
		return status;
	}
	
	/**
	 * Method to get the SOAP Envelope for re-sending it through an utility
	 * @param JobDetails
	 * @return 
	 */
	public void prepareSOAPEnvelope(JobDetails jobDetails) 
		throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSOAPEnvelope method");
		headendService.prepareSOAPEnvelope(jobDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSOAPEnvelope method");
	}
	/*
	 * Method to get the SOAP Envelope
	 * @param
	 * @return org.apache.axiom.soap.SOAPEnvelope
	 */
	public SOAPEnvelope getSOAPEnvelope() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getSOAPEnvelope method");
		SOAPEnvelope env = null;
		env = headendService.getSOAPEnvelope();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getSOAPEnvelope method");
		return env;
	}

	/*
	 * Method to get the SOAP Envelope
	 * @param
	 * @return org.apache.axiom.soap.SOAPEnvelope
	 */
	public SOAPEnvelope getAdjustSOAPEnvelope() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getAdjustSOAPEnvelope method");
		SOAPEnvelope env = null;
		env = headendService.getAdjustSOAPEnvelope();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getAdjustSOAPEnvelope method");
		return env;
	}

	/**
	 *  Method to get the SOAP Envelope for Update PP Key request to Headend
	 * 
	 * @param jobDetails
	 * @throws LoginFault
	 * @throws AccessDeniedFault
	 * @throws UnexpectedErrorFault
	 * @throws AxisFault
	 * @throws RemoteException
	 */
	public void preparePPKeySOAPEnvelope(JobDetails jobDetails) throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSOAPEnvelope method");
		headendService.preparePPKeySOAPEnvelope(jobDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving prepareSOAPEnvelope method");
	}

	
}
