
/**
 * BusinessProcessingFaultException0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

package com.centrica.vms.ws.ppk.service;

public class BusinessProcessingFaultException0 extends java.lang.Exception{
    
    private com.centrica.vms.ws.ppk.service.BusinessProcessingFault faultMessage;
    
    public BusinessProcessingFaultException0() {
        super("BusinessProcessingFaultException0");
    }
           
    public BusinessProcessingFaultException0(java.lang.String s) {
       super(s);
    }
    
    public BusinessProcessingFaultException0(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.centrica.vms.ws.ppk.service.BusinessProcessingFault msg){
       faultMessage = msg;
    }
    
    public com.centrica.vms.ws.ppk.service.BusinessProcessingFault getFaultMessage(){
       return faultMessage;
    }
}
    