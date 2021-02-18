import java.util.ArrayList;

public class RouteManagement {

    private static RouteManagement instance = new RouteManagement();

    // Port
    public Port port;

    private String manufacturer = "5703004 / 1716504";

    private boolean isOn;
    private ArrayList<CheckPoint> checkPoints;
    private double costIndex;

    // Private constructor
    private RouteManagement() {
        checkPoints = new ArrayList<>();
        port = new Port();
    }

    // Static method getInstance
    public static RouteManagement getInstance() {
        return instance;
    }

    // Methods
    private boolean innerOn() {
        isOn = true;
        return isOn;
    }

    private int innerAdd(CheckPoint checkPoint) {
        checkPoints.add(checkPoint);
        return checkPoints.size();
    }

    private int innerRemove(CheckPoint checkPoint) {
        checkPoints.remove(checkPoint);
        return checkPoints.size();
    }

    private double innerSetCostIndex(int value) {
        this.costIndex = value;
        return costIndex;
    }

    private boolean innerOff() {
        isOn = false;
        return isOn;
    }

    // Inner class port
    public class Port implements IRouteManagement {

        @Override
        public String version() {
            return "LeftAileron // " + manufacturer;
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public int add(CheckPoint checkPoint) {
            return innerAdd(checkPoint);
        }

        @Override
        public int remove(CheckPoint checkPoint) {
            return innerRemove(checkPoint);
        }

        @Override
        public void printCheckPoints() {
            for (CheckPoint p : checkPoints) {
                System.out.println(p.toString());
            }
        }

        @Override
        public double setCostIndex(int value) {
            return innerSetCostIndex(value);
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}
