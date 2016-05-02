
/**
 * SmartMeterProcessingActionVendAcknowledgment_OutServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package smartmeterprocessing.vend.uk.co.britishgas;

        /**
        *  SmartMeterProcessingActionVendAcknowledgment_OutServiceMessageReceiverInOut message receiver
        */

        public class SmartMeterProcessingActionVendAcknowledgment_OutServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        SmartMeterProcessingActionVendAcknowledgment_OutServiceSkeletonInterface skel = (SmartMeterProcessingActionVendAcknowledgment_OutServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJava(op.getName().getLocalPart())) != null)){

        

            if("VMS_VendAcknowledgment_Sync_Out".equals(methodName)){
                
                smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse vendAcknowlegmentResponse3 = null;
	                        smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest wrappedParam =
                                                             (smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               vendAcknowlegmentResponse3 =
                                                   
                                                   
                                                         skel.VMS_VendAcknowledgment_Sync_Out(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), vendAcknowlegmentResponse3, false);
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse param, boolean optimizeContent)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse wrapVMS_VendAcknowledgment_Sync_Out(){
                                smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse wrappedElement = new smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse();
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
        
                if (smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest.class.equals(type)){
                
                           return smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
           
                if (smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse.class.equals(type)){
                
                           return smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    