
package com.elcom.eodapp.ehotel.common.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for oPost complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="oPost">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cLEAR_TEXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dIALED_DIGITS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dURATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mINIBAR_ARTICLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nUMOF_ARTICLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pOSTING_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pOST_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pOST_CALL_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pOST_SEQ_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rOOM_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sALES_OUTLET" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tAX_PULSE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tIME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oPost", propOrder = {
    "cleartext",
    "date",
    "dialeddigits",
    "duration",
    "minibararticle",
    "numofarticle",
    "postingtype",
    "postamount",
    "postcalltype",
    "postseqnumber",
    "roomnumber",
    "salesoutlet",
    "taxpulse",
    "time"
})
public class OPost {

    @XmlElementRef(name = "cLEAR_TEXT", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> cleartext;
    @XmlElementRef(name = "dATE", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> date;
    @XmlElementRef(name = "dIALED_DIGITS", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> dialeddigits;
    @XmlElementRef(name = "dURATION", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> duration;
    @XmlElementRef(name = "mINIBAR_ARTICLE", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> minibararticle;
    @XmlElementRef(name = "nUMOF_ARTICLE", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> numofarticle;
    @XmlElementRef(name = "pOSTING_TYPE", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> postingtype;
    @XmlElementRef(name = "pOST_AMOUNT", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> postamount;
    @XmlElementRef(name = "pOST_CALL_TYPE", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> postcalltype;
    @XmlElementRef(name = "pOST_SEQ_NUMBER", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> postseqnumber;
    @XmlElementRef(name = "rOOM_NUMBER", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomnumber;
    @XmlElementRef(name = "sALES_OUTLET", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> salesoutlet;
    @XmlElementRef(name = "tAX_PULSE", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> taxpulse;
    @XmlElementRef(name = "tIME", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> time;

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
     * Gets the value of the dialeddigits property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDIALEDDIGITS() {
        return dialeddigits;
    }

    /**
     * Sets the value of the dialeddigits property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDIALEDDIGITS(JAXBElement<String> value) {
        this.dialeddigits = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDURATION() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDURATION(JAXBElement<String> value) {
        this.duration = value;
    }

    /**
     * Gets the value of the minibararticle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMINIBARARTICLE() {
        return minibararticle;
    }

    /**
     * Sets the value of the minibararticle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMINIBARARTICLE(JAXBElement<String> value) {
        this.minibararticle = value;
    }

    /**
     * Gets the value of the numofarticle property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNUMOFARTICLE() {
        return numofarticle;
    }

    /**
     * Sets the value of the numofarticle property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNUMOFARTICLE(JAXBElement<String> value) {
        this.numofarticle = value;
    }

    /**
     * Gets the value of the postingtype property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPOSTINGTYPE() {
        return postingtype;
    }

    /**
     * Sets the value of the postingtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPOSTINGTYPE(JAXBElement<String> value) {
        this.postingtype = value;
    }

    /**
     * Gets the value of the postamount property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPOSTAMOUNT() {
        return postamount;
    }

    /**
     * Sets the value of the postamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPOSTAMOUNT(JAXBElement<String> value) {
        this.postamount = value;
    }

    /**
     * Gets the value of the postcalltype property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPOSTCALLTYPE() {
        return postcalltype;
    }

    /**
     * Sets the value of the postcalltype property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPOSTCALLTYPE(JAXBElement<String> value) {
        this.postcalltype = value;
    }

    /**
     * Gets the value of the postseqnumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPOSTSEQNUMBER() {
        return postseqnumber;
    }

    /**
     * Sets the value of the postseqnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPOSTSEQNUMBER(JAXBElement<String> value) {
        this.postseqnumber = value;
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
     * Gets the value of the salesoutlet property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSALESOUTLET() {
        return salesoutlet;
    }

    /**
     * Sets the value of the salesoutlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSALESOUTLET(JAXBElement<String> value) {
        this.salesoutlet = value;
    }

    /**
     * Gets the value of the taxpulse property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTAXPULSE() {
        return taxpulse;
    }

    /**
     * Sets the value of the taxpulse property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTAXPULSE(JAXBElement<String> value) {
        this.taxpulse = value;
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
