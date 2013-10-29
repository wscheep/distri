package rental;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SessionManager implements SessionManagerInterface{

	private ManagerSessionInterface managerSession;
	
	public SessionManager() throws RemoteException{
		ManagerSession manager = new ManagerSession();
		this.managerSession = (ManagerSessionInterface) UnicastRemoteObject.exportObject(manager, 0);
	}

	public ManagerSessionInterface getManagerSession(){
		return managerSession;
	}

	@Override
	public ReservationSessionInterface getNewReservationSession(String renter) throws RemoteException {
		
		ReservationSession session = new ReservationSession(renter);
		return (ReservationSessionInterface) UnicastRemoteObject.exportObject(session, 0);
	}
	
}
