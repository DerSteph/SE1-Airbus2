package event.hydraulic_pump;

public class HydraulicPumpWingCompressAmount {

    int value;

    public HydraulicPumpWingCompressAmount(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Event: HydraulicPumpWing - CompressAmount";
    }

    public int getValue() {
        return value;
    }
}
