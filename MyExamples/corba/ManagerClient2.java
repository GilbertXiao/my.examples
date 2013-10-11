import emp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class ManagerClient2
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

		NameComponent ncPath[] = {nc};

		org.omg.CORBA.Object obj = 
									rootCtx.resolve(ncPath);

		Manager m = ManagerHelper.narrow(obj);

		emp.Employee ee[] = m.getAll();
		
		System.out.println();
		for(int i=0; i<ee.length;i++)
		{
			System.out.println(ee[i].empno + "\t" + 
												 ee[i].ename + "\t" + 
												 ee[i].sal); 
		}	
		System.out.println();
	}
}