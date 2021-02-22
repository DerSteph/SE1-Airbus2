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
    public int temperatureWing;
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

    public boolean isAPUStarted;
    public int rpmAPU;
    public boolean isEngineStarted;
    public int rpmEngine;
    public boolean isEngineFire;
    public boolean isGearDown;
    public int gearBrakePercentage;
    public int hydraulicPumpBodyOilAmount;
    public int hydraulicPumpWingOilAmount;
    public int degreeLeftAileron;
    public int degreeRightAileron;
    public int degreeRudder;
    public boolean isRouteManagementOn;
    public int indexRouteManagement;
    public int numberOfCheckPointsRouteManagement;
    public int degreeSpoiler;
    public boolean isAntiCollisionLightOn;
    public boolean isCargoCompartmentLightOn;
    public boolean isLandingLightBodyOn;
    public boolean isLandingLightWingOn;
    public boolean isLeftNavigationLightOn;
    public boolean isLogoLightOn;
    public boolean isCostOptimizerOn;
    public int indexCostOptimizer;
    public int numberOfCheckPointsCostOptimizer;

}