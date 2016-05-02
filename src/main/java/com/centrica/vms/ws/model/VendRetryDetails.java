package com.centrica.vms.ws.model;

public class VendRetryDetails {

	private Integer retryCount;
	private String transactionId;
	
	public Integer getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return " retryCount: " + this.retryCount + " ; Transaction ID: " + this.transactionId; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((retryCount == null) ? 0 : retryCount.hashCode());
		result = prime * result
				+ ((transactionId == null) ? 0 : transactionId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendRetryDetails other = (VendRetryDetails) obj;
		if (retryCount == null) {
			if (other.retryCount != null)
				return false;
		} else if (!retryCount.equals(other.retryCount))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

}
