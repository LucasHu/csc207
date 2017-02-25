package lucashu.for_demo.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import lucashu.for_demo.MainActivity;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import flight_itinerary_search.Flight;
import lucashu.for_demo.R;
import manager.ClientManager;
import manager.FlightManager;
import user.Admin;

/**
 * A class represents the AdminFlightsShowActivity.
 *
 */
public class AdminFlightsShowActivity extends Activity {

    private FlightManager flightsManager;
    private ClientManager clientsManager;


    /**
     * onCreate method is called once the activity is triggered.
     *
     * @param savedInstanceState
     *            is the saved instanced state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_flights_show);

        Intent intent = getIntent();

        readClientFlight();

    }

    /**
     * Gets to the next activity by an onClick method. Search for flight.
     *
     * @param view
     *            represents the parameter of an onClick method.
     * @throws IOException
     *             when there is an error while reading the file.
     * @throws ClassNotFoundException
     *             if the class is not found
     */
    public void searchFlights(View view)
            throws IOException, ClassNotFoundException {
        // Gets the date from the 1st EditText field.
        EditText dateField = (EditText) findViewById(
                R.id.departure_date);
        String date = dateField.getText().toString();

        // Gets the origin from the 2nd EditText field.
        EditText originField = (EditText) findViewById(
                R.id.origin);
        String origin = originField.getText().toString();

        // Gets the destination from the 3rd EditText field.
        EditText destinationField = (EditText) findViewById(
                R.id.destination);
        String destination = destinationField.getText().toString();

        Admin tempAdmin = new Admin();
        List<Flight> searchedFlights;
        searchedFlights = tempAdmin.searchFlightsBySerializable(date, origin,
                destination, this.flightsManager.getFlights());


        if (!searchedFlights.isEmpty()) {

            ListView listView = (ListView) findViewById(R.id.listView_for_flights);

            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_for_list);

            int i = 0;

            for (Flight o : searchedFlights) {

                adapter.add(o.returnFlightWithTimeCost());
                i = i + 1;

            }

            listView.setAdapter(adapter);


        } else {

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No flights found :(", Toast.LENGTH_SHORT);
            bookedMsg.show();

        }

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
