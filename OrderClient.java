import OrderApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class OrderClient
{
  static Order orderImpl;

  public static void main(String args[])
    {
      try{
        // create and initialize the ORB
		    ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
	      orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // resolve the Object Reference in Naming
        String name = "Order";
        orderImpl = OrderHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Obtained a handle on server object: " + orderImpl);
        System.out.println(orderImpl.view_menu());
		    short a = 5;
		    System.out.println(orderImpl.place_order("ammar", a, a));
		    System.out.println(orderImpl.check_order_status("ammar"));
		    System.out.println(orderImpl.view_current_orders());
        orderImpl.shutdown();

	    } catch (Exception e) {
        System.out.println("ERROR : " + e) ;
	      e.printStackTrace(System.out);
	    }
    }

	private void programLoop(Order orderimpl){
		int userInput = 0;

    while(userInput != 5) {

      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  String s = br.readLine();
    }

	}

}