package event.cost_optimizer;

public class CostOptimizerValidate {

    private int costIndex;

    public CostOptimizerValidate(int costIndex) {
        this.costIndex = costIndex;
    }

    @Override
    public String toString() {
        return "Event: CostOptimizer - Validate";
    }

    public int getCostIndex() {
        return costIndex;
    }
}
