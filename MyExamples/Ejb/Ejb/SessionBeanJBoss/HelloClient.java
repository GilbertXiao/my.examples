
import demo.*;
import java.util.*;
import java.rmi.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class HelloClient
{
	public static void main(String[] args) 
	throws Exception
	{

	Properties p = new Properties();

	p.put(Context.INITIAL_CONTEXT_FACTORY,
		"weblogic.jndi.WLInitialContextFactory");

	p.put(Context.PROVIDER_URL,
		"t3://localhost:7001");
		
	InitialContext ctx = 
			new InitialContext( p );
		
	Object obj = ctx.lookup("HelloBean");	
	Class intf = demo.HelloHome.class;

	Object ref = 
		PortableRemoteObject.narrow(obj,intf);
	
	HelloHome homeRef = (HelloHome)ref;

	Hello r = homeRef.create();
	String result = r.sayHello(args[0]);
	System.out.println(result);

	r.remove(); // bean not required any more

	}
}
