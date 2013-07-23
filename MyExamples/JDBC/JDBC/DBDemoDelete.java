
import java.sql.*;
import java.util.*;

class DBDemoDelete
{
	public static void main(String[] args) throws Exception
	{
		String url = "jdbc:odbc:mydsn";
		Properties p = new Properties();
		p.put("user", "sa");
		p.put("password","dss");
		
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

		Connection conn = 
			DriverManager.getConnection( url, p );

String deleteCommand = 
"DELETE FROM employee WHERE empno = "+args[0];

		Statement stm = conn.createStatement();

int count = stm.executeUpdate( deleteCommand );
System.out.println(count + " row(s) deleted");

		conn.close();
	}
}