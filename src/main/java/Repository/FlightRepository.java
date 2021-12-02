package Repository;

import Entity.Flight;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    public Flight createFlight(Long flightId,
                               String airbus, String route,
                               Data departTime, Data travelTime) {
        return new Flight(flightId, airbus, route, departTime, travelTime);
    }
}
