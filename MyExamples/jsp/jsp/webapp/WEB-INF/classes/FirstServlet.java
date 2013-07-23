
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet
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

		String requestMethod = request.getMethod();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("First Servlet");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");
		out.println("Welcome to WebLogic World");
		out.println("<br>");

		out.println
			("REQUEST_METHOD : " + requestMethod);

		out.println("</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}
}