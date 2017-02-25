package lucashu.for_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import flight_itinerary_search.Flight;
import manager.ClientManager;
import manager.FlightManager;
import user.DataHandler;

/**
 * A class called MainActivity to trigger the whole app
 *
 */
public class MainActivity extends Activity {

    public static final String PASSWORDSFILE = "passwords.txt";
    public static final String ADMIN = "admin";
    public static final String CLIENT = "client";

    public static final String CLIENTSFILE = "clients.ser";
    public static final String FLIGHTSFILE = "flights.ser";
    public static final String DATABASEDIR = "database";
    public static final String CLIENT_MANAGER_KEY = "clientManagerKey";
    public static final String FLIGHT_MANAGER_KEY = "flightManagerKey";
    public static final String CURR_USER_KEY = "currentUserKey";
    public static final String BOOKED_ITINERARY = "bookedIineraryKey";

    private ClientManager clientsManager;
    private FlightManager flightsManager;

    private Map<String, String> usernamePassword;
    private Map<String, String> usernameType;

    private DataHandler dh = new DataHandler();

    /**
     * onCreate method is called once the activity is triggered.
     *
     * @param savedInstanceState
     *            is the saved instanced state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File passwords = new File(this.getApplicationContext().getFilesDir(), PASSWORDSFILE);

        try {
            dh.openPasswordFile(passwords);
            this.usernamePassword = dh.getUsernamePasswordMap();
            this.usernameType = dh.getUsernameTypeMap();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Logs into the next activity by an onClick method
     *
     * @param view
     *            represents a onClick method is called.
     */
    public void login(View view) {

        // Gets the username from the 1st EditText field.
        EditText usernameField = (EditText) findViewById(R.id.username_field);
        String username = usernameField.getText().toString();

        // Gets the password from the 2nd EditText field.
        EditText passwordField = (EditText) findViewById(R.id.password_field);
        String password = passwordField.getText().toString();

        if (this.usernamePassword.containsKey(username)) {
            if (this.usernamePassword.get(username).equals(password)) {
                System.out.println("Valid authentication");
                if (this.usernameType.get(username).equals(ADMIN)) {
                    Intent intent = new Intent(this, AdminMainActivity.class);
                    readClientFlight();
                    intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
                    intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
                    startActivity(intent);
                } else if (this.usernameType.get(username).equals(CLIENT)) {
                    readClientFlight();
                    String currentClient = username;
                    Intent intent = new Intent(this, ClientMainActivity.class);
                    intent.putExtra(CURR_USER_KEY, currentClient);
                    intent.putExtra(CLIENT_MANAGER_KEY, clientsManager);
                    intent.putExtra(FLIGHT_MANAGER_KEY, flightsManager);
                    startActivity(intent);
                } else {
                    Toast bookedMsg = Toast.makeText(getApplicationContext(), "Unknown type, neither admin or client !", Toast.LENGTH_SHORT);
                    bookedMsg.show();
                }
            } else {
                Toast bookedMsg = Toast.makeText(getApplicationContext(), "Wrong password !", Toast.LENGTH_SHORT);
                bookedMsg.show();
            }
        } else {
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "User not registered in database !", Toast.LENGTH_SHORT);
            bookedMsg.show();
        }

    }

    private void readClientFlight() {

        File database = this.getApplicationContext().getDir(DATABASEDIR,
                MODE_PRIVATE); //get path stored into File database...

        File flightsFile = new File(database, FLIGHTSFILE);
        File clientsFile = new File(database, CLIENTSFILE);

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