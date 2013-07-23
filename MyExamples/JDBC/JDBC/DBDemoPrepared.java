import java.sql.*;
class DBDemoPrepared
{
	public static void main(String args[]) throws Exception
	{
		String url			= "jdbc:odbc:mydsn";
		String userID		= "sa";
		String password = "dss";

		Class.forName
			("sun.jdbc.odbc.JdbcOdbcDriver");

		Connection conn = 
			DriverManager.getConnection(url,userID,password);
	
	PreparedStatement ps = 
		conn.prepareStatement
		("INSERT INTO employee values(?,?,?)" );

	ps.setInt   (1,401);
	ps.setString(2,"Abcd");
	ps.setFloat (3,1239.9F);
	int count = ps.executeUpdate();
	System.out.println(count + " row(s) inserted");
		
	ps.setInt   (1,403);
	ps.setString(2,"Qwer");
	ps.setFloat (3,1998.9F);
	count = ps.executeUpdate();
	System.out.println(count + " row(s) inserted");
		
		ps.close();
		conn.close();
	}
}