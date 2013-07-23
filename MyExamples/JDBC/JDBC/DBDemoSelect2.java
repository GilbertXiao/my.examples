
import java.sql.*;

class DBDemoSelect
{
	public static void main(String[] args) 
	throws SQLException
	{
		String url = "jdbc:odbc:javadsn";

		java.util.Properties p = 
			new java.util.Properties();

		p.put("user","sa");
		p.put("password","dss");
		
		Class cls = 
			sun.jdbc.odbc.JdbcOdbcDriver.class;

		Connection conn = 
			DriverManager.getConnection( url, p );

		Statement stm = conn.createStatement();		

		ResultSet rs = 
			stm.executeQuery
			("SELECT * FROM employee");

		ResultSetMetaData rm = rs.getMetaData();

		int colCount = rm.getColumnCount();

		System.out.println();
		for(int i=1; i<=colCount; i++)
		{
			System.out.print
				( 
					rm.getColumnName(i) + "\t" 
				);
		}
		System.out.println();

		/*
			while(rs.next())
			{
				for(int i=1; i<=colCount; i++)
				{
					System.out.print( rs.getString(i) );
					System.out.print( "\t" );
				}
				System.out.println();
			}
		*/
		while(rs.next())
		{
			int		 empno = rs.getInt("empno");
			String ename = rs.getString("ename");
			float  sal   = rs.getFloat("sal");

			String salDisplay = String.valueOf(sal);

			if( rs.wasNull() )
				salDisplay = "NULL";				

			System.out.println
				( empno + "\t" + 
				  ename + "\t" + salDisplay );
		}

		rs.close();
		stm.close();
		conn.close();
	}
}