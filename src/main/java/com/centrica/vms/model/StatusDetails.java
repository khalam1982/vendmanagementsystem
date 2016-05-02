/**
 * StatusDetails.java
 * Purpose: Status details model
 * @author ramasap1
 */
package com.centrica.vms.model;

/**
 * Methods for status details
 */
public class StatusDetails {
	
	
	private int statusCode;
	
	/*
	 * Method to get the status code
	 * @param
	 * @return int
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/*
	 * Method to set the status code
	 * @param int - status code
	 * @return
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/*
	 * Method to get the status description
	 * @param
	 * @return String
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/*
	 * Method to set the status description
	 * @param String - status description
	 * @return
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	private String statusDesc;

}
