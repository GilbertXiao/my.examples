
package emp;
import java.io.Serializable;

public class EmployeeVO implements Serializable
{
	public int    empno;
	public String ename;
	public float  sal;
	
	public EmployeeVO()
	{}

	public EmployeeVO(int empno,String ename,float sal)
	{
		this.empno = empno;
		this.ename = ename;
		this.sal   = sal;
	}

	public int getEmpno()
	{
		return empno;
	}
	public void setEmpno(int empno)
	{
		this.empno = empno;
	}

	public String getEname()
	{
		return ename;
	}
	public void setEname(String ename)
	{
		this.ename = ename;
	}

	public float getSalary()
	{
		return sal;
	}
	public void setSalary(float sal)
	{
		this.sal = sal;
	}
}