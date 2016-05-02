/**
 * SAPDelegate.java
 * Purpose: SAP delegate
 * @author nagarajk
 */
package com.centrica.vms.scheduler.external.delegate;

import java.rmi.RemoteException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.scheduler.external.service.ISAPService;
import com.centrica.vms.scheduler.external.service.SAPService;
import com.centrica.vms.scheduler.model.VendAckJobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;


/**
 * Method for SAP delegate functions
 */
public class SAPDelegate {
	
	private Logger logger = null;
	private ISAPService sapService = null;
	
	/*
	 * Constructor
	 */
	public SAPDelegate() {
		logger = ESAPI.getLogger(getClass().getName());
		sapService = new SAPService();
	}
	
	/**
	 * Method to delegate the send vend acknowledgement call to the service
	 * @param JobDetails
	 * @return int
	 */
	public int sendVendAcknowledgement(VendAckJobDetails jobDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendCode method");
		int status = 0;
		
		status = sapService.sendVendAcknowledgement(jobDetails);
		logger.info(Logger.EVENT_UNSPECIFIED,"Status is "+status);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendCode method");
		return status;
	}
	
	/**
	 * Method to get the SOAP Envelope for re-sending it through an utility
	 * @param JobDetails
	 * @return 
	 */
	public void prepareSOAPEnvelope(VendAckJobDetails jobDetails) 
		throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering prepareSOAPEnvelope method");
		sapService.prepareSOAPEnvelope(jobDetails);
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
		env = sapService.getSOAPEnvelope();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getSOAPEnvelope method");
		return env;
	}
}
