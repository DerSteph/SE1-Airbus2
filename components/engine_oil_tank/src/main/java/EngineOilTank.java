public class EngineOilTank
{
    // static instance
    private static final EngineOilTank instance = new EngineOilTank();
    // port
    public Port port;
    private final String manufacture = "x/x";
    private final String type = "team 10";
    private final String id = "7903471 / 2120099";
    private int level;

    private EngineOilTank()
    {
        port = new Port();
    }

    public static EngineOilTank getInstance()
    {
        return instance;
    }

    private String innerVersion()
    {
        return "EngineOilTank // " + manufacture + " - " + type + " - " + id;
    }

    private int innerIncreaseLevel(int value)
    {
        level += value;
        return level;
    }

    private int innerDecreaseLevel(int value)
    {
        level -= value;
        return level;
    }

    public class Port implements IEngineOilTank
    {

        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public int increaseLevel(int value)
        {
            return innerIncreaseLevel(value);
        }

        @Override
        public int decreaseLevel(int value)
        {
            return innerDecreaseLevel(value);
        }
    }


}
