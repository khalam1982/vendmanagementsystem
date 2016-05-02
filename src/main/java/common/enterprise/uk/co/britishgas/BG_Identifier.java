
/**
 * BG_Identifier.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package common.enterprise.uk.co.britishgas;
            

            /**
            *  BG_Identifier bean class
            */
        
        public  class BG_Identifier extends common.enterprise.uk.co.britishgas.BG_IdentifierContent
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = BG_Identifier
                Namespace URI = urn:britishgas.co.uk:Enterprise:Common
                Namespace Prefix = ns2
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:britishgas.co.uk:Enterprise:Common")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for BG_IdentifierContent
                        */

                        

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.Token
                           */
                           public  org.apache.axis2.databinding.types.Token getBG_IdentifierContent(){
                               return localBG_IdentifierContent;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BG_IdentifierContent
                               */
                               public void setBG_IdentifierContent(org.apache.axis2.databinding.types.Token param){
                            
                                            if (  (1 <= java.lang.String.valueOf(param).length()) &&  (java.lang.String.valueOf(param).length() <= 40)  ) {
                                                this.localBG_IdentifierContent=param;
                                            }
                                            else {
                                                throw new java.lang.RuntimeException();
                                            }
                                        

                               }
                            

                            public java.lang.String toString(){
                                
                                        return localBG_IdentifierContent.toString();
                                    
                            }
                        

                        /**
                        * field for SchemeID
                        * This was an Attribute!
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.SchemeID_type0 localSchemeID ;
                                

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.SchemeID_type0
                           */
                           public  common.enterprise.uk.co.britishgas.SchemeID_type0 getSchemeID(){
                               return localSchemeID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SchemeID
                               */
                               public void setSchemeID(common.enterprise.uk.co.britishgas.SchemeID_type0 param){
                            
                                            this.localSchemeID=param;
                                    

                               }
                            

                        /**
                        * field for SchemeAgencyID
                        * This was an Attribute!
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.SchemeAgencyID_type0 localSchemeAgencyID ;
                                

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.SchemeAgencyID_type0
                           */
                           public  common.enterprise.uk.co.britishgas.SchemeAgencyID_type0 getSchemeAgencyID(){
                               return localSchemeAgencyID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SchemeAgencyID
                               */
                               public void setSchemeAgencyID(common.enterprise.uk.co.britishgas.SchemeAgencyID_type0 param){
                            
                                            this.localSchemeAgencyID=param;
                                    

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
                       BG_Identifier.this.serialize(parentQName,factory,xmlWriter);
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
                

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:britishgas.co.uk:Enterprise:Common");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":BG_Identifier",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "BG_Identifier",
                           xmlWriter);
                   }

               
                                    
                                    if (localSchemeID != null){
                                        writeAttribute("",
                                           "schemeID",
                                           localSchemeID.toString(), xmlWriter);
                                    }
                                    
                                    
                                    if (localSchemeAgencyID != null){
                                        writeAttribute("",
                                           "schemeAgencyID",
                                           localSchemeAgencyID.toString(), xmlWriter);
                                    }
                                    

                                          if (localBG_IdentifierContent==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("BG_Identifier.Content cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBG_IdentifierContent));
                                            
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

                
                    attribList.add(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema-instance","type"));
                    attribList.add(new javax.xml.namespace.QName("urn:britishgas.co.uk:Enterprise:Common","BG_Identifier"));
                
                                     
                                     elementList.add(org.apache.axis2.databinding.utils.reader.ADBXMLStreamReader.ELEMENT_TEXT);
                                 
                                        if (localBG_IdentifierContent != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBG_IdentifierContent));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("BG_Identifier.Content cannot be null!!");
                                        }
                                    
                            attribList.add(
                            new javax.xml.namespace.QName("","schemeID"));
                            
                                      attribList.add(localSchemeID.toString());
                                
                            attribList.add(
                            new javax.xml.namespace.QName("","schemeAgencyID"));
                            
                                      attribList.add(localSchemeAgencyID.toString());
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        
                public static BG_Identifier fromString(java.lang.String value,
                                                    java.lang.String namespaceURI){
                    BG_Identifier returnValue = new  BG_Identifier();
                    
                            returnValue.setBG_IdentifierContent(
                                org.apache.axis2.databinding.utils.ConverterUtil.convertToToken(value));
                        

                    return returnValue;
                }

                public static BG_Identifier fromString(javax.xml.stream.XMLStreamReader xmlStreamReader,
                                                                    java.lang.String content) {
                    if (content.indexOf(":") > -1){
                        java.lang.String prefix = content.substring(0,content.indexOf(":"));
                        java.lang.String namespaceUri = xmlStreamReader.getNamespaceContext().getNamespaceURI(prefix);
                        return BG_Identifier.Factory.fromString(content,namespaceUri);
                    } else {
                       return BG_Identifier.Factory.fromString(content,"");
                    }
                }

            

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static BG_Identifier parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            BG_Identifier object =
                new BG_Identifier();

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
                    
                            if (!"BG_Identifier".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (BG_Identifier)common.enterprise.uk.co.britishgas.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    // handle attribute "schemeID"
                    java.lang.String tempAttribSchemeID =
                        
                                reader.getAttributeValue(null,"schemeID");
                            
                   if (tempAttribSchemeID!=null){
                         java.lang.String content = tempAttribSchemeID;
                        
                                                  object.setSchemeID(
                                                        common.enterprise.uk.co.britishgas.SchemeID_type0.Factory.fromString(reader,tempAttribSchemeID));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("schemeID");
                    
                    // handle attribute "schemeAgencyID"
                    java.lang.String tempAttribSchemeAgencyID =
                        
                                reader.getAttributeValue(null,"schemeAgencyID");
                            
                   if (tempAttribSchemeAgencyID!=null){
                         java.lang.String content = tempAttribSchemeAgencyID;
                        
                                                  object.setSchemeAgencyID(
                                                        common.enterprise.uk.co.britishgas.SchemeAgencyID_type0.Factory.fromString(reader,tempAttribSchemeAgencyID));
                                            
                    } else {
                       
                    }
                    handledAttributes.add("schemeAgencyID");
                       
                while(!reader.isEndElement()) {
                    if (reader.isStartElement()  || reader.hasText()){
                
                                    if (reader.isStartElement()  || reader.hasText()){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBG_IdentifierContent(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToToken(content));
                                            
                              }  // End of if for expected property start element
                                
                             else{
                                        // A start element we are not expecting indicates an invalid parameter was passed
                                        throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                             }
                          
                             } else {
                                reader.next();
                             }  
                           }  // end of while loop
                        



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          