package com.centrica.vms.scheduler.model;

public class VendAckJobDetails extends JobDetails {
	String msn;
	Integer status;
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
