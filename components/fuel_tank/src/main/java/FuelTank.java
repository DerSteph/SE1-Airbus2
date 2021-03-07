public class FuelTank
{
    // static instance
    private static final FuelTank instance = new FuelTank();
    // port
    public Port port;
    private final String manufacture = "x/x";
    private final String type = "team 10";
    private final String id = "7903471 / 2120099";
    private int amount = 1000;

    private FuelTank()
    {
        port = new Port();
    }

    public static FuelTank getInstance()
    {
        return instance;
    }

    private String innerVersion()
    {
        return "FuelTank // " + manufacture + " - " + type + " - " + id;
    }

    private int innerTakeOut(int amount)
    {
        this.amount -= amount;
        return this.amount;
    }

    private int innerRefill()
    {
        this.amount = 1000;
        return this.amount;
    }

    private int innerRefill(int amount)
    {
        this.amount += amount;
        return this.amount;
    }

    public class Port implements IFuelTank
    {

        @Override
        public String version()
        {
            return innerVersion();
        }

        @Override
        public int takeOut(int amount)
        {
            return innerTakeOut(amount);
        }

        @Override
        public int refill()
        {
            return innerRefill();
        }

        @Override
        public int refill(int refill)
        {
            return innerRefill(refill);
        }
    }

}
