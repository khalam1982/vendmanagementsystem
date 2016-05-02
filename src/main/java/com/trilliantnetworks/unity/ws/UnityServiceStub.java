
/**
 * UnityServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
        package com.trilliantnetworks.unity.ws;

import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI; 
import org.owasp.esapi.Logger;

        

        /*
        *  UnityServiceStub java implementation
        */

        
        public class UnityServiceStub extends org.apache.axis2.client.Stub
        {
        private Logger logger = ESAPI.getLogger(this.getClass());
        protected org.apache.axis2.description.AxisOperation[] _operations;
        private static final String MASKED_VALUE = "XXXX";

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
     _service = new org.apache.axis2.description.AxisService("UnityService" + getUniqueSuffix());
     addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[4];
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com", "logout"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[0]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com", "applyVendCode"));
	    _service.addOperation(__operation);
	    

	    
	    
            _operations[1]=__operation;
            
        
                   __operation = new org.apache.axis2.description.OutInAxisOperation();
                

            __operation.setName(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com", "login"));
	    _service.addOperation(__operation);
	    
	    _operations[2]=__operation;
	    
	    __operation = new org.apache.axis2.description.OutInAxisOperation();
        

        __operation.setName(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com", "updatePrepaymentKey"));
    _service.addOperation(__operation);
	    
	    
            _operations[3]=__operation;
            
        
        }

    //populates the faults
    private void populateFaults(){
         
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.UnexpectedErrorFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.UnexpectedErrorFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.AccessDeniedFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.AccessDeniedFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.fault.AccessDeniedFault");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.UnexpectedErrorFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.UnexpectedErrorFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.AccessDeniedFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.AccessDeniedFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.fault.AccessDeniedFault");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","LoginFault"),"com.trilliantnetworks.unity.ws.LoginFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","LoginFault"),"com.trilliantnetworks.unity.ws.LoginFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","LoginFault"),"com.trilliantnetworks.unity.ws.fault.LoginFault");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.UnexpectedErrorFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.UnexpectedErrorFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","UnexpectedErrorFault"),"com.trilliantnetworks.unity.ws.fault.UnexpectedErrorFault");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.AccessDeniedFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.AccessDeniedFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","AccessDeniedFault"),"com.trilliantnetworks.unity.ws.fault.AccessDeniedFault");
           
              faultExceptionNameMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","LoginFault"),"com.trilliantnetworks.unity.ws.LoginFault");
              faultExceptionClassNameMap.put(new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","LoginFault"),"com.trilliantnetworks.unity.ws.LoginFault");
              faultMessageMap.put( new javax.xml.namespace.QName("urn:fault.ws.unity.trilliantnetworks.com","LoginFault"),"com.trilliantnetworks.unity.ws.fault.LoginFault");
           


    }

    /**
      *Constructor that takes in a configContext
      */

    public UnityServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
       java.lang.String targetEndpoint)
       throws org.apache.axis2.AxisFault {
         this(configurationContext,targetEndpoint,false);
   }


   /**
     * Constructor that takes in a configContext  and useseperate listner
     */
   public UnityServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
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
    public UnityServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext) throws org.apache.axis2.AxisFault {
        
                    this(configurationContext,"http://localhost:8080/trilliant/services/UnityService" );
                
    }

    /**
     * Default Constructor
     */
    public UnityServiceStub() throws org.apache.axis2.AxisFault {
        
                    this("http://localhost:8080/trilliant/services/UnityService" );
                
    }

    /**
     * Constructor taking the target endpoint
     */
    public UnityServiceStub(java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null,targetEndpoint);
    }



        
                    /**
                     * Auto generated method signature
                     * Logout from the Trilliant Networks
                     * @see com.trilliantnetworks.unity.ws.UnityService#logout
                     * @param logout42
                    
                     * @throws com.trilliantnetworks.unity.ws.UnexpectedErrorFault : 
                     * @throws com.trilliantnetworks.unity.ws.AccessDeniedFault : 
                     */

                    

                            public  com.trilliantnetworks.unity.ws.LogoutResponse logout(

                            com.trilliantnetworks.unity.ws.Logout logout42)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,com.trilliantnetworks.unity.ws.UnexpectedErrorFault
                        ,com.trilliantnetworks.unity.ws.AccessDeniedFault{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
              _operationClient.getOptions().setAction("urn:ws.unity.trilliantnetworks.com:Soap:logout");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    logout42,
                                                    optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
                                                    "logout")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

        logger.info(Logger.EVENT_UNSPECIFIED,"Headend logout request " + env );
         
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
       logger.info(Logger.EVENT_UNSPECIFIED,"Headend logout response " + _returnEnv ); 
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             com.trilliantnetworks.unity.ws.LogoutResponse.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (com.trilliantnetworks.unity.ws.LogoutResponse)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
                          throw (com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex;
                        }
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
                          throw (com.trilliantnetworks.unity.ws.AccessDeniedFault)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * Logout from the Trilliant Networks
                * @see com.trilliantnetworks.unity.ws.UnityService#startlogout
                    * @param logout42
                
                */
                public  void startlogout(

                 com.trilliantnetworks.unity.ws.Logout logout42,

                  final com.trilliantnetworks.unity.ws.UnityServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
             _operationClient.getOptions().setAction("urn:ws.unity.trilliantnetworks.com:Soap:logout");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    logout42,
                                                    optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
                                                    "logout")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         com.trilliantnetworks.unity.ws.LogoutResponse.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultlogout(
                                        (com.trilliantnetworks.unity.ws.LogoutResponse)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorlogout(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
														callback.receiveErrorlogout((com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex);
											            return;
										            }
										            
													if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
														callback.receiveErrorlogout((com.trilliantnetworks.unity.ws.AccessDeniedFault)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorlogout(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogout(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogout(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogout(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogout(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogout(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogout(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogout(f);
                                            }
									    } else {
										    callback.receiveErrorlogout(f);
									    }
									} else {
									    callback.receiveErrorlogout(f);
									}
								} else {
								    callback.receiveErrorlogout(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorlogout(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[0].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * Apply Vend Code
                     * @see com.trilliantnetworks.unity.ws.UnityService#applyVendCode
                     * @param applyVendCodeRequest44
                    
                     * @throws com.trilliantnetworks.unity.ws.UnexpectedErrorFault : 
                     * @throws com.trilliantnetworks.unity.ws.AccessDeniedFault : 
                     * @throws com.trilliantnetworks.unity.ws.LoginFault : 
                     */

                    

                            public  com.trilliantnetworks.unity.ws.ApplyVendCodeResponse applyVendCode(

                            com.trilliantnetworks.unity.ws.ApplyVendCodeRequest applyVendCodeRequest44)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,com.trilliantnetworks.unity.ws.UnexpectedErrorFault
                        ,com.trilliantnetworks.unity.ws.AccessDeniedFault
                        ,com.trilliantnetworks.unity.ws.LoginFault{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
              _operationClient.getOptions().setAction("urn:applyVendCode");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    applyVendCodeRequest44,
                                                    optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
                                                    "applyVendCode")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

         logger.info(Logger.EVENT_UNSPECIFIED,"Headend apply vend code request " + env );
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
         logger.info(Logger.EVENT_UNSPECIFIED,"Headend apply vend code response " + _returnEnv );
                
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             com.trilliantnetworks.unity.ws.ApplyVendCodeResponse.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (com.trilliantnetworks.unity.ws.ApplyVendCodeResponse)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
                          throw (com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex;
                        }
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
                          throw (com.trilliantnetworks.unity.ws.AccessDeniedFault)ex;
                        }
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.LoginFault){
                          throw (com.trilliantnetworks.unity.ws.LoginFault)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
            
                /**
                * Auto generated method signature for Asynchronous Invocations
                * Apply Vend Code
                * @see com.trilliantnetworks.unity.ws.UnityService#startapplyVendCode
                    * @param applyVendCodeRequest44
                
                */
                public  void startapplyVendCode(

                 com.trilliantnetworks.unity.ws.ApplyVendCodeRequest applyVendCodeRequest44,

                  final com.trilliantnetworks.unity.ws.UnityServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[1].getName());
             _operationClient.getOptions().setAction("urn:applyVendCode");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    applyVendCodeRequest44,
                                                    optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
                                                    "applyVendCode")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         com.trilliantnetworks.unity.ws.ApplyVendCodeResponse.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultapplyVendCode(
                                        (com.trilliantnetworks.unity.ws.ApplyVendCodeResponse)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorapplyVendCode(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
														callback.receiveErrorapplyVendCode((com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex);
											            return;
										            }
										            
													if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
														callback.receiveErrorapplyVendCode((com.trilliantnetworks.unity.ws.AccessDeniedFault)ex);
											            return;
										            }
										            
													if (ex instanceof com.trilliantnetworks.unity.ws.LoginFault){
														callback.receiveErrorapplyVendCode((com.trilliantnetworks.unity.ws.LoginFault)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorapplyVendCode(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorapplyVendCode(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorapplyVendCode(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorapplyVendCode(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorapplyVendCode(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorapplyVendCode(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorapplyVendCode(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorapplyVendCode(f);
                                            }
									    } else {
										    callback.receiveErrorapplyVendCode(f);
									    }
									} else {
									    callback.receiveErrorapplyVendCode(f);
									}
								} else {
								    callback.receiveErrorapplyVendCode(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorapplyVendCode(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[1].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[1].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

                    }
                
                    /**
                     * Auto generated method signature
                     * Login to the Trilliant Networks
                     * @see com.trilliantnetworks.unity.ws.UnityService#login
                     * @param login46
                    
                     * @throws com.trilliantnetworks.unity.ws.UnexpectedErrorFault : 
                     * @throws com.trilliantnetworks.unity.ws.AccessDeniedFault : 
                     * @throws com.trilliantnetworks.unity.ws.LoginFault : 
                     */

                    

                            public  com.trilliantnetworks.unity.ws.LoginResponse login(

                            com.trilliantnetworks.unity.ws.Login login46)
                        

                    throws java.rmi.RemoteException
                    
                    
                        ,com.trilliantnetworks.unity.ws.UnexpectedErrorFault
                        ,com.trilliantnetworks.unity.ws.AccessDeniedFault
                        ,com.trilliantnetworks.unity.ws.LoginFault{
              org.apache.axis2.context.MessageContext _messageContext = null;
              try{
               org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
              _operationClient.getOptions().setAction("urn:ws.unity.trilliantnetworks.com:Soap:login");
              _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              

              // create a message context
              _messageContext = new org.apache.axis2.context.MessageContext();

              

              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env = null;
                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    login46,
                                                    optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
                                                    "login")));
                                                
        //adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // set the message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message contxt to the operation client
        _operationClient.addMessageContext(_messageContext);

        //execute the operation client
        _operationClient.execute(true);

        logger.info(Logger.EVENT_UNSPECIFIED,"Headend login request " + mask(env.toString()) );
               org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
                                           org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
                org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
                
        logger.info(Logger.EVENT_UNSPECIFIED,"Headend login response " + _returnEnv );
                                java.lang.Object object = fromOM(
                                             _returnEnv.getBody().getFirstElement() ,
                                             com.trilliantnetworks.unity.ws.LoginResponse.class,
                                              getEnvelopeNamespaces(_returnEnv));

                               
                                        return (com.trilliantnetworks.unity.ws.LoginResponse)object;
                                   
         }catch(org.apache.axis2.AxisFault f){

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt!=null){
                if (faultExceptionNameMap.containsKey(faultElt.getQName())){
                    //make the fault by reflection
                    try{
                        java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.Exception ex=
                                (java.lang.Exception) exceptionClass.newInstance();
                        //message class
                        java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                   new java.lang.Class[]{messageClass});
                        m.invoke(ex,new java.lang.Object[]{messageObject});
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
                          throw (com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex;
                        }
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
                          throw (com.trilliantnetworks.unity.ws.AccessDeniedFault)ex;
                        }
                        
                        if (ex instanceof com.trilliantnetworks.unity.ws.LoginFault){
                          throw (com.trilliantnetworks.unity.ws.LoginFault)ex;
                        }
                        

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    }catch(java.lang.ClassCastException e){
                       // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }  catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }   catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                }else{
                    throw f;
                }
            }else{
                throw f;
            }
            } finally {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
                            private String mask(String soapMessage) {
                        		String message = mask(soapMessage, "username");
                        		message = mask(message,"password");
                        		return message;
            					}
                            

                            private String mask(String soapMessage, String tagName) {
                        		String tag = StringUtils.substringBetween(soapMessage, tagName);
                        		String value = StringUtils.substringBetween(tag, ">", "<");
                        		return StringUtils.replaceOnce(soapMessage, value, MASKED_VALUE);
            					}


				/**
                * Auto generated method signature for Asynchronous Invocations
                * Login to the Trilliant Networks
                * @see com.trilliantnetworks.unity.ws.UnityService#startlogin
                    * @param login46
                
                */
                public  void startlogin(

                 com.trilliantnetworks.unity.ws.Login login46,

                  final com.trilliantnetworks.unity.ws.UnityServiceCallbackHandler callback)

                throws java.rmi.RemoteException{

              org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[2].getName());
             _operationClient.getOptions().setAction("urn:ws.unity.trilliantnetworks.com:Soap:login");
             _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

              
              
                  addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");
              


              // create SOAP envelope with that payload
              org.apache.axiom.soap.SOAPEnvelope env=null;
              final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

                    
                                    //Style is Doc.
                                    
                                                    
                                                    env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                                                    login46,
                                                    optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
                                                    "login")));
                                                
        // adding SOAP soap_headers
         _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);


                    
                        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
                            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                            try {
                                org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();
                                
                                        java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
                                                                         com.trilliantnetworks.unity.ws.LoginResponse.class,
                                                                         getEnvelopeNamespaces(resultEnv));
                                        callback.receiveResultlogin(
                                        (com.trilliantnetworks.unity.ws.LoginResponse)object);
                                        
                            } catch (org.apache.axis2.AxisFault e) {
                                callback.receiveErrorlogin(e);
                            }
                            }

                            public void onError(java.lang.Exception error) {
								if (error instanceof org.apache.axis2.AxisFault) {
									org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
									org.apache.axiom.om.OMElement faultElt = f.getDetail();
									if (faultElt!=null){
										if (faultExceptionNameMap.containsKey(faultElt.getQName())){
											//make the fault by reflection
											try{
													java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
													java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
													java.lang.Exception ex=
														(java.lang.Exception) exceptionClass.newInstance();
													//message class
													java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
														java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
													java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
													java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
															new java.lang.Class[]{messageClass});
													m.invoke(ex,new java.lang.Object[]{messageObject});
													
													if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
														callback.receiveErrorlogin((com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex);
											            return;
										            }
										            
													if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
														callback.receiveErrorlogin((com.trilliantnetworks.unity.ws.AccessDeniedFault)ex);
											            return;
										            }
										            
													if (ex instanceof com.trilliantnetworks.unity.ws.LoginFault){
														callback.receiveErrorlogin((com.trilliantnetworks.unity.ws.LoginFault)ex);
											            return;
										            }
										            
					
										            callback.receiveErrorlogin(new java.rmi.RemoteException(ex.getMessage(), ex));
                                            } catch(java.lang.ClassCastException e){
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogin(f);
                                            } catch (java.lang.ClassNotFoundException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogin(f);
                                            } catch (java.lang.NoSuchMethodException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogin(f);
                                            } catch (java.lang.reflect.InvocationTargetException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogin(f);
                                            } catch (java.lang.IllegalAccessException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogin(f);
                                            } catch (java.lang.InstantiationException e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogin(f);
                                            } catch (org.apache.axis2.AxisFault e) {
                                                // we cannot intantiate the class - throw the original Axis fault
                                                callback.receiveErrorlogin(f);
                                            }
									    } else {
										    callback.receiveErrorlogin(f);
									    }
									} else {
									    callback.receiveErrorlogin(f);
									}
								} else {
								    callback.receiveErrorlogin(error);
								}
                            }

                            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
                                onError(fault);
                            }

                            public void onComplete() {
                                try {
                                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                                } catch (org.apache.axis2.AxisFault axisFault) {
                                    callback.receiveErrorlogin(axisFault);
                                }
                            }
                });
                        

          org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if ( _operations[2].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
           _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
          _operations[2].setMessageReceiver(
                    _callbackReceiver);
        }

           //execute the operation client
           _operationClient.execute(false);

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
     //http://localhost:8080/trilliant/services/UnityService
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
        
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.trilliantnetworks.unity.ws.Logout param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(com.trilliantnetworks.unity.ws.Logout.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.trilliantnetworks.unity.ws.ApplyVendCodeRequest param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(com.trilliantnetworks.unity.ws.ApplyVendCodeRequest.MY_QNAME,factory));
                                                            return emptyEnvelope;
                                                        } catch(org.apache.axis2.databinding.ADBException e){
                                                            throw org.apache.axis2.AxisFault.makeFault(e);
                                                        }
                                                

                                        }
                                
                             
                             /* methods to provide back word compatibility */

                             
                                    
                                        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.trilliantnetworks.unity.ws.Login param, boolean optimizeContent)
                                        throws org.apache.axis2.AxisFault{

                                             
                                                    try{

                                                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                                                            emptyEnvelope.getBody().addChild(param.getOMElement(com.trilliantnetworks.unity.ws.Login.MY_QNAME,factory));
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

                if (com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse.class.equals(type)){
                	
                	return com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                	
                	
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
         * Auto generated method signature
         * Change Prepayment Key
         * @see com.trilliantnetworks.unity.ws.UnityService#updatePrepaymentKey
         * @param updatePrepaymentKeyRequest0

         * @throws com.trilliantnetworks.unity.ws.UnexpectedErrorFault : 
         * @throws com.trilliantnetworks.unity.ws.AccessDeniedFault : 
         * @throws com.trilliantnetworks.unity.ws.LoginFault : 
         */
        public  com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse updatePrepaymentKey(
        		com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyRequest updatePrepaymentKeyRequest0)


        throws java.rmi.RemoteException


        ,com.trilliantnetworks.unity.ws.UnexpectedErrorFault
        ,com.trilliantnetworks.unity.ws.AccessDeniedFault
        ,com.trilliantnetworks.unity.ws.LoginFault{
        	org.apache.axis2.context.MessageContext _messageContext = null;
        	try{
        		org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[3].getName());
        		_operationClient.getOptions().setAction("urn:updatePrepaymentKey");
        		_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



        		addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");


        		// create a message context
        		_messageContext = new org.apache.axis2.context.MessageContext();



        		// create SOAP envelope with that payload
        		org.apache.axiom.soap.SOAPEnvelope env = null;


        		env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
        				updatePrepaymentKeyRequest0,
        				optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
        				"updatePrepaymentKey")));

        		//adding SOAP soap_headers
        		_serviceClient.addHeadersToEnvelope(env);
        		// set the message context with that soap envelope
        		_messageContext.setEnvelope(env);

        		// add the message contxt to the operation client
        		_operationClient.addMessageContext(_messageContext);

        		logger.info(Logger.EVENT_UNSPECIFIED,"Headend Update PP Key request " + env );
        		//execute the operation client
        		_operationClient.execute(true);


        		org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(
        				org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
        		org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

        		logger.info(Logger.EVENT_UNSPECIFIED,"Headend Update PP Key response " + _returnEnv );

        		java.lang.Object object = fromOM(
        				_returnEnv.getBody().getFirstElement() ,
        				com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse.class,
        				getEnvelopeNamespaces(_returnEnv));


        		return (com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse)object;

        	}catch(org.apache.axis2.AxisFault f){

        		org.apache.axiom.om.OMElement faultElt = f.getDetail();
        		if (faultElt!=null){
        			if (faultExceptionNameMap.containsKey(faultElt.getQName())){
        				//make the fault by reflection
        				try{
        					java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
        					java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
        					java.lang.Exception ex=
        						(java.lang.Exception) exceptionClass.newInstance();
        					//message class
        					java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
        					java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
        					java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
        					java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
        							new java.lang.Class[]{messageClass});
        					m.invoke(ex,new java.lang.Object[]{messageObject});

        					if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
        						throw (com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex;
        					}

        					if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
        						throw (com.trilliantnetworks.unity.ws.AccessDeniedFault)ex;
        					}

        					if (ex instanceof com.trilliantnetworks.unity.ws.LoginFault){
        						throw (com.trilliantnetworks.unity.ws.LoginFault)ex;
        					}


        					throw new java.rmi.RemoteException(ex.getMessage(), ex);
        				}catch(java.lang.ClassCastException e){
        					// we cannot intantiate the class - throw the original Axis fault
        					throw f;
        				} catch (java.lang.ClassNotFoundException e) {
        					// we cannot intantiate the class - throw the original Axis fault
        					throw f;
        				}catch (java.lang.NoSuchMethodException e) {
        					// we cannot intantiate the class - throw the original Axis fault
        					throw f;
        				} catch (java.lang.reflect.InvocationTargetException e) {
        					// we cannot intantiate the class - throw the original Axis fault
        					throw f;
        				}  catch (java.lang.IllegalAccessException e) {
        					// we cannot intantiate the class - throw the original Axis fault
        					throw f;
        				}   catch (java.lang.InstantiationException e) {
        					// we cannot intantiate the class - throw the original Axis fault
        					throw f;
        				}
        			}else{
        				throw f;
        			}
        		}else{
        			throw f;
        		}
        	} finally {
        		_messageContext.getTransportOut().getSender().cleanup(_messageContext);
        	}
        }

        /**
         * Auto generated method signature for Asynchronous Invocations
         * Change Prepayment Key
         * @see com.trilliantnetworks.unity.ws.UnityService#startupdatePrepaymentKey
         * @param updatePrepaymentKeyRequest0

         */
        public  void startupdatePrepaymentKey(

        		com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyRequest updatePrepaymentKeyRequest0,

        		final com.trilliantnetworks.unity.ws.UnityServiceCallbackHandler callback)

        throws java.rmi.RemoteException{

        	org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
        	_operationClient.getOptions().setAction("urn:updatePrepaymentKey");
        	_operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);



        	addPropertyToOperationClient(_operationClient,org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,"&");



        	// create SOAP envelope with that payload
        	org.apache.axiom.soap.SOAPEnvelope env=null;
        	final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();


        	//Style is Doc.


        	env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
        			updatePrepaymentKeyRequest0,
        			optimizeContent(new javax.xml.namespace.QName("urn:ws.unity.trilliantnetworks.com",
        			"updatePrepaymentKey")));

        	// adding SOAP soap_headers
        	_serviceClient.addHeadersToEnvelope(env);
        	// create message context with that soap envelope
        	_messageContext.setEnvelope(env);

        	// add the message context to the operation client
        	_operationClient.addMessageContext(_messageContext);



        	_operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
        		public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
        			try {
        				org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

        				java.lang.Object object = fromOM(resultEnv.getBody().getFirstElement(),
        						com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse.class,
        						getEnvelopeNamespaces(resultEnv));
        				callback.receiveResultupdatePrepaymentKey(
        						(com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse)object);

        			} catch (org.apache.axis2.AxisFault e) {
        				callback.receiveErrorupdatePrepaymentKey(e);
        			}
        		}

        		public void onError(java.lang.Exception error) {
        			if (error instanceof org.apache.axis2.AxisFault) {
        				org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
        				org.apache.axiom.om.OMElement faultElt = f.getDetail();
        				if (faultElt!=null){
        					if (faultExceptionNameMap.containsKey(faultElt.getQName())){
        						//make the fault by reflection
        						try{
        							java.lang.String exceptionClassName = (java.lang.String)faultExceptionClassNameMap.get(faultElt.getQName());
        							java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
        							java.lang.Exception ex=
        								(java.lang.Exception) exceptionClass.newInstance();
        							//message class
        							java.lang.String messageClassName = (java.lang.String)faultMessageMap.get(faultElt.getQName());
        							java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
        							java.lang.Object messageObject = fromOM(faultElt,messageClass,null);
        							java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
        									new java.lang.Class[]{messageClass});
        							m.invoke(ex,new java.lang.Object[]{messageObject});

        							if (ex instanceof com.trilliantnetworks.unity.ws.UnexpectedErrorFault){
        								callback.receiveErrorupdatePrepaymentKey((com.trilliantnetworks.unity.ws.UnexpectedErrorFault)ex);
        								return;
        							}

        							if (ex instanceof com.trilliantnetworks.unity.ws.AccessDeniedFault){
        								callback.receiveErrorupdatePrepaymentKey((com.trilliantnetworks.unity.ws.AccessDeniedFault)ex);
        								return;
        							}

        							if (ex instanceof com.trilliantnetworks.unity.ws.LoginFault){
        								callback.receiveErrorupdatePrepaymentKey((com.trilliantnetworks.unity.ws.LoginFault)ex);
        								return;
        							}


        							callback.receiveErrorupdatePrepaymentKey(new java.rmi.RemoteException(ex.getMessage(), ex));
        						} catch(java.lang.ClassCastException e){
        							// we cannot intantiate the class - throw the original Axis fault
        							callback.receiveErrorupdatePrepaymentKey(f);
        						} catch (java.lang.ClassNotFoundException e) {
        							// we cannot intantiate the class - throw the original Axis fault
        							callback.receiveErrorupdatePrepaymentKey(f);
        						} catch (java.lang.NoSuchMethodException e) {
        							// we cannot intantiate the class - throw the original Axis fault
        							callback.receiveErrorupdatePrepaymentKey(f);
        						} catch (java.lang.reflect.InvocationTargetException e) {
        							// we cannot intantiate the class - throw the original Axis fault
        							callback.receiveErrorupdatePrepaymentKey(f);
        						} catch (java.lang.IllegalAccessException e) {
        							// we cannot intantiate the class - throw the original Axis fault
        							callback.receiveErrorupdatePrepaymentKey(f);
        						} catch (java.lang.InstantiationException e) {
        							// we cannot intantiate the class - throw the original Axis fault
        							callback.receiveErrorupdatePrepaymentKey(f);
        						} catch (org.apache.axis2.AxisFault e) {
        							// we cannot intantiate the class - throw the original Axis fault
        							callback.receiveErrorupdatePrepaymentKey(f);
        						}
        					} else {
        						callback.receiveErrorupdatePrepaymentKey(f);
        					}
        				} else {
        					callback.receiveErrorupdatePrepaymentKey(f);
        				}
        			} else {
        				callback.receiveErrorupdatePrepaymentKey(error);
        			}
        		}

        		public void onFault(org.apache.axis2.context.MessageContext faultContext) {
        			org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils.getInboundFaultFromMessageContext(faultContext);
        			onError(fault);
        		}

        		public void onComplete() {
        			try {
        				_messageContext.getTransportOut().getSender().cleanup(_messageContext);
        			} catch (org.apache.axis2.AxisFault axisFault) {
        				callback.receiveErrorupdatePrepaymentKey(axisFault);
        			}
        		}
        	});


        	org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        	if ( _operations[0].getMessageReceiver()==null &&  _operationClient.getOptions().isUseSeparateListener()) {
        		_callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
        		_operations[0].setMessageReceiver(
        				_callbackReceiver);
        	}

        	//execute the operation client
        	_operationClient.execute(false);

        }

        private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyRequest param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault{

             
                    try{

                            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                            emptyEnvelope.getBody().addChild(param.getOMElement(com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyRequest.MY_QNAME,factory));
                            return emptyEnvelope;
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                

        }
    
   }
   
