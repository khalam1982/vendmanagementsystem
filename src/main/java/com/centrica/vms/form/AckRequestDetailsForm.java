/**
 * AckRequestDetailsForm.java
 * Purpose: Void request details form
 * @author nagarajk
 */
package com.centrica.vms.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Methods for Vend request details form
 * @see VMSWebForm
 */
public class AckRequestDetailsForm extends VMSWebForm {
	

	/*
	 * Method to get the description
	 * @param
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/*
	 * Method to set the description
	 * @param String - description
	 * @return
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * Method to get the transaction ID
	 * @param
	 * @return String
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/*
	 * Method to set the transaction ID
	 * @param String - transaction ID
	 * @return
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	private String description;
	
	private String transactionID;
	
	private String transactionDesc;

	/**
	 * @return the transactionDesc
	 */
	public String getTransactionDesc() {
		return transactionDesc;
	}

	/**
	 * @param transactionDesc the transactionDesc to set
	 */
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	private String statusCode;
	
	/*
	 * Method to get the status code
	 * @param
	 * @return String
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/*
	 * Method to set the status code
	 * @param String - status
	 * @return
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if(getDispatch()!=null && getDispatch().equals("submit")){
			if (getTransactionID() == null || ("".equals(getTransactionID()))) {
				errors.add("app.transactionid", new ActionMessage("error.transactionid.required"));
			} else if (getTransactionDesc() == null || ("".equals(getTransactionDesc()))) {
					errors.add("app.transactiondesc", new ActionMessage("error.transactiondesc.required"));
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
		setTransactionID("");
		setTransactionDesc("");
	}
	
	/**
	 * Method to clear down the values
	 * @param
	 * @return
	 */
	public void clear(){
		if(getStatusCode()!=null && getStatusCode().equals("")){
			setTransactionID("");
			setTransactionDesc("");
		}
	}
}
