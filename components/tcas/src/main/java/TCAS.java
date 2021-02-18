public class TCAS {
    // static instance
    private static TCAS instance = new TCAS();
    // port
    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";
    private boolean isOn = false;
    private boolean isConnected = false;
    private boolean isAlarm = false;
    private int altitude = 0;

    private TCAS() {
        port = new Port();
    }

    // static method getInstance
    public static TCAS getInstance() {
        return instance;
    }

    //inner methods
    public String innerVersion() {
        return "TCAS // " + manufacture + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerConnect(String frequency) {
        isConnected = true;
        return isConnected;
    }

    public boolean innerScan(String environment) {
        // ist das richtig?
        return true;
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return isAlarm;
    }

    public int innerDetermineAltitude(String environment) {
        return altitude;
    }

    public int innerSetAltitude(int value) {
        altitude = value;
        return altitude;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public class Port implements ITCAS {
        public String version()
        {
            return innerVersion();
        }
        public boolean on() {
            return innerOn();
        }
        public boolean connect(String frequency) {
            return innerConnect(frequency);
        }
        public boolean scan(String environment) {
            return innerScan(environment);
        }
        public boolean alarm() {
            return innerAlarm();
        }
        public int determineAltitude(String environment) {
            return innerDetermineAltitude(environment);
        }
        public int setAltitude(int value) {
            return innerSetAltitude(value);
        }
        public boolean off() {
            return innerOff();
        }
    }
}
