package demo;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface Hello extends EJBObject
{
	String sayHello(String name) 
				 throws RemoteException;
}