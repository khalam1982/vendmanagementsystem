
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package com.centrica.vms.ws.headend.ack.model;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://model.ppkey.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryPPKeyReceipt".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.ppkey.model.DeliveryPPKeyReceipt.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://header.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "RequestHeader".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.header.RequestHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "ResponseCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.model.ResponseCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "MeterSource".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.model.MeterSource.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ppkey.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "PPKeyStatusCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.ppkey.model.PPKeyStatusCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "Transaction".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.model.Transaction.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://header.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "UserDetails".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.header.UserDetails.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryStatusCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.model.DeliveryStatusCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ppkey.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryPPKeyReceiptResponse".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.ppkey.model.DeliveryPPKeyReceiptResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://fault.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "ApiFault".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.fault.ApiFault.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryReceipt".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.model.DeliveryReceipt.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ppkey.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "PPKeyTransaction".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.ppkey.model.PPKeyTransaction.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://model.ack.headend.ws.vms.centrica.com".equals(namespaceURI) &&
                  "DeliveryReceiptResponse".equals(typeName)){
                   
                            return  com.centrica.vms.ws.headend.ack.model.DeliveryReceiptResponse.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    