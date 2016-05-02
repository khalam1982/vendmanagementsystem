
/**
 * SmartMeterVendHistoryMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package com.centrica.vms.vh.ws.sap.service;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;

        /**
        *  SmartMeterVendHistoryMessageReceiverInOut message receiver
        */

        public class SmartMeterVendHistoryMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

    	 //VMS - Start
        Logger logger = ESAPI.getLogger(this.getClass());
        logger.info(Logger.EVENT_UNSPECIFIED,"Vend History SOAP request : " + msgContext.getEnvelope());
        //VMS - end
        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        SmartMeterVendHistorySkeletonInterface skel = (SmartMeterVendHistorySkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("VendHistory".equals(methodName)){
                
                com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse getVendHistoryResponse3 = null;
	                        com.centrica.vms.vh.ws.sap.service.GetVendHistory wrappedParam =
                                                             (com.centrica.vms.vh.ws.sap.service.GetVendHistory)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.centrica.vms.vh.ws.sap.service.GetVendHistory.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getVendHistoryResponse3 =
                                                   
                                                   
                                                         skel.VendHistory(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getVendHistoryResponse3, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        
        //VMS - Start
        logger.info(Logger.EVENT_UNSPECIFIED,"Vend History SOAP response : " + envelope);
        //VMS - end 
        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.vh.ws.sap.service.GetVendHistory param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.vh.ws.sap.service.GetVendHistory.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse wrapVendHistory(){
                                com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse wrappedElement = new com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse();
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
        
                if (com.centrica.vms.vh.ws.sap.service.GetVendHistory.class.equals(type)){
                
                           return com.centrica.vms.vh.ws.sap.service.GetVendHistory.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse.class.equals(type)){
                
                           return com.centrica.vms.vh.ws.sap.service.GetVendHistoryResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    