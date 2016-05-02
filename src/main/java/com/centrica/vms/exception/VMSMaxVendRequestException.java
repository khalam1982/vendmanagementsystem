package com.centrica.vms.exception;

/**
 * VMSMaxVendRequestException - Exception class
 * thrown when the number of transactions exceeds the
 * allowed limit for the source
 *
 */
public class VMSMaxVendRequestException extends VMSException {

	private static final long serialVersionUID = 2308492533223267909L;

	/**
	 * Constructor
	 */
	public VMSMaxVendRequestException() {
		super();
	}

}
