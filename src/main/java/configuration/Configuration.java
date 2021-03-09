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
    public String pathToPortableWaterTankJavaArchive = commonPathToJavaArchive + "portable_watertank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "portable_watertank.jar";
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

    //sat_com
    public String pathToSatComJavaArchive = commonPathToJavaArchive + "sat_com" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "sat_com.jar";
    public int numberOfSatCom = 2;

    // droop_nose
    public String pathToDroopNoseArchive = commonPathToJavaArchive + "droop_nose" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "droop_nose.jar";
    public int numberOfDroopNose = 2;

    // tcas
    public String pathToTCASArchive = commonPathToJavaArchive + "tcas" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "tcas.jar";
    public int numberOfTCAS = 2;

    // turbulent_air_flow_sensor
    public String pathToTurbulentAirFlowSensorArchive = commonPathToJavaArchive + "turbulent_air_flow_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "turbulent_air_flow_sensor.jar";
    public int numberOfTurbulentAirFlowSensorBody = 2;
    public int numberOfTurbulentAirFlowSensorWing = 4;

    // camera
    public String pathToCameraArchive = commonPathToJavaArchive + "camera" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "camera.jar";
    public int numberOfCameraBody = 2;
    public int numberOfCameraWing = 1;

    // gps
    public String pathToGPSArchive = commonPathToJavaArchive + "gps" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "gps.jar";
    public int numberOfGPS = 2;

    // radar
    public String pathToRadarArchive = commonPathToJavaArchive + "radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "radar.jar";
    public int numberOfRadar = 2;

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
    public String pathToAirFlowSensorJavaArchive = commonPathToJavaArchive + "airflow_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "air_flow_sensor.jar";
    public int numberOfAirFlowSensorBody = 2;
    public int numberOfAirFlowSensorWing = 2;

    // Add new configurations below...

    // apu
    public String pathToAPUJavaArchive = commonPathToJavaArchive + "apu" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "apu.jar";
    public int numberOfAPU = 1;

    // engine
    public String pathToEngineJavaArchive = commonPathToJavaArchive + "engine" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "engine.jar";
    public int numberOfEngine = 1;

    // gear
    public String pathToGearJavaArchive = commonPathToJavaArchive + "gear" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "gear.jar";
    public int numberOfGear = 3;

    // hydraulic_pump
    public String pathToHydraulicPumpJavaArchive = commonPathToJavaArchive + "hydraulic_pump" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "hydraulic_pump.jar";
    public int numberOfHydraulicPumpBody = 6;
    public int numberOfHydraulicPumpWing = 4;

    // air_conditioning
    public String pathToAirConditioningJavaArchive = commonPathToJavaArchive + "air_conditioning" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "air_conditioning.jar";
    public int numberOfAirConditioning = 4;

    // kitchen
    public String pathToKitchenJavaArchive = commonPathToJavaArchive + "kitchen" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "kitchen.jar";
    public int numberOfKitchen = 3;

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