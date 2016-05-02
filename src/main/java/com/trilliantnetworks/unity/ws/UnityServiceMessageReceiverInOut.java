
/**
 * UnityServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package com.trilliantnetworks.unity.ws;

        /**
        *  UnityServiceMessageReceiverInOut message receiver
        */

        public class UnityServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        UnityServiceSkeletonInterface skel = (UnityServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("logout".equals(methodName)){
                
                com.trilliantnetworks.unity.ws.LogoutResponse logoutResponse7 = null;
	                        com.trilliantnetworks.unity.ws.Logout wrappedParam =
                                                             (com.trilliantnetworks.unity.ws.Logout)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.trilliantnetworks.unity.ws.Logout.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               logoutResponse7 =
                                                   
                                                   
                                                         skel.logout(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), logoutResponse7, false);
                                    } else 

            if("applyVendCode".equals(methodName)){
                
                com.trilliantnetworks.unity.ws.ApplyVendCodeResponse applyVendCodeResponse9 = null;
	                        com.trilliantnetworks.unity.ws.ApplyVendCodeRequest wrappedParam =
                                                             (com.trilliantnetworks.unity.ws.ApplyVendCodeRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.trilliantnetworks.unity.ws.ApplyVendCodeRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               applyVendCodeResponse9 =
                                                   
                                                   
                                                         skel.applyVendCode(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), applyVendCodeResponse9, false);
                                    } else 

            if("login".equals(methodName)){
                
                com.trilliantnetworks.unity.ws.LoginResponse loginResponse11 = null;
	                        com.trilliantnetworks.unity.ws.Login wrappedParam =
                                                             (com.trilliantnetworks.unity.ws.Login)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.trilliantnetworks.unity.ws.Login.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               loginResponse11 =
                                                   
                                                   
                                                         skel.login(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), loginResponse11, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        } catch (UnexpectedErrorFault e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"UnexpectedErrorFault");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
         catch (LoginFault e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"LoginFault");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
         catch (AccessDeniedFault e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"AccessDeniedFault");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
        
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.Logout param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.Logout.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.LogoutResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.LogoutResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.fault.AccessDeniedFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.fault.AccessDeniedFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.ApplyVendCodeRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.ApplyVendCodeRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.ApplyVendCodeResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.ApplyVendCodeResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.fault.LoginFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.fault.LoginFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.Login param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.Login.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.trilliantnetworks.unity.ws.LoginResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.trilliantnetworks.unity.ws.LoginResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.trilliantnetworks.unity.ws.LogoutResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.trilliantnetworks.unity.ws.LogoutResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.trilliantnetworks.unity.ws.LogoutResponse wraplogout(){
                                com.trilliantnetworks.unity.ws.LogoutResponse wrappedElement = new com.trilliantnetworks.unity.ws.LogoutResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.trilliantnetworks.unity.ws.ApplyVendCodeResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.trilliantnetworks.unity.ws.ApplyVendCodeResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.trilliantnetworks.unity.ws.ApplyVendCodeResponse wrapapplyVendCode(){
                                com.trilliantnetworks.unity.ws.ApplyVendCodeResponse wrappedElement = new com.trilliantnetworks.unity.ws.ApplyVendCodeResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.trilliantnetworks.unity.ws.LoginResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.trilliantnetworks.unity.ws.LoginResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.trilliantnetworks.unity.ws.LoginResponse wraplogin(){
                                com.trilliantnetworks.unity.ws.LoginResponse wrappedElement = new com.trilliantnetworks.unity.ws.LoginResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (com.trilliantnetworks.unity.ws.Logout.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.Logout.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.LogoutResponse.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.LogoutResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.AccessDeniedFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.AccessDeniedFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.ApplyVendCodeRequest.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.ApplyVendCodeRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.ApplyVendCodeResponse.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.ApplyVendCodeResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.AccessDeniedFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.AccessDeniedFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.LoginFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.LoginFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.Login.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.Login.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.LoginResponse.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.LoginResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.AccessDeniedFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.AccessDeniedFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.trilliantnetworks.unity.ws.fault.LoginFault.class.equals(type)){
                
                           return com.trilliantnetworks.unity.ws.fault.LoginFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    
