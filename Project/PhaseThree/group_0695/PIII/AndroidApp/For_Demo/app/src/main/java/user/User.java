package user;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.io.Serializable;

import flight_itinerary_search.Flight;
import flight_itinerary_search.Itinerary;
import flight_itinerary_search.ItinerariesSearch;

/**
 *
 * A class used to perform a search and store it's result.
 *
 *
 */

public abstract class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //Initializes a DataHandler.
    private DataHandler dh = new DataHandler();

    /**
     * Constructor of the User class.
     */
    public User() {
    }

    /**
     *
     * Performs a search of itineraries.
     *
     * @param departureDate
     *            is the date when the client wants to leave the origin city.
     * @param origin
     *            is the city where the first plane takes off.
     * @param destination
     *            is the city where the last plane of the itinerary lands.
     * @return a list of all the itineraries that link the city of origin to the
     *         destination at the given date of departure.
     * @throws IOException
     *             when there is a problem while reading or writing a file.
     * @throws ClassNotFoundException
     *             when the class is not found.
     */
    public List<Flight> searchFlights(String departureDate, String origin,
                                      String destination) throws IOException, ClassNotFoundException {

        List<Flight> searchedFlights = new ArrayList<Flight>();

        List<Flight> allFlights = new ArrayList<Flight>();

        allFlights = readFlightFile();

        for (Flight individualFlight : allFlights) {

            if (individualFlight.getOrigin().equals(origin)
                    && individualFlight.getDestination().equals(destination)) {

                String[] items = individualFlight.getDepartureDateTime()
                        .split("\\s+");

                if (items[0].equals(departureDate)) {

                    searchedFlights.add(individualFlight);

                }

            }

        }
        return searchedFlights;

    }

    /**
     *
     * @param departureDate is the date when the client wants to leave the origin city.
     * @param origin is the city where the first plane takes off.
     * @param destination is the city where the last plane of the itinerary lands.
     * @param tempAllFlights is a list that has all flights read from files and stored in system.
     * @return
     * @throws IOException when there is a problem while reading or writing a file.
     * @throws ClassNotFoundException when the class is not found.
     */
    public List<Flight> searchFlightsBySerializable(String departureDate,
                                                    String origin, String destination, List<Flight> tempAllFlights)
            throws IOException, ClassNotFoundException {

        List<Flight> searchedFlights = new ArrayList<Flight>();

        List<Flight> allFlights = new ArrayList<Flight>();

        allFlights = tempAllFlights;

        for (Flight individualFlight : allFlights) {

            if (individualFlight.getOrigin().equals(origin)
                    && individualFlight.getDestination().equals(destination)) {

                String[] items = individualFlight.getDepartureDateTime()
                        .split("\\s+");

                if (items[0].equals(departureDate)) {

                    searchedFlights.add(individualFlight);

                }

            }

        }

        return searchedFlights;

    }

    /**
     * Sorts the searched Itineraries result by time.
     * @param allSearchedItineraries
     * @return sorted Itineraries by time
     */
    public List<Itinerary> sortItinerariesByTime(List<Itinerary> allSearchedItineraries) {

        Collections.sort(allSearchedItineraries, new timeComparator());

        return allSearchedItineraries;
    }

    /**
     * Sorts the searched Itineraries result by price.
     * @param allSearchedItineraries
     * @return sorted Itineraries by price
     */
    public List<Itinerary> sortItinerariesByPrice(List<Itinerary> allSearchedItineraries) {

        Collections.sort(allSearchedItineraries, new costComparator());

        return allSearchedItineraries;
    }

    /**
     *
     * Returns the itineraries list
     *
     * @param dapartureDate
     *            is the date of departure of the client.
     * @param origin
     *            is the city of origin of the client.
     * @param destination
     *            is the city where the client wants to go.
     * @return the list of all possible itineraries that link the city of origin
     *         to the destination at the given departure date.
     * @throws ClassNotFoundException
     *             when the class is not found.
     * @throws IOException
     *             when an error occurs while reading or writing a file.
     */
    public List<Itinerary> searchedItinerariesList(String dapartureDate,
                                                   String origin, String destination)
            throws ClassNotFoundException, IOException {

        List<Flight> allFlights = new ArrayList<Flight>();

        allFlights = readFlightFile();

        ItinerariesSearch searchingItinerariesList = new ItinerariesSearch();

        searchingItinerariesList.findItineraries(
                searchingItinerariesList.findAllItineraries(dapartureDate,
                        origin, destination, allFlights));

        return searchingItinerariesList.getItinerariesList();
    }

    /**
     *
     * Returns the itineraries list from serialized data.
     *
     * @param dapartureDate
     *            is the date of departure of the client.
     * @param origin
     *            is the city of origin of the client.
     * @param destination
     *            is the city where the client wants to go.
     * @return the list of all possible itineraries that link the city of origin
     *         to the destination at the given departure date.
     * @throws ClassNotFoundException
     *             when the class is not found.
     * @throws IOException
     *             when an error occurs while reading or writing a file.
     */
    public List<Itinerary> searchedItinerariesListSerializable(String dapartureDate,
                                                               String origin, String destination, List<Flight> tempFlightLists)
            throws ClassNotFoundException, IOException {

        List<Flight> allFlights = new ArrayList<Flight>();

        allFlights = tempFlightLists;

        ItinerariesSearch searchingItinerariesList = new ItinerariesSearch();

        searchingItinerariesList.findItineraries(
                searchingItinerariesList.findAllItineraries(dapartureDate,
                        origin, destination, allFlights));

        return searchingItinerariesList.getItinerariesList();
    }

    /**
     *
     * Sorts the list of itineraries by the time required to complete each one
     * of the itinerary.
     *
     * @param dapartureDate
     *            is the date when the itinerary starts.
     * @param origin
     *            is the city of origin of the client.
     * @param destination
     *            is the city where the client wants to go.
     * @return the list of all possible itineraries that link the city of origin
     *         to the destination at the given departure date, sorted by time.
     * @throws ClassNotFoundException
     *             when the class is not found.
     * @throws IOException
     *             when an error occurs while reading or writing a file.
     */
    public List<Itinerary> sortItinerariesListByTime(String dapartureDate,
                                                     String origin, String destination)
            throws ClassNotFoundException, IOException {

        List<Itinerary> allSearchedItineraries = searchedItinerariesList(
                dapartureDate, origin, destination);

        Collections.sort(allSearchedItineraries, new timeComparator());

        return allSearchedItineraries;

    }

    /**
     *
     * Sorts the list of itineraries by the time required to complete each one
     * of the itinerary from Serialized data.
     *
     * @param dapartureDate
     *            is the date when the itinerary starts.
     * @param origin
     *            is the city of origin of the client.
     * @param destination
     *            is the city where the client wants to go.
     * @return the list of all possible itineraries that link the city of origin
     *         to the destination at the given departure date, sorted by time.
     * @throws ClassNotFoundException
     *             when the class is not found.
     * @throws IOException
     *             when an error occurs while reading or writing a file.
     */
    public List<Itinerary> sortItinerariesListByTimeSerialized(String dapartureDate,
                                                               String origin, String destination, List<Flight> tempFlights)
            throws ClassNotFoundException, IOException {

        List<Itinerary> allSearchedItineraries = searchedItinerariesListSerializable(
                dapartureDate, origin, destination, tempFlights);

        Collections.sort(allSearchedItineraries, new timeComparator());

        return allSearchedItineraries;

    }

    /**
     *
     * Sorts the list of itineraries by the cost of each one of the itineraries.
     *
     * @param dapartureDate
     *            is the date of departure of the client.
     * @param origin
     *            is the city of origin of the client.
     * @param destination
     *            is the city where the client wants to go.
     * @return the list of all possible itineraries that link the city of origin
     *         to the destination at the given departure date, sorted by cost.
     * @throws ClassNotFoundException
     *             when the class is not found.
     * @throws IOException
     *             when an error occurs while reading or writing a file.
     */
    public List<Itinerary> sortItinerariesListByCost(String dapartureDate,
                                                     String origin, String destination)
            throws ClassNotFoundException, IOException {

        List<Itinerary> allSearchedItineraries = searchedItinerariesList(
                dapartureDate, origin, destination);

        Collections.sort(allSearchedItineraries, new costComparator());

        return allSearchedItineraries;

    }

    /**
     *
     * Sorts the list of itineraries by the cost of each one of the itineraries from serialized data.
     *
     * @param dapartureDate
     *            is the date of departure of the client.
     * @param origin
     *            is the city of origin of the client.
     * @param destination
     *            is the city where the client wants to go.
     * @return the list of all possible itineraries that link the city of origin
     *         to the destination at the given departure date, sorted by cost.
     * @throws ClassNotFoundException
     *             when the class is not found.
     * @throws IOException
     *             when an error occurs while reading or writing a file.
     */
    public List<Itinerary> sortItinerariesListByCostSerilizable(String dapartureDate,
                                                                String origin, String destination, List<Flight> tempFlights)
            throws ClassNotFoundException, IOException {

        List<Itinerary> allSearchedItineraries = searchedItinerariesListSerializable(
                dapartureDate, origin, destination, tempFlights);

        Collections.sort(allSearchedItineraries, new costComparator());

        return allSearchedItineraries;

    }

    /**
     *
     * Compares the total travel time of two itineraries to sort them.
     *
     * @return -1 if the total travel time of the first itinerary is smaller
     *         then the second itinerary.
     */
    public class timeComparator implements Comparator<Itinerary> {

        public int compare(Itinerary a, Itinerary b) {

            try {

                if (a.getTotalTime() < b.getTotalTime()) {

                    return -1;

                } else if (a.getTotalTime() > b.getTotalTime()) {

                    return 1;

                }

            } catch (ParseException e) {

                e.printStackTrace();

            }

            return 0;

        }

    }

    /**
     *
     * Compares the total cost of two itineraries to sort them.
     *
     * @return -1 if the total cost of the first itinerary is smaller then the
     *         second itinerary.
     */
    public class costComparator implements Comparator<Itinerary> {

        public int compare(Itinerary a, Itinerary b) {

            if (a.getTotalCost() < b.getTotalCost()) {

                return -1;

            } else if (a.getTotalCost() > b.getTotalCost()) {

                return 1;

            }

            return 0;

        }

    }

    /**
     *
     * Reads the flights file to get the list of flights.
     *
     * @return the list of all the flights.
     * @throws IOException
     *             when there is an error while reading the file.
     */
    public List<Flight> readFlightFile() throws IOException {

        return dh.getFlightsList();

    }

    /**
     *
     * Uploads new flights to the list of flights from a file.
     *
     * @param path
     *            is the file to upload.
     * @throws IOException
     *             when an error occurs when reading the file.
     */
    public void uploadFlightFile(String path) throws IOException {

        dh.openFlightFile(path);

    }

}
