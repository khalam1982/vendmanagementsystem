
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package com.trilliantnetworks.unity.ws.tobject;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "urn:fault.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "ExceptionCode".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.fault.ExceptionCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:header.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "SessionHeader".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.header.SessionHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:header.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "GrantedAuthority".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.header.GrantedAuthority.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:tobject.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "BaseValue".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.tobject.BaseValue.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:header.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "QueryOptions".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.header.QueryOptions.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:header.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "UserDetails".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.header.UserDetails.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:tobject.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "tObject".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.tobject.TObject.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:prepay.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "StatusCode".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.prepay.StatusCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:header.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "ReadMeterOptions".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.header.ReadMeterOptions.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:prepay.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "VendCodeType".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.prepay.VendCodeType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:fault.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "ApiFault".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.fault.ApiFault.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:result.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "LoginResult".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.result.LoginResult.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:prepay.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
                  "Prepayment".equals(typeName)){
                   
                            return  com.trilliantnetworks.unity.ws.prepay.Prepayment.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    