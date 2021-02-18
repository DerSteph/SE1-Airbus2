package event.battery;

public class BatteryCharge
{
    int percentage;

    public BatteryCharge(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Event: Battery - Charge";
    }
}
