package OrderApp.OrderPackage;


/**
* OrderApp/OrderPackage/CurrentOrdersHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Order.idl
* Wednesday, March 15, 2023 1:04:58 AM CDT
*/

public final class CurrentOrdersHolder implements org.omg.CORBA.portable.Streamable
{
  public OrderApp.OrderPackage.OrderEntry value[] = null;

  public CurrentOrdersHolder ()
  {
  }

  public CurrentOrdersHolder (OrderApp.OrderPackage.OrderEntry[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = OrderApp.OrderPackage.CurrentOrdersHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    OrderApp.OrderPackage.CurrentOrdersHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return OrderApp.OrderPackage.CurrentOrdersHelper.type ();
  }

}
