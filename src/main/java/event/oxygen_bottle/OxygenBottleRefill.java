package event.oxygen_bottle;

public class OxygenBottleRefill {
    private int amount;
    public OxygenBottleRefill(){
        amount = 100;
    }
    public OxygenBottleRefill(int amount){
        this.amount += amount;
    }
    @Override
    public String toString() {
        return "Event: OxygenBottle - Refill";
    }
}