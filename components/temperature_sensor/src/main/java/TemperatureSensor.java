public class TemperatureSensor
{
    // static instance
    private static TemperatureSensor instance = new TemperatureSensor();
    // port
    public Port port;
    private String manufacturer = "Max Mustermann / Jemand Anderes";
    private String type = "team 10";
    private String id = "2120099 / 7903471";

    private int temperature = 0;
    private boolean isAlarm;

    // private constructor
    private TemperatureSensor()
    {
        port = new Port();
    }

    // static method getInstance
    public static TemperatureSensor getInstance()
    {
        return instance;
    }

    // inner methods
    public String innerVersion()
    {
        return "TemperatureSensor // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasure() {
        return temperature;
    }

    public boolean innerAlarm(int threshold) {
        isAlarm = true;
        return isAlarm;
    }


    public class Port implements ITemperatureSensor
    {
        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public int measure()
        {
            return innerMeasure();
        }

        @Override
        public boolean alarm(int threshold)
        {
            return innerAlarm(threshold);
        }
    }
}
