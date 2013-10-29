package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ManagerSessionInterface extends Remote {

	public void registerRentalCompany(RentalInterface carRentalCompany);
	public void unRegisterRentalCompany(String carRentalCompanyName);
	public Collection<String> getCarRentalCompanies(); 
	public Collection<String> getCarTypesOf(String rentalCompany);
	public int getNumberOfReservations(String rentalCompany, String carType) throws Exception;
	public String getMostPopularRentalCompany();
	public String getMostPopularCarType(String rentalCompany);

}
