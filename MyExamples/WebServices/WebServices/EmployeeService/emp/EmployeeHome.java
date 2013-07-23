
package emp;

import java.rmi.*;
import java.util.*;

import javax.ejb.*;

public interface EmployeeHome extends EJBHome
{
	Employee findByPrimaryKey(Integer pk) 
		throws RemoteException,FinderException;

	Employee create(Integer id,String name,float salary) 
		throws RemoteException,CreateException;
}