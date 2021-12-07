package Interfaice;

import Entity.Flight;
import Exception.NotFoundException;
import java.util.List;

public interface InterFlight {
    Boolean save(Flight flight);
    Boolean delete(Flight flight);
    Boolean fileUnload(List <Flight> flight);
    List<Flight> fileLoad();
    Flight findFlight(String airbus, String route) throws NotFoundException;
    Boolean findFlight(Flight flight) throws NotFoundException;
}
