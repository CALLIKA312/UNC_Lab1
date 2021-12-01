package Entity;

public class Route {

    private String departPoint, arrivalPoint;

    public Route() {
    }

    public Route(String departPoint, String arrivalPoint) {
        this.departPoint = departPoint;
        this.arrivalPoint = arrivalPoint;
    }

    public String getDepartPoint() {
        return departPoint;
    }

    public void setDepartPoint(String departPoint) {
        this.departPoint = departPoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    @Override
    public String toString() {
        return "Route{" +
                "departPoint='" + departPoint + '\'' +
                ", arrivalPoint='" + arrivalPoint + '\'' +
                '}';
    }
}
