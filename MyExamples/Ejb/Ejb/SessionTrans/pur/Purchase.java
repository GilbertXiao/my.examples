
package pur;

//import java.rmi.*;
import javax.ejb.*;
//import javax.transaction.*;

public interface Purchase extends EJBLocalObject
{
	void purchaseItem(int id,int qty)
	throws TransactionRolledbackLocalException;
}