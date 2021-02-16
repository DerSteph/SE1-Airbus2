package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    public int degreeSlat;
    public int degreeLeftAileron;
    public int degreeRightAileron;
    public int degreeRudder;
    public boolean isRouteManagementOn;
    public int indexRouteManagement;
    public int numberOfCheckPointsRouteManagement;
    public int degreeSpoiler;
    public boolean isAntiCollisionLightOn;
    public boolean isCargoCompartmentLightOn;
}