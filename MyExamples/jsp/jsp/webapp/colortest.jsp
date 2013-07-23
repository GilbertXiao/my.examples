<!-- 
	If there is no parameter by name "mycolor" in request object
	then this page forwards the request (<jsp:forward page="color.jsp"/>)
	to color.jsp page with appropriate message attribute
-->
<%
	String mycolorValue = 
		request.getParameter("mycolor");

	if(mycolorValue == null)
	{
		request.setAttribute("mymsg","You did not select a color!");
%>
		<jsp:forward page="color.jsp"/>
<%
	}
%>

<html>
	<body text='<%= mycolorValue%>'>
		<h1>
			This is the response from colortest.jsp
		</h1>
	</body>
</html>