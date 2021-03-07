package event.cost_optimizer;

import event.route_management.CheckPoint;

import java.util.ArrayList;

public class CostOptimizerOptimize {

    private ArrayList<CheckPoint> checkPointList;
    
    public CostOptimizerOptimize(ArrayList<CheckPoint> checkPointList) {
        this.checkPointList = checkPointList;
    }
    
    @Override
    public String toString() {
        return "Event: CostOptimizer - Optimize";
    }
    
    public ArrayList<CheckPoint> getCheckPointList() {
        return checkPointList;
    }
}
