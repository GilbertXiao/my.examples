package pur;

import java.rmi.*;
import javax.ejb.*;

public interface PurchaseHome extends EJBHome
{
	public abstract Purchase create()
					throws CreateException,RemoteException;
}