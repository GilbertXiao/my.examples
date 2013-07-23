package emp;

import java.rmi.*;
import javax.ejb.*;

public interface Employee extends EJBObject
{
	String getName() throws RemoteException;
	void   setName(String name) throws RemoteException;

	float getSalary() throws RemoteException;
	void  setSalary(float salary) throws RemoteException;

	EmployeeDO getEmployee() throws RemoteException;
}