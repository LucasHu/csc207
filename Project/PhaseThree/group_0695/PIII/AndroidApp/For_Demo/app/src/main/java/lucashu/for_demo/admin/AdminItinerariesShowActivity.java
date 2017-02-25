package lucashu.for_demo.admin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import flight_itinerary_search.Flight;
import flight_itinerary_search.Itinerary;
import lucashu.for_demo.AdminBookItineraryActivity;
import lucashu.for_demo.BookItineryActivity;
import lucashu.for_demo.MainActivity;
import lucashu.for_demo.R;
import manager.ClientManager;
import manager.FlightManager;
import user.Admin;

public class AdminItinerariesShowActivity extends Activity {

    private FlightManager flightsManager;
    private ClientManager clientsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_itineraries_show);

        Intent intent = getIntent();

        readClientFlight();


//        flightsManager = (FlightManager) intent
//                .getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);
//        System.out.println("flightsmanager uploaded");
//
//
//        clientsManager = (ClientManager) intent
//                .getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
//        System.out.println("clientsmanager uploaded");

    }

    public void searchItineraries(View view)
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
        List<Itinerary> searchedItineraries;
        searchedItineraries = tempAdmin.searchedItinerariesListSerializable(date, origin,
                destination, this.flightsManager.getFlights());


        if (!searchedItineraries.isEmpty()) {

            ListView listView = (ListView) findViewById(R.id.listView_for_flights);

            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_for_list);


            for (Itinerary o : searchedItineraries) {

                adapter.add(o.returnItinerary());

            }

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                    String item = ((TextView) view).getText().toString();

                                                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

                                                    Intent intent = new Intent(getApplicationContext(), AdminBookItineraryActivity.class);

                                                    intent.putExtra(MainActivity.BOOKED_ITINERARY, getItinerary(item));
                                                    intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);
                                                    intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);

                                                    startActivity(intent);

                                                }

                                            }
            );

        } else {

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No Itineraries found :(", Toast.LENGTH_SHORT);
            bookedMsg.show();

        }

    }

    public void searchItinerariesByTime(View view)
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
        List<Itinerary> searchedItineraries;
        List<Itinerary> searchedAllItineraries;

        searchedAllItineraries = tempAdmin.searchedItinerariesListSerializable(date, origin,
                destination, this.flightsManager.getFlights());

        searchedItineraries = tempAdmin.sortItinerariesByTime(searchedAllItineraries);


        if (!searchedItineraries.isEmpty()) {

            ListView listView = (ListView) findViewById(R.id.listView_for_flights);

            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_for_list);


            for (Itinerary o : searchedItineraries) {

                adapter.add(o.returnItinerary());

            }

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                    String item = ((TextView) view).getText().toString();

                                                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

                                                    Intent intent = new Intent(getApplicationContext(), AdminBookItineraryActivity.class);

                                                    intent.putExtra(MainActivity.BOOKED_ITINERARY, getItinerary(item));
                                                    intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);
                                                    intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);

                                                    startActivity(intent);

                                                }

                                            }
            );

        } else {

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No Itineraries found :(", Toast.LENGTH_SHORT);
            bookedMsg.show();

        }

    }

    public void searchItinerariesByPrice(View view)
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
        List<Itinerary> searchedItineraries;
        List<Itinerary> searchedAllItineraries;

        searchedAllItineraries = tempAdmin.searchedItinerariesListSerializable(date, origin,
                destination, this.flightsManager.getFlights());

        searchedItineraries = tempAdmin.sortItinerariesByPrice(searchedAllItineraries);


        if (!searchedItineraries.isEmpty()) {

            ListView listView = (ListView) findViewById(R.id.listView_for_flights);

            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_for_list);


            for (Itinerary o : searchedItineraries) {

                adapter.add(o.returnItinerary());

            }

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                    String item = ((TextView) view).getText().toString();

                                                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

                                                    Intent intent = new Intent(getApplicationContext(), AdminBookItineraryActivity.class);

                                                    intent.putExtra(MainActivity.BOOKED_ITINERARY, getItinerary(item));
                                                    intent.putExtra(MainActivity.CLIENT_MANAGER_KEY, clientsManager);
                                                    intent.putExtra(MainActivity.FLIGHT_MANAGER_KEY, flightsManager);

                                                    startActivity(intent);

                                                }

                                            }
            );

        } else {

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No Itineraries found :(", Toast.LENGTH_SHORT);
            bookedMsg.show();

        }

    }

    //a helper function to get Itinerary
    private Itinerary getItinerary(String item) {

        String[] items = item.split("[\\r\\n]+");

        Itinerary selectedItinerary;

        String tempDate = "";

        String tempOrigin = "";

        String tempDestination = "";

        List<Flight> flightList = new ArrayList<Flight>();

        for (int i = 0; i < items.length; i ++) {

            //split by comma...
            List<String> tempItems = Arrays.asList(items[i].split(","));

            if (tempItems.size() == 6) {

                String flightnumber = tempItems.get(0);


                Flight tempFlight = flightsManager.getTheFlight(flightnumber);

                flightList.add(tempFlight);

                if (i == 0) {
                    tempDate = tempItems.get(1);
                    tempOrigin = tempItems.get(4);
                }

                if (i == items.length - 3) {
                    tempDestination = tempItems.get(5);

                }

            }
        }

        selectedItinerary = new Itinerary(tempDate, tempOrigin, tempDestination, flightList);

        return selectedItinerary;
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
