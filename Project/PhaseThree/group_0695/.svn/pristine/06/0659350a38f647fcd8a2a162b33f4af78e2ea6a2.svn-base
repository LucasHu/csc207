package people.flightsearchproject.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import flight_itinerary_search.Flight;
import manager.FlightManager;
import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;

/**
 * A class represents the AdminEditFLights Activity.
 *
 */
public class AdminEditFlights extends AppCompatActivity {

    private FlightManager flightmanager;
    
	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_edit_flights);

        Intent intent = getIntent();

        flightmanager = (FlightManager) intent.getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);

    }
    
	/**
	 * Gets to the next activity by an onClick method. 
	 * Edits the flight information.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
    public void editFlight(View view) {

        EditText Numberfield = (EditText) findViewById(R.id.Number_field);
        String number = Numberfield.getText().toString();

        EditText DepartureDateField = (EditText) findViewById(R.id.DepartureDate_field);
        String departureDate = DepartureDateField.getText().toString();

        EditText ArriveDateField = (EditText) findViewById(R.id.ArriveDate_field);
        String arriveDate = ArriveDateField.getText().toString();

        EditText AirLineField = (EditText) findViewById(R.id.AirLine_field);
        String airline = AirLineField.getText().toString();

        EditText OriginField = (EditText) findViewById(R.id.Origin_field);
        String origin = OriginField.getText().toString();

        EditText DestinationField = (EditText) findViewById(R.id.Destination_field);
        String destination = DestinationField.getText().toString();

        EditText PriceField = (EditText) findViewById(R.id.Price_field);
        String price = PriceField.getText().toString();
        double priceDouble = Double.parseDouble(price);

        EditText NumberSeatsField = (EditText) findViewById(R.id.NumSeats_field);
        String numseats = NumberSeatsField.getText().toString();
        int numseatsInt = Integer.parseInt(numseats);

        if (!flightmanager.hasTheFlight(number)) {

            Intent intent = new Intent(this, AdminNoSuchFlightActivity.class);
            startActivity(intent);

        } else {

            flightmanager.removeFlight(number);
            flightmanager.add(new Flight(number, departureDate, arriveDate, airline, origin, destination, priceDouble, numseatsInt));



        }

        flightmanager.saveToFile();

    }
}
