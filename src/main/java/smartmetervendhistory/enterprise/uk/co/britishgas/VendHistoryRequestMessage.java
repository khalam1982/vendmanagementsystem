
/**
 * VendHistoryRequestMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package smartmetervendhistory.enterprise.uk.co.britishgas;
            

            /**
            *  VendHistoryRequestMessage bean class
            */
        
        public  class VendHistoryRequestMessage
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = VendHistoryRequestMessage
                Namespace URI = urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory
                Namespace Prefix = ns2
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Pan
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.StringType localPan ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.StringType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.StringType getPan(){
                               return localPan;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Pan
                               */
                               public void setPan(vhcommon.enterprise.uk.co.britishgas.StringType param){
                            
                                            this.localPan=param;
                                    

                               }
                            

                        /**
                        * field for ValidFrom
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.DateType localValidFrom ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localValidFromTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.DateType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.DateType getValidFrom(){
                               return localValidFrom;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ValidFrom
                               */
                               public void setValidFrom(vhcommon.enterprise.uk.co.britishgas.DateType param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localValidFromTracker = true;
                                       } else {
                                          localValidFromTracker = false;
                                              
                                       }
                                   
                                            this.localValidFrom=param;
                                    

                               }
                            

                        /**
                        * field for ValidTo
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.DateType localValidTo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localValidToTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.DateType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.DateType getValidTo(){
                               return localValidTo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ValidTo
                               */
                               public void setValidTo(vhcommon.enterprise.uk.co.britishgas.DateType param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localValidToTracker = true;
                                       } else {
                                          localValidToTracker = false;
                                              
                                       }
                                   
                                            this.localValidTo=param;
                                    

                               }
                            

                        /**
                        * field for NoOfTxns
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.IntegerType localNoOfTxns ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.IntegerType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.IntegerType getNoOfTxns(){
                               return localNoOfTxns;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NoOfTxns
                               */
                               public void setNoOfTxns(vhcommon.enterprise.uk.co.britishgas.IntegerType param){
                            
                                            this.localNoOfTxns=param;
                                    

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
                       VendHistoryRequestMessage.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:britishgas.co.uk:Enterprise:SmartMeterVendHistory");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":VendHistoryRequestMessage",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "VendHistoryRequestMessage",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localPan==null){
                                                 throw new org.apache.axis2.databinding.ADBException("pan cannot be null!!");
                                            }
                                           localPan.serialize(new javax.xml.namespace.QName("","pan"),
                                               factory,xmlWriter);
                                         if (localValidFromTracker){
                                            if (localValidFrom==null){
                                                 throw new org.apache.axis2.databinding.ADBException("validFrom cannot be null!!");
                                            }
                                           localValidFrom.serialize(new javax.xml.namespace.QName("","validFrom"),
                                               factory,xmlWriter);
                                        } if (localValidToTracker){
                                            if (localValidTo==null){
                                                 throw new org.apache.axis2.databinding.ADBException("validTo cannot be null!!");
                                            }
                                           localValidTo.serialize(new javax.xml.namespace.QName("","validTo"),
                                               factory,xmlWriter);
                                        }
                                            if (localNoOfTxns==null){
                                                 throw new org.apache.axis2.databinding.ADBException("noOfTxns cannot be null!!");
                                            }
                                           localNoOfTxns.serialize(new javax.xml.namespace.QName("","noOfTxns"),
                                               factory,xmlWriter);
                                        
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
                                                                      "pan"));
                            
                            
                                    if (localPan==null){
                                         throw new org.apache.axis2.databinding.ADBException("pan cannot be null!!");
                                    }
                                    elementList.add(localPan);
                                 if (localValidFromTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "validFrom"));
                            
                            
                                    if (localValidFrom==null){
                                         throw new org.apache.axis2.databinding.ADBException("validFrom cannot be null!!");
                                    }
                                    elementList.add(localValidFrom);
                                } if (localValidToTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "validTo"));
                            
                            
                                    if (localValidTo==null){
                                         throw new org.apache.axis2.databinding.ADBException("validTo cannot be null!!");
                                    }
                                    elementList.add(localValidTo);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "noOfTxns"));
                            
                            
                                    if (localNoOfTxns==null){
                                         throw new org.apache.axis2.databinding.ADBException("noOfTxns cannot be null!!");
                                    }
                                    elementList.add(localNoOfTxns);
                                

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
        public static VendHistoryRequestMessage parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            VendHistoryRequestMessage object =
                new VendHistoryRequestMessage();

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
                    
                            if (!"VendHistoryRequestMessage".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (VendHistoryRequestMessage)com.centrica.vms.vh.ws.sap.service.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","pan").equals(reader.getName())){
                                
                                                object.setPan(vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","validFrom").equals(reader.getName())){
                                
                                                object.setValidFrom(vhcommon.enterprise.uk.co.britishgas.DateType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","validTo").equals(reader.getName())){
                                
                                                object.setValidTo(vhcommon.enterprise.uk.co.britishgas.DateType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","noOfTxns").equals(reader.getName())){
                                
                                                object.setNoOfTxns(vhcommon.enterprise.uk.co.britishgas.IntegerType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
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
           
          