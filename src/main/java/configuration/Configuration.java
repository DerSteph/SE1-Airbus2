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

    // fuel_tank
    public String pathToFuelTankJavaArchive = commonPathToJavaArchive + "fuel_tank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "fuel_tank.jar";
    public int numberOfFuelTank = 3;

    // air_flow_sensor
    public String pathToAirFlowSensorJavaArchive = commonPathToJavaArchive + "air_flow_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "air_flow_sensor.jar";
    public int numberOfAirFlowSensorBody = 2;
    public int numberOfAirFlowSensorWing = 2;

    // Add new configurations below...
}