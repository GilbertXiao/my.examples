import java.net.URL;
import javax.xml.rpc.*;
import javax.xml.rpc.namespace.*;

/*
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Service;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;

import javax.xml.rpc.namespace.QName;
*/

public class ClientWithWsdl
{
  public static void main(String[] args) throws Exception 
  {
    // Setup the global JAX-RPC service factory

System.setProperty
("javax.xml.rpc.ServiceFactory",
 "weblogic.webservice.core.rpc.ServiceFactoryImpl");

// create service factory
ServiceFactory factory = ServiceFactory.newInstance();

URL wsdlURL = new URL
("http://localhost:7001/WebServices/HelloWorld?WSDL");

QName serviceName	= new QName("HelloWorld");

	// create service
Service service = 
	factory.createService(wsdlURL, serviceName);
	
QName portName		= new QName("HelloWorldPort");
QName operationName = new QName("sayHello");

// create call
 Call call = 
	 service.createCall(portName,operationName);

//Array contains parameters for invoking the service
Object methArgs[] = {args[0]};

// invoke the remote web service
Object obj = call.invoke(methArgs);
String result = (String)obj;
System.out.println(result);

  } 
}

