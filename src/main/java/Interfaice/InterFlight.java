package Interfaice;

import Entity.Flight;
import Exception.NotFoundException;
import java.util.List;

public interface InterFlight {
    Boolean save(Flight flight);
    void delete(Flight flight);
    Boolean fileUnload(List <Flight> flight);
    List<Flight> fileLoad();
    Flight findFlight(String airbus, Long route) throws NotFoundException;
    boolean findByAll(Flight flight);
}
