package com.centrica.vms.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Form bean used in Source Details Page
 */
public class SourceDetailsForm extends VMSWebForm {

	private static final long serialVersionUID = 5702093703792860376L;

	private static final String PROCESS_SUBMIT = "processSubmit";
	private static final String APP_VENDLIMIT = "app.vendlimit";
	private static final String APP_SOURCE = "app.source";
	private static final String ERROR_SOURCE_VENDLIMIT = "error.source.vendlimit";
	private static final String ERROR_SOURCE_SELECT = "error.source.select";

	private String vendLimit;
	private String selectedSource;

	/**
	 * Validates input request
	 * 
	 * @param mapping - ActionMapping
	 * @param request - HttpServletRequest
	 * @return ActionErrors
	 */
	@Override
	public ActionErrors validate(final ActionMapping mapping, final HttpServletRequest request) {

		final ActionErrors errors = new ActionErrors();
		if( getDispatch() != null && getDispatch().equals(PROCESS_SUBMIT)) {
			if( selectedSource == null || "-1".equalsIgnoreCase(selectedSource) ) {
				errors.add(APP_SOURCE, new ActionMessage(ERROR_SOURCE_SELECT));
			}
			if( vendLimit == null || "".equalsIgnoreCase(vendLimit) ) {
				errors.add(APP_VENDLIMIT, new ActionMessage(ERROR_SOURCE_VENDLIMIT));
			} else {
				try {
					final int maxVend = Integer.parseInt(vendLimit);
					if( maxVend < 0 ) {
						errors.add(APP_VENDLIMIT, new ActionMessage(ERROR_SOURCE_VENDLIMIT));
					}
				} catch( NumberFormatException e) {
					errors.add(APP_VENDLIMIT, new ActionMessage(ERROR_SOURCE_VENDLIMIT));
				}
			}
		}
		return errors;

	}

	/**
	 * Resets data
	 * 
	 * @param mapping - ActionMapping
	 * @param request - HttpServletRequest
	 */
	@Override
	public void reset(final ActionMapping mapping, final HttpServletRequest request) {
		setSelectedSource("");
		setVendLimit("");
	}

	/**
	 * Returns Selected Source
	 * @return String
	 */
	public String getSelectedSource() {
		return selectedSource;
	}

	/**
	 * Sets Selected Source
	 * @param selectedSource of type String
	 */
	public void setSelectedSource(final String selectedSource) {
		this.selectedSource = selectedSource;
	}

	/**
	 * Returns vendLimit
	 * @return String
	 */
	public String getVendLimit() {
		return vendLimit;
	}

	/**
	 * Sets Vend Limit
	 * @param vendLimit of type String
	 */
	public void setVendLimit(final String vendLimit) {
		this.vendLimit = vendLimit;
	}

}
