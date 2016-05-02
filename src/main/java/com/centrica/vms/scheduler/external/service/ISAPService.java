/**
 * ISAPService.java
 * Purpose: Interface for SAP service
 * @author nagarajk
 */
package com.centrica.vms.scheduler.external.service;

import java.rmi.RemoteException;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axis2.AxisFault;

import com.centrica.vms.scheduler.model.VendAckJobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 * Interface Methods for Head end service
 */
/**
 * @author nagarajk
 *
 */
public interface ISAPService {
	

	/**
	 * @param jobDetails
	 * @return
	 */
	public int sendVendAcknowledgement(VendAckJobDetails jobDetails);
	
	
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
	public void prepareSOAPEnvelope(VendAckJobDetails jobDetails) 
		throws LoginFault, AccessDeniedFault, UnexpectedErrorFault, AxisFault, RemoteException;
	
	/**
	 * Method to get the SOAP Envelope for re-sending it through an utility
	 * @param UnityServiceStub
	 * @param JobDetails
	 * @return
	 */
	public SOAPEnvelope getSOAPEnvelope();
}
