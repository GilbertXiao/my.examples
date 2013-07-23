import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClient1
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

		String name = args[0];
		float sal = Float.parseFloat(args[1]);

		Employee myEmp = h.create(name,sal);

		System.out.println("Employee Created...");
		System.out.println("Name   : " + myEmp.getName());
		System.out.println("Salary : " + myEmp.getSalary());
		System.out.println("Primary: " + (Integer)myEmp.getPrimaryKey());		
	}
}
