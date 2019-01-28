
import java.util.HashSet;
import java.util.LinkedList;

//TODO: many fields should be private.
public class Path {
	private LinkedList<City> route = new LinkedList<City>();
	private HashSet<City> cities = new HashSet<City>();
	private double distance = 0;
	private boolean full = false;
	private boolean exactLocation;
	
	public Path(boolean exact) {
		this.exactLocation = exact;
	}
	
	public void addStop(City stop) {
		cities.add(stop);
		route.add(stop);
		if(route.size() >= 2) { //as long as we now have two cities
			if(this.exactLocation) {
				distance += route.get(route.size() - 2).getDistanceTo(stop);
			}
			else { //approximate distance based on zip codes
				distance += Math.abs(route.get(route.size() - 2).getZip() - stop.getZip());
			}
		}
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public boolean getFullPathP () { //full path predicate
		return this.full;
	}
	
	public void setFullPathP (boolean f) {
		this.full = f;
	}
	
	public LinkedList<City> getRoute() {
		return this.route;
	}
	
	public HashSet<City> getCities() {
		return this.cities;
	}
	
	public String toString() {
		String result = "";
		for(City cit: this.route) {
			result = result + cit.getName() + " => ";
		}
		return result + distance + "\n";
	}
	
	@SuppressWarnings("unchecked")
	public Path clone() {
		Path copy = new Path(this.exactLocation);
		copy.distance = this.distance;
		copy.route = (LinkedList<City>) this.route.clone();
		copy.cities = (HashSet<City>) this.cities.clone();
		copy.full = this.full;
		return copy;
	}
}
