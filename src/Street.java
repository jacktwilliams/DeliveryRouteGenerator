
/*
 * XP Programming - Route Generation Project
 * CS410 - Software Engineering
 * Jack Williams & Chang Moua
 * 
 * Street class
 */

public class Street {

	public boolean vertical;
	public String name;
	public int order;
	
	//Constructor
	public Street(String y, boolean x, int z) {
		this.vertical = x;
		this.name = y;
		this.order = z;
	}
}
