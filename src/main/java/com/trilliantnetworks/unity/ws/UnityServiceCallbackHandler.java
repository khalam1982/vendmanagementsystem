
/**
 * UnityServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

    package com.trilliantnetworks.unity.ws;

    /**
     *  UnityServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class UnityServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public UnityServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public UnityServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for logout method
            * override this method for handling normal response from logout operation
            */
           public void receiveResultlogout(
                    com.trilliantnetworks.unity.ws.LogoutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from logout operation
           */
            public void receiveErrorlogout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for applyVendCode method
            * override this method for handling normal response from applyVendCode operation
            */
           public void receiveResultapplyVendCode(
                    com.trilliantnetworks.unity.ws.ApplyVendCodeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from applyVendCode operation
           */
            public void receiveErrorapplyVendCode(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for login method
            * override this method for handling normal response from login operation
            */
           public void receiveResultlogin(
                    com.trilliantnetworks.unity.ws.LoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from login operation
           */
            public void receiveErrorlogin(java.lang.Exception e) {
            }

            /**
             * auto generated Axis2 call back method for updatePrepaymentKey method
             * override this method for handling normal response from updatePrepaymentKey operation
             */
            public void receiveResultupdatePrepaymentKey(
            		com.trilliantnetworks.unity.ws.UpdatePrepaymentKeyResponse result
            ) {
            }

            /**
             * auto generated Axis2 Error handler
             * override this method for handling error response from updatePrepaymentKey operation
             */
            public void receiveErrorupdatePrepaymentKey(java.lang.Exception e) {
            }

    }
    
