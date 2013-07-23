import emp.*;

import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public class EmpClientBySalary
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

	Collection emps = 
		h.findBySalary(Float.parseFloat(args[0]));

	Iterator it = emps.iterator();

	while(it.hasNext())
	{
		Employee myEmp = (Employee) it.next();
		
		EmployeeVO myEmpVO = myEmp.getValue();

		System.out.println(myEmpVO.empno + "\t" + 
											 myEmpVO.ename+ "\t" + 
										   myEmpVO.sal);
	}





	}
}