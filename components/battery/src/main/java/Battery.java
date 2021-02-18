public class Battery
{
    // static instance
    private static final Battery instance = new Battery();
    // port
    public Port port;
    private final String manufacturer = "7903471";
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
        return "Battery // " + manufacturer;
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
