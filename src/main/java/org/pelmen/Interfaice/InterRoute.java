package org.pelmen.Interfaice;

import org.pelmen.Entity.Route;

import org.pelmen.Exception.NotFoundException;
import java.util.List;

public interface InterRoute {
    Boolean save(Route route);
    Boolean delete(Route route);
    Boolean fileUnload(List<Route> list);
    List<Route> fileLoad();
    Route getRouteDepart(String departPoint, String arrivalPoint) throws NotFoundException;
    Route getRouteDepart(Long id) throws NotFoundException;
}
