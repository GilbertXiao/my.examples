
import java.sql.*;
import java.util.*;

class DBDemoCreate 
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
	
		String createCommand = 
			"CREATE TABLE employee" + 
			"(" + 
				"empno int primary key," + 
				"ename varchar(20)," + 
				"sal	  float" + 
			")";

		try
		{	
			stm.execute("DROP TABLE employee");
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}

		stm.execute(createCommand);
		System.out.println("Table Create Succesfully");
		stm.close();
		conn.close();
	}
}