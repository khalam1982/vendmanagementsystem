package com.centrica.vms.model;

public enum ServiceId {
	APPLY_VEND_CODE("1",false), CREDIT_ADJUSTMENT("2",true), UPDATE_PPKEY("3",false);
	
	 private String serviceCode;
	private boolean ami;

	ServiceId(String serviceCode, boolean ami) {
		this.setAmi(ami);
		this.setServiceCode(serviceCode);
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public boolean isAmi() {
		return ami;
	}

	public void setAmi(boolean ami) {
		this.ami = ami;
	}
	
	
}
