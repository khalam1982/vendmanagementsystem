/**
 * UserDetails.java
 * Purpose: User details model
 * @author ramasap1
 */
package com.centrica.vms.model;

import java.util.Date;
import java.util.Set;
/**
 * Methods for User details
 */
public class UserDetails {
	
	private Integer version;
	
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
	private void setVersion(Integer version) {
		this.version = version;
	}
	
	/*
	 * Method to get the user ID
	 * @param
	 * @return java.lang.Integer
	 */
	private Integer getUserID() {
		return userID;
	}

	/*
	 * Method to set the user ID
	 * @param java.lang.Integer - user ID
	 * @return
	 */
	private void setUserID(Integer userID) {
		this.userID = userID;
	}

	/*
	 * Method to get the user name
	 * @param java.lang.String
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/*
	 * Method to set the user name
	 * @param java.lang.String - user name
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * Method to get the LAN ID
	 * @param
	 * @return java.lang.String
	 * 
	 */
	public String getLanID() {
		return lanID;
	}

	/*
	 * Method to set the LAN ID
	 * @param java.lang.String - lan ID
	 * @return
	 */
	public void setLanID(String lanID) {
		this.lanID = lanID;
	}

	/*
	 * Method to get the group ID
	 * @param
	 * @return java.lang.String
	 */
	public String getGroupID() {
		return groupID;
	}

	/*
	 * Method to set the group ID
	 * @param java.lang.String - group ID
	 * @return
	 */
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	/*
	 * Method to get the Power IND
	 * @param
	 * @return char
	 */
	public char getPowerIND() {
		return powerIND;
	}

	/*
	 * Method to set the power IND
	 * @param char - power indicator
	 * @return
	 */
	public void setPowerIND(char powerIND) {
		this.powerIND = powerIND;
	}

	/*
	 * Method to get the lock indicator
	 * @param
	 * @return char
	 * 
	 */
	public char getLockIND() {
		return lockIND;
	}

	/*
	 * Method to set the lock indicator
	 * @param char - lock indicator
	 * @return
	 */
	public void setLockIND(char lockIND) {
		this.lockIND = lockIND;
	}

	/*
	 * Method to get the super indicator
	 * @param
	 * @return char
	 */
	public char getSuperIND() {
		return superIND;
	}

	/*
	 * Method to set the super indicator
	 * @param char - super indicator
	 * @return 
	 */
	public void setSuperIND(char superIND) {
		this.superIND = superIND;
	}

	/*
	 * Method to get the retry count
	 * @param
	 * @return int
	 */
	public int getRetryCount() {
		return retryCount;
	}

	/*
	 * Method to set the retry count
	 * @param int - retry count
	 * @return
	 */
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	/*
	 * Method to get the created time stamp
	 * @param
	 * @return java.util.Date
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	/*
	 * Method to set the created time stamp
	 * @param java.util.Date - created time stamp
	 * @return
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
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
	 * Method to set the updated time stamp.
	 * @param java.util.Date - updated time stamp
	 * @return
	 */
	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

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
	 * @param java.util.Date - password Date
	 * @return
	 */
	public void setPasswordDate(Date passwordDate) {
		this.passwordDate = passwordDate;
	}

	/*
	 * Method to get the user function details
	 * @param
	 * @return Set<UserFunctionDetails>
	 */
	public Set<UserFunctionDetails> getUserFunctionDetails() {
		return userFunctionDetails;
	}

	/*
	 * Method to set the user function details
	 * @param Set<UserFunctionDetails>
	 * @return
	 */
	public void setUserFunctionDetails(Set<UserFunctionDetails> userFunctionDetails) {
		this.userFunctionDetails = userFunctionDetails;
	}
	
	/*
	 * Method to get the created by string
	 * @param
	 * @return String
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/*
	 * Method to set the created by string
	 * @param String - created by string
	 * @return
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/*
	 * Method to get the updated by string
	 * @param
	 * @return String
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/*
	 * Method to set the updated by string
	 * @param String - updated by string
	 * @return
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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
	
	private int passDueDate;

	/*
	 * Method to get the password due date
	 * @param
	 * @return int
	 */
	public int getPassDueDate() {
		return passDueDate;
	}

	/*
	 * Method to set the password due date
	 * @param int - password due date value
	 * @return
	 */
	public void setPassDueDate(int passDueDate) {
		this.passDueDate = passDueDate;
	}
	
	private Integer userID;
	
	private String userName;
	
	private String lanID;
	
	private String groupID;
	
	private char powerIND;
	
	private char lockIND;
	
	private char superIND;
	
	private int retryCount;
	
	private Date createdTimestamp;
	
	private Date updatedTimestamp;
	
	private Date passwordDate;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Set<UserFunctionDetails> userFunctionDetails;
	
	private String password;

    public enum Indicator{
		   
		   N('N'),Y('Y');
		   
		   private char indicator;
		   
		   private Indicator(char indicator){
			   this.indicator = indicator;
		   }
		   
		   public char getIndicator(){
			   return this.indicator;
		   }
	}
}
