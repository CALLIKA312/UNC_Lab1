package Interfaice.Default;

import Entity.Route;
import Interfaice.InterRoute;
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
public class RouteInter implements InterRoute{

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Boolean save(Route route) {
        List<Route> list = fileLoad();
        list.add(route);
        return FileUnload(list);
    }


    @Override
    public Boolean delete(Route route) {
        List<Route> list = fileLoad();

        for(Route tmpRoute:list){
            if(route.getDepartPoint() == tmpRoute.getDepartPoint() & route.getArrivalPoint() == tmpRoute.getArrivalPoint()) {
                list.remove(tmpRoute);
                break;
            }
        }
        if(!list.isEmpty())
            FileUnload(list);
        return true;
    }


    public List<Route> fileLoad(){

        BufferedReader reader = null;
        List<Route> list = new ArrayList<>();
        Type itemsListType = new TypeToken<List<Route>>() {}.getType();
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
    public Route getRouteDepart(String departPoint, String arrivalPoint) throws NotFoundException {
        List<Route> list = fileLoad();
        for(Route route: list){
            if(route.getDepartPoint().equals(departPoint) & route.getDepartPoint().equals(arrivalPoint)) return route;
        }
        throw new NotFoundException("Not found route: " + departPoint + " " + arrivalPoint);
    }

    @Override
    public Route getRouteDepart(Long id) throws NotFoundException {
        List<Route> list = fileLoad();
        for(Route route: list){
            if(route.getRouteId().equals(id)) return route;
        }
        throw new NotFoundException("Not found id: " + id);
    }


    public Boolean FileUnload(List<Route> list){

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

}
