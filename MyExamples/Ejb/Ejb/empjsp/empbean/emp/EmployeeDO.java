package emp;

import java.io.*;

public class EmployeeDO implements java.io.Serializable
{
	public int empno;
	public String ename;
	public float sal;

	public EmployeeDO(int empno,String ename,float sal)
	{
		this.empno = empno;
		this.ename = ename;
		this.sal   = sal;
	}

	public void show()
	{
		show(System.out);
	}

	public void show(PrintStream out)
	{
		out.println(empno + "\t" + ename + "\t" + sal);
	}
}