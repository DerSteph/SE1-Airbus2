public class DeIcingSystem
{
    // static instance
    private static final DeIcingSystem instance = new DeIcingSystem();
    // port
    public Port port;
    private final String manufacturer = "7903471";

    private int amount = 1000;
    private boolean isActivated;

    private DeIcingSystem()
    {
        port = new Port();
    }

    public static DeIcingSystem getInstance()
    {
        return instance;
    }

    private String innerVersion() {
        return "DeIcingSystem // " + manufacturer;
    }

    private boolean innerActivate()
    {
         isActivated = true;
         return isActivated;
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
        isActivated = false;
        return isActivated;
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
