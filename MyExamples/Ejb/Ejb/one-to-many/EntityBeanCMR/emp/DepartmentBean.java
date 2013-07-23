package emp;

import java.rmi.*;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;

public abstract class DepartmentBean implements EntityBean
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

	public abstract Integer getDBDeptno();
	public abstract void 	setDBDeptno(Integer id);

	public abstract String getDBDeptname();
	public abstract void   setDBDeptname(String name);

	public abstract String getDBLocation();
	public abstract void   setDBLocation(String location);

    public abstract Set  getDBEmployee();
    public abstract void setDBEmployee(Set emp);
	public abstract Collection ejbSelectAllEmployeesByName(int deptno,String name) throws FinderException;

	public Integer ejbCreate(String name,String location)
 	{
		this. setDBDeptname(name);
		this.setDBLocation(location);

       	return null;
 	}
	public void ejbPostCreate(String deptname,String location)
	{
		
	}

	public EmpVal getEmployee(Integer id)
    {
		Set empSet = this.getDBEmployee();

		if (empSet.isEmpty())
			return null;
		EmpVal empObj=null;
		Iterator it = empSet.iterator();
		while(it.hasNext())
		{
			Employee emp=(Employee)it.next();
			Integer empid = (Integer)emp.getPrimaryKey();
			if(id.intValue()== empid.intValue())
			{
				empObj = new EmpVal(id.intValue(),emp.getName(),emp.getSal());
			}

		}

		return empObj;
    }

	public void addEmployee(EmpVal eval)
	{

		System.out.println("Entered setEmployee");
		addEmployee(eval.name,eval.sal);
	}
	public void addEmployee(String name,float sal)
	{
		Set empSet = this.getDBEmployee();

				try
				{
						EmployeeHome empHome = getEmpHome();

						System.out.println("Narrowed EmployeeHome");

						Employee emp = empHome.create(name,sal);

						System.out.println("Employee Created");

						empSet.add(emp);
						this.setDBEmployee(empSet);

				}
				catch (CreateException ce)
				{
					throw new EJBException(ce);
				}
	}
	public Collection getAllEmployees()
	{
		Vector v = new Vector();
			Set allEmp = getDBEmployee();
			Iterator it  = allEmp.iterator();
			while(it.hasNext())
			{
				Employee emp=(Employee)it.next();
				Integer id = (Integer)emp.getPrimaryKey();
				v.add(new EmpVal(id.intValue(),emp.getName(),emp.getSal()));
			}
		return v;
	}
	public EmployeeHome getEmpHome()
	{
		EmployeeHome empHome;
		try
		{
			InitialContext cntx = new InitialContext();
			Object obj = cntx.lookup("java:comp/env/ejb/employee");
			System.out.println("GOT EmployeeHome");
			empHome =  (EmployeeHome)obj;
		}
		catch (NamingException ne)
		{
			throw new EJBException(ne);
		}
		return empHome;

	}
	public void removeEmployee(Integer id)
	{
		try
		{
			EmployeeHome empHome = getEmpHome();
			empHome.findByPrimaryKey(id).remove();
			//Set empSet = this.getDBEmployee();
			//empSet.remove(empHome.findByPrimaryKey(id));
		}
		catch(FinderException e)
		{
			throw new EJBException(e);
		}
		catch(RemoveException e)
		{
			throw new EJBException(e);
		}
	}
	public Collection getAllEmployeesByName(String name)
	{
		Vector v = new Vector();
		try
		{
			Collection allEmp = ejbSelectAllEmployeesByName(getDBDeptno().intValue(),name);
			Iterator it  = allEmp.iterator();
			while(it.hasNext())
			{
				Employee emp=(Employee)it.next();
				Integer id = (Integer)emp.getPrimaryKey();
				v.add(new EmpVal(id.intValue(),emp.getName(),emp.getSal()));
			}
		}
		catch(FinderException e)
		{
			throw new EJBException(e);
		}
		return v;
	}
	public void   setEmployeeSalary(Integer id,float newSalary)
	{
		try
		{
			EmployeeHome empHome = getEmpHome();
			empHome.findByPrimaryKey(id).setSal(newSalary);
			System.out.println("Salary updated");
		}
		catch(FinderException e)
		{
			throw new EJBException(e);
		}
	}
}