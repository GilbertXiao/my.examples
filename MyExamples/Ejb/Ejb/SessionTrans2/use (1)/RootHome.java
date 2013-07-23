package use;

import java.rmi.*;
import javax.ejb.*;

public interface RootHome extends EJBHome
{
	public abstract Root create()
		throws RemoteException,CreateException;
}