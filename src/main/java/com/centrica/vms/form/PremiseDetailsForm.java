/**
 * PremiseDetailsForm.java
 * Purpose: Premise details request form
 * @author nagarajk
 */
package com.centrica.vms.form;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Methods for Premise details request form
 * @see VMSWebForm
 */
@SuppressWarnings("serial")
public class PremiseDetailsForm extends VMSWebForm {

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
	 * Method to get the MSN
	 * @param
	 * @return String
	 */
	public String getMsn() {
		return msn;
	}

	/* 
	 * Method to set the MSN
	 * @param String - MSN
	 * @return
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	private String msn;

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if (getDispatch().equals("detailPage")) {
			if (getMpxn() == null || ("".equals(getMpxn()))) {
				errors.add("app.mpxn", new ActionMessage("error.mpxn.required"));
			}else {
				try{
					int value = new BigInteger(getMpxn()).intValue();
				}catch(NumberFormatException ex){
					errors.add("app.mpxn", new ActionMessage("error.mpxn.invalid"));
				}
				
			}
		} else if (getDispatch().equals("save")
				|| getDispatch().equals("update")) {
			if (getMsn() == null || ("".equals(getMsn()))) {
				errors.add("app.msn", new ActionMessage(
						"error.msn.required"));
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
		setMpxn("");
	}
}
