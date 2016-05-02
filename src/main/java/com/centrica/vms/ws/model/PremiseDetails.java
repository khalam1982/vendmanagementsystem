/**
 * 
 */
package com.centrica.vms.ws.model;

import java.util.Date;




/**
 * @author nagarajk
 *
 */
public class PremiseDetails {
	
	private String mpxn;

	private String msn;
	
	public String getMpxn() {
		return mpxn;
	}
	
	public void setMpxn(String mpxn) {
		this.mpxn = mpxn;
	}

	public String getMSN() {
		return msn;
	}

	public void setMSN(String msn) {
		this.msn = msn;
	}
	
	private String createdBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	private Date createdTimestamp;

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	private String updatedBy;

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	private Date updatedTimestamp;

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	
}
