import java.util.HashSet;
import java.util.LinkedList;


public class PathFinder {
	private LinkedList<Path> paths = new LinkedList<Path>();
	private LinkedList<City> cities;
	
	public LinkedList<City> optimumPath(LinkedList<City> cities) {
		this.cities = cities;

		//initialize routes. One route starting at each city. 
		for(int i = 0; i < cities.size(); ++i) {
			Path newPath = new Path();
			newPath.addStop(cities.get(i));
			paths.add(newPath);
		}
		
		while(addToPaths()); //add more routes to paths until we have generated them all
		
		//find shortest path
		Path shortest = new Path();
		shortest.distance = Double.MAX_VALUE;
		for(Path path : paths) {
			if (path.distance < shortest.distance) {
				shortest = path;
			}
		}
		
		System.out.println(shortest);
		return shortest.route;
	}
	
	@SuppressWarnings("unchecked")
	private boolean addToPaths() {
		boolean additions = false; //assume all possible routes have been generated.
		LinkedList<Path> newPaths = new LinkedList<Path>();
		
		for(Path path: paths) {
			if(!path.full) {
				boolean finished = true; //assume this path is complete (assume we have been to all cities).
				
				//find cities which we haven't traveled to yet.
				HashSet<City> remaining = new HashSet<City>(cities);
				remaining.removeAll(path.cities);
				
				/* For every city that hasn't been traveled to in this path, we will generate a new route in which that city is traveled to next.
				 * First we will use the path object we are looking at currently. In other words, we will add the city to this route.
				 * Next, we will have to clone this route in order to create an alternative route where we go to a different city next.
				 */
				boolean haveUsedFirstPath = false;
				for(City city: remaining) {
					finished = false; //path not complete
					additions = true; //all routes haven't been generated.
					if(!haveUsedFirstPath) { 
						path.addStop(city); //TODO: the first time around we are adding a city and then after that we are cloning the route with the first city already added and that seems erroneous. Don't want to lose this version of my code in case I am missing something since this code has been tested before.
						haveUsedFirstPath = true;
					}
					else {
						Path toAdd = path.clone();
						toAdd.addStop(city);
						newPaths.add(toAdd);
					}
				}
				path.full = finished;
			}
		}
		for(Path newP : newPaths) {
			paths.add(newP);
		}
		return additions;
	}
}