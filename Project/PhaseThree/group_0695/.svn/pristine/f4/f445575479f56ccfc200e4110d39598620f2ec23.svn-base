package people.flightsearchproject.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import people.flightsearchproject.R;

/**
 * A class represents AdminViewBookedItinerariesActivity.
 *
 */
public class AdminViewBookedItinerariesActivity extends AppCompatActivity {

	private String output;

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_view_booked_itineraries);

		Intent intent = getIntent();

		output = (String) intent.getSerializableExtra(
				AdminViewEditClientsActivity.VIEW_BOOKED_INTINERARIES);

		TextView newlyRegistered = (TextView) findViewById(
				R.id.admin_view_booked_itineraries_field);

		newlyRegistered.setText(output);

	}
}
