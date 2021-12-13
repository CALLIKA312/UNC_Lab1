package Entity;

import lombok.Data;

@Data
public class Route {
    private Long RouteId;
    private String departPoint;
    private String arrivalPoint;

    public Route() {
    }

    public Route(String departPoint, String arrivalPoint) {
        this.departPoint = departPoint;
        this.arrivalPoint = arrivalPoint;
    }
}