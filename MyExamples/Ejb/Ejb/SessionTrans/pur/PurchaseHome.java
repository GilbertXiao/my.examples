
package pur;

//import java.rmi.*;
import javax.ejb.*;

public interface PurchaseHome extends EJBLocalHome
{
	public abstract Purchase create() 
	throws CreateException;
}