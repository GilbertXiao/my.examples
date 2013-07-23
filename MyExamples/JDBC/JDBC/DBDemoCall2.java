
import java.sql.*;

class DBDemoCall2
{
	public static void main(String[] args) 
	throws SQLException
	{
		String url = "jdbc:odbc:javadsn";

		Class cls = 
			sun.jdbc.odbc.JdbcOdbcDriver.class;

	Connection conn = 
	DriverManager.getConnection(url,"sa","dss");

	CallableStatement cs = 
		conn.prepareCall("{call get_salary(?,?)}");

	int empno = Integer.parseInt(args[0]);
	cs.setInt(1,empno);
			
	cs.registerOutParameter(2, Types.FLOAT);

	cs.execute();

	float salary = cs.getFloat(2);
	
	if(cs.wasNull())
	{
		System.out.println("No such employee");
	}
	else 
		System.out.println("Salary = " + salary);

		cs.close();
		conn.close();
	}
}