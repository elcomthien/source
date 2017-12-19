
package com.elcom.eodapp.ehotel.sokha;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GUEST_ARV_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_DEPT_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_FIRST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_LAST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_RESERVATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_ROOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_LANGUAGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_GROUP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_TITLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_VIP_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_TV_RIGHT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_VIDEO_RIGHT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_BIRTHDAY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ROOM_SHARE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "guestarvdate",
    "guestdeptdate",
    "guestfirstname",
    "guestlastname",
    "guestname",
    "guestreservation",
    "guestroom",
    "guestlanguage",
    "guestgroup",
    "guesttitle",
    "guestvipstatus",
    "guesttvright",
    "guestvideoright",
    "guestbirthday",
    "roomshareflag"
})
@XmlRootElement(name = "GuestCheckin")
public class GuestCheckin {

    @XmlElementRef(name = "GUEST_ARV_DATE", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestarvdate;
    @XmlElementRef(name = "GUEST_DEPT_DATE", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestdeptdate;
    @XmlElementRef(name = "GUEST_FIRST_NAME", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestfirstname;
    @XmlElementRef(name = "GUEST_LAST_NAME", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestlastname;
    @XmlElementRef(name = "GUEST_NAME", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestname;
    @XmlElementRef(name = "GUEST_RESERVATION", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestreservation;
    @XmlElementRef(name = "GUEST_ROOM", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestroom;
    @XmlElementRef(name = "GUEST_LANGUAGE", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestlanguage;
    @XmlElementRef(name = "GUEST_GROUP", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestgroup;
    @XmlElementRef(name = "GUEST_TITLE", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guesttitle;
    @XmlElementRef(name = "GUEST_VIP_STATUS", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestvipstatus;
    @XmlElementRef(name = "GUEST_TV_RIGHT", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guesttvright;
    @XmlElementRef(name = "GUEST_VIDEO_RIGHT", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestvideoright;
    @XmlElementRef(name = "GUEST_BIRTHDAY", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestbirthday;
    @XmlElementRef(name = "ROOM_SHARE_FLAG", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomshareflag;

    /**
     * Gets the value of the guestarvdate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTARVDATE() {
        return guestarvdate;
    }

    /**
     * Sets the value of the guestarvdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTARVDATE(JAXBElement<String> value) {
        this.guestarvdate = value;
    }

    /**
     * Gets the value of the guestdeptdate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTDEPTDATE() {
        return guestdeptdate;
    }

    /**
     * Sets the value of the guestdeptdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTDEPTDATE(JAXBElement<String> value) {
        this.guestdeptdate = value;
    }

    /**
     * Gets the value of the guestfirstname property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTFIRSTNAME() {
        return guestfirstname;
    }

    /**
     * Sets the value of the guestfirstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTFIRSTNAME(JAXBElement<String> value) {
        this.guestfirstname = value;
    }

    /**
     * Gets the value of the guestlastname property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTLASTNAME() {
        return guestlastname;
    }

    /**
     * Sets the value of the guestlastname property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTLASTNAME(JAXBElement<String> value) {
        this.guestlastname = value;
    }

    /**
     * Gets the value of the guestname property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTNAME() {
        return guestname;
    }

    /**
     * Sets the value of the guestname property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTNAME(JAXBElement<String> value) {
        this.guestname = value;
    }

    /**
     * Gets the value of the guestreservation property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTRESERVATION() {
        return guestreservation;
    }

    /**
     * Sets the value of the guestreservation property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTRESERVATION(JAXBElement<String> value) {
        this.guestreservation = value;
    }

    /**
     * Gets the value of the guestroom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTROOM() {
        return guestroom;
    }

    /**
     * Sets the value of the guestroom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTROOM(JAXBElement<String> value) {
        this.guestroom = value;
    }

    /**
     * Gets the value of the guestlanguage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTLANGUAGE() {
        return guestlanguage;
    }

    /**
     * Sets the value of the guestlanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTLANGUAGE(JAXBElement<String> value) {
        this.guestlanguage = value;
    }

    /**
     * Gets the value of the guestgroup property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTGROUP() {
        return guestgroup;
    }

    /**
     * Sets the value of the guestgroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTGROUP(JAXBElement<String> value) {
        this.guestgroup = value;
    }

    /**
     * Gets the value of the guesttitle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTTITLE() {
        return guesttitle;
    }

    /**
     * Sets the value of the guesttitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTTITLE(JAXBElement<String> value) {
        this.guesttitle = value;
    }

    /**
     * Gets the value of the guestvipstatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTVIPSTATUS() {
        return guestvipstatus;
    }

    /**
     * Sets the value of the guestvipstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTVIPSTATUS(JAXBElement<String> value) {
        this.guestvipstatus = value;
    }

    /**
     * Gets the value of the guesttvright property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTTVRIGHT() {
        return guesttvright;
    }

    /**
     * Sets the value of the guesttvright property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTTVRIGHT(JAXBElement<String> value) {
        this.guesttvright = value;
    }

    /**
     * Gets the value of the guestvideoright property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTVIDEORIGHT() {
        return guestvideoright;
    }

    /**
     * Sets the value of the guestvideoright property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTVIDEORIGHT(JAXBElement<String> value) {
        this.guestvideoright = value;
    }

    /**
     * Gets the value of the guestbirthday property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTBIRTHDAY() {
        return guestbirthday;
    }

    /**
     * Sets the value of the guestbirthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTBIRTHDAY(JAXBElement<String> value) {
        this.guestbirthday = value;
    }

    /**
     * Gets the value of the roomshareflag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getROOMSHAREFLAG() {
        return roomshareflag;
    }

    /**
     * Sets the value of the roomshareflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setROOMSHAREFLAG(JAXBElement<String> value) {
        this.roomshareflag = value;
    }

}
