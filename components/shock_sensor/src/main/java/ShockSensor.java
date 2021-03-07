public class ShockSensor
{
    // static instance
    private static ShockSensor instance = new ShockSensor();
    // port
    public Port port;
    private String manufacturer = "Max Mustermann / Jemand Anderes";
    private String type = "team 10";
    private String id = "2120099 / 7903471";

    private boolean isCalibrated;
    private boolean isShockDetected;

    // private constructor
    private ShockSensor()
    {
        port = new Port();
    }

    // static method getInstance
    public static ShockSensor getInstance()
    {
        return instance;
    }

    // inner methods
    public String innerVersion()
    {
        return "ShockSensor // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerCalibrate()
    {
        isCalibrated = true;
        return isCalibrated;
    }

    public boolean innerCalibrate(double level) {
        isCalibrated = true;
        return isCalibrated;
    }

    public int innerMeasure() {
        return 21;
    }

    public boolean innerAlarm() {
        isShockDetected = true;
        return isShockDetected;
    }


    public class Port implements IShockSensor
    {
        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public boolean calibrate()
        {
            return innerCalibrate();
        }

        @Override
        public boolean calibrate(double level)
        {
            return innerCalibrate(level);
        }

        @Override
        public int measure()
        {
            return innerMeasure();
        }

        @Override
        public boolean alarm()
        {
            return innerAlarm();
        }
    }
}
