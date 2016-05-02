/**
 * FunctionDetails.java
 * Purpose: Function details model
 * @author ramasap1
 */
package com.centrica.vms.model;

import java.io.Serializable;
/**
 * Methods for Function details
 * @see Serializable
 */
public class FunctionDetails implements Serializable{
	
	/*
	 * Method to get the function code
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
	private void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/*
	 * Method to get the function description
	 * @param
	 * @return String
	 */
	public String getFunctionDescription() {
		return functionDescription;
	}

	/*
	 * Method to set the function description
	 * @param String - function description
	 * @return
	 */
	private void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}

	private String functionDescription;
	
	private String functionCode;
	
}
