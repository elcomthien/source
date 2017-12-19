
package com.elcom.eodapp.ehotel.common.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for oBill complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="oBill">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rESERVATION_NUMER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rOOM_NUMER" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oBill", propOrder = {
    "reservationnumer",
    "roomnumer"
})
public class OBill {

    @XmlElementRef(name = "rESERVATION_NUMER", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> reservationnumer;
    @XmlElementRef(name = "rOOM_NUMER", namespace = "http://common.ehotel.eodapp.elcom.com/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomnumer;

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

}
