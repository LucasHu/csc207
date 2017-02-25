package lucashu.for_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import flight_itinerary_search.Flight;
import flight_itinerary_search.Itinerary;
import manager.ClientManager;
import manager.FlightManager;
import user.Client;

public class BookItineryActivity extends AppCompatActivity {

    private Itinerary bookedItinerary;
    private ClientManager clientsManager;
    private FlightManager flightsManager;
    private String currentClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_itinery);

        Intent intent = getIntent();

        bookedItinerary = (Itinerary) intent
                .getSerializableExtra(MainActivity.BOOKED_ITINERARY);

//        clientsManager = (ClientManager) intent
//                .getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
        readClientFlight();

        currentClient = (String) intent
                .getSerializableExtra(MainActivity.CURR_USER_KEY);


        ListView listView = (ListView) findViewById(R.id.selected_itinerary);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_for_list);

        adapter.add(bookedItinerary.returnItinerary());

        listView.setAdapter(adapter);

    }

    public void bookItinerary(View view) {

        Client currClient = clientsManager.getClients().get(currentClient);
        System.out.println("heelo i am here");
        System.out.println(currClient.getEmail());



        if ((!hasTheItinerary(currClient.getBookedItineraries(),bookedItinerary))  && ifBookableForItineraries(bookedItinerary)) {
            currClient.bookItinerary(bookedItinerary);
            clientsManager.remove(currentClient);
            clientsManager.add(currClient);
            clientsManager.saveToFile();
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "Itineraries booked successful", Toast.LENGTH_SHORT);
            bookedMsg.show();
        } else {
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "Can't book itinerary, check if the Itinerary is already booked for this client or if any flight is full", Toast.LENGTH_SHORT);
            bookedMsg.show();
        }
    }

    //a helper function to double check if the itinerary is bookable.
    private boolean ifBookableForItineraries(Itinerary thisItinerary) {

        for (Flight flight: thisItinerary.getFlightsList()) {
            if (flight.ifFullyBooked()) {
                return false;
            }
        }
        return true;
    }

    public void showBookedItineraries(View view) {

        if(clientsManager.hasTheClientByEmail(currentClient)) {

            Client currClient = clientsManager.getClients().get(currentClient);

            List<Itinerary> bookedItineraries = currClient.getBookedItineraries();

            if (bookedItineraries.size() == 0) {
                Toast bookedMsg = Toast.makeText(getApplicationContext(), "This client didn't book anything :(", Toast.LENGTH_SHORT);
                bookedMsg.show();
            } else {


                ListView listView = (ListView) findViewById(R.id.selected_itinerary);

                ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.textview_for_list);

                for (Itinerary itinerary: bookedItineraries) {
                    adapter.add(itinerary.returnItinerary());
                }

                listView.setAdapter(adapter);


            }

        } else {

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No such client :(", Toast.LENGTH_SHORT);
            bookedMsg.show();

        }
    }

    //a helper function to test if two Itineraries are equal...
    public boolean ifSameItinerary(Itinerary first,Itinerary second){

        if (!first.getDepartureDateTime().equals(second.getDepartureDateTime())) {
            return false;
        } else if (!first.getOrigin().equals(second.getOrigin())) {
            return false;
        } else if (!first.getDestination().equals(second.getDestination())) {
            return false;
        } else if (first.getFlightsList().size() != second.getFlightsList().size()){
            return false;
        } else {
            for (int i = 0; i < first.getFlightsList().size(); i ++) {
                if (!first.getFlightsList().get(i).getFlightNumber().equals(second.getFlightsList().get(i).getFlightNumber())) {
                    return false;
                }
            }
        }

        return true;

    }

    //a helper function to test if a Itinerary list has the target Itinerary...
    public boolean hasTheItinerary(List<Itinerary> itineraryList, Itinerary itinerary) {

        for (Itinerary i: itineraryList) {
            if (ifSameItinerary(i, itinerary)) {
                return true;
            }
        }
        return false;
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
