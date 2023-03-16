package OrderApp.OrderPackage;


/**
* OrderApp/OrderPackage/OrderStatus.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Order.idl
* Wednesday, March 15, 2023 1:04:58 AM CDT
*/

public final class OrderStatus implements org.omg.CORBA.portable.IDLEntity
{
  public String userName = null;
  public short numFriedChicken = (short)0;
  public short numCola = (short)0;
  public short orderTotal = (short)0;

  public OrderStatus ()
  {
  } // ctor

  public OrderStatus (String _userName, short _numFriedChicken, short _numCola, short _orderTotal)
  {
    userName = _userName;
    numFriedChicken = _numFriedChicken;
    numCola = _numCola;
    orderTotal = _orderTotal;
  } // ctor

} // class OrderStatus
