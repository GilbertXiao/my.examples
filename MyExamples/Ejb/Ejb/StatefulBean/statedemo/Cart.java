package statedemo;

import java.rmi.*;
import java.util.*;
import javax.ejb.*;

public interface Cart extends EJBObject
{
	String getName() throws RemoteException;

	void addTitle(String title)
	throws RemoteException,InvalidTitleException;

	void removeTitle(String title)
	throws RemoteException,TitleNotFoundException;

	Collection getContents() throws RemoteException;
}