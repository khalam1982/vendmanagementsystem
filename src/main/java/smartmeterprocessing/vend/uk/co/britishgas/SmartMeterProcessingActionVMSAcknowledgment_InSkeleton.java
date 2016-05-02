
/**
 * SmartMeterProcessingActionVMSAcknowledgment_InSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
    package smartmeterprocessing.vend.uk.co.britishgas;

import com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDeliveryResponse;
import com.centrica.vms.ws.sap.service.VMSSAPService;
import com.centrica.vms.ws.service.VMSServiceImpl;
    /**
     *  SmartMeterProcessingActionVMSAcknowledgment_InSkeleton java skeleton for the axisService
     */
    public class SmartMeterProcessingActionVMSAcknowledgment_InSkeleton implements SmartMeterProcessingActionVMSAcknowledgment_InSkeletonInterface{
        
         
        /**
         * Auto generated method signature
         * 
                                     * @param acknowledgePaymentDelivery0
         */
        
                 public com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDeliveryResponse HESAcknowledgment_Sync_In
                  (
                  com.centrica.vms.ws.sap.adjustment.service.AcknowledgePaymentDelivery acknowledgePaymentDelivery0
                  )
            {
                	 VMSSAPService vmsSAPService = new VMSServiceImpl();
                	 AcknowledgePaymentDeliveryResponse acknowledgePaymentDeliveryResponse = vmsSAPService.
                	 	acknowledgeAdjustResponse(acknowledgePaymentDelivery0);
                     return acknowledgePaymentDeliveryResponse;     
        }
     
    }
    