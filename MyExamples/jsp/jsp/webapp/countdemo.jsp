<%
	Object obj = session.getAttribute("visits");
	Integer intObj = (Integer)obj;

	if(intObj == null)
		intObj = new Integer(0);

	int count = intObj.intValue();
	count++;

	session.setAttribute
		( "visits", new Integer(count) );
%>
<html>
	<body>
		<h1>

			You have visited 
			<%= session.getAttribute("visits")%> 
			times.
			<br>
			<a href="countdemo.jsp">Visit Again</a>
		</h1>
	</body>
</html>