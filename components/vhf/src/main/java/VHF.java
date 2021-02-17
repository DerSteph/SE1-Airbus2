public class VHF {
    // static instance
    private static VHF instance = new VHF();
    // port
    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private boolean isOn;
    private String[] channelList;
    private String selectedCannel;

    // private Constructor
    private VHF() {
        port = new Port();
    }

    // public static method getInstance
    public static VHF getInstance() {
        return instance;
    }

    // inner methods

    public String innerVersion() {
        return "VHF // " + manufacture + " - " + type + " - " + id;
    }
    public boolean innerOn(){
        isOn=true;
        return isOn;
    }
    public String[] innerSearch(){
        return channelList;
    }
    public String innerForwardChannel(){
        return "";
    }
    public  String innerBackwardChannel(){
        return "";
    }
    public String innerSelectChannel(String channel){
        channelList = new String [99];
        channelList[0]=channel;
        return "";
    }
    public boolean innerOff(){
        isOn=false;
        return isOn;
    }
    // inner class port
    public class Port implements IVHF {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public String[] search() {
            return innerSearch();
        }

        public String forwardChannel() {
            return innerForwardChannel();
        }

        public String backwardChannel() {
            return innerBackwardChannel();
        }

        public String selectChannel(String channel) {
            return innerSelectChannel(channel);
        }

        public boolean off() {
            return innerOff();
        }
        
    }
}