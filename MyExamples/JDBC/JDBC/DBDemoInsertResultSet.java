
import java.sql.*;
import java.util.*;

class DBDemoInsertResultSet
{
	public static void main(String[] args) 
	throws Exception
	{

Class.forName
("com.microsoft.jdbc.sqlserver.SQLServerDriver");

String url  = 
	"jdbc:microsoft:sqlserver://localhost:1433";
		
	Connection conn = 
		DriverManager.getConnection(url,"sa","dss");
	
	Statement stm = 
			conn.createStatement
				( ResultSet.TYPE_SCROLL_SENSITIVE, 
				  ResultSet.CONCUR_UPDATABLE
			  );

	ResultSet rs = stm.executeQuery
						 ("SELECT * from employee");

		rs.moveToInsertRow();

		rs.updateInt	 ("empno",401);
		rs.updateString("ename","abcd");
		rs.updateInt   ("sal",13400);
				
		rs.insertRow();
		System.out.println("Row Inserted");

		rs.moveToCurrentRow();

		rs.close();
		stm.close();
		conn.close();
	}
}