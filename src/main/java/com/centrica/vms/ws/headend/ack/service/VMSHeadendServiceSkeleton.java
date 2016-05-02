
/**
 * VMSHeadendServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
    package com.centrica.vms.ws.headend.ack.service;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse;
import com.centrica.vms.ws.service.PPKeyService;
import com.centrica.vms.ws.service.PPKeyServiceImpl;
import com.centrica.vms.ws.service.VMSServiceImpl;
    /**
     *  VMSHeadendServiceSkeleton java skeleton for the axisService
     */
    public class VMSHeadendServiceSkeleton implements VMSHeadendServiceSkeletonInterface{
    	private Logger logger = ESAPI.getLogger(this.getClass());
         
        /**
         * Auto generated method signature
         * 
                                     * @param acknowledgePPKeyDelivery0
             * @throws LoginFault : 
         */
        
                 public com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDeliveryResponse acknowledgePPKeyDelivery
                  (
                  com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDelivery acknowledgePPKeyDelivery0
                  )
            throws LoginFault{
                	 logger.debug(Logger.EVENT_SUCCESS,"Entering acknowledgePaymentDelivery method");
             		final PPKeyService service = new PPKeyServiceImpl();
             		final AcknowledgePPKeyDeliveryResponse response = service.acknowledgePPKeyDelivery(acknowledgePPKeyDelivery0);
             		logger.debug(Logger.EVENT_SUCCESS,"Leaving acknowledgePaymentDelivery method");
             		return response;
        }
     
         
        /**
         * Auto generated method signature
         * 
                                     * @param acknowledgePaymentDelivery2
             * @throws LoginFault : 
         */
        
                 public com.centrica.vms.ws.headend.ack.service.AcknowledgePaymentDeliveryResponse acknowledgePaymentDelivery
                  (
                  com.centrica.vms.ws.headend.ack.service.AcknowledgePaymentDelivery acknowledgePaymentDelivery2
                  )
            throws LoginFault{
              		logger.debug(Logger.EVENT_SUCCESS,"Entering acknowledgePaymentDelivery method");
            		VMSHeadendService VMSHeadendService = new VMSServiceImpl();
            		DeliveryReceiptResponse deliveryReceiptResponse = VMSHeadendService.acknowledgePaymentDelivery(acknowledgePaymentDelivery2.getDeliveryReceipt());
            		AcknowledgePaymentDeliveryResponse acknowledgePaymentDeliveryResponse = new AcknowledgePaymentDeliveryResponse();
            		acknowledgePaymentDeliveryResponse.setDeliveryReceiptResponse(deliveryReceiptResponse);
            		logger.debug(Logger.EVENT_SUCCESS,"Leaving acknowledgePaymentDelivery method");
            		return acknowledgePaymentDeliveryResponse;
        }
     
    }
    
