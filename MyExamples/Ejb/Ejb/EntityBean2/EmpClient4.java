import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClient4
{
	public static void main(String[] args) 
	throws Exception
	{
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");

		Context ctx = new InitialContext(p);
		Object o = ctx.lookup("EmployeeBean");
		Class cls = EmployeeHome.class;

		Object obj = 
			PortableRemoteObject.narrow(o,cls);

		EmployeeHome h = (EmployeeHome)obj;

		Integer pk = new Integer(args[0]);

		//h.remove(pk);

		Employee myEmp = h.findByPrimaryKey(pk);
		System.out.println("Employee Located...");
		//Thread.sleep(5000);
		myEmp.remove();
		System.out.println("Employee Removed...");
	}
}