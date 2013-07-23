import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClient3
{
	public static void main(String args[]) throws Exception
	{
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
		InitialContext ic = new InitialContext(p);
		Object o = ic.lookup("DepartmentBean");

		Object obj = PortableRemoteObject.narrow(o,DepartmentHome.class);

		DepartmentHome h = (DepartmentHome)obj;

		Department dept = h.findByPrimaryKey(new Integer(args[0]));

//		Collection allEmp = dept.getAllEmployees();
		Collection allEmp = dept.getAllEmployeesByName(args[1]);
		Iterator it  = allEmp.iterator();
		while(it.hasNext())
		{
			EmpVal emp=(EmpVal)it.next();
			System.out.println(emp.id+" "+emp.name+" "+emp.sal);
		}

    }
}