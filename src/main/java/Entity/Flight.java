package Entity;

import java.util.Date;

public class Flight {

    private Long flightId,route;
    private String airbus;
    private Date departTime, travelTime;

    public Flight() {
    }

    public Flight(Long flightId, String airbus, Long route, Date departTime, Date travelTime) {
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

    public Long getRoute() {
        return route;
    }

    public void setRoute(Long route) {
        this.route = route;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date travelTime) {
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
