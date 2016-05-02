
/**
 * TransactionEventNotificationMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package smartvend.enterprise.uk.co.britishgas;
            

            /**
            *  TransactionEventNotificationMessage bean class
            */
        
        public  class TransactionEventNotificationMessage
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = TransactionEventNotificationMessage
                Namespace URI = urn:britishgas.co.uk:Enterprise:SmartVend
                Namespace Prefix = ns3
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:britishgas.co.uk:Enterprise:SmartVend")){
                return "ns3";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for MessageHeader
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.BG_MessageHeader localMessageHeader ;
                                

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.BG_MessageHeader
                           */
                           public  common.enterprise.uk.co.britishgas.BG_MessageHeader getMessageHeader(){
                               return localMessageHeader;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param MessageHeader
                               */
                               public void setMessageHeader(common.enterprise.uk.co.britishgas.BG_MessageHeader param){
                            
                                            this.localMessageHeader=param;
                                    

                               }
                            

                        /**
                        * field for PANNumber
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber localPANNumber ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber getPANNumber(){
                               return localPANNumber;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PANNumber
                               */
                               public void setPANNumber(smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber param){
                            
                                            this.localPANNumber=param;
                                    

                               }
                            

                        /**
                        * field for VendAmount
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount localVendAmount ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount getVendAmount(){
                               return localVendAmount;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendAmount
                               */
                               public void setVendAmount(smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount param){
                            
                                            this.localVendAmount=param;
                                    

                               }
                            

                        /**
                        * field for VendProcessState
                        */

                        
                                    protected smartvend.enterprise.uk.co.britishgas.VendProcessState localVendProcessState ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartvend.enterprise.uk.co.britishgas.VendProcessState
                           */
                           public  smartvend.enterprise.uk.co.britishgas.VendProcessState getVendProcessState(){
                               return localVendProcessState;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendProcessState
                               */
                               public void setVendProcessState(smartvend.enterprise.uk.co.britishgas.VendProcessState param){
                            
                                            this.localVendProcessState=param;
                                    

                               }
                            

                        /**
                        * field for VendCode
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendCode localVendCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendCode
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendCode getVendCode(){
                               return localVendCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendCode
                               */
                               public void setVendCode(smartmeterprocessing.enterprise.uk.co.britishgas.VendCode param){
                            
                                            this.localVendCode=param;
                                    

                               }
                            

                        /**
                        * field for VendHistoryTimestamps
                        */

                        
                                    protected smartvend.enterprise.uk.co.britishgas.VendHistoryTimestamps localVendHistoryTimestamps ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartvend.enterprise.uk.co.britishgas.VendHistoryTimestamps
                           */
                           public  smartvend.enterprise.uk.co.britishgas.VendHistoryTimestamps getVendHistoryTimestamps(){
                               return localVendHistoryTimestamps;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendHistoryTimestamps
                               */
                               public void setVendHistoryTimestamps(smartvend.enterprise.uk.co.britishgas.VendHistoryTimestamps param){
                            
                                            this.localVendHistoryTimestamps=param;
                                    

                               }
                            

                        /**
                        * field for VendRequestAdditional
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional localVendRequestAdditional ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional getVendRequestAdditional(){
                               return localVendRequestAdditional;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendRequestAdditional
                               */
                               public void setVendRequestAdditional(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional param){
                            
                                            this.localVendRequestAdditional=param;
                                    

                               }
                            

                        /**
                        * field for VendRequestType
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestType localVendRequestType ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestType
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestType getVendRequestType(){
                               return localVendRequestType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendRequestType
                               */
                               public void setVendRequestType(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestType param){
                            
                                            this.localVendRequestType=param;
                                    

                               }
                            

                        /**
                        * field for VendRequestKey
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey localVendRequestKey ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey getVendRequestKey(){
                               return localVendRequestKey;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendRequestKey
                               */
                               public void setVendRequestKey(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey param){
                            
                                            this.localVendRequestKey=param;
                                    

                               }
                            

                        /**
                        * field for VendRequestTimestamp
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp localVendRequestTimestamp ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp getVendRequestTimestamp(){
                               return localVendRequestTimestamp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendRequestTimestamp
                               */
                               public void setVendRequestTimestamp(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp param){
                            
                                            this.localVendRequestTimestamp=param;
                                    

                               }
                            

                        /**
                        * field for OriginalVendRequestKey
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey localOriginalVendRequestKey ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localOriginalVendRequestKeyTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey getOriginalVendRequestKey(){
                               return localOriginalVendRequestKey;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param OriginalVendRequestKey
                               */
                               public void setOriginalVendRequestKey(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localOriginalVendRequestKeyTracker = true;
                                       } else {
                                          localOriginalVendRequestKeyTracker = false;
                                              
                                       }
                                   
                                            this.localOriginalVendRequestKey=param;
                                    

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
                       TransactionEventNotificationMessage.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:britishgas.co.uk:Enterprise:SmartVend");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":TransactionEventNotificationMessage",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "TransactionEventNotificationMessage",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localMessageHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                            }
                                           localMessageHeader.serialize(new javax.xml.namespace.QName("","MessageHeader"),
                                               factory,xmlWriter);
                                        
                                            if (localPANNumber==null){
                                                 throw new org.apache.axis2.databinding.ADBException("PANNumber cannot be null!!");
                                            }
                                           localPANNumber.serialize(new javax.xml.namespace.QName("","PANNumber"),
                                               factory,xmlWriter);
                                        
                                            if (localVendAmount==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendAmount cannot be null!!");
                                            }
                                           localVendAmount.serialize(new javax.xml.namespace.QName("","VendAmount"),
                                               factory,xmlWriter);
                                        
                                            if (localVendProcessState==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendProcessState cannot be null!!");
                                            }
                                           localVendProcessState.serialize(new javax.xml.namespace.QName("","VendProcessState"),
                                               factory,xmlWriter);
                                        
                                            if (localVendCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendCode cannot be null!!");
                                            }
                                           localVendCode.serialize(new javax.xml.namespace.QName("","VendCode"),
                                               factory,xmlWriter);
                                        
                                            if (localVendHistoryTimestamps==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendHistoryTimestamps cannot be null!!");
                                            }
                                           localVendHistoryTimestamps.serialize(new javax.xml.namespace.QName("","VendHistoryTimestamps"),
                                               factory,xmlWriter);
                                        
                                            if (localVendRequestAdditional==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendRequestAdditional cannot be null!!");
                                            }
                                           localVendRequestAdditional.serialize(new javax.xml.namespace.QName("","VendRequestAdditional"),
                                               factory,xmlWriter);
                                        
                                            if (localVendRequestType==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendRequestType cannot be null!!");
                                            }
                                           localVendRequestType.serialize(new javax.xml.namespace.QName("","VendRequestType"),
                                               factory,xmlWriter);
                                        
                                            if (localVendRequestKey==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendRequestKey cannot be null!!");
                                            }
                                           localVendRequestKey.serialize(new javax.xml.namespace.QName("","VendRequestKey"),
                                               factory,xmlWriter);
                                        
                                            if (localVendRequestTimestamp==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendRequestTimestamp cannot be null!!");
                                            }
                                           localVendRequestTimestamp.serialize(new javax.xml.namespace.QName("","VendRequestTimestamp"),
                                               factory,xmlWriter);
                                         if (localOriginalVendRequestKeyTracker){
                                            if (localOriginalVendRequestKey==null){
                                                 throw new org.apache.axis2.databinding.ADBException("OriginalVendRequestKey cannot be null!!");
                                            }
                                           localOriginalVendRequestKey.serialize(new javax.xml.namespace.QName("","OriginalVendRequestKey"),
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

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "MessageHeader"));
                            
                            
                                    if (localMessageHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                    }
                                    elementList.add(localMessageHeader);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "PANNumber"));
                            
                            
                                    if (localPANNumber==null){
                                         throw new org.apache.axis2.databinding.ADBException("PANNumber cannot be null!!");
                                    }
                                    elementList.add(localPANNumber);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendAmount"));
                            
                            
                                    if (localVendAmount==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendAmount cannot be null!!");
                                    }
                                    elementList.add(localVendAmount);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendProcessState"));
                            
                            
                                    if (localVendProcessState==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendProcessState cannot be null!!");
                                    }
                                    elementList.add(localVendProcessState);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendCode"));
                            
                            
                                    if (localVendCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendCode cannot be null!!");
                                    }
                                    elementList.add(localVendCode);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendHistoryTimestamps"));
                            
                            
                                    if (localVendHistoryTimestamps==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendHistoryTimestamps cannot be null!!");
                                    }
                                    elementList.add(localVendHistoryTimestamps);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendRequestAdditional"));
                            
                            
                                    if (localVendRequestAdditional==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendRequestAdditional cannot be null!!");
                                    }
                                    elementList.add(localVendRequestAdditional);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendRequestType"));
                            
                            
                                    if (localVendRequestType==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendRequestType cannot be null!!");
                                    }
                                    elementList.add(localVendRequestType);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendRequestKey"));
                            
                            
                                    if (localVendRequestKey==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendRequestKey cannot be null!!");
                                    }
                                    elementList.add(localVendRequestKey);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendRequestTimestamp"));
                            
                            
                                    if (localVendRequestTimestamp==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendRequestTimestamp cannot be null!!");
                                    }
                                    elementList.add(localVendRequestTimestamp);
                                 if (localOriginalVendRequestKeyTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "OriginalVendRequestKey"));
                            
                            
                                    if (localOriginalVendRequestKey==null){
                                         throw new org.apache.axis2.databinding.ADBException("OriginalVendRequestKey cannot be null!!");
                                    }
                                    elementList.add(localOriginalVendRequestKey);
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
        public static TransactionEventNotificationMessage parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            TransactionEventNotificationMessage object =
                new TransactionEventNotificationMessage();

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
                    
                            if (!"TransactionEventNotificationMessage".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (TransactionEventNotificationMessage)smartmeterprocessing.enterprise.uk.co.britishgas.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","MessageHeader").equals(reader.getName())){
                                
                                                object.setMessageHeader(common.enterprise.uk.co.britishgas.BG_MessageHeader.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","PANNumber").equals(reader.getName())){
                                
                                                object.setPANNumber(smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendAmount").equals(reader.getName())){
                                
                                                object.setVendAmount(smartmeterprocessing.enterprise.uk.co.britishgas.VendAmount.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendProcessState").equals(reader.getName())){
                                
                                                object.setVendProcessState(smartvend.enterprise.uk.co.britishgas.VendProcessState.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendCode").equals(reader.getName())){
                                
                                                object.setVendCode(smartmeterprocessing.enterprise.uk.co.britishgas.VendCode.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendHistoryTimestamps").equals(reader.getName())){
                                
                                                object.setVendHistoryTimestamps(smartvend.enterprise.uk.co.britishgas.VendHistoryTimestamps.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendRequestAdditional").equals(reader.getName())){
                                
                                                object.setVendRequestAdditional(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendRequestType").equals(reader.getName())){
                                
                                                object.setVendRequestType(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendRequestKey").equals(reader.getName())){
                                
                                                object.setVendRequestKey(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendRequestTimestamp").equals(reader.getName())){
                                
                                                object.setVendRequestTimestamp(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","OriginalVendRequestKey").equals(reader.getName())){
                                
                                                object.setOriginalVendRequestKey(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey.Factory.parse(reader));
                                              
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
           
          