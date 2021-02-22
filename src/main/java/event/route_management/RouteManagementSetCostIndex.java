package event.route_management;

public class RouteManagementSetCostIndex {

    private int value;

    public RouteManagementSetCostIndex(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Event: RouteManagement - SetCostIndex";
    }

    public int getValue() {
        return value;
    }
}
