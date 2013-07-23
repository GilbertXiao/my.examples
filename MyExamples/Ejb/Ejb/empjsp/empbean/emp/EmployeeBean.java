package emp;

import java.rmi.*;
import javax.ejb.*;

public abstract class EmployeeBean implements EntityBean
{
	private EntityContext eCtx;

	public void ejbActivate() {}
	public void ejbPassivate(){}
	public void ejbLoad()     {}
	public void ejbStore()    {}
	public void ejbRemove()   {}

	public void setEntityContext(EntityContext ec)
	{
		eCtx = ec;
		System.out.println("Bean instance created");
	}
	public void unsetEntityContext()
	{
		eCtx = null;
	}

	public Integer ejbCreate(String name,float salary) throws CreateException
	{
		//setEmployeeID(id);
		setEmployeeName(name);
		setEmployeeSalary(salary);

		return null;
	}

	public void ejbPostCreate(String name,float salary)
	{

	}

	public String getName()
	{
		return getEmployeeName();
	}
	public void setName(String s)
	{
		setEmployeeName(s);
	}

	public float getSalary()
	{
		return getEmployeeSalary();
	}
	public void setSalary(float sal)
	{
		setEmployeeSalary(sal);
	}

	public EmployeeDO getEmployee()
	{
		return new EmployeeDO(getEmployeeID().intValue(),getEmployeeName(),getEmployeeSalary());
	}

	public abstract Integer getEmployeeID();
	public abstract void setEmployeeID(Integer id);

	public abstract String getEmployeeName();
	public abstract void setEmployeeName(String str);

	public abstract float getEmployeeSalary();
	public abstract void setEmployeeSalary(float sal);
}