public class DeIcingSystem
{
    // static instance
    private static final DeIcingSystem instance = new DeIcingSystem();
    // port
    public Port port;
    private final String manufacture = "x/x";
    private final String type = "team 10";
    private final String id = "7903471 / 2120099";
    private int amount = 1000;

    private DeIcingSystem()
    {
        port = new Port();
    }

    public static DeIcingSystem getInstance()
    {
        return instance;
    }

    private String innerVersion() {
        return "DeIcingSystem // " + manufacture + " - " + type + " - " + id;
    }

    private boolean innerActivate()
    {
        return true;
    }

    private int innerDeIce(int amount)
    {
        this.amount -= amount;
        return this.amount;
    }

    private int innerRefill()
    {
        amount = 1000;
        return amount;
    }

    private boolean innerDeactivate()
    {
        return false;
    }

    public class Port implements IDeIcingSystem
    {

        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public boolean activate()
        {
            return innerActivate();
        }

        @Override
        public int deIce(int amount)
        {
            return innerDeIce(amount);
        }

        @Override
        public int refill()
        {
            return innerRefill();
        }

        @Override
        public boolean deactivate()
        {
            return innerDeactivate();
        }
    }
}
