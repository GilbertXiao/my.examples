package emp;


/**
* emp/ManagerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from emp.idl
* Wednesday, October 17, 2012 5:46:25 PM EDT
*/

abstract public class ManagerHelper
{
  private static String  _id = "IDL:emp/Manager:1.0";

  public static void insert (org.omg.CORBA.Any a, emp.Manager that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static emp.Manager extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (emp.ManagerHelper.id (), "Manager");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static emp.Manager read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ManagerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, emp.Manager value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static emp.Manager narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof emp.Manager)
      return (emp.Manager)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      emp._ManagerStub stub = new emp._ManagerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static emp.Manager unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof emp.Manager)
      return (emp.Manager)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      emp._ManagerStub stub = new emp._ManagerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
