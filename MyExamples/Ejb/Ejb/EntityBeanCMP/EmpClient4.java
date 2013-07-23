import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClient4
{
	public static void main(String args[])
	throws Exception
	{

	Properties p = new Properties();

	p.put(Context.INITIAL_CONTEXT_FACTORY,
		"weblogic.jndi.WLInitialContextFactory");

	p.put(Context.PROVIDER_URL,"t3://localhost:7001");

	Context ctx = new InitialContext(p);

	Object obj = ctx.lookup("EmployeeBean");

	Object o = PortableRemoteObject.narrow
						(obj,EmployeeHome.class);

	EmployeeHome h = (EmployeeHome)o;

	float sal = Float.parseFloat(args[0]);
	Collection c = h.findBySalary(sal);

	Iterator it = c.iterator();

	while(it.hasNext())
	{
		Employee ee = (Employee)it.next();
		System.out.println(ee.getName());
	}

	}
}