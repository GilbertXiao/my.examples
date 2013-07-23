package emp;

import java.rmi.*;
import javax.ejb.*;
import java.util.*;

public interface EmployeeHome extends EJBHome
{
	Employee findByPrimaryKey(Integer pk) 
		throws RemoteException,FinderException;

	Employee create(String name,float salary) 
		throws RemoteException,CreateException;

	Collection findBySalary(float sal)
		throws RemoteException,FinderException;
}