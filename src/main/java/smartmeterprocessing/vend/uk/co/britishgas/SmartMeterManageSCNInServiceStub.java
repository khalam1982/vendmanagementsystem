
/**
 * SmartMeterManageSCNInServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package smartmeterprocessing.vend.uk.co.britishgas;

import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

        

        /*
        *  SmartMeterManageSCNInServiceStub java implementation
        */

        
        public class SmartMeterManageSCNInServiceStub extends org.apache.axis2.client.Stub
        {
        private Logger logger = ESAPI.getLogger(this.getClass());
        protected org.apache.axis2.description.AxisOperation[] _operations;

        //hashmaps to keep the fault mapping
        private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
        private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
        private java.util.HashMap faultMessageMap = new java.util.HashMap();

        private static int counter = 0;

        private static synchronized String getUniqueSuffix(){
            // reset the counter if it is greater than 99999
            if (counter > 99999){
                counter = 0;
            }
            counter = counter + 1; 
            return Long.toString(System.currentTimeMillis()) + "_" + counter;
        }

    
    private void populateAxisService() throws org.apache.axis2.AxisFault {

     //creating the Service with a unique name
     _service = new org.apache.axis2.description.AxisService("SmartMeterManageSCNInService" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];
        
                    __operation = new org.apache.axis2.description.OutOnlyAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("urn:britishgas.co.uk:Vend:SmartMeterProcessing", "SmartMeterManageSCNIn"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         


    }

    /**
      *Constructor that takes in a configContext
      */

    public SmartMeterManageSCNInServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public SmartMeterManageSCNInServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
         //To populate AxisService
         populateAxisService();
         populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,_service);
        
	
        configurationContext = _serviceClient.getServiceContext().getConfigurationContext();

        _serviceClient.getOptions().setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);
        
    
    }

    /**
     * Default Constructor
     */
    public SmartMeterManageSCNInServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://tpicxd2dv.uk.centricaplc.com:50000/sap/xi/engine?type=entry&version=3.0&Sender.Service=VEND_MODULE_SMART&Interface=urn%3Abritishgas.co.uk%3AVend%3ASmartMeterProcessing%5ESmartMeterManageSCNIn" );
                
    }

    /**
     * Default Constructor
     */
    public SmartMeterManageSCNInServiceStub() throws org.apache.axis2.AxisFault {
        
                    this("http://tpicxd2dv.uk.centricaplc.com:50000/sap/xi/engine?type=entry&version=3.0&Sender.Service=VEND_MODULE_SMART&Interface=urn%3Abritishgas.co.uk%3AVend%3ASmartMeterProcessing%5ESmartMeterManageSCNIn" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public SmartMeterManageSCNInServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



         
                
                /**
                  * Auto generated method signature
                  * 
                  */
                public void  SmartMeterManageSCNIn(
                 com.centrica.vms.ws.pi.service.AsynActivationandDeactivation asynActivationandDeactivation7

                ) throws java.rmi.RemoteException
                
                
                {
                	  logger.info(Logger.EVENT_UNSPECIFIED,"SCN Activation/De-activation request "  );
                org.apache.axis2.context.MessageContext _messageContext = null;

                
                org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
                _operationClient.getOptions().setAction("http://sap.com/xi/WebService/soap1.1");
                _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

                
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              
                org.apache.axiom.soap.SOAPEnvelope env = null;
                 _messageContext = new org.apache.axis2.context.MessageContext();

                
                                                    //Style is Doc.
                                                    
                                                                    
                                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                                    asynActivationandDeactivation7,
                                                                    optimizeContent(new javax.xml.namespace.QName("urn:britishgas.co.uk:Vend:SmartMeterProcessing",
                                                                    "SmartMeterManageSCNIn")));
                                                                

              //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
                // create message context with that soap envelope

            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

             _operationClient.execute(true);

             logger.info(Logger.EVENT_UNSPECIFIED,"SCN Activation/De-activation request " + env );
             
              _messageContext.getTransportOut().getSender().cleanup(_messageContext); 
           
             return;
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

    
    
    private javax.xml.namespace.QName[] opNameArray = null;
    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;   
            }
        }
        return false;
    }
     //http://tpicxd2dv.uk.centricaplc.com:50000/sap/xi/engine?type=entry&version=3.0&Sender.Service=VEND_MODULE_SMART&Interface=urn%3Abritishgas.co.uk%3AVend%3ASmartMeterProcessing%5ESmartMeterManageSCNIn
            private  org.apache.axiom.om.OMElement  toOM(com.centrica.vms.ws.pi.service.AsynActivationandDeactivation param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(com.centrica.vms.ws.pi.service.AsynActivationandDeactivation.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.centrica.vms.ws.pi.service.AsynActivationandDeactivation param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(com.centrica.vms.ws.pi.service.AsynActivationandDeactivation.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             


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



    
   }
   
