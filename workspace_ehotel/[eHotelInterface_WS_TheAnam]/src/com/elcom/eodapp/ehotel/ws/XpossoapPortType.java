// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation (1.1.3, build R1)
// Generated source version: 1.1.2

package com.elcom.eodapp.ehotel.ws;

public interface XpossoapPortType extends java.rmi.Remote {
    public void readMessage(java.lang.String sMessageId, javax.xml.rpc.holders.StringHolder responseCode, javax.xml.rpc.holders.StringHolder responseMessage, javax.xml.rpc.holders.StringHolder responseData) throws 
         java.rmi.RemoteException;
    public void postCharge(java.lang.String sRoomNo, java.lang.String sGuestNo, double sAmount, javax.xml.rpc.holders.StringHolder responseCode, javax.xml.rpc.holders.StringHolder responseMessage, javax.xml.rpc.holders.StringHolder responseData) throws 
         java.rmi.RemoteException;
    public void guestMessage(java.lang.String sRoomNo, java.lang.String sGuestNo, javax.xml.rpc.holders.StringHolder responseCode, javax.xml.rpc.holders.StringHolder responseMessage, javax.xml.rpc.holders.StringHolder responseData) throws 
         java.rmi.RemoteException;
    public void bill(java.lang.String sRoomNo, java.lang.String sGuestNo, javax.xml.rpc.holders.StringHolder responseCode, javax.xml.rpc.holders.StringHolder responseMessage, javax.xml.rpc.holders.StringHolder responseData) throws 
         java.rmi.RemoteException;
    public void dbSwap(javax.xml.rpc.holders.StringHolder responseCode, javax.xml.rpc.holders.StringHolder responseMessage, javax.xml.rpc.holders.StringHolder responseData) throws 
         java.rmi.RemoteException;
}
