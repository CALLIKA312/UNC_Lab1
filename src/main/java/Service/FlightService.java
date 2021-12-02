package Service;

import Entity.Flight;
import Repository.FlightRepository;

import javax.xml.crypto.Data;

public class FlightService {

    private FlightRepository flightRepository;

    public Flight createFlight() {
        return new Flight();
    }

    public Flight createFlight(Long flightId,
                               String airbus, String route,
                               Data departTime, Data travelTime) {
        return flightRepository.createFlight(flightId, airbus, route, departTime, travelTime);
    }
}
