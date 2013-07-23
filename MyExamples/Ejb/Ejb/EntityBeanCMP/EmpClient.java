import emp.*;

import java.rmi.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.rmi.*;

public class EmpClient
{
	public static void main(String args[])
	throws Exception
	{

	Properties p = new Properties();

	p.put(Context.INITIAL_CONTEXT_FACTORY,
		"weblogic.jndi.WLInitialContextFactory");

	p.put(Context.PROVIDER_URL,"t3://localhost:7001");

	Context ctx = new InitialContext(p);

	Object obj = ctx.lookup("emp.EmployeeHome");

	Object o = PortableRemoteObject.narrow
						(obj,EmployeeHome.class);

	EmployeeHome h = (EmployeeHome)o;

	Integer intObj = new Integer(101);
	String str = new String("Abc");
	float sal = 12500.0f;

	Employee ee = h.create(intObj,str,sal);

	System.out.println("Employee Created");
	System.out.println("Details are...");

	System.out.println(ee.getPrimaryKey());
	System.out.println(ee.getName());
	System.out.println(ee.getSalary());


	}
}