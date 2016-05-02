
/**
 * CreateVendMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package smartmeterprocessing.enterprise.uk.co.britishgas;
            

            /**
            *  CreateVendMessage bean class
            */
        
        public  class CreateVendMessage
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = CreateVendMessage
                Namespace URI = urn:britishgas.co.uk:Enterprise:SmartMeterProcessing
                Namespace Prefix = ns3
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:britishgas.co.uk:Enterprise:SmartMeterProcessing")){
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
                        * field for PanNumber
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber localPanNumber ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber getPanNumber(){
                               return localPanNumber;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PanNumber
                               */
                               public void setPanNumber(smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber param){
                            
                                            this.localPanNumber=param;
                                    

                               }
                            

                        /**
                        * field for VendMSN
                        */

                        
                                    protected meter.enterprise.uk.co.britishgas.MeteringAssetBasic localVendMSN ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localVendMSNTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return meter.enterprise.uk.co.britishgas.MeteringAssetBasic
                           */
                           public  meter.enterprise.uk.co.britishgas.MeteringAssetBasic getVendMSN(){
                               return localVendMSN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendMSN
                               */
                               public void setVendMSN(meter.enterprise.uk.co.britishgas.MeteringAssetBasic param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localVendMSNTracker = true;
                                       } else {
                                          localVendMSNTracker = false;
                                              
                                       }
                                   
                                            this.localVendMSN=param;
                                    

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
                        * field for VendAdditional
                        */

                        
                                    protected smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional localVendAdditional ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional
                           */
                           public  smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional getVendAdditional(){
                               return localVendAdditional;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendAdditional
                               */
                               public void setVendAdditional(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional param){
                            
                                            this.localVendAdditional=param;
                                    

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
                       CreateVendMessage.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:britishgas.co.uk:Enterprise:SmartMeterProcessing");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":CreateVendMessage",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "CreateVendMessage",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localMessageHeader==null){
                                                 throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                            }
                                           localMessageHeader.serialize(new javax.xml.namespace.QName("","MessageHeader"),
                                               factory,xmlWriter);
                                        
                                            if (localVendRequestKey==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendRequestKey cannot be null!!");
                                            }
                                           localVendRequestKey.serialize(new javax.xml.namespace.QName("","VendRequestKey"),
                                               factory,xmlWriter);
                                        
                                            if (localPanNumber==null){
                                                 throw new org.apache.axis2.databinding.ADBException("PanNumber cannot be null!!");
                                            }
                                           localPanNumber.serialize(new javax.xml.namespace.QName("","PanNumber"),
                                               factory,xmlWriter);
                                         if (localVendMSNTracker){
                                            if (localVendMSN==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendMSN cannot be null!!");
                                            }
                                           localVendMSN.serialize(new javax.xml.namespace.QName("","VendMSN"),
                                               factory,xmlWriter);
                                        }
                                            if (localVendAmount==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendAmount cannot be null!!");
                                            }
                                           localVendAmount.serialize(new javax.xml.namespace.QName("","VendAmount"),
                                               factory,xmlWriter);
                                        
                                            if (localVendRequestTimestamp==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendRequestTimestamp cannot be null!!");
                                            }
                                           localVendRequestTimestamp.serialize(new javax.xml.namespace.QName("","VendRequestTimestamp"),
                                               factory,xmlWriter);
                                        
                                            if (localVendAdditional==null){
                                                 throw new org.apache.axis2.databinding.ADBException("VendAdditional cannot be null!!");
                                            }
                                           localVendAdditional.serialize(new javax.xml.namespace.QName("","VendAdditional"),
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
                                                                      "MessageHeader"));
                            
                            
                                    if (localMessageHeader==null){
                                         throw new org.apache.axis2.databinding.ADBException("MessageHeader cannot be null!!");
                                    }
                                    elementList.add(localMessageHeader);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendRequestKey"));
                            
                            
                                    if (localVendRequestKey==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendRequestKey cannot be null!!");
                                    }
                                    elementList.add(localVendRequestKey);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "PanNumber"));
                            
                            
                                    if (localPanNumber==null){
                                         throw new org.apache.axis2.databinding.ADBException("PanNumber cannot be null!!");
                                    }
                                    elementList.add(localPanNumber);
                                 if (localVendMSNTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendMSN"));
                            
                            
                                    if (localVendMSN==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendMSN cannot be null!!");
                                    }
                                    elementList.add(localVendMSN);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendAmount"));
                            
                            
                                    if (localVendAmount==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendAmount cannot be null!!");
                                    }
                                    elementList.add(localVendAmount);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendRequestTimestamp"));
                            
                            
                                    if (localVendRequestTimestamp==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendRequestTimestamp cannot be null!!");
                                    }
                                    elementList.add(localVendRequestTimestamp);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "VendAdditional"));
                            
                            
                                    if (localVendAdditional==null){
                                         throw new org.apache.axis2.databinding.ADBException("VendAdditional cannot be null!!");
                                    }
                                    elementList.add(localVendAdditional);
                                

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
        public static CreateVendMessage parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            CreateVendMessage object =
                new CreateVendMessage();

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
                    
                            if (!"CreateVendMessage".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (CreateVendMessage)smartmeterprocessing.enterprise.uk.co.britishgas.ExtensionMapper.getTypeObject(
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendRequestKey").equals(reader.getName())){
                                
                                                object.setVendRequestKey(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestKey.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","PanNumber").equals(reader.getName())){
                                
                                                object.setPanNumber(smartmeterprocessing.enterprise.uk.co.britishgas.PanNumber.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendMSN").equals(reader.getName())){
                                
                                                object.setVendMSN(meter.enterprise.uk.co.britishgas.MeteringAssetBasic.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendRequestTimestamp").equals(reader.getName())){
                                
                                                object.setVendRequestTimestamp(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestTimestamp.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","VendAdditional").equals(reader.getName())){
                                
                                                object.setVendAdditional(smartmeterprocessing.enterprise.uk.co.britishgas.VendRequestAdditional.Factory.parse(reader));
                                              
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
           
          