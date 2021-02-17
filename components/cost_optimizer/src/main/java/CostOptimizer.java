import java.util.ArrayList;

public class CostOptimizer {

    private static CostOptimizer instance = new CostOptimizer();

    // Port
    public Port port;

    private String manufacturer = "<student_name_01> / <student_name_02>";
    private String type = "team <id>";
    private String id = "<student_id_01> / <student_id_02>";

    private boolean isOn;
    private ArrayList<CheckPoint> checkPointList;
    private int costIndex;

    // Private constructor
    private CostOptimizer() {
        checkPointList = new ArrayList<>();
        port = new Port();
    }

    // Static method getInstance
    public static CostOptimizer getInstance() {
        return instance;
    }

    // Methods
    private boolean innerOn(){
        isOn = true;
        return isOn;
    }

    private int innerAdd(CheckPoint checkPoint) {
        checkPointList.add(checkPoint);
    }

    private int innerRemove(int checkPoint) {
        checkPointList.remove(checkPoint);
    }

    private boolean innerValidate(int costIndex) {
        throw new RuntimeException("Not implemented yet!");
    }

    private boolean innerOff(){
        isOn = false;
        return isOn;
    }

    // Inner class port
    public class Port implements ICostOptimizer {

        @Override
        public String version() {
            return "CostOptimizer // " + manufacturer + " - " + type + " - " + id;
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
        public int remove(int checkPoint) {
            return innerRemove(checkPoint);
        }

        @Override
        public boolean validate(int costIndex) {
            return innerValidate(costIndex);
        }

        @Override
        public boolean off() {
            return innerOff();
        }
    }
}