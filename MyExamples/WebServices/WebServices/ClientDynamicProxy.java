import java.net.URL;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.namespace.QName;

public class ClientDynamicProxy
{
  public static void main(String[] args) throws Exception
  {

System.setProperty
("javax.xml.rpc.ServiceFactory",
 "weblogic.webservice.core.rpc.ServiceFactoryImpl");

// create service factory
ServiceFactory factory = 
			ServiceFactory.newInstance();

URL wsdlURL = new URL
("http://localhost:7001/WebServices/HelloWorld?WSDL");

QName serviceName = new QName("HelloWorld");

// create service
Service service = 
	factory.createService(wsdlURL , serviceName);

QName portName = new QName("HelloWorldPort");
Class cls = IHello.class;

IHello h = (IHello)
 					 service.getPort(portName,cls);

String result = h.sayHello(args[0]);
System.out.println(result);

	}
}
