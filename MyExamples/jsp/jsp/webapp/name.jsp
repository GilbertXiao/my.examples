<html>
	<body>
		<h1>
			<%
				Object msgValue = 
					request.getAttribute("msg");

				if(msgValue != null)
				{
			%>	
					<font color='red'>
						<%= msgValue%>
					</font>
		<%
				}			
			%>
			<form action='setcookie.jsp' method='post'>
				Enter your name: 
				<input type="text" 
							 name="myname" value="">
				<input type="submit" 
							 name="submit" value="Proceed">
			</form>
		</h1>
	</body>
</html>
