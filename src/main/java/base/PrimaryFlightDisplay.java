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

    public int airPressure;
    public boolean isAirFlowSensorBodyAlarm;
    public boolean isAirFlowSensorWingAlarm;

    public int percentageBattery;
    public boolean isDeIcingSystemActivated;
    public int amountDeIcingSystem;
    public boolean isPitotTubeCleaned;
    public int velocity;
    public boolean isRadarAltimeterOn;
    public int altitudeRadarAltimeter;
    public int levelEngineOilTank;
    public int amountOfFuel;

    // Add new fields below...
}