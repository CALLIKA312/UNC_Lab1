package org.pelmen.Interfaice;

import org.pelmen.Entity.Flight;
import org.pelmen.Exception.NotFoundException;
import java.util.List;

public interface InterFlight {
    Boolean save(Flight flight);
    void delete(Flight flight);
    Boolean fileUnload(List <Flight> flight);
    List<Flight> fileLoad();
    Flight findFlight(String airbus, Long route) throws NotFoundException;
    boolean findByAll(Flight flight);
}
