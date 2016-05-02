
/**
 * VMSHeadendServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
    package com.centrica.vms.ws.headend.ack.service;
    /**
     *  VMSHeadendServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface VMSHeadendServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * 
                                    * @param acknowledgePPKeyDelivery
             * @throws LoginFault : 
         */

        
                public com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDeliveryResponse acknowledgePPKeyDelivery
                (
                  com.centrica.vms.ws.headend.ack.service.AcknowledgePPKeyDelivery acknowledgePPKeyDelivery
                 )
            throws LoginFault;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param acknowledgePaymentDelivery
             * @throws LoginFault : 
         */

        
                public com.centrica.vms.ws.headend.ack.service.AcknowledgePaymentDeliveryResponse acknowledgePaymentDelivery
                (
                  com.centrica.vms.ws.headend.ack.service.AcknowledgePaymentDelivery acknowledgePaymentDelivery
                 )
            throws LoginFault;
        
         }
    