public class WasteWaterTank {
    // static instance
    private static WasteWaterTank instance = new WasteWaterTank();
    // port
    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private int capacity = 1000;
    private boolean isLocked;

    // private Constructor
    private WasteWaterTank() {
        port = new Port();
    }

    // public static method getInstance
    public static WasteWaterTank getInstance() {
        return instance;
    }

    // inner methods

    public String innerVersion() {
        return "WasteWaterTank // " + manufacture + " - " + type + " - " + id;
    }
    public boolean innerLock(){
        isLocked = true;
        return true;
    }
    public boolean innerUnlock(){
        isLocked = false;
        return false;
    }
    public int innerAdd(int amount){
        capacity -= amount;
        return capacity;
    }
   public int innerPumpOut(){
        capacity = 1000;
        return capacity;
   }

    // inner class port
    public class Port implements IWasteWaterTank {
        public String version() {
            return innerVersion();
        }

        public boolean lock() {
            return innerLock();
        }

        public boolean unlock() {
            return innerUnlock();
        }

        public int add(int amount) {
            return innerAdd(amount);
        }

        public int pumpOut() {
            return innerPumpOut();
        }

    }
}
