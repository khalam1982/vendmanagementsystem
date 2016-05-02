/**
 * PowerUserAdminForm.java
 * Purpose: Power user admin form
 * @author ramasap1
 */
package com.centrica.vms.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.centrica.vms.common.VMSUtils;
import com.centrica.vms.model.FunctionDetails;
import com.centrica.vms.model.GroupDetails;

/**
 * Methods for Power user admin form
 * @see VMSWebForm
 */
public class PowerUserAdminForm extends VMSWebForm {
	
	private ArrayList<GroupDetails> groupDetails;
	
	private String vmsGroup;

	private String userName;
	
	private boolean passwordExpired;
	
	private String userFunctionCodes;
	
	private String lanID;

	private ArrayList<FunctionDetails> availableFunctionDetails;
	
	private ArrayList<FunctionDetails> selectedFunctionDetails;
	
	private String vmsGroupName;
	
	private Logger logger = ESAPI.getLogger(getClass());
	
	/*
	 * Method to get the VMS group name
	 * @param
	 * @return String
	 */
	public String getVmsGroupName() {
		return vmsGroupName;
	}

	/*
	 * Method to set the VMS group name
	 * @param String - group name
	 * @return
	 */
	public void setVmsGroupName(String vmsGroupName) {
		this.vmsGroupName = vmsGroupName;
	}

	/*
	 * Method to get the LAN ID
	 * @param
	 * @return String
	 */
	public String getLanID() {
		return lanID;
	}

	/*
	 * Method to set the LAN ID
	 * @param String - LAN ID
	 * @return
	 */
	public void setLanID(String lanID) {
		this.lanID = lanID;
	}
	
	/*
	 * Method to get the selected function details
	 * @param
	 * @return String
	 * 
	 */
	public  ArrayList<FunctionDetails> getSelectedFunctionDetails() {
		return selectedFunctionDetails;
	}

	/*
	 * Method to set the selected function details
	 * @param String - function detail
	 * @Return
	 */
	public void setSelectedFunctionDetails(ArrayList<FunctionDetails> selectedFunctionDetails) {
		this.selectedFunctionDetails = selectedFunctionDetails;
	}

	/*
	 * Method to get the available function details
	 * @param
	 * @return String
	 */
	public ArrayList<FunctionDetails> getAvailableFunctionDetails() {
		return availableFunctionDetails;
	}

	/*
	 * Method to set the available function details
	 * @param String - function details
	 * @return
	 */
	public void setAvailableFunctionDetails(
			ArrayList<FunctionDetails> availableFunctionDetails) {
		this.availableFunctionDetails = availableFunctionDetails;
	}

	/*
	 * Method to get the user function codes
	 * @param
	 * @return String
	 */
	public String getUserFunctionCodes() {
		return userFunctionCodes;
	}

	/*
	 * Method to set the user function codes
	 * @param String - function codes
	 * @return
	 */
	public void setUserFunctionCodes(String userFunctionCodes) {
		this.userFunctionCodes = userFunctionCodes;
	}

	/* 
	 * Method to get the user name
	 * @param
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/* 
	 * Method to set the user name
	 * @param String - user name
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/* 
	 * Method to get the LOCK IND
	 * @param 
	 * @return String
	 */
	public char getLockIND() {
		return lockIND;
	}

	/*
	 * Method to set the LOCK IND
	 * @param String - lock
	 * @return
	 */
	public void setLockIND(char lockIND) {
		this.lockIND = lockIND;
	}

	/* 
	 * Method to get the password expired status
	 * @param
	 * @return boolean
	 */
	public boolean getPasswordExpired() {
		return passwordExpired;
	}

	/*
	 * Method to set the password expired status
	 * @param boolean
	 * @return
	 */
	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	// CEN_3 start
	
	private String userPassword="";
	
	/*
	 * Method to get the user password
	 * @param
	 * @return String
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/* 
	 * Method to set the user password
	 * @param String - user password
	 * @return
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword.trim();
	}

	/*
	 * Method to get the admin password
	 * @param
	 * @return String
	 */
	public String getAdminPassword() {
		return adminPassword;
	}

	/*
	 * Method to set the admin password
	 * @param String - admin password
	 * @return
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword.trim();
	}

	private String adminPassword="";
	
	// CEN_3 end 
	
	private char lockIND;
	
	private String operationType;
	
	/*
	 * Method to get the operation type
	 * @param
	 * @return String
	 */
	public String getOperationType() {
		return operationType;
	}

	/*
	 * Method to set the operation type
	 * @param String - operation type
	 * @return
	 * 
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/*
	 * Method of get the group details
	 * @param
	 * @return ArrayList<GroupDetails>
	 */
	public ArrayList<GroupDetails> getGroupDetails() {
		return groupDetails;
	}

