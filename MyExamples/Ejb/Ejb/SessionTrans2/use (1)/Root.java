
package use;

import java.rmi.*;
import javax.ejb.*;
import javax.transaction.*;

public interface Root extends EJBObject
{
	void startPurchaseTrans(int id,int qty)
		throws RemoteException,TransactionRolledbackException;
}