package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    public int degreeSlat;
    // Add new fields below...
    public boolean isRightNavigationLightOn;
    public boolean isTailNavigationLightOn;
    public int exhaustGasTemperature;
    public boolean isAlarmMajorExhaustGasTemperatureSensor;
    public boolean isAlarmCriticalExhaustGasTemperatureSensor;
    public int fuelFlow;
}