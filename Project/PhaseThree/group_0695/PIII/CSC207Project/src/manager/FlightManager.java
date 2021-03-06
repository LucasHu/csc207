package manager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import flight_itinerary_search.Flight;
import flight_itinerary_search.Itinerary;

/**
 * A class helps to manager serialized flights data.
 *
 */
public class FlightManager implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private List<Flight> flights;

	private List<Itinerary> results;

	private Itinerary selectedItinerary;

	private String filePath;

	private static final Logger logger = Logger
			.getLogger(FlightManager.class.getPackage().getName());

	private static final ConsoleHandler consoleHandler = new ConsoleHandler();

	/**
	 * Creates a serialized FlightManager, which stores all the flights data.
	 * 
	 * @param file
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public FlightManager(File file) throws ClassNotFoundException, IOException {
		this.flights = new ArrayList<Flight>();

		// Associate the handler with the logger.
		logger.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);

		if (file.exists()) {
			readFromFile(file.getPath());
		} else {
			file.createNewFile();
		}

		// Records the path of the serialized data file.
		this.filePath = file.getPath();
	}

	/**
	 * Reads serialized student data from file at path and populates this
	 * StudentManager's map with that data.
	 * 
	 * @param path
	 *            is the path of the file.
	 */
	public void readFromFile(String path) {

		try {
			InputStream file = new FileInputStream(path);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

			this.flights = (ArrayList<Flight>) input.readObject();
			input.close();
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Cannot read from input.", ex);
		} catch (ClassNotFoundException ex) {
			logger.log(Level.SEVERE, "Cannot find class.", ex);
		}
	}

	/**
	 * Adds a flight into the FlightManager.
	 * 
	 * @param flight
	 *            is the flight will be added into the FlightManager.
	 */
	public void add(Flight flight) {

		this.flights.add(flight);

		// Log the addition of a flight.
		logger.log(Level.FINE, "Added a flight " + flight.getFlightNumber());
	}

	/**
	 * Updates a flight into the FlightManager.
	 * 
	 * @param flight
	 *            is the flight will be updated in the FlightManager.
	 */
	public void update(Flight flight) {

		this.flights.add(flight);

		// Log the addition of a flight.
		logger.log(Level.FINE, "Update a flight " + flight.getFlightNumber());

	}

	/**
	 * Tests if the FlightManager has the flight based on the flight number.
	 * 
	 * @param number
	 *            is the flight number, which the flight has.
	 * @return if the flight is in the FlightManager, if so, returns true,
	 *         otherwise, returns false.
	 */
	public boolean hasTheFlight(String number) {

		for (Flight flight : this.flights) {
			if (flight.getFlightNumber() == number) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Saves any changes of the FLightManager into serialized data.
	 */
	public void saveToFile() {

		try {
			OutputStream file = new FileOutputStream(filePath);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);

			// Serialize the students Map.
			output.writeObject(this.flights);
			logger.log(Level.FINE, "Serialized flight manager.");
			output.close();
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Cannot perform output. File I/O failed.",
					ex);
		}
	}

	/**
	 * Gets the flight given the flight number.
	 * 
	 * @param number
	 *            is the flight number that this flight has.
	 * @return the flight if there is a such flight otherwise returns null.
	 */
	public Flight getTheFlight(String number) {

		for (Flight flight : this.flights) {
			if (flight.getFlightNumber() == number) {
				return flight;
			}
		}

		return null;

	}

	/**
	 * Removes a flight from the FlightManager.
	 * 
	 * @param number
	 *            is the flight number that the flight has.
	 */
	public void removeFlight(String number) {
		this.flights.remove(getTheFlight(number));
	}

	/**
	 * Returns the path the FlightManager sits.
	 * 
	 * @return the path the FlightManager sits.
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Gets the flights list the FlightManager has.
	 * 
	 * @return a list of flight that the FlightManager has.
	 */
	public List<Flight> getFlights() {

		return this.flights;
	}

	/**
	 * Sets a list of itineraries of as result .
	 * 
	 * @param results
	 *            is the list of itineraries.
	 */
	public void setResults(List<Itinerary> results) {
		this.results = results;
	}

	/**
	 * returns the result, which is a list of itineraries.
	 * 
	 * @return a list of itineraries that represents the resuts.
	 */
	public List<Itinerary> getResults() {
		return this.results;
	}

	/**
	 * Sets selectedItinerary
	 * 
	 * @param selectedItinerary
	 *            is the list of selected itineraries.
	 */
	public void setSelectedItinerary(Itinerary selectedItinerary) {
		this.selectedItinerary = selectedItinerary;
	}

	/**
	 * Returns the list of selected itineraries.
	 * 
	 * @return the list of selected itineraries.
	 */
	public Itinerary getSelectedItinerary() {
		return this.selectedItinerary;
	}
}