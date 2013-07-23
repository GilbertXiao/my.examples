<%
	Cookie c[] = request.getCookies();

	if( c==null || c.length<1 )
	{
		session.setAttribute
				("myurl",request.getRequestURI());

		request.setAttribute("msg","No Cookie arrived with your Request");
%>
		<jsp:forward page="name.jsp"/>
<%
	}
	
	String str = null;
	for(int i=0; i<c.length; i++)
	{
		if(c[i].getName().equals("myCookie"))
		{
			str = c[i].getValue();
			break;
		}
	}

	if(str == null)
	{
		session.setAttribute
			("myurl",request.getRequestURI());

		request.setAttribute("msg","Required Cookie NOT Found");
%>
		<jsp:forward page="name.jsp"/>
<%
	}
%>

<html>
	<body>
		<h1>
			Welcome <%= str%>
		</h1>
	</body>
</html>