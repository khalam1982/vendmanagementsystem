
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package com.centrica.vms.ws.transaction.query;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "TransactionEndTimestamp".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.TransactionEndTimestamp.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "GetTransactionsResponseMessage".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.GetTransactionsResponseMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "Channel".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.Channel.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "VendDateTimeStamp".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.VendDateTimeStamp.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "TransactionStartTimestamp".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.TransactionStartTimestamp.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "VendAmount".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.VendAmount.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "NumberOfTransactions".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.NumberOfTransactions.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "VendCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.VendCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "UserDetails".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.UserDetails.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "BusinessProcessingFaultCode".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.BusinessProcessingFaultCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "VendTransaction".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.VendTransaction.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "GetTransactionsRequestMessage".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.GetTransactionsRequestMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "PanNumber".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.PanNumber.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "Status".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.Status.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "BG_CurrencyAmount".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.BG_CurrencyAmount.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://query.transaction.ws.vms.centrica.com".equals(namespaceURI) &&
                  "BG_Amount".equals(typeName)){
                   
                            return  com.centrica.vms.ws.transaction.query.BG_Amount.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    