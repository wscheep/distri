package rental;

import java.util.Collection;
import java.util.HashMap;

public class NamingService {

	
	private static HashMap<String, RentalInterface> carRentalCompanies = new HashMap<String, RentalInterface>();
	
	public static void registerCarRentalCompany(RentalInterface rentalC){
		carRentalCompanies.put(rentalC.getName(), rentalC);
	}
	
	public static void unRegisterCarRentalCompany(String rentalCompanyName){
		carRentalCompanies.remove(rentalCompanyName);
	}
	
	public static RentalInterface lookUp(String rentalCompanyName){
		return carRentalCompanies.get(rentalCompanyName);
	}

	public static Collection<String> getCarRentalCompanies() {
		return carRentalCompanies.keySet();
	}
}
