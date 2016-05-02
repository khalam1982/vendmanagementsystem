
/**
 * LoginFault.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

package com.trilliantnetworks.unity.ws;

public class LoginFault extends java.lang.Exception{
    
    private com.trilliantnetworks.unity.ws.fault.LoginFault faultMessage;
    
    public LoginFault() {
        super("LoginFault");
    }
           
    public LoginFault(java.lang.String s) {
       super(s);
    }
    
    public LoginFault(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(com.trilliantnetworks.unity.ws.fault.LoginFault msg){
       faultMessage = msg;
    }
    
    public com.trilliantnetworks.unity.ws.fault.LoginFault getFaultMessage(){
       return faultMessage;
    }
}
    