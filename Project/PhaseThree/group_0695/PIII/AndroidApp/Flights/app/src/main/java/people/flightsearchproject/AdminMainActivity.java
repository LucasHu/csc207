package people.flightsearchproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import manager.ClientManager;
import manager.FlightManager;
import people.flightsearchproject.admin.AdminEditFlights;
import people.flightsearchproject.admin.AdminItinerariesShowActivity;
import people.flightsearchproject.admin.AdminUploadActivity;
import people.flightsearchproject.admin.AdminViewEditClientsActivity;
import user.Admin;

/**
 * A class represents AdminMainActivity.
 */
public class AdminMainActivity extends Activity {

	private ClientManager clientManager;

	private FlightManager flightManager;

	private static Admin tempAdmin = new Admin();

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminactivity_main);

		Intent intent = getIntent();

		flightManager = (FlightManager) intent
				.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);

		clientManager = (ClientManager) intent
				.getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
	}

	/**
	 * Gets to the next activity by an onClick method. Searches for the flights.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void searchFlights(View view) {

		Intent intent = new Intent(this,
				people.flightsearchproject.admin.AdminFlightsShowActivity.class);

		intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightManager);
		intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientManager);

		startActivity(intent);

	}

	/**
	 * Gets to the next activity by an onClick method. Searches for itineraries.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void searchItineraries(View view) {

		Intent intent = new Intent(this, AdminItinerariesShowActivity.class);

		intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightManager);
		intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientManager);

		startActivity(intent);
	}

	/**
	 * Gets to the next activity by an onClick method. Upload files.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void upload(View view) {

		Intent intent = new Intent(this, AdminUploadActivity.class);
		startActivity(intent);

	}

	/**
	 * Gets to the next activity by an onClick method. View and edit the clients
	 * information.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void viewEditClients(View view) {

		Intent intent = new Intent(this, AdminViewEditClientsActivity.class);

		intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightManager);
		intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientManager);

		startActivity(intent);

	}

	/**
	 * Gets to the next activity by an onClick method. Edits the flight
	 * information.
	 * 
	 * @param view
	 *            view represents the parameter of an onClick method.
	 */
	public void editFlights(View view) {

		Intent intent = new Intent(this, AdminEditFlights.class);

		intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightManager);

		startActivity(intent);

	}

}
