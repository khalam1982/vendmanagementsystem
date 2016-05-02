
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package com.centrica.vms.ws.pi.ack;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://ack.pi.ws.vms.centrica.com".equals(namespaceURI) &&
                  "SmartMeterManageSCNAcknData_type0".equals(typeName)){
                   
                            return  com.centrica.vms.ws.pi.ack.SmartMeterManageSCNAcknData_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://ack.pi.ws.vms.centrica.com".equals(namespaceURI) &&
                  "SmartMeterManageSCNAcknMessage".equals(typeName)){
                   
                            return  com.centrica.vms.ws.pi.ack.SmartMeterManageSCNAcknMessage.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    