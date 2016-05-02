/**
 * UtilityRequestForm.java
 * Purpose: Utility request form
 * @author ramasap1
 */
package com.centrica.vms.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
/**
 * Methods for Utility request form
 * @see VMSWebForm
 */
public class UtilityRequestForm extends VMSWebForm {
	

	private String requestType;
	/*
	 * Method to get the request type
	 * @param
	 * @return String
	 */
	public String getRequestType() {
		return requestType;
	}

	/*
	 * Method to set the request type
	 * @param String - request type
	 * @return
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/* 
	 * Method to get the count
	 * @param
	 * @return String
	 */
	public String getCount() {
		return count;
	}

	/*
	 * Method to set the count
	 * @param String - count
	 * @return
	 */
	public void setCount(String count) {
		this.count = count;
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

	private String count;
	
	private String userName;
	
	private String processStatus;

	/*
	 * Method to ge the processed status
	 * @param
	 * @return String
	 */
	public String getProcessStatus() {
		return processStatus;
	}

	/*
	 * Method to set the processed status
	 * @param String - status
	 * @return
	 */
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (getDispatch().equals("requestPage")) {
			if(requestType.equals("00")){
				errors.add("app.utilitytype", new ActionMessage("error.utilitytype.required"));
			}
		}
		return errors;
	}
}
