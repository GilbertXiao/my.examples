package emp;


/**
* emp/Employee.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from emp.idl
* Wednesday, October 17, 2012 5:46:25 PM EDT
*/

public final class Employee implements org.omg.CORBA.portable.IDLEntity
{
  public int empno = (int)0;
  public String ename = null;
  public float sal = (float)0;

  public Employee ()
  {
  } // ctor

  public Employee (int _empno, String _ename, float _sal)
  {
    empno = _empno;
    ename = _ename;
    sal = _sal;
  } // ctor

} // class Employee
