import use.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import javax.transaction.*;

class Client
{
	public static void main(String args[])
	throws Exception
	{
		Properties p = new Properties();
	
		p.put(Context.INITIAL_CONTEXT_FACTORY ,
			"weblogic.jndi.WLInitialContextFactory");

		InitialContext ic = new InitialContext(p);
		Object o = ic.lookup("RootBean");

		Object obj =
			PortableRemoteObject.narrow(o,RootHome.class);

		RootHome h = (RootHome)obj;
		Root r = h.create();

		int id = Integer.parseInt(args[0]);
		int qty = Integer.parseInt(args[1]);

		r.startPurchaseTrans(id,qty);
		System.out.println("Transaction Complete");
	}
}