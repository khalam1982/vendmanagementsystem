package com.trilliantnetworks.unity.ws;

import com.trilliantnetworks.unity.ws.fault.ExceptionCode;

public class ExtensionMapper {



    public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                 java.lang.String typeName,
                                                 javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

        
            if (
            "urn:fault.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
            "ExceptionCode".equals(typeName)){
             
                      return  ExceptionCode.Factory.parse(reader);
                  

            }

        
            if (
            "urn:prepay.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
            "PrepaymentKeyStatus".equals(typeName)){
             
                      return  PrepaymentKeyStatus.Factory.parse(reader);
                  

            }

        
            if (
            "urn:prepay.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
            "RequestSource".equals(typeName)){
             
                      return  RequestSource.Factory.parse(reader);
                  

            }

        
            if (
            "urn:prepay.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
            "StatusCode".equals(typeName)){
             
                      return  StatusCode.Factory.parse(reader);
                  

            }

        
            if (
            "urn:prepay.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
            "PrepaymentKeyRecord".equals(typeName)){
             
                      return  PrepaymentKeyRecord.Factory.parse(reader);
                  

            }

        
            if (
            "urn:fault.ws.unity.trilliantnetworks.com".equals(namespaceURI) &&
            "ApiFault".equals(typeName)){
             
                      return  ApiFault.Factory.parse(reader);
                  

            }

        
       throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
    }

}
