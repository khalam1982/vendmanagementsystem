
/**
 * LoginFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

package com.centrica.vms.ws.headend.ack.service;

public class LoginFault extends java.lang.Exception{
    
    private com.centrica.vms.ws.headend.ack.fault.LoginFault faultMessage;
    
    public LoginFault() {
        super("LoginFault");
    }
           
    public LoginFault(java.lang.String s) {
       super(s);
    }
    
    public LoginFault(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.centrica.vms.ws.headend.ack.fault.LoginFault msg){
       faultMessage = msg;
    }
    
    public com.centrica.vms.ws.headend.ack.fault.LoginFault getFaultMessage(){
       return faultMessage;
    }
}
    