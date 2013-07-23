
import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClientCreate
{
	public static void main(String args[]) throws Exception
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

	//Integer id  = new Integer(args[0]);
	String name = args[0];
	float  sal  = Float.parseFloat(args[1]);

	Employee myEmp = h.create( name, sal );
	
	System.out.println("Employee Created");
	System.out.println();

	EmployeeVO empValue = myEmp.getValue();
	System.out.println( empValue );
	
	}
}