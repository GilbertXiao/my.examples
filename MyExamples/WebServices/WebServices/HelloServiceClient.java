
import wsdemo.*;

class HelloServiceClient
{
	public static void main(String[] args)  
	throws Exception
	{
		HelloWorld service = 
				new HelloWorld
("http://localhost:7001/WebServices/HelloWorld?WSDL");
		
		HelloWorldPort port = 
					 service.getHelloWorldPort(); 

		String result = port.sayHello(args[0]);
		System.out.println(result);
	}
}
