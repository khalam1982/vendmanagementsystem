/**
 * VendRequestDetailsForm.java
 * Purpose: Void request details form
 * @author nagarajk
 */
package com.centrica.vms.form;

import java.math.BigInteger;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Methods for Vend request details form
 * @see VMSWebForm
 */
public class VoidRequestDetailsForm extends VMSWebForm {

	/*
	 * Method to get the PAN
	 * @param
	 * @return String
	 */
	public String getPan() {
		return pan;
	}

	/*
	 * Method to set the PAN
	 * @param String - PAN
	 * @return
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}

	private String pan;
	
	/*
	 * Method to get the date and time
	 * @param
	 * @return Date
	 */
	public Date getDateTime() {
		setDateTime(new Date());
		return dateTime;
	}

	/*
	 * Method to set the date and time
	 * @param Dae - date to be set
	 * @return
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	private Date dateTime;
	
	

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
	
	private String originalTransactionID;
	

	/**
	 * @return the originalTransactionID
	 */
	public String getOriginalTransactionID() {
		return originalTransactionID;
	}

	/**
	 * @param originalTransactionID the originalTransactionID to set
	 */
	public void setOriginalTransactionID(String originalTransactionID) {
		this.originalTransactionID = originalTransactionID;
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
			if (getPan() == null || ("".equals(getPan()))) {
				errors.add("app.pan", new ActionMessage("error.pan.required"));
			}
			else if (getTransactionID() == null || ("".equals(getTransactionID()))) {
				errors.add("app.transactionid", new ActionMessage("error.transactionid.required"));
			} else if (getTransactionID() == null || ("".equals(getTransactionID()))) {
					errors.add("app.originaltransactionid", new ActionMessage("error.originaltransactionid.required"));
			}else if(getPan().length()!=19){
				errors.add("app.pan", new ActionMessage("error.pan.length"));
			}else if(getPan().length()==19){
				try{
					int value = new BigInteger(getPan()).intValue();
				}catch(NumberFormatException ex){
					errors.add("app.pan", new ActionMessage("error.pan.invalid"));
				}
				
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
		setPan("");
		setTransactionID("");
		setOriginalTransactionID("");
	}
	
	/**
	 * Method to clear down the values
	 * @param
	 * @return
	 */
	public void clear(){
		if(getStatusCode()!=null && getStatusCode().equals("")){
			setPan("");
			setTransactionID("");
			setOriginalTransactionID("");
		}
	}
}
