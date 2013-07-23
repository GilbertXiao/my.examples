package emp;

import java.rmi.*;
import java.util.*;
import javax.ejb.*;

public interface EmployeeHome extends EJBHome
{
	Employee findByPrimaryKey(Integer pk) throws RemoteException,FinderException;
	Collection findByAllPrimaryKey() throws RemoteException,FinderException;

	Employee create(String name,float salary) throws RemoteException,CreateException;

	//Collection findByMinimumSalary(float sal) throws RemoteException,FinderException;
	//Collection findByName(String namePattern) throws RemoteException,FinderException;
}