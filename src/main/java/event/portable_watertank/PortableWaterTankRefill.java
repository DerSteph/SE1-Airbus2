package event.portable_watertank;

public class PortableWaterTankRefill {
    private int amount;
    public PortableWaterTankRefill(){
        amount = 1000;
    }
    public PortableWaterTankRefill(int amount){
        this.amount += amount;
    }
    @Override
    public String toString() {
        return "Event: PortableWaterTank - Refill";
    }
}
