package event.shock_sensor;

public class ShockSensorWingCalibrate
{
    String level;

    public ShockSensorWingCalibrate(String level) {
        this.level = level;
    }

    public String toString() {
        return "Event: ShockSensorWing - Calibrate";
    }
}
