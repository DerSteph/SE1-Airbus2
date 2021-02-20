
public class HydraulicPump{

    // static instance
    private static HydraulicPump instance = new HydraulicPump();
    // port
    public Port port;
    private String manufacture = "Max Mustermann / Max Mustermann";
    private String type = "Team 7";
    private String id = "7862288 / 1633332";
    private int amount = 1000;

    private HydraulicPump(){
        port = new Port();
    }

    // static method getInstance
    public static HydraulicPump getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "1";
    }

    public int innerCompress(){
        System.out.println("HydraulicPump compressing "+amount);
        return amount;
    }

    public int innerCompress(int amount){
        System.out.println("HydraulicPump compressing "+amount);
        return amount;
    }

    public int innerDecompress(){
        System.out.println("HydraulicPump decompressing "+amount);
        return amount;
    }

    public int innerRefillOil(){
        System.out.println("HydraulicPump refueled");
        amount=1000;
        return amount;
    }

    public int innerRefillOil(int amount){
        System.out.println("HydraulicPump refueled for "+amount);
        this.amount+=amount;
        return amount;
    }

    public class Port implements IHydraulicPump {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int compress() {
            return innerCompress();
        }

        @Override
        public int compress(int amount) {
            return innerCompress(amount);
        }

        @Override
        public int decompress() {
            return innerDecompress();
        }

        @Override
        public int refillOil() {
            return innerRefillOil();
        }

        @Override
        public int refillOil(int amount) {
            return innerRefillOil(amount);
        }

    }
}
