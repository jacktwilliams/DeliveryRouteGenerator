
import java.util.HashSet;
import java.util.LinkedList;

//TODO: many fields should be private.
public class Path {
	public LinkedList<City> route = new LinkedList<City>();
	public HashSet<City> cities = new HashSet<City>();
	public double distance = 0;
	public boolean full = false;
	
	public void addStop(City stop) {
		cities.add(stop);
		route.add(stop);
		if(route.size() >= 2) { //as long as we now have two cities
			distance += route.get(route.size() - 2).getDistanceTo(stop);
		}
	}
	
	public String toString() {
		String result = "";
		for(City cit: this.cities) {
			result = result + cit.getName() + " => ";
		}
		return result + "\n " + distance;
	}
	
	public Path clone() {
		Path copy = new Path();
		copy.distance = this.distance;
		copy.route = new LinkedList<City>(this.route);
		copy.full = this.full;
		return copy;
	}
}
