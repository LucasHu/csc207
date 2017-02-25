package people.flightsearchproject.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import people.flightsearchproject.R;

/**
 * A class represents EditClientInfoActivity.
 *
 */
public class EditClientInfoActivity extends AppCompatActivity {
	
	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client_info);
    }

}
