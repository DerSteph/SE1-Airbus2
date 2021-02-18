package event.hydraulic_pump;

public class HydraulicPumpWingRefillOilAmount {

    int value;

    public HydraulicPumpWingRefillOilAmount(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Event: HydraulicPumpWing - RefillOilAmount";
    }
}
