
/**
 * VendTransaction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package com.centrica.vms.ws.transaction.query;
            

            /**
            *  VendTransaction bean class
            */
        
        public  class VendTransaction
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = VendTransaction
                Namespace URI = http://query.transaction.ws.vms.centrica.com
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://query.transaction.ws.vms.centrica.com")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for VendDateTimeStamp
                        */

                        
                                    protected com.centrica.vms.ws.transaction.query.VendDateTimeStamp localVendDateTimeStamp ;
                                

                           /**
                           * Auto generated getter method
                           * @return com.centrica.vms.ws.transaction.query.VendDateTimeStamp
                           */
                           public  com.centrica.vms.ws.transaction.query.VendDateTimeStamp getVendDateTimeStamp(){
                               return localVendDateTimeStamp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendDateTimeStamp
                               */
                               public void setVendDateTimeStamp(com.centrica.vms.ws.transaction.query.VendDateTimeStamp param){
                            
                                            this.localVendDateTimeStamp=param;
                                    

                               }
                            

                        /**
                        * field for Channel
                        */

                        
                                    protected com.centrica.vms.ws.transaction.query.Channel localChannel ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localChannelTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.centrica.vms.ws.transaction.query.Channel
                           */
                           public  com.centrica.vms.ws.transaction.query.Channel getChannel(){
                               return localChannel;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Channel
                               */
                               public void setChannel(com.centrica.vms.ws.transaction.query.Channel param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localChannelTracker = true;
                                       } else {
                                          localChannelTracker = false;
                                              
                                       }
                                   
                                            this.localChannel=param;
                                    

                               }
                            

                        /**
                        * field for VendCode
                        */

                        
                                    protected com.centrica.vms.ws.transaction.query.VendCode localVendCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localVendCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.centrica.vms.ws.transaction.query.VendCode
                           */
                           public  com.centrica.vms.ws.transaction.query.VendCode getVendCode(){
                               return localVendCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendCode
                               */
                               public void setVendCode(com.centrica.vms.ws.transaction.query.VendCode param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localVendCodeTracker = true;
                                       } else {
                                          localVendCodeTracker = false;
                                              
                                       }
                                   
                                            this.localVendCode=param;
                                    

                               }
                            

                        /**
                        * field for VendAmount
                        */

                        
                                    protected com.centrica.vms.ws.transaction.query.VendAmount localVendAmount ;
                                

                           /**
                           * Auto generated getter method
                           * @return com.centrica.vms.ws.transaction.query.VendAmount
                           */
                           public  com.centrica.vms.ws.transaction.query.VendAmount getVendAmount(){
                               return localVendAmount;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendAmount
                               */
                               public void setVendAmount(com.centrica.vms.ws.transaction.query.VendAmount param){
                            
                                            this.localVendAmount=param;
                                    

                               }
                            

                        /**
                        * field for Status
                        */

                        
                                    protected com.centrica.vms.ws.transaction.query.Status localStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localStatusTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.centrica.vms.ws.transaction.query.Status
                           */
                           public  com.centrica.vms.ws.transaction.query.Status getStatus(){
                               return localStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Status
                               */
                               public void setStatus(com.centrica.vms.ws.transaction.query.Status param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localStatusTracker = true;
                                       } else {
                                          localStatusTracker = false;
                                              
                                       }
                                   
                                            this.localStatus=param;
                                    

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
                       VendTransaction.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://query.transaction.ws.vms.centrica.com");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":VendTransaction",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "VendTransaction",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localVendDateTimeStamp==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendDateTimeStamp cannot be null!!");
                                            }
                                           localVendDateTimeStamp.serialize(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","VendDateTimeStamp"),
                                               factory,xmlWriter);
                                         if (localChannelTracker){
                                            if (localChannel==null){
                                                 throw new org.apache.axis2.databinding.ADBException("Channel cannot be null!!");
                                            }
                                           localChannel.serialize(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","Channel"),
                                               factory,xmlWriter);
                                        } if (localVendCodeTracker){
                                            if (localVendCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendCode cannot be null!!");
                                            }
                                           localVendCode.serialize(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","VendCode"),
                                               factory,xmlWriter);
                                        }
                                            if (localVendAmount==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendAmount cannot be null!!");
                                            }
                                           localVendAmount.serialize(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","VendAmount"),
                                               factory,xmlWriter);
                                         if (localStatusTracker){
                                            if (localStatus==null){
                                                 throw new org.apache.axis2.databinding.ADBException("Status cannot be null!!");
                                            }
                                           localStatus.serialize(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","Status"),
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

                
                            elementList.add(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com",
                                                                      "VendDateTimeStamp"));
                            
                            
                                    if (localVendDateTimeStamp==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendDateTimeStamp cannot be null!!");
                                    }
                                    elementList.add(localVendDateTimeStamp);
                                 if (localChannelTracker){
                            elementList.add(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com",
                                                                      "Channel"));
                            
                            
                                    if (localChannel==null){
                                         throw new org.apache.axis2.databinding.ADBException("Channel cannot be null!!");
                                    }
                                    elementList.add(localChannel);
                                } if (localVendCodeTracker){
                            elementList.add(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com",
                                                                      "VendCode"));
                            
                            
                                    if (localVendCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendCode cannot be null!!");
                                    }
                                    elementList.add(localVendCode);
                                }
                            elementList.add(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com",
                                                                      "VendAmount"));
                            
                            
                                    if (localVendAmount==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendAmount cannot be null!!");
                                    }
                                    elementList.add(localVendAmount);
                                 if (localStatusTracker){
                            elementList.add(new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com",
                                                                      "Status"));
                            
                            
                                    if (localStatus==null){
                                         throw new org.apache.axis2.databinding.ADBException("Status cannot be null!!");
                                    }
                                    elementList.add(localStatus);
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
        public static VendTransaction parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            VendTransaction object =
                new VendTransaction();

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
                    
                            if (!"VendTransaction".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (VendTransaction)com.centrica.vms.ws.transaction.query.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","VendDateTimeStamp").equals(reader.getName())){
                                
                                                object.setVendDateTimeStamp(com.centrica.vms.ws.transaction.query.VendDateTimeStamp.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","Channel").equals(reader.getName())){
                                
                                                object.setChannel(com.centrica.vms.ws.transaction.query.Channel.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","VendCode").equals(reader.getName())){
                                
                                                object.setVendCode(com.centrica.vms.ws.transaction.query.VendCode.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","VendAmount").equals(reader.getName())){
                                
                                                object.setVendAmount(com.centrica.vms.ws.transaction.query.VendAmount.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://query.transaction.ws.vms.centrica.com","Status").equals(reader.getName())){
                                
                                                object.setStatus(com.centrica.vms.ws.transaction.query.Status.Factory.parse(reader));
                                              
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
           
          