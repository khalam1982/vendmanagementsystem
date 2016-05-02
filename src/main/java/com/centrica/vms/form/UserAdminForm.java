/**
 * UserAdminForm.java
 * Purpose: User admin form
 * @author ramasap1
 */
package com.centrica.vms.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 * Methods for User admin form
 * @see PowerUserAdminForm
 */
public class UserAdminForm extends PowerUserAdminForm {
	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.form.PowerUserAdminForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}
	
}
