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

    // Add new configurations below...

    //right_navigation_light
    public String pathToRightNavigationLight = commonPathToJavaArchive + "right_navigation_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "right_navigation_light.jar";
    public int numberOfLeftNavigationLight = 1;

    //tail_navigation_light
    public String pathToTailNavigationLight = commonPathToJavaArchive + "tail_navigation_light" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "tail_navigation_light.jar";
    public int numberOfTailNavigationLight = 2;

}