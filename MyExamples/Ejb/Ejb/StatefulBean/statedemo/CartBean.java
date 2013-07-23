
package statedemo;

import java.rmi.*;
import java.util.*;
import javax.ejb.*;

public class CartBean implements SessionBean
{
	String name;
	Vector titles;
	SessionContext ctx;
	
	public void ejbActivate ()
	{
		System.out.println("CartBean : ejbActivate " + name);
	}

	public void ejbPassivate()
	{
		System.out.println("CartBean : ejbPassivate " + name);
	}
	public void ejbRemove()
	{
		System.out.println("CartBean : ejbRemove " + name);
	}

	public void setSessionContext
	(SessionContext ctx)
	{
		System.out.println("CartBean : setSessionContext");
		this.ctx = ctx; 
	}

	public void ejbCreate(String name) throws CreateException
	{

		if( name == null || name.equals("") )
			throw new CreateException("Invalid Name");

		this.name = name;
		titles = new Vector();
		System.out.println("CartBean : ejbCreate " + name);
	}
	
	public String getName() 
	{
		System.out.println("CartBean : getName " + name);

		return name;
	}

public void addTitle(String title)
throws InvalidTitleException
{
	System.out.println
		("CartBean : addTitle " + name);

	if(title == null || title.equals(""))
		throw new InvalidTitleException("Invalid Title");

	titles.addElement(title);
}






	public void removeTitle(String title)
	throws TitleNotFoundException
	{
		System.out.println("CartBean : removeTitle " + name);

		boolean removed = titles.remove(title);
		
		if( !removed )
			throw new TitleNotFoundException
			(title + " DOES NOT EXIST");
	}


	public Collection getContents()
	{
		System.out.println("CartBean : getContents " + name);
		return titles;
	}
}