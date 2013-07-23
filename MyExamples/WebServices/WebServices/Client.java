import wsdemo.*;

public class Client 
{
  public static void main(String[] args) throws Exception
  {

String service_url = 
	"http://localhost:7001/WebServices/HelloWorld?WSDL";
		
		HelloWorld h = new HelloWorld(service_url);

		HelloWorldPort port = 
			h.getHelloWorldPort();

		String result = port.sayHello(args[0]);

		System.out.println(result);
  }
}
