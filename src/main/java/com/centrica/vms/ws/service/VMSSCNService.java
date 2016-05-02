/**
 * VMSSCNService.java
 */
package com.centrica.vms.ws.service;

import java.text.ParseException;
import java.util.Calendar;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.common.DeviceTypeEnum;
import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.dao.VMSTransactionDAO;
import com.centrica.vms.exception.DBException;
import com.centrica.vms.exception.VMSInvalidPANException;
import com.centrica.vms.model.VMSMessagingSystem;
import com.centrica.vms.ws.DAO.WSTransactionDAO;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.pi.service.AsynActivationandDeactivation;

/**
 * @author nagarajk
 *
 */
public class VMSSCNService {

	private Logger logger = ESAPI.getLogger(getClass().getName());
	
	/**
	 * Method used to handle the incoming SCN registration/de-registration request.
	 * @param asynActivationandDeactivation
	 */
	public void handleSCNRegistration(AsynActivationandDeactivation asynActivationandDeactivation) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering handleSCNRegistration method");
		VMSUtils util = new VMSUtils();
		Boolean isValid = Boolean.TRUE;
		Boolean status = Boolean.FALSE;
		String pan = asynActivationandDeactivation.getCustomerDetails().getScn();
		String mpxn = asynActivationandDeactivation.getCustomerDetails().getMxpn();
		Calendar validFrom = asynActivationandDeactivation.getCustomerDetails().getValid_from();
		Calendar vFrom = util.truncateDate(validFrom);
		Calendar validTo = asynActivationandDeactivation.getCustomerDetails().getValid_to();
		Calendar vTo = util.truncateDate(validTo);
		VMSValidationService vmsValidationService = new VMSValidationService();
		try {
			vmsValidationService.validatePanInRequest(pan);
			//isValid = vmsValidationService.isWithinRange(vFrom.getTime(), vTo.getTime());
			if (isValid) {
				registerSCNForActiviationDeActivation(pan, mpxn, vFrom,	vTo);
				status = Boolean.TRUE;
			} else {
				logger.info(Logger.EVENT_UNSPECIFIED,"Date range not valid");
			}
		} catch (VMSInvalidPANException ex) {
			logger.error(Logger.EVENT_FAILURE,"VMSInvalidPANException is thrown " + ex.getMessage());
		} catch (DBException ex) {
			logger.error(Logger.EVENT_FAILURE,"DBException is thrown " + ex.getMessage());
		} catch(ParseException ex){
			logger.error(Logger.EVENT_FAILURE,"Parse exception is thrown " + ex.getMessage());
		}finally {
			writeToMessagingSystem(pan, mpxn, vFrom, vTo, status);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving handleSCNRegistration method");
	}

