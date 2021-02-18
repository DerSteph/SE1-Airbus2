public class RadarAltimeter
{
    // static instance
    private static final RadarAltimeter instance = new RadarAltimeter();
    // port
    public Port port;
    private final String manufacture = "x/x";
    private final String type = "team 10";
    private final String id = "7903471 / 2120099";
    private boolean isOn;
    private int altitude;

    private RadarAltimeter()
    {
        port = new Port();
    }

    public static RadarAltimeter getInstance()
    {
        return instance;
    }

    private String innerVersion()
    {
        return "PitotTube // " + manufacture + " - " + type + " - " + id;
    }

    private boolean innerOn()
    {
        isOn = true;
        return isOn;
    }

    private void innerSend(String radioWave)
    {

    }

    private int innerReceive(String radioWave)
    {
        return 1;
    }

    private int innerMeasureAltitude()
    {
        altitude = 5000;
        return altitude;
    }

    private boolean innerOff()
    {
        isOn = false;
        return isOn;
    }

    public class Port implements IRadarAltimeter
    {

        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public boolean on()
        {
            return innerOn();
        }

        @Override
        public void send(String radioWave)
        {
            innerSend(radioWave);
        }

        @Override
        public int receive(String radioWave)
        {
            return innerReceive(radioWave);
        }

        @Override
        public int measureAltitude()
        {
            return innerMeasureAltitude();
        }

        @Override
        public boolean off()
        {
            return innerOff();
        }
    }


}
