package com.example.helloworld;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.BindingProvider;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.ws.WebServiceException;
import javax.xml.soap.MessageFactory;
import java.util.Map;
import java.util.Iterator;


public class HelloClient {

	public static void main(String[] args) {

		try {
//			 QNames for service as defined in wsdl.			
		QName serviceName =
		  new QName("http://www.example.com/services/HelloWorld", "HelloService");

//		QName for Port As defined in wsdl.
		QName portName =
		  new QName("http://www.example.com/services/HelloWorld", "HelloPort");

//		Endpoint Address
		String  endpointAddress =
		  "http://localhost:9080/hello/HelloService";

//		 Create a dynamic Service instance
		Service service = Service.create(serviceName);

//		 Add a port to the Service
		service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

//		Create a dispatch instance
		Dispatch<SOAPMessage> dispatch = 
		   service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);

//		 Use Dispatch as BindingProvider
		BindingProvider bp = (BindingProvider) dispatch;

//		 Optionally Configure RequestContext to send SOAPAction HTTP Header
		Map<String, Object> rc = bp.getRequestContext();
		rc.put(BindingProvider.SOAPACTION_USE_PROPERTY, Boolean.TRUE);
		rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, "hello");

//		 Obtain a preconfigured SAAJ MessageFactory
		MessageFactory factory =
		   ((SOAPBinding) bp.getBinding()).getMessageFactory();

//		 Create SOAPMessage Request
		SOAPMessage request = factory.createMessage();

//		 Request Header
		SOAPHeader header = request.getSOAPHeader();

//		 Request Body
		SOAPBody body = request.getSOAPBody();

//		 Compose the soap:Body payload
		QName payloadName =
		   new QName("http://www.example.com/schemas/HelloWorld", "hello", "ns1");

		SOAPBodyElement payload = body.addBodyElement(payloadName); 

		SOAPElement message = payload.addChildElement("message");

		message.addTextNode("World!");

//		 Invoke the endpoint synchronously
		SOAPMessage reply = null;

		try {
			//Invoke Endpoint Operation and read response
			reply = dispatch.invoke(request);
		} catch (WebServiceException wse){
			wse.printStackTrace();
		}

//		 process the reply
		body = reply.getSOAPBody();

		QName responseName =
		   new QName("http://www.example.com/schemas/HelloWorld", "helloResponse");

		SOAPBodyElement bodyElement = (SOAPBodyElement)body.getChildElements(responseName).next();
		
		QName elementName =
			new QName("", "message");
		SOAPElement soapElement = (SOAPElement)bodyElement.getChildElements(elementName).next();
		
		String rspMessage = soapElement.getValue();
		System.out.println("Response: "+rspMessage);
		
		} catch (Exception e) {
			System.out.println("Caught exception: "+e.toString());
			e.printStackTrace();
		}


	}

}
