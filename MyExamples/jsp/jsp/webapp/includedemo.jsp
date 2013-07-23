<!-- 
	This page demonstrate the usage of include directive(<%@include file="a.html"%>)

	it displays a.html page in fitst column and b.html in second column
-->
<html>
	<body>
		<table width="100%" height="100%"
					 border = "1" align="center">
			<tr valign="top">
				<td>
					<%@include file="a.html"%>
				</td>
				<td>
					<%@include file="b.html"%>
				</td>
			</tr>
		</table>
	</body>
</html>
