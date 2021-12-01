package Entity;

import javax.xml.crypto.Data;

public class Flight {

    private Long flightId;
    private String airbus, route;
    private Data departTime, travelTime;

    public Flight() {
    }

    public Flight(Long flightId, String airbus, String route, Data departTime, Data travelTime) {
        this.flightId = flightId;
        this.airbus = airbus;
        this.route = route;
        this.departTime = departTime;
        this.travelTime = travelTime;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getAirbus() {
        return airbus;
    }

    public void setAirbus(String airbus) {
        this.airbus = airbus;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Data getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Data departTime) {
        this.departTime = departTime;
    }

    public Data getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Data travelTime) {
        this.travelTime = travelTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", airbus='" + airbus + '\'' +
                ", route='" + route + '\'' +
                ", departTime=" + departTime +
                ", travelTime=" + travelTime +
                '}';
    }
}
