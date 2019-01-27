
/*
 * XP Programming - Route Generation Project
 * CS410 - Software Engineering
 * Jack Williams & Chang Moua
 * 
 * Street class
 */

public class Street {

	private boolean vertical;
	private String name;
	private int order;
	
	//Constructor
	public Street(String y, boolean x, int z) {
		this.vertical = x;
		this.name = y;
		this.order = z;
	}
	
	public Street(String y) {
		this.name = y;
	}
	
	public boolean getVertical() {
		return this.vertical;
	}
	
	public int getOrder() {
		return this.order;
	}
	
	/* Overriding so we can find a street in the city's street list.
	 * An address has a street name, so we need to find a street based solely on the name;
	 */
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		else if(!(o instanceof Street)) {
			return false;
		}
		return this.toString().equals(o.toString());
	}
	
	public String toString() {
		return this.name;
	}
}
