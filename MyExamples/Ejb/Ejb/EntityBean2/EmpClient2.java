import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClient2
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
		Employee myEmp = h.findByPrimaryKey(pk);

		System.out.println("Employee Located...");
		System.out.println("Name   : " + myEmp.getName());
		System.out.println("Salary : " + myEmp.getSalary());
		System.out.println("Primary: " + (Integer)myEmp.getPrimaryKey());		
	}
}
