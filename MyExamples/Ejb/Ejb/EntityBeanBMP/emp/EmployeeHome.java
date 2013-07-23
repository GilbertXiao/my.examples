package emp;

import java.rmi.*;
import javax.ejb.*;
import java.util.Collection ;

public interface EmployeeHome extends EJBHome
{
	public	Employee 
					findByPrimaryKey(Integer pk) 
					throws RemoteException,FinderException; 

	public	Employee 
					create(String name,float sal) 
					throws RemoteException,CreateException;

	public Collection 
					findBySalary(float amount)
					throws RemoteException,FinderException; 
}