package etienne.flightfinderdotcom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayClientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_client_info);

        // Gets the Intent passed from MainActivity
        Intent intent = getIntent();
        // Grab the object that was associated with the key "clientInfoKey".
        // We know it was a String[].
        String[] clientInfo = (String[]) intent.getSerializableExtra("clientInfoKey");

        // Find the TextView for the newly_registered_field, and set
        // it to report who registered.
        TextView firstName = (TextView) findViewById(R.id.first_name_field);
        firstName.setText("Firstname: " + clientInfo[0].toString());

        TextView lastName = (TextView) findViewById(R.id.last_name_field);
        lastName.setText("Lastname: " + clientInfo[1].toString());

        TextView email = (TextView) findViewById(R.id.email_field);
        email.setText("Email: " + clientInfo[2].toString());

        TextView address = (TextView) findViewById(R.id.address_field);
        address.setText("Address: " + clientInfo[3].toString());

        TextView creditCardNumber = (TextView) findViewById(R.id.credit_card_number_field);
        creditCardNumber.setText("Credit card number: " + clientInfo[4].toString());

        TextView expiracyDate = (TextView) findViewById(R.id.expiracy_date_field);
        expiracyDate.setText("Expiracy date: " + clientInfo[5].toString());

    }

    public void editClientInfo(View view) {

        // Gets the first name from the 1st EditText field.
        EditText firstNameField = (EditText) findViewById(R.id.first_name_field);
        String firstName = firstNameField.getText().toString();

        // Gets the last name from the 2nd EditText field.
        EditText lastNameField = (EditText) findViewById(R.id.last_name_field);
        String lastName = lastNameField.getText().toString();

        // Gets the email
        EditText emailField = (EditText) findViewById(R.id.email_field);
        String email = emailField.getText().toString();

        // Gets the address
        EditText addressField = (EditText) findViewById(R.id.address_field);
        String address = addressField.getText().toString();

        // Gets the credit card number
        EditText creditCardNumberField = (EditText) findViewById(R.id.credit_card_number_field);
        String creditCardNumber = creditCardNumberField.getText().toString();

        // Gets the expiracy date
        EditText expiracyDateField = (EditText) findViewById(R.id.expiracy_date_field);
        String expiracyDate = expiracyDateField.getText().toString();

        String[] clientInfo = {firstName, lastName, email, address, creditCardNumber, expiracyDate};

        // Specifies the next Activity to move to: EditClientInfoActivity.
        Intent intent = new Intent(this, DisplayClientInfoActivity.class);

        // Attaches the name object to this intent, under the key "studentKey".
        intent.putExtra("clientInfoKey", clientInfo);

        // Starts ShowActivity.
        startActivity(intent);
    }
}