	/*
	 * Method to set the group details
	 * @param ArrayList<GroupDetails>
	 * @return
	 */
	public void setGroupDetails(ArrayList<GroupDetails> groupDetails) {
		this.groupDetails = groupDetails;
	}

	/*
	 * Method to get the VMS group
	 * @param 
	 * @return String
	 */
	public String getVmsGroup() {
		return vmsGroup;
	}

	/*
	 * Method to set the VMS group
	 * @param String - VMS group
	 * @return 
	 */
	public void setVmsGroup(String vmsGroup) {
		this.vmsGroup = vmsGroup;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validate method");
		ActionErrors errors = new ActionErrors();
		if (getDispatch().equals("detailPage")) {
			if (getVmsGroup() == null || ("".equals(getVmsGroup()) || ("0".equals(getVmsGroup())))) {
				errors.add("app.vmsgroup", new ActionMessage("error.vmsgroup.required"));
				setGroupDetails((ArrayList<GroupDetails>)request.getSession(false).getAttribute("GroupDetails"));
				request.getSession(false).removeAttribute("GroupDetails");
			}
		}else if(getDispatch().equals("userDetailPage")){
			if (getUserName() == null || ("".equals(getUserName()))) {
				errors.add("app.username", new ActionMessage("error.username.required"));
			}
		}// CEN_3 start
		else if(getVmsGroup()!=null && getVmsGroup().equals("G4")){
			validateData(request, errors,getAdminPassword(),getUserPassword());
		}// CEN_3 end 
		else if (!getDispatch().equals("groupPage") && !getDispatch().equals("searchPage")){
			if(getLanID() == null || getLanID().equals("")){
				errors.add("app.lanid", new ActionMessage("error.lanid.required"));
				populateFunctionDetails(request);
			}// CEN_3 start
			else{
				validateData(request, errors,getAdminPassword(),getUserPassword());
			} // CEN_3 end 
				
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validate method");
		return errors;
	}
	// CEN_3 start
	/**
	 * Method to validate the user entered data
	 * @param HttpServletRequest
	 * @param ActionErrors
	 * @param String - password
	 * @param String - userPassword
	 * @return
	 */
	private void validateData(HttpServletRequest request,ActionErrors errors,String adminPassword,String userPassword) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validatePassword method");
		VMSUtils vmsUtils = new VMSUtils();
		if(getUserName() == null || getUserName().equals("")){
			errors.add("app.username", new ActionMessage("error.username.required"));
			populateFunctionDetails(request);
		}else if(adminPassword.equals("")){
			errors.add("app.password", new ActionMessage("error.adminpassword.required"));
			populateFunctionDetails(request);
		}else if(getOperationType().equals("SAVE") && userPassword.equals("")){
			errors.add("app.password", new ActionMessage("error.userpassword.required"));
			populateFunctionDetails(request);
		}
		else if(!getOperationType().equals("DELETE") && !userPassword.equals("")){
			validateUserPassword(request, errors, userPassword, vmsUtils);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Entering validatePassword method");
	}

	/**
	 * Method to validate the user password
	 * @param HttpServletRequest
	 * @param ActionErrors
	 * @param String - userPassword
	 * @param VMSUtils
	 * @return
	 */
	private void validateUserPassword(HttpServletRequest request,
			ActionErrors errors, String userPassword, VMSUtils vmsUtils) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validateUserPassword method");
		if(userPassword.length()<8){
			errors.add("app.password", new ActionMessage("error.password.minlength"));
			populateFunctionDetails(request);
		}else if(userPassword.length()>12){
			errors.add("app.password", new ActionMessage("error.password.maxlength"));
			populateFunctionDetails(request);
		}else if(!vmsUtils.hasSpecialCharacter(userPassword) && !vmsUtils.hasNumeric(userPassword)){
			errors.add("app.password", new ActionMessage("error.password.nonnumeric"));
			populateFunctionDetails(request);
		}else if(vmsUtils.allNumeric(userPassword) || vmsUtils.allSpecialCharacters(userPassword)){
			errors.add("app.password", new ActionMessage("error.password.number"));
			populateFunctionDetails(request);
		}else if(vmsUtils.isConsecutivePassword(userPassword) ){
			errors.add("app.password", new ActionMessage("error.password.consecutive"));
			populateFunctionDetails(request);
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validateUserPassword method");
	}
	// CEN_3 end 
	/**
	 * Method used to populate the function details if the validation has failed
	 * @param HttpServletRequest
	 * @return
	 */
	private void populateFunctionDetails(HttpServletRequest request) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering populateFunctionDetails method");
		setSelectedFunctionDetails((ArrayList<FunctionDetails>)request.getSession(false).getAttribute("selectedFunctionDetails"));
		setAvailableFunctionDetails((ArrayList<FunctionDetails>)request.getSession(false).getAttribute("availableFunctionDetails"));
		logger.debug(Logger.EVENT_SUCCESS,"Leaving populateFunctionDetails method");
	}
}
