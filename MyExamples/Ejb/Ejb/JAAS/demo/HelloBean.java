
package demo;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;

public class HelloBean implements SessionBean
{
	SessionContext sCtx;

	public void ejbActivate()  
	{
		System.out.println("HelloBean : ejbActivate");
	}
	public void ejbPassivate() 
	{
		System.out.println("HelloBean : ejbPassivate");	
	}
	public void ejbRemove()		 
	{
		System.out.println("HelloBean : ejbRemove");
	}

	public void setSessionContext(SessionContext ctx)
	{
		System.out.println("HelloBean : setSessionContext");
		sCtx = ctx;
	}

	public String sayHello(String s) 
	{
		java.security.Principal p = 
					sCtx.getCallerPrincipal();
			
		String userName = p.getName();

		return 
			"You logged in by name = " + userName;
	}


	public void ejbCreate() throws CreateException
	{
		System.out.println("HelloBean : ejbCreate");
	}	
}