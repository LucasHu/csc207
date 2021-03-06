package people.flightsearchproject.client;

import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import people.flightsearchproject.R;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;
import flight_itinerary_search.Flight;
import flight_itinerary_search.ItinerariesSearch;
import flight_itinerary_search.Itinerary;
import manager.ClientManager;
import manager.FlightManager;
import user.Client;
import user.User;

/**
 * A class represents SearchFlightsResultsActivity.
 *
 */
public class SearchFlightsResultsActivity extends ListActivity {

	public static final String CLIENT_MANAGER_KEY = "clientManagerKey";
	public static final String FLIGHT_MANAGER_KEY = "flightManagerKey";
	public static final String DEPARTURE_DATE_KEY = "departureDateKey";
	public static final String DESTINATION_KEY = "destinationKey";
	public static final String ORIGIN_KEY = "originKey";

	private ClientManager clientsManager;
	private FlightManager flightsManager;
	private String departureDate;
	private String origin;
	private String destination;

	private ItinerariesSearch search;
	private List<Itinerary> results;

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_flights_results);

		Intent intent = getIntent();

		clientsManager = (ClientManager) intent
				.getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
		flightsManager = (FlightManager) intent
				.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);
		departureDate = intent
				.getStringExtra(SearchFlightsFormActivity.DEPARTURE_DATE_KEY);
		origin = intent.getStringExtra(SearchFlightsFormActivity.ORIGIN_KEY);
		destination = intent
				.getStringExtra(SearchFlightsFormActivity.DESTINATION_KEY);

		results = flightsManager.getResults();
		int size = results.size();
		String[] stringArray = new String[size];

		int i;
		for (i = 0; i < size; i++) {
			stringArray[i] = results.get(i).returnItinerary();
			System.out.println(stringArray[i]);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getListView().getContext(), android.R.layout.simple_list_item_1,
				stringArray);
		getListView().setAdapter(adapter);
	}

	/**
	 * Gets to the next activity by an onClick method. Sorts Itineraries by
	 * cost.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void sortByPrice(View view) {

		try {
			sortItinerariesListByPrice(results);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		flightsManager.setResults(results);
		Intent intent = new Intent(this, SearchFlightsResultsActivity.class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
		intent.putExtra(DEPARTURE_DATE_KEY, departureDate);
		intent.putExtra(DESTINATION_KEY, destination);
		intent.putExtra(ORIGIN_KEY, origin);
		finish();
		startActivity(intent);

	}

	/**
	 * Gets to the next activity by an onClick method. Sorts Itineraries by
	 * time.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void sortByTime(View view) {
		try {
			sortItinerariesListByTime(results);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		flightsManager.setResults(results);
		Intent intent = new Intent(this, SearchFlightsResultsActivity.class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
		intent.putExtra(DEPARTURE_DATE_KEY, departureDate);
		intent.putExtra(DESTINATION_KEY, destination);
		intent.putExtra(ORIGIN_KEY, origin);
		finish();
		startActivity(intent);
	}

	/**
	 * Returns a list of itineraries sorted by price.
	 * 
	 * @param results
	 *            the search itineraries.
	 * @return a list of itineraries sorted by price.
	 * @throws ClassNotFoundException
	 *             if the class is not found.
	 * @throws IOException
	 *             when there is an error while reading the file.
	 */
	public List<Itinerary> sortItinerariesListByPrice(List<Itinerary> results)
			throws ClassNotFoundException, IOException {

		Collections.sort(results, new priceComparator());

		return results;

	}

	/**
	 * Returns a list of itineraries sorted by time.
	 * 
	 * @param results
	 *            the search itineraries.
	 * @return a list of itineraries sorted by time.
	 * @throws ClassNotFoundException
	 *             if the class is not found.
	 * @throws IOException
	 *             when there is an error while reading the file.
	 */
	public List<Itinerary> sortItinerariesListByTime(List<Itinerary> results)
			throws ClassNotFoundException, IOException {

		Collections.sort(results, new timeComparator());

		return results;
	}

	/**
	 * A timeComparator class helps to sort itineraries.
	 *
	 */
	public class timeComparator implements Comparator<Itinerary> {

		public int compare(Itinerary a, Itinerary b) {
			try {
				if (a.getTotalTime() < b.getTotalTime()) {
					return -1;
				} else if (a.getTotalTime() > b.getTotalTime()) {
					return 1;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return 0;
		}

	}

	/**
	 * A priceComparator class helps to sort itineraries by price.
	 *
	 */
	public class priceComparator implements Comparator<Itinerary> {

		public int compare(Itinerary a, Itinerary b) {
			if (a.getTotalCost() < b.getTotalCost()) {
				return -1;
			} else if (a.getTotalCost() > b.getTotalCost()) {
				return 1;
			}
			return 0;
		}

	}

	/**
	 * onLictItemClick method helps us to select booked itineraries.
	 * 
	 * @param l
	 * @param v
	 * @param position
	 * @param id
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Itinerary selected = results.get(position);
		flightsManager.setSelectedItinerary(selected);
		Intent intent = new Intent(this, SelectedFlightActivity.class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
		startActivity(intent);
	}
}