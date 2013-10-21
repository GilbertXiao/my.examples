package emp;


/**
* emp/_ManagerImplBase.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from emp.idl
* Sunday, June 16, 2002 6:43:16 PM IST
*/

public abstract class _ManagerImplBase extends org.omg.CORBA.portable.ObjectImpl
                implements emp.Manager, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors
  public _ManagerImplBase ()
  {
  }

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getEmployee", new java.lang.Integer (0));
    _methods.put ("getAll", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get (method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // emp/Manager/getEmployee
       {
         try {
           int empid = in.read_long ();
           emp.Employee __result = null;
           __result = this.getEmployee (empid);
           out = rh.createReply();
           emp.EmployeeHelper.write (out, __result);
         } catch (emp.EmployeeNotFound __ex) {
           out = rh.createExceptionReply ();
           emp.EmployeeNotFoundHelper.write (out, __ex);
         }
         break;
       }

       case 1:  // emp/Manager/getAll
       {
         emp.Employee __result[] = null;
         __result = this.getAll ();
         out = rh.createReply();
         emp.EmployeeSeqHelper.write (out, __result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:emp/Manager:1.0"};

  public String[] _ids ()
  {
    return __ids;
  }


} // class _ManagerImplBase