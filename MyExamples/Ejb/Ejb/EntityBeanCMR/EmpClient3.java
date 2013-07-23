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

		Object o = ic.lookup("EmployeeBean");
		Class cls = emp.EmployeeHome.class;
		Object obj = PortableRemoteObject.narrow(o,cls);
		emp.EmployeeHome h = (emp.EmployeeHome)obj;

		Integer pk = new Integer(args[0]);
		Employee ee = h.findByPrimaryKey(pk);

		ee.remove();

		System.out.println("Employee Deleted");

    }
}