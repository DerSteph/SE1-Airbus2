package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    public int degreeSlat;

    public boolean isShockSensorBodyShockDetected;
    public boolean isShockSensorBodyCalibrated;
    public boolean isShockSensorWingShockDetected;
    public boolean isShockSensorWingCalibrated;

    public boolean isStallingSensorAlarm;

    public int temperatureBody;
    public boolean isTemperatureSensorBodyAlarm;
    public int TemperatureWing;
    public boolean isTemperatureSensorWingAlarm;

    // Add new fields below...
}