import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClient1
{
	public static void main(String args[]) throws Exception
	{
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");

		InitialContext ic = new InitialContext(p);

		Object o = ic.lookup("EmployeeBean");

		Class cls = EmployeeHome.class;

		Object obj =
			PortableRemoteObject.narrow(o,cls);

		EmployeeHome h = (EmployeeHome)obj;

		String name = "Abcd";
		float sal   = 15600;

    AddressDAO ad = new AddressDAO
         	("1-2-3","M.G. Road","Hyd","India");

		Employee ee = h.create(name,sal,ad);

    System.out.println("Employee Created");
	}
}