package people.flightsearchproject.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import people.flightsearchproject.R;

/**
 * A class represents SearchItinerariesFormActivity.
 *
 */
public class SearchItinerariesFormActivity extends AppCompatActivity {
	
	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_itineraries_form);
    }
    
	/**
	 * Gets to the next activity by an onClick method. Searches the Itineraries.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
    public void searchItineraries(View view) {

        EditText originField = (EditText) findViewById(R.id.origin);
        String origin = originField.getText().toString();

        EditText destinationField = (EditText) findViewById(R.id.destination);
        String destination = destinationField.getText().toString();

        EditText departureDateField = (EditText) findViewById(R.id.departure_date);
        String departureDate = departureDateField.getText().toString();

        Intent intent = new Intent(this, SearchItinerariesResultsActivity.class);
        startActivity(intent);

    }

}
