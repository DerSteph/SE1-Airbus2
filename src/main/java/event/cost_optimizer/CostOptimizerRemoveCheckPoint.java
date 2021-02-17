package event.cost_optimizer;

import event.route_management.CheckPoint;

public class CostOptimizerRemoveCheckPoint {

    private CheckPoint checkPoint;

    public CostOptimizerRemoveCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    @Override
    public String toString() {
        return "Event: CostOptimizer - RemoveCheckPoint";
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }
}
