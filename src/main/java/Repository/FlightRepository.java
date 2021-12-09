package Repository;

import Entity.Flight;

import java.util.Date;

public class FlightRepository {

    public Flight createFlight(Long flightId,
                               String airbus, Long route,
                               Date departTime, Date travelTime) {
        return new Flight(flightId, airbus, route, departTime, travelTime);
    }
}
