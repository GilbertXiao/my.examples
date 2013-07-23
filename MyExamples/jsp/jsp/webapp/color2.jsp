<!--
	This page submits the form to colortest2.jsp page.

-->
<html>
	<body>
		<h1>
			<form action="colortest2.jsp" 
						method="post">
				<%
					String msg = 
						request.getParameter("myErrMsg");

					if(msg != null)
					{
				%>
						<font color='blue'>	
							<%= msg%><br>
						</font>	
				<%
					}
				%>

				select a color : <br><br>
				<input type="radio" name="mycolor" value="red">Red 
				<input type="radio" name="mycolor" value="green">Green 
				<input type="radio" name="mycolor" value="blue">Blue
				<br><br>
				<input type="submit" name="submit" value="Submit">
			</form>
		</h1>
	</body>
</html>