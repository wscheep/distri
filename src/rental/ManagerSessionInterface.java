package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ManagerSessionInterface extends Remote {

	public void registerRentalCompany(RentalInterface carRentalCompany) throws RemoteException;
	public void unRegisterRentalCompany(String carRentalCompanyName) throws RemoteException;
	public Collection<String> getCarRentalCompanies() throws RemoteException; 
	public Collection<String> getCarTypesOf(String rentalCompany) throws RemoteException;
	public int getNumberOfReservations(String rentalCompany, String carType) throws Exception;
	public String getMostPopularRentalCompany() throws RemoteException;
	public CarType getMostPopularCarType(String rentalCompany) throws RemoteException;

}
