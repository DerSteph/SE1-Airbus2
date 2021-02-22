package event.cost_optimizer;

public class CostOptimizerRemoveCheckPoint {

    private int checkPointIndex;

    public CostOptimizerRemoveCheckPoint(int checkPointIndex) {
        this.checkPointIndex = checkPointIndex;
    }

    @Override
    public String toString() {
        return "Event: CostOptimizer - RemoveCheckPoint";
    }

    public int getCheckPointIndex() {
        return checkPointIndex;
    }
}
