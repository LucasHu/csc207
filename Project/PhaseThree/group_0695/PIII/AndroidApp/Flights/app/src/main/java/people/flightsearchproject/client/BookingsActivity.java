package people.flightsearchproject.client;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;
import flight_itinerary_search.Itinerary;
import manager.ClientManager;
import manager.FlightManager;
import user.Client;

/**
 * A class represents BookingsActivity.
 *
 */
public class BookingsActivity extends ListActivity {

	private ClientManager clientsManager;
	private List<Itinerary> bookings;

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookings);

		File database = this.getApplicationContext().getDir(
				people.flightsearchproject.MainActivity.DATABASEDIR,
				MODE_PRIVATE);
		File clientsFile = new File(database,
				people.flightsearchproject.MainActivity.CLIENTSFILE);

		try {
			clientsManager = new ClientManager(clientsFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Intent intent = getIntent();
		boolean toast = intent.getBooleanExtra(SelectedFlightActivity.TOAST_KEY,
				false);

		if (toast) {
			Toast bookedMsg = Toast.makeText(getApplicationContext(),
					"This itinerary has been booked ! :)", Toast.LENGTH_SHORT);
			bookedMsg.show();
		}

		// Populate a list from the intent
		Client client = (Client) clientsManager.getUser();
		System.out.println(client.getFirstname());
		System.out.println(client.getBookedItineraries());
		if (client.getBookedItineraries() != null) {
			this.bookings = client.getBookedItineraries();
			int size = this.bookings.size();
			String[] stringArray = new String[size];

			int i;
			for (i = 0; i < size; i++) {
				stringArray[i] = bookings.get(i).returnItinerary();
				System.out.println(stringArray[i]);
			}

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getListView().getContext(),
					android.R.layout.simple_list_item_1, stringArray);
			getListView().setAdapter(adapter);
		} else {
			TextView noBookings = (TextView) findViewById(R.id.no_bookings);
			noBookings.setText("No bookings registered yet.");
		}
	}
}
