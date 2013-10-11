
import emp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class ManagerClient
{
	public static void main(String args[])
	throws Exception
	{
	
ORB myORB = ORB.init(args,null);

org.omg.CORBA.Object ncObj = 
	myORB.resolve_initial_references("NameService");

		NamingContext rootCtx = 
				NamingContextHelper.narrow(ncObj);

		NameComponent nc = 
				new NameComponent("manager","");

		NameComponent ncPath[] = { nc };

		org.omg.CORBA.Object obj = 
									rootCtx.resolve(ncPath);

		Manager m = ManagerHelper.narrow(obj);

		try
		{
			emp.Employee ee = 
				m.getEmployee(Integer.parseInt(args[0]));
	
			System.out.println("Empno  : " + ee.empno); 
			System.out.println("Name   : " + ee.ename); 
			System.out.println("Salary : " + ee.sal); 
		}
		catch(emp.EmployeeNotFound e)
		{
			System.out.println(e.message);
			return;
		}

	}
}