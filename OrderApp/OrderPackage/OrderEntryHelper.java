package OrderApp.OrderPackage;


/**
* OrderApp/OrderPackage/OrderEntryHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Order.idl
* Wednesday, March 15, 2023 1:04:58 AM CDT
*/

abstract public class OrderEntryHelper
{
  private static String  _id = "IDL:OrderApp/Order/OrderEntry:1.0";

  public static void insert (org.omg.CORBA.Any a, OrderApp.OrderPackage.OrderEntry that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static OrderApp.OrderPackage.OrderEntry extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [2];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "userName",
            _tcOf_members0,
            null);
          _tcOf_members0 = OrderApp.OrderPackage.OrderStatusHelper.type ();
          _members0[1] = new org.omg.CORBA.StructMember (
            "value",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (OrderApp.OrderPackage.OrderEntryHelper.id (), "OrderEntry", _members0);
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

  public static OrderApp.OrderPackage.OrderEntry read (org.omg.CORBA.portable.InputStream istream)
  {
    OrderApp.OrderPackage.OrderEntry value = new OrderApp.OrderPackage.OrderEntry ();
    value.userName = istream.read_string ();
    value.value = OrderApp.OrderPackage.OrderStatusHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, OrderApp.OrderPackage.OrderEntry value)
  {
    ostream.write_string (value.userName);
    OrderApp.OrderPackage.OrderStatusHelper.write (ostream, value.value);
  }

}
