package emp;

import java.rmi.RemoteException;
import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import javax.ejb.FinderException;

public interface EmployeeHome extends EJBHome
{
	Employee findByPrimaryKey(Integer pk) 
		throws RemoteException,FinderException;

	Employee create(String name,float sal)
		throws RemoteException,CreateException;

	java.util.Collection findBySalary(float sal)
		throws RemoteException,FinderException;
}