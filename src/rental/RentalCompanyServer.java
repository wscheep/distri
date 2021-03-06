package rental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class RentalCompanyServer {

	public static void main(String[] args) throws ReservationException, NumberFormatException, IOException {
		try {
			
            addCompany("hertz.csv" , "Hertz");
            addCompany("dockx.csv" , "Dockx");

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
	}
	
	public static void addCompany(String file, String name) throws NumberFormatException, ReservationException, IOException, NotBoundException{
		List<Car> cars = loadData(file);
		
		CarRentalCompany obj = new CarRentalCompany(name, cars);
        RentalInterface stub = (RentalInterface) UnicastRemoteObject.exportObject(obj, 0);
        Registry registry = LocateRegistry.getRegistry();
        
        SessionManagerInterface sessionManager = (SessionManagerInterface) registry.lookup("RentalAgency");
        sessionManager.getManagerSession().registerRentalCompany(stub);
        
		
	}

	public static List<Car> loadData(String datafile)
			throws ReservationException, NumberFormatException, IOException {

		List<Car> cars = new LinkedList<Car>();

		int nextuid = 0;
		
		//open file
		BufferedReader in = new BufferedReader(new FileReader(datafile));
		//while next line exists
		while (in.ready()) {
			//read line
			String line = in.readLine();
			//if comment: skip
			if(line.startsWith("#"))
				continue;
			//tokenize on ,
			StringTokenizer csvReader = new StringTokenizer(line, ",");
			//create new car type from first 5 fields
			CarType type = new CarType(csvReader.nextToken(),
					Integer.parseInt(csvReader.nextToken()),
					Float.parseFloat(csvReader.nextToken()),
					Double.parseDouble(csvReader.nextToken()),
					Boolean.parseBoolean(csvReader.nextToken()));
			System.out.println(type);
			//create N new cars with given type, where N is the 5th field
			for(int i = Integer.parseInt(csvReader.nextToken());i>0;i--){
				cars.add(new Car(nextuid++, type));
			}
		}
		
		return cars;
	}
}