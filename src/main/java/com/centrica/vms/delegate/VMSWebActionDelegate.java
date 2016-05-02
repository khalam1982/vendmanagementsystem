/**
 * VMSWebActionDelegate.java
 * Purpose: Class for VMS web action delegate
 *
 * @author ramasap1
 */
package com.centrica.vms.delegate;

import java.io.OutputStream;
import java.util.ArrayList;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.exception.UserOperationException;
import com.centrica.vms.exception.UsrAlreadyExistException;
import com.centrica.vms.exception.UsrLockedException;
import com.centrica.vms.exception.UsrPwdExpiredException;
import com.centrica.vms.exception.UsrisaPowerUsrException;
import com.centrica.vms.form.AckRequestDetailsForm;
import com.centrica.vms.form.AdjustmentRequestDetailsForm;
import com.centrica.vms.form.HEUtilityRequestForm;
import com.centrica.vms.form.VendReportRequestForm;
import com.centrica.vms.form.VendRequestDetailsForm;
import com.centrica.vms.form.VoidRequestDetailsForm;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.GroupDetails;
import com.centrica.vms.model.SourceDetails;
import com.centrica.vms.model.StatusDetails;
import com.centrica.vms.model.UserCredentials;
import com.centrica.vms.model.UserDetails;
import com.centrica.vms.model.VendHistoryDetails;
import com.centrica.vms.service.IVMSWebActionService;
import com.centrica.vms.service.VMSWebActionService;
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;
/**
 * Methods for VMS web action delegate
 */
public class VMSWebActionDelegate {
	
 
	private Logger logger = ESAPI.getLogger(this.getClass());
	private IVMSWebActionService vmsWebActionService = new VMSWebActionService();
	/**
	 * Method to get the meter details
	 * @param String - pan
	 * @return MeterDetails
	 */
	public MeterDetails getMeterDetails(String msn){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getMeterDetails method");
		MeterDetails meterDetails = vmsWebActionService.getMeterDetails(msn);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getMeterDetails method");
		return meterDetails;
	}
	
	/**
	 * Method to get the customer details
	 * @param String - pan
	 * @return CustomerDetails
	 */
	public CustomerDetails getCustomerDetails(String pan){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getCustomerDetails method");
		CustomerDetails customerDetails = vmsWebActionService.getCustomerDetails(pan);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getCustomerDetails method");
		return customerDetails;
	}
	
	/**
	 * Method to get the premise details
	 * @param String - mpxn
	 * @return PremiseDetails
	 */
	public PremiseDetails getPremiseDetails(String mpxn){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getPremiseDetails method");
		PremiseDetails premiseDetails = vmsWebActionService.getPremiseDetails(mpxn);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getPremiseDetails method");
		return premiseDetails;
	}
	
