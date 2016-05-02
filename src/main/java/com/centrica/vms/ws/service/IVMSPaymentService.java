package com.centrica.vms.ws.service;

import com.centrica.vms.exception.VMSException;
import com.centrica.vms.ws.sap.service.CreateVend;
import com.centrica.vms.ws.sap.service.CreateVendResponse;

public interface IVMSPaymentService {
	
	/**
	 * Method used to handle the payment request
	 * @param createRequest
	 * @return
	 */
	public CreateVendResponse handlePaymentRequest(CreateVend createRequest,String createdBy,String updatedBy) throws VMSException;

}
