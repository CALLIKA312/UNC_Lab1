package Service;

import Entity.Flight;
import Repository.FlightRepository;

import java.util.Date;

public class FlightService {

    private FlightRepository flightRepository;

    public Flight createFlight() {
        return new Flight();
    }

    public Flight createFlight(Long flightId,
                               String airbus, Long route,
                               Date departTime, Date travelTime) {
        return flightRepository.createFlight(flightId, airbus, route, departTime, travelTime);
    }
}
