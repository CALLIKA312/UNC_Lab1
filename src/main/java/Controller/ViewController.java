package Controller;

import Entity.Flight;
import Exception.NotFoundException;
import Interfaice.InterFlight;
import Interfaice.InterRoute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShellComponent
public class ViewController {
    @Autowired
    InterFlight flight;
    @Autowired
    InterRoute route;

    private static final Gson GSON = new GsonBuilder().create();

    @ShellMethod("View all")
    public String viewAll(){
        List<Flight> airFl = flight.fileLoad();
        StringBuffer data = new StringBuffer("");
        for(Flight flight:airFl){
            viewContent(flight, data);
            data.append("-----------------------------------------\n");
        }

        return data.toString();
    }

    @ShellMethod("View find airbus and route")
    public String searchFlight(String airbus, Long route){
        try {
            Flight flighty = flight.findFlight(airbus, route);

            StringBuffer data = new StringBuffer("");
            viewContent(flighty, data);
            data.append("-------------------end-------------------\n");
            return data.toString();
        } catch (NotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @ShellMethod("View search")
    public String search(String regex){
        List<Flight> flightList = flight.fileLoad();
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m1, m2, m3, m4;

        StringBuffer data = new StringBuffer("");
        String airbus, routee;
        Date departTime, travelTime;
        for(Flight airFl:flightList){
            try {
                airbus = airFl.getAirbus();
                departTime = airFl.getDepartTime();
                travelTime = airFl.getTravelTime();
                routee = route.getRouteDepart(airFl.getRoute()).getDepartPoint();
                m1 = p.matcher(airbus);
                m2 = p.matcher((CharSequence) departTime);
                m3 = p.matcher((CharSequence) travelTime);
                m4 = p.matcher(routee);
                if (m1.find() || m2.find() || m3.find() || m4.find()) {
                    viewContent(airFl, data);
                    data.append("-----------------------------------------\n");
                }else continue;
            } catch (NotFoundException e) {
                e.printStackTrace();
                continue;
            }
        }

        return data.toString();
    }


    private void viewContent(Flight flight, StringBuffer data) {
        try{
            Long flightId = flight.getFlightId();
            String airbus = flight.getAirbus();
            Date departTime = flight.getDepartTime();
            Date travelTime = flight.getTravelTime();
            String routeDp = route.getRouteDepart(flight.getRoute()).getDepartPoint();
            String routeAp = route.getRouteDepart(flight.getRoute()).getArrivalPoint();
            data.append("flightId: ").append(flightId).append("\n");
            data.append("airbus: ").append(airbus).append("\n");
            data.append("depart point: ").append(routeDp).append("\n");
            data.append("arrival point: ").append(routeAp).append("\n");
            data.append("departTime: ").append(departTime).append("\n");
            data.append("travelTime: ").append(travelTime).append("\n");

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
