package emp;

import java.rmi.*;
import javax.ejb.*;
import java.util.*;

public interface EmployeeHome extends EJBHome
{
    Employee create
			(String name,float salary,AddressDAO ad) throws RemoteException,CreateException;

    Employee findByPrimaryKey(Integer pk) throws RemoteException,FinderException;
}