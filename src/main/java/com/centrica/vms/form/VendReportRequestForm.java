/**
 * VendReportRequestForm.java
 * Purpose: HEAD END utility request form
 * @author nagarajk
 */
package com.centrica.vms.form;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.centrica.vms.model.StatusDetails;
/**
 * Methods for Vend report utility request form
 * @see UtilityRequestForm
 */
@SuppressWarnings("serial")
public class VendReportRequestForm extends VMSWebForm {

	private String startDate = null;
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	private String endDate = null;
	
	
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	private String menuStartHour = null;

	/**
	 * @return the menuStartHour
	 */
	public String getMenuStartHour() {
		return menuStartHour;
	}

	/**
	 * @param menuStartHour the menuStartHour to set
	 */
	public void setMenuStartHour(String menuStartHour) {
		this.menuStartHour = menuStartHour;
	}

	private String menuStartMinute = null;
	

	/**
	 * @return the menuStartMinute
	 */
	public String getMenuStartMinute() {
		return menuStartMinute;
	}

	/**
	 * @param menuStartMinute the menuStartMinute to set
	 */
	public void setMenuStartMinute(String menuStartMinute) {
		this.menuStartMinute = menuStartMinute;
	}

	private String menuStartMerid = null;
	

	/**
	 * @return the menuStartMerid
	 */
	public String getMenuStartMerid() {
		return menuStartMerid;
	}

	/**
	 * @param menuStartMerid the menuStartMerid to set
	 */
	public void setMenuStartMerid(String menuStartMerid) {
		this.menuStartMerid = menuStartMerid;
	}

	private String menuEndHour = null;
	
	

	/**
	 * @return the menuEndHour
	 */
	public String getMenuEndHour() {
		return menuEndHour;
	}

	/**
	 * @param menuEndHour the menuEndHour to set
	 */
	public void setMenuEndHour(String menuEndHour) {
		this.menuEndHour = menuEndHour;
	}

	private String menuEndMinute = null;
	

	/**
	 * @return the menuEndMinute
	 */
	public String getMenuEndMinute() {
		return menuEndMinute;
	}

	/**
	 * @param menuEndMinute the menuEndMinute to set
	 */
	public void setMenuEndMinute(String menuEndMinute) {
		this.menuEndMinute = menuEndMinute;
	}

	private String menuEndMerid = null;
	

	/**
	 * @return the menuEndMerid
	 */
	public String getMenuEndMerid() {
		return menuEndMerid;
	}

	/**
	 * @param menuEndMerid the menuEndMerid to set
	 */
	public void setMenuEndMerid(String menuEndMerid) {
		this.menuEndMerid = menuEndMerid;
	}
	
	private String selectedSource = null;
	
	
	/**
	 * @return the selectedSource
	 */
	public String getSelectedSource() {
		return selectedSource;
	}

	/**
	 * @param selectedSource the selectedSource to set
	 */
	public void setSelectedSource(String selectedSource) {
		this.selectedSource = selectedSource;
	}
	
	private String selectedStatus = null;

	/**
	 * @return the selectedStatus
	 */
	public String getSelectedStatus() {
		return selectedStatus;
	}

	/**
	 * @param selectedStatus the selectedStatus to set
	 */
	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}
	
	public List<StatusDetails> statusDetailsList = null;

	/**
	 * @return the statusDetailsList
	 */
	public List<StatusDetails> getStatusDetailsList() {
		return statusDetailsList;
	}

	/**
	 * @param statusDetailsList the statusDetailsList to set
	 */
	public void setStatusDetailsList(List<StatusDetails> statusDetailsList) {
		this.statusDetailsList = statusDetailsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.centrica.vms.form.UtilityRequestForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if(getDispatch()!=null && getDispatch().equals("submit")){
			if (getStartDate() == null || ("".equals(getStartDate()))) {
				errors.add("app.startdate", new ActionMessage("error.startdate.required"));
			} else if (getEndDate() == null || ("".equals(getEndDate()))) {
				errors.add("app.enddate", new ActionMessage("error.enddate.required"));
			} else if (getMenuStartHour() == null || ("".equals(getMenuStartHour()))) {
				errors.add("app.time", new ActionMessage("error.time.required"));
			} else if (getMenuStartMinute() == null || ("".equals(getMenuStartMinute()))) {
				errors.add("app.time", new ActionMessage("error.time.required"));
			} else if (getMenuStartMerid() == null || ("".equals(getMenuStartMerid()))) {
				errors.add("app.time", new ActionMessage("error.time.required"));
			} else if (getMenuEndHour() == null || ("".equals(getMenuEndHour()))) {
				errors.add("app.time", new ActionMessage("error.time.required"));
			} else if (getMenuEndMinute() == null || ("".equals(getMenuEndMinute()))) {
				errors.add("app.time", new ActionMessage("error.time.required"));
			} else if (getMenuEndMerid() == null || ("".equals(getMenuEndMerid()))) {
				errors.add("app.time", new ActionMessage("error.time.required"));
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
		setStartDate("");
		setEndDate("");
		setSelectedSource("");
		setSelectedStatus("");
	}
	
	/**
	 * Method to clear down the values
	 * @param
	 * @return
	 */
	public void clear(){
		setStartDate("");
		setEndDate("");
		setSelectedSource("");
		setSelectedStatus("");
	}
}
