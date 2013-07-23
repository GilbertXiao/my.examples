
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/*
	This servlet takes a required parameter from the request
	and displays a message in appropriate formate.
*/
public class MessageServlet extends HttpServlet
{
	public void init() throws ServletException
	{
		System.out.println
			("First Servlet : init()");
	}

	public void destroy()
	{
		System.out.println
			("First Servlet : destroy()");
	}

	protected void doGet( HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doPost( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");

		ServletOutputStream out = 
			response.getOutputStream();

		String msgValue = 
			request.getParameter("msg");
	
		String boldValue = 
				request.getParameter("bold");
		
		if(boldValue != null)
		{
			msgValue = "<b>" + msgValue + "</b>";
		}

		String mycolorValue = 
			request.getParameter("mycolor");

		if(mycolorValue != null)
		{
			msgValue = "<font color = '" + 
									mycolorValue + "'>" + 
								  msgValue + 
				         "</font>";
		}
		
		String myheaderValue = 
			request.getParameter("myheader");

		if(myheaderValue != null)
		{
			msgValue = "<" + myheaderValue + ">"
									+ msgValue + 
								 "</" + myheaderValue + ">";
		}



		out.println("<html>");
		out.println("<body>");
		out.println("</body>");
		out.println(msgValue);
		out.println("</html>");
		out.flush();
	}
}