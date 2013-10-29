package rental;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

public class ReservationSession implements ReservationSessionInterface {

	
	private String renter;
	private ArrayList<Quote> currentQuotes;
	
	public ReservationSession(String renter){
		this.renter = renter;
		this.currentQuotes = new ArrayList<Quote>();
	}
	
	@Override
	public void createQuote(ReservationConstraints constraints, String companyName) throws RemoteException, ReservationException {
		RentalInterface company = NamingService.lookUp(companyName);
		currentQuotes.add(company.createQuote(constraints, renter));
		
	}

	@Override
	public Collection<Quote> getCurrentQuotes() {
		return new ArrayList<Quote>(currentQuotes);
	}

	@Override
	public void confirmQuotes() throws ReservationException, RemoteException {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		for(Quote quote: currentQuotes){
			RentalInterface company = NamingService.lookUp(quote.getRentalCompany());
			try {
				reservations.add(company.confirmQuote(quote));
			} catch (Exception e) {
				cancelReservations(reservations);
				e.printStackTrace();
			} 
		}
	}	
	
	private void cancelReservations(ArrayList<Reservation> reservations) throws ReservationException, RemoteException {
	       for(Reservation r: reservations) {
	         NamingService.lookUp(r.getRentalCompany()).cancelReservation(r);
	       }
	       throw new ReservationException("Canceled All Reservations.");
	   }

}
