
package emp;

public class EmployeeVO 
implements java.io.Serializable
{
	public int		empno;
	public String ename;
	public float	sal;

	public EmployeeVO()
	{}

	public EmployeeVO(int empno,String ename,float sal)
	{
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
	}

	public String toString()
	{
		return 
			(empno + "\t" + ename + "\t" + sal);
	}
}