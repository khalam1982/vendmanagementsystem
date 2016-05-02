package com.centrica.vms.ws.service;

import com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDelivery;
import com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDeliveryResponse;
import com.centrica.vms.ws.ppk.service.BusinessProcessingFaultException0;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyRequest;
import com.centrica.vms.ws.ppk.service.UpdatePPKeyResponse;

/**
 * PPKeyService
 * 
 * Interface to handle PP Key Update
 * 
 */
public interface PPKeyService {

	/**
	 * Updates PP Key
	 * 
	 * @param ppkRequest - UpdatePPKeyRequest
	 * @return UpdatePPKeyResponse
	 * @throws BusinessProcessingFaultException0
	 */
	UpdatePPKeyResponse updatePPKey(UpdatePPKeyRequest ppkRequest) throws BusinessProcessingFaultException0;

	/**
	 * Acknowledges PP Key Delivery from HE
	 * 
	 * @param acknowledgePPKeyDelivery
	 * @return AcknowledgePPKeyDeliveryResponse
	 */
	AcknowledgePPKeyDeliveryResponse acknowledgePPKeyDelivery(final AcknowledgePPKeyDelivery acknowledgePPKeyDelivery);

}
