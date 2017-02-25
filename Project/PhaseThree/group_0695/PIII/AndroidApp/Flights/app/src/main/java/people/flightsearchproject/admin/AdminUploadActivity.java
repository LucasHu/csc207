package people.flightsearchproject.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import flight_itinerary_search.Flight;
import manager.ClientManager;
import manager.FlightManager;
import people.flightsearchproject.MainActivity;
import people.flightsearchproject.R;
import user.Client;
import user.DataHandler;


/**
 * A class represents AdminUploadActivity.
 *
 */
public class AdminUploadActivity extends Activity {

    public static final String PASSWORDSFILE = "passwords.txt";
    public static final String CLIENTSFILE = "clients.ser";
    public static final String FLIGHTSFILE = "flights.ser";
    public static final String DATABASEDIR = "database";
    public static final String CSV_DIR = "csv_uploads";
    
	/**
	 * onCreate method is called once the activity is triggered.
	 * 
	 * @param savedInstanceState
	 *            is the saved instanced state.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_upload_activity);
        Intent intent = getIntent();

    }
    
	/**
	 * Gets to the next activity by an onClick method. Uploads clients.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
    public void uploadClients(View view) throws ClassNotFoundException, IOException {

        EditText clientCSVPath = (EditText) findViewById(R.id.upload_client_information_text);
        String clientFilename = clientCSVPath.getText().toString();

        DataHandler clients = new DataHandler();
        ClientManager clientManager;


        //We get the CSV file that holds the modifications to upload
        File clientsCSVFile = new File(this.getApplicationContext().getFilesDir(),
                clientFilename);

        if (clientsCSVFile.exists()) {
            //We get the passwords file to change if update brings new users
            File passwords = new File(this.getApplicationContext().getFilesDir(), PASSWORDSFILE);

            //We open the database that holds the serializabled files
            File database = this.getApplicationContext().getDir(DATABASEDIR, MODE_PRIVATE);

            //We get the clients serializabled database
            File clientsFile = new File(database, CLIENTSFILE);

            //If clientsFile exists in database, we load the date to clientManger
            // otherwise, We create an empty clientManager.
            clientManager = new ClientManager(clientsFile);


            //Create a datahandler to help read client informtaion...
            DataHandler tempHandlerOpenClientFile = new DataHandler();

            try {
                tempHandlerOpenClientFile.openClientFile(clientsCSVFile.getPath());
            } catch (IOException e) {
            }

            List<Client> clientsFromFile = tempHandlerOpenClientFile.getClientsList();

            if (clientManager.getClients().isEmpty()) {
                for (Client c : clientsFromFile) {
                    clientManager.add(c);
                }
            } else {
                for (Client c : clientsFromFile) {
                    if (!clientManager.hasTheClient(c)) {
                        clientManager.add(c);
                    } else {
                        clientManager.remove(c.getEmail());
                        clientManager.add(c);
                    }
                }
            }
        } else {
            Toast bookedMsg = Toast.makeText(getApplicationContext(), "Error. :/", Toast.LENGTH_SHORT);
            bookedMsg.show();
        }




        //We check if the path is correct for the CSV file, and then get info from it
//        if (clientsCSVFile.exists()) {
//            try {
//                clients.openClientFile(clientsCSVFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Toast bookedMsg = Toast.makeText(getApplicationContext(), "Error. :/", Toast.LENGTH_SHORT);
//            bookedMsg.show();
//            return;
//        }
//
//        for(Client client : clients.getClientsList()) {
//            if(clientManager.getClients().containsKey(client.getEmail())) {
//                String email = client.getEmail();
//                String firstname = client.getFirstname();
//                String lastname = client.getLastName();
//                String credit_card = client.getCreditCardNumber();
//                String expiracy_date = client.getExpiryDate();
//                String address = client.getAddress();
//                clientManager.getClients().get(email).setFirstname(firstname);
//                clientManager.getClients().get(email).setLastName(lastname);
//                clientManager.getClients().get(email).setAddress(address);
//                clientManager.getClients().get(email).setCreditCardNumber(credit_card);
//                clientManager.getClients().get(email).setExpiryDate(expiracy_date);
//            }
//            else {
//                clientManager.add(client);
//                //Create password
//                String password = "" + client.getFirstname() + client.getLastName();
//                try {
//                    addClientPassword(passwords, client, password);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        //All file read, save.
//        clientManager.saveToFile();
//        Toast bookedMsg = Toast.makeText(getApplicationContext(), "Clients file updated ! :)", Toast.LENGTH_SHORT);
//        bookedMsg.show();
    }
    
    /**
     * Add new client a password.
     * @param passwords is the file
     * @param client is the client
     * @param password is the string of the password
     * @throws IOException is the system reports an error
     */
    public void addClientPassword(File passwords, Client client, String password) throws IOException {

        String aline;
        BufferedWriter out = null;
        try
        {
            FileWriter fstream = new FileWriter(passwords, true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write("\n" + "client," + client.getEmail() + "," + password);
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        finally
        {
            if(out != null) {
                out.close();
            }
        }

    }
    
	/**
	 * Gets to the next activity by an onClick method. Uploads flights.
	 * 
	 * @param view
	 *            represents the parameter of an onClick method.
	 */
    public void uploadFlights(View view) {

        EditText flightsCSVPath = (EditText) findViewById(R.id.upload_flight_information_text);
        String flightsFilename = flightsCSVPath.getText().toString();

        DataHandler flights = new DataHandler();
        FlightManager flightManager;

        //We get the CSV file that holds the modifications to upload
        File flightsCSVFile = new File(CSV_DIR, flightsFilename);

        //We open the database that holds the serializabled files
        File database = this.getApplicationContext().getDir(DATABASEDIR, MODE_PRIVATE);

        //We get the clients serializabled database
        File flightsFile = new File(database, FLIGHTSFILE);

        //We create a clientManager to save in database
        try {
            flightManager = new FlightManager(flightsFile);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //We check if the path is correct for the CSV file, and then get info from it
        if (flightsCSVFile.exists()) {
            try {
                flights.openClientFile(flightsCSVFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //TOAST de mauvais nom, vous avez mis .txt ?
        }

        for(Flight flight : flights.getFlightsList()) {
            if(flightManager.getFlights().contains(flight)) {
                String departureDateTime = flight.getDepartureDateTime();
                String arriveDateTime = flight.getArriveDateTime();
                String airline = flight.getAirline();
                String origin = flight.getOrigin();
                String destination = flight.getDestination();
                double cost = flight.getCost();
                int seats = flight.getSeats();
                flightManager.getTheFlight(flight.getFlightNumber()).setDepartureDateTime(departureDateTime);
                flightManager.getTheFlight(flight.getFlightNumber()).setArriveDateTime(arriveDateTime);
                flightManager.getTheFlight(flight.getFlightNumber()).setAirline(airline);
                flightManager.getTheFlight(flight.getFlightNumber()).setOrigin(origin);
                flightManager.getTheFlight(flight.getFlightNumber()).setDestination(destination);
                flightManager.getTheFlight(flight.getFlightNumber()).setCost(cost);
                flightManager.getTheFlight(flight.getFlightNumber()).setSeats(seats);
            }
            else {
                flightManager.add(flight);
            }
        }
        //All file read, save.
        flightManager.saveToFile();
        Toast bookedMsg = Toast.makeText(getApplicationContext(), "Flights file updated ! :)", Toast.LENGTH_SHORT);
        bookedMsg.show();
    }

}
