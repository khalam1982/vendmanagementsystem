
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package com.centrica.vms.ws.ppk.service;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://service.ppk.ws.vms.centrica.com".equals(namespaceURI) &&
                  "UpdatePPKeyResponseMessage".equals(typeName)){
                   
                            return  com.centrica.vms.ws.ppk.service.UpdatePPKeyResponseMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://service.ppk.ws.vms.centrica.com".equals(namespaceURI) &&
                  "BusinessProcessingFaultCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.ppk.service.BusinessProcessingFaultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://service.ppk.ws.vms.centrica.com".equals(namespaceURI) &&
                  "UpdatePPKeyRequestMessage".equals(typeName)){
                   
                            return  com.centrica.vms.ws.ppk.service.UpdatePPKeyRequestMessage.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    