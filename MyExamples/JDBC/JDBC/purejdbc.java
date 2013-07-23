import java.sql.*;
import java.util.*;
import java.io.*;

class purejdbc 
{
	public static void main(String[] args) throws IOException
	{
		PrintWriter pw = new PrintWriter (System.out,true);

		String url= "jdbc:microsoft:sqlServer://localhost:1433";
		//"jdbc:oracle:thin:@localhost:1521:shailu"; //for oracle
		// String url="jdbc:odbc:dsn1"; //for bridge
		String user="sa";
		String password="dss";

		try
			{
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
				//Class.forName("oracle.jdbc.driver.OracleDriver");  //for oracle
				//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");     //for bridge
			}
		catch(ClassNotFoundException e)
			{
				pw.println("----------Unable to Load driver----\n"+e);
				pw.println("--------"+e.getMessage()+"-----------");
				return;
			}

		try
			{
				Connection con=DriverManager.getConnection(url,user,password);
				pw.println("--------connection created--------");

				con.close();
				pw.println("---connection closed---");
			}
		catch(SQLException ex)
			{
				pw.print("SQLException:\n");
				pw.println("-------------");
				pw.println(ex.getMessage());
            }	
	}
}
