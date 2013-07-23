package emp;

import java.rmi.*;
import java.util.*;
import javax.ejb.*;

public interface EmployeeHome extends EJBHome
{
	public Employee 
				 findByPrimaryKey(Integer pk) 
				 throws RemoteException,FinderException; 

	public Employee 
				 create(String name,float sal) 
				 throws RemoteException,CreateException; 

	public Collection 
				 findBySalary(float amount)
				 throws RemoteException,FinderException; 

	public Collection 
				 findByName(String pattern)
				 throws RemoteException,FinderException; 
}