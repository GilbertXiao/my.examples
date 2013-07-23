package emp;

import java.rmi.*;
import java.util.*;
import javax.ejb.*;

public interface DepartmentHome extends EJBHome
{
    Department create(String deptname,String location) throws RemoteException,CreateException;
    Department findByPrimaryKey(Integer pk) throws RemoteException,FinderException;
}