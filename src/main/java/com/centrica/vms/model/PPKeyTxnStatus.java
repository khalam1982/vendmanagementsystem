package com.centrica.vms.model;

import java.util.Date;

/**
 * PPKeyTxnStatus - Value Object
 * Holds PPKeyTransaction Status Information
 * 
 * @author chackram
 */
public class PPKeyTxnStatus {

	private Integer ppkStatusId;
	private Integer status;
	private Date updatedDate;
	private StatusDetails statusDetails;

	/**
	 * Returns PPK Status Id
	 * @return ppkStatusId
	 */
	public Integer getPpkStatusId() {
		return ppkStatusId;
	}

	/**
	 * Sets PPK Status Id
	 * @param ppkStatusId the ppkStatusId to set
	 */
	public void setPpkStatusId(final Integer ppkStatusId) {
		this.ppkStatusId = ppkStatusId;
	}

	/**
	 * Returns Status
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets Status
	 * @param status the status to set
	 */
	public void setStatus(final Integer status) {
		this.status = status;
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
	 * Returns Status Details
	 * @return statusDetails
	 */
	public StatusDetails getStatusDetails() {
		return statusDetails;
	}

	/**
	 * Sets Status Details
	 * @param statusDetails the statusDetails to set
	 */
	public void setStatusDetails(final StatusDetails statusDetails) {
		this.statusDetails = statusDetails;
	}

}
