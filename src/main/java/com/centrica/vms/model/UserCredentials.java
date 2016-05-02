/**
 * UserCredentials.java
 * Purpose: User credentials model
 * @author ramasap1
 */
package com.centrica.vms.model;

import java.util.Date;
/**
 * Methods for User credentials
 */
public class UserCredentials {
	
	private String userName;
	
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
	 * @param String - user name
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * Method to get the password
	 * @param
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * Method to set the password
	 * @param String - password
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * Method to get the user ID
	 * @param
	 * @return Integer
	 */
	public Integer getUserID() {
		return userID;
	}

	/*
	 * Method to set the user ID
	 * @param Integer - user ID
	 * @return
	 */
	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	/*
	 * Method to get the version
	 * @param
	 * @return java.lang.Integer
	 */
	public Integer getVersion() {
		return version;
	}

	/*
	 * Method to set the version
	 * @param java.lang.Integer - version
	 * @return 
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	private String password;
	
	private Integer userID;
	
	private Integer version;
	
	private Date passwordDate;
	
	/*
	 * Method to get the password date
	 * @param
	 * @return java.util.Date
	 */
	public Date getPasswordDate() {
		return passwordDate;
	}

	/*
	 * Method to set the password date
	 * @param java.util.Date - date
	 * @return
	 */
	public void setPasswordDate(Date passwordDate) {
		this.passwordDate = passwordDate;
	}

	/*
	 * Method to get the update by string
	 * @param
	 * @return java.lang.String
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/*
	 * Method to set the update by string
	 * @param java.lang.String - update by
	 * @return
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/*
	 * Method to get the updated time stamp
	 * @param
	 * @return java.util.Date
	 */
	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	/*
	 * Method to set the updated tiem stamp
	 * @param java.util.Date - updated time stamp
	 * @return
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	private String updatedBy;
	
	private Date updatedTimestamp;

}
