/**
 * VMSException.java
 * Purpose: Exception class for VMS exception
 *
 * @author ramasap1
 */
package com.centrica.vms.exception;
/**
 * VMS exception
 * @see Exception
 */
public class VMSException extends Exception {

	private int intErrorNo;
	
	public VMSException() {

	}
	
	VMSException(String message) {
		super(message);
	}
	
	public String toString() {
		 return "ApplicationException["+intErrorNo+"]";

	}
}
