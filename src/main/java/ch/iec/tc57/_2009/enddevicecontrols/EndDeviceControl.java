
/**
 * EndDeviceControl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package ch.iec.tc57._2009.enddevicecontrols;
            

            /**
            *  EndDeviceControl bean class
            */
        
        public  class EndDeviceControl
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = EndDeviceControl
                Namespace URI = http://iec.ch/TC57/2009/EndDeviceControls#
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://iec.ch/TC57/2009/EndDeviceControls#")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for MRID
                        */

                        
                                    protected java.lang.String localMRID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localMRIDTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getMRID(){
                               return localMRID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MRID
                               */
                               public void setMRID(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localMRIDTracker = true;
                                       } else {
                                          localMRIDTracker = false;
                                              
                                       }
                                   
                                            this.localMRID=param;
                                    

                               }
                            

                        /**
                        * field for Description
                        */

                        
                                    protected java.lang.String localDescription ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDescriptionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDescription(){
                               return localDescription;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Description
                               */
                               public void setDescription(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDescriptionTracker = true;
                                       } else {
                                          localDescriptionTracker = false;
                                              
                                       }
                                   
                                            this.localDescription=param;
                                    

                               }
                            

                        /**
                        * field for DrProgramLevel
                        */

                        
                                    protected java.math.BigInteger localDrProgramLevel ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDrProgramLevelTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.math.BigInteger
                           */
                           public  java.math.BigInteger getDrProgramLevel(){
                               return localDrProgramLevel;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DrProgramLevel
                               */
                               public void setDrProgramLevel(java.math.BigInteger param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDrProgramLevelTracker = true;
                                       } else {
                                          localDrProgramLevelTracker = false;
                                              
                                       }
                                   
                                            this.localDrProgramLevel=param;
                                    

                               }
                            

                        /**
                        * field for DrProgramMandatory
                        */

                        
                                    protected boolean localDrProgramMandatory ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDrProgramMandatoryTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getDrProgramMandatory(){
                               return localDrProgramMandatory;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DrProgramMandatory
                               */
                               public void setDrProgramMandatory(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (false) {
                                           localDrProgramMandatoryTracker = false;
                                              
                                       } else {
                                          localDrProgramMandatoryTracker = true;
                                       }
                                   
                                            this.localDrProgramMandatory=param;
                                    

                               }
                            

                        /**
                        * field for PriceSignal
                        */

                        
                                    protected float localPriceSignal ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPriceSignalTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return float
                           */
                           public  float getPriceSignal(){
                               return localPriceSignal;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PriceSignal
                               */
                               public void setPriceSignal(float param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (java.lang.Float.isNaN(param)) {
                                           localPriceSignalTracker = false;
                                              
                                       } else {
                                          localPriceSignalTracker = true;
                                       }
                                   
                                            this.localPriceSignal=param;
                                    

                               }
                            

                        /**
                        * field for Type
                        */

                        
                                    protected java.lang.String localType ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getType(){
                               return localType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Type
                               */
                               public void setType(java.lang.String param){
                            
                                            this.localType=param;
                                    

                               }
                            

                        /**
                        * field for DemandResponseProgram
                        */

                        
                                    protected ch.iec.tc57._2009.enddevicecontrols.DemandResponseProgram_type0 localDemandResponseProgram ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDemandResponseProgramTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return ch.iec.tc57._2009.enddevicecontrols.DemandResponseProgram_type0
                           */
                           public  ch.iec.tc57._2009.enddevicecontrols.DemandResponseProgram_type0 getDemandResponseProgram(){
                               return localDemandResponseProgram;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DemandResponseProgram
                               */
                               public void setDemandResponseProgram(ch.iec.tc57._2009.enddevicecontrols.DemandResponseProgram_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDemandResponseProgramTracker = true;
                                       } else {
                                          localDemandResponseProgramTracker = false;
                                              
                                       }
                                   
                                            this.localDemandResponseProgram=param;
                                    

                               }
                            

                        /**
                        * field for EndDeviceAsset
                        */

                        
                                    protected ch.iec.tc57._2009.enddevicecontrols.EndDeviceAsset_type0 localEndDeviceAsset ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEndDeviceAssetTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return ch.iec.tc57._2009.enddevicecontrols.EndDeviceAsset_type0
                           */
                           public  ch.iec.tc57._2009.enddevicecontrols.EndDeviceAsset_type0 getEndDeviceAsset(){
                               return localEndDeviceAsset;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EndDeviceAsset
                               */
                               public void setEndDeviceAsset(ch.iec.tc57._2009.enddevicecontrols.EndDeviceAsset_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEndDeviceAssetTracker = true;
                                       } else {
                                          localEndDeviceAssetTracker = false;
                                              
                                       }
                                   
                                            this.localEndDeviceAsset=param;
                                    

                               }
                            

                        /**
                        * field for EndDeviceGroup
                        */

                        
                                    protected ch.iec.tc57._2009.enddevicecontrols.EndDeviceGroup_type0 localEndDeviceGroup ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEndDeviceGroupTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return ch.iec.tc57._2009.enddevicecontrols.EndDeviceGroup_type0
                           */
                           public  ch.iec.tc57._2009.enddevicecontrols.EndDeviceGroup_type0 getEndDeviceGroup(){
                               return localEndDeviceGroup;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param EndDeviceGroup
                               */
                               public void setEndDeviceGroup(ch.iec.tc57._2009.enddevicecontrols.EndDeviceGroup_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEndDeviceGroupTracker = true;
                                       } else {
                                          localEndDeviceGroupTracker = false;
                                              
                                       }
                                   
                                            this.localEndDeviceGroup=param;
                                    

                               }
                            

                        /**
                        * field for ScheduledInterval
                        */

                        
                                    protected ch.iec.tc57._2009.enddevicecontrols.ScheduledInterval_type0 localScheduledInterval ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localScheduledIntervalTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return ch.iec.tc57._2009.enddevicecontrols.ScheduledInterval_type0
                           */
                           public  ch.iec.tc57._2009.enddevicecontrols.ScheduledInterval_type0 getScheduledInterval(){
                               return localScheduledInterval;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ScheduledInterval
                               */
                               public void setScheduledInterval(ch.iec.tc57._2009.enddevicecontrols.ScheduledInterval_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localScheduledIntervalTracker = true;
                                       } else {
                                          localScheduledIntervalTracker = false;
                                              
                                       }
                                   
                                            this.localScheduledInterval=param;
                                    

                               }
                            

                        /**
                        * field for ServiceDeliveryPoint
                        */

                        
                                    protected ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0 localServiceDeliveryPoint ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localServiceDeliveryPointTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0
                           */
                           public  ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0 getServiceDeliveryPoint(){
                               return localServiceDeliveryPoint;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ServiceDeliveryPoint
                               */
                               public void setServiceDeliveryPoint(ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0 param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localServiceDeliveryPointTracker = true;
                                       } else {
                                          localServiceDeliveryPointTracker = false;
                                              
                                       }
                                   
                                            this.localServiceDeliveryPoint=param;
                                    

                               }
                            

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       EndDeviceControl.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://iec.ch/TC57/2009/EndDeviceControls#");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":EndDeviceControl",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "EndDeviceControl",
                           xmlWriter);
                   }

               
                   }
                if (localMRIDTracker){
                                    namespace = "http://iec.ch/TC57/2009/EndDeviceControls#";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"mRID", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"mRID");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("mRID");
                                    }
                                

                                          if (localMRID==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("mRID cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localMRID);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDescriptionTracker){
                                    namespace = "http://iec.ch/TC57/2009/EndDeviceControls#";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"description", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"description");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("description");
                                    }
                                

                                          if (localDescription==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("description cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDescription);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDrProgramLevelTracker){
                                    namespace = "http://iec.ch/TC57/2009/EndDeviceControls#";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"drProgramLevel", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"drProgramLevel");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("drProgramLevel");
                                    }
                                

                                          if (localDrProgramLevel==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("drProgramLevel cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDrProgramLevel));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDrProgramMandatoryTracker){
                                    namespace = "http://iec.ch/TC57/2009/EndDeviceControls#";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"drProgramMandatory", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"drProgramMandatory");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("drProgramMandatory");
                                    }
                                
                                               if (false) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("drProgramMandatory cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDrProgramMandatory));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPriceSignalTracker){
                                    namespace = "http://iec.ch/TC57/2009/EndDeviceControls#";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"priceSignal", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"priceSignal");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("priceSignal");
                                    }
                                
                                               if (java.lang.Float.isNaN(localPriceSignal)) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("priceSignal cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPriceSignal));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "http://iec.ch/TC57/2009/EndDeviceControls#";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"type", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"type");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("type");
                                    }
                                

                                          if (localType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("type cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localDemandResponseProgramTracker){
                                            if (localDemandResponseProgram==null){
                                                 throw new org.apache.axis2.databinding.ADBException("DemandResponseProgram cannot be null!!");
                                            }
                                           localDemandResponseProgram.serialize(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","DemandResponseProgram"),
                                               factory,xmlWriter);
                                        } if (localEndDeviceAssetTracker){
                                            if (localEndDeviceAsset==null){
                                                 throw new org.apache.axis2.databinding.ADBException("EndDeviceAsset cannot be null!!");
                                            }
                                           localEndDeviceAsset.serialize(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","EndDeviceAsset"),
                                               factory,xmlWriter);
                                        } if (localEndDeviceGroupTracker){
                                            if (localEndDeviceGroup==null){
                                                 throw new org.apache.axis2.databinding.ADBException("EndDeviceGroup cannot be null!!");
                                            }
                                           localEndDeviceGroup.serialize(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","EndDeviceGroup"),
                                               factory,xmlWriter);
                                        } if (localScheduledIntervalTracker){
                                            if (localScheduledInterval==null){
                                                 throw new org.apache.axis2.databinding.ADBException("scheduledInterval cannot be null!!");
                                            }
                                           localScheduledInterval.serialize(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","scheduledInterval"),
                                               factory,xmlWriter);
                                        } if (localServiceDeliveryPointTracker){
                                            if (localServiceDeliveryPoint==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ServiceDeliveryPoint cannot be null!!");
                                            }
                                           localServiceDeliveryPoint.serialize(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","ServiceDeliveryPoint"),
                                               factory,xmlWriter);
                                        }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localMRIDTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "mRID"));
                                 
                                        if (localMRID != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localMRID));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("mRID cannot be null!!");
                                        }
                                    } if (localDescriptionTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "description"));
                                 
                                        if (localDescription != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescription));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("description cannot be null!!");
                                        }
                                    } if (localDrProgramLevelTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "drProgramLevel"));
                                 
                                        if (localDrProgramLevel != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDrProgramLevel));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("drProgramLevel cannot be null!!");
                                        }
                                    } if (localDrProgramMandatoryTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "drProgramMandatory"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDrProgramMandatory));
                            } if (localPriceSignalTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "priceSignal"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPriceSignal));
                            }
                                      elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "type"));
                                 
                                        if (localType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("type cannot be null!!");
                                        }
                                     if (localDemandResponseProgramTracker){
                            elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "DemandResponseProgram"));
                            
                            
                                    if (localDemandResponseProgram==null){
                                         throw new org.apache.axis2.databinding.ADBException("DemandResponseProgram cannot be null!!");
                                    }
                                    elementList.add(localDemandResponseProgram);
                                } if (localEndDeviceAssetTracker){
                            elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "EndDeviceAsset"));
                            
                            
                                    if (localEndDeviceAsset==null){
                                         throw new org.apache.axis2.databinding.ADBException("EndDeviceAsset cannot be null!!");
                                    }
                                    elementList.add(localEndDeviceAsset);
                                } if (localEndDeviceGroupTracker){
                            elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "EndDeviceGroup"));
                            
                            
                                    if (localEndDeviceGroup==null){
                                         throw new org.apache.axis2.databinding.ADBException("EndDeviceGroup cannot be null!!");
                                    }
                                    elementList.add(localEndDeviceGroup);
                                } if (localScheduledIntervalTracker){
                            elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "scheduledInterval"));
                            
                            
                                    if (localScheduledInterval==null){
                                         throw new org.apache.axis2.databinding.ADBException("scheduledInterval cannot be null!!");
                                    }
                                    elementList.add(localScheduledInterval);
                                } if (localServiceDeliveryPointTracker){
                            elementList.add(new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#",
                                                                      "ServiceDeliveryPoint"));
                            
                            
                                    if (localServiceDeliveryPoint==null){
                                         throw new org.apache.axis2.databinding.ADBException("ServiceDeliveryPoint cannot be null!!");
                                    }
                                    elementList.add(localServiceDeliveryPoint);
                                }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static EndDeviceControl parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            EndDeviceControl object =
                new EndDeviceControl();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"EndDeviceControl".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (EndDeviceControl)ch.iec.www.tc57._2008.schema.message.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","mRID").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setMRID(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","description").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDescription(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","drProgramLevel").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDrProgramLevel(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInteger(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","drProgramMandatory").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDrProgramMandatory(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","priceSignal").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPriceSignal(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToFloat(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setPriceSignal(java.lang.Float.NaN);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","type").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","DemandResponseProgram").equals(reader.getName())){
                                
                                                object.setDemandResponseProgram(ch.iec.tc57._2009.enddevicecontrols.DemandResponseProgram_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","EndDeviceAsset").equals(reader.getName())){
                                
                                                object.setEndDeviceAsset(ch.iec.tc57._2009.enddevicecontrols.EndDeviceAsset_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","EndDeviceGroup").equals(reader.getName())){
                                
                                                object.setEndDeviceGroup(ch.iec.tc57._2009.enddevicecontrols.EndDeviceGroup_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","scheduledInterval").equals(reader.getName())){
                                
                                                object.setScheduledInterval(ch.iec.tc57._2009.enddevicecontrols.ScheduledInterval_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://iec.ch/TC57/2009/EndDeviceControls#","ServiceDeliveryPoint").equals(reader.getName())){
                                
                                                object.setServiceDeliveryPoint(ch.iec.tc57._2009.enddevicecontrols.ServiceDeliveryPoint_type0.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          