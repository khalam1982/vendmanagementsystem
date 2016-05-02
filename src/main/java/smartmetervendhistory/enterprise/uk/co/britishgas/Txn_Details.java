
/**
 * Txn_Details.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package smartmetervendhistory.enterprise.uk.co.britishgas;
            

            /**
            *  Txn_Details bean class
            */
        
        public  class Txn_Details
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Txn_Details
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
                        * field for TransactionID
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.StringType localTransactionID ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.StringType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.StringType getTransactionID(){
                               return localTransactionID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionID
                               */
                               public void setTransactionID(vhcommon.enterprise.uk.co.britishgas.StringType param){
                            
                                            this.localTransactionID=param;
                                    

                               }
                            

                        /**
                        * field for VendCode
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.StringType localVendCode ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.StringType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.StringType getVendCode(){
                               return localVendCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param VendCode
                               */
                               public void setVendCode(vhcommon.enterprise.uk.co.britishgas.StringType param){
                            
                                            this.localVendCode=param;
                                    

                               }
                            

                        /**
                        * field for TransactionType
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.StringType localTransactionType ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.StringType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.StringType getTransactionType(){
                               return localTransactionType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TransactionType
                               */
                               public void setTransactionType(vhcommon.enterprise.uk.co.britishgas.StringType param){
                            
                                            this.localTransactionType=param;
                                    

                               }
                            

                        /**
                        * field for TxnOutcome
                        * This was an Array!
                        */

                        
                                    protected smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[] localTxnOutcome ;
                                

                           /**
                           * Auto generated getter method
                           * @return smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[]
                           */
                           public  smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[] getTxnOutcome(){
                               return localTxnOutcome;
                           }

                           
                        


                               
                              /**
                               * validate the array for TxnOutcome
                               */
                              protected void validateTxnOutcome(smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException();
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param TxnOutcome
                              */
                              public void setTxnOutcome(smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[] param){
                              
                                   validateTxnOutcome(param);

                               
                                      this.localTxnOutcome=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome
                             */
                             public void addTxnOutcome(smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome param){
                                   if (localTxnOutcome == null){
                                   localTxnOutcome = new smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localTxnOutcome);
                               list.add(param);
                               this.localTxnOutcome =
                             (smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[])list.toArray(
                            new smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[list.size()]);

                             }
                             

                        /**
                        * field for Msn
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.StringType localMsn ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.StringType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.StringType getMsn(){
                               return localMsn;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Msn
                               */
                               public void setMsn(vhcommon.enterprise.uk.co.britishgas.StringType param){
                            
                                            this.localMsn=param;
                                    

                               }
                            

                        /**
                        * field for HoldingPeriod
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.StringType localHoldingPeriod ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.StringType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.StringType getHoldingPeriod(){
                               return localHoldingPeriod;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HoldingPeriod
                               */
                               public void setHoldingPeriod(vhcommon.enterprise.uk.co.britishgas.StringType param){
                            
                                            this.localHoldingPeriod=param;
                                    

                               }
                            

                        /**
                        * field for SourceDetails
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.SourceType localSourceDetails ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.SourceType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.SourceType getSourceDetails(){
                               return localSourceDetails;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SourceDetails
                               */
                               public void setSourceDetails(vhcommon.enterprise.uk.co.britishgas.SourceType param){
                            
                                            this.localSourceDetails=param;
                                    

                               }
                            

                        /**
                        * field for Created_timestamp
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.DateType localCreated_timestamp ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.DateType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.DateType getCreated_timestamp(){
                               return localCreated_timestamp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Created_timestamp
                               */
                               public void setCreated_timestamp(vhcommon.enterprise.uk.co.britishgas.DateType param){
                            
                                            this.localCreated_timestamp=param;
                                    

                               }
                            

                        /**
                        * field for Actual_timestamp
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.DateType localActual_timestamp ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.DateType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.DateType getActual_timestamp(){
                               return localActual_timestamp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Actual_timestamp
                               */
                               public void setActual_timestamp(vhcommon.enterprise.uk.co.britishgas.DateType param){
                            
                                            this.localActual_timestamp=param;
                                    

                               }
                            

                        /**
                        * field for Vendcode_timestamp
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.DateType localVendcode_timestamp ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.DateType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.DateType getVendcode_timestamp(){
                               return localVendcode_timestamp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Vendcode_timestamp
                               */
                               public void setVendcode_timestamp(vhcommon.enterprise.uk.co.britishgas.DateType param){
                            
                                            this.localVendcode_timestamp=param;
                                    

                               }
                            

                        /**
                        * field for Credit_value
                        */

                        
                                    protected vhcommon.enterprise.uk.co.britishgas.StringType localCredit_value ;
                                

                           /**
                           * Auto generated getter method
                           * @return vhcommon.enterprise.uk.co.britishgas.StringType
                           */
                           public  vhcommon.enterprise.uk.co.britishgas.StringType getCredit_value(){
                               return localCredit_value;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Credit_value
                               */
                               public void setCredit_value(vhcommon.enterprise.uk.co.britishgas.StringType param){
                            
                                            this.localCredit_value=param;
                                    

                               }
                               /**
                                * field for TopUpSource
                                */

                                
                                            protected vhcommon.enterprise.uk.co.britishgas.StringType localTopUpSource ;
                                        
                                   /*  This tracker boolean wil be used to detect whether the user called the set method
                                  *   for this attribute. It will be used to determine whether to include this field
                                   *   in the serialized XML
                                   */
                                   protected boolean localTopUpSourceTracker = false ;
                                   

                                   /**
                                   * Auto generated getter method
                                   * @return vhcommon.enterprise.uk.co.britishgas.StringType
                                   */
                                   public  vhcommon.enterprise.uk.co.britishgas.StringType getTopUpSource(){
                                       return localTopUpSource;
                                   }

                                   
                                
                                    /**
                                       * Auto generated setter method
                                       * @param param TopUpSource
                                       */
                                       public void setTopUpSource(vhcommon.enterprise.uk.co.britishgas.StringType param){
                                    
                                               if (param != null){
                                                  //update the setting tracker
                                                  localTopUpSourceTracker = true;
                                               } else {
                                                  localTopUpSourceTracker = false;
                                                      
                                               }
                                           
                                                    this.localTopUpSource=param;
                                            

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
                       Txn_Details.this.serialize(parentQName,factory,xmlWriter);
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
                           namespacePrefix+":Txn_Details",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Txn_Details",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localTransactionID==null){
                                                 throw new org.apache.axis2.databinding.ADBException("transactionID cannot be null!!");
                                            }
                                           localTransactionID.serialize(new javax.xml.namespace.QName("","transactionID"),
                                               factory,xmlWriter);
                                        
                                            if (localVendCode==null){
                                                 throw new org.apache.axis2.databinding.ADBException("vendCode cannot be null!!");
                                            }
                                           localVendCode.serialize(new javax.xml.namespace.QName("","vendCode"),
                                               factory,xmlWriter);
                                        
                                            if (localTransactionType==null){
                                                 throw new org.apache.axis2.databinding.ADBException("transactionType cannot be null!!");
                                            }
                                           localTransactionType.serialize(new javax.xml.namespace.QName("","transactionType"),
                                               factory,xmlWriter);
                                        
                                       if (localTxnOutcome!=null){
                                            for (int i = 0;i < localTxnOutcome.length;i++){
                                                if (localTxnOutcome[i] != null){
                                                 localTxnOutcome[i].serialize(new javax.xml.namespace.QName("","TxnOutcome"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("TxnOutcome cannot be null!!");
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("TxnOutcome cannot be null!!");
                                        
                                    }
                                 
                                            if (localMsn==null){
                                                 throw new org.apache.axis2.databinding.ADBException("msn cannot be null!!");
                                            }
                                           localMsn.serialize(new javax.xml.namespace.QName("","msn"),
                                               factory,xmlWriter);
                                        
                                            if (localHoldingPeriod==null){
                                                 throw new org.apache.axis2.databinding.ADBException("holdingPeriod cannot be null!!");
                                            }
                                           localHoldingPeriod.serialize(new javax.xml.namespace.QName("","holdingPeriod"),
                                               factory,xmlWriter);
                                        
                                            if (localSourceDetails==null){
                                                 throw new org.apache.axis2.databinding.ADBException("SourceDetails cannot be null!!");
                                            }
                                           localSourceDetails.serialize(new javax.xml.namespace.QName("","SourceDetails"),
                                               factory,xmlWriter);
                                        
                                            if (localCreated_timestamp==null){
                                                 throw new org.apache.axis2.databinding.ADBException("created_timestamp cannot be null!!");
                                            }
                                           localCreated_timestamp.serialize(new javax.xml.namespace.QName("","created_timestamp"),
                                               factory,xmlWriter);
                                        
                                            if (localActual_timestamp==null){
                                                 throw new org.apache.axis2.databinding.ADBException("actual_timestamp cannot be null!!");
                                            }
                                           localActual_timestamp.serialize(new javax.xml.namespace.QName("","actual_timestamp"),
                                               factory,xmlWriter);
                                        
                                            if (localVendcode_timestamp==null){
                                                 throw new org.apache.axis2.databinding.ADBException("vendcode_timestamp cannot be null!!");
                                            }
                                           localVendcode_timestamp.serialize(new javax.xml.namespace.QName("","vendcode_timestamp"),
                                               factory,xmlWriter);
                                        
                                            if (localCredit_value==null){
                                                 throw new org.apache.axis2.databinding.ADBException("credit_value cannot be null!!");
                                            }
                                           localCredit_value.serialize(new javax.xml.namespace.QName("","credit_value"),
                                               factory,xmlWriter);
                                           if (localTopUpSourceTracker){
                                               if (localTopUpSource==null){
                                                    throw new org.apache.axis2.databinding.ADBException("topUpSource cannot be null!!");
                                               }
                                              localTopUpSource.serialize(new javax.xml.namespace.QName("","topUpSource"),
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
                                                                      "transactionID"));
                            
                            
                                    if (localTransactionID==null){
                                         throw new org.apache.axis2.databinding.ADBException("transactionID cannot be null!!");
                                    }
                                    elementList.add(localTransactionID);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "vendCode"));
                            
                            
                                    if (localVendCode==null){
                                         throw new org.apache.axis2.databinding.ADBException("vendCode cannot be null!!");
                                    }
                                    elementList.add(localVendCode);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "transactionType"));
                            
                            
                                    if (localTransactionType==null){
                                         throw new org.apache.axis2.databinding.ADBException("transactionType cannot be null!!");
                                    }
                                    elementList.add(localTransactionType);
                                
                             if (localTxnOutcome!=null) {
                                 for (int i = 0;i < localTxnOutcome.length;i++){

                                    if (localTxnOutcome[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("",
                                                                          "TxnOutcome"));
                                         elementList.add(localTxnOutcome[i]);
                                    } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("TxnOutcome cannot be null !!");
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("TxnOutcome cannot be null!!");
                                    
                             }

                        
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "msn"));
                            
                            
                                    if (localMsn==null){
                                         throw new org.apache.axis2.databinding.ADBException("msn cannot be null!!");
                                    }
                                    elementList.add(localMsn);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "holdingPeriod"));
                            
                            
                                    if (localHoldingPeriod==null){
                                         throw new org.apache.axis2.databinding.ADBException("holdingPeriod cannot be null!!");
                                    }
                                    elementList.add(localHoldingPeriod);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "SourceDetails"));
                            
                            
                                    if (localSourceDetails==null){
                                         throw new org.apache.axis2.databinding.ADBException("SourceDetails cannot be null!!");
                                    }
                                    elementList.add(localSourceDetails);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "created_timestamp"));
                            
                            
                                    if (localCreated_timestamp==null){
                                         throw new org.apache.axis2.databinding.ADBException("created_timestamp cannot be null!!");
                                    }
                                    elementList.add(localCreated_timestamp);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "actual_timestamp"));
                            
                            
                                    if (localActual_timestamp==null){
                                         throw new org.apache.axis2.databinding.ADBException("actual_timestamp cannot be null!!");
                                    }
                                    elementList.add(localActual_timestamp);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "vendcode_timestamp"));
                            
                            
                                    if (localVendcode_timestamp==null){
                                         throw new org.apache.axis2.databinding.ADBException("vendcode_timestamp cannot be null!!");
                                    }
                                    elementList.add(localVendcode_timestamp);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "credit_value"));
                            
                            
                                    if (localCredit_value==null){
                                         throw new org.apache.axis2.databinding.ADBException("credit_value cannot be null!!");
                                    }
                                    elementList.add(localCredit_value);
                                
                                    if (localTopUpSourceTracker){
                                        elementList.add(new javax.xml.namespace.QName("",
                                                                                  "topUpSource"));
                                        
                                        
                                                if (localTopUpSource==null){
                                                     throw new org.apache.axis2.databinding.ADBException("topUpSource cannot be null!!");
                                                }
                                                elementList.add(localTopUpSource);
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
        public static Txn_Details parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Txn_Details object =
                new Txn_Details();

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
                    
                            if (!"Txn_Details".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Txn_Details)com.centrica.vms.vh.ws.sap.service.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                        java.util.ArrayList list4 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionID").equals(reader.getName())){
                                
                                                object.setTransactionID(vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","vendCode").equals(reader.getName())){
                                
                                                object.setVendCode(vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","transactionType").equals(reader.getName())){
                                
                                                object.setTransactionType(vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","TxnOutcome").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list4.add(smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone4 = false;
                                                        while(!loopDone4){
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
                                                                loopDone4 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","TxnOutcome").equals(reader.getName())){
                                                                    list4.add(smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone4 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setTxnOutcome((smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                smartmetervendhistory.enterprise.uk.co.britishgas.Txn_Outcome.class,
                                                                list4));
                                                            
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","msn").equals(reader.getName())){
                                
                                                object.setMsn(vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","holdingPeriod").equals(reader.getName())){
                                
                                                object.setHoldingPeriod(vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SourceDetails").equals(reader.getName())){
                                
                                                object.setSourceDetails(vhcommon.enterprise.uk.co.britishgas.SourceType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","created_timestamp").equals(reader.getName())){
                                
                                                object.setCreated_timestamp(vhcommon.enterprise.uk.co.britishgas.DateType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","actual_timestamp").equals(reader.getName())){
                                
                                                object.setActual_timestamp(vhcommon.enterprise.uk.co.britishgas.DateType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","vendcode_timestamp").equals(reader.getName())){
                                
                                                object.setVendcode_timestamp(vhcommon.enterprise.uk.co.britishgas.DateType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","credit_value").equals(reader.getName())){
                                
                                                object.setCredit_value(vhcommon.enterprise.uk.co.britishgas.StringType.Factory.parse(reader));
                                              
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
           
          