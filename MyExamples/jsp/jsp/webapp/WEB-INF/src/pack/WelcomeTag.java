
package pack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class WelcomeTag extends TagSupport
{
	public int doStartTag() 
	{
		return SKIP_BODY;
	}
	public int doEndTag()
	{
		ServletRequest request = 
			pageContext.getRequest();

		String mynameValue = 
			request.getParameter("myname");

		try
		{
			if( mynameValue == null || 
				  mynameValue.equalsIgnoreCase(""))
			{
				pageContext.forward("/name.html");
			}
			else
			{
				pageContext.getOut().println
					("<h1>" + mynameValue + "</h1>");	
			}
		}
		catch(IOException e)
		{}
		catch(ServletException e)
		{}

		return EVAL_PAGE;
	} 
}
