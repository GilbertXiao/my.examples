
package emp;

import java.rmi.*;
import javax.ejb.*;
import javax.xml.rpc.*;

public interface EmployeeService extends EJBObject
{
	public void createEmployee(EmployeeVO emp) 
				throws RemoteException,JAXRPCException;

	public EmployeeVO getEmployee(int pk) 
				throws RemoteException,JAXRPCException;
}