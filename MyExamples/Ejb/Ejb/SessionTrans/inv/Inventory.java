
package inv;

//import java.rmi.*;
import javax.ejb.*;
//import javax.transaction.*;

public interface Inventory extends EJBLocalObject
{
	public abstract void updateStock(int id,int qty)
	throws TransactionRolledbackLocalException;
}