/**
 * 
 */
package com.centrica.vms.ws.model;

import java.util.Date;

/**
 * @author ramasap1
 *
 */
public class MeterDetails {
	
	public String getPan() {
		return pan;
	}
	
	public void setPan(String pan) {
		this.pan = pan;
	}
	private String pan;

	private String prepayKey;

	private String msn;
	
	private Integer vendTxnStatus;
	
	public Integer getVendTxnStatus() {
		return vendTxnStatus;
	}

	public void setVendTxnStatus(final Integer vendTxnStatus) {
		this.vendTxnStatus = vendTxnStatus;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getPrepayKey() {
		return prepayKey;
	}

	public void setPrepayKey(String prepayKey) {
		this.prepayKey = prepayKey;
	}
	
	private int fuelTypeID;

	public int getFuelTypeID() {
		return fuelTypeID;
	}

	public void setFuelTypeID(int fuelTypeID) {
		this.fuelTypeID = fuelTypeID;
	}
	
	private int deviceTypeID;

	public int getDeviceTypeID() {
		return deviceTypeID;
	}

	public void setDeviceTypeID(int deviceTypeID) {
		this.deviceTypeID = deviceTypeID;
	}
	
	private int manuTypeID;

	public int getManuTypeID() {
		return manuTypeID;
	}

	public void setManuTypeID(int manuTypeID) {
		this.manuTypeID = manuTypeID;
	}
	
	private String createdByID;

	public String getCreatedByID() {
		return createdByID;
	}

	public void setCreatedByID(String createdByID) {
		this.createdByID = createdByID;
	}

	private Date createdDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	private String lastUpdatedByID;

	public String getLastUpdatedByID() {
		return lastUpdatedByID;
	}

	public void setLastUpdatedByID(String lastUpdatedByID) {
		this.lastUpdatedByID = lastUpdatedByID;
	}

	private Date lastUpdatedDate;

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
}
