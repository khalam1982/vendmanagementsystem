package com.centrica.vms.model;

import java.util.Date;
import java.util.Set;

/**
 * PPKeyTransaction - Value Object
 * Holds PPKeyTransaction Information
 * 
 * @author chackram
 */
public class PPKeyTransaction {

	private String transactionId;
	private String msn;
	private String mpxn;
	private String ppKey;
	private Date requestDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	private String headend;
	private Set<PPKeyTxnStatus> statusDetails;

	/**
	 * Returns Transaction Id
	 * @return transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets Transaction Id
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(final String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Returns MSN
	 * @return msn
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * Sets MSN
	 * @param msn the msn to set
	 */
	public void setMsn(final String msn) {
		this.msn = msn;
	}

	/**
	 * Returns MPXN
	 * @return mpxn
	 */
	public String getMpxn() {
		return mpxn;
	}

	/**
	 * Sets MPXN
	 * @param mpxn the mpxn to set
	 */
	public void setMpxn(final String mpxn) {
		this.mpxn = mpxn;
	}

	/**
	 * Returns PP Key
	 * @return ppKey
	 */
	public String getPpKey() {
		return ppKey;
	}

	/**
	 * Sets PP KEY
	 * @param ppKey the ppKey to set
	 */
	public void setPpKey(final String ppKey) {
		this.ppKey = ppKey;
	}

	/**
	 * Returns Request Date
	 * @return requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 * Sets Request Date
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * Returns Updated Date
	 * @return updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets Updated Date
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(final Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Returns Created By
	 * @return createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets CreatedBy
	 * @param CreatedBy the Created By to set
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Returns Updated By
	 * @return updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets Updated By
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Returns Status Details
	 * @return statusDetails
	 */
	public Set<PPKeyTxnStatus> getStatusDetails() {
		return statusDetails;
	}

	/**
	 * Sets TransactionId
	 * @param transactionId the transactionId to set
	 */
	public void setStatusDetails(final Set<PPKeyTxnStatus> statusDetails) {
		this.statusDetails = statusDetails;
	}

	public String getHeadend() {
		return headend;
	}

	public void setHeadend(String headend) {
		this.headend = headend;
	}

}
