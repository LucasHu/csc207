package people.flightsearchproject.admin;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;

import flight_itinerary_search.Flight;
import flight_itinerary_search.Itinerary;
import manager.ClientManager;
import manager.FlightManager;
import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;
import user.Admin;

/**
 * A class represents the AdminItinerariesShowActivity.
 *
 */
public class AdminItinerariesShowActivity extends ListActivity {

	private FlightManager flightManager;
	private ClientManager clientManager;

	private List<Itinerary> resultsList;

	Admin tempAdmin = new Admin();
	
	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_itineraries_show);

		Intent intent = getIntent();

		flightManager = (FlightManager) intent
				.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);
		clientManager = (ClientManager) intent
				.getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
	}

	/**
	 * Gets to the next activity by an onClick method. Search for itineraries.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 * @throws IOException
	 *             when there is an error while reading the file.
	 * @throws ClassNotFoundException
	 *             if the class is not found
	 */
	public void searchItineraries(View view)
			throws IOException, ClassNotFoundException {

		// Gets the date from the 1st EditText field.
		EditText dateField = (EditText) findViewById(
				R.id.admin_search_itinerary_date_field);
		String date = dateField.getText().toString();

		// Gets the origin from the 2nd EditText field.
		EditText originField = (EditText) findViewById(
				R.id.admin_search_itinerary_origin_field);
		String origin = originField.getText().toString();

		// Gets the destination from the 3rd EditText field.
		EditText destinationField = (EditText) findViewById(
				R.id.admin_search_itinerary_destination_field);
		String destination = destinationField.getText().toString();

		resultsList = tempAdmin.searchedItinerariesListSerializable(date,
				origin, destination, flightManager.getFlights());

		int size = resultsList.size();
		String[] stringArray = new String[size];

		int i;
		for (i = 0; i < size; i++) {
			stringArray[i] = resultsList.get(i).toString();
			// the toString is optional
			System.out.println(stringArray[i]); // debug
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getListView().getContext(), android.R.layout.simple_list_item_1,
				stringArray);
		getListView().setAdapter(adapter);

	}

	/**
	 * Gets to the next activity by an onClick method. Search for itineraries
	 * and sort by time.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 * @throws IOException
	 *             when there is an error while reading the file.
	 * @throws ClassNotFoundException
	 *             if the class is not found
	 */
	public void searchItinerariesByTime(View view)
			throws IOException, ClassNotFoundException {

		// Gets the date from the 1st EditText field.
		EditText dateField = (EditText) findViewById(
				R.id.admin_search_itinerary_date_field);
		String date = dateField.getText().toString();

		// Gets the origin from the 2nd EditText field.
		EditText originField = (EditText) findViewById(
				R.id.admin_search_itinerary_origin_field);
		String origin = originField.getText().toString();

		// Gets the destination from the 3rd EditText field.
		EditText destinationField = (EditText) findViewById(
				R.id.admin_search_itinerary_destination_field);
		String destination = destinationField.getText().toString();

	}

	/**
	 * Gets to the next activity by an onClick method. Search for itineraries
	 * and sort by cost.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 * @throws IOException
	 *             when there is an error while reading the file.
	 * @throws ClassNotFoundException
	 *             if the class is not found
	 */
	public void searchItinerariesByCost(View view)
			throws IOException, ClassNotFoundException {

		// Gets the date from the 1st EditText field.
		EditText dateField = (EditText) findViewById(
				R.id.admin_search_itinerary_date_field);
		String date = dateField.getText().toString();

		// Gets the origin from the 2nd EditText field.
		EditText originField = (EditText) findViewById(
				R.id.admin_search_itinerary_origin_field);
		String origin = originField.getText().toString();

		// Gets the destination from the 3rd EditText field.
		EditText destinationField = (EditText) findViewById(
				R.id.admin_search_itinerary_destination_field);
		String destination = destinationField.getText().toString();
	}

}
