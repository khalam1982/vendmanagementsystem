/**
 * VendReportDetails.java
 * Purpose : Class invoked from the web service skeleton class
 * @author nagarajk
 * @version 1.0
 */
package com.centrica.vms.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;




/**
 * Methods for vend report details
 * @see VendTransaction
 */
public class VendReportDetails extends VendTransaction {
	
	private String triggerName = null;

	private String vendCode;
    
	private String transactionType;
	
	private String creditValue;
	
	private String pan;
	
	private SourceDetails sourceDetails;
	
	private String msn;
	
	private Long holdingPeriod;
	
	/**
	 * Method used to get MSN
	 * @return
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * Method used to set the MSN
	 * @param msn
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	/**
	 * Method used to get the holding period
	 * @param msn
	 */
	public Long getHoldingPeriod() {
		return holdingPeriod;
	}

	/**
	 * Method used to set the holding period
	 * @param holdingPeriod
	 */
	public void setHoldingPeriod(Long holdingPeriod) {
		this.holdingPeriod = holdingPeriod;
	}

	/*
	 * Method to get the source details
	 * @param
	 * @return SourceDetails
	 */
	public SourceDetails getSourceDetails() {
		return sourceDetails;
	}

	/*
	 * Method to set the source details
	 * @param SourceDetails
	 * @return
	 */
	public void setSourceDetails(SourceDetails sourceDetails) {
		this.sourceDetails = sourceDetails;
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
	 * @param String - pan
	 * @return
	 */
	public void setPan(String pan) {
		this.pan = pan;
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
	 * @param String - credit value
	 * @return
	 */
	public void setCreditValue(String creditValue) {
		this.creditValue = creditValue;
	}


	/**
	 * Method to get the vend code
	 * @param
	 * @return String
	 */
	public String getVendCode() {
		return vendCode;
	}



	/**
	 * Method to set the vend code
	 * @param String - the vendCode to set
	 * @return
	 */
	public void setVendCode(String vendCode) {
		this.vendCode = vendCode;
	}

	/**
	 * Method to get the transaction type
	 * @param
	 * @return String 
	 */
	public String getTransactionType() {
		return this.transactionType;
	}



	/**
	 * Method to set the transaction type
	 * @param String  - the vendType to set
	 * @return
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * Method for the purpose of displaying
	 * the current time (BST/GMT) in VMS GUI.
	 * @param
	 * @return java.lang.String
	 */
	public String getVendTimeStamp() {
		Date actualTimeStamp = super.getActualTimeStamp();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		dateFormatter.setTimeZone(TimeZone.getTimeZone("Europe/London"));
		return dateFormatter.format(actualTimeStamp).toString();
	}
	
	/**
	 * @return the triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}

	/**
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	
}
