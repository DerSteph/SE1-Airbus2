public class PortableWaterTank {
    // static instance
    private static PortableWaterTank instance = new PortableWaterTank();
    // port
    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private int amount = 1000;
    private boolean isLocked;

    // private Constructor
    private PortableWaterTank() {
        port = new Port();
    }

    // public static method getInstance
    public static PortableWaterTank getInstance() {
        return instance;
    }

    // inner methods

    public String innerVersion() {
        return "PortableWaterTank // " + manufacture + " - " + type + " - " + id;
    }
    public boolean innerLock(){
        isLocked = true;
        return true;
    }
    public boolean innerUnlock(){
        isLocked = false;
        return false;
    }
    public int innerTakeOut(int amount){
        this.amount -=amount;
        return this.amount;
    }
    public int innerRefill(){
        amount = 1000;
        return  amount;
    }
    public int innerRefill(int amount){
        this.amount +=amount;
        return this.amount;
    }


    // inner class port
    public class Port implements IPortableWaterTank {
        public String version() {
            return innerVersion();
        }

        public boolean lock() {
            return innerLock();
        }

        public boolean unlock() {
            return innerUnlock();
        }

        public int takeOut(int amount) {
            return innerTakeOut(amount);
        }

        public int refill() {
            return innerRefill();
        }

        public int refill(int amount) {
            return innerRefill(amount);
        }
        
    }
}