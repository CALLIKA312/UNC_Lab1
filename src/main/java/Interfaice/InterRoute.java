package Interfaice;

import Entity.Route;

import Exception.NotFoundException;
import java.util.List;

public interface InterRoute {
    Boolean save(Route route);
    Boolean delete(Route route);
    Boolean FileUnload(List<Route> list);
    List<Route> fileLoad();
    Route getRouteDepart(String departPoint, String arrivalPoint) throws NotFoundException;
    Route getRouteDepart(Long id) throws NotFoundException;
}
