package event.route_management;

import event.Subscriber;

public class RouteManagementSetCostIndex extends Subscriber {

    private int value;

    public RouteManagementSetCostIndex(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Event: RouteManagement - SetCostIndex";
    }
}
