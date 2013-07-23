package inv;

//import java.rmi.*;
import java.sql.*;
import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.sql.*;
//import javax.transaction.*;

public class InventoryBean implements SessionBean
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

	public void updateStock(int idP,int qtyP) 
	throws TransactionRolledbackLocalException
	{
		InitialContext ctx = null;
		Connection con = null;	
		try
		{
			if(qtyP <= 0)
				throw new TransactionRolledbackLocalException("Illegal Quantity Purchased");

     	ctx = new InitialContext();

			Object o = 
				ctx.lookup("java:comp/env/jdbc/MyDataSource");

     	DataSource ds = (DataSource)o;
			con = ds.getConnection();
			Statement st = con.createStatement();

			String sql = "update inventory set qty = qty + " + qtyP + " where id = " + idP;
			st.executeUpdate(sql);

			System.out.println
				("InventoryBean UPDATE Successful");
		}
    catch(Exception e)
		{
			System.out.println("Trans Rolledback -- Inventory Bean -- updateStock");
			sc.setRollbackOnly();
			throw new TransactionRolledbackLocalException(e.getMessage());
		}
		finally
		{
			try
			{
				if(con != null)
					con.close();
				if(ctx != null)
					ctx.close();
			}
			catch(Exception e)
			{
				throw new EJBException();
			}		
		}

	}
}