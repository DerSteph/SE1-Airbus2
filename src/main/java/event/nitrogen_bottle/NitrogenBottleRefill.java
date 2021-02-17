package event.nitrogen_bottle;

public class NitrogenBottleRefill {
    private int amount;
    public NitrogenBottleRefill(){
        amount = 100;
    }
    public NitrogenBottleRefill(int amount){
        this.amount += amount;
    }
    @Override
    public String toString() {
        return "Event: OxygenBottle - Refill";
    }
}