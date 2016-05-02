
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package com.centrica.vms.vh.ws.sap.service;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "VendHistoryResponseMessage".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.VendHistoryResponseMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:VHCommon".equals(namespaceURI) &&
                  "integerType".equals(typeName)){
                   
                            return  vhcommon.enterprise.uk.co.britishgas.IntegerType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "VendHistoryRequestMessage".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.VendHistoryRequestMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "Txn_Details".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Details.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "VoidTxn_Details".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.VoidTxn_Details.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:VHCommon".equals(namespaceURI) &&
                  "dateType".equals(typeName)){
                   
                            return  vhcommon.enterprise.uk.co.britishgas.DateType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "Purchase_Txn".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:VHCommon".equals(namespaceURI) &&
                  "statusType".equals(typeName)){
                   
                            return  vhcommon.enterprise.uk.co.britishgas.StatusType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "Adjustment_Type".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Type.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "Txn_Outcome".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:VHCommon".equals(namespaceURI) &&
                  "stringType".equals(typeName)){
                   
                            return  vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:VHCommon".equals(namespaceURI) &&
                  "sourceType".equals(typeName)){
                   
                            return  vhcommon.enterprise.uk.co.britishgas.SourceType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory".equals(namespaceURI) &&
                  "Adjustment_Txn".equals(typeName)){
                   
                            return  smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    