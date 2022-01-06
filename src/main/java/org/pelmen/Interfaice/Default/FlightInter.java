package org.pelmen.Interfaice.Default;

import org.pelmen.Entity.Flight;
import org.pelmen.Interfaice.InterFlight;
import org.pelmen.Exception.NotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.pelmen.ConstFile.FILE_FLIGHT_DBASE;

@Service
public class FlightInter implements InterFlight {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
            .create();

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
        fileUnload(list);
    }

    @Override
    public List<Flight> fileLoad(){

        BufferedReader reader;
        List<Flight> list = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Flight>>() {}.getType();
        try {
            reader = new BufferedReader(new FileReader(FILE_FLIGHT_DBASE));
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
            FileWriter fileWriter = new FileWriter(FILE_FLIGHT_DBASE, false);
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
