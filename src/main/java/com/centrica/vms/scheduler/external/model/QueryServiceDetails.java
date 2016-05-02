package com.centrica.vms.scheduler.external.model;

public class QueryServiceDetails {

	private String password;
	private String username;
	private String timeout;
	private String usernameAmi;
	private String passwordAmi;
	private String separator;
	private String hostName;
	private String portNumber;
	private String hostNameAmi;
	private String portNumberAmi;
	private String label;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "QueryServiceDetails [timeout=" + timeout + ", hostName="
				+ hostName + ", portNumber=" + portNumber + ", hostNameAmi="
				+ hostNameAmi + ", portNumberAmi=" + portNumberAmi + ", label="
				+ label + "]";
	}
	
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getUsernameAmi() {
		return usernameAmi;
	}
	public void setUsernameAmi(String usernameAmi) {
		this.usernameAmi = usernameAmi;
	}
	public String getPasswordAmi() {
		return passwordAmi;
	}
	public void setPasswordAmi(String passwordAmi) {
		this.passwordAmi = passwordAmi;
	}
	public String getSeparator() {
		return separator;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public String getHostNameAmi() {
		return hostNameAmi;
	}
	public void setHostNameAmi(String hostNameAmi) {
		this.hostNameAmi = hostNameAmi;
	}
	public String getPortNumberAmi() {
		return portNumberAmi;
	}
	public void setPortNumberAmi(String portNumberAmi) {
		this.portNumberAmi = portNumberAmi;
	}
	public String getHostName(boolean isAmi) {
		if (isAmi) {
			return this.getHostNameAmi();
		}
		return this.getHostName();
	}
	public String getPortNumber(boolean isAmi) {
		if (isAmi) {
			return this.getPortNumberAmi();
		}
		return this.getPortNumber();
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
