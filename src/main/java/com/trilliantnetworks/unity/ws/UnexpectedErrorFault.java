
/**
 * UnexpectedErrorFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

package com.trilliantnetworks.unity.ws;

public class UnexpectedErrorFault extends java.lang.Exception{
    
    private com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault faultMessage;
    
    public UnexpectedErrorFault() {
        super("UnexpectedErrorFault");
    }
           
    public UnexpectedErrorFault(java.lang.String s) {
       super(s);
    }
    
    public UnexpectedErrorFault(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault msg){
       faultMessage = msg;
    }
    
    public com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault getFaultMessage(){
       return faultMessage;
    }
}
    