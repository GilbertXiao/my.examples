import java.sql.*;
import java.util.*;

class DBDemoPrepared2
{
	public static void main(String[] args) 
	throws SQLException
	{
		String url  = "jdbc:odbc:javadsn";

		Properties props = new Properties();
		props.put("user", "sa");
		props.put("password","dss");
		
		Class cls = 
			sun.jdbc.odbc.JdbcOdbcDriver.class;

		Connection conn = 
			DriverManager.getConnection(url,props);
	
PreparedStatement ps = conn.prepareStatement
	( "SELECT * FROM employee WHERE empno = ?");

		ps.setInt(1, Integer.parseInt(args[0]));

		ResultSet rs = ps.executeQuery();

		ResultSetMetaData rm = rs.getMetaData();

		int cols = rm.getColumnCount();

		System.out.println();
		for(int i=1; i<=cols; i++)
		{
			System.out.print
				(rm.getColumnName(i) + "\t");
		}
		
		System.out.println();

		while(rs.next())
		{
			for(int i=1; i<=cols; i++)
			{
				System.out.print(rs.getString(i) + "\t");
			}
		}

		System.out.println();

		ps.close();
		conn.close();
	}
}