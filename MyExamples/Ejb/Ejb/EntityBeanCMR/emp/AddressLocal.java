package emp;

import javax.ejb.*;

public interface AddressLocal extends EJBLocalObject
{
    public String getHouseNo();
    public void   setHouseNo(String house_no);

    public String getStreet();
    public void   setStreet(String street);

    public String getCity();
    public void   setCity(String city);

    public String getCountry();
    public void   setCountry(String country);
}