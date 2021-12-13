package Interfaice.Default;

import Entity.Flight;
import Interfaice.InterFlight;
import Exception.NotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightInter implements InterFlight {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Boolean save(Flight flight) {
        List<Flight> list = fileLoad();
        list.add(flight);
        return fileUnload(list);
    }


    @Override
    public void delete(Flight flight) {
        List<Flight> list = fileLoad();

        for(Flight tmpFlight:list){
            if(flight.getFlightId().equals(tmpFlight.getFlightId())) {
                list.remove(tmpFlight);
                break;
            }
        }
        if(!list.isEmpty())
            fileUnload(list);
    }

    @Override
    public List<Flight> fileLoad(){

        BufferedReader reader;
        List<Flight> list = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Flight>>() {}.getType();
        try {
            reader = new BufferedReader(new FileReader("name file"));
            list = GSON.fromJson(reader, itemsListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(list == null) list = new ArrayList<>();
        return list;
    }

    @Override
    public Boolean fileUnload(List<Flight> list){

        try {
            FileWriter fileWriter = new FileWriter("name file", false);
            fileWriter.write(GSON.toJson(list));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Flight findFlight(String airbus, Long route) throws NotFoundException {
        List<Flight> list = fileLoad();
        for(Flight flight:list){
            if(flight.getAirbus().equals(airbus) && flight.getRoute().equals(route))
                return flight;
        }
        throw new NotFoundException("Not found flight: " + airbus + " " + route);
    }

    @Override
    public boolean findByAll(Flight flight) {
        List<Flight> fl = fileLoad();
        for (Flight tmpFlight: fl){
            if(flight.getAirbus().equals(tmpFlight.getAirbus())){
                if (tmpFlight.getAirbus().equals(flight.getAirbus()) &&
                        tmpFlight.getRoute().equals(flight.getRoute())) {
                    return true;
                }
                else break;
            }
        }
        return false;
    }
}
