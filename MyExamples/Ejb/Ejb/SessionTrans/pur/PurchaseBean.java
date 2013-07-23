
package pur;

//import java.rmi.*;
import java.sql.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
//import javax.transaction.*;

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
	throws TransactionRolledbackLocalException
	{
		InitialContext ic = null;
		Connection conn = null;
		try
		{
			ic = new InitialContext();
		
			Object o = 
				ic.lookup("java:comp/env/jdbc/MyDataSource");
			
			DataSource ds = (DataSource)o;

			conn = ds.getConnection();
			Statement st = conn.createStatement();

			String sql = 
				"insert into purchase values(" + id + "," + qty + ")";

			st.executeUpdate(sql);

			System.out.println
				("Purchase Table INSERT Successful");
		}
		catch(Exception e)
		{
			System.out.println("Transaction Rolledback - PurchaseBean -- purchaseItem");
			sc.setRollbackOnly();
			throw new TransactionRolledbackLocalException(e.getMessage());
		}

		finally
		{
			try
			{
				if(conn != null)
					conn.close();
				if(ic != null)
					ic.close();
			}
			catch(Exception e)
			{
				throw new EJBException();
			}		
		}
	}
}
