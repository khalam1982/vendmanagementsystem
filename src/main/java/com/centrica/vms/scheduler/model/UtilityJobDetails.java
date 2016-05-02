/**
 * UtilityJobDetails.java
 * Purpose: Utility Job details
 * @author ramasap1
 */
package com.centrica.vms.scheduler.model;
/**
 * Methods for Utility Job details 
 */
public class UtilityJobDetails {
	
	private String count;
	
	private String userName;
	
	/*
	 * Method to get the count
	 * @param
	 * @return String
	 */
	public String getCount() {
		return count;
	}

	/*
	 * Method to set the count
	 * @param String count
	 * @return
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/*
	 * Method to get the user name
	 * @param
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/*
	 * Method to set the user name
	 * @param String user name
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
