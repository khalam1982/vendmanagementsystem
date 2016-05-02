/**
 * 
 */
package com.centrica.vms.ws.headend.ack.service;


/**
 * @author ramasap1
 *
 */
public interface VMSHeadendService {

	public com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse 
	acknowledgePaymentDelivery(com.centrica.vms.ws.headend.ack.model.DeliveryReceipt deliveryReceipt);
}
