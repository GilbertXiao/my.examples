<html>
	<body>
		<h1>
			<form action="colortest.jsp" 
						method="post">
				<%
					Object obj=request.getAttribute("mymsg");
					if(obj != null)
					{
				%>
						<font color='red'>
							<%= (String)obj %><br>
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