
package use;

import java.rmi.*;
import javax.ejb.*;

public interface RootHome extends EJBHome
{
	Root create()
		throws RemoteException,CreateException;
}