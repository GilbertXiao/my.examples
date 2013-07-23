package pur;

import java.rmi.*;
import java.sql.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
import javax.transaction.*;

public class PurchaseBean implements SessionBean
{
	SessionContext sc;

	public void ejbActivate() {}
	public void ejbPassivate(){}
	public void ejbRemove()   {}

	public void setSessionContext(SessionContext sc)
	{
		this.sc = sc;
	}

	public void ejbCreate()
	{}

	public void purchaseItem(int id,int qty)
	throws TransactionRolledbackException
	{

		Connection con = null;

		try
	  {
		String sql = "insert into purchase values(" + id + "," + qty + ")";

		InitialContext ic = new InitialContext();

   	Object o =
   	  ic.lookup("java:comp/env/jdbc/MyDataSource");

   	DataSource ds = (DataSource)o;

		con = ds.getConnection();

		Statement st = con.createStatement();

		st.executeUpdate(sql);

		System.out.println("Purchase Table INSERT Successful");

		}
		catch(Exception e)
		{
			sc.setRollbackOnly();

			System.out.println("Transaction Rolledback - PurchaseBean -- purchaseItem");

			throw new TransactionRolledbackException();
		}

		finally{
			try{
				con.close();
			}
			catch(SQLException e)
			{}		
		}
	}
}