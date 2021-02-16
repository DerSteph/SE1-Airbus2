package event.route_management;

import event.Subscriber;

public class RouteManagementRemove extends Subscriber {

    private CheckPoint checkPoint;

    public RouteManagementRemove(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    @Override
    public String toString() {
        return "Event: RouteManagement - Remove";
    }
}
