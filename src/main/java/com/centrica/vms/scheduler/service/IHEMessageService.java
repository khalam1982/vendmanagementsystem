/**
 * 
 */
package com.centrica.vms.scheduler.service;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.naming.NamingException;
import javax.xml.soap.SOAPException;

import org.apache.axis2.databinding.ADBException;
import org.hibernate.StaleObjectStateException;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.HESLookUpFailedException;
import com.centrica.vms.scheduler.model.UtilityJobDetails;
import com.trilliantnetworks.unity.ws.AccessDeniedFault;
import com.trilliantnetworks.unity.ws.LoginFault;
import com.trilliantnetworks.unity.ws.UnexpectedErrorFault;

/**
 * @author nagarajk
 *
 */
public interface IHEMessageService {
	/**
	 * Process the unsent messages triggered through VMS utility
	 * @param UtilityJobDetails
	 * @return Boolean
	 * @throws NamingException 
	 * @throws UnexpectedErrorFault 
	 * @throws AccessDeniedFault 
	 * @throws LoginFault 
	 * @throws RemoteException 
	 * @throws ADBException 
	 * @throws SOAPException 
	 * @throws DBException 
	 * @throws StaleObjectStateException 
	 * @throws IOException
	 * @throws HESLookUpFailedException 
	 */
	public Boolean processUnsentMessages(UtilityJobDetails utilityJobDetails) throws ADBException, RemoteException, 
		LoginFault, AccessDeniedFault, UnexpectedErrorFault, NamingException, SOAPException, 
		StaleObjectStateException, DBException, IOException, HESLookUpFailedException;
}
