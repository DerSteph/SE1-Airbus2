package event.wastewater_tank;

public class WasteWaterTankAdd {
    int amount;
    public WasteWaterTankAdd(int amount){
        this.amount +=amount;
    }
    @Override
    public String toString() {
        return "Event: WasteWaterTank - Add";
    }
}
