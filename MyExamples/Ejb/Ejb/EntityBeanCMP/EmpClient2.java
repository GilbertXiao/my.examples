import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClient2
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

	Integer pk = new Integer(args[0]);

	Employee ee = h.findByPrimaryKey(pk);

	System.out.println("Employee Located");
	System.out.println("Details are...");

	System.out.println(ee.getPrimaryKey());
	System.out.println(ee.getName());
	System.out.println(ee.getSalary());

	}
}