/**
 * IHeadendService.java
 * Purpose: Interface for Head end service
 * @author ramasap1
 */
package com.centrica.vms.scheduler.external.service;

import java.rmi.RemoteException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;

import com.centrica.vms.scheduler.model.JobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 * Interface Methods for Head end service
 */
public interface IHeadendService {
	
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.scheduler.external.service.IHeadendService#sendVendCode(com.centrica.vms.scheduler.model.JobDetails)
	 */
	public int sendVendCode(JobDetails jobDetails);
	
	/**
	 * Sends the Prepayment Key to headend system
	 *  
	 * @param JobDetails - The details for building request
	 * @return status - The status from headend.
	 */
	public int sendPPKey(JobDetails jobDetails);
	
	/**
	 * @param jobDetails
	 * @return
	 */
	public int sendAdjustCredit(JobDetails jobDetails);
	
	
	/**
	 * Method to prepare the SOAP Envelope for re-sending it through an utility
	 * @param JobDetails
	 * @return
	 * @throws LoginFault
	 * @throws AccessDeniedFault
	 * @throws UnexpectedErrorFault
	 * @throws AxisFault
	 * @throws RemoteException
	 */
	public void prepareSOAPEnvelope(JobDetails jobDetails) 
		throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException;
	
	/**
	 * Method to get the SOAP Envelope for re-sending it through an utility
	 * @param UnityServiceStub
	 * @param JobDetails
	 * @return
	 */
	public SOAPEnvelope getSOAPEnvelope();
	
	/**
	 * Method to get the SOAP Envelope for re-sending it through an utility
	 * @param UnityServiceStub
	 * @param JobDetails
	 * @return
	 */
	public SOAPEnvelope getAdjustSOAPEnvelope();

	public void preparePPKeySOAPEnvelope(JobDetails jobDetails) throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException;

	

}
