package emp;


/**
* emp/EmployeeSeqHelper.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from emp.idl
* Sunday, June 16, 2002 6:43:16 PM IST
*/

abstract public class EmployeeSeqHelper
{
  private static String  _id = "IDL:emp/EmployeeSeq:1.0";

  public static void insert (org.omg.CORBA.Any a, emp.Employee[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static emp.Employee[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = emp.EmployeeHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (emp.EmployeeSeqHelper.id (), "EmployeeSeq", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static emp.Employee[] read (org.omg.CORBA.portable.InputStream istream)
  {
    emp.Employee value[] = null;
    int _len0 = istream.read_long ();
    value = new emp.Employee[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = emp.EmployeeHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, emp.Employee[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      emp.EmployeeHelper.write (ostream, value[_i0]);
  }

}
