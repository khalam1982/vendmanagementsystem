/**
 * HEUtilityRequestForm.java
 * Purpose: HEAD END utility request form
 * @author nagarajk
 */
package com.centrica.vms.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
/**
 * Methods for HEAD END utility request form
 * @see UtilityRequestForm
 */
public class HEUtilityRequestForm extends UtilityRequestForm {

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.form.UtilityRequestForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}
}
