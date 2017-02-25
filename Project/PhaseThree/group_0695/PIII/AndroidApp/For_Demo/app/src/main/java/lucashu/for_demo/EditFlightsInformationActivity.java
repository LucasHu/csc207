package lucashu.for_demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import flight_itinerary_search.Flight;
import manager.ClientManager;
import manager.FlightManager;

public class EditFlightsInformationActivity extends Activity {

    private FlightManager flightsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flights_information);

        Intent intent = getIntent();

        flightsManager = (FlightManager) intent
                .getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);
    }

    public void editFlightInformation(View view) {

        EditText flightnumberField = (EditText) findViewById(R.id.flight_number);
        String flightnumber = flightnumberField.getText().toString();

        EditText departureDateField = (EditText) findViewById(R.id.departure_date);
        String departureDate = departureDateField.getText().toString();

        EditText arriveDateField = (EditText) findViewById(R.id.arrive_date);
        String arriveDate = arriveDateField.getText().toString();

        EditText airlineCardField = (EditText) findViewById(R.id.airline);
        String airline = airlineCardField.getText().toString();

        EditText originDateField = (EditText) findViewById(R.id.origin);
        String origin = originDateField.getText().toString();

        EditText destinationField = (EditText) findViewById(R.id.destination);
        String destination = destinationField.getText().toString();

        EditText priceField = (EditText) findViewById(R.id.price);
        String price = priceField.getText().toString();

        EditText seatsNumberField = (EditText) findViewById(R.id.number_of_seats);
        String seatsNumber = seatsNumberField.getText().toString();


        if (!flightsManager.hasTheFlight(flightnumber)) {

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No such flight :(", Toast.LENGTH_SHORT);
            bookedMsg.show();

        } else {

            Flight tempFlight = new Flight(flightnumber, departureDate, arriveDate, airline, origin, destination, Double.parseDouble(price), Integer.parseInt(seatsNumber));

            flightsManager.removeFlight(flightnumber);

            flightsManager.add(tempFlight);

            flightsManager.saveToFile();

            Toast bookedMsg = Toast.makeText(getApplicationContext(), "Flights Information Updated :P", Toast.LENGTH_SHORT);
            bookedMsg.show();

        }

    }
}
