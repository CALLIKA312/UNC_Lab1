package Controller;

import Exception.AlreadyExistException;
import Exception.NotFoundException;
import Entity.Flight;
import Entity.Route;
import Interfaice.InterFlight;
import Interfaice.InterRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ShellComponent
public class MainControler {
    @Autowired
    InterFlight flight;
    @Autowired
    InterRoute routee;


    @ShellMethod("add new flight")
    public String addFlight(String airbus, String route, Date departTime, Date travelTime) {
        long id = 1l;
        List<Flight> list = flight.fileLoad();
        Flight flightEnd;
        if (!list.isEmpty()) {
            flightEnd = list.get(list.size()-1);
            if (flightEnd != null)
                id = flightEnd.getFlightId() + 1;
        }
        try{
            Flight flighter = new Flight(id, airbus, route, departTime, travelTime);
            if (flight.findFlight(flighter)) throw new AlreadyExistException("Track already exists!");
            if (flight.save(flighter))
                return "Flight added.";
        }
        catch (AlreadyExistException | NotFoundException e){
            e.printStackTrace();
        }
        return "Not added!";
    }


    @ShellMethod("update flight")
    public String updateFlight(String airbus, String route) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MМ-yyyy");
        try {

            Flight fUpdate = new Flight(flight.findFlight(airbus, route).getFlightId(),
                    reader.readLine(),
                    reader.readLine(),
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
    public String deleteFlight(String airbus, String route){
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
        Long routeId = (long) 1;
        List<Route> list = routee.fileLoad();
        Route routeEnd;
        if (!list.isEmpty()) {
            routeEnd = list.get(list.size()-1);
            if (routeEnd != null)
                routeId = routeEnd.getRouteId() + 1;
        }
        try {
            if(routee.getRouteDepart(DepartPoint, ArrivalPoint) != null)
                throw new AlreadyExistException("Route " + DepartPoint + " " + ArrivalPoint + " already exist!");
        } catch (NotFoundException e) {
            Route route = new Route(DepartPoint, ArrivalPoint);
            route.setRouteId(routeId);
            if (routee.save(route))
                return "Successfully added!";
        } catch (AlreadyExistException e) {
            e.printStackTrace();
        }
        return "Not added";
    }


    @ShellMethod("delete route")
    public String deleteGenre(String DepartPoint, String ArrivalPoint){
        try {
            if (routee.getRouteDepart(DepartPoint, ArrivalPoint) != null) {
                Long routeId = routee.getRouteDepart(DepartPoint, ArrivalPoint).getRouteId();
                List<Flight> fligthe = flight.fileLoad();
                if (routee.delete(routee.getRouteDepart(DepartPoint, ArrivalPoint))) {
                    try {
                        routee.getRouteDepart(DepartPoint, ArrivalPoint);
                    }catch (NotFoundException e){
                        Route route = new Route("None","None");
                        route.setRouteId((long) 0);
                        routee.save(route);
                    }
                    for(Flight flight: fligthe){
                        if(flight.getRoute().equals(routeId)) {
                            flight.setRoute("None");
                        }
                    }
                    flight.fileUnload(fligthe);
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
