package people.flightsearchproject.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import people.flightsearchproject.R;

/**
 * A class reprents AdminViewClientActivity.
 *
 */
public class AdminViewClientActivity extends AppCompatActivity {

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
		setContentView(R.layout.activity_admin_view_client);

		Intent intent = getIntent();

		output = (String) intent
				.getSerializableExtra(AdminViewEditClientsActivity.VIEW_CLIENT);

		TextView newlyRegistered = (TextView) findViewById(
				R.id.client_output_field);
		newlyRegistered.setText(output);

	}
}
