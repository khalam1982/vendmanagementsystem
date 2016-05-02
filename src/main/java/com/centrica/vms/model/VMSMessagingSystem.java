/**
 * VMSMessagingSystem.java
 * Purpose: VMS Messaging system
 * @author nagarajk
 */
package com.centrica.vms.model;

/**
 * Methods for VMS Messaging system
 */
public class VMSMessagingSystem {
	
	private String message;
	private Integer messageID;
	
	/*
	 * Method to get the message ID
	 * @param
	 * @return java.lang.Integer
	 */
	@SuppressWarnings("unused")
	private Integer getMessageID() {
		return messageID;
	}

	/*
	 * Method to set the message ID
	 * @param java.lang.Integer - message ID
	 * @return
	 */
	@SuppressWarnings("unused")
	private void setMessageID(Integer messageID) {
		this.messageID = messageID;
	}

	/*
	 * Method to get message data
	 * @param
	 * @return java.lang.String
	 */
	public String getMessageData() {
		return message;
	}

	/*
	 * Method to set message data
	 * @param java.lang.String - message data
	 * @return
	 */
	public void setMessageData(String message) {
		this.message = message;
	}

	private int deviceTypeID;

	public int getDeviceTypeID() {
		return deviceTypeID;
	}

	public void setDeviceTypeID(int deviceTypeID) {
		this.deviceTypeID = deviceTypeID;
	}
}