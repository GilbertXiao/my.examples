import java.sql.*;
import java.util.*;

class DBDemoSelect
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

		Statement stm = conn.createStatement();

String myQuery = 
"SELECT empno,ename,sal FROM employee " + 
 "WHERE 1 = 2";

		ResultSet rs = stm.executeQuery(myQuery);
		ResultSetMetaData rm = rs.getMetaData();

		int cols = rm.getColumnCount();

		System.out.println();
		for(int i=1; i<=cols; i++)
		{
			System.out.print
				(rm.getColumnLabel(i) + "\t");
		}
		System.out.println("\n");

		while(rs.next())
		{
			int			empno = rs.getInt   (1);
			String	ename = rs.getString(2);
			float		sal		= rs.getFloat (3);
			String salDisplay;

			if( rs.wasNull() )
				salDisplay  =	"null";
			else 
				salDisplay = String.valueOf(sal);

			System.out.println
				(	empno + "\t" + ename + "\t" + salDisplay );
		}

		rs.close();
		stm.close();
		conn.close();
	}
}