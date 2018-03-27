
package com.elcom.eodapp.ehotel.common.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for oRoomData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="oRoomData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cLEAR_TEXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rOOM_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rOOM_STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uSERID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vOICE_MAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oRoomData", propOrder = {
    "cleartext",
    "roomnumber",
    "roomstatus",
    "userid",
    "voicemail"
})
public class ORoomData {

    @XmlElementRef(name = "cLEAR_TEXT", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cleartext;
    @XmlElementRef(name = "rOOM_NUMBER", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomnumber;
    @XmlElementRef(name = "rOOM_STATUS", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomstatus;
    @XmlElementRef(name = "uSERID", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> userid;
    @XmlElementRef(name = "vOICE_MAIL", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> voicemail;

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
     * Gets the value of the roomnumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getROOMNUMBER() {
        return roomnumber;
    }

    /**
     * Sets the value of the roomnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setROOMNUMBER(JAXBElement<String> value) {
        this.roomnumber = value;
    }

    /**
     * Gets the value of the roomstatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getROOMSTATUS() {
        return roomstatus;
    }

    /**
     * Sets the value of the roomstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setROOMSTATUS(JAXBElement<String> value) {
        this.roomstatus = value;
    }

    /**
     * Gets the value of the userid property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUSERID() {
        return userid;
    }

    /**
     * Sets the value of the userid property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUSERID(JAXBElement<String> value) {
        this.userid = value;
    }

    /**
     * Gets the value of the voicemail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVOICEMAIL() {
        return voicemail;
    }

    /**
     * Sets the value of the voicemail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVOICEMAIL(JAXBElement<String> value) {
        this.voicemail = value;
    }

}
