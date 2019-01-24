import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * XP Programming - Route Generation Project
 * CS410 - Software Engineering
 * 
 * Driver class - Main Class to run the program
 * 
 */


public class RouteGenDriver {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		LinkedList<City> c = getCities();
		System.out.println(c);
		
		getStreets(c);
		
	}

	//getCities method
	public static LinkedList<City> getCities() throws FileNotFoundException{
		
		Scanner scan = new Scanner(new File("Address.dat"));
		
		LinkedList<City> cities = new LinkedList<City>();
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] cityData = line.split(", ");
			City current = new City(cityData[1], Integer.parseInt(cityData[2]));
			int existIndex = cities.indexOf(current); 
			boolean cityInList = existIndex != -1;
			if(cityInList) {
				current = cities.get(existIndex);
			}
			current.addAddress(new Address(cityData[0]));
			if(!cityInList) {
				cities.add(current);
			}
		}
		scan.close();
		return cities;
	}
	
	//getStreets method
	public static void getStreets(LinkedList<City> cities) throws FileNotFoundException{
		
		Scanner read = new Scanner(new File("Layout.dat"));
		
		City current = null;
		boolean vertical = false;
		int order = -1;
		
		while(read.hasNextLine()) {
			String line = read.nextLine();
			if(line.indexOf("Horizontal Streets") != -1 || line.indexOf("Vertical Streets") != -1) {
				vertical = line.indexOf("Vertical Streets") != -1;
				String[] cityData = line.split(": ")[1].split(", ");
				current = cities.get(cities.indexOf(new City(cityData[0], Integer.parseInt(cityData[1]))));
				order = 1;
			}
			else{
				current.addStreet(new Street(line, vertical, order));
				++order;
			}
			
		}
		read.close();
	}
	
}
