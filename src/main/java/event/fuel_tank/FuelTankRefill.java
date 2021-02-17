package event.fuel_tank;

public class FuelTankRefill
{
    int amount;

    public FuelTankRefill() {}

    public FuelTankRefill(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Event: FuelTank - Refill";
    }
}
