package people.flightsearchproject.client;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import people.flightsearchproject.R;

import java.text.ParseException;
import java.util.List;

import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;
import flight_itinerary_search.Flight;
import flight_itinerary_search.ItinerariesSearch;
import flight_itinerary_search.Itinerary;
import manager.ClientManager;
import manager.FlightManager;
import user.Client;

/**
 * A class represents SelectedFlightActivity.
 *
 */
public class SelectedFlightActivity extends ListActivity {

    public static final String CLIENT_MANAGER_KEY = "clientManagerKey";
    public static final String TOAST_KEY= "toastKey";

    private ClientManager clientsManager;
    private FlightManager flightsManager;

    private Itinerary selected;
    
	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_flight);

        Intent intent = getIntent();

        clientsManager = (ClientManager) intent.getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
        flightsManager = (FlightManager) intent.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);

        this.selected = flightsManager.getSelectedItinerary();

        TextView totalPrice = (TextView) findViewById(R.id.totalCost);
        totalPrice.setText("TOTAL COST: " + this.selected.getTotalCost());

        TextView totalTime = (TextView) findViewById(R.id.totalTime);
        try {
            totalTime.setText("TOTAL TIME: " + this.selected.getTotalTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Generates the list of flights composing the itinerary
        int size = this.selected.getFlightsList().size();
        String[] stringArray = new String[size];

        int i;
        for(i=0; i<size; i++){
            stringArray[i] = this.selected.getFlightsList().get(i).toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, stringArray);
        getListView().setAdapter(adapter);

    }
    
	/**
	 * Gets to the next activity by an onClick method. Books the Itineraries.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
    public void book(View view){
        Client client = (Client) clientsManager.getUser();
        if (client.getCreditCardNumber().length() == 0 || client.getExpiryDate().length() == 0){
            //TODO incomplete billing info
        }
        else if(client.getExpiryDate() == "erg"){
            //TODO expired credit card
        }
        client.bookItinerary(this.selected);
        clientsManager.setUser(client);
        clientsManager.saveToFile();

        //TODO save on file flights for seats reason
        Intent intent = new Intent(this, BookingsActivity.class);
        intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
        intent.putExtra(TOAST_KEY, true);
        startActivity(intent);
    }
}
