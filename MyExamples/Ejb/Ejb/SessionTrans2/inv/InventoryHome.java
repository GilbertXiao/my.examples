
package inv;

import java.rmi.*;
import javax.ejb.*;

public interface InventoryHome 
extends EJBLocalHome
{
	Inventory create() throws CreateException;
}