/**
 * GroupDetails.java
 * Purpose: Group details model
 * @author ramasap1
 */
package com.centrica.vms.model;

import java.io.Serializable;
/**
 * Methods for group details
 * @see Serializable
 */
public class GroupDetails implements Serializable{
	
	private String groupID;
	
	/*
	 * Method to get the group ID
	 * @param
	 * @return String
	 */
	public String getGroupID() {
		return groupID;
	}

	/*
	 * Method to set the group ID
	 * @param String - group ID
	 * @return
	 */
	private void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	/*
	 * Method to get the group description
	 * @param
	 * @return String
	 */
	public String getGroupDescription() {
		return groupDescription;
	}

	/*
	 * Method to set the group description
	 * @param String - group description
	 * @return
	 */
	private void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	private String groupDescription;

}
