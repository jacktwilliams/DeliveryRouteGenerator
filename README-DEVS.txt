Documentation for Developers

The application's main class in RouteGenDriver. The main method here starts up the GUI and a listener for that interface. When the Route Generator is started, RouteGenDriver's method startRouteGeneration is called. The rest of the functionality can be traced from here.

There are two main chokepoints of the application. We use a brute-force algorithm for finding the optimum route between cities. This algorithm has a growth-complexity of O(n!).

The other chokepoint is when the Java Collection class sorts our cities' addresses. This takes unreasonably long once you get close to around 100,000 addresses per city.

We use two external dependencies.

We use Appache's httpcomponents for doing http requests. http://hc.apache.org/
We use Google's GSON library for working with JSON web responses. https://github.com/google/gson

Exact dependency information can be found in pom.xml.


We also use Mapquest GeoCoding API for getting coordinates for our cities. https://developer.mapquest.com/documentation/geocoding-api/

