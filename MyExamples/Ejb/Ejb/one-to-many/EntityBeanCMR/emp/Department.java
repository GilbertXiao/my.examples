package emp;

import java.rmi.*;
import javax.ejb.*;
import java.util.*;

public interface Department extends EJBObject
{
    public void addEmployee(EmpVal eval) throws RemoteException;
    public void addEmployee(String name,float sal)throws RemoteException;
    public void removeEmployee(Integer id) throws RemoteException;

    public EmpVal getEmployee(Integer id) throws RemoteException;
    public void   setEmployeeSalary(Integer id,float newSalary) throws RemoteException;
    public Collection getAllEmployees() throws RemoteException;
    public Collection getAllEmployeesByName(String name) throws RemoteException;
}