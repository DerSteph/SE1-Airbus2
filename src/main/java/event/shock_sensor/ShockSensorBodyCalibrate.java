package event.shock_sensor;

public class ShockSensorBodyCalibrate
{
    String level;

    public ShockSensorBodyCalibrate(String level) {
        this.level = level;
    }

    public String toString() {
        return "Event: ShockSensorBody - Calibrate";
    }
}
