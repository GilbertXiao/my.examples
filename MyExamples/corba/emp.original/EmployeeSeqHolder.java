package emp;


/**
* emp/EmployeeSeqHolder.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from emp.idl
* Sunday, June 16, 2002 6:43:16 PM IST
*/

public final class EmployeeSeqHolder implements org.omg.CORBA.portable.Streamable
{
  public emp.Employee value[] = null;

  public EmployeeSeqHolder ()
  {
  }

  public EmployeeSeqHolder (emp.Employee[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = emp.EmployeeSeqHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    emp.EmployeeSeqHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return emp.EmployeeSeqHelper.type ();
  }

}
