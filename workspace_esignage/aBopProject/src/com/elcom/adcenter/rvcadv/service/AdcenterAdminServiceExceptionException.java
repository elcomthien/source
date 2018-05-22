
/**
 * AdcenterAdminServiceExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.elcom.adcenter.rvcadv.service;

public class AdcenterAdminServiceExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1443598956730L;
    
    private com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdcenterAdminServiceException faultMessage;

    
        public AdcenterAdminServiceExceptionException() {
            super("AdcenterAdminServiceExceptionException");
        }

        public AdcenterAdminServiceExceptionException(java.lang.String s) {
           super(s);
        }

        public AdcenterAdminServiceExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public AdcenterAdminServiceExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdcenterAdminServiceException msg){
       faultMessage = msg;
    }
    
    public com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdcenterAdminServiceException getFaultMessage(){
       return faultMessage;
    }
}
    