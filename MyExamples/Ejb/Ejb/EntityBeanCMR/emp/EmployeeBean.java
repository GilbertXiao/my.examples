
package emp;

import java.rmi.*;
import javax.ejb.*;
import javax.naming.*;

public abstract class EmployeeBean implements EntityBean
{
	private EntityContext ec;

	public void ejbActivate()  {}
	public void ejbPassivate() {}
	public void ejbLoad()      {}
	public void ejbStore()     {}
	public void ejbRemove()    {}

	public void setEntityContext(EntityContext ec)
	{
		this.ec = ec;
	}
	public void unsetEntityContext()
	{
		this.ec = null;
	}

	public abstract Integer  getDBEmpno();
	public abstract void setDBEmpno(Integer id);

	public abstract String getDBEname();
	public abstract void   setDBEname(String name);

	public abstract float getDBSal();
	public abstract void  setDBSal(float f);

	public abstract AddressLocal getDBAddress();
	public abstract void setDBAddress(AddressLocal al);

public Integer ejbCreate
(String name,float salary,AddressDAO ad)
{
	this.setDBEname(name);
	this.setDBSal(salary);
	return null;
}

public void ejbPostCreate
(String name, float salary, AddressDAO ad)
{
	this.setAddress
	 (ad.house_no, ad.street, ad.city, ad.country);
}

	public String getName()
	{
    return this.getDBEname();
	}
  public void setName(String s)
	{
    this.setDBEname(s);
	}

	public float getSalary()
	{
    return this.getDBSal();
	}
	public void setSalary(float f)
	{
    this.setDBSal(f);
  }
  
	public AddressDAO getAddress()
  {
		AddressLocal addrLocal = this.getDBAddress();

		if (addrLocal == null)
			return null;

		String house_no = addrLocal.getHouseNo();
		String street   = addrLocal.getStreet();
		String city     = addrLocal.getCity();
		String country  = addrLocal.getCountry();

		AddressDAO addrObj = new AddressDAO
								(house_no,street,city,country);

		return addrObj;
	}

	public void setAddress(AddressDAO ad)
	{
		String house_no = ad.house_no;
		String street   = ad.street;
		String city     = ad.city;
		String country  = ad.country;

		setAddress(house_no,street,city,country);

		System.out.println("Address is set");
	}

	public void setAddress
	(String house_no,String street,String city,String country)
	{
		AddressLocal addr = this.getDBAddress();

		try{
			if (addr == null)
			{
				InitialContext cntx = new InitialContext();

	Object obj = 
		cntx.lookup("java:comp/env/ejb/address");

				AddressLocalHome addrHome =
									  (AddressLocalHome)obj;

				addr = addrHome.create
					(house_no,street,city,country);

				this.setDBAddress(addr);
			}
			else
			{
				addr.setHouseNo(house_no);
				addr.setStreet(street);
				addr.setCity(city);
				addr.setCountry(country);
			}
		}
		catch (NamingException ne)
		{
			throw new EJBException(ne);
		}
		catch (CreateException ce)
		{
			throw new EJBException(ce);
		}
	}
}