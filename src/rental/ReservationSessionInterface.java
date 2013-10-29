package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ReservationSessionInterface extends Remote {
	
	public void createQuote(ReservationConstraints constraints, String companyName) throws RemoteException, ReservationException;
	public Collection<Quote> getCurrentQuotes() throws RemoteException;
	public void confirmQuotes() throws ReservationException, RemoteException ;

}
