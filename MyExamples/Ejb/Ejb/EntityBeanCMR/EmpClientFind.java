import emp.*;
import java.io.*;
import java.rmi.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClientFind
{
	public static void main(String args[]) throws Exception
	{
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");

		InitialContext ic = new InitialContext(p);

		Object o = ic.lookup("EmployeeBean");

		Class cls = EmployeeHome.class;
		Object obj = PortableRemoteObject.narrow(o,cls);

		EmployeeHome h = (EmployeeHome)obj;
	
		Employee ee = 
			h.findByPrimaryKey(new Integer(args[0]));

		Integer pk = (Integer)ee.getPrimaryKey();
		String ename = ee.getName();
		float sal = ee.getSalary();
		AddressDAO ad = ee.getAddress();

		System.out.println
			(pk + "\t" + ename + "\t" + sal);

		System.out.println
			( ad.house_no + "\t" + 
				ad.street + "\t" + 
				ad.city + "\t" + ad.country);
	}
}