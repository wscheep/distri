package rental;

import java.rmi.RemoteException;
import java.util.Collection;

public class ManagerSession implements ManagerSessionInterface {

	@Override
	public void registerRentalCompany(RentalInterface carRentalCompany) {
		NamingService.registerCarRentalCompany(carRentalCompany);
		
	}

	@Override
	public void unRegisterRentalCompany(String carRentalCompanyName) {
		NamingService.unRegisterCarRentalCompany(carRentalCompanyName);
		
	}

	@Override
	public Collection<String> getCarRentalCompanies() {
		return NamingService.getCarRentalCompanies();
	}

	@Override
	public Collection<String> getCarTypesOf(String rentalCompany) {
		RentalInterface company = NamingService.lookUp(rentalCompany);
		return company.getCarTypes();
		
	}

	@Override
	public int getNumberOfReservations(String rentalCompany, String carType) throws Exception {
		RentalInterface company = NamingService.lookUp(rentalCompany);
		return company.getNumberOfReservationsForCarType(carType);
	}

	@Override
	public String getMostPopularRentalCompany() {
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
	public String getMostPopularCarType(String rentalCompany) {
		return NamingService.lookUp(rentalCompany).getMostPopularCarType();
	}

}
