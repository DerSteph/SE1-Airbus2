package event.shock_sensor;

public class ShockSensorBodyCalibrate
{
    double level;

    public ShockSensorBodyCalibrate(double level) {
        this.level = level;
    }

    public String toString() {
        return "Event: ShockSensorBody - Calibrate";
    }
}
