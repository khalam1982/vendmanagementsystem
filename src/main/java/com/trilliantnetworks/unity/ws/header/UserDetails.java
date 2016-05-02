
/**
 * UserDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:44 LKT)
 */
            
                package com.trilliantnetworks.unity.ws.header;
            

            /**
            *  UserDetails bean class
            */
        
        public  class UserDetails
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = UserDetails
                Namespace URI = urn:header.ws.unity.trilliantnetworks.com
                Namespace Prefix = ns2
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:header.ws.unity.trilliantnetworks.com")){
                return "ns2";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Password
                        */

                        
                                    protected java.lang.String localPassword ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPasswordTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPassword(){
                               return localPassword;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Password
                               */
                               public void setPassword(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPasswordTracker = true;
                                       } else {
                                          localPasswordTracker = true;
                                              
                                       }
                                   
                                            this.localPassword=param;
                                    

                               }
                            

                        /**
                        * field for Username
                        */

                        
                                    protected java.lang.String localUsername ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUsernameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getUsername(){
                               return localUsername;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Username
                               */
                               public void setUsername(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localUsernameTracker = true;
                                       } else {
                                          localUsernameTracker = true;
                                              
                                       }
                                   
                                            this.localUsername=param;
                                    

                               }
                            

                        /**
                        * field for Id
                        */

                        
                                    protected java.lang.String localId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId(){
                               return localId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id
                               */
                               public void setId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIdTracker = true;
                                       } else {
                                          localIdTracker = true;
                                              
                                       }
                                   
                                            this.localId=param;
                                    

                               }
                            

                        /**
                        * field for LogonId
                        */

                        
                                    protected java.lang.String localLogonId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLogonIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLogonId(){
                               return localLogonId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LogonId
                               */
                               public void setLogonId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localLogonIdTracker = true;
                                       } else {
                                          localLogonIdTracker = true;
                                              
                                       }
                                   
                                            this.localLogonId=param;
                                    

                               }
                            

                        /**
                        * field for Name
                        */

                        
                                    protected java.lang.String localName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getName(){
                               return localName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Name
                               */
                               public void setName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localNameTracker = true;
                                       } else {
                                          localNameTracker = true;
                                              
                                       }
                                   
                                            this.localName=param;
                                    

                               }
                            

                        /**
                        * field for Email
                        */

                        
                                    protected java.lang.String localEmail ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEmailTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getEmail(){
                               return localEmail;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Email
                               */
                               public void setEmail(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localEmailTracker = true;
                                       } else {
                                          localEmailTracker = true;
                                              
                                       }
                                   
                                            this.localEmail=param;
                                    

                               }
                            

                        /**
                        * field for PartnerId
                        */

                        
                                    protected java.lang.String localPartnerId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPartnerIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPartnerId(){
                               return localPartnerId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PartnerId
                               */
                               public void setPartnerId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPartnerIdTracker = true;
                                       } else {
                                          localPartnerIdTracker = true;
                                              
                                       }
                                   
                                            this.localPartnerId=param;
                                    

                               }
                            

                        /**
                        * field for PartnerName
                        */

                        
                                    protected java.lang.String localPartnerName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPartnerNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPartnerName(){
                               return localPartnerName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PartnerName
                               */
                               public void setPartnerName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPartnerNameTracker = true;
                                       } else {
                                          localPartnerNameTracker = true;
                                              
                                       }
                                   
                                            this.localPartnerName=param;
                                    

                               }
                            

                        /**
                        * field for PartnerNo
                        */

                        
                                    protected java.lang.String localPartnerNo ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPartnerNoTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPartnerNo(){
                               return localPartnerNo;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PartnerNo
                               */
                               public void setPartnerNo(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPartnerNoTracker = true;
                                       } else {
                                          localPartnerNoTracker = true;
                                              
                                       }
                                   
                                            this.localPartnerNo=param;
                                    

                               }
                            

                        /**
                        * field for PartnerCountry
                        */

                        
                                    protected java.lang.String localPartnerCountry ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPartnerCountryTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPartnerCountry(){
                               return localPartnerCountry;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PartnerCountry
                               */
                               public void setPartnerCountry(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPartnerCountryTracker = true;
                                       } else {
                                          localPartnerCountryTracker = true;
                                              
                                       }
                                   
                                            this.localPartnerCountry=param;
                                    

                               }
                            

                        /**
                        * field for PartnerTimeZoneLookupId
                        */

                        
                                    protected java.lang.String localPartnerTimeZoneLookupId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPartnerTimeZoneLookupIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getPartnerTimeZoneLookupId(){
                               return localPartnerTimeZoneLookupId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PartnerTimeZoneLookupId
                               */
                               public void setPartnerTimeZoneLookupId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPartnerTimeZoneLookupIdTracker = true;
                                       } else {
                                          localPartnerTimeZoneLookupIdTracker = true;
                                              
                                       }
                                   
                                            this.localPartnerTimeZoneLookupId=param;
                                    

                               }
                            

                        /**
                        * field for SinglePartner
                        */

                        
                                    protected boolean localSinglePartner ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localSinglePartnerTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getSinglePartner(){
                               return localSinglePartner;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SinglePartner
                               */
                               public void setSinglePartner(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (false) {
                                           localSinglePartnerTracker = true;
                                              
                                       } else {
                                          localSinglePartnerTracker = true;
                                       }
                                   
                                            this.localSinglePartner=param;
                                    

                               }
                            

                        /**
                        * field for TimeZone
                        */

                        
                                    protected java.lang.String localTimeZone ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTimeZoneTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTimeZone(){
                               return localTimeZone;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TimeZone
                               */
                               public void setTimeZone(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTimeZoneTracker = true;
                                       } else {
                                          localTimeZoneTracker = true;
                                              
                                       }
                                   
                                            this.localTimeZone=param;
                                    

                               }
                            

                        /**
                        * field for TimeZoneOlsonName
                        */

                        
                                    protected java.lang.String localTimeZoneOlsonName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTimeZoneOlsonNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTimeZoneOlsonName(){
                               return localTimeZoneOlsonName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TimeZoneOlsonName
                               */
                               public void setTimeZoneOlsonName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTimeZoneOlsonNameTracker = true;
                                       } else {
                                          localTimeZoneOlsonNameTracker = true;
                                              
                                       }
                                   
                                            this.localTimeZoneOlsonName=param;
                                    

                               }
                            

                        /**
                        * field for DateFormat
                        */

                        
                                    protected java.lang.String localDateFormat ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localDateFormatTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDateFormat(){
                               return localDateFormat;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param DateFormat
                               */
                               public void setDateFormat(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localDateFormatTracker = true;
                                       } else {
                                          localDateFormatTracker = true;
                                              
                                       }
                                   
                                            this.localDateFormat=param;
                                    

                               }
                            

                        /**
                        * field for TimeFormat
                        */

                        
                                    protected java.lang.String localTimeFormat ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTimeFormatTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getTimeFormat(){
                               return localTimeFormat;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TimeFormat
                               */
                               public void setTimeFormat(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localTimeFormatTracker = true;
                                       } else {
                                          localTimeFormatTracker = true;
                                              
                                       }
                                   
                                            this.localTimeFormat=param;
                                    

                               }
                            

                        /**
                        * field for CurrencySymbol
                        */

                        
                                    protected java.lang.String localCurrencySymbol ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurrencySymbolTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCurrencySymbol(){
                               return localCurrencySymbol;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurrencySymbol
                               */
                               public void setCurrencySymbol(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCurrencySymbolTracker = true;
                                       } else {
                                          localCurrencySymbolTracker = true;
                                              
                                       }
                                   
                                            this.localCurrencySymbol=param;
                                    

                               }
                            

                        /**
                        * field for HomePreferences
                        */

                        
                                    protected java.lang.String localHomePreferences ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHomePreferencesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getHomePreferences(){
                               return localHomePreferences;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param HomePreferences
                               */
                               public void setHomePreferences(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localHomePreferencesTracker = true;
                                       } else {
                                          localHomePreferencesTracker = true;
                                              
                                       }
                                   
                                            this.localHomePreferences=param;
                                    

                               }
                            

                        /**
                        * field for UtilityPreferences
                        */

                        
                                    protected java.lang.String localUtilityPreferences ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUtilityPreferencesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getUtilityPreferences(){
                               return localUtilityPreferences;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UtilityPreferences
                               */
                               public void setUtilityPreferences(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localUtilityPreferencesTracker = true;
                                       } else {
                                          localUtilityPreferencesTracker = true;
                                              
                                       }
                                   
                                            this.localUtilityPreferences=param;
                                    

                               }
                            

                        /**
                        * field for Authorities
                        * This was an Array!
                        */

                        
                                    protected com.trilliantnetworks.unity.ws.header.GrantedAuthority[] localAuthorities ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAuthoritiesTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return com.trilliantnetworks.unity.ws.header.GrantedAuthority[]
                           */
                           public  com.trilliantnetworks.unity.ws.header.GrantedAuthority[] getAuthorities(){
                               return localAuthorities;
                           }

                           
                        


                               
                              /**
                               * validate the array for Authorities
                               */
                              protected void validateAuthorities(com.trilliantnetworks.unity.ws.header.GrantedAuthority[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Authorities
                              */
                              public void setAuthorities(com.trilliantnetworks.unity.ws.header.GrantedAuthority[] param){
                              
                                   validateAuthorities(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localAuthoritiesTracker = true;
                                          } else {
                                             localAuthoritiesTracker = true;
                                                 
                                          }
                                      
                                      this.localAuthorities=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param com.trilliantnetworks.unity.ws.header.GrantedAuthority
                             */
                             public void addAuthorities(com.trilliantnetworks.unity.ws.header.GrantedAuthority param){
                                   if (localAuthorities == null){
                                   localAuthorities = new com.trilliantnetworks.unity.ws.header.GrantedAuthority[]{};
                                   }

                            
                                 //update the setting tracker
                                localAuthoritiesTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localAuthorities);
                               list.add(param);
                               this.localAuthorities =
                             (com.trilliantnetworks.unity.ws.header.GrantedAuthority[])list.toArray(
                            new com.trilliantnetworks.unity.ws.header.GrantedAuthority[list.size()]);

                             }
                             

                        /**
                        * field for AccountNonExpired
                        */

                        
                                    protected boolean localAccountNonExpired ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountNonExpiredTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getAccountNonExpired(){
                               return localAccountNonExpired;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountNonExpired
                               */
                               public void setAccountNonExpired(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (false) {
                                           localAccountNonExpiredTracker = true;
                                              
                                       } else {
                                          localAccountNonExpiredTracker = true;
                                       }
                                   
                                            this.localAccountNonExpired=param;
                                    

                               }
                            

                        /**
                        * field for AccountNonLocked
                        */

                        
                                    protected boolean localAccountNonLocked ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountNonLockedTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getAccountNonLocked(){
                               return localAccountNonLocked;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountNonLocked
                               */
                               public void setAccountNonLocked(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (false) {
                                           localAccountNonLockedTracker = true;
                                              
                                       } else {
                                          localAccountNonLockedTracker = true;
                                       }
                                   
                                            this.localAccountNonLocked=param;
                                    

                               }
                            

                        /**
                        * field for CredentialsNonExpired
                        */

                        
                                    protected boolean localCredentialsNonExpired ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCredentialsNonExpiredTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getCredentialsNonExpired(){
                               return localCredentialsNonExpired;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CredentialsNonExpired
                               */
                               public void setCredentialsNonExpired(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (false) {
                                           localCredentialsNonExpiredTracker = true;
                                              
                                       } else {
                                          localCredentialsNonExpiredTracker = true;
                                       }
                                   
                                            this.localCredentialsNonExpired=param;
                                    

                               }
                            

                        /**
                        * field for Enabled
                        */

                        
                                    protected boolean localEnabled ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localEnabledTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getEnabled(){
                               return localEnabled;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Enabled
                               */
                               public void setEnabled(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (false) {
                                           localEnabledTracker = true;
                                              
                                       } else {
                                          localEnabledTracker = true;
                                       }
                                   
                                            this.localEnabled=param;
                                    

                               }
                            

                        /**
                        * field for TemporaryPassword
                        */

                        
                                    protected boolean localTemporaryPassword ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTemporaryPasswordTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return boolean
                           */
                           public  boolean getTemporaryPassword(){
                               return localTemporaryPassword;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TemporaryPassword
                               */
                               public void setTemporaryPassword(boolean param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (false) {
                                           localTemporaryPasswordTracker = true;
                                              
                                       } else {
                                          localTemporaryPasswordTracker = true;
                                       }
                                   
                                            this.localTemporaryPassword=param;
                                    

                               }
                            

                        /**
                        * field for PasswordCreatedDate
                        */

                        
                                    protected java.util.Date localPasswordCreatedDate ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPasswordCreatedDateTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.util.Date
                           */
                           public  java.util.Date getPasswordCreatedDate(){
                               return localPasswordCreatedDate;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PasswordCreatedDate
                               */
                               public void setPasswordCreatedDate(java.util.Date param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localPasswordCreatedDateTracker = true;
                                       } else {
                                          localPasswordCreatedDateTracker = true;
                                              
                                       }
                                   
                                            this.localPasswordCreatedDate=param;
                                    

                               }
                            

                        /**
                        * field for PartnerPasswordExpiration
                        */

                        
                                    protected int localPartnerPasswordExpiration ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPartnerPasswordExpirationTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getPartnerPasswordExpiration(){
                               return localPartnerPasswordExpiration;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PartnerPasswordExpiration
                               */
                               public void setPartnerPasswordExpiration(int param){
                            
                                       // setting primitive attribute tracker to true
                                       
                                               if (param==java.lang.Integer.MIN_VALUE) {
                                           localPartnerPasswordExpirationTracker = true;
                                              
                                       } else {
                                          localPartnerPasswordExpirationTracker = true;
                                       }
                                   
                                            this.localPartnerPasswordExpiration=param;
                                    

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
                       UserDetails.this.serialize(parentQName,factory,xmlWriter);
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
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:header.ws.unity.trilliantnetworks.com");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":UserDetails",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "UserDetails",
                           xmlWriter);
                   }

               
                   }
                if (localPasswordTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"password", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"password");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("password");
                                    }
                                

                                          if (localPassword==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPassword);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localUsernameTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"username", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"username");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("username");
                                    }
                                

                                          if (localUsername==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localUsername);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localIdTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"id", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"id");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("id");
                                    }
                                

                                          if (localId==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLogonIdTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"logonId", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"logonId");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("logonId");
                                    }
                                

                                          if (localLogonId==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLogonId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNameTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"name", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"name");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("name");
                                    }
                                

                                          if (localName==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEmailTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"email", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"email");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("email");
                                    }
                                

                                          if (localEmail==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localEmail);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPartnerIdTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"partnerId", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"partnerId");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("partnerId");
                                    }
                                

                                          if (localPartnerId==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPartnerId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPartnerNameTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"partnerName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"partnerName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("partnerName");
                                    }
                                

                                          if (localPartnerName==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPartnerName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPartnerNoTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"partnerNo", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"partnerNo");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("partnerNo");
                                    }
                                

                                          if (localPartnerNo==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPartnerNo);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPartnerCountryTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"partnerCountry", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"partnerCountry");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("partnerCountry");
                                    }
                                

                                          if (localPartnerCountry==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPartnerCountry);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPartnerTimeZoneLookupIdTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"partnerTimeZoneLookupId", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"partnerTimeZoneLookupId");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("partnerTimeZoneLookupId");
                                    }
                                

                                          if (localPartnerTimeZoneLookupId==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localPartnerTimeZoneLookupId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localSinglePartnerTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"singlePartner", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"singlePartner");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("singlePartner");
                                    }
                                
                                               if (false) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSinglePartner));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTimeZoneTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"timeZone", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"timeZone");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("timeZone");
                                    }
                                

                                          if (localTimeZone==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTimeZone);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTimeZoneOlsonNameTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"timeZoneOlsonName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"timeZoneOlsonName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("timeZoneOlsonName");
                                    }
                                

                                          if (localTimeZoneOlsonName==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTimeZoneOlsonName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localDateFormatTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"dateFormat", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"dateFormat");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("dateFormat");
                                    }
                                

                                          if (localDateFormat==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDateFormat);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTimeFormatTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"timeFormat", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"timeFormat");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("timeFormat");
                                    }
                                

                                          if (localTimeFormat==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localTimeFormat);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurrencySymbolTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"currencySymbol", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"currencySymbol");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("currencySymbol");
                                    }
                                

                                          if (localCurrencySymbol==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCurrencySymbol);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localHomePreferencesTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"homePreferences", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"homePreferences");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("homePreferences");
                                    }
                                

                                          if (localHomePreferences==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localHomePreferences);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localUtilityPreferencesTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"utilityPreferences", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"utilityPreferences");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("utilityPreferences");
                                    }
                                

                                          if (localUtilityPreferences==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localUtilityPreferences);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAuthoritiesTracker){
                                       if (localAuthorities!=null){
                                            for (int i = 0;i < localAuthorities.length;i++){
                                                if (localAuthorities[i] != null){
                                                 localAuthorities[i].serialize(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","authorities"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                            // write null attribute
                                                            java.lang.String namespace2 = "urn:header.ws.unity.trilliantnetworks.com";
                                                            if (! namespace2.equals("")) {
                                                                java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                                if (prefix2 == null) {
                                                                    prefix2 = generatePrefix(namespace2);

                                                                    xmlWriter.writeStartElement(prefix2,"authorities", namespace2);
                                                                    xmlWriter.writeNamespace(prefix2, namespace2);
                                                                    xmlWriter.setPrefix(prefix2, namespace2);

                                                                } else {
                                                                    xmlWriter.writeStartElement(namespace2,"authorities");
                                                                }

                                                            } else {
                                                                xmlWriter.writeStartElement("authorities");
                                                            }

                                                           // write the nil attribute
                                                           writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                           xmlWriter.writeEndElement();
                                                    
                                                }

                                            }
                                     } else {
                                        
                                                // write null attribute
                                                java.lang.String namespace2 = "urn:header.ws.unity.trilliantnetworks.com";
                                                if (! namespace2.equals("")) {
                                                    java.lang.String prefix2 = xmlWriter.getPrefix(namespace2);

                                                    if (prefix2 == null) {
                                                        prefix2 = generatePrefix(namespace2);

                                                        xmlWriter.writeStartElement(prefix2,"authorities", namespace2);
                                                        xmlWriter.writeNamespace(prefix2, namespace2);
                                                        xmlWriter.setPrefix(prefix2, namespace2);

                                                    } else {
                                                        xmlWriter.writeStartElement(namespace2,"authorities");
                                                    }

                                                } else {
                                                    xmlWriter.writeStartElement("authorities");
                                                }

                                               // write the nil attribute
                                               writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                               xmlWriter.writeEndElement();
                                        
                                    }
                                 } if (localAccountNonExpiredTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"accountNonExpired", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"accountNonExpired");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("accountNonExpired");
                                    }
                                
                                               if (false) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountNonExpired));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountNonLockedTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"accountNonLocked", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"accountNonLocked");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("accountNonLocked");
                                    }
                                
                                               if (false) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountNonLocked));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCredentialsNonExpiredTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"credentialsNonExpired", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"credentialsNonExpired");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("credentialsNonExpired");
                                    }
                                
                                               if (false) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCredentialsNonExpired));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localEnabledTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"enabled", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"enabled");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("enabled");
                                    }
                                
                                               if (false) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnabled));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localTemporaryPasswordTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"temporaryPassword", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"temporaryPassword");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("temporaryPassword");
                                    }
                                
                                               if (false) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTemporaryPassword));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPasswordCreatedDateTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"passwordCreatedDate", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"passwordCreatedDate");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("passwordCreatedDate");
                                    }
                                

                                          if (localPasswordCreatedDate==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPasswordCreatedDate));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPartnerPasswordExpirationTracker){
                                    namespace = "urn:header.ws.unity.trilliantnetworks.com";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"partnerPasswordExpiration", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"partnerPasswordExpiration");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("partnerPasswordExpiration");
                                    }
                                
                                               if (localPartnerPasswordExpiration==java.lang.Integer.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPartnerPasswordExpiration));
                                               }
                                    
                                   xmlWriter.writeEndElement();
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

                 if (localPasswordTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "password"));
                                 
                                         elementList.add(localPassword==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPassword));
                                    } if (localUsernameTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "username"));
                                 
                                         elementList.add(localUsername==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUsername));
                                    } if (localIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "id"));
                                 
                                         elementList.add(localId==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId));
                                    } if (localLogonIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "logonId"));
                                 
                                         elementList.add(localLogonId==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLogonId));
                                    } if (localNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "name"));
                                 
                                         elementList.add(localName==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localName));
                                    } if (localEmailTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "email"));
                                 
                                         elementList.add(localEmail==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEmail));
                                    } if (localPartnerIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "partnerId"));
                                 
                                         elementList.add(localPartnerId==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPartnerId));
                                    } if (localPartnerNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "partnerName"));
                                 
                                         elementList.add(localPartnerName==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPartnerName));
                                    } if (localPartnerNoTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "partnerNo"));
                                 
                                         elementList.add(localPartnerNo==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPartnerNo));
                                    } if (localPartnerCountryTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "partnerCountry"));
                                 
                                         elementList.add(localPartnerCountry==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPartnerCountry));
                                    } if (localPartnerTimeZoneLookupIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "partnerTimeZoneLookupId"));
                                 
                                         elementList.add(localPartnerTimeZoneLookupId==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPartnerTimeZoneLookupId));
                                    } if (localSinglePartnerTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "singlePartner"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localSinglePartner));
                            } if (localTimeZoneTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "timeZone"));
                                 
                                         elementList.add(localTimeZone==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTimeZone));
                                    } if (localTimeZoneOlsonNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "timeZoneOlsonName"));
                                 
                                         elementList.add(localTimeZoneOlsonName==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTimeZoneOlsonName));
                                    } if (localDateFormatTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "dateFormat"));
                                 
                                         elementList.add(localDateFormat==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDateFormat));
                                    } if (localTimeFormatTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "timeFormat"));
                                 
                                         elementList.add(localTimeFormat==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTimeFormat));
                                    } if (localCurrencySymbolTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "currencySymbol"));
                                 
                                         elementList.add(localCurrencySymbol==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCurrencySymbol));
                                    } if (localHomePreferencesTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "homePreferences"));
                                 
                                         elementList.add(localHomePreferences==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localHomePreferences));
                                    } if (localUtilityPreferencesTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "utilityPreferences"));
                                 
                                         elementList.add(localUtilityPreferences==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUtilityPreferences));
                                    } if (localAuthoritiesTracker){
                             if (localAuthorities!=null) {
                                 for (int i = 0;i < localAuthorities.length;i++){

                                    if (localAuthorities[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                          "authorities"));
                                         elementList.add(localAuthorities[i]);
                                    } else {
                                        
                                                elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                          "authorities"));
                                                elementList.add(null);
                                            
                                    }

                                 }
                             } else {
                                 
                                        elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                          "authorities"));
                                        elementList.add(localAuthorities);
                                    
                             }

                        } if (localAccountNonExpiredTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "accountNonExpired"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountNonExpired));
                            } if (localAccountNonLockedTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "accountNonLocked"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAccountNonLocked));
                            } if (localCredentialsNonExpiredTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "credentialsNonExpired"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCredentialsNonExpired));
                            } if (localEnabledTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "enabled"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnabled));
                            } if (localTemporaryPasswordTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "temporaryPassword"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localTemporaryPassword));
                            } if (localPasswordCreatedDateTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "passwordCreatedDate"));
                                 
                                         elementList.add(localPasswordCreatedDate==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPasswordCreatedDate));
                                    } if (localPartnerPasswordExpirationTracker){
                                      elementList.add(new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com",
                                                                      "partnerPasswordExpiration"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPartnerPasswordExpiration));
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
        public static UserDetails parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            UserDetails object =
                new UserDetails();

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
                    
                            if (!"UserDetails".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (UserDetails)com.trilliantnetworks.unity.ws.tobject.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                        java.util.ArrayList list20 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","password").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPassword(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","username").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUsername(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","id").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","logonId").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLogonId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","name").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","email").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEmail(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","partnerId").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPartnerId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","partnerName").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPartnerName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","partnerNo").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPartnerNo(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","partnerCountry").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPartnerCountry(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","partnerTimeZoneLookupId").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPartnerTimeZoneLookupId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","singlePartner").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setSinglePartner(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","timeZone").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTimeZone(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","timeZoneOlsonName").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTimeZoneOlsonName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","dateFormat").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDateFormat(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","timeFormat").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTimeFormat(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","currencySymbol").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCurrencySymbol(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","homePreferences").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setHomePreferences(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","utilityPreferences").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUtilityPreferences(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","authorities").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                                          nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                          if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                              list20.add(null);
                                                              reader.next();
                                                          } else {
                                                        list20.add(com.trilliantnetworks.unity.ws.header.GrantedAuthority.Factory.parse(reader));
                                                                }
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone20 = false;
                                                        while(!loopDone20){
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
                                                                loopDone20 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","authorities").equals(reader.getName())){
                                                                    
                                                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                                                          list20.add(null);
                                                                          reader.next();
                                                                      } else {
                                                                    list20.add(com.trilliantnetworks.unity.ws.header.GrantedAuthority.Factory.parse(reader));
                                                                        }
                                                                }else{
                                                                    loopDone20 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setAuthorities((com.trilliantnetworks.unity.ws.header.GrantedAuthority[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                com.trilliantnetworks.unity.ws.header.GrantedAuthority.class,
                                                                list20));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","accountNonExpired").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountNonExpired(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","accountNonLocked").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAccountNonLocked(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","credentialsNonExpired").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCredentialsNonExpired(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","enabled").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEnabled(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","temporaryPassword").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setTemporaryPassword(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","passwordCreatedDate").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPasswordCreatedDate(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("urn:header.ws.unity.trilliantnetworks.com","partnerPasswordExpiration").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPartnerPasswordExpiration(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setPartnerPasswordExpiration(java.lang.Integer.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setPartnerPasswordExpiration(java.lang.Integer.MIN_VALUE);
                                           
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
           
          
