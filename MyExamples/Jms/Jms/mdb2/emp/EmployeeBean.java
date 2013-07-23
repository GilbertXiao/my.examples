package emp;

import java.rmi.*;
import javax.ejb.*;

public abstract class EmployeeBean implements EntityBean
{
	private EntityContext ctx;

	public void ejbActivate() {}
	public void ejbPassivate(){}
	public void ejbRemove()   {}

	public void ejbLoad() {}
	public void ejbStore(){}

	public void setEntityContext(EntityContext eCtx)
	{
		ctx = eCtx;
	}

	public void unsetEntityContext()
	{
		ctx = null;
	}

	public Integer ejbCreate(String name,float salary) 
	throws CreateException
	{
		setDBEname(name);
		setDBSal(salary);
		
		return null;
	}
	
	public void ejbPostCreate(String name,float salary)
	{}

	public abstract Integer getDBEmpno();
	public abstract void setDBEmpno(Integer a);

	public abstract String getDBEname();
	public abstract void setDBEname(String a);

	public abstract float getDBSal();
	public abstract void setDBSal(float a);

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
		//validate 

		setDBSal(salary);
	}

	public EmployeeVO getValue()
	{
		return new EmployeeVO(getDBEmpno().intValue(),
													getDBEname(),
													getDBSal());
	}
}