
/**
 * VendTransactionQueryServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package com.centrica.vms.ws.transaction.query;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

        /**
        *  VendTransactionQueryServiceMessageReceiverInOut message receiver
        */

        public class VendTransactionQueryServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {
        	
        //Added to do the validation - VMS start
        Logger logger = ESAPI.getLogger(this.getClass());
        logger.info(Logger.EVENT_UNSPECIFIED,"SOAP request : " + msgContext.getEnvelope());
        //new AuthenticationService().authenticateRequest(msgContext);
        //Added to do the validation - VMS end

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        VendTransactionQueryServiceSkeleton skel = (VendTransactionQueryServiceSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("GetTransactions".equals(methodName)){
                
                com.centrica.vms.ws.transaction.query.GetTransactionsResponse getTransactionsResponse1 = null;
	                        com.centrica.vms.ws.transaction.query.GetTransactions wrappedParam =
                                                             (com.centrica.vms.ws.transaction.query.GetTransactions)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    com.centrica.vms.ws.transaction.query.GetTransactions.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getTransactionsResponse1 =
                                                   
                                                   
                                                         skel.GetTransactions(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getTransactionsResponse1, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        } catch (BusinessProcessingFaultException0 e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"BusinessProcessingFault");
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
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.transaction.query.GetTransactions param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.transaction.query.GetTransactions.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.transaction.query.GetTransactionsResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.transaction.query.GetTransactionsResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.transaction.query.BusinessProcessingFault param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.transaction.query.BusinessProcessingFault.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.centrica.vms.ws.transaction.query.GetTransactionsResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(com.centrica.vms.ws.transaction.query.GetTransactionsResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private com.centrica.vms.ws.transaction.query.GetTransactionsResponse wrapGetTransactions(){
                                com.centrica.vms.ws.transaction.query.GetTransactionsResponse wrappedElement = new com.centrica.vms.ws.transaction.query.GetTransactionsResponse();
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
        
                if (com.centrica.vms.ws.transaction.query.GetTransactions.class.equals(type)){
                
                           return com.centrica.vms.ws.transaction.query.GetTransactions.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.ws.transaction.query.GetTransactionsResponse.class.equals(type)){
                
                           return com.centrica.vms.ws.transaction.query.GetTransactionsResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (com.centrica.vms.ws.transaction.query.BusinessProcessingFault.class.equals(type)){
                
                           return com.centrica.vms.ws.transaction.query.BusinessProcessingFault.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    