package people.flightsearchproject.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import people.flightsearchproject.R;

import manager.ClientManager;
import people.flightsearchproject.MainActivity;
import user.Client;

/**
 * A class represents the AdminEditClientActivity.
 *
 */
public class AdminEditClientActivity extends AppCompatActivity {

	private ClientManager clientManager;
	private String email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_edit_client);

		Intent intent = getIntent();

		clientManager = (ClientManager) intent
				.getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
		email = (String) intent
				.getSerializableExtra(AdminViewEditClientsActivity.VIEW_EMAIL);

	}

	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
	public void editClient(View view) {

		EditText lastNameField = (EditText) findViewById(
				R.id.admin_edit_client__lastname_field);
		String lastname = lastNameField.getText().toString();

		EditText firstNameField = (EditText) findViewById(
				R.id.admin_edit_client__firstname_field);
		String firstname = firstNameField.getText().toString();

		EditText addressField = (EditText) findViewById(
				R.id.admin_edit_client__address_field);
		String address = addressField.getText().toString();

		EditText creditcardField = (EditText) findViewById(
				R.id.admin_edit_client__creditcardnumber_field);
		String creditcardnumber = creditcardField.getText().toString();

		EditText expirydateField = (EditText) findViewById(
				R.id.admin_edit_client__expirydate_field);
		String expirydate = expirydateField.getText().toString();

		Client tempClient = new Client(email, lastname, firstname, address,
				creditcardnumber, expirydate);

		clientManager.update(tempClient);

		clientManager.saveToFile();

		Intent intent = new Intent(this, AdminClientEidtSuccessfully.class);

		startActivity(intent);

	}
}
