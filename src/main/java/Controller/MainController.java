package Controller;

import Entity.Flight;
import Entity.Route;
import Exception.AlreadyExistException;
import Exception.NotFoundException;
import Interfaice.InterFlight;
import Interfaice.InterRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ShellComponent
public class MainController {
    @Autowired
    InterFlight flight;

    @Autowired
    InterRoute routes;

    @ShellMethod("add new flight")
    public String addFlight(String airbus, Long route, Date departTime, Date travelTime) {
        long id = 1L;
        List<Flight> list = flight.fileLoad();
        Flight flightEnd;
        if (!list.isEmpty()) {
            flightEnd = list.get(list.size()-1);
            if (flightEnd != null)
                id = flightEnd.getFlightId() + 1;
        }
        try{
            Flight flightier = new Flight(id, airbus, route, departTime, travelTime);
            if (flight.findByAll(flightier)) throw new AlreadyExistException("Track already exists!");
            if (flight.save(flightier))
                return "Flight added.";
        }
        catch (AlreadyExistException e){
            e.printStackTrace();
        }
        return "Not added!";
    }


    @ShellMethod("update flight")
    public String updateFlight(String airbus, Long route) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MМ-yyyy");
        try {

            Flight fUpdate = new Flight(flight.findFlight(airbus, route).getFlightId(),
                    reader.readLine(),
                    Long.parseLong(reader.readLine()),
                    formatter.parse(reader.readLine()),
                    formatter.parse(reader.readLine())
            );

            List <Flight> list = flight.fileLoad();
            for(Flight flightTmp:list){
                if(Objects.equals(flightTmp.getFlightId(), flight.findFlight(airbus, route).getFlightId())) {
                    list.set(list.indexOf(flightTmp), fUpdate);
                    break;
                }
            }
            flight.fileUnload(list);
            return "Flight changed!";
        } catch (NullPointerException | NotFoundException | ParseException | IOException e) {
            e.printStackTrace();
        }
        return "Not changed";
    }


    @ShellMethod("delete flight")
    public String deleteFlight(String airbus, Long route){
        try {
            flight.delete(flight.findFlight(airbus, route));
            return "Flight deleted!";
        } catch (NotFoundException e){
            e.printStackTrace();
        }
        return "Not deleted";
    }


    @ShellMethod("add route")
    public String addRoute(String DepartPoint, String ArrivalPoint){
        long routeId = 1L;
        List<Route> list = routes.fileLoad();
        Route routeEnd;
        if (!list.isEmpty()) {
            routeEnd = list.get(list.size()-1);
            if (routeEnd != null)
                routeId = routeEnd.getRouteId() + 1;
        }
        try {
            if(routes.getRouteDepart(routeId) != null)
                throw new AlreadyExistException("Route " + DepartPoint + " " + ArrivalPoint + " already exist!");
        } catch (NotFoundException e) {
            Route route = new Route(DepartPoint, ArrivalPoint);
            route.setRouteId(routeId);
            if (routes.save(route))
                return "Successfully added!";
        } catch (AlreadyExistException e) {
            e.printStackTrace();
        }
        return "Not added";
    }


    @ShellMethod("delete route")
    public String deleteRoute(String DepartPoint, String ArrivalPoint){
        try {
            if (routes.getRouteDepart(DepartPoint, ArrivalPoint) != null) {
                Long routeId = routes.getRouteDepart(DepartPoint, ArrivalPoint).getRouteId();
                List<Flight> flighted = flight.fileLoad();
                if (routes.delete(routes.getRouteDepart(DepartPoint, ArrivalPoint))) {
                    try {
                        routes.getRouteDepart(DepartPoint, ArrivalPoint);
                    }catch (NotFoundException e){
                        Route route = new Route(null,null);
                        route.setRouteId(0L);
                        routes.save(route);
                    }
                    for(Flight flight: flighted){
                        if(flight.getRoute().equals(routeId)) {
                            flight.setRoute(0L);
                        }
                    }
                    flight.fileUnload(flighted);
                    return "Route deleted";
                }
                else return "ERROR: Not remotely";
            }
        }catch (NotFoundException e){
            e.printStackTrace();
        }
        return "Not deleted";
    }

}
