import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        findPlanesLeavingInTheNextTwoHours(airport);
        System.out.println(findPlanesLeavingInTheNextTwoHours(airport));
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date currentDate = new Date();
        /* old
        List<Terminal> allTerminals = airport.getTerminals();
        List<Flight> allFlight = new ArrayList<>();
        allTerminals.forEach(terminal -> allFlight.addAll(terminal.getFlights()));
        List<Flight> allFlightDeparture = allFlight.stream()
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .collect(Collectors.toList());

        return allFlightDeparture.stream()
                .filter(flight -> TimeUnit.MILLISECONDS
                        .toMinutes(flight.getDate().getTime() - currentDate.getTime()) < 120
                        && TimeUnit.MILLISECONDS
                        .toMinutes(flight.getDate().getTime() - currentDate.getTime()) > 0)
                .collect(Collectors.toList());

         */

        // new
        return airport.getTerminals().stream()
                .flatMap(terminal -> terminal.getFlights().stream())
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .filter(flight -> TimeUnit.MILLISECONDS
                        .toMinutes(flight.getDate().getTime() - currentDate.getTime()) < 120
                        && TimeUnit.MILLISECONDS
                        .toMinutes(flight.getDate().getTime() - currentDate.getTime()) > 0)
                .collect(Collectors.toList());
    }

}