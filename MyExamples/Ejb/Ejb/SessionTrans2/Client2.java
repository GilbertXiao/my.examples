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
		Object o = ic.lookup("RootBean");

		Object obj =
			PortableRemoteObject.narrow(o,RootHome.class);

		RootHome h = (RootHome)obj;
		Root r = h.create();

		int id = Integer.parseInt(args[0]);
		int qty = Integer.parseInt(args[1]);

		UserTransaction tx = 
			(UserTransaction)ic.lookup
					("javax.transaction.UserTransaction");

		try
		{
			tx.begin();
			r.startPurchaseTrans(id,qty);
			System.out.println("Transaction Complete");
			tx.commit();
		}
		catch(Exception e)
		{
			System.out.println("Transaction Rolled Back");

			tx.rollback();
		}
	}
}