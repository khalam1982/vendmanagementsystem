/**
 * VMSSAPService.java
 * Purpose : Interface provided to the sap web service
 * @author ramasap1
 * @version 1.0
 */
package com.centrica.vms.ws.sap.service;

import com.centrica.vms.vh.ws.sap.service.GetVendHistory;
import com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse;
import com.centrica.vms.ws.pi.service.AsynActivationandDeactivation;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDelivery;
import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDeliveryResponse;


public interface VMSSAPService {

	/**
	 * Method to generate the payment code for the request from SAP PI
	 * @param createRequest
	 * @return
	 */
	public CreateVendResponse generatePaymentCode(CreateVend createRequest);
	
	/**
	 * Method to reverse the payment for the request from SAP PI 
	 * @param reverseRequest
	 * @return
	 */
	public ReverseVendResponse reversePayment(ReverseVend reverseRequest);

	
	/**
	 * Method to void the payment for the request from SAP PI.
	 * @param voidRequest
	 * @return
	 */
	public VoidVendResponse voidPayment(VoidVend voidRequest);
	
	
	/**
	 * Method used to get the vend history
	 * @param vendHistoryRequest
	 * @return
	 */
	public GetVendHistoryResponse getVendHistory(GetVendHistory vendHistoryRequest);
	
	/**
	 * Method used to handle SCN registration and de-registration
	 * @param asynActivationandDeactivation
	 */
	public void handleSCNRegistrationService(AsynActivationandDeactivation asynActivationandDeactivation);

	/**
	 * @param acknowledgePaymentDelivery0
	 * @return
	 */
	public AcknowledgePaymentDeliveryResponse acknowledgeAdjustResponse(AcknowledgePaymentDelivery acknowledgePaymentDelivery0);
	
}
