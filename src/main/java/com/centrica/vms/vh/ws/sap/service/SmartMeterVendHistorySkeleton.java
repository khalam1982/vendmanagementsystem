
/**
 * SmartMeterVendHistorySkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
    package com.centrica.vms.vh.ws.sap.service;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

import com.centrica.vms.ws.service.VMSServiceImpl;

    /**
     *  SmartMeterVendHistorySkeleton java skeleton for the axisService
     */
    public class SmartMeterVendHistorySkeleton implements SmartMeterVendHistorySkeletonInterface{
    	
    	private Logger logger = ESAPI.getLogger(getClass().getName());
         
        		/**
        		 * Method to invoke the local service implemented to get the vend history
        		 */
                 public com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse VendHistory(
                  com.centrica.vms.vh.ws.sap.service.GetVendHistory getVendHistory0)
                 {
                	 logger.debug(Logger.EVENT_SUCCESS,"Entering VendHistory method");
                	 VMSServiceImpl vmsServiceImpl = new VMSServiceImpl();
                	 GetVendHistoryResponse getVendHistoryResponse = vmsServiceImpl.getVendHistory(getVendHistory0);
                	 logger.debug(Logger.EVENT_SUCCESS,"Leaving VendHistory method");
                	 return getVendHistoryResponse;
                 }
                 
          
    }
    
