package emp;

import javax.ejb.*;

public interface AddressLocalHome 
extends javax.ejb.EJBLocalHome
{
    public AddressLocal create
    	(String house_no,String street,
			 String city,String country) 
			 throws CreateException;

    public AddressLocal 
			findByPrimaryKey(Integer pk) 
			throws javax.ejb.FinderException;
}