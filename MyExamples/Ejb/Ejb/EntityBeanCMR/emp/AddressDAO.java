
package emp;

import java.io.*;

public class AddressDAO implements Serializable
{
    public String house_no;
    public String street;
    public String city;
    public String country;

    public AddressDAO
			(String house_no,String street,
    	 String city,String country)
    {
        this.house_no = house_no;
        this.street = street;
        this.city = city;
        this.country = country;
    }
}