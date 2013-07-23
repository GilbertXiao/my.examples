import emp.*;

import java.io.*;
import java.rmi.*;
import java.util.*;

import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;

class EmpClient1
{
	public static void main(String args[]) throws Exception
	{
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");

		InitialContext ic = new InitialContext(p);

		Object o = ic.lookup("DepartmentBean");

		Object obj = PortableRemoteObject.narrow(o,DepartmentHome.class);

		DepartmentHome h = (DepartmentHome)obj;

        String name = args[0];
        String location = args[1];
       
		Department dept = h.create(name,location);

        System.out.println("Department Created");
	}
}