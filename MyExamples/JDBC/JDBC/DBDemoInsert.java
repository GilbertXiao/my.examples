
import java.sql.*;
import java.util.*;

class DBDemoInsert 
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

String insertCommand = 
	"INSERT INTO employee VALUES(" + args[0] + 
	",'" + args[1] + "'," + args[2] + ")";

		Statement stm = conn.createStatement();

int count = stm.executeUpdate( insertCommand );
System.out.println(count + " row(s) inserted");

		conn.close();
	}
}