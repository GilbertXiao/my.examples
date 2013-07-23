
package statedemo;

import java.rmi.*;
import javax.ejb.*;

public interface CartHome extends EJBHome
{
	public Cart create(String name)
			throws RemoteException,CreateException;
}