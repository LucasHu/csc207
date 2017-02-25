package user;

/**
 * 
 * A class used to store all the information about a client.
 * 
 *
 */
public class Client {

	private String lastName;
	private String firstname;
	private String email;
	private String address;
	private String creditCardNumber;
	private String expiryDate;

	/**
	 * 
	 * Constructor of the client class.
	 * 
	 * @param lastName
	 *            of the client.
	 * @param firstname
	 *            of the client.
	 * @param email
	 *            is the email address of the client.
	 * @param address
	 *            is the address of the client.
	 * @param creditCardNumber
	 *            is the billing information of the client.
	 * @param expiryDate
	 *            is the date of expiration of the client credit card.
	 */
	public Client(String lastName, String firstname, String email,
			String address, String creditCardNumber, String expiryDate) {

		this.lastName = lastName;
		this.firstname = firstname;
		this.email = email;
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.expiryDate = expiryDate;

	}

	/**
	 * 
	 * Returns the last name of the client.
	 * 
	 * @return the last name of the client.
	 */
	public String getLastName() {

		return lastName;

	}

	/**
	 * 
	 * Sets the last name of the client.
	 * 
	 * @param lastName
	 *            of the client.
	 */
	public void setLastName(String lastName) {

		this.lastName = lastName;

	}

	/**
	 * 
	 * Returns the first name of the client.
	 * 
	 * @return the first name of the client.
	 */
	public String getFirstname() {

		return firstname;

	}

	/**
	 * 
	 * Sets the last name of the client.
	 * 
	 * @param firstname
	 *            is the last name of the client.
	 */
	public void setFirstname(String firstname) {

		this.firstname = firstname;

	}

	/**
	 * 
	 * Returns the email address of the client.
	 * 
	 * @return the email address of the client.
	 */
	public String getEmail() {

		return email;

	}

	/**
	 * 
	 * Sets the email address of the client.
	 * 
	 * @param email
	 *            is the email address of the client.
	 */
	public void setEmail(String email) {

		this.email = email;

	}

	/**
	 * 
	 * Returns the address of the client.
	 * 
	 * @return the address of the client.
	 */
	public String getAddress() {

		return address;

	}

	/**
	 * 
	 * Sets the address of the client.
	 * 
	 * @param address
	 *            of the client.
	 */
	public void setAddress(String address) {

		this.address = address;

	}

	/**
	 * 
	 * Returns the number of the client's credit card.
	 * 
	 * @return the number of the client's credit card.
	 */
	public String getCreditCardNumber() {

		return creditCardNumber;

	}

	/**
	 * 
	 * Sets the number of the client's credit card.
	 * 
	 * @param creditCardNumber
	 *            is the number of the client's credit card.
	 */
	public void setCreditCardNumber(String creditCardNumber) {

		this.creditCardNumber = creditCardNumber;

	}

	/**
	 * 
	 * Returns the date of expiration of the client's credit card.
	 * 
	 * @return the date of expiration of the client's credit card.
	 */
	public String getExpiryDate() {

		return expiryDate;

	}

	/**
	 * 
	 * Sets the date of expiration of the client's credit card.
	 * 
	 * @param expiryDate
	 *            is the date of expiration of the client's credit card.
	 */
	public void setExpiryDate(String expiryDate) {

		this.expiryDate = expiryDate;

	}

	/**
	 * 
	 * Returns all the information of the client into a string.
	 * 
	 * @return all the information of the client into a string.
	 */
	public String returnClient() {

		return this.lastName + "," + this.firstname + "," + this.email + ","
				+ this.address + "," + this.creditCardNumber + ","
				+ this.expiryDate;

	}
}
