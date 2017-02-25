package lucashu.for_demo.client;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import flight_itinerary_search.Flight;
import flight_itinerary_search.Itinerary;
import lucashu.for_demo.AdminMainActivity;
import lucashu.for_demo.EditClientInformationActivity;
import lucashu.for_demo.MainActivity;
import lucashu.for_demo.R;
import manager.ClientManager;
import manager.FlightManager;
import user.Client;

public class ClientInformationShowActivity extends Activity {

    private ClientManager clientsManager;
    private FlightManager flightsManager;
    private String currentClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_information_show);

        Intent intent = getIntent();

        clientsManager = (ClientManager) intent
                .getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);

        currentClient = (String) intent
                .getSerializableExtra(MainActivity.CURR_USER_KEY);


    }

    public void showPersonalInformation(View view) {
        readClientFlight();

        Client tempClient = clientsManager.getClients().get(currentClient);


        ListView listView = (ListView) findViewById(R.id.listView_for_client_information);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_for_list);

        adapter.add(tempClient.getFirstname());
        adapter.add(tempClient.getLastName());
        adapter.add(tempClient.getAddress());
        adapter.add(tempClient.getEmail());
        adapter.add(tempClient.getCreditCardNumber());
        adapter.add(tempClient.getExpiryDate());

        listView.setAdapter(adapter);

    }

    public void editPersonalInformation(View view) {
        Intent intent = new Intent(this, EditClientInformationActivity.class);
        intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);
        intent.putExtra(MainActivity.CURR_USER_KEY, currentClient);
        startActivity(intent);
    }

    private void readClientFlight() {

        File database = this.getApplicationContext().getDir(MainActivity.DATABASEDIR,
                MODE_PRIVATE); //get path stored into File database...

        File flightsFile = new File(database, MainActivity.FLIGHTSFILE);
        File clientsFile = new File(database, MainActivity.CLIENTSFILE);

        try {
            clientsManager = new ClientManager(clientsFile);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            flightsManager = new FlightManager(flightsFile);
            if (flightsManager.getFlights().size() == 0) {
                System.out.println("DIDNT STORE FLIGHT INFOR");
            }
            for (Flight f: flightsManager.getFlights()) {
                String s = f.getFlightNumber();
                System.out.println(s);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
