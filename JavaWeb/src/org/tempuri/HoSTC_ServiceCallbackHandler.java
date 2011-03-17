
/**
 * HoSTC_ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

    package org.tempuri;

    /**
     *  HoSTC_ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class HoSTC_ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public HoSTC_ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public HoSTC_ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for fPUT_THROUGHT method
            * override this method for handling normal response from fPUT_THROUGHT operation
            */
           public void receiveResultfPUT_THROUGHT(
                    org.tempuri.HoSTC_ServiceStub.FPUT_THROUGHTResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fPUT_THROUGHT operation
           */
            public void receiveErrorfPUT_THROUGHT(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetLiveSecurity method
            * override this method for handling normal response from GetLiveSecurity operation
            */
           public void receiveResultGetLiveSecurity(
                    org.tempuri.HoSTC_ServiceStub.GetLiveSecurityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetLiveSecurity operation
           */
            public void receiveErrorGetLiveSecurity(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetLiveTotalMKT method
            * override this method for handling normal response from GetLiveTotalMKT operation
            */
           public void receiveResultGetLiveTotalMKT(
                    org.tempuri.HoSTC_ServiceStub.GetLiveTotalMKTResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetLiveTotalMKT operation
           */
            public void receiveErrorGetLiveTotalMKT(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getStatus method
            * override this method for handling normal response from getStatus operation
            */
           public void receiveResultgetStatus(
                    org.tempuri.HoSTC_ServiceStub.GetStatusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getStatus operation
           */
            public void receiveErrorgetStatus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for fUserOnline method
            * override this method for handling normal response from fUserOnline operation
            */
           public void receiveResultfUserOnline(
                    org.tempuri.HoSTC_ServiceStub.FUserOnlineResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fUserOnline operation
           */
            public void receiveErrorfUserOnline(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for GetFileTotal method
            * override this method for handling normal response from GetFileTotal operation
            */
           public void receiveResultGetFileTotal(
                    org.tempuri.HoSTC_ServiceStub.GetFileTotalResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from GetFileTotal operation
           */
            public void receiveErrorGetFileTotal(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for fGetSTOCKROOM method
            * override this method for handling normal response from fGetSTOCKROOM operation
            */
           public void receiveResultfGetSTOCKROOM(
                    org.tempuri.HoSTC_ServiceStub.FGetSTOCKROOMResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from fGetSTOCKROOM operation
           */
            public void receiveErrorfGetSTOCKROOM(java.lang.Exception e) {
            }
                


    }
    