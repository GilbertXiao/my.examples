import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClient6
{
	public static void main(String args[]) throws Exception
	{
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
		InitialContext ic = new InitialContext(p);
		Object o = ic.lookup("DepartmentBean");

		Object obj = PortableRemoteObject.narrow(o,DepartmentHome.class);

		DepartmentHome h = (DepartmentHome)obj;

		Department dept = h.findByPrimaryKey(new Integer(119));

		EmpVal emp = dept.getEmployee(new Integer(19));

		System.out.println(emp.id+" "+emp.name+" "+emp.sal);

    }
}