
package emp;

import java.rmi.*;
import javax.ejb.*;

public interface EmployeeServiceHome 
extends EJBHome
{
	public EmployeeService create() 
					throws RemoteException,CreateException;
}
