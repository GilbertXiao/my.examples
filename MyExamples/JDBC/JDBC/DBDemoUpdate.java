
import java.sql.*;
import java.util.*;

class DBDemoUpdate
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

		String updateCommand = 
			"UPDATE employee " + 
			"SET ename = 'Qwerty',sal = 11223 " + 
			"WHERE empno = 103";

		Statement stm = conn.createStatement();
		int count = stm.executeUpdate( updateCommand );
		System.out.println(count + " row(s) updated");
		conn.close();
	}
}