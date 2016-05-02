package com.centrica.vms.exception;
/**
 * Exception used to tell whether invalid  transactionID is found
 * @see DBException
 */
public class VMSInvalidTransactionIdException extends VMSAppException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4601921326909222503L;

	public VMSInvalidTransactionIdException() {
		super();
	}
}
