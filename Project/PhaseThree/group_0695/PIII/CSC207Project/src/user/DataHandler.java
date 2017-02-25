package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flight_itinerary_search.Flight;

/**
 * A class used to manage the reading and writing into files.
 */

public class DataHandler {

	private static final String COMMA_DELIMITER = ",";
	private List<Flight> flightsList;
	private List<Client> clientsList;
	private Map<String, String> usernamePassword;
	private Map<String, String> usernameType;

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

			int seats = Integer.parseInt(items[7]);

			Flight tempFlight = new Flight(flightnumber, departureDateTime,
					arriveDateTime, airline, origin, destination, cost, seats);

			if (flightsList.isEmpty()) {

				flightsList.add(tempFlight);

				this.flightsList = flightsList;

			} else {

				if (!(hasTheFlight(tempFlight, this.flightsList))) {

					this.flightsList.add(tempFlight);

				} else {

					// overwrite the client if the flight is already there.
					for (Flight flight : this.flightsList) {

						if (flight.getFlightNumber() == flightnumber) {

							this.flightsList.remove(flight);

							this.flightsList.add(tempFlight);
						}

					}

				}

			}

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

			if (clientsList.isEmpty()) {

				clientsList.add(tempClient);

				this.clientsList = clientsList;

			} else {

				if (!(hasTheClient(tempClient, this.clientsList))) {

					this.clientsList.add(tempClient);

				} else {

					// overwrite the client if the client is already there.
					for (Client client : this.clientsList) {

						if (client.getEmail() == email) {

							this.clientsList.remove(client);

							this.clientsList.add(tempClient);
						}

					}

				}

			}

		}

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

	/**
	 * Tests if the clientslist has the new client.
	 * 
	 * @param client
	 *            is the client.
	 * @param clientslist
	 *            is the clients list.
	 * @return true if the the clientslist has the new client, otherwise, false.
	 */
	private boolean hasTheClient(Client client, List<Client> clientslist) {

		for (Client tempClient : clientslist) {

			if (tempClient.getEmail() == client.getEmail()) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Tests if the flightlist has the flight.
	 * 
	 * @param flight
	 *            is the flight.
	 * @param flightsList
	 *            is the clights list.
	 * @return true, if if the flightlist has the flight, otherwise, false.
	 */
	private boolean hasTheFlight(Flight flight, List<Flight> flightsList) {

		for (Flight tempFlight : flightsList) {

			if (tempFlight.getFlightNumber() == flight.getFlightNumber()) {

				return true;

			}

		}

		return false;

	}

	/**
	 * Returns a map, in which key is user name, and value is password.
	 * 
	 * @return a map, in which key is user name, and value is password.
	 */
	public Map<String, String> getUsernamePasswordMap() {

		return this.usernamePassword;

	}

	/**
	 * Returns a map, in which key is user name, and value is type of the user
	 * (admin or client).
	 * 
	 * @return a map, in which key is user name, and value is type of the user
	 *         (admin or client).
	 */
	public Map<String, String> getUsernameTypeMap() {

		return this.usernameType;

	}

	/**
	 * Opens the file that has the password and user name information for admin
	 * and client
	 * 
	 * @param passwords
	 *            the path has the password informtion.
	 * @throws IOException
	 *             when an error occurs while manipulating the file.
	 */
	public void openPasswordFile(File passwords) throws IOException {

		Map<String, String> usernamePassword = new HashMap<String, String>();
		Map<String, String> usernameType = new HashMap<String, String>();

		String aline;

		FileReader file = new FileReader(passwords);

		BufferedReader bf = new BufferedReader(file);

		while ((aline = bf.readLine()) != null) {

			String[] items = aline.split(COMMA_DELIMITER);

			String type = items[0];

			String username = items[1];

			String password = items[2];

			usernamePassword.put(username, password);
			usernameType.put(username, type);

		}

		this.usernamePassword = usernamePassword;
		this.usernameType = usernameType;

		bf.close();
	}

	/**
	 * Adds the new client into passwords file.
	 * 
	 * @param passwords
	 *            the passwords which file we will add new client into.
	 * @param client
	 *            the new client we will add into the passwords file
	 * @throws IOException
	 *             when an error occurs while manipulating the file.
	 */
	public void openPasswordFileAddClient(File passwords, Client client)
			throws IOException {

		String aline;

		BufferedWriter out = null;
		try {
			FileWriter fstream = new FileWriter(passwords, true); // true tells
																	// to append
																	// data.
			out = new BufferedWriter(fstream);
			out.write("\n" + "Client" + client.getEmail() + "newclient");
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * Opens ClientFile based on serialized ClientFile.
	 * 
	 * @param clientFile
	 *            is the serialized ClientFile.
	 * @throws IOException
	 *             when there is a problem while reading or writing a file.
	 */
	public void openClientFile(File clientFile) throws IOException {

		List<Client> clientsList = new ArrayList<Client>();

		String aline;

		FileReader file = new FileReader(clientFile);

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

			if (clientsList.isEmpty()) {

				clientsList.add(tempClient);

				this.clientsList = clientsList;

			} else {

				if (!(hasTheClient(tempClient, this.clientsList))) {

					this.clientsList.add(tempClient);

				} else {

					// overwrite the client if the client is already there.
					for (Client client : this.clientsList) {

						if (client.getEmail() == email) {

							this.clientsList.remove(client);

							this.clientsList.add(tempClient);
						}

					}

				}

			}

		}
		bf.close();
	}

}
