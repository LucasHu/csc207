package user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import flight_itinerary_search.Flight;

/**
 * A class used to manage the reading and writing into files.
 */

public class DataHandler {

	private static final String COMMA_DELIMITER = ",";
	private List<Flight> flightsList;
	private List<Client> clientsList;

	/**
	 * Constructor of the DataHandler class.
	 */
	public DataHandler() {
	}

	/**
	 * 
	 * Opens the file containing the list of all the flights, and reads all
	 * flight information into an ArrayList.
	 * 
	 * @param path
	 *            is the file containing the list of flights.
	 * @throws IOException
	 *             when an error occurs while manipulating the file.
	 */
	public void openFlightFile(String path) throws IOException {

		List<Flight> flightsList = new ArrayList<Flight>();

		String aline;

		FileReader file = new FileReader(path);

		BufferedReader bf = new BufferedReader(file);

		while ((aline = bf.readLine()) != null) {

			String[] items = aline.split(COMMA_DELIMITER);

			String flightnumber = items[0];

			String departureDateTime = items[1];

			String arriveDateTime = items[2];

			String airline = items[3];

			String origin = items[4];

			String destination = items[5];

			double cost = Double.parseDouble(items[6]);

			Flight tempFlight = new Flight(flightnumber, departureDateTime,
					arriveDateTime, airline, origin, destination, cost);

			flightsList.add(tempFlight);

		}

		this.flightsList = flightsList;

		bf.close();

	}

	/**
	 * 
	 * Opens the file containing the list of all the clients, and reads all
	 * client information into an ArrayList.
	 * 
	 * @param path
	 *            is the file containing the list of clients.
	 * @throws IOException
	 *             when an error occurs while manipulating the file.
	 */
	public void openClientFile(String path) throws IOException {

		List<Client> clientsList = new ArrayList<Client>();

		String aline;

		FileReader file = new FileReader(path);

		BufferedReader bf = new BufferedReader(file);

		while ((aline = bf.readLine()) != null) {

			String[] items = aline.split(COMMA_DELIMITER);

			String lastName = items[0];

			String firstname = items[1];

			String email = items[2];

			String address = items[3];

			String creditCardNumber = items[4];

			String expiryDate = items[5];

			Client tempClient = new Client(lastName, firstname, email, address,
					creditCardNumber, expiryDate);

			clientsList.add(tempClient);

		}

		this.clientsList = clientsList;

		bf.close();

	}

	/**
	 * 
	 * Returns the list of flights.
	 * 
	 * @return the list of flights.
	 */
	public List<Flight> getFlightsList() {

		return flightsList;

	}

	/**
	 * 
	 * Returns the list of clients.
	 * 
	 * @return the list of clients.
	 */
	public List<Client> getClientsList() {

		return clientsList;

	}

}
