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
        return fileUnload(list);
    }


    @Override
    public Boolean delete(Route route) {
        List<Route> list = fileLoad();

        for(Route tmpRoute:list){
            if(route.getRouteId().equals(tmpRoute.getRouteId())) {
                list.remove(tmpRoute);
                break;
            }
        }
        if(!list.isEmpty())
            fileUnload(list);
        return true;
    }


    public List<Route> fileLoad(){

        BufferedReader reader;
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
            if(route.getDepartPoint().equals(departPoint) && route.getDepartPoint().equals(arrivalPoint)) return route;
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


    public Boolean fileUnload(List<Route> list){

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

