public class Battery
{
    // static instance
    private static final Battery instance = new Battery();
    // port
    public Port port;
    private final String manufacture = "x/x";
    private final String type = "team 10";
    private final String id = "7903471 / 2120099";
    private int percentage;

    private Battery()
    {
        port = new Port();
    }

    public static Battery getInstance()
    {
        return instance;
    }

    private String innerVersion() {
        return "Battery // " + manufacture + " - " + type + " - " + id;
    }

    private int innerCharge()
    {
        this.percentage = 100;
        return this.percentage;
    }

    private int innerDischarge()
    {
        this.percentage = 0;
        return this.percentage;
    }


    public class Port implements IBattery
    {

        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public int charge()
        {
            return innerCharge();
        }

        @Override
        public int discharge()
        {
            return innerDischarge();
        }
    }
}
