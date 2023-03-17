package OrderApp;


/**
* OrderApp/OrderOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Order.idl
* Friday, March 17, 2023 2:29:59 PM CDT
*/

public interface OrderOperations 
{
  String view_menu ();
  String place_order (String usrName, short friedChickenQuant, short colaQuant);
  String check_order_status (String usrName);
  String view_current_orders ();
  boolean login (String userName);
  void logout (String userName);
  void shutdown ();
} // interface OrderOperations
