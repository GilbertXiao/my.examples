import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClientRemove2
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

	Integer intObj = new Integer(args[0]);

	//h.remove(intObj);

	Employee ee = h.findByPrimaryKey(intObj);
	ee.remove();

	System.out.println("Employee Removed");

	}
}