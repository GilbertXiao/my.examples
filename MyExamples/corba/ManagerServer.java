
import emp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class ManagerServer 
{
	public static void main(String args[])
	throws Exception
	{
		Class.forName
			("sun.jdbc.odbc.JdbcOdbcDriver");

		ORB myORB = ORB.init(args,null);

		java.lang.Object obj = new ManagerServant
						("jdbc:odbc:javadsn","sa","dss");

		myORB.connect(obj); //register with ORB

org.omg.CORBA.Object ncObj = 
	myORB.resolve_initial_references("NameService");

		NamingContext rootCtx = 
				NamingContextHelper.narrow(ncObj);

		NameComponent nc = 
				new NameComponent("manager","");

		NameComponent ncPath[] = {nc};

		rootCtx.rebind (ncPath, obj);

		System.out.println("Object Binded");

		Thread.currentThread().join();

	}
}