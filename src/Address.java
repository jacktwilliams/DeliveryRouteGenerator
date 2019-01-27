
/*
 * XP Programming - Route Generation Project
 * CS410 - Software Engineering
 * Jack Williams & Chang Moua
 * 
 * Address class
 */

public class Address {
	private String name;
	private int streetIndex; //Refers to the ordering of the street this address sits on. 0 Would be furthest North or furthest West.

	//Constructor
	public Address(String s) {
		this.name = s;
	}
	
	public void setStreetIndex(int x) {
		this.streetIndex = x;
	}
	
	public int getStreetIndex() {
		return this.streetIndex;
	}
	
	
}
