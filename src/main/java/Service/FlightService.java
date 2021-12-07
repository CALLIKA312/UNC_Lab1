package Service;

import Entity.Flight;
import Repository.FlightRepository;

import javax.xml.crypto.Data;
import java.util.Date;

public class FlightService {

    private FlightRepository flightRepository;

    public Flight createFlight() {
        return new Flight();
    }

    public Flight createFlight(Long flightId,
                               String airbus, String route,
                               Date departTime, Date travelTime) {
        return flightRepository.createFlight(flightId, airbus, route, departTime, travelTime);
    }
}
