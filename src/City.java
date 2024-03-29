import java.util.*;

/*
 * XP Programming - Route Generation Project
 * CS410 - Software Engineering
 * Jack Williams & Chang Moua
 * 
 * City class
 */

public class City {
	private LinkedList<Address> addresses = new LinkedList<Address>();	
	private LinkedList<Street> streets = new LinkedList<Street>();
	private int zipCode;
	private String name;
	private double longitude, latitude;
	
	public City(String y, int x) {
		this.name = y;
		this.zipCode = x;
	}
	
	public void addAddress(Address addy) {
		addresses.add(addy);
	}
	
	public void addStreet(Street st) {
		streets.add(st);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getZipAsString() {
		return String.valueOf(zipCode);
	}
	
	public int getZip() {
		return this.zipCode;
	}
	
	public void setLatAndLong(double latit, double longit) {
		this.longitude = longit;
		this.latitude = latit;
	}
	
	public double[] getLatAndLong() {
		double[] ret = {this.latitude, this.longitude};
		return ret;
	}
	
	public double getDistanceTo(City c) {
		double[] otherLoc = c.getLatAndLong();
		double otherLat = otherLoc[0], otherLong = otherLoc[1];
		return Math.sqrt(Math.pow((this.latitude - otherLat), 2) + Math.pow((this.longitude - otherLong), 2));
	}
	
	public double getDistanceUsingZipCodes(City c) {
		return Math.sqrt(Math.pow((this.zipCode - c.getZip()), 2) + Math.pow((this.zipCode - c.getZip()), 2));
	}
	
	@SuppressWarnings("unchecked")
	/* Sort our address list. Address implements Comparable.
	 * We will first travel North to South. Then West to East. 
	 */
	public void sortAddresses() throws Exception {
		String badAddresses = "";
		for(Address addr : this.addresses) {
			Street holdsAddr; // will be filled with the street that this address is on.
			Street addrStreetName = new Street(addr.getStreetName());
			try {
				holdsAddr = streets.get(streets.indexOf(addrStreetName));
				addr.setStreetIndex(holdsAddr.getOrder());
				addr.setVertical(holdsAddr.getVertical());
			} catch(Exception e) {
				badAddresses += "Address: \"" + addr + "\" does not sit upon a street defined in the Layout file." + 
						"We will place this address at the end of the route for this city.";
				addr.setStreetIndex(Integer.MAX_VALUE);
				addr.setVertical(false); //horizontals go last
			}
		}
		Collections.sort(addresses);
		
		if(!badAddresses.equals("")) { //if we caught any bad addresses
			throw new Exception(badAddresses);
		}
	}
	
	public String toString() {
		return this.name + ", " + this.zipCode;
	}
	
	public LinkedList<Address> getAddresses() {
		return this.addresses;
	}
	
	public String getAddressesAsString() {
		String result = "";
		for(Address addr : this.addresses) {
			result += "    " + addr.toString() + "\n"; //primitive formatting...
		}
		return result;
	}
	
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		else if(!(o instanceof City)) {
			return false;
		}
		return this.toString().equals(o.toString());
	}
	
	public int hashCode() {
		return this.toString().hashCode();
	}
}
