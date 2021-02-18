public class AirFlowSensor
{
    // static instance
    private static AirFlowSensor instance = new AirFlowSensor();
    // port
    public Port port;
    private String manufacturer = "Max Mustermann / Jemand Anderes";
    private String type = "team 10";
    private String id = "2120099 / 7903471";

    private int airPressure;
    private boolean isAlarm;

    // private constructor
    private AirFlowSensor()
    {
        port = new Port();
    }

    // static method getInstance
    public static AirFlowSensor getInstance()
    {
        return instance;
    }

    // inner methods
    public String innerVersion()
    {
        return "AirFlowSensor // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasure(String airFlow) {
        return airPressure;
    }

    public boolean innerAlarm(int threshold) {
        isAlarm = true;
        return isAlarm;
    }


    public class Port implements IAirFlowSensor
    {
        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public int measure(String airFlow)
        {
            return innerMeasure(airFlow);
        }

        @Override
        public boolean alarm(int threshold)
        {
            return innerAlarm(threshold);
        }
    }


}
