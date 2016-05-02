/**
 * VMSWebForm.java
 * Purpose: VMS web form
 * @author ramasap1
 */
package com.centrica.vms.form;

import org.apache.struts.action.ActionForm;
/**
 * Methods for VMS web form
 * @see ActionForm
 */
public class VMSWebForm extends ActionForm {
	
	private String dispatch;

	/*
	 * Method to get the dispatch
	 * @param
	 * @return String
	 */
	public String getDispatch() {
		return dispatch;
	}

	/*
	 * Method to set the dispatch
	 * @param String - dispatch
	 * @return
	 */
	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}
}
