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

    //wastewater_tank
    public String pathToWasteWaterTankJavaArchive = commonPathToJavaArchive + "wastewater_tank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "wastewater_tank.jar";
    public int numberOfWasteWaterTank = 4;

    //portable_watertank
    public String pathToPortableWaterTankJavaArchive = commonPathToJavaArchive + "wastewater_tank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "portable_watertank.jar";
    public int numberOfPortableWaterTank = 8;

    //oxygen_bottle
    public String pathToOxygenBottleJavaArchive = commonPathToJavaArchive + "oxygen_bottle" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "oxygen_bottle.jar";
    public int numberOfOxygenBottle = 10;

    //nitrogen_bottle
    public String pathToNitrogenBottleJavaArchive = commonPathToJavaArchive + "nitrogen_bottle" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "nitrogen_bottle.jar";
    public int numberOfNitrogenBottle = 6;

    //vhf
    public String pathToVHFJavaArchive = commonPathToJavaArchive + "vhf" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "vhf.jar";
    public int numberOfVHF = 2;

}