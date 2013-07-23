<%@ page import = "statedemo.*,javax.ejb.*,javax.naming.*,javax.rmi.*" %>

<html>
	<body>
	<h2>

	<%
		boolean z = session.isNew();

		if(z)
		{
			String s = 	request.getParameter("username");
			
			Context ctx = new InitialContext();

System.out.println("Logical Name Referred");
Object o = ctx.lookup("java:comp/env/ejb/cart");

		Object obj = PortableRemoteObject.narrow								 (o,CartHome.class);

			CartHome h = (CartHome)obj;
			Cart c = h.create(s);
			Handle cartHandle = c.getHandle();
			session.setAttribute("cart",cartHandle);
		}

		else
		{
			Object myobj = 
				session.getAttribute("cart");

			Handle handle = (Handle)myobj;
			
			EJBObject myEJBObject = 
								handle.getEJBObject();
			
			Cart mycart =	(Cart)myEJBObject;

			String itemName =
				request.getParameter("item");

			String str =
				request.getParameter("submit");

			if(str != null)
			{
				try
				{
					if(str.equalsIgnoreCase("add"))
						mycart.addTitle(itemName);

					else if(str.equalsIgnoreCase("remove"))
						mycart.removeTitle(itemName);
				}
				catch(Exception e)
				{}
			}

			out.println
			 ("Cart of " + mycart.getName() + "<br><br>");

			 java.util.Collection v =
				 	mycart.getContents();

			 java.util.Iterator it = v.iterator();

			 while(it.hasNext())
			 {
				 out.println
				  (it.next().toString() + "<BR>");
			 }
		}
	%>

	<form action = "cart.jsp" method = "post">
		Item :
		<input type = text name = "item" value = "">
		<br><br>

		<input type = submit
			   name = "submit" value = "Add">

		<input type = submit
			   name = "submit" value = "Remove">

		<input type = submit
			   name = "submit" value = "Show">

	</form>

	</h2>
	</body>
</html>