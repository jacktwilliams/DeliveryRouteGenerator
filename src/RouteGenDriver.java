import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.*;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/*
 * XP Programming - Route Generation Project
 * CS410 - Software Engineering
 * 
 * Driver class - Main Class to run the program
 * 
 */


public class RouteGenDriver {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		
		LinkedList<City> cities = getCities();
		System.out.println(cities);
		
		getStreets(cities);
		fillCityLocations(cities);
		
		//got all data. Time to generate a route
		PathFinder finder = new PathFinder();
		finder.optimumPath(cities);
		
		for(City cit : cities) {
			cit.sortAddresses();
		}
	}

	//
	public static LinkedList<City> getCities() throws FileNotFoundException{
		Scanner scan = new Scanner(new File("Address.dat"));
		LinkedList<City> cities = new LinkedList<City>();
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] cityData = line.split(", ");
			City current = new City(cityData[1], Integer.parseInt(cityData[2]));
			int existingIndex = cities.indexOf(current); 
			boolean cityInList = existingIndex != -1;
			
			//if city already exists in the list, cities, then we will modify that instance
			if(cityInList) {
				current = cities.get(existingIndex);
			}
			
			current.addAddress(new Address(cityData[0]));
			
			//if not in list then put it there
			if(!cityInList) {
				cities.add(current);
			}
		}
		scan.close();
		return cities;
	}
	
	//reads Layout.dat. Gets street information and fills adds those streets into the appropriate City.
	public static void getStreets(LinkedList<City> cities) throws FileNotFoundException{
		Scanner read = new Scanner(new File("Layout.dat"));
		
		City current = null;
		boolean vertical = false;
		int order = -1;
		
		while(read.hasNextLine()) {
			String line = read.nextLine();
			if(line.indexOf("Horizontal Streets") != -1 || line.indexOf("Vertical Streets") != -1) { 
				//if this line starts a new list we figure if it is a vertical list and what city and initialize the order counter
				vertical = line.indexOf("Vertical Streets") != -1;
				String[] cityData = line.split(": ")[1].split(", ");
				current = cities.get(cities.indexOf(new City(cityData[0], Integer.parseInt(cityData[1]))));
				order = 1;
			}
			else{
				current.addStreet(new Street(line, vertical, order));
				++order;
			}
		}
		read.close();
	}
	
	//Sends request to Mapquest Geocoding API. Gets latitude and longitude for cities.
	public static void fillCityLocations(LinkedList<City> cities) throws ClientProtocolException, IOException {
		final String key = "jqAyJhhlsi2QQKdDQhPpM6vnVAnQcL9q";
		String addr = "http://www.mapquestapi.com/geocoding/v1/batch?key=" + key;
		
		//construct a full address for a get request. This is a batch request. We will request location data for all cities at once.
		//note that the number of cities must be less than 100 for a batch request. TODO?
		for(City cit : cities) {
			addr += "&location=" + cit.getName() + "," + cit.getZipAsString();
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(addr);
		//response handler that returns JSON.
		ResponseHandler<JsonObject> rh = new ResponseHandler<JsonObject>() {
		    @Override
		    public JsonObject handleResponse(final HttpResponse response) throws IOException {
		        StatusLine statusLine = response.getStatusLine();
		        HttpEntity entity = response.getEntity();
		        if (statusLine.getStatusCode() >= 300) {
		            throw new HttpResponseException(
		                    statusLine.getStatusCode(),
		                    statusLine.getReasonPhrase());
		        }
		        if (entity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }
		        Gson gson = new GsonBuilder().create();
		        ContentType contentType = ContentType.getOrDefault(entity);
		        Charset charset = contentType.getCharset();
		        Reader reader = new InputStreamReader(entity.getContent(), charset);
		        return gson.fromJson(reader, JsonObject.class);
		    }
		};
		JsonObject myjson = httpclient.execute(httpget, rh);
		Gson gson = new Gson();
		@SuppressWarnings("rawtypes")
		ArrayList results = gson.fromJson(myjson.get("results"), ArrayList.class); //one result for each city
		
		//for each result (and each city) parse out the latitude and longitude and fill in city properties.
		for(int i = 0; i < results.size(); ++i) {
			JsonObject result = gson.fromJson(gson.toJson(results.get(i)), JsonObject.class);
			JsonObject latlng = result.getAsJsonArray("locations").get(0).getAsJsonObject().get("latLng").getAsJsonObject();
			double latit = latlng.get("lat").getAsDouble();
			double longit = latlng.get("lng").getAsDouble();
			
			cities.get(i).setLatAndLong(latit, longit);
		}
	}
}
