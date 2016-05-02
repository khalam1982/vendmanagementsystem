
/**
 * SmartMeterPPKeyServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
package com.centrica.vms.ws.ppk.service;

import com.centrica.vms.ws.service.PPKeyService;
import com.centrica.vms.ws.service.PPKeyServiceImpl;

/**
 *  SmartMeterPPKeyServiceSkeleton java skeleton for the axisService
 */
public class SmartMeterPPKeyServiceSkeleton{


	/**
	 * Auto generated method signature
	 * 
	 * @param updatePPKeyRequest
	 * @throws BusinessProcessingFaultException0 : 
	 */
	public com.centrica.vms.ws.ppk.service.UpdatePPKeyResponse UpdatePPKey(final com.centrica.vms.ws.ppk.service.UpdatePPKeyRequest updatePPKeyRequest) throws BusinessProcessingFaultException0{

		final PPKeyService ppkService = new PPKeyServiceImpl();
		return ppkService.updatePPKey(updatePPKeyRequest);

	}

}
