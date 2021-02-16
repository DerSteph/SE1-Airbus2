package event.route_management;

import event.Subscriber;

public class RouteManagementAdd extends Subscriber {

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
