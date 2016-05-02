package com.centrica.vms.ws.model;

import java.io.Serializable;

public class CreditIDCompKey implements Serializable{
	private String msn;
	private String transactionType;


	public CreditIDCompKey() {
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}