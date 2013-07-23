package pur;

import java.rmi.*;
import javax.ejb.*;
import javax.transaction.*;

public interface Purchase extends EJBObject
{
	abstract void purchaseItem(int id,int qty)
			throws RemoteException,TransactionRolledbackException;
}