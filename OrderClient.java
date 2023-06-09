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
    boolean logInResult = orderImpl.login(userName);

    if(logInResult) {
      if (userName.equals("manager")) {
        System.out.println("Welcome Manager");
        while(userInput != 2) {
          System.out.println("\nEnter digits below to complete the corresponding actions: ");
          System.out.println(" 1: View Current Orders\n 2: Log Out");
          userInput = Integer.valueOf(getInput());

          switch(userInput) {
            case 1:
              System.out.println(orderImpl.view_current_orders());
              break;
            case 2:
              orderImpl.logout(userName);
              break;
          }
        }
      } else {
        orderImpl.login(userName);
        System.out.println("Welcome " + userName);
        customerLogic(userInput, userName);
      }
    } else {
      System.out.println( userName + " Already Logged In");
    }    
	}

  public static String getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		return s;
	}

  public static void placeOrder(String userName) throws IOException {
    System.out.println("\nHow much fried chicken do you want to order?");
    short chickenOrder = Short.parseShort(getInput());
    System.out.println("\nHow much cola do you want to order?");
    short colaOrder = Short.parseShort(getInput());
    System.out.println(orderImpl.place_order(userName, chickenOrder, colaOrder));
	}

  public static void customerLogic(int userInput, String userName) throws IOException {
    while(userInput != 4) {
      System.out.println("\nEnter digits below to complete the corresponding actions: ");
      System.out.println(" 1: View Menu\n 2: Place Order\n 3: Check Order Status\n 4: Log Out");
      userInput = Integer.valueOf(getInput());

      switch(userInput) {
        case 1:
          System.out.println(orderImpl.view_menu());
          break;
        case 2:
          placeOrder(userName);
          break;
        case 3: 
          System.out.println("\nWhat user order would you like to check?");
          String user = getInput();
          System.out.println(orderImpl.check_order_status(user));
          break;
        case 4: 
          orderImpl.logout(userName);
          break;
      }
    }
	}

}