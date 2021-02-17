import java.util.ArrayList;

public class Kitchen{

    // static instance
    private static Kitchen instance = new Kitchen();
    // port
    public Port port;
    private String manufacture = "Max Mustermann / Max Mustermann";
    private KitchenType type;
    private String id = "7862288 / 1633332";
    private boolean isFilled = false;
    private boolean isLocked = false;
    private ArrayList<Trolley> trolleysList = new ArrayList<>();

    private Kitchen(){
        port = new Port();
    }

    // static method getInstance
    public static Kitchen getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "1";
    }

    public boolean innerLock() {
        isLocked=true;
        System.out.println("Kitchen locked");
        return true;
    }

    public boolean innerUnlock() {
        isLocked=false;
        System.out.println("Kitchen unlocked");
        return true;
    }

    public double innerGetTotalWeightTrolleys() {
        System.out.println("Kitchen Trolley weight requested");
        return 0.0;
    }

    public void innerAddTrolley() {
        trolleysList.add(new Trolley());
        System.out.println("Kitchen Trolley added");
    }

    public class Port implements IKitchen {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean lock() {
            return innerLock();
        }

        @Override
        public boolean unlock() {
            return innerUnlock();
        }

        @Override
        public double getTotalWeightTrolleys() {
            return innerGetTotalWeightTrolleys();
        }

        @Override
        public void addTrolley() {
           innerAddTrolley();
        }


    }
}
