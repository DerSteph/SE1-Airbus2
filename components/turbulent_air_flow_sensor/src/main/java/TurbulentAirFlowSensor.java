public class TurbulentAirFlowSensor {
    private static TurbulentAirFlowSensor instance = new TurbulentAirFlowSensor();

    public Port port;
    private String manufacture = "Baniel Bierwicki / Baniel Bierwicki";
    private String type = "team 11";
    private String id = "3326612 / 2516708";

    private boolean isAlarm = false;

    private TurbulentAirFlowSensor() {
        port = new Port();
    }

    public static TurbulentAirFlowSensor getInstance() {
        return instance;
    }

    //inner methods
    public String innerVersion() {
        return "TurbulentAirFlowSensor // " + manufacture + " - " + type + " - " + id;
    }

    public int innerMeasure(String airFlow) {
        return 100;
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return isAlarm;
    }

    public class Port implements ITurbulentAirFlowSensor {
        public String version() {
            return version();
        }

        public int measure(String airFlow) {
            return innerMeasure(airFlow);
        }

        public boolean alarm() {
            return innerAlarm();
        }
    }
}
