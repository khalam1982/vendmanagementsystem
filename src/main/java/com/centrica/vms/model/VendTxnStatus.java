/**
 * VendTxnStatus.java
 * Purpose: Vend Transaction Status
 * @author ramasap1
 */

package com.centrica.vms.model;

import java.util.Date;

import com.centrica.vms.model.StatusDetails;

public class VendTxnStatus {

	private Integer vendStatusTxnsHisID;
	
	private Integer status;
	
	private Date updatedTimeStamp;
	
	private StatusDetails statusDetails;
	
	
	/**
	 * Method used to get the status details
	 * @return
	 */
	public StatusDetails getStatusDetails() {
		return statusDetails;
	}

	/**
	 * Method used to set the status details
	 * @param statusDetails
	 */
	public void setStatusDetails(StatusDetails statusDetails) {
		this.statusDetails = statusDetails;
	}

	/**
	 * Method to get the vend transaction status  
	 * @return
	 */
	public Integer getVendStatusTxnsHisID() {
		return vendStatusTxnsHisID;
	}

	/**
	 * Method used to set the vend status transaction history id
	 * @param vendStatusTxnsHisID
	 */
	public void setVendStatusTxnsHisID(Integer vendStatusTxnsHisID) {
		this.vendStatusTxnsHisID = vendStatusTxnsHisID;
	}

	/**
	 * Method used to get the transaction status 
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Method used to set the transaction status
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Method used to get the updated timestamp
	 * @return
	 */
	public Date getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	/**
	 * Method used to set the updated timestamp.
	 * @param updatedTimeStamp
	 */
	public void setUpdatedTimeStamp(Date updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
	
}
