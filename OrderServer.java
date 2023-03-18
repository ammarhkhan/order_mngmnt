import OrderApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import java.util.Properties;

class OrderStatus {
	String username;
	short friedChickenQuant;
	short colaQuant;
	short totalPrice;

	OrderStatus(String username, short friedChickenQuant, short colaQuant, short totalPrice){
		this.username = username;
		this.friedChickenQuant = friedChickenQuant;
		this.colaQuant = colaQuant;
		this.totalPrice = totalPrice;
	}

	public String getusername(){
		return username;
	}

	public short getfriedChickenQuant(){
		return friedChickenQuant;
	}

	public short getcolaQuant(){
		return colaQuant;
	}

	public short gettotalPrice(){
		return totalPrice;
	}
  
}

class OrderImpl extends OrderPOA {
  private ORB orb;
  private Map<String,OrderStatus> currentOrders = new HashMap<>();
  private List<String> usersLoggedIn = new ArrayList<>();
  private int chickenPrice = 5;
  private int colaPrice = 1;

  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  // implement view_menu() method
  public String view_menu() {
    return "\nMenu:\n\nFried Chicken: $5\nCola: $1";
  }

  // implement place_order() method
  public String place_order(String usrName, short friedChickenQuant, short colaQuant) {
    if(currentOrders.containsKey(usrName)){
      return "This user already has an active order";
    }
	  short totalPrice = (short) ((friedChickenQuant * chickenPrice) + (colaQuant * colaPrice));
	  OrderStatus order = new OrderStatus(usrName, friedChickenQuant, colaQuant, totalPrice);
	  currentOrders.put(usrName, order);
    return "Order placed";
  }

  // implement check_order_status() method
  public String check_order_status(String usrName) {
    if(currentOrders.containsKey(usrName)){
      OrderStatus currentOrder = currentOrders.get(usrName);
      return "Order Status:\n\nCustomer: " + usrName + "\nChicken Ordered: " + currentOrder.getfriedChickenQuant() + "\nCola Ordered: " + currentOrder.getcolaQuant() +
	    "\nTotal Price: " + currentOrder.gettotalPrice();
    }

    return "No orders exist for this user";
  }

  // implement view_current_orders() method
  public String view_current_orders() {
    StringBuilder str = new StringBuilder();
    str.append("Current Number of Orders: " + currentOrders.size());
    str.append("\nCurrent Orders: ");

    for (String userName : currentOrders.keySet()) {
      OrderStatus currentOrder = currentOrders.get(userName);
      str.append("\nUserName: " + userName);
      str.append("\n\tChicken Order: " + currentOrder.getfriedChickenQuant());
      str.append("\n\tCola Order: " + currentOrder.getcolaQuant());
      str.append("\n\tPrice: " + currentOrder.gettotalPrice());
    }

    return str.toString(); 
  }

  public boolean login(String userName) {
    if(usersLoggedIn.contains(userName)) {
      return false;
    }
    
    usersLoggedIn.add(userName);
    return true;
  }

  // implement check_order_status() method
  public void logout(String userName) {
    usersLoggedIn.remove(userName); 
  }
    
  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
  }
}

public class OrderServer {

  public static void main(String args[]) {
    try{
      // create and initialize the ORB
      ORB orb = ORB.init(args, null);

      // get reference to rootpoa & activate the POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // create servant and register it with the ORB
      OrderImpl orderImpl = new OrderImpl();
      orderImpl.setORB(orb); 

      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(orderImpl);
      Order href = OrderHelper.narrow(ref);
	  
      // get the root naming context
      // NameService invokes the name service
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      // Use NamingContextExt which is part of the Interoperable
      // Naming Service (INS) specification.
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // bind the Object Reference in Naming
      String name = "Order";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("OrderServer ready and waiting ...");

      // wait for invocations from clients
      orb.run();
    } 
	
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
	  
      System.out.println("OrderServer Exiting ...");
	
  }
}