package rental;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RentalAgencyServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setSecurityManager(null);
		
		try{
			SessionManager sessionManager = new SessionManager();
			SessionManagerInterface stub = (SessionManagerInterface) UnicastRemoteObject.exportObject(sessionManager, 0);
			Registry registry = LocateRegistry.getRegistry();
	        registry.bind("RentalAgency", stub);
	        System.out.println("The Rental Agency Server is ready!");
		}
		
		catch(Exception e){
			System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
			
		}

        
        // Bind the remote object's stub in the registry
        
	}

}
