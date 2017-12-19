
package com.elcom.eodapp.ehotel.common.xsd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.elcom.eodapp.ehotel.common.xsd package. 
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

    private final static QName _OBillROOMNUMER_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "rOOM_NUMER");
    private final static QName _OBillRESERVATIONNUMER_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "rESERVATION_NUMER");
    private final static QName _OPostSALESOUTLET_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "sALES_OUTLET");
    private final static QName _OPostPOSTAMOUNT_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "pOST_AMOUNT");
    private final static QName _OPostNUMOFARTICLE_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "nUMOF_ARTICLE");
    private final static QName _OPostPOSTINGTYPE_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "pOSTING_TYPE");
    private final static QName _OPostDURATION_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "dURATION");
    private final static QName _OPostTIME_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "tIME");
    private final static QName _OPostTAXPULSE_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "tAX_PULSE");
    private final static QName _OPostDATE_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "dATE");
    private final static QName _OPostMINIBARARTICLE_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "mINIBAR_ARTICLE");
    private final static QName _OPostROOMNUMBER_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "rOOM_NUMBER");
    private final static QName _OPostPOSTCALLTYPE_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "pOST_CALL_TYPE");
    private final static QName _OPostPOSTSEQNUMBER_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "pOST_SEQ_NUMBER");
    private final static QName _OPostCLEARTEXT_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "cLEAR_TEXT");
    private final static QName _OPostDIALEDDIGITS_QNAME = new QName("http://common.ehotel.eodapp.elcom.com/xsd", "dIALED_DIGITS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.elcom.eodapp.ehotel.common.xsd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OBill }
     * 
     */
    public OBill createOBill() {
        return new OBill();
    }

    /**
     * Create an instance of {@link OPost }
     * 
     */
    public OPost createOPost() {
        return new OPost();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "rOOM_NUMER", scope = OBill.class)
    public JAXBElement<String> createOBillROOMNUMER(String value) {
        return new JAXBElement<String>(_OBillROOMNUMER_QNAME, String.class, OBill.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "rESERVATION_NUMER", scope = OBill.class)
    public JAXBElement<String> createOBillRESERVATIONNUMER(String value) {
        return new JAXBElement<String>(_OBillRESERVATIONNUMER_QNAME, String.class, OBill.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "sALES_OUTLET", scope = OPost.class)
    public JAXBElement<String> createOPostSALESOUTLET(String value) {
        return new JAXBElement<String>(_OPostSALESOUTLET_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "pOST_AMOUNT", scope = OPost.class)
    public JAXBElement<String> createOPostPOSTAMOUNT(String value) {
        return new JAXBElement<String>(_OPostPOSTAMOUNT_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "nUMOF_ARTICLE", scope = OPost.class)
    public JAXBElement<String> createOPostNUMOFARTICLE(String value) {
        return new JAXBElement<String>(_OPostNUMOFARTICLE_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "pOSTING_TYPE", scope = OPost.class)
    public JAXBElement<String> createOPostPOSTINGTYPE(String value) {
        return new JAXBElement<String>(_OPostPOSTINGTYPE_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "dURATION", scope = OPost.class)
    public JAXBElement<String> createOPostDURATION(String value) {
        return new JAXBElement<String>(_OPostDURATION_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "tIME", scope = OPost.class)
    public JAXBElement<String> createOPostTIME(String value) {
        return new JAXBElement<String>(_OPostTIME_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "tAX_PULSE", scope = OPost.class)
    public JAXBElement<String> createOPostTAXPULSE(String value) {
        return new JAXBElement<String>(_OPostTAXPULSE_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "dATE", scope = OPost.class)
    public JAXBElement<String> createOPostDATE(String value) {
        return new JAXBElement<String>(_OPostDATE_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "mINIBAR_ARTICLE", scope = OPost.class)
    public JAXBElement<String> createOPostMINIBARARTICLE(String value) {
        return new JAXBElement<String>(_OPostMINIBARARTICLE_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "rOOM_NUMBER", scope = OPost.class)
    public JAXBElement<String> createOPostROOMNUMBER(String value) {
        return new JAXBElement<String>(_OPostROOMNUMBER_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "pOST_CALL_TYPE", scope = OPost.class)
    public JAXBElement<String> createOPostPOSTCALLTYPE(String value) {
        return new JAXBElement<String>(_OPostPOSTCALLTYPE_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "pOST_SEQ_NUMBER", scope = OPost.class)
    public JAXBElement<String> createOPostPOSTSEQNUMBER(String value) {
        return new JAXBElement<String>(_OPostPOSTSEQNUMBER_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "cLEAR_TEXT", scope = OPost.class)
    public JAXBElement<String> createOPostCLEARTEXT(String value) {
        return new JAXBElement<String>(_OPostCLEARTEXT_QNAME, String.class, OPost.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.ehotel.eodapp.elcom.com/xsd", name = "dIALED_DIGITS", scope = OPost.class)
    public JAXBElement<String> createOPostDIALEDDIGITS(String value) {
        return new JAXBElement<String>(_OPostDIALEDDIGITS_QNAME, String.class, OPost.class, value);
    }

}
