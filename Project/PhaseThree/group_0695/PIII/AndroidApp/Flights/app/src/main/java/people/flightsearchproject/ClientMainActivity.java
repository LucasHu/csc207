package people.flightsearchproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import manager.ClientManager;
import manager.FlightManager;
import user.Client;
import user.User;

/**
 * A class represents the ClientmainActivity.
 *
 */
public class ClientMainActivity extends AppCompatActivity {

	public static final String CLIENT_MANAGER_KEY = "clientManagerKey";
	public static final String FLIGHT_MANAGER_KEY = "flightManagerKey";

	private ClientManager clientsManager;
	private FlightManager flightsManager;

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_main);

		Intent intent = getIntent();

		clientsManager = (ClientManager) intent
				.getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
		flightsManager = (FlightManager) intent
				.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);

		User user = clientsManager.getUser();
		Client client = (Client) user;
		System.out.println(client.getFirstname());

	}

	/**
	 * Gets to the next activity by an onClick method. Searches for the flights.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void searchFlights(View view) {

		Intent intent = new Intent(this,
				people.flightsearchproject.client.SearchFlightsFormActivity.
				class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
		startActivity(intent);
	}

	/**
	 * Gets to the next activity by an onClick method. Searches for the
	 * itineraries.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void searchItineraries(View view) {

		Intent intent = new Intent(this,
				people.flightsearchproject.client.SearchItinerariesFormActivity.
				class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
		startActivity(intent);
	}

	/**
	 * Gets to the next activity by an onClick method. Views booked itineraries.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void viewBookings(View view) {

		Intent intent = new Intent(this,
				people.flightsearchproject.client.BookingsActivity.class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		startActivity(intent);
	}

	/**
	 * Gets to the next activity by an onClick method. Views information of the
	 * client.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
	public void viewInfo(View view) {

		Intent intent = new Intent(this,
				people.flightsearchproject.client.ClientInfoActivity.class);
		intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
		startActivity(intent);
	}
}
