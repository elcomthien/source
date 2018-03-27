
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
 *         &lt;element name="ROOM_NUMER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RESERVATION_NUMER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ANSWER_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CLEAR_TEXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEQ_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TIME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "roomnumer",
    "reservationnumer",
    "answerstatus",
    "cleartext",
    "seqnumber",
    "date",
    "time"
})
@XmlRootElement(name = "GuestPostAnswer")
public class GuestPostAnswer {

    @XmlElementRef(name = "ROOM_NUMER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomnumer;
    @XmlElementRef(name = "RESERVATION_NUMER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> reservationnumer;
    @XmlElementRef(name = "ANSWER_STATUS", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> answerstatus;
    @XmlElementRef(name = "CLEAR_TEXT", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cleartext;
    @XmlElementRef(name = "SEQ_NUMBER", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> seqnumber;
    @XmlElementRef(name = "DATE", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> date;
    @XmlElementRef(name = "TIME", namespace = "http://service.ehotel.eodapp.elcom.com", type = JAXBElement.class, required = false)
    protected JAXBElement<String> time;

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
     * Gets the value of the answerstatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getANSWERSTATUS() {
        return answerstatus;
    }

    /**
     * Sets the value of the answerstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setANSWERSTATUS(JAXBElement<String> value) {
        this.answerstatus = value;
    }

    /**
     * Gets the value of the cleartext property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCLEARTEXT() {
        return cleartext;
    }

    /**
     * Sets the value of the cleartext property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCLEARTEXT(JAXBElement<String> value) {
        this.cleartext = value;
    }

    /**
     * Gets the value of the seqnumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSEQNUMBER() {
        return seqnumber;
    }

    /**
     * Sets the value of the seqnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSEQNUMBER(JAXBElement<String> value) {
        this.seqnumber = value;
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

}
