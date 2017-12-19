
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
 *         &lt;element name="GUEST_EXROOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_RESERVATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GUEST_ROOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ROOM_EXSHARE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "guestexroom",
    "guestreservation",
    "guestroom",
    "roomexshareflag",
    "roomshareflag"
})
@XmlRootElement(name = "GuestRoomMove")
public class GuestRoomMove {

    @XmlElementRef(name = "GUEST_EXROOM", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestexroom;
    @XmlElementRef(name = "GUEST_RESERVATION", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestreservation;
    @XmlElementRef(name = "GUEST_ROOM", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> guestroom;
    @XmlElementRef(name = "ROOM_EXSHARE_FLAG", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomexshareflag;
    @XmlElementRef(name = "ROOM_SHARE_FLAG", namespace = "http://sokha.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomshareflag;

    /**
     * Gets the value of the guestexroom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getGUESTEXROOM() {
        return guestexroom;
    }

    /**
     * Sets the value of the guestexroom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGUESTEXROOM(JAXBElement<String> value) {
        this.guestexroom = value;
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
     * Gets the value of the roomexshareflag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getROOMEXSHAREFLAG() {
        return roomexshareflag;
    }

    /**
     * Sets the value of the roomexshareflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setROOMEXSHAREFLAG(JAXBElement<String> value) {
        this.roomexshareflag = value;
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
