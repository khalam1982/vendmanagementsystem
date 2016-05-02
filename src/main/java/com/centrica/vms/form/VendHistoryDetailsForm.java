/**
 * VendHistoryDetailsForm.java
 * Purpose: Vend History details form
 * @author nagarajk
 */
package com.centrica.vms.form;

import java.math.BigInteger;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.centrica.vms.model.VendHistoryDetails;

/**
 * Methods for Vend History details form
 * @see VMSWebForm
 */
public class VendHistoryDetailsForm extends VMSWebForm {

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
	
	private ArrayList<VendHistoryDetails> vendHistoryDetails;

	/*
	 * Method to get the Vend history details
	 * @param
	 * @return ArrayList<VendHistoryDetails>
	 */
	public ArrayList<VendHistoryDetails> getVendHistoryDetails() {
		return vendHistoryDetails;
	}

	/*
	 * Method to set the Vend history details
	 * @param ArrayList<VendHistoryDetails>
	 * @return
	 */
	public void setVendHistoryDetails(
			ArrayList<VendHistoryDetails> vendHistoryDetails) {
		this.vendHistoryDetails = vendHistoryDetails;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if(getDispatch()!=null && getDispatch().equals("detailPage")){
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
