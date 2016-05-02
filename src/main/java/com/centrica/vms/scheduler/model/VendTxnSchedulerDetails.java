/**
 * VendTxnSchedulerDetails.java
 * Purpose: Vend transaction scheduler details
 * @author ramasap1
 */
package com.centrica.vms.scheduler.model;

import com.centrica.vms.model.VendTransaction;



/**
 * Methods for Vend transaction scheduler details
 * @see VendTransaction
 */
public class VendTxnSchedulerDetails extends VendTransaction  {

	private String triggerName;
	
	/*
	 * Method to get the trigger name
	 * @param
	 * @return String
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/*
	 * Method to set the trigger name
	 * @param String trigger name
	 * @return
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
}
