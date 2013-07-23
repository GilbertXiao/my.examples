
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/*
	This servlet generates a multication table for given number.
*/
public class MultiplyServlet extends HttpServlet
{
	public void init() throws ServletException
	{
		System.out.println
			("MultiplyServlet : init()");
	}

	public void destroy()
	{
		System.out.println
			("MultiplyServlet : destroy()");
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

		int a = Integer.parseInt
						(request.getParameter("n1"));

		int b = Integer.parseInt
						(request.getParameter("n2"));
	
		out.println("<html>");
		out.println("<body>");

		out.println
			("<table align='center' border='1'>");

		for(int i=1; i<=b; i++)
		{
			out.println("<tr>");
			out.println("<td><h2>" + a + "*" + i + "</td>");
			out.println("<td><h2>" + (a * i)+ "</td>");
			out.println("</tr>");
		}

		out.println("</table>");
		out.println("</body>");
		out.println("</html>");		
		
		out.flush();
	}
}