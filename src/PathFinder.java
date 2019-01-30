import java.util.HashSet;
import java.util.LinkedList;


public class PathFinder {
	private LinkedList<Path> paths = new LinkedList<Path>();
	private LinkedList<City> cities;
	private boolean exactLocMode;
	
	public PathFinder(boolean exact) {
		this.exactLocMode = exact;
	}
	
	public LinkedList<City> optimumPath(LinkedList<City> cities) {
		this.cities = cities;

		//initialize routes. One route starting at each city. 
		for(int i = 0; i < cities.size(); ++i) {
			Path newPath = new Path(this.exactLocMode);
			newPath.addStop(cities.get(i));
			paths.add(newPath);
		}
		
		while(addToPaths()); //add more routes to paths until we have generated them all
		
		//find shortest path
		Path shortest = null;
		double shortestDistance = Double.MAX_VALUE;
		for(Path path : paths) {
			if (path.getDistance() < shortestDistance) {
				shortest = path;
				shortestDistance = path.getDistance();
			}
		}
		
		System.out.println("Shortest Path: " + shortest);
		return shortest.getRoute();
	}
	
	private boolean addToPaths() {
		boolean additions = false; //assume all possible routes have been generated.
		LinkedList<Path> newPaths = new LinkedList<Path>();
		
		for(Path path: paths) {
			//if path is not full (does not have all cities)
			if(!path.getFullPathP()) {
				boolean finished = true; //assume this path is complete (assume we have been to all cities).
				
				//find cities which we haven't traveled to yet.
				HashSet<City> remaining = new HashSet<City>(cities);
				remaining.removeAll(path.getCities());
				
				/* For every city that hasn't been traveled to in this path, we will generate a new route in which that city is traveled to next.
				 * First we will use the path object we are looking at currently. In other words, we will add the city to this route.
				 * Next, we will add to a clone of this route in order to create an alternative route where we go to a different city next.
				 */
				boolean haveUsedFirstPath = false;
				Path toModify = path.clone(); //since we will be modifying the original path by adding stops, we save the original here as a template to add alternative stops to
				for(City city: remaining) {
					finished = false; //path not complete
					additions = true; //all routes haven't been generated.
					if(!haveUsedFirstPath) { 
						path.addStop(city);
						haveUsedFirstPath = true;
					}
					else {
						Path toAdd = toModify.clone();
						toAdd.addStop(city);
						newPaths.add(toAdd);
					}
				}
				path.setFullPathP(finished); //we will know path is full when we add no more cities.
			}
		}
		for(Path newP : newPaths) {
			paths.add(newP);
		}
		return additions;
	}
}
