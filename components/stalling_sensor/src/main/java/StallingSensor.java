public class StallingSensor
{
    // static instance
    private static StallingSensor instance = new StallingSensor();
    // port
    public Port port;
    private String manufacturer = "Max Mustermann / Jemand Anderes";
    private String type = "team 10";
    private String id = "2120099 / 7903471";

    private boolean isAlarm;

    // private constructor
    private StallingSensor()
    {
        port = new Port();
    }

    // static method getInstance
    public static StallingSensor getInstance()
    {
        return instance;
    }

    // inner methods
    public String innerVersion()
    {
        return "StallingSensor // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasure(String airFlow) {
        return 21;
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return isAlarm;
    }


    public class Port implements IStallingSensor
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
        public boolean alarm()
        {
            return innerAlarm();
        }
    }
}
