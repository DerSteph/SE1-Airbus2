public class SatCom {
    // static instance
    private static SatCom instance = new SatCom();
    // port
    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private boolean isConnected;
    // private Constructor
    private SatCom() {
        port = new Port();
    }

    // public static method getInstance
    public static SatCom getInstance() {
        return instance;
    }

    // inner methods

    public String innerVersion() {
        return "SatCom // " + manufacture + " - " + type + " - " + id;
    }
    public boolean innerOn(){
        isConnected=true;
        return isConnected;
    }
    public boolean innerConnect(String satelite, String frequency){
        return true;
    }
    public void innerSend(String request){
        
    }
    public String innerReceive(){
        return "";
    }
    public boolean innerOff(){
    isConnected =false;
    return isConnected;
    }
    
    // inner class port
    public class Port implements ISatCom {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean connect(String satelite, String frequency) {
            return innerConnect(satelite, frequency);
        }

        public void send(String request) {
            innerSend(request);
        }

        public String receive() {
            return innerReceive();
        }

        public boolean off() {
            return innerOff();
        }
        
    }
}