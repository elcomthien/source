
package com.elcom.eodapp.ehotel.service;

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
 *         &lt;element name="RESERVATION_NUMER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_ARV_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_DEPT_DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_TITLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_FIRST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ROOM_NUMER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_PIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SHARE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_LANGUAGE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_GROUP_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SWAP_FLAG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reservationnumer",
    "guestarvdate",
    "guestdeptdate",
    "guesttitle",
    "guestfirstname",
    "guestname",
    "roomnumer",
    "guestpin",
    "shareflag",
    "guestlanguage",
    "guestgroupnumber",
    "date",
    "time",
    "swapflag"
})
@XmlRootElement(name = "GuestCheckin")
public class GuestCheckin {

    @XmlElementRef(name = "RESERVATION_NUMER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> reservationnumer;
    @XmlElementRef(name = "GUEST_ARV_DATE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestarvdate;
    @XmlElementRef(name = "GUEST_DEPT_DATE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestdeptdate;
    @XmlElementRef(name = "GUEST_TITLE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guesttitle;
    @XmlElementRef(name = "GUEST_FIRST_NAME", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestfirstname;
    @XmlElementRef(name = "GUEST_NAME", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestname;
    @XmlElementRef(name = "ROOM_NUMER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomnumer;
    @XmlElementRef(name = "GUEST_PIN", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestpin;
    @XmlElementRef(name = "SHARE_FLAG", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> shareflag;
    @XmlElementRef(name = "GUEST_LANGUAGE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestlanguage;
    @XmlElementRef(name = "GUEST_GROUP_NUMBER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestgroupnumber;
    @XmlElementRef(name = "DATE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> date;
    @XmlElementRef(name = "TIME", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> time;
    @XmlElementRef(name = "SWAP_FLAG", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> swapflag;

    /**
     * Gets the value of the reservationnumer property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRESERVATIONNUMER() {
        return reservationnumer;
    }

    /**
     * Sets the value of the reservationnumer property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRESERVATIONNUMER(JAXBElement<String> value) {
        this.reservationnumer = value;
    }

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
     * Gets the value of the roomnumer property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getROOMNUMER() {
        return roomnumer;
    }

    /**
     * Sets the value of the roomnumer property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setROOMNUMER(JAXBElement<String> value) {
        this.roomnumer = value;
    }

    /**
     * Gets the value of the guestpin property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTPIN() {
        return guestpin;
    }

    /**
     * Sets the value of the guestpin property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTPIN(JAXBElement<String> value) {
        this.guestpin = value;
    }

    /**
     * Gets the value of the shareflag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSHAREFLAG() {
        return shareflag;
    }

    /**
     * Sets the value of the shareflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSHAREFLAG(JAXBElement<String> value) {
        this.shareflag = value;
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
     * Gets the value of the guestgroupnumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTGROUPNUMBER() {
        return guestgroupnumber;
    }

    /**
     * Sets the value of the guestgroupnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTGROUPNUMBER(JAXBElement<String> value) {
        this.guestgroupnumber = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDATE() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDATE(JAXBElement<String> value) {
        this.date = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTIME() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTIME(JAXBElement<String> value) {
        this.time = value;
    }

    /**
     * Gets the value of the swapflag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSWAPFLAG() {
        return swapflag;
    }

    /**
     * Sets the value of the swapflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSWAPFLAG(JAXBElement<String> value) {
        this.swapflag = value;
    }

}
