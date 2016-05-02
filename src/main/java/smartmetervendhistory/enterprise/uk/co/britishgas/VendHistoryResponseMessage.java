
/**
 * VendHistoryResponseMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package smartmetervendhistory.enterprise.uk.co.britishgas;
            

            /**
            *  VendHistoryResponseMessage bean class
            */
        
        public  class VendHistoryResponseMessage
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = VendHistoryResponseMessage
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
                        * field for CreditPurchase
                        * This was an Array!
                        */

                        
                                    protected smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[] localCreditPurchase ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCreditPurchaseTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[]
                           */
                           public  smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[] getCreditPurchase(){
                               return localCreditPurchase;
                           }

                           
                        


                               
                              /**
                               * validate the array for CreditPurchase
                               */
                              protected void validateCreditPurchase(smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param CreditPurchase
                              */
                              public void setCreditPurchase(smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[] param){
                              
                                   validateCreditPurchase(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localCreditPurchaseTracker = true;
                                          } else {
                                             localCreditPurchaseTracker = false;
                                                 
                                          }
                                      
                                      this.localCreditPurchase=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn
                             */
                             public void addCreditPurchase(smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn param){
                                   if (localCreditPurchase == null){
                                   localCreditPurchase = new smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[]{};
                                   }

                            
                                 //update the setting tracker
                                localCreditPurchaseTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localCreditPurchase);
                               list.add(param);
                               this.localCreditPurchase =
                             (smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[])list.toArray(
                            new smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[list.size()]);

                             }
                             

                        /**
                        * field for CreditAdjustment
                        * This was an Array!
                        */

                        
                                    protected smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[] localCreditAdjustment ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCreditAdjustmentTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[]
                           */
                           public  smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[] getCreditAdjustment(){
                               return localCreditAdjustment;
                           }

                           
                        


                               
                              /**
                               * validate the array for CreditAdjustment
                               */
                              protected void validateCreditAdjustment(smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param CreditAdjustment
                              */
                              public void setCreditAdjustment(smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[] param){
                              
                                   validateCreditAdjustment(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localCreditAdjustmentTracker = true;
                                          } else {
                                             localCreditAdjustmentTracker = false;
                                                 
                                          }
                                      
                                      this.localCreditAdjustment=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn
                             */
                             public void addCreditAdjustment(smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn param){
                                   if (localCreditAdjustment == null){
                                   localCreditAdjustment = new smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[]{};
                                   }

                            
                                 //update the setting tracker
                                localCreditAdjustmentTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localCreditAdjustment);
                               list.add(param);
                               this.localCreditAdjustment =
                             (smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[])list.toArray(
                            new smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[list.size()]);

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
                       VendHistoryResponseMessage.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":VendHistoryResponseMessage",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "VendHistoryResponseMessage",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localPan==null){
                                                 throw new org.apache.axis2.databinding.ADBException("pan cannot be null!!");
                                            }
                                           localPan.serialize(new javax.xml.namespace.QName("","pan"),
                                               factory,xmlWriter);
                                         if (localCreditPurchaseTracker){
                                       if (localCreditPurchase!=null){
                                            for (int i = 0;i < localCreditPurchase.length;i++){
                                                if (localCreditPurchase[i] != null){
                                                 localCreditPurchase[i].serialize(new javax.xml.namespace.QName("","CreditPurchase"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("CreditPurchase cannot be null!!");
                                        
                                    }
                                 } if (localCreditAdjustmentTracker){
                                       if (localCreditAdjustment!=null){
                                            for (int i = 0;i < localCreditAdjustment.length;i++){
                                                if (localCreditAdjustment[i] != null){
                                                 localCreditAdjustment[i].serialize(new javax.xml.namespace.QName("","CreditAdjustment"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("CreditAdjustment cannot be null!!");
                                        
                                    }
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
                                                                      "pan"));
                            
                            
                                    if (localPan==null){
                                         throw new org.apache.axis2.databinding.ADBException("pan cannot be null!!");
                                    }
                                    elementList.add(localPan);
                                 if (localCreditPurchaseTracker){
                             if (localCreditPurchase!=null) {
                                 for (int i = 0;i < localCreditPurchase.length;i++){

                                    if (localCreditPurchase[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "CreditPurchase"));
                                         elementList.add(localCreditPurchase[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("CreditPurchase cannot be null!!");
                                    
                             }

                        } if (localCreditAdjustmentTracker){
                             if (localCreditAdjustment!=null) {
                                 for (int i = 0;i < localCreditAdjustment.length;i++){

                                    if (localCreditAdjustment[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "CreditAdjustment"));
                                         elementList.add(localCreditAdjustment[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("CreditAdjustment cannot be null!!");
                                    
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
        public static VendHistoryResponseMessage parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            VendHistoryResponseMessage object =
                new VendHistoryResponseMessage();

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
                    
                            if (!"VendHistoryResponseMessage".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (VendHistoryResponseMessage)com.centrica.vms.vh.ws.sap.service.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                        java.util.ArrayList list2 = new java.util.ArrayList();
                    
                        java.util.ArrayList list3 = new java.util.ArrayList();
                    
                                    
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CreditPurchase").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list2.add(smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone2 = false;
                                                        while(!loopDone2){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone2 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","CreditPurchase").equals(reader.getName())){
                                                                    list2.add(smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone2 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setCreditPurchase((smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                smartmetervendhistory.enterprise.uk.co.britishgas.Purchase_Txn.class,
                                                                list2));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CreditAdjustment").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list3.add(smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone3 = false;
                                                        while(!loopDone3){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone3 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","CreditAdjustment").equals(reader.getName())){
                                                                    list3.add(smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone3 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setCreditAdjustment((smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                smartmetervendhistory.enterprise.uk.co.britishgas.Adjustment_Txn.class,
                                                                list3));
                                                            
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
           
          