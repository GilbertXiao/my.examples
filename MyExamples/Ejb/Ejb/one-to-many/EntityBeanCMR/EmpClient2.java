import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClient2
{
	public static void main(String args[]) throws Exception
	{
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
		InitialContext ic = new InitialContext(p);
		Object o = ic.lookup("DepartmentBean");

		Object obj = PortableRemoteObject.narrow(o,DepartmentHome.class);

		DepartmentHome h = (DepartmentHome)obj;
		int id = Integer.parseInt(args[0]);
		Department dept = h.findByPrimaryKey(new Integer(id));

		String name = args[1];
		float sal = Float.parseFloat(args[2]);

		dept.addEmployee(name,sal);
        System.out.println("Employee Created");
    }
}