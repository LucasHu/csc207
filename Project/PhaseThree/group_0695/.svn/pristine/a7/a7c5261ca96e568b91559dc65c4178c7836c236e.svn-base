package people.flightsearchproject.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import flight_itinerary_search.Flight;
import flight_itinerary_search.ItinerariesSearch;
import flight_itinerary_search.Itinerary;
import manager.ClientManager;
import manager.FlightManager;
import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;

/**
 * A class represents SearchFlightsFormActivity.
 *
 */
public class SearchFlightsFormActivity extends AppCompatActivity {

	public static final String CLIENT_MANAGER_KEY = "clientManagerKey";
	public static final String FLIGHT_MANAGER_KEY = "flightManagerKey";
	public static final String DEPARTURE_DATE_KEY = "departureDateKey";
	public static final String DESTINATION_KEY = "destinationKey";
	public static final String ORIGIN_KEY = "originKey";

	private ClientManager clientsManager;
	private FlightManager flightsManager;
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
		setContentView(R.layout.activity_search_flights_form);

		Intent intent = getIntent();

		clientsManager = (ClientManager) intent
				.getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
		flightsManager = (FlightManager) intent
				.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);
	}

	/**
	 * Gets to the next activity by an onClick method. Search Flights.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void searchFlights(View view) {

		EditText originField = (EditText) findViewById(R.id.origin);
		String origin = originField.getText().toString();

		EditText destinationField = (EditText) findViewById(R.id.destination);
		String destination = destinationField.getText().toString();

		EditText departureDateField = (EditText) findViewById(
				R.id.departure_date);
		String departureDate = departureDateField.getText().toString();

		if (origin.length() != 0 && destination.length() != 0
				&& departureDate.length() != 0) {

			search = new ItinerariesSearch();
			results = search.findAllItineraries(departureDate, origin,
					destination, flightsManager.getFlights());
			flightsManager.setResults(results);
		}

		Intent intent = new Intent(this, SearchFlightsResultsActivity.class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
		intent.putExtra(DEPARTURE_DATE_KEY, departureDate);
		intent.putExtra(DESTINATION_KEY, destination);
		intent.putExtra(ORIGIN_KEY, origin);
		startActivity(intent);

	}

}
