package com.trilliantnetworks.unity.ws;


public class PrepaymentKeyStatus
implements org.apache.axis2.databinding.ADBBean {


    /* This type was generated from the piece of schema that had
            name = PrepaymentKeyStatus
            Namespace URI = urn:prepay.ws.unity.trilliantnetworks.com
            Namespace Prefix = ns2
            */
        

    private static java.lang.String generatePrefix(java.lang.String namespace) {
        if(namespace.equals("urn:prepay.ws.unity.trilliantnetworks.com")){
            return "ns2";
        }
        return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
    }

    

                    /**
                    * field for TransactionId
                    */

                    
                                protected java.lang.String localTransactionId ;
                            

                       /**
                       * Auto generated getter method
                       * @return java.lang.String
                       */
                       public  java.lang.String getTransactionId(){
                           return localTransactionId;
                       }

                       
                    
                        /**
                           * Auto generated setter method
                           * @param param TransactionId
                           */
                           public void setTransactionId(java.lang.String param){
                        
                                        this.localTransactionId=param;
                                

                           }
                        

                    /**
                    * field for DeviceSerialNo
                    */

                    
                                protected java.lang.String localDeviceSerialNo ;
                            

                       /**
                       * Auto generated getter method
                       * @return java.lang.String
                       */
                       public  java.lang.String getDeviceSerialNo(){
                           return localDeviceSerialNo;
                       }

                       
                    
                        /**
                           * Auto generated setter method
                           * @param param DeviceSerialNo
                           */
                           public void setDeviceSerialNo(java.lang.String param){
                        
                                        this.localDeviceSerialNo=param;
                                

                           }
                        

                    /**
                    * field for DeliveryStatus
                    */

                    
                                protected StatusCode localDeliveryStatus ;
                            

                       /**
                       * Auto generated getter method
                       * @return StatusCode
                       */
                       public  StatusCode getDeliveryStatus(){
                           return localDeliveryStatus;
                       }

                       
                    
                        /**
                           * Auto generated setter method
                           * @param param DeliveryStatus
                           */
                           public void setDeliveryStatus(StatusCode param){
                        
                                        this.localDeliveryStatus=param;
                                

                           }
                        

                    /**
                    * field for DeviceClass
                    */

                    
                                protected java.lang.String localDeviceClass ;
                            
                       /*  This tracker boolean wil be used to detect whether the user called the set method
                      *   for this attribute. It will be used to determine whether to include this field
                       *   in the serialized XML
                       */
                       protected boolean localDeviceClassTracker = false ;
                       

                       /**
                       * Auto generated getter method
                       * @return java.lang.String
                       */
                       public  java.lang.String getDeviceClass(){
                           return localDeviceClass;
                       }

                       
                    
                        /**
                           * Auto generated setter method
                           * @param param DeviceClass
                           */
                           public void setDeviceClass(java.lang.String param){
                        
                                   if (param != null){
                                      //update the setting tracker
                                      localDeviceClassTracker = true;
                                   } else {
                                      localDeviceClassTracker = false;
                                          
                                   }
                               
                                        this.localDeviceClass=param;
                                

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
                   PrepaymentKeyStatus.this.serialize(parentQName,factory,xmlWriter);
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
           

               java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:prepay.ws.unity.trilliantnetworks.com");
               if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                   writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                       namespacePrefix+":PrepaymentKeyStatus",
                       xmlWriter);
               } else {
                   writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                       "PrepaymentKeyStatus",
                       xmlWriter);
               }

           
               }
           
                                namespace = "urn:prepay.ws.unity.trilliantnetworks.com";
                                if (! namespace.equals("")) {
                                    prefix = xmlWriter.getPrefix(namespace);

                                    if (prefix == null) {
                                        prefix = generatePrefix(namespace);

                                        xmlWriter.writeStartElement(prefix,"transactionId", namespace);
                                        xmlWriter.writeNamespace(prefix, namespace);
                                        xmlWriter.setPrefix(prefix, namespace);

                                    } else {
                                        xmlWriter.writeStartElement(namespace,"transactionId");
                                    }

                                } else {
                                    xmlWriter.writeStartElement("transactionId");
                                }
                            

                                      if (localTransactionId==null){
                                          // write the nil attribute
                                          
                                                 throw new org.apache.axis2.databinding.ADBException("transactionId cannot be null!!");
                                              
                                      }else{

                                    
                                               xmlWriter.writeCharacters(localTransactionId);
                                        
                                      }
                                
                               xmlWriter.writeEndElement();
                         
                                namespace = "urn:prepay.ws.unity.trilliantnetworks.com";
                                if (! namespace.equals("")) {
                                    prefix = xmlWriter.getPrefix(namespace);

                                    if (prefix == null) {
                                        prefix = generatePrefix(namespace);

                                        xmlWriter.writeStartElement(prefix,"deviceSerialNo", namespace);
                                        xmlWriter.writeNamespace(prefix, namespace);
                                        xmlWriter.setPrefix(prefix, namespace);

                                    } else {
                                        xmlWriter.writeStartElement(namespace,"deviceSerialNo");
                                    }

                                } else {
                                    xmlWriter.writeStartElement("deviceSerialNo");
                                }
                            

                                      if (localDeviceSerialNo==null){
                                          // write the nil attribute
                                          
                                                 throw new org.apache.axis2.databinding.ADBException("deviceSerialNo cannot be null!!");
                                              
                                      }else{

                                    
                                               xmlWriter.writeCharacters(localDeviceSerialNo);
                                        
                                      }
                                
                               xmlWriter.writeEndElement();
                         
                                        if (localDeliveryStatus==null){
                                             throw new org.apache.axis2.databinding.ADBException("deliveryStatus cannot be null!!");
                                        }
                                       localDeliveryStatus.serialize(new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com","deliveryStatus"),
                                           factory,xmlWriter);
                                     if (localDeviceClassTracker){
                                namespace = "urn:prepay.ws.unity.trilliantnetworks.com";
                                if (! namespace.equals("")) {
                                    prefix = xmlWriter.getPrefix(namespace);

                                    if (prefix == null) {
                                        prefix = generatePrefix(namespace);

                                        xmlWriter.writeStartElement(prefix,"deviceClass", namespace);
                                        xmlWriter.writeNamespace(prefix, namespace);
                                        xmlWriter.setPrefix(prefix, namespace);

                                    } else {
                                        xmlWriter.writeStartElement(namespace,"deviceClass");
                                    }

                                } else {
                                    xmlWriter.writeStartElement("deviceClass");
                                }
                            

                                      if (localDeviceClass==null){
                                          // write the nil attribute
                                          
                                                 throw new org.apache.axis2.databinding.ADBException("deviceClass cannot be null!!");
                                              
                                      }else{

                                    
                                               xmlWriter.writeCharacters(localDeviceClass);
                                        
                                      }
                                
                               xmlWriter.writeEndElement();
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

            
                                  elementList.add(new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com",
                                                                  "transactionId"));
                             
                                    if (localTransactionId != null){
                                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTransactionId));
                                    } else {
                                       throw new org.apache.axis2.databinding.ADBException("transactionId cannot be null!!");
                                    }
                                
                                  elementList.add(new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com",
                                                                  "deviceSerialNo"));
                             
                                    if (localDeviceSerialNo != null){
                                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeviceSerialNo));
                                    } else {
                                       throw new org.apache.axis2.databinding.ADBException("deviceSerialNo cannot be null!!");
                                    }
                                
                        elementList.add(new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com",
                                                                  "deliveryStatus"));
                        
                        
                                if (localDeliveryStatus==null){
                                     throw new org.apache.axis2.databinding.ADBException("deliveryStatus cannot be null!!");
                                }
                                elementList.add(localDeliveryStatus);
                             if (localDeviceClassTracker){
                                  elementList.add(new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com",
                                                                  "deviceClass"));
                             
                                    if (localDeviceClass != null){
                                        elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDeviceClass));
                                    } else {
                                       throw new org.apache.axis2.databinding.ADBException("deviceClass cannot be null!!");
                                    }
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
    public static PrepaymentKeyStatus parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
        PrepaymentKeyStatus object =
            new PrepaymentKeyStatus();

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
                
                        if (!"PrepaymentKeyStatus".equals(type)){
                            //find namespace for the prefix
                            java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                            return (PrepaymentKeyStatus)ExtensionMapper.getTypeObject(
                                 nsUri,type,reader);
                          }
                    

              }
            

            }

            

            
            // Note all attributes that were handled. Used to differ normal attributes
            // from anyAttributes.
            java.util.Vector handledAttributes = new java.util.Vector();
            

             
                
                reader.next();
            
                                
                                while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                            
                                if (reader.isStartElement() && new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com","transactionId").equals(reader.getName())){
                            
                                java.lang.String content = reader.getElementText();
                                
                                          object.setTransactionId(
                                                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                          
                                    reader.next();
                                
                          }  // End of if for expected property start element
                            
                            else{
                                // A start element we are not expecting indicates an invalid parameter was passed
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            }
                        
                                
                                while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                            
                                if (reader.isStartElement() && new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com","deviceSerialNo").equals(reader.getName())){
                            
                                java.lang.String content = reader.getElementText();
                                
                                          object.setDeviceSerialNo(
                                                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                          
                                    reader.next();
                                
                          }  // End of if for expected property start element
                            
                            else{
                                // A start element we are not expecting indicates an invalid parameter was passed
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            }
                        
                                
                                while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                            
                                if (reader.isStartElement() && new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com","deliveryStatus").equals(reader.getName())){
                            
                                            object.setDeliveryStatus(StatusCode.Factory.parse(reader));
                                          
                                    reader.next();
                                
                          }  // End of if for expected property start element
                            
                            else{
                                // A start element we are not expecting indicates an invalid parameter was passed
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            }
                        
                                
                                while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                            
                                if (reader.isStartElement() && new javax.xml.namespace.QName("urn:prepay.ws.unity.trilliantnetworks.com","deviceClass").equals(reader.getName())){
                            
                                java.lang.String content = reader.getElementText();
                                
                                          object.setDeviceClass(
                                                org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                          
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
