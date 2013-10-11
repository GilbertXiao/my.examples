import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import emp.EmployeeNotFound;

public class ManagerServant extends 
emp._ManagerImplBase
{
	Connection conn;
	PreparedStatement psGetEmployee,psAll;
		
	public ManagerServant(String url,String user,String pass) 
	throws SQLException
	{
		conn = 
			DriverManager.getConnection(url,user,pass);
		
		psGetEmployee = conn.prepareStatement	
			("SELECT ename,sal from employees where empno = ?");

		psAll = conn.prepareStatement	
			("SELECT empno,ename,sal from employees");

		System.out.println("Manager Ready");
	}

	public emp.Employee getEmployee(int empid) 
	throws EmployeeNotFound
	{
		emp.Employee myEmp = null;
		try
		{
			psGetEmployee.setInt(1,empid);
			ResultSet rs = psGetEmployee.executeQuery();

			if(rs.next())
			{
				String ename = rs.getString("ename");
				float sal = rs.getFloat("sal");
				
				myEmp = new emp.Employee(empid,ename,sal);
			}
			else 
			{
				throw new EmployeeNotFound
											("Employee Not Found");
			}
		}
		catch(SQLException e)
		{}

		return myEmp;
	}

	public emp.Employee[] getAll()
	{
		Vector v = new Vector();

		try
		{
			ResultSet rs = psAll.executeQuery();
			while(rs.next())
			{
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				float sal = rs.getFloat("sal");
				
				v.addElement
					( new emp.Employee(empno,ename,sal) );
			}
		}
		catch(SQLException e)
		{}

		emp.Employee empArray[] = new emp.Employee[v.size()];

		for(int i=0; i<empArray.length; i++)
			empArray[i] = (emp.Employee)v.elementAt(i);

		return empArray;
	}
}