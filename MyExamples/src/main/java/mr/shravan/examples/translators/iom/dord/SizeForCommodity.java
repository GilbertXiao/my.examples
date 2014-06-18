//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.28 at 05:49:19 PM EDT 
//


package mr.shravan.examples.translators.iom.dord;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SizeForCommodity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SizeForCommodity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SizeUOMCode">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SizeValue">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="16"/>
 *               &lt;fractionDigits value="4"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ShippedSizeValue" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                   &lt;totalDigits value="16"/>
 *                   &lt;fractionDigits value="4"/>
 *                 &lt;/restriction>
 *               &lt;/simpleType>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                   &lt;enumeration value=""/>
 *                 &lt;/restriction>
 *               &lt;/simpleType>
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ReceivedSizeValue" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                   &lt;totalDigits value="16"/>
 *                   &lt;fractionDigits value="4"/>
 *                 &lt;/restriction>
 *               &lt;/simpleType>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                   &lt;enumeration value=""/>
 *                 &lt;/restriction>
 *               &lt;/simpleType>
 *             &lt;/union>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SizeForCommodity", propOrder = {
    "sizeUOMCode",
    "sizeValue",
    "shippedSizeValue",
    "receivedSizeValue"
})
public class SizeForCommodity {

    @XmlElement(name = "SizeUOMCode", required = true)
    protected String sizeUOMCode;
    @XmlElement(name = "SizeValue", required = true)
    protected BigDecimal sizeValue;
    @XmlElementRef(name = "ShippedSizeValue", type = JAXBElement.class, required = false)
    protected JAXBElement<String> shippedSizeValue;
    @XmlElementRef(name = "ReceivedSizeValue", type = JAXBElement.class, required = false)
    protected JAXBElement<String> receivedSizeValue;

    /**
     * Gets the value of the sizeUOMCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSizeUOMCode() {
        return sizeUOMCode;
    }

    /**
     * Sets the value of the sizeUOMCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSizeUOMCode(String value) {
        this.sizeUOMCode = value;
    }

    /**
     * Gets the value of the sizeValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSizeValue() {
        return sizeValue;
    }

    /**
     * Sets the value of the sizeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSizeValue(BigDecimal value) {
        this.sizeValue = value;
    }

    /**
     * Gets the value of the shippedSizeValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getShippedSizeValue() {
        return shippedSizeValue;
    }

    /**
     * Sets the value of the shippedSizeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setShippedSizeValue(JAXBElement<String> value) {
        this.shippedSizeValue = value;
    }

    /**
     * Gets the value of the receivedSizeValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReceivedSizeValue() {
        return receivedSizeValue;
    }

    /**
     * Sets the value of the receivedSizeValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReceivedSizeValue(JAXBElement<String> value) {
        this.receivedSizeValue = value;
    }

}