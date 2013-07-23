
import statedemo.*;

import java.rmi.*;
import java.util.*;

import javax.ejb.*;
import javax.rmi.*;
import javax.naming.*;

public class CartClient
{
	public static void main(String args[])
	throws Exception
	{

	Properties p = new Properties();

	p.put(Context.INITIAL_CONTEXT_FACTORY,
	 "weblogic.jndi.WLInitialContextFactory");

//	p.put(Context.PROVIDER_URL,"localhost");

	Context ctx = new InitialContext(p);

	Object obj = ctx.lookup("CartBean");

	Object o = PortableRemoteObject.narrow
											(obj,CartHome.class);

	CartHome h = (CartHome)o;

	Cart cart = null;

		try
		{
			cart = h.create(args[0]);
		}
		catch(CreateException e)
		{
			System.err.println
				("CartBean Could NOT be created");

			return;
		}

		cart.addTitle("Mastering EJB");
		cart.addTitle("Complate Reference");
		cart.addTitle("Servlet Programming");

		Collection v = cart.getContents();

		System.out.println
			("Cart Contents for " + cart.getName());

		showContents(v);

		int x = System.in.read();
		while(x != '\n')
			x = System.in.read();

		try
		{
			cart.removeTitle("Mastering Java");
		}
		catch(TitleNotFoundException e)
		{
			System.err.println(e.getMessage());
		}

		v = cart.getContents();
		showContents(v);
		cart.remove();
	}

	public static void showContents(Collection v)
	{
		Iterator it = v.iterator();
				
		System.out.println("----------------------");

		while(it.hasNext())
		{
			System.out.println((String)it.next());
		}

		System.out.println("----------------------");
	}
}