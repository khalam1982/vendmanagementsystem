
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package smartmeterprocessing.trilliant.uk.co.britishgas;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "urn:britishgas.co.uk:Trilliant:SmartMeterProcessing".equals(namespaceURI) &&
                  "ServerDetails_type0".equals(typeName)){
                   
                            return  smartmeterprocessing.trilliant.uk.co.britishgas.ServerDetails_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Trilliant:SmartMeterProcessing".equals(namespaceURI) &&
                  "QueryHeadendMastershipRequest".equals(typeName)){
                   
                            return  smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Trilliant:SmartMeterProcessing".equals(namespaceURI) &&
                  "Exception_type0".equals(typeName)){
                   
                            return  smartmeterprocessing.trilliant.uk.co.britishgas.Exception_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Trilliant:SmartMeterProcessing".equals(namespaceURI) &&
                  "HLUParameters_type0".equals(typeName)){
                   
                            return  smartmeterprocessing.trilliant.uk.co.britishgas.HLUParameters_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Trilliant:SmartMeterProcessing".equals(namespaceURI) &&
                  "QueryHeadendMastershipResponse".equals(typeName)){
                   
                            return  smartmeterprocessing.trilliant.uk.co.britishgas.QueryHeadendMastershipResponse.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    