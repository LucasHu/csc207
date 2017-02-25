package people.flightsearchproject.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;

import java.io.IOException;
import java.util.List;

import flight_itinerary_search.Flight;
import manager.FlightManager;
import user.Admin;

/**
 * A class represents the AdminFlightsShowActivity.
 *
 */
public class AdminFlightsShowActivity extends Activity {

	private FlightManager flightManager;
	public static final String ADMINFLIGHTOUTPUT = "adminflightoutput";

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.admin_flight_show_activity);

		Intent intent = getIntent();

		flightManager = (FlightManager) intent
				.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);

	}

	/**
	 * Gets to the next activity by an onClick method. Search for flight.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 * @throws IOException
	 *             when there is an error while reading the file.
	 * @throws ClassNotFoundException
	 *             if the class is not found
	 */
	public void searchFlights(View view)
			throws IOException, ClassNotFoundException {
		// Gets the date from the 1st EditText field.
		EditText dateField = (EditText) findViewById(
				R.id.admin_search_flights_departure_date_field);
		String date = dateField.getText().toString();

		// Gets the origin from the 2nd EditText field.
		EditText originField = (EditText) findViewById(
				R.id.admin_search_flights_origin_field);
		String origin = originField.getText().toString();

		// Gets the destination from the 3rd EditText field.
		EditText destinationField = (EditText) findViewById(
				R.id.admin_search_flights_destination_field);
		String destination = destinationField.getText().toString();

		Admin tempAdmin = new Admin();
		List<Flight> searchedFlights = null;
		searchedFlights = tempAdmin.searchFlightsBySerializable(date, origin,
				destination, this.flightManager.getFlights());

		String adminFlightsOutput;

		if (!searchedFlights.isEmpty()) {

			String returnedList = "";

			for (Flight o : searchedFlights) {

				returnedList = returnedList + o.returnFlightWithTimeCost();

			}

			adminFlightsOutput = returnedList;

		} else {

			adminFlightsOutput = "No flights found!";

		}

		Intent intent = new Intent(this,
				AdminShowSearchedFlightsActivity.class);
		intent.putExtra(ADMINFLIGHTOUTPUT, adminFlightsOutput);

		startActivity(intent);

	}

}
