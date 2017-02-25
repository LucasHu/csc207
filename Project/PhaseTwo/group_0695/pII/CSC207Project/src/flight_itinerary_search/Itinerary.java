package flight_itinerary_search;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * A class used to store all the informations about an itinerary from an
 * "origin" city to a "destination" city, at the "departureDateTime" date.
 *
 *
 */
public class Itinerary {

	private List<Flight> flightsList;

	private long totalTime;

	private double totalCost;

	private String departureDateTime;

	private String origin;

	private String destination;

	/**
	 * 
	 * Constructor of the Itinerary class.
	 * 
	 * @param departureDateTime
	 *            is the date and time when the plane takes off from the origin
	 *            city.
	 * @param origin
	 *            is the city where the first flight begins.
	 * @param destination
	 *            is the city where the last flight ends.
	 */
	public Itinerary(String departureDateTime, String origin,
			String destination, List<Flight> flightsList) {

		this.departureDateTime = departureDateTime;

		this.origin = origin;

		this.destination = destination;

		this.flightsList = new ArrayList<Flight>(flightsList);

		try {

			this.totalTime = getTotalTime();

		} catch (ParseException e) {

			e.printStackTrace();

		}

		this.totalCost = getTotalCost();

	}

	/**
	 * 
	 * Adds a flight to the itinerary.
	 * 
	 * @param flight
	 *            is the flight added to the itinerary.
	 */
	public void addFlight(Flight flight) {

		this.flightsList.add(flight);

	}

	/**
	 * 
	 * Sets the date and time of departure from the origin city.
	 * 
	 * @param departureDateTime
	 *            is the date and time of departure from the origin city.
	 */
	public void setDepartureDateTime(String departureDateTime) {

		this.departureDateTime = departureDateTime;

	}

	/**
	 * 
	 * Returns the date and time of departure from the origin city.
	 * 
	 * @return the date and time of departure from the origin city.
	 */
	public String getDepartureDateTime() {

		return this.departureDateTime;

	}

	/**
	 * 
	 * Returns the original city where the itinerary starts.
	 * 
	 * @return the original city where the itinerary starts.
	 */
	public String getOrigin() {

		return this.origin;

	}

	/**
	 * Returns the destination city where the itinerary ends.
	 * 
	 * @return the destination city where the itinerary ends.
	 */
	public String getDestination() {

		return this.destination;

	}

	public List<Flight> getFlightsList() {

		return this.flightsList;

	}

	/**
	 * 
	 * Returns the total cost of the itinerary.
	 * 
	 * @return the total cost of the itinerary.
	 */
	public double getTotalCost() {

		double tempTotalCost = 0;

		for (int i = 0; i < flightsList.size(); i++) {

			tempTotalCost = tempTotalCost + flightsList.get(i).getCost();

		}

		return tempTotalCost;
	}

	/**
	 *
	 * Computes the time required to complete the entire journey from the origin
	 * city to the destination city by plane.
	 * 
	 * @return the total journey time of this itinerary.
	 */
	public long getTotalTime() throws ParseException {

		long tempTotalTime = 0;

		for (int i = 0; i < flightsList.size(); i++) {

			tempTotalTime = tempTotalTime + flightsList.get(i).getTravelTime();

			if (i != flightsList.size() - 1) {

				tempTotalTime = tempTotalTime + getStopOver(flightsList.get(i),
						flightsList.get(i + 1));

			}

		}

		return tempTotalTime;

	}

	/**
	 * 
	 * Returns the time between the arrival in the city that is the destination
	 * of the first flight, and the departure of this city with the second
	 * flight.
	 * 
	 * @param flight1
	 *            is the flight that arrives in the city.
	 * @param flight2
	 *            is the flight that leaves the city.
	 * @return the length of stop over the city shared by the two flights.
	 */
	public long getStopOver(Flight flight1, Flight flight2) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date startDate = new Date();

		Date endDate = new Date();

		try {

			startDate = format.parse(flight1.getArriveDateTime());

		} catch (ParseException e) {

			e.printStackTrace();

		}

		try {

			endDate = format.parse(flight2.getDepartureDateTime());

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return ((endDate.getTime() - startDate.getTime()) / (60 * 1000));

	}

	/**
	 * 
	 * Returns a string describing the flights composing the itinerary.
	 * 
	 * @return a string describing the flights composing the itinerary.
	 */
	public String returnItinerary() {

		String returnedItinerary = "";

		if (!this.flightsList.isEmpty()) {

			for (Flight o : this.flightsList) {

				returnedItinerary = returnedItinerary
						+ o.returnFlightWithTime();

			}

			DecimalFormat decimalFormat = new DecimalFormat("#.00");

			String costTwoDecimal = decimalFormat.format(this.totalCost);

			int time = (int) totalTime;

			int HH = time / 60;

			int MM = time % 60;

			String StringMM;

			if (MM < 10) {

				StringMM = "0" + String.valueOf(MM);

			} else {

				StringMM = String.valueOf(MM);

			}

			String StringHH;

			if (HH < 10) {

				StringHH = "0" + String.valueOf(HH);

			} else {

				StringHH = String.valueOf(HH);

			}

			returnedItinerary = returnedItinerary + costTwoDecimal + "\n"
					+ StringHH + ":" + StringMM + "\n";

			return returnedItinerary;

		} else {

			return null;

		}

	}

}
