
package emp;

import javax.rmi.*;
import java.util.*;
import javax.ejb.*;

public abstract class EmployeeBean 
implements EntityBean
{
	private EntityContext eCtx;

	public void ejbPassivate(){}
	public void ejbActivate() {}
	public void ejbRemove()  	{}
	public void ejbLoad()     {}
	public void ejbStore()    {}

	public void setEntityContext(EntityContext ctx)
	{
		eCtx = ctx;
	}

	public void unsetEntityContext()
	{
		eCtx = null;
	}

	public Integer ejbCreate(String name,float sal) 
	throws CreateException
	{
		//setDBEmpno(id);
		setDBEname(name);
		setDBSal(sal);
		
		return null;
	}

	public void ejbPostCreate(String name,float sal)
	{}

	public abstract Integer getDBEmpno();
	public abstract void setDBEmpno(Integer empno);

	public abstract String getDBEname();
	public abstract void   setDBEname(String name);
	
	public abstract float getDBSal();
	public abstract void  setDBSal(float sal);
	
	public String getName()
	{
		return getDBEname();
	}

	public void setName(String name) 
	{
		setDBEname(name);
	}
	
	public float getSalary() 
	{
		return getDBSal();
	}
	
	public void setSalary(float salary)
	{
		// validation
		setDBSal(salary);
	}

	public EmployeeVO getValue() 
	{
		int    empno = getDBEmpno().intValue();
		String name  = getDBEname();
		float  sal   = getDBSal();

		return new EmployeeVO(empno,name,sal);
	}
}