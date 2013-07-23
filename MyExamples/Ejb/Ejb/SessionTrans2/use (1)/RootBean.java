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
		try
		{
			InitialContext ic = new InitialContext();

			Object po = 
			ic.lookup("java:comp/env/ejb/purchase");

			PurchaseHome ph = (PurchaseHome)po;
			Purchase pur		= ph.create();
			pur.purchaseItem(id,qty);

			Object io = 
			ic.lookup("java:comp/env/ejb/inventory");

			InventoryHome ih	= (InventoryHome)io;
			Inventory inv			= ih.create();
			inv.updateStock(id,qty);

			System.out.println
				("RootBean: Transaction Completed");
		}
		catch(Exception e)
		{
			sc.setRollbackOnly();
			System.out.println("Transaction Rolledback - RootBean : startPurchaseTrans");
			throw new 
				TransactionRolledbackException(e.getMessage());
		}
	}
}