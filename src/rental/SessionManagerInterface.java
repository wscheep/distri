package rental;

import java.rmi.Remote;

public interface SessionManagerInterface extends Remote {

		
	public ManagerSessionInterface getManagerSession();
	public ReservationSessionInterface getNewReservationSession(String renter);
	
}
