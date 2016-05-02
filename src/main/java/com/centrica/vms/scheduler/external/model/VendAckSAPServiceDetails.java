/**
 * VendServiceDetails.java
 * Purpose: vend service details
 * @author nagarajk
 */
package com.centrica.vms.scheduler.external.model;

/**
 * Method for vend service details
 */
public class VendAckSAPServiceDetails {

	private String url;
	private int retryCount;
	private String retryPeriod;
	private String timeout;
	private String userName;
	private String password;

	/*
	 * Method to get the URL
	 * 
	 * @param
	 * 
	 * @return java.lang.String
	 */
	public String getUrl() {
		return url;
	}

	/*
	 * Method to set the URL
	 * 
	 * @param java.lang.String - URL
	 * 
	 * @return
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/*
	 * Method to get the retry period
	 * 
	 * @param
	 * 
	 * @return java.lang.String
	 */
	public String getRetryPeriod() {
		return retryPeriod;
	}

	/*
	 * Method to get the retry period
	 * 
	 * @param int - retry number
	 * 
	 * @return java.lang.String
	 */
	public String getRetryPeriod(int retryNo) {
		String[] retryPeriods = retryPeriod.split(",");
		return retryPeriods[retryNo];
	}

	/*
	 * Method to set the retry period
	 * 
	 * @param java.lang.String - rety period
	 * 
	 * @return
	 */
	public void setRetryPeriod(String retryPeriod) {
		this.retryPeriod = retryPeriod;
	}

	/*
	 * Method to get the number of retries
	 * 
	 * @param
	 * 
	 * @return int
	 */
	public int getNoofretries() {
		return retryCount;
	}

	/*
	 * Method to set the number of retries
	 * 
	 * @param int - retry count
	 * 
	 * @return
	 */
	public void setNoofretries(int retryCount) {
		this.retryCount = retryCount;
	}

	/*
	 * Method to get the timeout value
	 * 
	 * @param
	 * 
	 * @return java.lang.String
	 */
	public String getTimeout() {
		return timeout;
	}

	/*
	 * Method to set the timeout value
	 * 
	 * @param java.lang.String - time out value
	 * 
	 * @return
	 */
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	private String pdTimeout;

	/**
	 * 
	 * Method used to set the premise details timeout period
	 * 
	 * @return
	 */

	public String getPdTimeout() {

		return pdTimeout;

	}

	/**
	 * 
	 * Method used to set the premise details timeout period
	 * 
	 * @param pdTimeout
	 */

	public void setPdTimeout(String pdTimeout) {

		this.pdTimeout = pdTimeout;

	}
	/*
	 * Method to get the user name
	 * @param
	 * @return java.lang.String
	 */
	public String getUserName() {
		return userName;
	}

	/*
	 * Method to set the user name
	 * @param java.lang.String
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * Method to get the password
	 * @param
	 * @return java.lang.String
	 */
	public String getPassword() {
		return password;
	}

	/*
	 * Method to set the password
	 * @param java.lang.String - password
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}


}
