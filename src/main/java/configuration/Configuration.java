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

    //shock_sensor
    public String pathToShockSensorJavaArchive = commonPathToJavaArchive + "shock_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "shock_sensor.jar";
    public int numberOfShockSensorBody = 2;
    public int numberOfShockSensorWing = 2;

    //stalling_sensor
    public String pathToStallingSensorJavaArchive = commonPathToJavaArchive + "stalling_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "stalling_sensor.jar";
    public int numberOfStallingSensorBody = 2;
    public int numberOfStallingSensorWing = 4;

    //temperature_sensor
    public String pathToTemperatureSensorJavaArchive = commonPathToJavaArchive + "temperature_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "temperature_sensor.jar";
    public int numberOfTemperatureSensorBody = 2;
    public int numberOfTemperatureSensorWing = 4;

    // Add new configurations below...
}