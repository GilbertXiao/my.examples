package demo;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;

public class HelloBean implements SessionBean
{
	private SessionContext ctx;

	public void ejbActivate() {}
	public void ejbPassivate(){}
	public void ejbRemove()	  {}

	public void setSessionContext(SessionContext sc)
	{
		ctx = sc;
	}

	public String sayHello(String name)
	{
		return "Hello From Websphere : " + name;
	}

	public void ejbCreate() throws CreateException
	{}
}