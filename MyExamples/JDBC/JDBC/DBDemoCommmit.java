import java.sql.*;
import java.util.*;

class DBDemoCommmit
{
	public static void main(String[] args) 
	throws SQLException
	{

Class cls = 
com.microsoft.jdbc.sqlserver.SQLServerDriver.class;

String url  = 
	"jdbc:microsoft:sqlserver://localhost:1433";
		
	Connection conn = 
		DriverManager.getConnection(url,"sa","dss");

		conn.setAutoCommit(false);

		Statement stm = conn.createStatement();
	
	int count = stm.executeUpdate
		("DELETE FROM employee where empno=401");

		if(count > 0)
		{
			System.out.println("DELETED");
			conn.commit();
			System.out.println("Tx Complete");
		}
		else
		{
			System.out.println("No Such Employee");
		}

		stm.close();
		conn.close();
	}
}