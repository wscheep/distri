package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import rental.CarType;
import rental.ManagerSessionInterface;
import rental.RentalInterface;
import rental.Reservation;
import rental.ReservationConstraints;
import rental.ReservationSessionInterface;
import rental.SessionManagerInterface;

public class Client extends AbstractScriptedTripTest<ReservationSessionInterface,ManagerSessionInterface> {

	public static void main(String[] args) throws Exception{
		Client client = new Client("trips");
		client.run();
	}
	
	public Client(String scriptFile) {
		super(scriptFile);
	}

	
	private SessionManagerInterface getSessionManager(){
		SessionManagerInterface sessionManager = null;
		try {
            Registry registry = LocateRegistry.getRegistry();
            sessionManager = (SessionManagerInterface) registry.lookup("RentalAgency");
            
            
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
		return sessionManager;
	}


	@Override
	protected ReservationSessionInterface getNewReservationSession(String name)
			throws Exception {
		return getSessionManager().getNewReservationSession(name);
	}


	@Override
	protected ManagerSessionInterface getNewManagerSession(String name)
			throws Exception {
		return getSessionManager().getManagerSession();
	}


	@Override
	protected void checkForAvailableCarTypes(
			ReservationSessionInterface session, Date start, Date end)
			throws Exception {
		
		for(CarType type: session.getAvailableCarTypes(start, end))
			System.out.println(type.getName());
	}


	@Override
	protected void addQuoteToSession(ReservationSessionInterface session,
			Date start, Date end, String carType, String carRentalName)
			throws Exception {
		session.createQuote(new ReservationConstraints(start, end, carType), carRentalName);		
	}


	@Override
	protected List<Reservation> confirmQuotes(
			ReservationSessionInterface session) throws Exception {
		return session.confirmQuotes();
	}


	@Override
	protected int getNumberOfReservationsBy(ManagerSessionInterface ms,
			String clientName) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	protected int getNumberOfReservationsForCarType(ManagerSessionInterface ms,
			String carRentalCompanyName, String carType) throws Exception {
		return ms.getNumberOfReservations(carRentalCompanyName, carType);
	}


	@Override
	protected String getMostPopularCarRentalCompany(ManagerSessionInterface ms)
			throws Exception {
		return ms.getMostPopularRentalCompany();
	}


	@Override
	protected CarType getMostPopularCarTypeIn(ManagerSessionInterface ms,
			String carRentalCompanyName) throws Exception {
		return ms.getMostPopularCarType(carRentalCompanyName);
	}
	



}
