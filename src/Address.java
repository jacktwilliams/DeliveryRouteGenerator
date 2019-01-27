
/*
 * XP Programming - Route Generation Project
 * CS410 - Software Engineering
 * Jack Williams & Chang Moua
 * 
 * Address class
 */

public class Address implements Comparable{
	private String name;
	private int streetIndex; //Refers to the ordering of the street this address sits on. 0 Would be furthest North or furthest West.
	private boolean vertical;

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
	
	public void setVertical(boolean v) {
		this.vertical = v;
	}
	
	public boolean getVertical() {
		return this.vertical;
	}
	
	//name will look like "100 Sometin sometin drive". We will splice out the house number to get the street only.
	public String getStreetName() {
		String[] data = this.name.split("\\s");
		String street = "";
		for(int i = 0; i < data.length; ++i) {
			if(i != 0) {
				street += data[i];
				if(i != data.length - 1) { // add space unless this is the last token
					street += " ";
				}
			}
			
		}
		return street;
	}

	//vertical first, then horizontal. Go from North to South or West to East
	@Override
	public int compareTo(Object o) {
		boolean oIsVertical = ((Address) o).getVertical();
		int oStreetIndex = ((Address) o).getStreetIndex();
		if(this.vertical && !oIsVertical) {
			return -1;
		}
		else if (!this.vertical && oIsVertical) {
			return 1;
		}
		else {
			if(this.streetIndex <= oStreetIndex) {
				return -1;
			}
			else {
				return 1;
			}
		}
	}
	
	public String toString() {
		return this.name;
	}
}
