package people.flightsearchproject.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import people.flightsearchproject.R;

/**
 * A class represents SearchItinerariesResultsActivity
 *
 */
public class SearchItinerariesResultsActivity extends AppCompatActivity {
	
	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_itineraries_results);
    }

}
