
/**
 * SmartMeterVendProcessingMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package com.centrica.vms.ws.sap.service;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

        /**
        *  SmartMeterVendProcessingMessageReceiverInOut message receiver
        */

        public class SmartMeterVendProcessingMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


         
        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        //VMS - Start
        Logger logger = ESAPI.getLogger(this.getClass());
        logger.info(Logger.EVENT_UNSPECIFIED,"SOAP request : " + msgContext.getEnvelope());
        //VMS - end
        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        SmartMeterVendProcessingSkeleton skel = (SmartMeterVendProcessingSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("CreateVend".equals(methodName)){
                
                com.centrica.vms.ws.sap.service.CreateVendResponse createVendResponse1 = null;
	                        com.centrica.vms.ws.sap.service.CreateVend wrappedParam =
                                                             (com.centrica.vms.ws.sap.service.CreateVend)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.centrica.vms.ws.sap.service.CreateVend.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               createVendResponse1 =
                                                   
                                                   
                                                         skel.CreateVend(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), createVendResponse1, false);
                                    } else 

            if("VoidVend".equals(methodName)){
                
                com.centrica.vms.ws.sap.service.VoidVendResponse voidVendResponse3 = null;
	                        com.centrica.vms.ws.sap.service.VoidVend wrappedParam =
                                                             (com.centrica.vms.ws.sap.service.VoidVend)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.centrica.vms.ws.sap.service.VoidVend.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               voidVendResponse3 =
                                                   
                                                   
                                                         skel.VoidVend(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), voidVendResponse3, false);
                                    } else 

            if("ReverseVend".equals(methodName)){
                
                com.centrica.vms.ws.sap.service.ReverseVendResponse reverseVendResponse5 = null;
	                        com.centrica.vms.ws.sap.service.ReverseVend wrappedParam =
                                                             (com.centrica.vms.ws.sap.service.ReverseVend)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.centrica.vms.ws.sap.service.ReverseVend.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               reverseVendResponse5 =
                                                   
                                                   
                                                         skel.ReverseVend(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), reverseVendResponse5, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
         //VMS - Start
        logger.info(Logger.EVENT_UNSPECIFIED,"SOAP response : " + envelope);
        //VMS - end 
        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.sap.service.CreateVend param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.sap.service.CreateVend.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.sap.service.CreateVendResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.sap.service.CreateVendResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.sap.service.VoidVend param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.sap.service.VoidVend.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.sap.service.VoidVendResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.sap.service.VoidVendResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.sap.service.ReverseVend param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.sap.service.ReverseVend.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.sap.service.ReverseVendResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.sap.service.ReverseVendResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.centrica.vms.ws.sap.service.CreateVendResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.centrica.vms.ws.sap.service.CreateVendResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.centrica.vms.ws.sap.service.CreateVendResponse wrapCreateVend(){
                                com.centrica.vms.ws.sap.service.CreateVendResponse wrappedElement = new com.centrica.vms.ws.sap.service.CreateVendResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.centrica.vms.ws.sap.service.VoidVendResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.centrica.vms.ws.sap.service.VoidVendResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.centrica.vms.ws.sap.service.VoidVendResponse wrapVoidVend(){
                                com.centrica.vms.ws.sap.service.VoidVendResponse wrappedElement = new com.centrica.vms.ws.sap.service.VoidVendResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.centrica.vms.ws.sap.service.ReverseVendResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.centrica.vms.ws.sap.service.ReverseVendResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.centrica.vms.ws.sap.service.ReverseVendResponse wrapReverseVend(){
                                com.centrica.vms.ws.sap.service.ReverseVendResponse wrappedElement = new com.centrica.vms.ws.sap.service.ReverseVendResponse();
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
        
                if (com.centrica.vms.ws.sap.service.CreateVend.class.equals(type)){
                
                           return com.centrica.vms.ws.sap.service.CreateVend.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.ws.sap.service.CreateVendResponse.class.equals(type)){
                
                           return com.centrica.vms.ws.sap.service.CreateVendResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.ws.sap.service.VoidVend.class.equals(type)){
                
                           return com.centrica.vms.ws.sap.service.VoidVend.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.ws.sap.service.VoidVendResponse.class.equals(type)){
                
                           return com.centrica.vms.ws.sap.service.VoidVendResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.ws.sap.service.ReverseVend.class.equals(type)){
                
                           return com.centrica.vms.ws.sap.service.ReverseVend.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.ws.sap.service.ReverseVendResponse.class.equals(type)){
                
                           return com.centrica.vms.ws.sap.service.ReverseVendResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    
