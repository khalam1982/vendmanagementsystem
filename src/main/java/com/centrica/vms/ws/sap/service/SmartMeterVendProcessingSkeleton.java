/**
 * SmartMeterVendProcessingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
package com.centrica.vms.ws.sap.service;

import com.centrica.vms.ws.service.VMSServiceImpl;

/**
 * SmartMeterVendProcessingSkeleton java skeleton for the axisService
 */
public class SmartMeterVendProcessingSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param createVend
	 */

	public com.centrica.vms.ws.sap.service.CreateVendResponse CreateVend(
			com.centrica.vms.ws.sap.service.CreateVend createVend) {
		VMSSAPService vmsSAPService = new VMSServiceImpl();
		CreateVendResponse createVendResponse = vmsSAPService
				.generatePaymentCode(createVend);
		return createVendResponse;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param voidVend
	 */

	public com.centrica.vms.ws.sap.service.VoidVendResponse VoidVend(
			com.centrica.vms.ws.sap.service.VoidVend voidVend) {
		VMSSAPService vmsSAPService = new VMSServiceImpl();
		VoidVendResponse voidVendResponse = vmsSAPService.voidPayment(voidVend);
		return voidVendResponse;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param reverseVend
	 */

	public com.centrica.vms.ws.sap.service.ReverseVendResponse ReverseVend(
			com.centrica.vms.ws.sap.service.ReverseVend reverseVend) {
		VMSSAPService vmsSAPService = new VMSServiceImpl();
		ReverseVendResponse reverseVendResponse = vmsSAPService.reversePayment(reverseVend);
		return reverseVendResponse;
	}

}
