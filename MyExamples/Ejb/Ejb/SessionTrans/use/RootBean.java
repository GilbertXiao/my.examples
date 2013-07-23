package use;

import pur.*;
import inv.*;

import java.rmi.*;
import java.sql.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import javax.transaction.*;

public class RootBean implements SessionBean
{
	SessionContext sc;

	public void ejbActivate() {}
	public void ejbPassivate(){}
	public void ejbRemove()   {}

	public void setSessionContext(SessionContext sc)
	{
		this.sc = sc;
	}

	public void ejbCreate()
	{}

	public void startPurchaseTrans(int id,int qty)
	throws TransactionRolledbackException
	{
		InitialContext ic = null;

		try
		{
			ic = new InitialContext();

			Object po = 
				ic.lookup("java:comp/env/ejb/purchase");
			
			//Object obj = PortableRemoteObject.narrow(po,PurchaseHome.class);

			PurchaseHome ph = (PurchaseHome)po;
			Purchase pur		= ph.create();
			pur.purchaseItem(id,qty);


			Object io = 
				ic.lookup("java:comp/env/ejb/inventory");

			//obj = PortableRemoteObject.narrow(io,InventoryHome.class);

			InventoryHome ih = (InventoryHome)io;
			Inventory inv		 = ih.create();
			inv.updateStock(id,qty);

			System.out.println
				("RootBean: Transaction Complete");
		}
		catch(Exception e)
		{
			System.out.println("Transaction Rolledback - RootBean : startPurchaseTrans");
			sc.setRollbackOnly();
			throw new TransactionRolledbackException
								(e.getMessage());
		}
		finally
		{
			if(ic != null)
			{
				try{
					ic.close();
				}
				catch(Exception e)
				{
					throw new EJBException();
				}
			}
		}
	}
}
	