/**
 * VendRequestDetailsForm.java
 * Purpose: Vend request details form
 * @author ramasap1
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
public class VendRequestDetailsForm extends VMSWebForm {

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
	
	private String creditValue;
	
	/*
	 * Method to get the credit value
	 * @param
	 * @return String
	 */
	public String getCreditValue() {
		return creditValue;
	}

	/*
	 * Method to set the credit value
	 * @param String - credit value
	 * @return
	 */
	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
	}

	/*
	 * Method to get the holding period
	 * @param
	 * @return String
	 */
	public String getHoldingPeriod() {
		return holdingPeriod;
	}

	/*
	 * Method to set the holding period
	 * @param String - holding period
	 * @return 
	 */
	public void setHoldingPeriod(String holdingPeriod) {
		this.holdingPeriod = holdingPeriod;
	}

	/*
	 * Method to get the payment channel
	 * @param 
	 * @return String
	 */
	public String getPayChannel() {
		return payChannel;
	}

	/*
	 * Method to set the payment channel
	 * @param String - payment channel
	 * @return
	 */
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	/*
	 * Method to get the credit type
	 * @param
	 * @return String
	 */
	public String getCreditType() {
		return creditType;
	}

	/*
	 * Method to set the credit type
	 * @param String - credit type
	 * @return
	 */
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

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

	private String holdingPeriod;
	
	private String payChannel;
	
	private String creditType;
	
	private Date dateTime;
	
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
	 * Method to get the vend code
	 * @param
	 * @return String
	 */
	public String getVendCode() {
		return vendCode;
	}

	/*
	 * Method to set the vend code
	 * @param String - vend code
	 * @return
	 */
	public void setVendCode(String vendCode) {
		this.vendCode = vendCode;
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
	
	private String vendCode;
	
	private String transactionID;

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
			else if (getCreditValue() == null || ("".equals(getCreditValue()))) {
				errors.add("app.creditvalue", new ActionMessage("error.creditvalue.required"));
			}else if(getCreditValue().contains(".")){
				errors.add("app.creditvalue", new ActionMessage("error.creditvalue.invalid"));
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
		setCreditValue("");
	}
	
	/**
	 * Method to clear down the values
	 * @param
	 * @return
	 */
	public void clear(){
		if(getStatusCode()!=null && getStatusCode().equals("")){
			setPan("");
			setCreditValue("");
			setHoldingPeriod("");
			setPayChannel("");
			setCreditType("");
			setDateTime(null);
			setStatusCode("");
			setDescription("");
			setVendCode("");
			setTransactionID("");
		}
	}
}
