package com.centrica.vms.scheduler.external.model;

public class VendRetryConfig {
	
	private String retries;
	private String retryPeriod;
	private String eligibleStatus;
	
	public String getEligibleStatus() {
		return eligibleStatus;
	}
	public void setEligibleStatus(String eligibleStatus) {
		this.eligibleStatus = eligibleStatus;
	}
	public String getRetries() {
		return retries;
	}
	public void setRetries(String retries) {
		this.retries = retries;
	}
	public String getRetryPeriod() {
		return retryPeriod;
	}
	public void setRetryPeriod(String retryPeriod) {
		this.retryPeriod = retryPeriod;
	}

	@Override
	public String toString() {
		return " retries: " + this.retries + " retryPeriod: " + this.retryPeriod + " retryStatus: " + this.eligibleStatus;
	}
}
