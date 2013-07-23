
<!-- 
	This page displays "This is line 1" and "This is line 2" in different color.
	color is provided by the color attribute of custom tag (<demo:color color="blue">)
-->
<%@taglib uri ="/WEB-INF/colortag.tld" 
				  prefix ="demo"%>

<html>
	<body>
		<h1>
			<demo:color color="blue">
				This is line 1<br>
				<demo:color>
					This is line 2<br>
				</demo:color>
				<%= "This is line 3"%>
			</demo:color>
		</h1>
	</body>
</html>