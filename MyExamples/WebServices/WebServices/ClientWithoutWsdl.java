import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Service;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;

import javax.xml.rpc.namespace.QName;

public class ClientWithoutWsdl
{
	public static void main(String[] args) throws Exception 
	{
    // Setup the global JAXM message factory
    //System.setProperty("javax.xml.soap.MessageFactory",
    //"weblogic.webservice.core.soap.MessageFactoryImpl");

    // Setup the global JAX-RPC service factory
    System.setProperty( "javax.xml.rpc.ServiceFactory",
      "weblogic.webservice.core.rpc.ServiceFactoryImpl");

    // create service factory
    ServiceFactory factory = ServiceFactory.newInstance();

	QName serviceName	= new QName("HelloWorld");
    QName portName		= new QName("HelloWorldPort");
    QName operationName = new QName("sayHello");

    // create service
    Service service = factory.createService(serviceName);

    // create call
    Call call = service.createCall();

    // set port and operation name
    call.setPortTypeName(portName);
    call.setOperationName(operationName);

    // add parameters
    call.addParameter("string", 
                      new QName("http://www.w3.org/2001/XMLSchema", "string"), 
                      ParameterMode.IN);

    call.addParameter("result", 
                      new QName("http://www.w3.org/2001/XMLSchema", "string"), 
                      ParameterMode.OUT);

    // set end point address
    call.setTargetEndpointAddress("http://localhost:7001/WebServices/HelloWorld");

    // invoke the remote web service
    String result = (String) call.invoke(new Object[] {args[0]});
    System.out.println(result);
  } 
}