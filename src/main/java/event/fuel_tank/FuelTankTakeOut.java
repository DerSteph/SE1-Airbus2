package event.fuel_tank;

public class FuelTankTakeOut
{
    int amount;

    public FuelTankTakeOut(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Event: FuelTank - TakeOut";
    }
}
