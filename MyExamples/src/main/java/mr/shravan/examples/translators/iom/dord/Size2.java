//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.28 at 05:49:19 PM EDT 
//


package mr.shravan.examples.translators.iom.dord;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Size2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Size2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlannedSize2" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                   &lt;totalDigits value="13"/>
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
 *         &lt;element name="ShippedSize2" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                   &lt;totalDigits value="13"/>
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
 *         &lt;element name="ReceivedSize2" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;union>
 *               &lt;simpleType>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                   &lt;totalDigits value="13"/>
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
 *         &lt;element name="Size2UOM">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="8"/>
 *             &lt;/restriction>
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
@XmlType(name = "Size2", propOrder = {
    "plannedSize2",
    "shippedSize2",
    "receivedSize2",
    "size2UOM"
})
public class Size2 {

    @XmlElementRef(name = "PlannedSize2", type = JAXBElement.class, required = false)
    protected JAXBElement<String> plannedSize2;
    @XmlElementRef(name = "ShippedSize2", type = JAXBElement.class, required = false)
    protected JAXBElement<String> shippedSize2;
    @XmlElementRef(name = "ReceivedSize2", type = JAXBElement.class, required = false)
    protected JAXBElement<String> receivedSize2;
    @XmlElement(name = "Size2UOM", required = true)
    protected String size2UOM;

    /**
     * Gets the value of the plannedSize2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPlannedSize2() {
        return plannedSize2;
    }

    /**
     * Sets the value of the plannedSize2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPlannedSize2(JAXBElement<String> value) {
        this.plannedSize2 = value;
    }

    /**
     * Gets the value of the shippedSize2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getShippedSize2() {
        return shippedSize2;
    }

    /**
     * Sets the value of the shippedSize2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setShippedSize2(JAXBElement<String> value) {
        this.shippedSize2 = value;
    }

    /**
     * Gets the value of the receivedSize2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReceivedSize2() {
        return receivedSize2;
    }

    /**
     * Sets the value of the receivedSize2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReceivedSize2(JAXBElement<String> value) {
        this.receivedSize2 = value;
    }

    /**
     * Gets the value of the size2UOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize2UOM() {
        return size2UOM;
    }

    /**
     * Sets the value of the size2UOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize2UOM(String value) {
        this.size2UOM = value;
    }

}
