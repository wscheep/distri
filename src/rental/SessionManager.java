package rental;

public class SessionManager implements SessionManagerInterface{

	private ManagerSessionInterface managerSession;
	
	public SessionManager(){
		this.managerSession = new ManagerSession();
	}

	
	public ManagerSessionInterface getManagerSession(){
		return managerSession;
	}

	@Override
	public ReservationSessionInterface getNewReservationSession(String renter) {
		return new ReservationSession(renter);
	}
	
}
