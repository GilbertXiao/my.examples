import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClient5
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

		Collection c = 
			h.findBySalary(Float.parseFloat(args[0]));
		
		Iterator it = c.iterator();

		while(it.hasNext())
		{
			Employee myemp = (Employee)it.next();
			System.out.println(myemp.getName());
		}
	}
}