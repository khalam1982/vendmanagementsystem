/**
 * 
 */
package com.centrica.vms.scheduler.external.model;

/**
 * @author nagarajk
 *
 */
public class SCNActvServiceDetails {

	private String scnActvJobTime;
	private String url;
	private String userName = "";
	
	/**
	 * Method used to get the username
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Method used to set the user name
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Method used to get the password
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method used to set the password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	private String password="";

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the scnActvJobTime
	 */
	public String getScnActvJobTime() {
		return scnActvJobTime;
	}

	/**
	 * @param scnActvJobTime the scnActvJobTime to set
	 */
	public void setScnActvJobTime(String scnActvJobTime) {
		this.scnActvJobTime = scnActvJobTime;
	}
	
}
