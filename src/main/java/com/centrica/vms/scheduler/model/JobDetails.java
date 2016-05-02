/**
 * JobDetails.java
 * Purpose: VMS Job details
 * @author ramasap1
 */
package com.centrica.vms.scheduler.model;

import java.util.Date;

import com.centrica.vms.common.DeviceTypeEnum;



/**
 * Methods for VMS Job details 
 */
public class JobDetails {
	
	private String pan;
	
	private String transactionID;
	
	private String vendCode;
	
	private String paymentType;
	
	private String creditValue;
	
	private Date timestamp;
	
	private int retryCount = 0;
	
	private String mpxn = null;
	
	private String msn;
	
	private Integer deviceType = DeviceTypeEnum.PH2B.getDeviceType();
	
	private Integer status;
	
	private String ppKey;
	
	/**
	 * @return the deviceType
	 */
	public Integer getDeviceType() {
		return deviceType;
	}


	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}


	/*
	 * Method to get the timestamp
	 * @param
	 * @return Date
	 */
	public Date getTimestamp() {
		return timestamp;
	}


	/*
	 * Method to set the time stamp
	 * @param Date time stamp
	 * @return
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/*
	 * Method to get the credit value
	 * @param
	 * @return String
	 */
	public String getCreditValue() {
		return creditValue;
	}

	/*
	 * Method to set the credit value
	 * @param String credit value
	 * @return
	 */
	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
	}

	/*
	 * Method to get the transaction ID
	 * @param
	 * @return String
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/*
	 * Method to set the transaction ID
	 * @param String transaction ID
	 * @return
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
	/*
	 * Method to get the PAN
	 * @param
	 * @return String
	 */
	public String getPan() {
		return pan;
	}

	/*
	 * Method to set the PAN
	 * @param String pan
	 * @return
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}

	
	
	/**
	 * Method to get the vend code
	 * @param
	 * @return String the vendCode
	 */
	public String getVendCode() {
		return vendCode;
	}
	/**
	 * Method to set the vend code
	 * @param String the vendCode to set
	 * @return
	 */
	public void setVendCode(String vendCode) {
		this.vendCode = vendCode;
	}
	
	

	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	
	/**
	 * @return the mpxn
	 */
	public String getMpxn() {
		return mpxn;
	}

	/**
	 * @param mpxn the mpxn to set
	 */
	public void setMpxn(String mpxn) {
		this.mpxn = mpxn;
	}

	/**
	 * @return the msn
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * @param msn the msn to set
	 */
	public void setMsn(final String msn) {
		this.msn = msn;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(final Integer status) {
		this.status = status;
	}


	public String getPpKey() {
		return ppKey;
	}


	public void setPpKey(String ppKey) {
		this.ppKey = ppKey;
	}

}
