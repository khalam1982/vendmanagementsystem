
/**
 * ExceptionCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package com.trilliantnetworks.unity.ws.fault;
            

            /**
            *  ExceptionCode bean class
            */
        
        public  class ExceptionCode
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "urn:fault.ws.unity.trilliantnetworks.com",
                "ExceptionCode",
                "ns6");

            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:fault.ws.unity.trilliantnetworks.com")){
                return "ns6";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for ExceptionCode
                        */

                        
                                    protected java.lang.String localExceptionCode ;
                                
                            private static java.util.HashMap _table_ = new java.util.HashMap();

                            // Constructor
                            
                                protected ExceptionCode(java.lang.String value, boolean isRegisterValue) {
                                    localExceptionCode = value;
                                    if (isRegisterValue){
                                        
                                               _table_.put(localExceptionCode, this);
                                           
                                    }

                                }
                            
                                    public static final java.lang.String _API_CURRENTLY_DISABLED =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("API_CURRENTLY_DISABLED");
                                
                                    public static final java.lang.String _BAD_CREDENTIALS =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("BAD_CREDENTIALS");
                                
                                    public static final java.lang.String _USER_LOCKOUT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("USER_LOCKOUT");
                                
                                    public static final java.lang.String _INVALID_PERMISSION =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_PERMISSION");
                                
                                    public static final java.lang.String _EXCEEDED_ID_LIMIT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("EXCEEDED_ID_LIMIT");
                                
                                    public static final java.lang.String _EXCEEDED_MAX_SIZE_REQUEST =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("EXCEEDED_MAX_SIZE_REQUEST");
                                
                                    public static final java.lang.String _EXCEEDED_MAX_TYPES_LIMIT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("EXCEEDED_MAX_TYPES_LIMIT");
                                
                                    public static final java.lang.String _EXCEEDED_RATE_LIMIT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("EXCEEDED_RATE_LIMIT");
                                
                                    public static final java.lang.String _FUNCTIONALITY_NOT_ENABLED =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("FUNCTIONALITY_NOT_ENABLED");
                                
                                    public static final java.lang.String _IMAGE_TOO_LARGE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("IMAGE_TOO_LARGE");
                                
                                    public static final java.lang.String _INACTIVE_USER =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INACTIVE_USER");
                                
                                    public static final java.lang.String _INSUFFICIENT_ACCESS =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INSUFFICIENT_ACCESS");
                                
                                    public static final java.lang.String _INVALID_ARGUMENT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_ARGUMENT");
                                
                                    public static final java.lang.String _INVALID_BATCH_SIZE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_BATCH_SIZE");
                                
                                    public static final java.lang.String _INVALID_COMM_CONFIG_CLASS =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_COMM_CONFIG_CLASS");
                                
                                    public static final java.lang.String _INVALID_CROSS_REFERENCE_KEY =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_CROSS_REFERENCE_KEY");
                                
                                    public static final java.lang.String _INVALID_FIELD =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_FIELD");
                                
                                    public static final java.lang.String _INVALID_ID_FIELD =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_ID_FIELD");
                                
                                    public static final java.lang.String _INVALID_LOGIN =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_LOGIN");
                                
                                    public static final java.lang.String _INVALID_NEW_PASSWORD =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_NEW_PASSWORD");
                                
                                    public static final java.lang.String _INVALID_OPERATION =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_OPERATION");
                                
                                    public static final java.lang.String _INVALID_OPERATION_WITH_EXPIRED_PASSWORD =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_OPERATION_WITH_EXPIRED_PASSWORD");
                                
                                    public static final java.lang.String _INVALID_QUERY_FILTER_OPERATOR =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_QUERY_FILTER_OPERATOR");
                                
                                    public static final java.lang.String _INVALID_QUERY_LOCATOR =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_QUERY_LOCATOR");
                                
                                    public static final java.lang.String _INVALID_QUERY_SCOPE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_QUERY_SCOPE");
                                
                                    public static final java.lang.String _INVALID_SEARCH =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_SEARCH");
                                
                                    public static final java.lang.String _INVALID_SEARCH_SCOPE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_SEARCH_SCOPE");
                                
                                    public static final java.lang.String _INVALID_SESSION_ID =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_SESSION_ID");
                                
                                    public static final java.lang.String _INVALID_SOAP_HEADER =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_SOAP_HEADER");
                                
                                    public static final java.lang.String _INVALID_SSO_GATEWAY_URL =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_SSO_GATEWAY_URL");
                                
                                    public static final java.lang.String _INVALID_TDEVICE_TYPE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_TDEVICE_TYPE");
                                
                                    public static final java.lang.String _INVALID_TYPE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_TYPE");
                                
                                    public static final java.lang.String _INVALID_TYPE_FOR_OPERATION =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("INVALID_TYPE_FOR_OPERATION");
                                
                                    public static final java.lang.String _LOGIN_DURING_RESTRICTED_DOMAIN =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("LOGIN_DURING_RESTRICTED_DOMAIN");
                                
                                    public static final java.lang.String _LOGIN_DURING_RESTRICTED_TIME =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("LOGIN_DURING_RESTRICTED_TIME");
                                
                                    public static final java.lang.String _MALFORMED_ID =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("MALFORMED_ID");
                                
                                    public static final java.lang.String _MALFORMED_QUERY =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("MALFORMED_QUERY");
                                
                                    public static final java.lang.String _MALFORMED_SEARCH =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("MALFORMED_SEARCH");
                                
                                    public static final java.lang.String _METER_READ_FAILED =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("METER_READ_FAILED");
                                
                                    public static final java.lang.String _MISSING_ARGUMENT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("MISSING_ARGUMENT");
                                
                                    public static final java.lang.String _OPERATION_TOO_LARGE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("OPERATION_TOO_LARGE");
                                
                                    public static final java.lang.String _PASSWORD_LOCKOUT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("PASSWORD_LOCKOUT");
                                
                                    public static final java.lang.String _QUERY_TIMEOUT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("QUERY_TIMEOUT");
                                
                                    public static final java.lang.String _QUERY_TOO_COMPLICATED =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("QUERY_TOO_COMPLICATED");
                                
                                    public static final java.lang.String _SERVER_UNAVAILABLE =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("SERVER_UNAVAILABLE");
                                
                                    public static final java.lang.String _SSO_SERVICE_DOWN =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("SSO_SERVICE_DOWN");
                                
                                    public static final java.lang.String _UNKNOWN_EXCEPTION =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("UNKNOWN_EXCEPTION");
                                
                                    public static final java.lang.String _UNSUPPORTED_API_VERSION =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("UNSUPPORTED_API_VERSION");
                                
                                    public static final java.lang.String _UNSUPPORTED_CLIENT =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("UNSUPPORTED_CLIENT");
                                
                                    public static final java.lang.String _UNSUPPORTED_OPERATION =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("UNSUPPORTED_OPERATION");
                                
                                    public static final java.lang.String _JOB_DOES_NOT_EXIST =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("JOB_DOES_NOT_EXIST");
                                
                                    public static final java.lang.String _JOB_EXISTS =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("JOB_EXISTS");
                                
                                    public static final java.lang.String _MAPPING_EXCEPTION =
                                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString("MAPPING_EXCEPTION");
                                
                                public static final ExceptionCode API_CURRENTLY_DISABLED =
                                    new ExceptionCode(_API_CURRENTLY_DISABLED,true);
                            
                                public static final ExceptionCode BAD_CREDENTIALS =
                                    new ExceptionCode(_BAD_CREDENTIALS,true);
                            
                                public static final ExceptionCode USER_LOCKOUT =
                                    new ExceptionCode(_USER_LOCKOUT,true);
                            
                                public static final ExceptionCode INVALID_PERMISSION =
                                    new ExceptionCode(_INVALID_PERMISSION,true);
                            
                                public static final ExceptionCode EXCEEDED_ID_LIMIT =
                                    new ExceptionCode(_EXCEEDED_ID_LIMIT,true);
                            
                                public static final ExceptionCode EXCEEDED_MAX_SIZE_REQUEST =
                                    new ExceptionCode(_EXCEEDED_MAX_SIZE_REQUEST,true);
                            
                                public static final ExceptionCode EXCEEDED_MAX_TYPES_LIMIT =
                                    new ExceptionCode(_EXCEEDED_MAX_TYPES_LIMIT,true);
                            
                                public static final ExceptionCode EXCEEDED_RATE_LIMIT =
                                    new ExceptionCode(_EXCEEDED_RATE_LIMIT,true);
                            
                                public static final ExceptionCode FUNCTIONALITY_NOT_ENABLED =
                                    new ExceptionCode(_FUNCTIONALITY_NOT_ENABLED,true);
                            
                                public static final ExceptionCode IMAGE_TOO_LARGE =
                                    new ExceptionCode(_IMAGE_TOO_LARGE,true);
                            
                                public static final ExceptionCode INACTIVE_USER =
                                    new ExceptionCode(_INACTIVE_USER,true);
                            
                                public static final ExceptionCode INSUFFICIENT_ACCESS =
                                    new ExceptionCode(_INSUFFICIENT_ACCESS,true);
                            
                                public static final ExceptionCode INVALID_ARGUMENT =
                                    new ExceptionCode(_INVALID_ARGUMENT,true);
                            
                                public static final ExceptionCode INVALID_BATCH_SIZE =
                                    new ExceptionCode(_INVALID_BATCH_SIZE,true);
                            
                                public static final ExceptionCode INVALID_COMM_CONFIG_CLASS =
                                    new ExceptionCode(_INVALID_COMM_CONFIG_CLASS,true);
                            
                                public static final ExceptionCode INVALID_CROSS_REFERENCE_KEY =
                                    new ExceptionCode(_INVALID_CROSS_REFERENCE_KEY,true);
                            
                                public static final ExceptionCode INVALID_FIELD =
                                    new ExceptionCode(_INVALID_FIELD,true);
                            
                                public static final ExceptionCode INVALID_ID_FIELD =
                                    new ExceptionCode(_INVALID_ID_FIELD,true);
                            
                                public static final ExceptionCode INVALID_LOGIN =
                                    new ExceptionCode(_INVALID_LOGIN,true);
                            
                                public static final ExceptionCode INVALID_NEW_PASSWORD =
                                    new ExceptionCode(_INVALID_NEW_PASSWORD,true);
                            
                                public static final ExceptionCode INVALID_OPERATION =
                                    new ExceptionCode(_INVALID_OPERATION,true);
                            
                                public static final ExceptionCode INVALID_OPERATION_WITH_EXPIRED_PASSWORD =
                                    new ExceptionCode(_INVALID_OPERATION_WITH_EXPIRED_PASSWORD,true);
                            
                                public static final ExceptionCode INVALID_QUERY_FILTER_OPERATOR =
                                    new ExceptionCode(_INVALID_QUERY_FILTER_OPERATOR,true);
                            
                                public static final ExceptionCode INVALID_QUERY_LOCATOR =
                                    new ExceptionCode(_INVALID_QUERY_LOCATOR,true);
                            
                                public static final ExceptionCode INVALID_QUERY_SCOPE =
                                    new ExceptionCode(_INVALID_QUERY_SCOPE,true);
                            
                                public static final ExceptionCode INVALID_SEARCH =
                                    new ExceptionCode(_INVALID_SEARCH,true);
                            
                                public static final ExceptionCode INVALID_SEARCH_SCOPE =
                                    new ExceptionCode(_INVALID_SEARCH_SCOPE,true);
                            
                                public static final ExceptionCode INVALID_SESSION_ID =
                                    new ExceptionCode(_INVALID_SESSION_ID,true);
                            
                                public static final ExceptionCode INVALID_SOAP_HEADER =
                                    new ExceptionCode(_INVALID_SOAP_HEADER,true);
                            
                                public static final ExceptionCode INVALID_SSO_GATEWAY_URL =
                                    new ExceptionCode(_INVALID_SSO_GATEWAY_URL,true);
                            
                                public static final ExceptionCode INVALID_TDEVICE_TYPE =
                                    new ExceptionCode(_INVALID_TDEVICE_TYPE,true);
                            
                                public static final ExceptionCode INVALID_TYPE =
                                    new ExceptionCode(_INVALID_TYPE,true);
                            
                                public static final ExceptionCode INVALID_TYPE_FOR_OPERATION =
                                    new ExceptionCode(_INVALID_TYPE_FOR_OPERATION,true);
                            
                                public static final ExceptionCode LOGIN_DURING_RESTRICTED_DOMAIN =
                                    new ExceptionCode(_LOGIN_DURING_RESTRICTED_DOMAIN,true);
                            
                                public static final ExceptionCode LOGIN_DURING_RESTRICTED_TIME =
                                    new ExceptionCode(_LOGIN_DURING_RESTRICTED_TIME,true);
                            
                                public static final ExceptionCode MALFORMED_ID =
                                    new ExceptionCode(_MALFORMED_ID,true);
                            
                                public static final ExceptionCode MALFORMED_QUERY =
                                    new ExceptionCode(_MALFORMED_QUERY,true);
                            
                                public static final ExceptionCode MALFORMED_SEARCH =
                                    new ExceptionCode(_MALFORMED_SEARCH,true);
                            
                                public static final ExceptionCode METER_READ_FAILED =
                                    new ExceptionCode(_METER_READ_FAILED,true);
                            
                                public static final ExceptionCode MISSING_ARGUMENT =
                                    new ExceptionCode(_MISSING_ARGUMENT,true);
                            
                                public static final ExceptionCode OPERATION_TOO_LARGE =
                                    new ExceptionCode(_OPERATION_TOO_LARGE,true);
                            
                                public static final ExceptionCode PASSWORD_LOCKOUT =
                                    new ExceptionCode(_PASSWORD_LOCKOUT,true);
                            
                                public static final ExceptionCode QUERY_TIMEOUT =
                                    new ExceptionCode(_QUERY_TIMEOUT,true);
                            
                                public static final ExceptionCode QUERY_TOO_COMPLICATED =
                                    new ExceptionCode(_QUERY_TOO_COMPLICATED,true);
                            
                                public static final ExceptionCode SERVER_UNAVAILABLE =
                                    new ExceptionCode(_SERVER_UNAVAILABLE,true);
                            
                                public static final ExceptionCode SSO_SERVICE_DOWN =
                                    new ExceptionCode(_SSO_SERVICE_DOWN,true);
                            
                                public static final ExceptionCode UNKNOWN_EXCEPTION =
                                    new ExceptionCode(_UNKNOWN_EXCEPTION,true);
                            
                                public static final ExceptionCode UNSUPPORTED_API_VERSION =
                                    new ExceptionCode(_UNSUPPORTED_API_VERSION,true);
                            
                                public static final ExceptionCode UNSUPPORTED_CLIENT =
                                    new ExceptionCode(_UNSUPPORTED_CLIENT,true);
                            
                                public static final ExceptionCode UNSUPPORTED_OPERATION =
                                    new ExceptionCode(_UNSUPPORTED_OPERATION,true);
                            
                                public static final ExceptionCode JOB_DOES_NOT_EXIST =
                                    new ExceptionCode(_JOB_DOES_NOT_EXIST,true);
                            
                                public static final ExceptionCode JOB_EXISTS =
                                    new ExceptionCode(_JOB_EXISTS,true);
                            
                                public static final ExceptionCode MAPPING_EXCEPTION =
                                    new ExceptionCode(_MAPPING_EXCEPTION,true);
                            

                                public java.lang.String getValue() { return localExceptionCode;}

                                public boolean equals(java.lang.Object obj) {return (obj == this);}
                                public int hashCode() { return toString().hashCode();}
                                public java.lang.String toString() {
                                
                                        return localExceptionCode.toString();
                                    

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
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       ExceptionCode.this.serialize(MY_QNAME,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               MY_QNAME,factory,dataSource);
            
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
            
                
                //We can safely assume an element has only one type associated with it
                
                            java.lang.String namespace = parentQName.getNamespaceURI();
                            java.lang.String localName = parentQName.getLocalPart();
                        
                            if (! namespace.equals("")) {
                                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                                if (prefix == null) {
                                    prefix = generatePrefix(namespace);

                                    xmlWriter.writeStartElement(prefix, localName, namespace);
                                    xmlWriter.writeNamespace(prefix, namespace);
                                    xmlWriter.setPrefix(prefix, namespace);

                                } else {
                                    xmlWriter.writeStartElement(namespace, localName);
                                }

                            } else {
                                xmlWriter.writeStartElement(localName);
                            }

                            // add the type details if this is used in a simple type
                               if (serializeType){
                                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:fault.ws.unity.trilliantnetworks.com");
                                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                                           namespacePrefix+":ExceptionCode",
                                           xmlWriter);
                                   } else {
                                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                                           "ExceptionCode",
                                           xmlWriter);
                                   }
                               }
                            
                                          if (localExceptionCode==null){
                                            
                                                     throw new org.apache.axis2.databinding.ADBException("Value cannot be null !!");
                                                
                                         }else{
                                        
                                                       xmlWriter.writeCharacters(localExceptionCode);
                                            
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


        
                
                //We can safely assume an element has only one type associated with it
                 return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(MY_QNAME,
                            new java.lang.Object[]{
                            org.apache.axis2.databinding.utils.reader.ADBXMLStreamReader.ELEMENT_TEXT,
                            org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localExceptionCode)
                            },
                            null);

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        
                public static ExceptionCode fromValue(java.lang.String value)
                      throws java.lang.IllegalArgumentException {
                    ExceptionCode enumeration = (ExceptionCode)
                       
                               _table_.get(value);
                           

                    if (enumeration==null) throw new java.lang.IllegalArgumentException();
                    return enumeration;
                }
                public static ExceptionCode fromString(java.lang.String value,java.lang.String namespaceURI)
                      throws java.lang.IllegalArgumentException {
                    try {
                       
                                       return fromValue(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(value));
                                   

                    } catch (java.lang.Exception e) {
                        throw new java.lang.IllegalArgumentException();
                    }
                }

                public static ExceptionCode fromString(javax.xml.stream.XMLStreamReader xmlStreamReader,
                                                                    java.lang.String content) {
                    if (content.indexOf(":") > -1){
                        java.lang.String prefix = content.substring(0,content.indexOf(":"));
                        java.lang.String namespaceUri = xmlStreamReader.getNamespaceContext().getNamespaceURI(prefix);
                        return ExceptionCode.Factory.fromString(content,namespaceUri);
                    } else {
                       return ExceptionCode.Factory.fromString(content,"");
                    }
                }
            

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static ExceptionCode parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ExceptionCode object = null;
                // initialize a hash map to keep values
                java.util.Map attributeMap = new java.util.HashMap();
                java.util.List extraAttributeList = new java.util.ArrayList();
            

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                    
                while(!reader.isEndElement()) {
                    if (reader.isStartElement()  || reader.hasText()){
                
                                    java.lang.String content = reader.getElementText();
                                    
                                        if (content.indexOf(":") > 0) {
                                            // this seems to be a Qname so find the namespace and send
                                            prefix = content.substring(0, content.indexOf(":"));
                                            namespaceuri = reader.getNamespaceURI(prefix);
                                            object = ExceptionCode.Factory.fromString(content,namespaceuri);
                                        } else {
                                            // this seems to be not a qname send and empty namespace incase of it is
                                            // check is done in fromString method
                                            object = ExceptionCode.Factory.fromString(content,"");
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
           
          