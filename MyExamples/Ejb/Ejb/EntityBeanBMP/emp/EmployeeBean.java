
package emp;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import javax.rmi.*;
import javax.sql.*;
import javax.ejb.*;
import javax.naming.*;

public class EmployeeBean implements EntityBean
{
	private int    empno;
	private String ename;
	private float  sal;

	//private boolean salModified;

	private EntityContext eCtx;

	public void ejbActivate() {}
	public void ejbPassivate(){}

	public void ejbLoad()
	{
		Connection con = null;
	  PreparedStatement ps = null;
		ResultSet rs = null;
		Integer intObj = 
			(Integer)eCtx.getPrimaryKey();

		empno = intObj.intValue();

		try
		{
			con = getConnection();
			ps = con.prepareStatement
("select ename,sal from emp where empno = ?");

			ps.setInt(1,empno);
			rs = ps.executeQuery();
			if (rs.next())
			{
				ename = rs.getString(1);
				sal = rs.getFloat(2);
			}
			else
			{
				String msg = "BMP EmployeeBean not found";
				throw new NoSuchEntityException (msg);
			}
   	}
    catch (SQLException e)
    {
     		throw new EJBException(e);
    }
    finally{
     	try{
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			}
			catch(Exception e){
				throw new EJBException(e);
			}
			closeConnection(con);
   	}
	}

	public void ejbStore()
	{
		Connection con = null;
    PreparedStatement ps = null;

		try{
			con = getConnection();
			ps = con.prepareStatement
			("update emp set ename = ?,sal = ? where empno = ?");

			ps.setString(1,ename);
			ps.setFloat(2,sal);
			ps.setInt(3,empno);

   		int count = ps.executeUpdate();

  		if (count<0)
   		{
				String msg = "BMP EmployeeBean not updated";
				throw new NoSuchEntityException (msg);
			}
   	}
		catch(SQLException e)
		{
					throw new EJBException(e);
		}
		finally{
				try{
			ps.close();
		}
		catch(Exception e){
			throw new EJBException(e);
		}
		closeConnection(con);
 	}
}

	public void ejbRemove()
	{
		Connection con = null;
		PreparedStatement ps = null;

		try {
		      con = getConnection();
		      Integer intObj =
		      	(Integer)eCtx.getPrimaryKey();

		      empno = intObj.intValue();

		      ps = con.prepareStatement
		      ("delete from emp where empno = ?");

		      ps.setInt(1,empno);
		      int count = ps.executeUpdate();
		      if (count < 0)
		      {
		        String msg = "BMP EmployeeBean not found";
		        throw new NoSuchEntityException (msg);
		      }
		}
		catch (SQLException e)
		{
			throw new EJBException(e);
		}
		finally{
			try{
				ps.close();
			}
			catch (Exception e){
				throw new EJBException(e);
	    	}
			closeConnection(con);
		}
	}

	public void setEntityContext(EntityContext ec)
	{
		eCtx = ec;
	}
	public void unsetEntityContext()
	{
		eCtx = null;
	}

public Integer ejbCreate
(String name,float salary) throws CreateException
{
	empno = id.intValue();
	ename = name;
	sal = salary;

 	Connection con = null;
  PreparedStatement ps = null;

  try
	{
   	con = getConnection();
		ps = con.prepareStatement
					("insert into emp values (?,?)");

		//ps.setInt   (1,empno);
		ps.setString(1,ename);
		ps.setFloat (2,sal);
			
		int count = ps.executeUpdate();

  	if (count != 1)
  	{
   		String msg = "BMP Unable to Create the EmployeeBean";
   		throw new CreateException (msg);
  	}
	    
		return id;
   }

	catch(SQLException e)
	{
	 try
	 {
		ejbFindByPrimaryKey(id);
	 }
	 catch(FinderException oe)
	 {
		throw new CreateException( e.getMessage() );
	 }

	 String msg = "BMP EmployeeBean already exists";
	 throw new DuplicateKeyException(msg);
	}

	 finally{
			try{
				ps.close();
			}
			catch (Exception e){
				throw new EJBException(e);
			}
		closeConnection(con);
		}
   }

public void ejbPostCreate(String name,float salary)
{}

public Integer ejbFindByPrimaryKey(Integer pk) throws FinderException
{
	Connection con = null;
  PreparedStatement ps = null;

	try{
		con = getConnection();

		ps  = con.prepareStatement
					("select empno from emp where empno = ?");

		ps.setInt( 1, pk.intValue() );
		ResultSet rs = ps.executeQuery();

		if (rs.next())
		{
			//ename = rs.getString(1);
			//sal = rs.getFloat(2);
		}
		else{
			String msg = "BMP PrimaryKey for EmployeeBean not found";
			throw new ObjectNotFoundException(msg);
		}
	}
	catch(SQLException e)
	{
		throw new FinderException(e.getMessage());
	}
	finally{
		try{
			ps.close();
		}
		catch (Exception e){
			throw new EJBException(e);
			}
		closeConnection(con);
	}
		return pk;
}


public Collection ejbFindBySalary(float amount) throws FinderException
{
	Connection con = null;
  PreparedStatement ps = null;
	try	{
 		con = getConnection();
		ps = con.prepareStatement
		("SELECT empno FROM emp WHERE sal >= ?");

		ps.setFloat( 1, amount);
		ResultSet rs = ps.executeQuery();
		Vector v = new Vector();
		while(rs.next())
		{
			v.addElement( rs.getObject(1) );
		}
		return v;
	}
	catch(SQLException e)
	{
		throw new FinderException(e.getMessage());
	}
	finally
	{
		try {
			ps.close();
		}
		catch (Exception e)
		{
				throw new EJBException(e);
	  }
		closeConnection(con);
	}
}


	public String getName()
	{
		return ename;
	}
	public void setName(String s)
	{
		ename = s;
	}
	public float getSalary()
	{
		return sal;
	}
	public void setSalary(float salary)
	{
		sal = salary;
		//salModfied = true;
	}

	public EmployeeVO getValue()
	{
		return new EmployeeVO(empno,ename,sal);
	}

	private Connection getConnection() throws SQLException
	{
	    InitialContext initCtx = null;
	  	try{
		    	initCtx = new InitialContext();

	      	DataSource ds =
		     	(DataSource)initCtx.lookup
			      				("java:comp/env/jdbc/ds");

	      	return ds.getConnection();
	    }
	    catch(NamingException e)
			{
	    	throw new EJBException(e);
	    }
	    finally{
	      try{
	        if(initCtx != null)
	        	initCtx.close();
	      }
	      catch(NamingException e)
	      {
	      	throw new EJBException(e);
	      }
	    }
	}

	private void closeConnection(Connection con)
	{
	    try
	    {
	      if (con != null)
	      	con.close();
	    }
	    catch(Exception e)
	    {
	    	throw new EJBException(e);
	    }
  	}
}