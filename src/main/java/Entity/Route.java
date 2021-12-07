package Entity;

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

    public Route(Long routeId, String departPoint, String arrivalPoint) {
        this.RouteId = routeId;
        this.departPoint = departPoint;
        this.arrivalPoint = arrivalPoint;
    }

    public Long getRouteId(){return this.RouteId;}

    public void setRouteId(Long RouteId){this.RouteId=RouteId;}

    public String getDepartPoint() {
        return this.departPoint;
    }

    public void setDepartPoint(String departPoint) {
        this.departPoint = departPoint;
    }

    public String getArrivalPoint() {
        return this.arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public String toString() {
        return "Route{departPoint='" + this.departPoint + '\'' + ", arrivalPoint='" + this.arrivalPoint + '\'' + '}';
    }
}