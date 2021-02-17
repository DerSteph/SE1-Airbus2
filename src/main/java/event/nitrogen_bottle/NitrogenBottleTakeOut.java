package event.nitrogen_bottle;

public class NitrogenBottleTakeOut {
    private int amount;
    public NitrogenBottleTakeOut(int amount){
        this.amount -= amount;
    }

    @Override
    public String toString() {
        return "Event: NitrogenBottle - TakeOut";
    }
}