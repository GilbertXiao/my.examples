
package inv;

//import java.rmi.*;
import javax.ejb.*;

public interface InventoryHome extends EJBLocalHome
{
	public abstract Inventory create() 
	throws CreateException;
}