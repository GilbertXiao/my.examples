
package demo;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.CreateException;

public interface HelloHome extends EJBHome
{
	demo.Hello create() 
		throws RemoteException,CreateException;
}