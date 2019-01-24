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
	
	//Constructor
	public City(String y, int x) {
		this.name = y;
		this.zipCode = x;
	}
	
	//addAddress method
	public void addAddress(Address addy) {
		addresses.add(addy);
	}
	
	//addStreet method
	public void addStreet(Street st) {
		street.add(st);
	}
	
	//equals method
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		else if(!(o instanceof City)) {
			return false;
		}
		return this.toString().equals(o.toString());
	}
	
	//toString method
	public String toString() {
		return this.name + ", " + this.zipCode;
	}
}
