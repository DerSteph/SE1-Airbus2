package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    public int degreeSlat;
    // Add new fields below...
    public boolean isAPUStarted;
    public int rpmAPU;
    public boolean isEngineStarted;
    public int rpmEngine;
    public boolean isEngineFire;
    public boolean isGearDown;
    public int gearBrakePercentage;
    public int hydraulicPumpBodyOilAmount;
    public int hydraulicPumpWingOilAmount;
}