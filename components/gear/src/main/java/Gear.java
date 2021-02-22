import java.util.ArrayList;

public class Gear{

    // static instance
    private static Gear instance = new Gear();
    // port
    public Port port;
    private String manufacture = "Max Mustermann / Max Mustermann";
    private GearType type;
    private String id = "7862288 / 1633332";
    private boolean isDown = false;
    private ArrayList<Wheel> wheels = new ArrayList<>();

    private Gear(){
        port = new Port();
        Brake brake = new Brake("1", 0L);
        Brake brake2 = new Brake("2", 0L);
        wheels.add(new Wheel("1", 1L ,brake));
        wheels.add(new Wheel("2", 1L ,brake2));
    }

    // static method getInstance
    public static Gear getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "1";
    }

    public boolean innerDown() {
        isDown=true;
        System.out.println("Gear down");
        return isDown;
    }

    private GearType innerSetGearType(String type) {
        this.type= GearType.valueOf(type);
        return this.type;
    }

    public int innerSetBrake() {
        for (Wheel wheel : wheels) {
            wheel.getBrake().setPercentage(100);
        }
        System.out.println("Gear brake set to 100%");
        return 100;
    }

    public int innerSetBrake(int value) {
        if(value>=0 && value <=100){
            for (Wheel wheel : wheels) {
                wheel.getBrake().setPercentage(100);
            }
            System.out.println("Gear brake set to "+value+"%");
            return value;
        }
        return wheels.size()>0 ? wheels.get(0).getBrake().getPercentage() : 0;
    }

    public boolean innerUp() {
        isDown=false;
        System.out.println("Gear up");
        return isDown;
    }

    public int innerReleaseBrake(){
        for (Wheel wheel : wheels) {
            wheel.getBrake().setPercentage(0);
        }
        System.out.println("Gear brake released");
        return 0;
    }

    public class Port implements IGear {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean down() {
            return innerDown();
        }

        @Override
        public GearType setGearType(String type){
            return innerSetGearType(type);
        }

        @Override
        public int setBrake(int value) {
            return innerSetBrake(value);
        }

        @Override
        public int setBrake() {
            return innerSetBrake();
        }

        @Override
        public boolean up() {
            return innerUp();
        }

        @Override
        public int releaseBrake(){
            return innerReleaseBrake();
        }
    }
}
