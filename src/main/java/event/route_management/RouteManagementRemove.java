package event.route_management;

public class RouteManagementRemove {

    private CheckPoint checkPoint;

    public RouteManagementRemove(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    @Override
    public String toString() {
        return "Event: RouteManagement - Remove";
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }
}
