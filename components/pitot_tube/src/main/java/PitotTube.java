public class PitotTube
{
    // static instance
    private static final PitotTube instance = new PitotTube();
    // port
    public Port port;
    private final String manufacture = "x/x";
    private final String type = "team 10";
    private final String id = "7903471 / 2120099";
    private boolean isCleaned = false;
    private int velocity = 30;

    private PitotTube()
    {
        port = new Port();
    }

    public static PitotTube getInstance()
    {
        return instance;
    }

    private String innerVersion() {
        return "PitotTube // " + manufacture + " - " + type + " - " + id;
    }

    private int innerMeasureStaticPressure(){
        return 10;
    }

    private int innerMeasureTotalPressure(){
        return 20;
    }

    private int innerMeasureVelocity(){
        return velocity;
    }

    private boolean innerClean()
    {
        isCleaned = true;
        return isCleaned;
    }


    public class Port implements IPitotTube
    {
        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public int measureStaticPressure()
        {
            return innerMeasureStaticPressure();
        }

        @Override
        public int measureTotalPressure()
        {
            return innerMeasureTotalPressure();
        }

        @Override
        public int measureVelocity()
        {
            return innerMeasureVelocity();
        }

        @Override
        public void clean()
        {
            innerClean();
        }
    }
}
