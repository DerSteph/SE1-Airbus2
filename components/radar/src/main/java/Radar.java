public class Radar {
    private static Radar instance = new Radar();

    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private boolean isOn;

    private Radar() {
        port = new Port();
    }

    public static Radar getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "Radar // " + manufacture + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerScan(String environment) {
        return environment.contains("plane");
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public class Port implements  IRadar {
        public String version() {
            return innerVersion();
        }
        public boolean on() {
            return innerOn();
        }
        public boolean scan(String environment) {
            return innerScan(environment);
        }
        public boolean off() {
            return innerOff();
        }
    }

}
