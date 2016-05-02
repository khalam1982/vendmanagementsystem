/**
 * VMSLoginForm.java
 * Purpose: VMS Login details form
 * @author ramasap1
 */
package com.centrica.vms.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Methods for VMS Login details form
 * @see VMSWebForm
 */
public class VMSLoginForm extends VMSWebForm {

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
	 * Method to get the password
	 * @param
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * Method to set the password
	 * @param String - password
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	private String password;

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if(getDispatch().equals("loginSubmit")){
			if (getUserName() != null && getUserName().equals("")) {
				errors.add("app.username", new ActionMessage("error.username.required"));
			}
			else if (getPassword() != null && getPassword().equals("")) {
				errors.add("app.password", new ActionMessage("error.password.required"));
			}
		}
		
		return errors;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setUserName("");
		setPassword("");
	}

}
