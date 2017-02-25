package people.flightsearchproject.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import people.flightsearchproject.R;

/**
 * A class tells if the admin successfully edit the client.
 *
 */
public class AdminClientEidtSuccessfully extends AppCompatActivity {

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_client_eidt_successfully);

		Intent intent = getIntent();

	}
}
