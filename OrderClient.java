import OrderApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

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
        programLoop(orderImpl);

	    } catch (Exception e) {
        System.out.println("ERROR : " + e);
	      e.printStackTrace(System.out);
	    }
    }

	private static void programLoop(Order orderimpl) throws IOException {
		int userInput = 0;

    System.out.println("Enter username to log in: ");
		String userName = getInput();

    if(userName.equals("manager")){
      if(orderImpl.is_manager_logged_in()){
        System.out.println("Manager Already Logged In");
      } else {
        orderImpl.login(userName);
        System.out.println("Welcome Manager");
      }
      
    } else {
      orderImpl.login(userName);
      System.out.println("Welcome " + userName);
      customerLogic(userInput);

    }
    
	}

  public static String getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		return s;
	}

  public static void customerLogic(int userInput) throws IOException {
    while(userInput != 4) {
      System.out.println("\nEnter digits below to complete the corresponding actions: ");
      System.out.println(" 1: View Menu\n 2: Place Order\n 3: Check Order Status\n 4: Log Out");
      userInput = Integer.valueOf(getInput());

      switch(userInput) {
        case 1:
          System.out.println(orderImpl.view_menu());
          break;
        case 2:
          short a = 5;
          System.out.println(orderImpl.place_order("ammar", a, a));
          break;
        case 3: 
          System.out.println(orderImpl.check_order_status("ammar"));
          break;
        case 4: 
          orderImpl.shutdown();
          break;
      }
    }
	}

}