
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SportsServlet extends HttpServlet
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

		String sportsValue[] = 
			request.getParameterValues("sports");
	
		out.println("<html>");
		out.println("<body>");
		out.println("</body>");
	
		for(int i=0; i<sportsValue.length;i++)
		{
			out.println(sportsValue[i] + "<br>");
		}

		out.println("</html>");
		out.flush();
	}
}