package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface ReservationSessionInterface extends Remote {
	
	public void createQuote(ReservationConstraints constraints, String companyName) throws RemoteException, ReservationException;
	public Collection<Quote> getCurrentQuotes() throws RemoteException;
	public List<Reservation> confirmQuotes() throws ReservationException, RemoteException ;
	public Collection<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException, ReservationException;
	
	

}
