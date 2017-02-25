package flight_itinerary_search;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * A graph that stores all the flights as nodes. Edges link two flights if the
 * origin of the first one is the destination of the second.
 *
 *
 */
public class FlightGraph {

    // Creates a HashMap to store flights and its linked flights.
    private static Map<Flight, Set<Flight>> graphedFlightList
            = new HashMap<Flight, Set<Flight>>();

    // Maximal stop-over time is 6 hrs.
    private final long maxTime = 6 * 60;

    // Minimal stop-over time is 0 hrs.
    private final long minTime = 0 * 60;

    /**
     * Constructor of the FlightGraph class.
     */
    public FlightGraph() {
    }

    /**
     *
     * Builds the graph by adding each flight as a node.
     *
     * @param allFlightsList
     *            is the list of all the flights stored in the database.
     */
    public void addNode(List<Flight> allFlightsList) {

        List<Flight> allFlightsListArrayList = new ArrayList<Flight>(
                allFlightsList);

        for (int $i = 0; $i < allFlightsListArrayList.size(); $i++) {

            Set<Flight> flightSet = new HashSet<Flight>();

            graphedFlightList.put(allFlightsListArrayList.get($i), flightSet);

        }
    }

    /**
     *
     * Links each flight with the flights that the destination is the origin of
     * this flight, or that the origin is the destination of this flight, also
     * the stop-over between two flights should between 0 to 6 hrs.
     *
     * @param allFlightsList
     *            is the list of all the flights stored in the database.
     */
    public void addEdge(List<Flight> allFlightsList) {

        List<Flight> allFlightsListArrayList = new ArrayList<Flight>(
                allFlightsList);

        for (int i = 0; i < allFlightsListArrayList.size(); i++) {

            for (int j = i + 1; j < allFlightsListArrayList.size(); j++) {

                if (allFlightsListArrayList.get(i).getDestination()
                        .equals(allFlightsListArrayList.get(j).getOrigin())) {

                    if (getStopOver(allFlightsListArrayList.get(i),
                            allFlightsListArrayList.get(j)) <= this.maxTime
                            && getStopOver(allFlightsListArrayList.get(i),
                            allFlightsListArrayList
                                    .get(j)) >= this.minTime) {

                        graphedFlightList.get(allFlightsListArrayList.get(i))
                                .add(allFlightsListArrayList.get(j));

                    }

                } else if (allFlightsListArrayList.get(j).getDestination()
                        .equals(allFlightsListArrayList.get(i).getOrigin())) {

                    if (getStopOver(allFlightsListArrayList.get(j),
                            allFlightsListArrayList.get(i)) <= this.maxTime
                            && getStopOver(allFlightsListArrayList.get(j),
                            allFlightsListArrayList
                                    .get(i)) >= this.minTime) {

                        graphedFlightList.get(allFlightsListArrayList.get(j))
                                .add(allFlightsListArrayList.get(i));
                    }

                }

            }
        }

    }

    /**
     *
     * Returns for a given flight all the flights that are linked with it in the
     * graph.
     *
     * @param flight
     *            is the given flight that we want to know the linked flights
     *            with it in the graph.
     *
     * @return all the flights linked in the graph with the given flight.
     */
    public Set<Flight> getNeighbours(Flight flight) {

        return graphedFlightList.get(flight);

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

}
