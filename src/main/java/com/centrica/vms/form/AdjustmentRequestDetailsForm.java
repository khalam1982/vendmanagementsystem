/**
 * AdjustmentRequestDetailsForm.java
 * Purpose: Adjustment request details forms
 * @author nagarajk
 */
package com.centrica.vms.form;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Methods for Adjustment request details forms
 * @see VendRequestDetailsForm
 * 
 */
public class AdjustmentRequestDetailsForm extends VendRequestDetailsForm {

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.form.VendRequestDetailsForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
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
			}else if(getCreditValue().contains(".") &&
					(!getCreditValue().substring(getCreditValue().length()-3).contains(".")
					|| getCreditValue().substring(getCreditValue().length()-1).contains("."))){
				errors.add("app.creditvalue", new ActionMessage("error.creditvalue.invalid"));
			} else if ((int)(new Float(getCreditValue()).floatValue() * 100) > 9999 
					|| (int)(new Float(getCreditValue()).floatValue() * 100) < -9999) {
				errors.add("app.creditvalue", new ActionMessage("error.creditrange.invalid"));
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
	 * @see com.centrica.vms.form.VendRequestDetailsForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
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
