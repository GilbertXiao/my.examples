package org.fusesource.camel;

public class OrderHelper {
    public boolean isCsv(String body) {
        return !body.contains("<?xml");
    }
    
    public boolean isXml(String body) {
        return body.contains("<?xml");
    }
}
