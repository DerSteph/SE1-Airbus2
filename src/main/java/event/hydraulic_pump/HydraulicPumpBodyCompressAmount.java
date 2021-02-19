package event.hydraulic_pump;

public class HydraulicPumpBodyCompressAmount {

    int value;

    public HydraulicPumpBodyCompressAmount(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Event: HydraulicPumpBody - CompressAmount";
    }

    public int getValue() {
        return value;
    }
}
