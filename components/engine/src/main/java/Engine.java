
public class Engine{

    // static instance
    private static Engine instance = new Engine();
    // port
    public Port port;
    private String manufacture = "Max Mustermann / Max Mustermann";
    private String type = "Team 7";
    private String id = "7862288 / 1633332";
    private boolean isStarted = false;
    private boolean isFire = false;
    private int rpm = 0;

    private Engine(){
        port = new Port();
    }

    // static method getInstance
    public static Engine getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "1";
    }

    public boolean innerStart() {
        isStarted=true;
        System.out.println("Engine started");
        return isStarted;
    }

    public int innerIncreaseRPM(int value) {
        rpm+=value;
        System.out.println("Engine RPM increased by "+value+" to "+rpm);
        return rpm++;
    }

    public int innerDecreaseRPM(int value) {
        if(rpm>=value){
            rpm-=value;
            System.out.println("Engine RPM decreased by "+value+" to "+rpm);
        }
        else{
            rpm=0;
            System.out.println("Engine RPM decreased to 0");
        }
        return rpm;
    }

    public boolean innerShutdown() {
        isStarted=false;
        System.out.println("Engine shutdown");
        return isStarted;
    }

    public void innerExtinguishFire(){
        isFire = false;
        System.out.println("Fire extinguished");
    }

    public class Port implements IEngine {

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

        @Override
        public void extinguishFire(){
            innerExtinguishFire();
        }
    }
}
