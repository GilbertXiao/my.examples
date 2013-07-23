import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClientRemove
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
	
		Employee ee = 
			h.findByPrimaryKey(new Integer(args[0]));

		ee.remove();

		System.out.println("Employee Removed");
	}
}