public class OxygenBottle {
    // static instance
    private static OxygenBottle instance = new OxygenBottle();
    // port
    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private int amount = 100;

    // private Constructor
    private OxygenBottle() {
        port = new Port();
    }

    // public static method getInstance
    public static OxygenBottle getInstance() {
        return instance;
    }

    // inner methods

    public String innerVersion() {
        return "OxygenBottle // " + manufacture + " - " + type + " - " + id;
    }
    public int innerTakeOut(int amount){
     this.amount -= amount;
     return this.amount;
    }
    public int innerRefill(){
        amount = 100;
        return amount;
    }
    public int innerRefill(int amount){
        this.amount += amount;
        return this.amount;
    }

    // inner class port
    public class Port implements IOxygenBottle {
        public String version() {
            return innerVersion();
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