
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WelcomeServlet extends HttpServlet
{
	public void init() throws ServletException
	{}

	public void destroy()
	{}

	protected void doGet( HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doPost( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");

		ServletOutputStream out = 
			response.getOutputStream();

		String mynameValue = 
			request.getParameter("myname");

		String body;
		
		if(mynameValue==null || mynameValue.equals(""))
		{
			body = 
				"Name cannot be EMPTY..." + 
				"<a href = 'name.html'>" + 
				"Try Again</a>";
		}
		else 
		{
			body = "Welcome " + mynameValue;
		}
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>");
		out.println(body);
		out.println("</h1>");
		out.println("</body>");
		out.println("</html>");

		out.flush();
	}
}