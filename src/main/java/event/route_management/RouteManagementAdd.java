package event.route_management;

public class RouteManagementAdd {

    private CheckPoint checkPoint;

    public RouteManagementAdd(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    @Override
    public String toString() {
        return "Event: RouteManagement - Add";
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }
}
