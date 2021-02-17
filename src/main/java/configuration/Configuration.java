package configuration;

public enum Configuration {
    instance;

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;

    public String lineSeparator = System.getProperty("line.separator");
    public String logFileDirectory = userDirectory + fileSeparator + "log" + fileSeparator;
    public String logFile = logFileDirectory + "airbus_a350.log";
    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String databaseFile = dataDirectory + "flight_recorder_a350.db";

    // weather_radar
    public String pathToWeatherRadarJavaArchive = commonPathToJavaArchive + "weather_radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "weather_radar.jar";
    public int numberOfWeatherRadar = 2;

    // slat
    public String pathToSlatJavaArchive = commonPathToJavaArchive + "slat" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "slat.jar";
    public int numberOfSlat = 6;

    // left_aileron
    public String pathToLeftAileronJavaArchive = commonPathToJavaArchive + "left_aileron" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "left_aileron.jar";
    public int numberOfLeftAileron = 3;

    // right_aileron
    public String pathToRightAileronJavaArchive = commonPathToJavaArchive + "right_aileron" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "right_aileron.jar";
    public int numberOfRightAileron = 3;

    // rudder
    public String pathToRudderJavaArchive = commonPathToJavaArchive + "rudder" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "rudder.jar";
    public int numberOfRudder = 2;

    // route_management
    public String pathToRouteManagementJavaArchive =  commonPathToJavaArchive + "route_management" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "route_management.jar";
    public int numberOfRouteManagement = 2;

    // spoiler
    public String pathToSpoilerJavaArchive = commonPathToJavaArchive + "spoiler" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "spoiler.jar";
    public int numberOfSpoiler = 8;

    // cost_optimizer
    public String pathToCostOptimizerJavaArchive = commonPathToJavaArchive + "cost_optimizer" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "cost_optimizer.jar";
    public int numberOfCostOptimizer = 2;

    // anti_collision_light
    public String pathToAntiCollisionLightJavaArchive = commonPathToJavaArchive + "anti_collision_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "anti_collision_light.jar";
    public int numberOfAntiCollisionLight = 2;

    // cargo_compartment_light
    public String pathToCargoCompartmentLightJavaArchive = commonPathToJavaArchive + "cargo_compartment_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "cargo_compartment_light.jar";
    public int numberOfCompartmentLight = 4;

    // landing_light
    public String pathToLandingLightJavaArchive = commonPathToJavaArchive + "landing_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "landing_light.jar";
    public int numberOfLandingLightBody = 2;
    public int numberOfLandingLightWing = 2;

    // left_navigation_light
    public String pathToLeftNavigationLightJavaArchive = commonPathToJavaArchive + "left_navigation_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "left_navigation_light.jar";
    public int numberOfLeftNavigationLight = 1;

    // logo_light
    public String pathToLogoLightJavaArchive = commonPathToJavaArchive + "logo_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "logo_light.jar";
    public int numberOfLogoLight = 2;
}