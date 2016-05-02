/**
 * MeterDetailsForm.java
 * Purpose: Meter details request form
 * @author ramasap1
 */
package com.centrica.vms.form;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Methods for Meter details request form
 * @see VMSWebForm
 */
public class MeterDetailsForm extends VMSWebForm {

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

	private String msn;
	
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

	private String fuelType;

	/*
	 * Method to get the fule type
	 * @param 
	 * @return String
	 */
	public String getFuelType() {
		return fuelType;
	}

	/*
	 * Method to set the fuel type
	 * @param String - fuel type
	 * @return
	 * 
	 */
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
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
			if (getMsn() == null || ("".equals(getMsn()))) {
				errors.add("app.msn", new ActionMessage("error.msn.required"));
			}
		} else if (getDispatch().equals("save")
				|| getDispatch().equals("update")) {
			if(getMsn()==null || getMsn().equals("")){
				errors.add("app.msn", new ActionMessage("error.msn.required"));
			}else{
				if (getFuelType() == null || ("0".equals(getFuelType()))) {
					errors.add("app.fueltype", new ActionMessage(
							"error.fueltype.required"));
				}
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
		setMsn("");
	}
}
