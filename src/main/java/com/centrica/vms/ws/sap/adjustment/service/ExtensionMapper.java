
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package com.centrica.vms.ws.sap.adjustment.service;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://model.adjustment.sap.ws.vms.centrica.com".equals(namespaceURI) &&
                  "Transaction".equals(typeName)){
                   
                            return  com.centrica.vms.ws.sap.adjustment.model.Transaction.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.adjustment.sap.ws.vms.centrica.com".equals(namespaceURI) &&
                  "MeterSource".equals(typeName)){
                   
                            return  com.centrica.vms.ws.sap.adjustment.model.MeterSource.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.adjustment.sap.ws.vms.centrica.com".equals(namespaceURI) &&
                  "ResponseCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.sap.adjustment.model.ResponseCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.adjustment.sap.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryReceiptResponse".equals(typeName)){
                   
                            return  com.centrica.vms.ws.sap.adjustment.model.DeliveryReceiptResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.adjustment.sap.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryReceipt".equals(typeName)){
                   
                            return  com.centrica.vms.ws.sap.adjustment.model.DeliveryReceipt.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.adjustment.sap.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryStatusCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.sap.adjustment.model.DeliveryStatusCode.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    