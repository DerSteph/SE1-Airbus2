package event.battery;

public class BatteryDischarge
{
    int percentage;

    public BatteryDischarge(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Event: Battery - Discharge";
    }
}
