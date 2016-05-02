
/**
 * BG_LogItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package common.enterprise.uk.co.britishgas;
            

            /**
            *  BG_LogItem bean class
            */
        
        public  class BG_LogItem
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = BG_LogItem
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
                        * field for TypeID
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.BG_LogItemTypeID localTypeID ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTypeIDTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.BG_LogItemTypeID
                           */
                           public  common.enterprise.uk.co.britishgas.BG_LogItemTypeID getTypeID(){
                               return localTypeID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TypeID
                               */
                               public void setTypeID(common.enterprise.uk.co.britishgas.BG_LogItemTypeID param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTypeIDTracker = true;
                                       } else {
                                          localTypeIDTracker = false;
                                              
                                       }
                                   
                                            this.localTypeID=param;
                                    

                               }
                            

                        /**
                        * field for CategoryCode
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.BG_LogItemCategoryCode localCategoryCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCategoryCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.BG_LogItemCategoryCode
                           */
                           public  common.enterprise.uk.co.britishgas.BG_LogItemCategoryCode getCategoryCode(){
                               return localCategoryCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CategoryCode
                               */
                               public void setCategoryCode(common.enterprise.uk.co.britishgas.BG_LogItemCategoryCode param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCategoryCodeTracker = true;
                                       } else {
                                          localCategoryCodeTracker = false;
                                              
                                       }
                                   
                                            this.localCategoryCode=param;
                                    

                               }
                            

                        /**
                        * field for SeverityCode
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.BG_LogItemSeverityCode localSeverityCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSeverityCodeTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.BG_LogItemSeverityCode
                           */
                           public  common.enterprise.uk.co.britishgas.BG_LogItemSeverityCode getSeverityCode(){
                               return localSeverityCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SeverityCode
                               */
                               public void setSeverityCode(common.enterprise.uk.co.britishgas.BG_LogItemSeverityCode param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localSeverityCodeTracker = true;
                                       } else {
                                          localSeverityCodeTracker = false;
                                              
                                       }
                                   
                                            this.localSeverityCode=param;
                                    

                               }
                            

                        /**
                        * field for Note
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.BG_LogItemNote localNote ;
                                

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.BG_LogItemNote
                           */
                           public  common.enterprise.uk.co.britishgas.BG_LogItemNote getNote(){
                               return localNote;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Note
                               */
                               public void setNote(common.enterprise.uk.co.britishgas.BG_LogItemNote param){
                            
                                            this.localNote=param;
                                    

                               }
                            

                        /**
                        * field for WebURI
                        */

                        
                                    protected common.enterprise.uk.co.britishgas.BG_WebURI localWebURI ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localWebURITracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return common.enterprise.uk.co.britishgas.BG_WebURI
                           */
                           public  common.enterprise.uk.co.britishgas.BG_WebURI getWebURI(){
                               return localWebURI;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param WebURI
                               */
                               public void setWebURI(common.enterprise.uk.co.britishgas.BG_WebURI param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localWebURITracker = true;
                                       } else {
                                          localWebURITracker = false;
                                              
                                       }
                                   
                                            this.localWebURI=param;
                                    

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
                       BG_LogItem.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:britishgas.co.uk:Enterprise:Common");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":BG_LogItem",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "BG_LogItem",
                           xmlWriter);
                   }

               
                   }
                if (localTypeIDTracker){
                                            if (localTypeID==null){
                                                 throw new org.apache.axis2.databinding.ADBException("TypeID cannot be null!!");
                                            }
                                           localTypeID.serialize(new javax.xml.namespace.QName("","TypeID"),
                                               factory,xmlWriter);
                                        } if (localCategoryCodeTracker){
                                            if (localCategoryCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("CategoryCode cannot be null!!");
                                            }
                                           localCategoryCode.serialize(new javax.xml.namespace.QName("","CategoryCode"),
                                               factory,xmlWriter);
                                        } if (localSeverityCodeTracker){
                                            if (localSeverityCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("SeverityCode cannot be null!!");
                                            }
                                           localSeverityCode.serialize(new javax.xml.namespace.QName("","SeverityCode"),
                                               factory,xmlWriter);
                                        }
                                            if (localNote==null){
                                                 throw new org.apache.axis2.databinding.ADBException("Note cannot be null!!");
                                            }
                                           localNote.serialize(new javax.xml.namespace.QName("","Note"),
                                               factory,xmlWriter);
                                         if (localWebURITracker){
                                            if (localWebURI==null){
                                                 throw new org.apache.axis2.databinding.ADBException("WebURI cannot be null!!");
                                            }
                                           localWebURI.serialize(new javax.xml.namespace.QName("","WebURI"),
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

                 if (localTypeIDTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "TypeID"));
                            
                            
                                    if (localTypeID==null){
                                         throw new org.apache.axis2.databinding.ADBException("TypeID cannot be null!!");
                                    }
                                    elementList.add(localTypeID);
                                } if (localCategoryCodeTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "CategoryCode"));
                            
                            
                                    if (localCategoryCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("CategoryCode cannot be null!!");
                                    }
                                    elementList.add(localCategoryCode);
                                } if (localSeverityCodeTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "SeverityCode"));
                            
                            
                                    if (localSeverityCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("SeverityCode cannot be null!!");
                                    }
                                    elementList.add(localSeverityCode);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "Note"));
                            
                            
                                    if (localNote==null){
                                         throw new org.apache.axis2.databinding.ADBException("Note cannot be null!!");
                                    }
                                    elementList.add(localNote);
                                 if (localWebURITracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "WebURI"));
                            
                            
                                    if (localWebURI==null){
                                         throw new org.apache.axis2.databinding.ADBException("WebURI cannot be null!!");
                                    }
                                    elementList.add(localWebURI);
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
        public static BG_LogItem parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            BG_LogItem object =
                new BG_LogItem();

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
                    
                            if (!"BG_LogItem".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (BG_LogItem)common.enterprise.uk.co.britishgas.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TypeID").equals(reader.getName())){
                                
                                                object.setTypeID(common.enterprise.uk.co.britishgas.BG_LogItemTypeID.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CategoryCode").equals(reader.getName())){
                                
                                                object.setCategoryCode(common.enterprise.uk.co.britishgas.BG_LogItemCategoryCode.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SeverityCode").equals(reader.getName())){
                                
                                                object.setSeverityCode(common.enterprise.uk.co.britishgas.BG_LogItemSeverityCode.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","Note").equals(reader.getName())){
                                
                                                object.setNote(common.enterprise.uk.co.britishgas.BG_LogItemNote.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","WebURI").equals(reader.getName())){
                                
                                                object.setWebURI(common.enterprise.uk.co.britishgas.BG_WebURI.Factory.parse(reader));
                                              
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
           
          