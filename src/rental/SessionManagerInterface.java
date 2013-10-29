package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SessionManagerInterface extends Remote {


	public ManagerSessionInterface getManagerSession() throws RemoteException;
	public ReservationSessionInterface getNewReservationSession(String renter) throws RemoteException;
	
}
