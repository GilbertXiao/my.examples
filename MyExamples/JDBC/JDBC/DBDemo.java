
import java.sql.*;
import java.util.*;
/**
	Class DBDemo creates a connection using JdbcOdbcDriver
*/
class DBDemo 
{
	public static void main(String[] args) throws Exception
	{
		String url = "jdbc:oracle:thin:@10.100.163.234:1521:EAIDB";
		Properties p = new Properties();
		p.put("user", "ifwpe");
		p.put("password","ifwpe");

		Class.forName("oracle.jdbc.pool.OracleDataSource");

		Connection conn = 
			DriverManager.getConnection( url, p );

		System.out.println("Connection Established");

		conn.close();
	}
}