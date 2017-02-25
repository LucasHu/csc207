package lucashu.for_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import manager.ClientManager;
import user.Client;

public class EditClientInformationActivity extends Activity {

    private ClientManager clientsManager;
    private String currentClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_information);

        Intent intent = getIntent();

        clientsManager = (ClientManager) intent
                .getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);

        currentClient = (String) intent
                .getSerializableExtra(MainActivity.CURR_USER_KEY);

    }

    public void editClientInformation(View view) {

        EditText firstNameField = (EditText) findViewById(R.id.first_name);
        String firstName = firstNameField.getText().toString();

        EditText lastNameField = (EditText) findViewById(R.id.last_name);
        String lastName = lastNameField.getText().toString();

        EditText addressField = (EditText) findViewById(R.id.address);
        String address = addressField.getText().toString();

        EditText creditCardField = (EditText) findViewById(R.id.credit_card);
        String creditCard = creditCardField.getText().toString();

        EditText expiryDateField = (EditText) findViewById(R.id.expiry_date);
        String expiryDate = expiryDateField.getText().toString();

        Client tempClient = new Client(lastName, firstName, currentClient, address, creditCard, expiryDate);

        clientsManager.remove(currentClient);
        clientsManager.add(tempClient);
        //System.out.print(clientsManager.getClients().get(currentClient).getAddress());
        clientsManager.saveToFile();

        Toast bookedMsg = Toast.makeText(getApplicationContext(), "Client Information Editted :P", Toast.LENGTH_SHORT);
        bookedMsg.show();

    }


}
