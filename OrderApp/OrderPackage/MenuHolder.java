package OrderApp.OrderPackage;

/**
* OrderApp/OrderPackage/MenuHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Order.idl
* Wednesday, March 15, 2023 12:27:39 AM CDT
*/

public final class MenuHolder implements org.omg.CORBA.portable.Streamable
{
  public OrderApp.OrderPackage.Menu value = null;

  public MenuHolder ()
  {
  }

  public MenuHolder (OrderApp.OrderPackage.Menu initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OrderApp.OrderPackage.MenuHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OrderApp.OrderPackage.MenuHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OrderApp.OrderPackage.MenuHelper.type ();
  }

}
