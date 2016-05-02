/**
 * AuthenticationService.java
 * Purpose: Authentication service class
 * @author ramasap1
 */
package com.centrica.vms.ws.headend.auth;

import java.util.Iterator;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.exception.DBException;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.headend.ack.fault.LoginFault;

/**
 * Methods for Authentication service
 */
public class AuthenticationService {
	
	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	private final WSTransactionDAO wstransactionDAO;
	
	/**
	 * Constructor
	 */
	public AuthenticationService() {
		wstransactionDAO = new WSTransactionDAO();
	}
	
	/**
	 * Constructor
	 * @param wstransactionDAO - WSTransactionDAO
	 */
	public AuthenticationService(final WSTransactionDAO wstransactionDAO) {
		this.wstransactionDAO = wstransactionDAO;
	}
	
	/**
	 * Method to authenticate the incoming request
	 * @param messageContext
	 * @return
	 * @throws AxisFault
	 * @throws com.centrica.vms.ws.headend.ack.service.LoginFault
	 */
	public void authenticateRequest(MessageContext messageContext) throws AxisFault,com.centrica.vms.ws.headend.ack.service.LoginFault{
		
		String userName = "";
		String password = "";
		logger.debug(Logger.EVENT_SUCCESS,"Entering authenticateRequest method");
		logger.info(Logger.EVENT_UNSPECIFIED,"Message context " + messageContext);
		if(messageContext!=null&&messageContext.getEnvelope()!=null&&messageContext.getEnvelope().getHeader()!=null
				&&messageContext.getEnvelope().getHeader().getFirstElement()!=null
				&&messageContext.getEnvelope().getHeader().getFirstElement().getFirstElement()!=null){
			String tagName = messageContext.getEnvelope().getHeader().getFirstElement().getFirstElement().getLocalName();
			if(tagName.equalsIgnoreCase("userDetails")){
				if (messageContext instanceof MessageContext) {
					Iterator<?> iterator = messageContext.getEnvelope().getHeader().getFirstElement().getFirstElement().getChildElements();
					while(iterator.hasNext()){
						OMElement omnode = (OMElement)iterator.next();
						if(omnode.getLocalName().equalsIgnoreCase("userName")){
							userName = omnode.getText();
						}else if(omnode.getLocalName().equalsIgnoreCase("password")){
							password = omnode.getText();
						}
					}
				}
				validateUser(userName,password);
			}else{
				logger.error(Logger.EVENT_FAILURE,"VMS fault is thrown");
				throw new AxisFault("No User details found in the header");
			}
			
		}else{
			logger.error(Logger.EVENT_FAILURE,"VMS fault is thrown");
			throw new AxisFault("SOAP message context is not valid");
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving authenticateRequest method");
	}
	
	/**
	 * Method to validate the user details
	 * @param userName
	 * @param password
	 * @return
	 */
	public void validateUser(String userName,String password) throws com.centrica.vms.ws.headend.ack.service.LoginFault{
		logger.debug(Logger.EVENT_SUCCESS,"Entering validateUser");
		
		String message = "";
		try{
			Boolean status = wstransactionDAO.getUserStatus(userName,password);
			if(status){
				logger.info(Logger.EVENT_UNSPECIFIED,"User Details are valid");
			}else{
				logger.info(Logger.EVENT_UNSPECIFIED,"User Details are incorrect");
				message = "User details are incorrect";
				throwLoginFault(message);
			}
		}catch(DBException ex){
			logger.error(Logger.EVENT_FAILURE,"DBException in thrown");
			throwLoginFault(message);
		}
		
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validateUser");
	}

	/**
	 * Method to throw the login fault message
	 * @param message
	 * @throws LoginFault
	 * @return
	 */
	private void throwLoginFault(String message)
			throws com.centrica.vms.ws.headend.ack.service.LoginFault {
		logger.debug(Logger.EVENT_SUCCESS,"Entering throwLoginFault method");
		LoginFault loginFault = new LoginFault();
		loginFault.setExceptionMessage(message);
		com.centrica.vms.ws.headend.ack.service.LoginFault loginException = new com.centrica.vms.ws.headend.ack.service.LoginFault();
		loginException.setFaultMessage(loginFault);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving throwLoginFault method");
		throw loginException;
	}

}
