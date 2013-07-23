
import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClient
{
	public static void main(String args[]) throws Exception
	{
    Properties p = new Properties();
    p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");

		InitialContext ic = new InitialContext(p);

		Object o = ic.lookup("EmployeeBean");

		Class cls = EmployeeHome.class;

Object obj = PortableRemoteObject.narrow(o,cls);

		EmployeeHome h = (EmployeeHome)obj;
	
		String name = "Abc";
		float sal   = 15700;

	AddressDAO ad = new AddressDAO
	("4-5-6","M.G. Road","Secunderabad","India");

	Employee ee = h.create(name,sal,ad);


		System.out.println("Employee Created");
	}
}