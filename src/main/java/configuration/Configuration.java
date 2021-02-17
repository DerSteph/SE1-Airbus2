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

    // battery
    public String pathToBatteryJavaArchive = commonPathToJavaArchive + "battery" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "battery.jar";
    public int numberOfBattery = 24;

    // deicing_system
    public String pathToDeIcingSystemJavaArchive = commonPathToJavaArchive + "deicing_system" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "deicing_system.jar";
    public int numberOfDeIcingSystemBody = 2;
    public int numberOfDeIcingSystemWing = 2;

    // pitot_tube
    public String pathToPitotTubeJavaArchive = commonPathToJavaArchive + "pitot_tube" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "pitot_tube.jar";
    public int numberOfPitotTube = 2;

    // radar_altimeter
    public String pathToRadarAltimeterJavaArchive = commonPathToJavaArchive + "radar_altimeter" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "radar_altimeter.jar";
    public int numberOfRadarAltimeter = 2;

    // engine_oil_tank
    public String pathToEngineOilTankJavaArchive = commonPathToJavaArchive + "engine_oil_tank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "engine_oil_tank.jar";
    public int numberOfEngineOilTank = 4;

    // Add new configurations below...
}