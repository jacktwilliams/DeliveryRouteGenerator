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
	private LinkedList<Street> street = new LinkedList<Street>();
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
		street.add(st);
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
	
	public String toString() {
		return this.name + ", " + this.zipCode;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getZipAsString() {
		return String.valueOf(zipCode);
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
}
