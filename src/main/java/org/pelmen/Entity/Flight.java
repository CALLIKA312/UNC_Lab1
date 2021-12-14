package org.pelmen.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Flight {

    private Long flightId, route;
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

    public Flight(String airbus, Long route, Date departTime, Date travelTime) {
        this.airbus = airbus;
        this.route = route;
        this.departTime = departTime;
        this.travelTime = travelTime;
    }

}