	/**
	 * Method used to write the message into the messaging system
	 * @param validTo 
	 * @param validFrom 
	 * @param mpxn 
	 * @param pan 
	 * @param flag
	 * @throws DBException 
	 */
	private void writeToMessagingSystem(String pan, String mpxn, Calendar validFrom, Calendar validTo, Boolean flag) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering writeToMessagingSystem method");
		VMSMessagingSystem messagingSystem = new VMSMessagingSystem();
		try{
			SOAPBodyElement messageData = constructXMLDataForSOAPResponse(pan, mpxn, validFrom, validTo, flag);
		    messagingSystem.setMessageData(messageData.getChildElements().next().toString());
			messagingSystem.setDeviceTypeID(DeviceTypeEnum.ISU.getDeviceType());
			try {
				new VMSTransactionDAO().insert(messagingSystem);
			} catch (DBException ex) {
				logger.error(Logger.EVENT_FAILURE,"DBException is thrown" + ex.getMessage());
			}
		}catch(NullPointerException ex){
			logger.error(Logger.EVENT_FAILURE,"Null pointer exception is thrown" + ex.getMessage());
		}catch(Exception ex){
			logger.error(Logger.EVENT_FAILURE,"Exception is thrown" + ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving writeToMessagingSystem method");
	}

	/**
	 * Method used to construct the XML data for soap response
	 * @param pan
	 * @param mpxn
	 * @param validFrom
	 * @param validTo
	 * @param flag
	 * @return
	 */
	private SOAPBodyElement constructXMLDataForSOAPResponse(String pan,
			String mpxn, Calendar validFrom, Calendar validTo, Boolean flag) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering constructXMLDataForSOAPResponse method");
		MessageFactory factory;
		SOAPBodyElement smartMeterManageSCN = null;
		try {
			factory = MessageFactory.newInstance();
			SOAPMessage message = factory.createMessage();
			SOAPBody body = message.getSOAPBody();
			QName bodyName = new QName("ack", "SmartMeterManageSCNAckn", "ack");
			smartMeterManageSCN = body.addBodyElement(bodyName);
			QName childName = new QName("SmartMeterManageSCNAcknData");
			SOAPElement smartMeterManagerSCNData = smartMeterManageSCN.addChildElement(childName);
			childName = new QName("SCN");
			SOAPElement product = smartMeterManagerSCNData.addChildElement(childName);
			product.addTextNode(pan);
			childName = new QName("MPXN");
			SOAPElement price = smartMeterManagerSCNData.addChildElement(childName);
			price.addTextNode(mpxn);
			String validFromDate = new Integer(validFrom.get(Calendar.YEAR)).toString()+"/"+formatData(validFrom.get(Calendar.MONTH)+1)+"/"+formatData(validFrom.get(Calendar.DAY_OF_MONTH));
			childName = new QName("StartDate");
			SOAPElement startDate = smartMeterManagerSCNData.addChildElement(childName);
			startDate.addTextNode(validFromDate);
			String validToDate = new Integer(validTo.get(Calendar.YEAR)).toString()+"/"+formatData(validTo.get(Calendar.MONTH)+1)+"/"+formatData(validTo.get(Calendar.DAY_OF_MONTH));
			childName = new QName("EndDate");
			SOAPElement endDate = smartMeterManagerSCNData.addChildElement(childName);
			endDate.addTextNode(validToDate);
			childName = new QName("Flag");
			SOAPElement status = smartMeterManagerSCNData.addChildElement(childName);
			if (flag == Boolean.FALSE) {
				status.addTextNode("N");
			} else {
				status.addTextNode("Y");
			}
		} catch (SOAPException ex) {
			logger.error(Logger.EVENT_FAILURE,"SOAPException :" + ex.getMessage());
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving constructXMLDataForSOAPResponse method");
		return smartMeterManageSCN;
	}


	/**
	 * @param pan
	 * @param mpxn
	 * @param validFrom
	 * @param validTo
	 * @throws DBException 
	 * @throws ParseException 
	 */
	private void registerSCNForActiviationDeActivation(String pan, String mpxn,
			Calendar validFrom, Calendar validTo) throws DBException,ParseException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering registerSCNForActiviation method");
		CustomerDetails customerDetails = setCustomerDetails(pan, mpxn,validFrom, validTo);
		WSTransactionDAO wstransactionDAO = new WSTransactionDAO();
		wstransactionDAO.insertOrUpdate(customerDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving registerSCNForActiviation method");
	}


	/**
	 * @param pan
	 * @param mpxn
	 * @param validFrom
	 * @param validTo
	 * @return
	 */
	private CustomerDetails setCustomerDetails(String pan, String mpxn,
			Calendar validFrom, Calendar validTo) throws ParseException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering setCustomerDetails method");
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setPan(pan);
		customerDetails.setMpxn(mpxn);
		customerDetails.setValidFrom(validFrom.getTime());
		customerDetails.setValidTo(validTo.getTime());
		customerDetails.setCreatedBy("IS-U");
		customerDetails.setUpdatedBy("IS-U");
		customerDetails.setCreatedTimestamp(Calendar.getInstance().getTime());
		customerDetails.setUpdatedTimestamp(Calendar.getInstance().getTime());
		logger.debug(Logger.EVENT_SUCCESS,"Leaving setCustomerDetails method");
		return customerDetails;
	}
	
	/**
	 * Method to get the month in the proper format
	 * @param param
	 * @return
	 */
	private String formatData(int param){
		logger.debug(Logger.EVENT_SUCCESS,"Entering formatData method");
		String returnValue = "";
		if(param<10){
			returnValue = "0"+new Integer(param).toString();
		}else{
			returnValue = new Integer(param).toString();
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving formatData method");
		return returnValue;
	}
}
