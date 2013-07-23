import java.sql.*;

class DBDemoCall
{
	public static void main(String args[]) throws Exception
	{
		String url = 
			"jdbc:microsoft:sqlserver://localhost:1433";
		String userID	= "sa";
		String password = "dss";
		Class.forName
			("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		Connection conn = 
			DriverManager.getConnection(url,userID,password);
		CallableStatement cs = 
			conn.prepareCall("{call add_num(?,?,?)}");
		cs.setInt(1, Integer.parseInt(args[0]));
		cs.setInt(2, Integer.parseInt(args[1]));
		cs.registerOutParameter(3,Types.INTEGER);
		cs.execute();
		int result = cs.getInt( 3 );
		System.out.println(result);
		cs.close();
		conn.close();
	}
}