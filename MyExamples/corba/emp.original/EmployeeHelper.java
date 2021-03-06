package emp;


/**
* emp/EmployeeHelper.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from emp.idl
* Sunday, June 16, 2002 6:43:16 PM IST
*/

abstract public class EmployeeHelper
{
  private static String  _id = "IDL:emp/Employee:1.0";

  public static void insert (org.omg.CORBA.Any a, emp.Employee that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static emp.Employee extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "empno",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "ename",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_float);
          _members0[2] = new org.omg.CORBA.StructMember (
            "sal",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (emp.EmployeeHelper.id (), "Employee", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static emp.Employee read (org.omg.CORBA.portable.InputStream istream)
  {
    emp.Employee value = new emp.Employee ();
    value.empno = istream.read_long ();
    value.ename = istream.read_string ();
    value.sal = istream.read_float ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, emp.Employee value)
  {
    ostream.write_long (value.empno);
    ostream.write_string (value.ename);
    ostream.write_float (value.sal);
  }

}
