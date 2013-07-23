<%@page contentType="text/html"%>
				
<html>
	<body>
		<h1>
			<%
				String s[] = {"C","C++","Java","C#"};
			%>
			<ul>
			<%
				for(int i=0;i<s.length;i++)
				{
			%>
					<li> <%= s[i]%> </li>
			<%		
				}
			%>
			</ul>
		</h1>
	</body>
<html>