package emp;

import java.rmi.*;
import javax.ejb.*;

public interface Employee extends EJBObject
{
    public String getName() throws RemoteException;
    public void  setName(String s) throws RemoteException;

    public float getSalary() throws RemoteException;
    public void setSalary(float f) throws RemoteException;

    public AddressDAO getAddress() throws RemoteException;

    public void setAddress(AddressDAO ad) throws RemoteException;

    public void setAddress(String house_no,String street,String city,String country) throws RemoteException;
}