
package emp;

import javax.ejb.*;

public abstract class AddressBean 
implements EntityBean
{
    public void ejbActivate(){}
    public void ejbPassivate(){}
    public void ejbLoad(){}
    public void ejbStore(){}
    public void ejbRemove(){}
    public void setEntityContext(EntityContext ec){}
    public void unsetEntityContext(){}

    public Integer ejbCreate
    (String house_no,String street,
		 String city,String country)
    {
			setDBHouseNo(house_no);
			setDBStreet(street);
			setDBCity(city);
			setDBCountry(country);

      return null;
    }

    public void ejbPostCreate
    (String house_no,String street,
		 String city,String country)
    {}

    public String getHouseNo()
    {
        return getDBHouseNo();
    }
    public void setHouseNo(String house_no)
    {
        setDBHouseNo(house_no);
    }

    public String getStreet()
    {
        return getDBStreet();
    }
    public void setStreet(String street)
    {
        setDBStreet(street);
    }

    public String getCity()
    {
        return getDBCity();
    }
    public void setCity(String city)
    {
        setDBCity(city);
    }

    public String getCountry()
    {
        return getDBCountry();
    }
    public void setCountry(String country)
    {
        setDBCountry(country);
    }

		public abstract Integer getDBId();
    public abstract void setDBId(Integer id);

    public abstract String getDBHouseNo();
    public abstract void setDBHouseNo(String house_no);

    public abstract String getDBStreet();
    public abstract void setDBStreet(String street);

    public abstract String getDBCity();
    public abstract void setDBCity(String city);

    public abstract String getDBCountry();
    public abstract void setDBCountry(String country);

    public abstract Integer getDBEmpID();
    public abstract void setDBEmpID(Integer empno);
}