

/**
 * SmartMeterManageSCNInServiceMessageReceiverInOnly.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package smartmeterprocessing.vend.uk.co.britishgas;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

        /**
        *  SmartMeterManageSCNInServiceMessageReceiverInOnly message receiver
        */

        public class SmartMeterManageSCNInServiceMessageReceiverInOnly extends org.apache.axis2.receivers.AbstractInMessageReceiver{

        private Logger logger = ESAPI.getLogger(getClass().getName());
        
        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext inMessage) throws org.apache.axis2.AxisFault{

        try {

        	 logger.info(Logger.EVENT_UNSPECIFIED,"SOAP request : " + inMessage.getEnvelope());
        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(inMessage);

        SmartMeterManageSCNInServiceSkeletonInterface skel = (SmartMeterManageSCNInServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = inMessage.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        
            if("SmartMeterManageSCNIn".equals(methodName)){
            
            com.centrica.vms.ws.pi.service.AsynActivationandDeactivation wrappedParam = (com.centrica.vms.ws.pi.service.AsynActivationandDeactivation)fromOM(
                                                        inMessage.getEnvelope().getBody().getFirstElement(),
                                                        com.centrica.vms.ws.pi.service.AsynActivationandDeactivation.class,
                                                        getEnvelopeNamespaces(inMessage.getEnvelope()));
                                            
                                                     skel.SmartMeterManageSCNIn(wrappedParam);
                                                
                } else {
                  throw new java.lang.RuntimeException("method not found");
                }
            

        }
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }


        
        //
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.pi.service.AsynActivationandDeactivation param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.pi.service.AsynActivationandDeactivation.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

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
        
                if (com.centrica.vms.ws.pi.service.AsynActivationandDeactivation.class.equals(type)){
                
                           return com.centrica.vms.ws.pi.service.AsynActivationandDeactivation.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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



        }//end of class

    
