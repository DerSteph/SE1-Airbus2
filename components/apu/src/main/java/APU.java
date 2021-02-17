
public class APU{

    // static instance
    private static APU instance = new APU();
    // port
    public Port port;
    private String manufacture = "Max Mustermann / Max Mustermann";
    private String type = "Team 7";
    private String id = "7862288 / 1633332";
    private boolean isStarted = false;
    private int rpm = 0;

    private APU(){
        port = new Port();
    }

    // static method getInstance
    public static APU getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "1";
    }

    public boolean innerStart() {
        isStarted=true;
        System.out.println("APU started");
        return true;
    }

    public int innerIncreaseRPM(int value) {
        rpm+=value;
        System.out.println("APU RPM increased by "+value+" to "+rpm);
        return rpm;
    }

    public int innerDecreaseRPM(int value) {
        if(rpm>=value){
            rpm-=value;
            System.out.println("APU RPM decreased by "+value+" to "+rpm);
        }
        else{
            rpm=0;
            System.out.println("APU RPM decreased to 0");
        }
        return rpm;
    }

    public boolean innerShutdown() {
        isStarted=false;
        System.out.println("APU shutdown");
        return true;
    }

    public class Port implements IAPU {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean start() {
            return innerStart();
        }

        @Override
        public int increaseRPM(int value) {
            return innerIncreaseRPM(value);
        }

        @Override
        public int decreaseRPM(int value) {
            return innerDecreaseRPM(value);
        }

        @Override
        public boolean shutdown() {
            return innerShutdown();
        }
    }
}
