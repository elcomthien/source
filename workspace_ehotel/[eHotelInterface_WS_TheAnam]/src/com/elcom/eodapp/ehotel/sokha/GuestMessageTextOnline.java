
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
 *         &lt;element name="GUEST_ROOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_RESERVATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MESSAGE_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MESSAGE_TEXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LAST_UPDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "guestroom",
    "guestreservation",
    "messageid",
    "messagetext",
    "lastupdate"
})
@XmlRootElement(name = "GuestMessageTextOnline")
public class GuestMessageTextOnline {

    @XmlElementRef(name = "GUEST_ROOM", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestroom;
    @XmlElementRef(name = "GUEST_RESERVATION", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestreservation;
    @XmlElementRef(name = "MESSAGE_ID", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> messageid;
    @XmlElementRef(name = "MESSAGE_TEXT", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> messagetext;
    @XmlElementRef(name = "LAST_UPDATE", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> lastupdate;

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
     * Gets the value of the messageid property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMESSAGEID() {
        return messageid;
    }

    /**
     * Sets the value of the messageid property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMESSAGEID(JAXBElement<String> value) {
        this.messageid = value;
    }

    /**
     * Gets the value of the messagetext property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMESSAGETEXT() {
        return messagetext;
    }

    /**
     * Sets the value of the messagetext property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMESSAGETEXT(JAXBElement<String> value) {
        this.messagetext = value;
    }

    /**
     * Gets the value of the lastupdate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLASTUPDATE() {
        return lastupdate;
    }

    /**
     * Sets the value of the lastupdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLASTUPDATE(JAXBElement<String> value) {
        this.lastupdate = value;
    }

}
