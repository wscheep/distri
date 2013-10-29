package rental;

import java.rmi.RemoteException;
import java.util.Collection;

public class ManagerSession implements ManagerSessionInterface {

	@Override
	public synchronized void registerRentalCompany(RentalInterface carRentalCompany) throws RemoteException {
		NamingService.registerCarRentalCompany(carRentalCompany);
		
	}

	@Override
	public synchronized void unRegisterRentalCompany(String carRentalCompanyName) {
		NamingService.unRegisterCarRentalCompany(carRentalCompanyName);
		
	}

	@Override
	public Collection<String> getCarRentalCompanies() {
		return NamingService.getCarRentalCompanies();
	}

	@Override
	public Collection<String> getCarTypesOf(String rentalCompany) throws RemoteException {
		RentalInterface company = NamingService.lookUp(rentalCompany);
		return company.getCarTypes();
		
	}

	@Override
	public int getNumberOfReservations(String rentalCompany, String carType) throws Exception {
		RentalInterface company = NamingService.lookUp(rentalCompany);
		return company.getNumberOfReservationsForCarType(carType);
	}

	@Override
	public String getMostPopularRentalCompany() throws RemoteException {
		String mostPopularCompany = "";
		int max = 0;
		for(String company: NamingService.getCarRentalCompanies()){
			int reservations = NamingService.lookUp(company).getTotalNumberOfReservations();
			if(reservations > max) {
				max = reservations;
				mostPopularCompany = company;
			}
		}
		return mostPopularCompany;
	}

	@Override
	public CarType getMostPopularCarType(String rentalCompany) throws RemoteException {
		return NamingService.lookUp(rentalCompany).getMostPopularCarType();
	}

}
