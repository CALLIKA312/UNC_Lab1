package org.pelmen.Entity;

public class Route {
    private Long routeId;
    private String departPoint;
    private String arrivalPoint;

    public Route(String departPoint, String arrivalPoint) {
        this.departPoint = departPoint;
        this.arrivalPoint = arrivalPoint;
    }
    public Long getRouteId(){
        return this.routeId;
    }
    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getDepartPoint(){
        return this.departPoint;
    }
    public void setDepartPoint(String departPoint) {
        this.departPoint = departPoint;
    }

    public String getArrivalPoint(){
        return this.arrivalPoint;
    }
    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }
}