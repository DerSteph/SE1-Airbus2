package event.hydraulic_pump;

public class HydraulicPumpBodyRefillOilAmount {

    int value;

    public HydraulicPumpBodyRefillOilAmount(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Event: HydraulicPumpBody - RefillOilAmount";
    }

    public int getValue() {
        return value;
    }
}
