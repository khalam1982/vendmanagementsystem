
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package smartmeterprocessing.enterprise.uk.co.britishgas;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendRequestKey".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendCreditType_type1".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:Common".equals(namespaceURI) &&
                  "BG_Identifier".equals(typeName)){
                   
                            return  common.enterprise.uk.co.britishgas.BG_Identifier.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendCode".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendCode.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendRequestTimestamp".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendAmount".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendCreditType_type1".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendCreditType_type1.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartVend".equals(namespaceURI) &&
                  "TransactionEventNotificationMessage".equals(typeName)){
                   
                            return  smartvend.enterprise.uk.co.britishgas.TransactionEventNotificationMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendRequestType".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:Common".equals(namespaceURI) &&
                  "schemeAgencyID_type0".equals(typeName)){
                   
                            return  common.enterprise.uk.co.britishgas.SchemeAgencyID_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartVend".equals(namespaceURI) &&
                  "VendHistoryTimestamps".equals(typeName)){
                   
                            return  smartvend.enterprise.uk.co.britishgas.VendHistoryTimestamps.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:Common".equals(namespaceURI) &&
                  "BG_MessageHeader".equals(typeName)){
                   
                            return  common.enterprise.uk.co.britishgas.BG_MessageHeader.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartVend".equals(namespaceURI) &&
                  "VendProcessState".equals(typeName)){
                   
                            return  smartvend.enterprise.uk.co.britishgas.VendProcessState.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendRequestAdditional".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:Common".equals(namespaceURI) &&
                  "BG_Identifier.Content".equals(typeName)){
                   
                            return  common.enterprise.uk.co.britishgas.BG_IdentifierContent.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:Common".equals(namespaceURI) &&
                  "schemeID_type0".equals(typeName)){
                   
                            return  common.enterprise.uk.co.britishgas.SchemeID_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:SmartMeterProcessing".equals(namespaceURI) &&
                  "PanNumber".equals(typeName)){
                   
                            return  smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:Common".equals(namespaceURI) &&
                  "BG_CurrencyAmount".equals(typeName)){
                   
                            return  common.enterprise.uk.co.britishgas.BG_CurrencyAmount.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Enterprise:Common".equals(namespaceURI) &&
                  "BG_Amount".equals(typeName)){
                   
                            return  common.enterprise.uk.co.britishgas.BG_Amount.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    