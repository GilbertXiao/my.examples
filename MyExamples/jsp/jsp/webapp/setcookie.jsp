
<%
	String mynameValue = 
		request.getParameter("myname");

	if( mynameValue == null || mynameValue.equals(""))
	{
		request.setAttribute("msg","You Did Not Enter Your name!");
%>
		<jsp:forward page="name.jsp"/>
<%
	}

	Cookie c=new Cookie("myCookie",mynameValue);
	response.addCookie( c );

	Object o = session.getAttribute("myurl");
	if( o != null )
	{
		session.removeAttribute("myurl");
		response.sendRedirect( o.toString() );
	}

%>

	<html>
		<body>
			<h1>
				I know your name.
				<a href='cookiedemo.jsp'>Show me</a>
			</h1>
		</body>
	</html>