
public class AirConditioning{

    // static instance
    private static AirConditioning instance = new AirConditioning();
    // port
    public Port port;
    private String manufacture = "Max Mustermann / Max Mustermann";
    private String type = "Team 7";
    private String id = "7862288 / 1633332";
    private int temperature = 0;
    private boolean isOn = false;

    private AirConditioning(){
        port = new Port();
    }

    // static method getInstance
    public static AirConditioning getInstance() {
        return instance;
    }

    public String innerVersion() {
        return "1";
    }

    public boolean innerOn(){
        isOn = true;
        System.out.println("Air conditioning on");
        return true;
    }

    public boolean innerOff(){
        isOn = false;
        System.out.println("Air conditioning off");
        return true;
    }

    public String innerClean(String airFlow){
        System.out.println("Air conditioning cleaned");
        return airFlow;
    }

    public int innerHeat(String airFlow, int temperature){
        System.out.println("Air conditioning heat temperature set to "+temperature);
        this.temperature=temperature;
        return temperature;
    }

    public int innerCool(String airFlow, int temperature){
        System.out.println("Air conditioning cooling temperature set to "+temperature);
        this.temperature=temperature;
        return temperature;
    }

    public class Port implements IAirConditioning {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerOn();
        }

        @Override
        public boolean off() {
            return innerOff();
        }

        @Override
        public String clean(String airFlow) {
            return innerClean(airFlow);
        }

        @Override
        public int heat(String airFlow, int temperature) {
            return innerHeat(airFlow,temperature);
        }

        @Override
        public int cool(String airFlow, int temperature) {
            return innerCool(airFlow,temperature);
        }

    }
}
