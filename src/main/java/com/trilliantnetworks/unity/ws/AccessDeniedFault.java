
/**
 * AccessDeniedFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

package com.trilliantnetworks.unity.ws;

public class AccessDeniedFault extends java.lang.Exception{
    
    private com.trilliantnetworks.unity.ws.fault.AccessDeniedFault faultMessage;
    
    public AccessDeniedFault() {
        super("AccessDeniedFault");
    }
           
    public AccessDeniedFault(java.lang.String s) {
       super(s);
    }
    
    public AccessDeniedFault(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.trilliantnetworks.unity.ws.fault.AccessDeniedFault msg){
       faultMessage = msg;
    }
    
    public com.trilliantnetworks.unity.ws.fault.AccessDeniedFault getFaultMessage(){
       return faultMessage;
    }
}
    