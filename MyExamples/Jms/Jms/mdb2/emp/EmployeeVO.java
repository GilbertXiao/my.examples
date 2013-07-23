package emp;

import java.io.Serializable;

public class EmployeeVO implements Serializable
{
	public int empno;
	public String ename;
	public float sal;
	
	public EmployeeVO()
	{}

	public EmployeeVO(int empno,String ename,float sal)
	{
		this.empno = empno;
		this.ename = ename;
		this.sal   = sal;
	}
}