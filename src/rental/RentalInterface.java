package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface RentalInterface extends Remote{

	public Collection<CarType> getAvailableCarTypes(Date start, Date end) throws RemoteException, ReservationException;
	public Quote createQuote(ReservationConstraints constraint, String guest) throws RemoteException, ReservationException;
	public Reservation confirmQuote(Quote quote) throws RemoteException, ReservationException;
	public void cancelReservation(Reservation reservation) throws RemoteException, ReservationException;
	public List<Reservation> getReservationsBy(String clientName) throws RemoteException;
	public int getNumberOfReservationsForCarType(String carType)throws RemoteException;
	public String getName() throws RemoteException;
	public Collection<String> getCarTypes() throws RemoteException;
	public int getTotalNumberOfReservations() throws RemoteException;
	public CarType getMostPopularCarType() throws RemoteException;
}
		