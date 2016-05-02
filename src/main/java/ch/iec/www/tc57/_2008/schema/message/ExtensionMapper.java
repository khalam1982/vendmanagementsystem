
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package ch.iec.www.tc57._2008.schema.message;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "RequestMessageType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.RequestMessageType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "HeaderType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.HeaderType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://iec.ch/TC57/2009/EndDeviceControls#".equals(namespaceURI) &&
                  "EndDeviceAsset_type0".equals(typeName)){
                   
                            return  ch.iec.tc57._2009.enddevicecontrols.EndDeviceAsset_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/TrilliantCommonTypes".equals(namespaceURI) &&
                  "adjustCredit".equals(typeName)){
                   
                            return  trilliantami.www.trilliantcommontypes.AdjustCredit.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd".equals(namespaceURI) &&
                  "KeyIdentifierType".equals(typeName)){
                   
                            return  org.oasis_open.docs.www.wss._2004._01.oasis_200401_wss_wssecurity_secext_1_0_xsd.KeyIdentifierType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://iec.ch/TC57/2009/EndDeviceControls#".equals(namespaceURI) &&
                  "scheduledInterval_type0".equals(typeName)){
                   
                            return  ch.iec.tc57._2009.enddevicecontrols.ScheduledInterval_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/TrilliantCommonTypes".equals(namespaceURI) &&
                  "CreditType".equals(typeName)){
                   
                            return  trilliantami.www.trilliantcommontypes.CreditType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/AdjustCreditMessages".equals(namespaceURI) &&
                  "AdjustCreditRequestMessage".equals(typeName)){
                   
                            return  trilliantami.www.adjustcreditmessages.AdjustCreditRequestMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "PayloadType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.PayloadType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://iec.ch/TC57/2009/EndDeviceControls#".equals(namespaceURI) &&
                  "EndDeviceControl".equals(typeName)){
                   
                            return  ch.iec.tc57._2009.enddevicecontrols.EndDeviceControl.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/TrilliantCommonTypes".equals(namespaceURI) &&
                  "Currency".equals(typeName)){
                   
                            return  trilliantami.www.trilliantcommontypes.Currency.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/TrilliantCommonTypes".equals(namespaceURI) &&
                  "credit".equals(typeName)){
                   
                            return  trilliantami.www.trilliantcommontypes.Credit.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "ResponseMessageType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.ResponseMessageType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://iec.ch/TC57/2009/EndDeviceControls#".equals(namespaceURI) &&
                  "DemandResponseProgram_type0".equals(typeName)){
                   
                            return  ch.iec.tc57._2009.enddevicecontrols.DemandResponseProgram_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd".equals(namespaceURI) &&
                  "PasswordString".equals(typeName)){
                   
                            return  org.oasis_open.docs.www.wss._2004._01.oasis_200401_wss_wssecurity_secext_1_0_xsd.PasswordString.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "UserType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.UserType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "FaultMessageType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.FaultMessageType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd".equals(namespaceURI) &&
                  "EncodedString".equals(typeName)){
                   
                            return  org.oasis_open.docs.www.wss._2004._01.oasis_200401_wss_wssecurity_secext_1_0_xsd.EncodedString.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/AdjustCreditMessages".equals(namespaceURI) &&
                  "AdjustCreditReplyMessage".equals(typeName)){
                   
                            return  trilliantami.www.adjustcreditmessages.AdjustCreditReplyMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://TrilliantAMIService/".equals(namespaceURI) &&
                  "adjustCreditResponse".equals(typeName)){
                   
                            return  trilliantamiservice.AdjustCreditResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "EventMessageType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.EventMessageType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "ReplayDetectionType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.ReplayDetectionType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd".equals(namespaceURI) &&
                  "BinarySecurityTokenType".equals(typeName)){
                   
                            return  org.oasis_open.docs.www.wss._2004._01.oasis_200401_wss_wssecurity_secext_1_0_xsd.BinarySecurityTokenType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://TrilliantAMIService/".equals(namespaceURI) &&
                  "adjustCredit".equals(typeName)){
                   
                            return  trilliantamiservice.AdjustCreditE.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/TrilliantCommonTypes".equals(namespaceURI) &&
                  "AdjustCreditValues".equals(typeName)){
                   
                            return  trilliantami.www.trilliantcommontypes.AdjustCreditValues.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.TrilliantAMI/TrilliantEndDeviceControls#".equals(namespaceURI) &&
                  "TrilliantEndDeviceControl".equals(typeName)){
                   
                            return  trilliantami.www.trilliantenddevicecontrols.TrilliantEndDeviceControl.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://iec.ch/TC57/2009/EndDeviceControls#".equals(namespaceURI) &&
                  "ServiceDeliveryPoint_type0".equals(typeName)){
                   
                            return  ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "RequestType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.RequestType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "MessageProperty".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.MessageProperty.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "MessageType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.MessageType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://iec.ch/TC57/2009/EndDeviceControls#".equals(namespaceURI) &&
                  "EndDeviceGroup_type0".equals(typeName)){
                   
                            return  ch.iec.tc57._2009.enddevicecontrols.EndDeviceGroup_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd".equals(namespaceURI) &&
                  "AttributedString".equals(typeName)){
                   
                            return  org.oasis_open.docs.www.wss._2004._01.oasis_200401_wss_wssecurity_secext_1_0_xsd.AttributedString.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.iec.ch/TC57/2008/schema/message".equals(namespaceURI) &&
                  "ReplyType".equals(typeName)){
                   
                            return  ch.iec.www.tc57._2008.schema.message.ReplyType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd".equals(namespaceURI) &&
                  "AttributedDateTime".equals(typeName)){
                   
                            return  org.oasis_open.docs.www.wss._2004._01.oasis_200401_wss_wssecurity_utility_1_0_xsd.AttributedDateTime.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    