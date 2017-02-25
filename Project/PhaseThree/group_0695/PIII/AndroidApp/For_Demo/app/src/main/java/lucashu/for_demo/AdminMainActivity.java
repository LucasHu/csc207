package lucashu.for_demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import flight_itinerary_search.Flight;
import lucashu.for_demo.admin.AdminFlightsShowActivity;
import lucashu.for_demo.admin.AdminItinerariesShowActivity;
import lucashu.for_demo.admin.AdminUploadActivity;
import lucashu.for_demo.admin.AdminViewEditClientsActivity;
import manager.ClientManager;
import manager.FlightManager;
import user.Admin;
import user.Client;

/**
 * A class represents AdminMainActivity.
 */
public class AdminMainActivity extends Activity {

    private ClientManager clientsManager;

    private FlightManager flightsManager;

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
        setContentView(R.layout.activity_admin_main);

        Intent intent = getIntent();

//        flightsManager = (FlightManager) intent
//                .getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);
//
//        clientsManager = (ClientManager) intent
//                .getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);

        readClientFlight();
    }

    /**
     * Gets to the next activity by an onClick method. Searches for the flights.
     *
     * @param view
     *            represents the parameter of an onClick method.
     */
    public void searchFlights(View view) {

        Intent intent = new Intent(this, AdminFlightsShowActivity.class);

        intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);
        intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);

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

        intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);
        intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);

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
        intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);
        intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);
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

        EditText usernameField = (EditText) findViewById(R.id.client_username);
        String username = usernameField.getText().toString();
        Client tempClient = clientsManager.getClients().get(username);

        if (tempClient == null) {

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "User not registered in database !", Toast.LENGTH_SHORT);
            bookedMsg.show();

        } else {

            Intent intent = new Intent(this, AdminViewEditClientsActivity.class);

            intent.putExtra(MainActivity.CURR_USER_KEY, username);
            intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);
            intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);

            startActivity(intent);
        }



    }

    /**
     * Gets to the next activity by an onClick method. Edits the flight
     * information.
     *
     * @param view
     *            view represents the parameter of an onClick method.
     */
    public void editFlights(View view) {

        Intent intent = new Intent(this, EditFlightsInformationActivity.class);

        intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);

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
