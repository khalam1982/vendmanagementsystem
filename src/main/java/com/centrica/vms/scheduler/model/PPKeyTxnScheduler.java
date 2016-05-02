package com.centrica.vms.scheduler.model;

import com.centrica.vms.model.PPKeyTransaction;

/**
 * PPKeyTxnScheduler - Value Object
 * Holds PPKeyTransaction Scheduler Information
 * 
 * @author chackram
 */
public class PPKeyTxnScheduler extends PPKeyTransaction {

	private String triggerName;

	/**
	 * Returns Trigger Name
	 * @return triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * Sets Trigger Name
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(final String triggerName) {
		this.triggerName = triggerName;
	}

}
