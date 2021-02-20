package event.cost_optimizer;

import event.route_management.CheckPoint;

public class CostOptimizerAddCheckPoint {

    private CheckPoint checkPoint;

    public CostOptimizerAddCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    @Override
    public String toString() {
        return "Event: CostOptimizer - AddCheckPoint";
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }
}
