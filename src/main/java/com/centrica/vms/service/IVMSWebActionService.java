/**
 * IVMSWebActionService.java
 * Purpose: Interface for VMS Web Action service class
 * @author ramasap1
 */
package com.centrica.vms.service;

import java.io.OutputStream;
import java.util.ArrayList;

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
import com.centrica.vms.ws.model.CustomerDetails;
import com.centrica.vms.ws.model.MeterDetails;
import com.centrica.vms.ws.model.PremiseDetails;

/**
 * Interface Methods for VMS Web Action service
 */
public interface IVMSWebActionService {
	
	
	/**
	 * Method to get the meter details
	 * @param pan
	 * @return
	 */
	public MeterDetails getMeterDetails(String pan);
	
	/**
	 * Method to get the customer details
	 * @param pan
	 * @return
	 */
	public CustomerDetails getCustomerDetails(String pan);
	
	/**
	 * @param mpxn
	 * @return
	 */
	public PremiseDetails getPremiseDetails(String mpxn);
	
	/**
	 * Method to save the meter details
	 * @param meterDetails
	 * @return
	 */
	public Boolean saveMeterDetails(MeterDetails meterDetails);
	
	/**
	 * Method to save the customer details
	 * @param customerDetails
	 * @return
	 */
	public Boolean saveCustomerDetails(CustomerDetails customerDetails);
	
	/**
	 * @param premiseDetails
	 * @return
	 */
	public Boolean savePremiseDetails(PremiseDetails premiseDetails);
	
	/**
	 * Method to schedule the Headend utility for process the unsent messages
	 * @param heUtilityRequestForm
	 * @return
	 */
	public Integer scheduleHEUtility(HEUtilityRequestForm heUtilityRequestForm, int deviceType);
	
	/**
	 * Method to schedule the Headend CIM utility for process the unsent messages
	 * @param heUtilityRequestForm
	 * @return
	 */
	public Integer scheduleCIMUtility(HEUtilityRequestForm heUtilityRequestForm);
	
	/**
	 * Method to update the meter details
	 * @param meterDetails
	 * @return
	 */
	public Boolean updateMeterDetails(MeterDetails meterDetails);
	
	/**
	 * Method to update the customer details
	 * @param customerDetails
	 * @return
	 */
	public Boolean updateCustomerDetails(CustomerDetails customerDetails);
	
	/**
	 * @param premiseDetails
	 * @return
	 */
	public Boolean updatePremiseDetails(PremiseDetails premiseDetails);
	
	/**
	 * Method to delete the meter details
	 * @param meterDetails
	 * @return
	 */
	public Boolean deleteMeterDetails(MeterDetails meterDetails);
	
	/**
	 * Method to retrieve the vend history details.
	 * @param pan
	 * @return
	 */
	public ArrayList<VendHistoryDetails> getVendHistory(String pan);
	
	/**
	 * Method to send the vend request
	 * @param vendRequestDetailsForm
	 * @return
	 */
	public VendRequestDetailsForm sendVendRequest(VendRequestDetailsForm vendRequestDetailsForm);

	/**
	 * Method to send the void request
	 * @param voidRequestDetailsForm
	 * @return
	 */
	public VoidRequestDetailsForm sendVoidRequest(VoidRequestDetailsForm voidRequestDetailsForm);
	
	/**
	 * Method to send the ACK request
	 * @param ackRequestDetailsForm
	 * @return
	 */
	public AckRequestDetailsForm sendAckRequest(AckRequestDetailsForm ackRequestDetailsForm);
	
	/**
	 * Method to send the vend report request
	 * @param vendReportRequestForm
	 * @return
	 */
	public VendReportRequestForm sendVendReportRequest(VendReportRequestForm vendReportRequestForm, OutputStream out);
	
	/**
	 * Method to get the user details
	 * @param userName
	 * @param password
	 * @return
	 * @throws UsrPwdExpiredException
	 * @throws UsrLockedException
	 */
	public UserDetails getUserDetails(String userName, String password)throws UsrPwdExpiredException,UsrLockedException;
	
	/**
	 * Method to get the group details expect the group id passed
	 * @param groupID
	 * @return
	 */
	public ArrayList<GroupDetails> getGroupDetails(String groupID);
	
	/**
	 * Method to get the power user of the group
	 * @param groupID
	 * @return
	 */
	public UserDetails getUserDetails(String groupID);
	
	/**
	 * Method used to get the list of available functions
	 * @param groupID
	 * @return
	 */
	public ArrayList<FunctionDetails> getFunctionDetails(String groupID);
	
	/**
	 * Method to update the user details
	 * @param userDetails
	 * @param isPowerUser
	 * @return
	 * @throws UserOperationException
	 */
	public Boolean updateUserDetails(UserDetails userDetails,Boolean isPowerUser) throws UserOperationException;
	
	/**
	 * Method used to add the  new user
	 * @param userDetails
	 * @return
	 */
	public Boolean saveUserDetails(UserDetails userDetails);
	
	/**
	 * Method used to delete the user
	 * @param userDetails
	 * @param isPowerUser
	 * @return
	 * @throws UserOperationException
	 */
	public Boolean deleteUserDetails(UserDetails userDetails,Boolean isPowerUser) throws UserOperationException;
	
	/**
	 * Method to get the user of the group
	 * @param userName
	 * @param groupID
	 * @return
	 * @throws UsrAlreadyExistException
	 * @throws UsrisaPowerUsrException
	 */
	public UserDetails getUser(String userName,String groupID) throws UsrAlreadyExistException,UsrisaPowerUsrException;
	

	/**
	 * Method used to get the user crendentails for the given user name
	 * @param userName
	 * @return
	 */
	public UserCredentials getUserCredentials(String userName);
	
	
	/**
	 * Method used to get the user crendentails for the given user name
	 * @param userName
	 * @return
	 */
	public Boolean updateUserCredentials(UserCredentials userCredentials);
	
	/**
	 * Method to get the count of vendcodes to resend.
	 * @param userName
	 * @return
	 */
	public Integer getResendTransactionCount(String userName, Integer type);
	
	/* Drop 2 - starts here */
	/**
	 * Method to send the adjustment request
	 * @param adjustmentRequestDetailsForm
	 * @return
	 */
	public AdjustmentRequestDetailsForm sendAdjustmentRequest(AdjustmentRequestDetailsForm adjustmentRequestDetailsForm);
	/* Drop 2 - ends here */
	
	/**
	 * @return
	 */
	public ArrayList<SourceDetails> getVendSourceList();

	/**
	 * @return
	 */
	public ArrayList<StatusDetails> getVendStatusList();
	
	/**
	 * Updates Source Details
	 * 
	 * @param sourceDetails - SourceDetails
	 * @return Boolean
	 */
	public Boolean updateSourceDetails(SourceDetails sourceDetails);

}
