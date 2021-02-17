package event.portable_watertank;

public class PortableWaterTankTakeOut {
    private int amount;
    public PortableWaterTankTakeOut(int amount){
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Event: PortableWaterTank - TakeOut";
    }
}
