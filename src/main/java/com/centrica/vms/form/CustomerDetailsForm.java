/**
 * CustomerDetailsForm.java
 * Purpose: Customer details request form
 * @author ramasap1
 */
package com.centrica.vms.form;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Methods for Customer details request form
 * @see VMSWebForm
 */
public class CustomerDetailsForm extends VMSWebForm {

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
	 * @param String - type
	 * @return
	 */
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	private String mpxn;
	
	/*
	 * Method to get the mpxn
	 * @param
	 * @return String
	 */
	public String getMpxn() {
		return mpxn;
	}

	/*
	 * Method to set the mpxn
	 * @param String - mpxn
	 * @return
	 */
	public void setMpxn(String mpxn) {
		this.mpxn = mpxn;
	}

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

	private String validFrom;

	/**
	 * Method to get the valid from date
	 * @return
	 */
	public String getValidFrom() {
		return validFrom;
	}

	/**
	 * Method to set the valid from date
	 * @param validFrom
	 */
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	private String validTo;

	/**
	 * Method used to get the valid to
	 * @return
	 */
	public String getValidTo() {
		return validTo;
	}

	/**
	 * Method used to set the valid to
	 * @param validTo
	 */
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if (getDispatch().equals("detailPage")) {
			if (getPan() == null || ("".equals(getPan()))) {
				errors.add("app.pan", new ActionMessage("error.pan.required"));
			}else if(getPan().length()!=19){
				errors.add("app.pan", new ActionMessage("error.pan.length"));
			}else if(getPan().length()==19){
				try{
					int value = new BigInteger(getPan()).intValue();
				}catch(NumberFormatException ex){
					errors.add("app.pan", new ActionMessage("error.pan.invalid"));
				}
				
			}
		} else if (getDispatch().equals("save")
				|| getDispatch().equals("update")) {
			if (getValidFrom() == null || ("".equals(getValidFrom()))) {
				errors.add("app.validfrom", new ActionMessage(
						"error.validfrom.required"));
			}else if(getValidFrom().length()!=10){
				errors.add("app.validfrom", new ActionMessage(
				"error.validfrom.format"));
			}else if (getValidTo() == null || ("".equals(getValidTo()))) {
				errors.add("app.validto", new ActionMessage(
				"error.validto.required"));
            }else if(getValidTo().length()!=10){
				errors.add("app.validto", new ActionMessage(
				"error.validto.format"));
			}else if(getMpxn()==null || getMpxn().equals("")){
				errors.add("app.mpxn", new ActionMessage("error.mpxn.required"));
			}
			request.setAttribute("OperationType", getDispatch());
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
	}
}
