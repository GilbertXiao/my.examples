package com.example.services.helloworld;

import javax.xml.ws.Holder;


@javax.jws.WebService (endpointInterface="com.example.services.helloworld.HelloWorldSEI", targetNamespace="http://www.example.com/services/HelloWorld", serviceName="HelloService", portName="HelloPort", wsdlLocation="WEB-INF/wsdl/hello.wsdl")
public class HelloPortImpl{

    public void hello(Holder<String> message) {
    	message.value = "Hello " + message.value;
        
    }

}