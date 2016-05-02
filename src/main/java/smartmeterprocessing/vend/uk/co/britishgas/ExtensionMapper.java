
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */

            package smartmeterprocessing.vend.uk.co.britishgas;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "urn:britishgas.co.uk:Vend:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendAcknowlegmentResponseMessage".equals(typeName)){
                   
                            return  smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentResponseMessage.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:britishgas.co.uk:Vend:SmartMeterProcessing".equals(namespaceURI) &&
                  "VendAcknowlegmentRequestMessage".equals(typeName)){
                   
                            return  smartmeterprocessing.vend.uk.co.britishgas.VendAcknowlegmentRequestMessage.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    