package lucashu.for_demo.admin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import flight_itinerary_search.Flight;
import lucashu.for_demo.MainActivity;
import lucashu.for_demo.R;
import manager.ClientManager;
import manager.FlightManager;
import user.Client;
import user.DataHandler;


/**
 * A class represents AdminUploadActivity.
 */
public class AdminUploadActivity extends Activity {

    public static final String CLIENTSFILE = "clients.ser";
    public static final String FLIGHTSFILE = "flights.ser";
    public static final String DATABASEDIR = "database";
    public static final String CSV_DIR = "csv_uploads";

    ClientManager clientsManager;
    FlightManager flightsManager;
    //We get the passwords file to change if update brings new users
    //File passwords = new File(getApplicationContext().getFilesDir(), MainActivity.PASSWORDSFILE);
    //File path=new File(getFilesDir(),MainActivity.PASSWORDSFILE);
    //File passwords=new File(path,MainActivity.PASSWORDSFILE);

    //File passwords = new File(MainActivity. ,MainActivity.PASSWORDSFILE);

    //We define a map to track the information of usernmae and password...
    private Map<String, String> trackedUsernamePassword;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    /**
     * onCreate method is called once the activity is triggered.
     *
     * @param savedInstanceState is the saved instanced state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upload);
        Intent intent = getIntent();
        clientsManager = (ClientManager) intent
                .getSerializableExtra(MainActivity.CLIENT_MANAGER_KEY);
        flightsManager = (FlightManager) intent
                .getSerializableExtra(MainActivity.FLIGHT_MANAGER_KEY);

    }
    /**
     * Gets to the next activity by an onClick method. Uploads clients.
     *
     * @param view represents the parameter of an onClick method.
     */
    public void uploadClient(View view) {

        EditText clientCSVPath = (EditText) findViewById(R.id.upload_client_information_text);
        String clientFilename = clientCSVPath.getText().toString();


        //We get the CSV file that holds the modifications to upload
        File clientsCSVFile = new File(this.getApplicationContext().getFilesDir(),
                clientFilename);


        if (clientsCSVFile.exists()) {
            File newPasswords = new File(this.getApplicationContext().getFilesDir(), MainActivity.PASSWORDSFILE);

            //read passwords file information...
            DataHandler dh = new DataHandler();

            try {
                dh.openPasswordFile(newPasswords);
                this.trackedUsernamePassword = dh.getUsernamePasswordMap();
                //this.usernameType = dh.getUsernameTypeMap();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Create a datahandler to help read client informtaion...
            DataHandler tempHandlerOpenClientFile = new DataHandler();

            try {
                tempHandlerOpenClientFile.openClientFile(clientsCSVFile.getPath());
            } catch (IOException e) {
            }

            List<Client> clientsFromFile = tempHandlerOpenClientFile.getClientsList();


            //If clientsFile exists in database, we load the date to clientManger
            // otherwise, We create an empty clientManager.
            try {
                if (clientsManager.getClients().isEmpty()) {
                    for (Client c : clientsFromFile) {
                        clientsManager.add(c);
                        if (!ifPasswordFileHas(c.getEmail())) {
                            //add elient and email to password file.
                            addClientPassword(newPasswords, c, "new_client_password");
                        }
                    }
                } else {
                    for (Client c : clientsFromFile) {
                        if (!clientsManager.hasTheClient(c)) {
                            clientsManager.add(c);
                            if (!ifPasswordFileHas(c.getEmail())) {
                                //add elient and email to password file.
                                addClientPassword(newPasswords, c, "new_client_password");
                            }

                        } else {
                            clientsManager.remove(c.getEmail());
                            clientsManager.add(c);
                        }
                    }
                }

            } catch (IOException e) {
            }
            clientsManager.saveToFile();
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "Clients files got updated successfully :)", Toast.LENGTH_SHORT);
            bookedMsg.show();
        } else {
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No such client infomrtaion file can be found :(", Toast.LENGTH_SHORT);
            bookedMsg.show();
        }
    }

    //check if the password file has the client...
    private boolean ifPasswordFileHas(String email) {
        if (this.trackedUsernamePassword.containsKey(email)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add new client a password.
     *
     * @param passwords is the file
     * @param client    is the client
     * @param password  is the string of the password
     * @throws IOException is the system reports an error
     */
    private void addClientPassword(File passwords, Client client, String password) throws IOException {

        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(passwords, true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write("\n" + "client," + client.getEmail() + "," + password);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * Gets to the next activity by an onClick method. Uploads flights.
     *
     * @param view represents the parameter of an onClick method.
     */
    public void uploadFlight(View view) {

        EditText flightsCSVPath = (EditText) findViewById(R.id.upload_flight_information_text);
        String flightsFilename = flightsCSVPath.getText().toString();

        //We get the CSV file that holds the modifications to upload
        File flightsCSVFile = new File(this.getApplicationContext().getFilesDir(),
                flightsFilename);

        //Create a datahandler to help read client informtaion...
        DataHandler tempHandlerOpenFlightFile = new DataHandler();

        try {
            tempHandlerOpenFlightFile.openFlightFile(flightsCSVFile.getPath());
        } catch (IOException e) {
        }

        List<Flight> flightsFromFile = tempHandlerOpenFlightFile.getFlightsList();


        //We check if the path is correct for the CSV file, and then get info from it
        if (!flightsCSVFile.exists()) {
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "No such flight information file :(", Toast.LENGTH_SHORT);
            bookedMsg.show();
        } else {
            if (flightsManager.getFlights().isEmpty()) {
                for (Flight f: flightsFromFile){
                    flightsManager.add(f);
                }
            } else {
                for (Flight f: flightsFromFile){
                    if (!flightsManager.hasTheFlight(f.getFlightNumber())) {
                        flightsManager.add(f);
                    } else {
                        flightsManager.removeFlight(f.getFlightNumber());
                        flightsManager.add(f);
                    }

                }

            }

            flightsManager.saveToFile();
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "Flights file updated ! :)", Toast.LENGTH_SHORT);
            bookedMsg.show();

        }

    }

}


