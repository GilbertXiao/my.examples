import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClient4
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
		dept.remove();
		//dept.removeEmployee(new Integer(args[0]));
		System.out.println("Employee deleted");
    }
}