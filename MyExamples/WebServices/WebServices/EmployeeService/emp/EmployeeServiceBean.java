
package emp;

import java.rmi.*;
import javax.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import javax.xml.rpc.*;

public class EmployeeServiceBean implements SessionBean
{
	public void ejbActivate() {}
	public void ejbPassivate(){}
	public void ejbRemove()   {}

	public void setSessionContext(SessionContext sc)
	{}

	public void ejbCreate() throws CreateException
	{}

	public void createEmployee(EmployeeVO emp) 
	throws JAXRPCException
	{
		try
		{
		
		Context ctx = new InitialContext();

		Object obj = 
			ctx.lookup("java:comp/env/ejb/employee");

		EmployeeHome empHome = (EmployeeHome)
		PortableRemoteObject.narrow(obj,EmployeeHome.class);

		empHome.create
			(new Integer(emp.empno),emp.ename,emp.sal);

		}
		catch(Exception e)
		{
			throw new JAXRPCException(e.getMessage());
		}
	}

	public EmployeeVO getEmployee(int pk) 
	throws JAXRPCException
	{
		Employee myEmp = null;
		try
		{
		
		Context ctx = new InitialContext();
		Object obj = ctx.lookup("java:comp/env/ejb/employee");

		EmployeeHome empHome = (EmployeeHome)
   	PortableRemoteObject.narrow(obj,EmployeeHome.class);

		myEmp = 
			empHome.findByPrimaryKey(new Integer(pk));

		return myEmp.getValue();

		}
		catch(Exception e)
		{
			throw new JAXRPCException(e.getMessage());
		}		
	}
}