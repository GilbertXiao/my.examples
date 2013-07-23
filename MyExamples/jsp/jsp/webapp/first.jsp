<%@page contentType="text/html"%>
<!-- 
	This page displays requested method name.
-->
<html>
	<head>
		<title>My First JSP</title>
	</head>
	<body>
		<h1>
			Welcome to JSP World again<br>
			REQUEST_METHOD : <%= request.getMethod()%>
		</h1>
	</body>
</html>