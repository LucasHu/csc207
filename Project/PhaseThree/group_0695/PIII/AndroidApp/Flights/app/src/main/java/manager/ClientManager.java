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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import user.Client;
import user.User;

/**
 * 
 * A class helps to manage serialized clients data.
 *
 */
public class ClientManager implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Client> clients;

	private User user;

	private String filePath;

	private static final Logger logger = Logger
			.getLogger(ClientManager.class.getPackage().getName());

	private static final ConsoleHandler consoleHandler = new ConsoleHandler();

	/**
	 * Creates a ClientManger, which stores the serialized clients data.
	 * 
	 * @param file
	 *            is the file where student data loads from.
	 * @throws ClassNotFoundException
	 *             when the class is not found.
	 * @throws IOException
	 *             when an error occurs while reading or writing a file.
	 */
	public ClientManager(File file) throws ClassNotFoundException, IOException {

		this.clients = new HashMap<String, Client>();

		// Associate the handler with the logger.
		logger.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);

		// Populate the clients HashMap using serialized data stored in file,
		// if it exists. If not, create a new empty file for it to be saved in
		// later.
		if (file.exists()) {
			readFromFile(file.getPath());
		} else {
			file.createNewFile();
		}

		// Records the path of the serialized data file.
		this.filePath = file.getPath();
	}

	/**
	 * Adds a client into the ClientManager.
	 * 
	 * @param client
	 *            is the client will be added into the ClientManager.
	 */
	public void add(Client client) {
		this.clients.put(client.getEmail(), client);

		// Log the addition of a client.
		logger.log(Level.FINE, "Added a new client " + client.returnClient());
	}

	/**
	 * Updates a client into the CLientManager.
	 * 
	 * @param client
	 *            is the client will be updated in the ClientManager.
	 */
	public void update(Client client) {

		this.clients.put(client.getEmail(), client);

		// Log the addition of a client.
		logger.log(Level.FINE, "Update a client " + client.returnClient());

	}

	/**
	 * Removes a client from the CLientManager.
	 *
	 * @param email
	 *            is the client will be updated in the ClientManager.
	 */
	public void remove(String email) {

		this.clients.remove(email);

		// Log the removal of a client.
		logger.log(Level.FINE, "remove a client, whose email " + email);

	}

	/**
	 * Saves any changes to the ClientManager.
	 */
	public void saveToFile() {

		try {

			OutputStream file = new FileOutputStream(filePath);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);

			output.writeObject(clients);

			logger.log(Level.FINE, "Serialized client manager.");

			output.close();

		} catch (IOException ex) {

			logger.log(Level.SEVERE, "Cannot perform output. File I/O failed.",
					ex);
		}
	}

	/**
	 * Reads Client File directly from path.
	 * 
	 * @param path
	 */
	@SuppressWarnings("unchecked")
	public void readFromFile(String path) {

		try {
			InputStream file = new FileInputStream(path);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);

			this.clients = (Map<String, Client>) input.readObject();
			input.close();
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Cannot read from input.", ex);
		} catch (ClassNotFoundException ex) {
			logger.log(Level.SEVERE, "Cannot find class.", ex);
		}
	}

	/**
	 * A helper function that can test if the client is in the manager.
	 * 
	 * @param client
	 *            is the Client will be tested.
	 * @return if the client is in the manager, returns true, otherwise, returns
	 *         false.
	 */
	public boolean hasTheClient(Client client) {

		Client tempClient = this.clients.get(client.getEmail());

		if (tempClient != null) {

			return true;

		} else {

			return false;

		}
	}

	/**
	 * Get all the clients in the ClientManager.
	 * 
	 * @return the clients in a map.
	 */
	public Map<String, Client> getClients() {
		return this.clients;
	}

	/**
	 * Returns the file path of the serialized ClientManager file.
	 * 
	 * @return the file path of the serialized ClientManager file as a string.
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Sets the user of the manager.
	 * 
	 * @param user
	 *            is the user of this manager.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the user of this manager.
	 * 
	 * @return the user is the user of this manager.
	 */
	public User getUser() {
		return this.user;
	}

}