	/**
	 * Method to save the meter details
	 * @param MeterDetails
	 * @return Boolean
	 */
	public Boolean saveMeterDetails(MeterDetails meterDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering saveMeterDetails method");
		Boolean status = vmsWebActionService.saveMeterDetails(meterDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving saveMeterDetails method");
		return status;
	}
	
	
	/**
	 * Method to save the premise details
	 * @param PremiseDetails
	 * @return Boolean
	 */
	public Boolean savePremiseDetails(PremiseDetails premiseDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering savePremiseDetails method");
		Boolean status = vmsWebActionService.savePremiseDetails(premiseDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving savePremiseDetails method");
		return status;
	}
	
	/**
	 * Method to save the customer details
	 * @param CustomerDetails
	 * @return Boolean
	 */
	public Boolean saveCustomerDetails(CustomerDetails customerDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering saveCustomerDetails method");
		Boolean status = vmsWebActionService.saveCustomerDetails(customerDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving saveCustomerDetails method");
		return status;
	}
	
	/**
	 * Method to update the meter details
	 * @param MeterDetails
	 * @return Boolean
	 */
	public Boolean updateMeterDetails(MeterDetails meterDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateMeterDetails method");
		Boolean status = vmsWebActionService.updateMeterDetails(meterDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateMeterDetails method");
		return status;
	}
	
	/**
	 * Method to update the customer details
	 * @param CustomerDetails
	 * @return Boolean
	 */
	public Boolean updateCustomerDetails(CustomerDetails customerDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateCustomerDetails method");
		Boolean status = vmsWebActionService.updateCustomerDetails(customerDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateCustomerDetails method");
		return status;
	}
	
	/**
	 * Method to update the premise details
	 * @param PremiseDetails
	 * @return Boolean
	 */
	public Boolean updatePremiseDetails(PremiseDetails premiseDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering updatePremiseDetails method");
		Boolean status = vmsWebActionService.updatePremiseDetails(premiseDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updatePremiseDetails method");
		return status;
	}
	
	/**
	 * Method to delete the meter details
	 * @param MeterDetails
	 * @return Boolean 
	 */
	public Boolean deleteMeterDetails(MeterDetails meterDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering deleteMeterDetails method");
		Boolean status = vmsWebActionService.deleteMeterDetails(meterDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving deleteMeterDetails method");
		return status;
	}

	/**
	 * Method to get the list of vend transactions for the given pin
	 * @param String - pan
	 * @return ArrayList<VendHistoryDetails>
	 */
	public ArrayList<VendHistoryDetails> getVendHistory(String pan){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendHistory method");
		ArrayList<VendHistoryDetails> vendHistoryDetails = vmsWebActionService.getVendHistory(pan);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendHistory method");
		return vendHistoryDetails;
	}
	
	/**
	 * Method to send the vend request
	 * @param VendRequestDetailsForm
	 * @return VendRequestDetailsForm
	 */
	public VendRequestDetailsForm sendVendRequest(VendRequestDetailsForm vendRequestDetailsForm){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendRequest method");
		VendRequestDetailsForm vendResponseDetailsForm = vmsWebActionService.sendVendRequest(vendRequestDetailsForm);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendRequest method");
		return vendResponseDetailsForm;
	}
	
	/**
	 * Method to send the vend request
	 * @param VendRequestDetailsForm
	 * @return VendRequestDetailsForm
	 */
	public VoidRequestDetailsForm sendVoidRequest(VoidRequestDetailsForm voidRequestDetailsForm){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendRequest method");
		VoidRequestDetailsForm voidResponseDetailsForm = vmsWebActionService.sendVoidRequest(voidRequestDetailsForm);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendRequest method");
		return voidResponseDetailsForm;
	}
	
	/**
	 * Method to send the ACK request
	 * @param AckRequestDetailsForm
	 * @return AckRequestDetailsForm
	 */
	public AckRequestDetailsForm sendAckRequest(AckRequestDetailsForm ackRequestDetailsForm){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendRequest method");
		AckRequestDetailsForm ackResponseDetailsForm = vmsWebActionService.sendAckRequest(ackRequestDetailsForm);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendRequest method");
		return ackResponseDetailsForm;
	}
	
	/**
	 * Method to send the vend request
	 * @param vendReportRequestForm
	 * @return vendReportRequestForm
	 */
	public VendReportRequestForm sendVendReportRequest(VendReportRequestForm vendReportRequestForm, OutputStream out){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendVendReportRequest method");
		VendReportRequestForm vendReportResponseForm = vmsWebActionService.sendVendReportRequest(vendReportRequestForm, out);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendVendReportRequest method");
		return vendReportResponseForm;
	}
	
	
	/**
	 * Method to get the valid user details
	 * @param String - userName
	 * @param String - password
	 * @return UserDetails
	 * @throws UsrPwdExpiredException
	 * @throws UsrLockedException
	 */
	public UserDetails getUserDetails(String userName, String password) throws UsrPwdExpiredException,UsrLockedException {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getUserDetails method");
		UserDetails userDetails = vmsWebActionService.getUserDetails(userName, password);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getUserDetails method");
		return userDetails;
	}
	
	/**
	 * Method to get the group details expect the group id passed
	 * @param String - group ID
	 * @return ArrayList<GroupDetails>
	 */
	public ArrayList<GroupDetails> getGroupDetails(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getGroupDetails method");
		ArrayList<GroupDetails> groupDetails = vmsWebActionService.getGroupDetails(groupID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getGroupDetails method");
		return groupDetails;
	}
	
	/**
	 * Method to get the power user of the group
	 * @param String - group ID
	 * @return UserDetails
	 */
	public UserDetails getUserDetails(String groupID) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getUserDetails method");
		UserDetails userDetails = vmsWebActionService.getUserDetails(groupID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getUserDetails method");
		return userDetails;
	}
	
	/**
	 * Method to get the list of functions
	 * @param String - group ID
	 * @return ArrayList<FunctionDetails>
	 */
	public ArrayList<FunctionDetails> getFunctionDetails(String groupID){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getFunctionDetails method");
		ArrayList<FunctionDetails> functionDetails = vmsWebActionService.getFunctionDetails(groupID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getFunctionDetails method");
		return functionDetails;
	}
	
	/**
	 *  Method used to update the user details
	 * @param UserDetails
	 * @param Boolean
	 * @return Boolean
	 * @throws UserOperationException
	 */
	public Boolean updateUserDetails(UserDetails userDetails,Boolean isPowerUser) throws UserOperationException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateUserDetails method");
		Boolean status = vmsWebActionService.updateUserDetails(userDetails,isPowerUser);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateUserDetails method");
		return status;
	}
	
	/**
	 * Method used to add the new user
	 * @param UserDetails
	 * @return Boolean
	 */
	public Boolean saveUserDetails(UserDetails userDetails){
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateUserDetails method");
		Boolean status = vmsWebActionService.saveUserDetails(userDetails);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateUserDetails method");
		return status;
	}
	
	/**
	 * Method used to delete the user details
	 * @param UserDetails
	 * @param Boolean
	 * @return Boolean
	 * @throws UserOperationException
	 */
	public Boolean deleteUserDetails(UserDetails userDetails,Boolean isPowerUser) throws UserOperationException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering deleteUserDetails method");
		Boolean status = vmsWebActionService.deleteUserDetails(userDetails,isPowerUser);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving deleteUserDetails method");
		return status;
		
	}
	
	/**
	 *  Method to get the user of the group
	 * @param String - userName
	 * @param String - groupID
	 * @return UserDetails
	 * @throws UsrAlreadyExistException
	 * @throws UsrisaPowerUsrException
	 */
	public UserDetails getUser(String userName,String groupID) throws UsrAlreadyExistException,UsrisaPowerUsrException{
		logger.debug(Logger.EVENT_SUCCESS,"Entering getUser method");
		UserDetails userDetails = vmsWebActionService.getUser(userName,groupID);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getUser method");
		return userDetails;
	}
	
	/**
	 * Method used to get the user credentials for the given user
	 * @param String - userName
	 * @return UserCredentials
	 */
	public UserCredentials getUserCredentials(String userName){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getUserCredentials method");
		UserCredentials userCredentials = vmsWebActionService.getUserCredentials(userName);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getUserCredentials method");
		return userCredentials;
		
	}
	
	/**
	 * Method used to update the user credentials
	 * @param UserCredentials
	 * @return Boolean
	 */
	public Boolean updateUserCredentials(UserCredentials userCredentials){
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateUserCredentials method");
		Boolean status = vmsWebActionService.updateUserCredentials(userCredentials);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateUserCredentials method");
		return status;
		
	}
	
	/* Drop 2 -  starts here */
	/**
	 * Method to send the adjustment request
	 * @param AdjustmentRequestDetailsForm
	 * @return AdjustmentRequestDetailsForm
	 */
	public AdjustmentRequestDetailsForm sendAdjustmentRequest(AdjustmentRequestDetailsForm adjustmentRequestDetailsForm){
		logger.debug(Logger.EVENT_SUCCESS,"Entering sendAdjustmentRequest method");
		AdjustmentRequestDetailsForm adjustmentResponseDetailsForm = vmsWebActionService.sendAdjustmentRequest(adjustmentRequestDetailsForm);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving sendAdjustmentRequest method");
		return adjustmentResponseDetailsForm;
	}
	/* Drop 2 -  ends here */
	
	
	/**
	 * Method to schedule the Headend utility for process the unsent messages
	 * @param HEUtilityRequestForm
	 * @return Integer
	 */
	public Integer scheduleHEUtility(HEUtilityRequestForm heUtilityRequestForm, int deviceType){
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleHEUtility method");
		Integer processStatus = vmsWebActionService.scheduleHEUtility(heUtilityRequestForm, deviceType);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving scheduleHEUtility method");
		return processStatus;
	}
	
	/**
	 * Method to schedule the Headend CIM utility for process the unsent messages
	 * @param HEUtilityRequestForm
	 * @return Integer
	 */
	public Integer scheduleCIMUtility(HEUtilityRequestForm heUtilityRequestForm){
		logger.debug(Logger.EVENT_SUCCESS,"Entering scheduleCIMUtility method");
		Integer processStatus = vmsWebActionService.scheduleCIMUtility(heUtilityRequestForm);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving scheduleCIMUtility method");
		return processStatus;
	}
	
	/**
	 * Method to call the service to get the vend history count to re-send.
	 * @param String - user name
	 * @param String - type
	 * @return Integer
	 */
	public Integer getResendTransactionCount(String userName, Integer type){
		logger.debug(Logger.EVENT_SUCCESS,"Entering getResendTransactionCount method");
		Integer processStatus = vmsWebActionService.getResendTransactionCount(userName, type);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getResendTransactionCount method");
		return processStatus;
	}
	
	/**
	 * @return
	 */
	public ArrayList<SourceDetails> getVendSourceList() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendSourceList method");
		ArrayList<SourceDetails> sourceLst = null;
		sourceLst = vmsWebActionService.getVendSourceList();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendSourceList method");
		return sourceLst;
		
	}
	
	/**
	 * @return
	 */
	public ArrayList<StatusDetails> getVendStatusList() {
		logger.debug(Logger.EVENT_SUCCESS,"Entering getVendStatusList method");
		ArrayList<StatusDetails> statusLst = null;
		statusLst = vmsWebActionService.getVendStatusList();
		logger.debug(Logger.EVENT_SUCCESS,"Leaving getVendStatusList method");
		return statusLst;
		
	}

	/**
	 * Updates Source Details
	 * 
	 * @param sourceDetails - SourceDetails
	 * @return Boolean
	 */
	public boolean updateSourceDetails(final SourceDetails source) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering updateSourceDetails method");
		Boolean status = vmsWebActionService.updateSourceDetails(source);
		logger.debug(Logger.EVENT_SUCCESS,"Leaving updateSourceDetails method");
		return status;
		
	}

}
