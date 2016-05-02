/**
 * ChangePasswordForm.java
 * Purpose: Change password form
 * @author ramasap1
 */
package com.centrica.vms.form;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.centrica.vms.common.VMSUtils;
/**
 * Methods for change password form
 * @see VMSWebForm
 * 
 */
public class ChangePasswordForm extends VMSWebForm {
	
	 private Logger logger = ESAPI.getLogger(getClass());
	 
     private String userName;
     
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
	 * Method to get the old password
	 * @param
	 * @return String
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/*
	 * Method to set the old password
	 * @param String - password
	 * @return 
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword.trim();
	}

	/*
	 * Method to get the new password
	 * @param
	 * @return String
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/* 
	 * Method to set the new password
	 * @param String - password
	 * @return
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword.trim();
	}

	private String oldPassword;
     
    private String newPassword;
    
    /*
     * (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validate method");
		ActionErrors errors = new ActionErrors();
		if(getDispatch().equals("update")){
			if (getUserName() == null || ("".equals(getUserName()))) {
				errors.add("app.username", new ActionMessage("error.username.required"));
			}
			else{
				validatePassword(request, errors,getNewPassword(),getOldPassword());
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Leaving validate method");
		return errors;
	}
    
  /**
   * Method to validate the user entered password
   * @param HttpServletRequest
   * @param ActionErrors
   * @param String - password
   * @param String - oldPassword
   * @return
   */
	private void validatePassword(HttpServletRequest request,ActionErrors errors,String password,String oldPassword) {
		logger.debug(Logger.EVENT_SUCCESS,"Entering validatePassword method");
		password = password.trim();
		VMSUtils vmsUtils = new VMSUtils();
		if(password.equals("")){
			errors.add("app.password", new ActionMessage("error.password.required"));
		}
		else if(!password.equals("")){
			if(password.equals(oldPassword)){
				errors.add("app.password", new ActionMessage("error.password.samepassword"));
			}else if(password.length()<8){
				errors.add("app.password", new ActionMessage("error.password.minlength"));
			}else if(password.length()>12){
				errors.add("app.password", new ActionMessage("error.password.maxlength"));
			}else if(!vmsUtils.hasSpecialCharacter(password) && !vmsUtils.hasNumeric(password)){
				errors.add("app.password", new ActionMessage("error.password.nonnumeric"));
			}else if(vmsUtils.allNumeric(password)  || vmsUtils.allSpecialCharacters(password)){
				errors.add("app.password", new ActionMessage("error.password.number"));
			}else if(vmsUtils.isConsecutivePassword(password)){
				errors.add("app.password", new ActionMessage("error.password.consecutive"));
			}
		}
		logger.debug(Logger.EVENT_SUCCESS,"Entering validatePassword method");
	}
     
}
