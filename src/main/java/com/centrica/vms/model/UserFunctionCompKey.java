/**
 * UserFunctionCompKey.java
 * Purpose: User function comp key
 * @author ramasap1
 */
package com.centrica.vms.model;

import java.io.Serializable;
/**
 * Methods for user function comp key
 * @see Serializable
 */
public class UserFunctionCompKey implements Serializable{
	private int userID;
	private String functionCode;

	/*
	 * Constructor
	 */
	public UserFunctionCompKey() {
	}

	/*
	 * Method to get user ID
	 * @param
	 * @return int
	 */
	public int getUserID() {
		return userID;
	}

	/*
	 * Method to set user ID
	 * @param int - user ID
	 * @return
	 * 
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/*
	 * Method to get function code
	 * @param
	 * @return String
	 */
	public String getFunctionCode() {
		return functionCode;
	}

	/*
	 * Method to set the function code
	 * @param String - function code
	 * @return
	 */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
}