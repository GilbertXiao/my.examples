package emp;

/**
* emp/EmployeeNotFoundHolder.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from emp.idl
* Sunday, June 16, 2002 6:43:16 PM IST
*/

public final class EmployeeNotFoundHolder implements org.omg.CORBA.portable.Streamable
{
  public emp.EmployeeNotFound value = null;

  public EmployeeNotFoundHolder ()
  {
  }

  public EmployeeNotFoundHolder (emp.EmployeeNotFound initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = emp.EmployeeNotFoundHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    emp.EmployeeNotFoundHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return emp.EmployeeNotFoundHelper.type ();
  }

}