
/**
 * UnityServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */
    package com.trilliantnetworks.unity.ws;
    /**
     *  UnityServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface UnityServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * Logout from the Trilliant Networks
                                    * @param logout
             * @throws UnexpectedErrorFault : 
             * @throws AccessDeniedFault : 
         */

        
                public com.trilliantnetworks.unity.ws.LogoutResponse logout
                (
                  com.trilliantnetworks.unity.ws.Logout logout
                 )
            throws UnexpectedErrorFault,AccessDeniedFault;
        
         
        /**
         * Auto generated method signature
         * Apply Vend Code
                                    * @param applyVendCodeRequest
             * @throws UnexpectedErrorFault : 
             * @throws AccessDeniedFault : 
             * @throws LoginFault : 
         */

        
                public com.trilliantnetworks.unity.ws.ApplyVendCodeResponse applyVendCode
                (
                  com.trilliantnetworks.unity.ws.ApplyVendCodeRequest applyVendCodeRequest
                 )
            throws UnexpectedErrorFault,AccessDeniedFault,LoginFault;
        
         
        /**
         * Auto generated method signature
         * Login to the Trilliant Networks
                                    * @param login
             * @throws UnexpectedErrorFault : 
             * @throws AccessDeniedFault : 
             * @throws LoginFault : 
         */

        
                public com.trilliantnetworks.unity.ws.LoginResponse login
                (
                  com.trilliantnetworks.unity.ws.Login login
                 )
            throws UnexpectedErrorFault,AccessDeniedFault,LoginFault;
        
         }
    
