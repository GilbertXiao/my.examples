package emp;

import javax.ejb.*;
import java.util.*;
public interface EmployeeHome extends javax.ejb.EJBLocalHome
{
    public Employee create(String name,float sal) throws CreateException;
    public Employee findByPrimaryKey(Integer pk) throws FinderException;
}