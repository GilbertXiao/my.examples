import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClientFind3
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

	float amount = Float.parseFloat(args[0]);

	Collection emps = h.findBySalary(amount);

	Iterator it = emps.iterator();

	while(it.hasNext())
		{
			Employee ee = (Employee)it.next();
			EmployeeVO value = ee.getValue();

			System.out.println
				(value.empno + "\t" + 
				 value.ename + "\t" +
				 value.sal);
		}
	
	}
}