import use.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import javax.transaction.*;

class Client2
{
	public static void main(String args[]) throws Exception
	{
		Properties p = new Properties();
	
		p.put(Context.INITIAL_CONTEXT_FACTORY ,
			"weblogic.jndi.WLInitialContextFactory");

		InitialContext ic = new InitialContext(p);

UserTransaction tx = (UserTransaction)
	ic.lookup("javax.transaction.UserTransaction");

	try
	{
		tx.begin();

		System.out.println("Client: Transaction Begin");

		Object o = ic.lookup("RootBean");

		Object obj =
			PortableRemoteObject.narrow(o,RootHome.class);

		RootHome h = (RootHome)obj;	
		Root r = h.create();

		int id = Integer.parseInt(args[0]);
		int qty = Integer.parseInt(args[1]);
		r.startPurchaseTrans(id,qty);

		tx.commit();

		System.out.println
			("Client: Transaction Commit");
	}
	catch(Exception e)
	{
		if( tx.getStatus() == Status.STATUS_MARKED_ROLLBACK	)
		{
			tx.rollback();

			System.out.println
				("Client: Transaction Rolled Back");
		}
	}


	}
}