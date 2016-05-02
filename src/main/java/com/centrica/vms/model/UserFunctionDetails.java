/**
 * UserFunctionDetails.java
 * Purpose: User function details
 * @author ramasap1
 */
package com.centrica.vms.model;


/**
 * Methods for user function details
 */
public class UserFunctionDetails {

	
	/*
	 * Method to get the group ID
	 * @param
	 * @return String
	 */
	private String getGroupID() {
		return groupID;
	}

	/*
	 * Method to set group ID
	 * @param String - groupID
	 * @return
	 */
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}


	/*
	 * Method to get the function details
	 * @param
	 * @return FunctionDetails
	 */
	public FunctionDetails getFunctionDetails() {
		return functionDetails;
	}

	/*
	 * Method to set the function details
	 * @param FunctionDetails
	 * @return
	 */
	private void setFunctionDetails(FunctionDetails functionDetails) {
		this.functionDetails = functionDetails;
	}
	

	/*
	 * Method to get the function code
	 * @param
	 * @return String
	 */
	private String getFunctionCode() {
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
	
	private int id;
	
	/*
	 * Method to get the ID
	 * @param
	 * @return int
	 */
	private int getId() {
		return id;
	}

	/*
	 * Method to set the ID
	 * @param int - ID
	 * @return
	 */
	private void setId(int id) {
		this.id = id;
	}

	private String groupID;
	
	private FunctionDetails functionDetails;
	
	private String functionCode;

	

}
