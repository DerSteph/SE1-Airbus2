public class GPS {
    private static GPS instance = new GPS();

    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private boolean isOn;
    private boolean isConnected;

    private GPS() {
        port = new Port();
    }

    public static GPS getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "TCAS // " + manufacture + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerConnect(String satelite) {
        isConnected = true;
        return isConnected;
    }

    public String innerSend(String request) {
        return "send";
    }

    public String innerReceive() {
        return "receive";
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public class Port implements IGPS {
        public String version() {
            return innerVersion();
        }
        public boolean on() {
            return innerOn();
        }
        public boolean connect(String satelite) {
            return innerConnect(satelite);
        }
        public String send(String request) {
            return innerSend(request);
        }
        public String receive() {
            return innerReceive();
        }
        public boolean off() {
            return innerOff();
        }
    }
}
