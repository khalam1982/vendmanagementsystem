package com.centrica.vms.ws.model;

public class CreditIDDetails {
	
	private Integer creditID;
	
	private CreditIDCompKey creditIDCompKey;

	public CreditIDCompKey getCreditIDCompKey() {
		return creditIDCompKey;
	}

	public void setCreditIDCompKey(CreditIDCompKey creditIDCompKey) {
		this.creditIDCompKey = creditIDCompKey;
	}

	public Integer getCreditID() {
		return creditID;
	}

	public void setCreditID(Integer creditID) {
		this.creditID = creditID;
	}
}
