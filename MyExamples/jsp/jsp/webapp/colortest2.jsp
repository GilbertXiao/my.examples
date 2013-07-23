<%
	String mycolorValue = 
		request.getParameter("mycolor");
	/*
		if none of the color is checked from color.html page, this page redirects
		the request color2.jsp page.
		
		If color is checked, it desplay the content of the page in given color.
	*/
	if(mycolorValue == null)
	{
		String name		= 
			java.net.URLEncoder.encode("myErrMsg");

		String value  =	
			java.net.URLEncoder.encode("You did NOT select a color");

		String qs	= name + "=" + value;
		String rurl	= "color2.jsp?" + qs;
		response.sendRedirect(rurl);
	}
%>

<html>
	<body text='<%= mycolorValue%>'>
		<h1>
			This is the response from colortest.jsp
		</h1>
	</body>
</html>