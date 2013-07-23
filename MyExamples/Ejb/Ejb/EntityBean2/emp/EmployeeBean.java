package emp;

import java.util.*;
import java.sql.*;
import javax.sql.*;

import javax.ejb.*;
import javax.naming.*;

public class EmployeeBean implements EntityBean
{
	private int empno;
	private String ename;
	private float sal;

	private EntityContext eCtx;

	public void ejbActivate() {}
	public void ejbPassivate(){}

	public void ejbLoad()
	{
		Connection con = null;
	    PreparedStatement ps = null;
	    Integer intObj = (Integer)eCtx.getPrimaryKey();
	    empno = intObj.intValue();

    	try
    	{
      		con = getConnection();
		    ps = con.prepareStatement
		    ("select ename,sal from emp where empno = ?");

      		ps.setInt(1,empno);

      		ResultSet rs = ps.executeQuery();
      		if (rs.next())
      		{
        		ename = rs.getString(1);
        		sal = rs.getFloat(2);
      		}
      		else
      		{
        		String msg = "EmployeeBean not found";
        		throw new NoSuchEntityException(msg);
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
			catch(Exception e){
				throw new EJBException(e);
			}
			closeConnection(con);
    	}
	} // end of ejbLoad

	public void ejbStore()
	{
		Connection con = null;
	    PreparedStatement ps = null;

	    try{
			con = getConnection();
      		ps = con.prepareStatement("update emp set ename = ?,sal = ? where empno = ?");

      		ps.setString(1,ename);
      		ps.setFloat(2,sal);
      		ps.setInt(3,empno);

      		int count = ps.executeUpdate();

      		if (count<0)
      		{
        		String msg = "EmployeeBean not updated";
        		throw new NoSuchEntityException(msg);
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
		      ps = con.prepareStatement
		      ("delete from emp where empno = ?");

			  Integer intObj =
		      	(Integer)eCtx.getPrimaryKey();

		      empno = intObj.intValue();
		      ps.setInt(1,empno);
		      int count = ps.executeUpdate();
		      if (count < 0)
		      {
		        String msg = "EmployeeBean not found";
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

	public Integer ejbCreate(String name,float salary)
	throws CreateException
	{
		ename = name;
	    sal = salary;

    	Connection con = null;
	    PreparedStatement ps = null;

	    try{
	      	con = getConnection();
			ps = con.prepareStatement("INSERT INTO emp(ename,sal) VALUES(?,?)");
      		ps.setString(1,ename);
		    ps.setFloat(2,sal);
			int count = ps.executeUpdate();

      		if (count != 1)
      		{
        		String msg = "Unable to Create the EmployeeBean";
				throw new CreateException(msg);
      		}

			ResultSet rs = 
				con.createStatement().executeQuery
							("SELECT @@identity");
			rs.next();
		    empno = rs.getInt(1);
			return new Integer(empno);
	    }
	    catch(SQLException e)
	    {
   		  throw new CreateException(e.getMessage());
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

	public Integer ejbFindByPrimaryKey(Integer pk)
	throws ObjectNotFoundException
  	{
        Connection con = null;
    	PreparedStatement ps = null;

	    try{
			con = getConnection();
			ps  = con.prepareStatement("SELECT ename,sal FROM emp WHERE empno = ?");
			ps.setInt(1,pk.intValue());

			ResultSet rs = ps.executeQuery();

			if (rs.next()){
				ename = rs.getString(1);
				sal = rs.getFloat(2);
			}
			else{
				String msg = "PrimaryKey for EmployeeBean Not Found";
				throw new ObjectNotFoundException (msg);
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
			catch (Exception e){
				throw new EJBException(e);
	    	}
			closeConnection(con);
		}
	    return pk;
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
	}

private Connection getConnection() throws SQLException
{
    InitialContext initCtx = null;
		try
		{
    	initCtx = new InitialContext();
     	DataSource ds = (DataSource)initCtx.lookup
							     			  ("java:comp/env/jdbc/ds");
    	return ds.getConnection();
    }
    catch(NamingException e){
    	throw new EJBException(e);
    }
    finally
		{
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

	public Collection ejbFindBySalary(float f) 
	throws FinderException
	{
		Connection con = null;
	    PreparedStatement ps = null;

    	try
    	{
      		con = getConnection();
		    ps = con.prepareStatement("SELECT empno FROM emp WHERE sal >= ?");
			ps.setFloat(1,f);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Vector v = new Vector();
				do
				{
					v.addElement(rs.getObject(1));
				}while(rs.next());
				return v;
			}
			else throw new FinderException();
		}
		catch(SQLException e)
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
}