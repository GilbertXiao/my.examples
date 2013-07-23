package examples.security.jaas;

import java.security.PrivilegedAction;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import java.rmi.RemoteException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * SampleAction.java
 *
 * JAAS sample PrivilegedAction Implementation
 *
 * @author Copyright (c) 2000-2002 by BEA Systems, Inc. All Rights Reserved.
 */
public class SampleAction 
implements PrivilegedAction
{
  private static final String JNDI_NAME = "HelloBean";
  private String url;
  
  public SampleAction(String url)
  {
    this.url = url;
  }
  
  public Object run()
  {
    Object obj = null;

    try {
      callHelloEJB();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * Call Trader EJB.
   */
  public void callHelloEJB() throws NamingException, CreateException, RemoteException, RemoveException
  {
		java.util.Properties p = new java.util.Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
			"weblogic.jndi.WLInitialContextFactory");

		if(url == null)
			url = "t3://localhost:7001";

		p.put(Context.PROVIDER_URL,url);

		InitialContext ctx = new InitialContext(p);

		Object o = ctx.lookup(JNDI_NAME);
	
		Class cls = demo.HelloHome.class;
	
		Object ref = 
			javax.rmi.PortableRemoteObject.narrow(o,cls);

		demo.HelloHome h = (demo.HelloHome)ref;
		demo.Hello r = h.create();
		String result = r.sayHello("abcd");
		System.out.println(result);
		r.remove(); // no longer need the EJB


  }
}

