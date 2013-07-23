package emp;

import javax.ejb.*;

public interface Employee extends EJBLocalObject
{
    public String getName();
    public void   setName(String name);

    public float getSal();
    public void setSal(float sal);
}