
package com.elcom.eodapp.ehotel.sokha;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.elcom.eodapp.ehotel.sokha package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GuestPostReqFun_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "fun");
    private final static QName _GuestRoomMoveROOMSHAREFLAG_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "ROOM_SHARE_FLAG");
    private final static QName _GuestRoomMoveROOMEXSHAREFLAG_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "ROOM_EXSHARE_FLAG");
    private final static QName _GuestRoomMoveGUESTRESERVATION_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_RESERVATION");
    private final static QName _GuestRoomMoveGUESTEXROOM_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_EXROOM");
    private final static QName _GuestRoomMoveGUESTROOM_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_ROOM");
    private final static QName _DeleteBillItemROOMNUMER_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "ROOM_NUMER");
    private final static QName _DeleteBillItemRESERVATIONNUMER_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "RESERVATION_NUMER");
    private final static QName _GuestBillItemDATE_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "DATE");
    private final static QName _GuestBillItemITEMAMOUNT_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "ITEM_AMOUNT");
    private final static QName _GuestBillItemDISPLAYFLAG_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "DISPLAY_FLAG");
    private final static QName _GuestBillItemTIME_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "TIME");
    private final static QName _GuestBillItemITEMDESC_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "ITEM_DESC");
    private final static QName _GuestCheckinGUESTTVRIGHT_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_TV_RIGHT");
    private final static QName _GuestCheckinGUESTVIPSTATUS_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_VIP_STATUS");
    private final static QName _GuestCheckinGUESTTITLE_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_TITLE");
    private final static QName _GuestCheckinGUESTARVDATE_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_ARV_DATE");
    private final static QName _GuestCheckinGUESTLANGUAGE_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_LANGUAGE");
    private final static QName _GuestCheckinGUESTGROUP_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_GROUP");
    private final static QName _GuestCheckinGUESTLASTNAME_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_LAST_NAME");
    private final static QName _GuestCheckinGUESTFIRSTNAME_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_FIRST_NAME");
    private final static QName _GuestCheckinGUESTDEPTDATE_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_DEPT_DATE");
    private final static QName _GuestCheckinGUESTNAME_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_NAME");
    private final static QName _GuestCheckinGUESTBIRTHDAY_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_BIRTHDAY");
    private final static QName _GuestCheckinGUESTVIDEORIGHT_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "GUEST_VIDEO_RIGHT");
    private final static QName _GuestMessageTextOnlineLASTUPDATE_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "LAST_UPDATE");
    private final static QName _GuestMessageTextOnlineMESSAGETEXT_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "MESSAGE_TEXT");
    private final static QName _GuestMessageTextOnlineMESSAGEID_QNAME = new QName("http://sokha.ehotel.eodapp.elcom.com", "MESSAGE_ID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.elcom.eodapp.ehotel.sokha
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GuestPostReqResponse }
     * 
     */
    public GuestPostReqResponse createGuestPostReqResponse() {
        return new GuestPostReqResponse();
    }

    /**
     * Create an instance of {@link DeleteBillItemResponse }
     * 
     */
    public DeleteBillItemResponse createDeleteBillItemResponse() {
        return new DeleteBillItemResponse();
    }

    /**
     * Create an instance of {@link GuestBillItem }
     * 
     */
    public GuestBillItem createGuestBillItem() {
        return new GuestBillItem();
    }

    /**
     * Create an instance of {@link GuestCheckinResponse }
     * 
     */
    public GuestCheckinResponse createGuestCheckinResponse() {
        return new GuestCheckinResponse();
    }

    /**
     * Create an instance of {@link GuestMessageTextOnline }
     * 
     */
    public GuestMessageTextOnline createGuestMessageTextOnline() {
        return new GuestMessageTextOnline();
    }

    /**
     * Create an instance of {@link GuestRoomAndChangeMoveResponse }
     * 
     */
    public GuestRoomAndChangeMoveResponse createGuestRoomAndChangeMoveResponse() {
        return new GuestRoomAndChangeMoveResponse();
    }

    /**
     * Create an instance of {@link GuestCheckoutResponse }
     * 
     */
    public GuestCheckoutResponse createGuestCheckoutResponse() {
        return new GuestCheckoutResponse();
    }

    /**
     * Create an instance of {@link GuestBillReqResponse }
     * 
     */
    public GuestBillReqResponse createGuestBillReqResponse() {
        return new GuestBillReqResponse();
    }

    /**
     * Create an instance of {@link GuestCheckout }
     * 
     */
    public GuestCheckout createGuestCheckout() {
        return new GuestCheckout();
    }

    /**
     * Create an instance of {@link DeleteBillItem }
     * 
     */
    public DeleteBillItem createDeleteBillItem() {
        return new DeleteBillItem();
    }

    /**
     * Create an instance of {@link GuestBillItemResponse }
     * 
     */
    public GuestBillItemResponse createGuestBillItemResponse() {
        return new GuestBillItemResponse();
    }

    /**
     * Create an instance of {@link GuestBillReq }
     * 
     */
    public GuestBillReq createGuestBillReq() {
        return new GuestBillReq();
    }

    /**
     * Create an instance of {@link GuestRoomMoveResponse }
     * 
     */
    public GuestRoomMoveResponse createGuestRoomMoveResponse() {
        return new GuestRoomMoveResponse();
    }

    /**
     * Create an instance of {@link GuestRoomMove }
     * 
     */
    public GuestRoomMove createGuestRoomMove() {
        return new GuestRoomMove();
    }

    /**
     * Create an instance of {@link GuestPostReq }
     * 
     */
    public GuestPostReq createGuestPostReq() {
        return new GuestPostReq();
    }

    /**
     * Create an instance of {@link GuestMessageTextOnlineResponse }
     * 
     */
    public GuestMessageTextOnlineResponse createGuestMessageTextOnlineResponse() {
        return new GuestMessageTextOnlineResponse();
    }

    /**
     * Create an instance of {@link GuestCheckin }
     * 
     */
    public GuestCheckin createGuestCheckin() {
        return new GuestCheckin();
    }

    /**
     * Create an instance of {@link GuestRoomAndChangeMove }
     * 
     */
    public GuestRoomAndChangeMove createGuestRoomAndChangeMove() {
        return new GuestRoomAndChangeMove();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "fun", scope = GuestPostReq.class)
    public JAXBElement<String> createGuestPostReqFun(String value) {
        return new JAXBElement<String>(_GuestPostReqFun_QNAME, String.class, GuestPostReq.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "fun", scope = GuestBillReq.class)
    public JAXBElement<String> createGuestBillReqFun(String value) {
        return new JAXBElement<String>(_GuestPostReqFun_QNAME, String.class, GuestBillReq.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_SHARE_FLAG", scope = GuestRoomMove.class)
    public JAXBElement<String> createGuestRoomMoveROOMSHAREFLAG(String value) {
        return new JAXBElement<String>(_GuestRoomMoveROOMSHAREFLAG_QNAME, String.class, GuestRoomMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_EXSHARE_FLAG", scope = GuestRoomMove.class)
    public JAXBElement<String> createGuestRoomMoveROOMEXSHAREFLAG(String value) {
        return new JAXBElement<String>(_GuestRoomMoveROOMEXSHAREFLAG_QNAME, String.class, GuestRoomMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_RESERVATION", scope = GuestRoomMove.class)
    public JAXBElement<String> createGuestRoomMoveGUESTRESERVATION(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTRESERVATION_QNAME, String.class, GuestRoomMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_EXROOM", scope = GuestRoomMove.class)
    public JAXBElement<String> createGuestRoomMoveGUESTEXROOM(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTEXROOM_QNAME, String.class, GuestRoomMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_ROOM", scope = GuestRoomMove.class)
    public JAXBElement<String> createGuestRoomMoveGUESTROOM(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTROOM_QNAME, String.class, GuestRoomMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_NUMER", scope = DeleteBillItem.class)
    public JAXBElement<String> createDeleteBillItemROOMNUMER(String value) {
        return new JAXBElement<String>(_DeleteBillItemROOMNUMER_QNAME, String.class, DeleteBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "RESERVATION_NUMER", scope = DeleteBillItem.class)
    public JAXBElement<String> createDeleteBillItemRESERVATIONNUMER(String value) {
        return new JAXBElement<String>(_DeleteBillItemRESERVATIONNUMER_QNAME, String.class, DeleteBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_SHARE_FLAG", scope = GuestCheckout.class)
    public JAXBElement<String> createGuestCheckoutROOMSHAREFLAG(String value) {
        return new JAXBElement<String>(_GuestRoomMoveROOMSHAREFLAG_QNAME, String.class, GuestCheckout.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_RESERVATION", scope = GuestCheckout.class)
    public JAXBElement<String> createGuestCheckoutGUESTRESERVATION(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTRESERVATION_QNAME, String.class, GuestCheckout.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_ROOM", scope = GuestCheckout.class)
    public JAXBElement<String> createGuestCheckoutGUESTROOM(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTROOM_QNAME, String.class, GuestCheckout.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_NUMER", scope = GuestBillItem.class)
    public JAXBElement<String> createGuestBillItemROOMNUMER(String value) {
        return new JAXBElement<String>(_DeleteBillItemROOMNUMER_QNAME, String.class, GuestBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "DATE", scope = GuestBillItem.class)
    public JAXBElement<String> createGuestBillItemDATE(String value) {
        return new JAXBElement<String>(_GuestBillItemDATE_QNAME, String.class, GuestBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ITEM_AMOUNT", scope = GuestBillItem.class)
    public JAXBElement<String> createGuestBillItemITEMAMOUNT(String value) {
        return new JAXBElement<String>(_GuestBillItemITEMAMOUNT_QNAME, String.class, GuestBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "DISPLAY_FLAG", scope = GuestBillItem.class)
    public JAXBElement<String> createGuestBillItemDISPLAYFLAG(String value) {
        return new JAXBElement<String>(_GuestBillItemDISPLAYFLAG_QNAME, String.class, GuestBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "TIME", scope = GuestBillItem.class)
    public JAXBElement<String> createGuestBillItemTIME(String value) {
        return new JAXBElement<String>(_GuestBillItemTIME_QNAME, String.class, GuestBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ITEM_DESC", scope = GuestBillItem.class)
    public JAXBElement<String> createGuestBillItemITEMDESC(String value) {
        return new JAXBElement<String>(_GuestBillItemITEMDESC_QNAME, String.class, GuestBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "RESERVATION_NUMER", scope = GuestBillItem.class)
    public JAXBElement<String> createGuestBillItemRESERVATIONNUMER(String value) {
        return new JAXBElement<String>(_DeleteBillItemRESERVATIONNUMER_QNAME, String.class, GuestBillItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_TV_RIGHT", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTTVRIGHT(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTTVRIGHT_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_VIP_STATUS", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTVIPSTATUS(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTVIPSTATUS_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_RESERVATION", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTRESERVATION(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTRESERVATION_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_TITLE", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTTITLE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTTITLE_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_ARV_DATE", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTARVDATE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTARVDATE_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_LANGUAGE", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTLANGUAGE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTLANGUAGE_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_GROUP", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTGROUP(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTGROUP_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_SHARE_FLAG", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinROOMSHAREFLAG(String value) {
        return new JAXBElement<String>(_GuestRoomMoveROOMSHAREFLAG_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_LAST_NAME", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTLASTNAME(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTLASTNAME_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_FIRST_NAME", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTFIRSTNAME(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTFIRSTNAME_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_DEPT_DATE", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTDEPTDATE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTDEPTDATE_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_NAME", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTNAME(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTNAME_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_BIRTHDAY", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTBIRTHDAY(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTBIRTHDAY_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_VIDEO_RIGHT", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTVIDEORIGHT(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTVIDEORIGHT_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_ROOM", scope = GuestCheckin.class)
    public JAXBElement<String> createGuestCheckinGUESTROOM(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTROOM_QNAME, String.class, GuestCheckin.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_TV_RIGHT", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTTVRIGHT(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTTVRIGHT_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_VIP_STATUS", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTVIPSTATUS(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTVIPSTATUS_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_TITLE", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTTITLE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTTITLE_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_RESERVATION", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTRESERVATION(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTRESERVATION_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_ARV_DATE", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTARVDATE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTARVDATE_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_LANGUAGE", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTLANGUAGE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTLANGUAGE_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_GROUP", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTGROUP(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTGROUP_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_EXSHARE_FLAG", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveROOMEXSHAREFLAG(String value) {
        return new JAXBElement<String>(_GuestRoomMoveROOMEXSHAREFLAG_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "ROOM_SHARE_FLAG", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveROOMSHAREFLAG(String value) {
        return new JAXBElement<String>(_GuestRoomMoveROOMSHAREFLAG_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_LAST_NAME", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTLASTNAME(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTLASTNAME_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_FIRST_NAME", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTFIRSTNAME(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTFIRSTNAME_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_EXROOM", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTEXROOM(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTEXROOM_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_DEPT_DATE", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTDEPTDATE(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTDEPTDATE_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_NAME", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTNAME(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTNAME_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_VIDEO_RIGHT", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTVIDEORIGHT(String value) {
        return new JAXBElement<String>(_GuestCheckinGUESTVIDEORIGHT_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_ROOM", scope = GuestRoomAndChangeMove.class)
    public JAXBElement<String> createGuestRoomAndChangeMoveGUESTROOM(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTROOM_QNAME, String.class, GuestRoomAndChangeMove.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_RESERVATION", scope = GuestMessageTextOnline.class)
    public JAXBElement<String> createGuestMessageTextOnlineGUESTRESERVATION(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTRESERVATION_QNAME, String.class, GuestMessageTextOnline.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "LAST_UPDATE", scope = GuestMessageTextOnline.class)
    public JAXBElement<String> createGuestMessageTextOnlineLASTUPDATE(String value) {
        return new JAXBElement<String>(_GuestMessageTextOnlineLASTUPDATE_QNAME, String.class, GuestMessageTextOnline.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "MESSAGE_TEXT", scope = GuestMessageTextOnline.class)
    public JAXBElement<String> createGuestMessageTextOnlineMESSAGETEXT(String value) {
        return new JAXBElement<String>(_GuestMessageTextOnlineMESSAGETEXT_QNAME, String.class, GuestMessageTextOnline.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "MESSAGE_ID", scope = GuestMessageTextOnline.class)
    public JAXBElement<String> createGuestMessageTextOnlineMESSAGEID(String value) {
        return new JAXBElement<String>(_GuestMessageTextOnlineMESSAGEID_QNAME, String.class, GuestMessageTextOnline.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sokha.ehotel.eodapp.elcom.com", name = "GUEST_ROOM", scope = GuestMessageTextOnline.class)
    public JAXBElement<String> createGuestMessageTextOnlineGUESTROOM(String value) {
        return new JAXBElement<String>(_GuestRoomMoveGUESTROOM_QNAME, String.class, GuestMessageTextOnline.class, value);
    }

}
