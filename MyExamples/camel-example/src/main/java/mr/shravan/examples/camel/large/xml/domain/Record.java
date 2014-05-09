package mr.shravan.examples.camel.large.xml.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
 
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "record", propOrder = { "key", "value" })
public class Record {
    @XmlAttribute(required = true)
    protected String key;
 
    @XmlAttribute(required = true)
    protected String value;
 
    public String getKey() {
        return key;
    }
 
    public void setKey(String key) {
        this.key = key;
    }
 
    public String getValue() {
        return value;
    }
 
    public void setValue(String value) {
        this.value = value;
    }
}