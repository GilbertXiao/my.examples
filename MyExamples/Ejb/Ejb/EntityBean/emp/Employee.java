
package emp;

import java.rmi.*;
import javax.ejb.*;

public interface Employee extends EJBObject
{
	public String getName() throws RemoteException;
	public void		setName(String name) throws RemoteException;

	public float getSalary() throws RemoteException;
	public void  setSalary(float salary) throws RemoteException;

	public EmployeeVO getValue() throws RemoteException;
}