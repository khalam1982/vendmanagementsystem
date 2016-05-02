/**
 * 
 */
package com.centrica.vms.ws.service;

/**
 * @author nagarajk
 *
 */
interface IVMSFaultService  {
	
	String msnNotFound = "MSN NOT FOUND";
	
	String unknownError = "UNKNOWN ERROR";
	
	String invalidHoldingPeriod = "INVALID HOLDING PERIOD";
	
	String invalidSource = "INVALID SOURCE";
	
	String invalidPan = "INVALID PAN NUMBER";

	String invalidTransactionId = "INVALID VEND REQUEST KEY";
	
	String invalidMSN = "INVALID MSN";
	
	String dbErrorMessage = "DATABASE EXCEPTION";
	
	String lgErrorMessage = "L+G Exception";
	
	String invalidLicenceMessage = "L+G LICENCE NOT VALID EXCEPTION";
	
	String invalidEPaymentKeyMessage = "L+G INVALID EPAYMENT KEY EXCEPTION";
	
	String invalidEPaymentOutOfRangeMessage = "L+G INVALID EPAYMENT OUT OF RANGE EXCEPTION";
	
	String invalidBreakupMessage = "L+G BREAKUP EXCEPTION";
	
	String manuTypeNotFound = "Manufacturer Type not found";
	
	String deviceTypeNotFound = "Device Type not found";
	
	String panNotFound = "PAN not found";
	
	String mpxnNotFound = "MPxN not found";
	
	String exceedsMaxVend = "EXCEEDS MAX VEND FOR SOURCE";

	String vendTxnSuspended = "VEND TRANSACTION SUSPENDED FOR METER";

}
