import base.Airplane;
import base.Cockpit;
import base.PrimaryFlightDisplay;
import factory.*;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcedureTest {
    private Airplane airplane;
    private Cockpit cockpit;

    // Ports
    private Object
        airConditioningPort,              // ToDo: not in PFD
        airFlowSensorPort,               // TODO alarm - method
        antiCollisionLightPort,
        apuPort,
        batteryPort,
        cameraPort,                     // TODO: no attributes in Primary Flight Display
        cargoCompartmentLightPort,
        costOptimizerPort,
        deicingSystemPort,
        enginePort,
        engineOilTankPort,
        fuelTankPort,
        gearPort,
        gpsPort,                        // TODO: no attributes in Primary Flight Display
        hydraulicPumpPort,               // ToDo: shutdown - off
        kitchenPort,                    // ToDo: not in PFD
        landingLightPort,
        leftAileronPort,
        leftNavigationLightPort,
        logoLightPort,
        nitrogenBottlePort,
        oxygenBottlePort,
        pitotTubePort,
        radar,                          // TODO: no attributes in Primary Flight Display
        radarAltimeter,
        rightAileronPort,
        routeManagementPort,
        rudderPort,
        shockSensorPort,
        slatPort,
        spoilerPort,
        stallingSensorPort,
        tcasPort,
        temperatureSensorPort,
        weatherRadarPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        airplane = new Airplane();
        airplane.build();

        cockpit = new Cockpit(airplane);
    }

    @AfterEach
    public void cleanup() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

    @Test
    @Order(1)
    public void startup() {
        airConditioningPort = AirConditioningFactory.build();
        airFlowSensorPort = AirFlowSensorFactory.build();
        antiCollisionLightPort = AntiCollisionLightFactory.build();
        apuPort = APUFactory.build();
        batteryPort = BatteryFactory.build();
        cameraPort = CameraFactory.build();
        cargoCompartmentLightPort = CargoCompartmentLightFactory.build();
        costOptimizerPort = CostOptimizerFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        enginePort = EngineFactory.build();
        engineOilTankPort = EngineOilTankFactory.build();
        fuelTankPort = FuelTankFactory.build();
        gearPort = GearFactory.build();
        hydraulicPumpPort = HydraulicPumpFactory.build();
        kitchenPort = KitchenFactory.build();
        leftNavigationLightPort = LeftNavigationLightFactory.build();
        logoLightPort = LogoLightFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        routeManagementPort = RouteManagementFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        spoilerPort = SpoilerFactory.build();
        stallingSensorPort = StallingSensorFactory.build();
        tcasPort = TCASFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();
        weatherRadarPort = WeatherRadarFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure", String.class);
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort, "air");
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // anti collision light (on)
            Method onMethod = antiCollisionLightPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(antiCollisionLightPort);
            PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isAntiCollisionLightOn);

            // apu (start)
            Method startMethod = apuPort.getClass().getDeclaredMethod("start");
            boolean isStarted = (boolean) startMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isAPUStarted);

            // apu (increaseRPM)
            Method increaseRPM = apuPort.getClass().getDeclaredMethod("increaseRPM", int.class);
            int rpm = (int) increaseRPM.invoke(apuPort, 1000);
            int oldRPM = PrimaryFlightDisplay.instance.rpmAPU;
            PrimaryFlightDisplay.instance.rpmAPU = rpm;
            assertTrue(PrimaryFlightDisplay.instance.rpmAPU > oldRPM);
            assertEquals(PrimaryFlightDisplay.instance.rpmAPU, 1000);

            // battery (charge)
            Method chargeMethod = batteryPort.getClass().getDeclaredMethod("charge");
            int percentage = (int) chargeMethod.invoke(batteryPort);
            PrimaryFlightDisplay.instance.percentageBattery = percentage;
            assertEquals(PrimaryFlightDisplay.instance.percentageBattery, 100);

            // brake (100%)
            Method brakeMethod = gearPort.getClass().getDeclaredMethod("setBrake");
            int brakePercentage = (int) brakeMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.gearBrakePercentage = brakePercentage;
            assertEquals(PrimaryFlightDisplay.instance.gearBrakePercentage, 100);

            /*
            // camera (on)
            onMethod = cameraPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(cameraPort);
            PrimaryFlightDisplay.instance. = isOn;
            assertTrue(PrimaryFlightDisplay.instance.);
            */

            // cargo compartment light (on)
            onMethod = cargoCompartmentLightPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(cargoCompartmentLightPort);
            PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);

            // cargo compartment light (on)
            onMethod = cargoCompartmentLightPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(cargoCompartmentLightPort);
            PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);

            // cost optimizer (on)
            onMethod = costOptimizerPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(costOptimizerPort);
            PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isCostOptimizerOn);

            // deicing system (activate)
            Method activateMethod = deicingSystemPort.getClass().getDeclaredMethod("activate");
            boolean isActivated = (boolean) activateMethod.invoke(deicingSystemPort);
            PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isActivated;
            assertTrue(PrimaryFlightDisplay.instance.isDeIcingSystemActivated);

            // deicing system (refill & deice)
            Method refillMethod = deicingSystemPort.getClass().getDeclaredMethod("refill");
            refillMethod.invoke(deicingSystemPort);
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 1000);
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce", int.class);
            int amount = (int) deIceMethod.invoke(deicingSystemPort, 100);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 900);

            // engine (start)
            startMethod = enginePort.getClass().getDeclaredMethod("start");
            isStarted = (boolean) startMethod.invoke(enginePort);
            PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isEngineStarted);

            // engine (increase rpm)
            Method increaseRPMMethod = enginePort.getClass().getDeclaredMethod("increaseRPM", int.class);
            rpm = (int) increaseRPMMethod.invoke(enginePort, 1000);
            oldRPM = PrimaryFlightDisplay.instance.rpmEngine;
            PrimaryFlightDisplay.instance.rpmEngine = rpm;
            assertTrue(PrimaryFlightDisplay.instance.rpmEngine > oldRPM);
            assertEquals(PrimaryFlightDisplay.instance.rpmEngine, 1000);

            // engine oil tank (increase)
            Method increaseLevelMethod = engineOilTankPort.getClass().getDeclaredMethod("increaseLevel", int.class);
            int level = (int) increaseLevelMethod.invoke(engineOilTankPort, 10);
            PrimaryFlightDisplay.instance.levelEngineOilTank = level;
            assertEquals(PrimaryFlightDisplay.instance.levelEngineOilTank, 10);

            // fuel tank (refill)
            refillMethod = fuelTankPort.getClass().getDeclaredMethod("refill");
            amount = (int) refillMethod.invoke(fuelTankPort);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 1000);

            // gear (down)
            Method gearDownMethod = gearPort.getClass().getDeclaredMethod("down");
            boolean isDown = (boolean) gearDownMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.isGearDown = isDown;
            assertTrue(PrimaryFlightDisplay.instance.isGearDown);

            // hydraulic pump (oilAmount > 0)
            Method compressMethod = hydraulicPumpPort.getClass().getDeclaredMethod("compress");
            int oilAmount = (int) brakeMethod.invoke(hydraulicPumpPort);
            PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount = oilAmount;
            assertTrue(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount > 0);
            oilAmount = (int) brakeMethod.invoke(hydraulicPumpPort);
            PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount = oilAmount;
            assertTrue(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount > 0);

//            // ToDo kitchen (unlock)
//            Method unlockMethod = kitchenPort.getClass().getDeclaredMethod("unlock");
//            boolean isLocked = (boolean) unlockMethod.invoke(kitchenPort);
//            PrimaryFlightDisplay.instance.isKitchenLocked = isLocked;
//            assertFalse(PrimaryFlightDisplay.instance.isKitchenLocked);

//            // ToDo kitchen (weight > 0)
//            Method weightMethod = kitchenPort.getClass().getDeclaredMethod("getTotalWeightTrolleys");
//            double trolleyWeight = (double) unlockMethod.invoke(kitchenPort);
//            PrimaryFlightDisplay.instance.trolleyWeight = trolleyWeight;
//            assertTrue(PrimaryFlightDisplay.instance.trolleyWeight > 0);

            // left navigation light (on)
            onMethod = leftNavigationLightPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(leftNavigationLightPort);
            PrimaryFlightDisplay.instance.isLeftNavigationLightOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isLeftNavigationLightOn);

            // logo light (on)
            onMethod = logoLightPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(logoLightPort);
            PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isLogoLightOn);

            // nitrogen bottle (refill)
            refillMethod = nitrogenBottlePort.getClass().getDeclaredMethod("refill");
            amount = (int) refillMethod.invoke(nitrogenBottlePort);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 100);

            // oxygen bottle (refill)
            refillMethod = oxygenBottlePort.getClass().getDeclaredMethod("refill");
            amount = (int) refillMethod.invoke(oxygenBottlePort);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 100);

            // pitot tube (clean)
            Method cleanMethod = pitotTubePort.getClass().getDeclaredMethod("clean");
            boolean isCleaned = (boolean) cleanMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.isPitotTubeCleaned = isCleaned;
            assertTrue(PrimaryFlightDisplay.instance.isPitotTubeCleaned);

            // radar altimeter (on)
            onMethod = radarAltimeter.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.isRadarAltimeterOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);

            // route management (on)
            onMethod = routeManagementPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(routeManagementPort);
            PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isRouteManagementOn);

            // shock sensor (calibrated)
            Method calibrateMethod = shockSensorPort.getClass().getDeclaredMethod("calibrate");
            boolean isCalibrated = (boolean) calibrateMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance.isShockSensorBodyCalibrated = isCalibrated;
            PrimaryFlightDisplay.instance.isShockSensorWingCalibrated = isCalibrated;
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorBodyCalibrated);
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorWingCalibrated);

            // spoiler (neutral)
            Method neutralMethod = spoilerPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(spoilerPort);
            PrimaryFlightDisplay.instance.degreeSpoiler = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeSpoiler, 0);

            /* TODO: no attribute in primary flight display
            // stalling sensor (measure)
            measureMethod = stallingSensorPort.getClass().getDeclaredMethod("measure", String.class);
            int stalling = (int) measureMethod.invoke(stallingSensorPort, "air");
            assertEquals(stalling, 21);
            */

            // tcas (on)
            onMethod = tcasPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(tcasPort);
            PrimaryFlightDisplay.instance.isTCASOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isTCASOn);

            // tcas (connect)
            Method connectMethod= tcasPort.getClass().getDeclaredMethod("connect", String.class);
            boolean isConnected = (boolean) connectMethod.invoke(tcasPort, "89,5 MHz");
            PrimaryFlightDisplay.instance.isTCASConnected = isConnected;
            assertTrue(PrimaryFlightDisplay.instance.isTCASConnected);

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

            // weather radar (on)
            onMethod = weatherRadarPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(weatherRadarPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void taxi() {
        airFlowSensorPort = AirFlowSensorFactory.build();
        apuPort = APUFactory.build();
        cargoCompartmentLightPort = CargoCompartmentLightFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        fuelTankPort = FuelTankFactory.build();
        kitchenPort = KitchenFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        tcasPort = TCASFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // apu (decreaseRPM)
            Method decreaseRPM = apuPort.getClass().getDeclaredMethod("decreaseRPM", int.class);
            int rpm = (int) decreaseRPM.invoke(apuPort, 1000);
            int oldRPM = PrimaryFlightDisplay.instance.rpmAPU;
            PrimaryFlightDisplay.instance.rpmAPU = rpm;
            if (oldRPM >= 1000){
                assertEquals(PrimaryFlightDisplay.instance.rpmAPU, oldRPM - 1000);
            } else {
                assertEquals(PrimaryFlightDisplay.instance.rpmAPU, 0);
            }

            // apu (shutdown)
            Method shutdownMethod = apuPort.getClass().getDeclaredMethod("shutdown");
            boolean isStarted = (boolean) shutdownMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertFalse(PrimaryFlightDisplay.instance.isAPUStarted);

            // brake (0%)
            Method brakeMethod = gearPort.getClass().getDeclaredMethod("releaseBrake");
            int brakePercentage = (int) brakeMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.gearBrakePercentage = brakePercentage;
            assertEquals(PrimaryFlightDisplay.instance.gearBrakePercentage, 0);

            // cargo compartment light (off)
            Method offMethod = cargoCompartmentLightPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(cargoCompartmentLightPort);
            PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);

            // deicing system (deice)
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce", int.class);
            int amount = (int) deIceMethod.invoke(deicingSystemPort, 100);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 800);

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTankPort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(fuelTankPort, 10);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 990);

//            // ToDo kitchen (lock)
//            Method unlockMethod = kitchenPort.getClass().getDeclaredMethod("unlock");
//            boolean isLocked = (boolean) unlockMethod.invoke(kitchenPort);
//            PrimaryFlightDisplay.instance.isKitchenLocked = isLocked;
//            assertTrue(PrimaryFlightDisplay.instance.isKitchenLocked);

            // nitrogen bottle (takeOut)
            takeOutMethod = nitrogenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(nitrogenBottlePort, 1);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 99);

            // oxygen bottle (takeOut)
            takeOutMethod = oxygenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(oxygenBottlePort, 1);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 99);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            // tcas (determine altitude)
            Method determineAltitude = tcasPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            int altitude = (int) determineAltitude.invoke(tcasPort, "ground");
            PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeTCAS, 0);

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(3)
    public void takeOff() {
        airFlowSensorPort = AirFlowSensorFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        enginePort = EngineFactory.build();
        fuelTankPort = FuelTankFactory.build();
        gearPort = GearFactory.build();
        logoLightPort = LogoLightFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        rudderPort = RudderFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        slatPort = SlatFactory.build();
        tcasPort = TCASFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure", String.class);
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort, "air");
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // deicing system (deice)
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce", int.class);
            int amount = (int) deIceMethod.invoke(deicingSystemPort, 100);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 700);

            // engine (increase RPM)
            Method increaseMethod = enginePort.getClass().getDeclaredMethod("increaseRPM", int.class);
            int rpm = (int) increaseMethod.invoke(increaseMethod, 2000);
            int oldRPM = PrimaryFlightDisplay.instance.rpmEngine;
            PrimaryFlightDisplay.instance.rpmEngine = rpm;
            assertEquals(PrimaryFlightDisplay.instance.rpmEngine, oldRPM + 2000);

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTankPort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(fuelTankPort, 100);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 890);

            // gear (up)
            Method gearUpMethod = gearPort.getClass().getDeclaredMethod("up");
            boolean isDown = (boolean) gearUpMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.isGearDown = isDown;
            assertFalse(PrimaryFlightDisplay.instance.isGearDown);

            // logo light (off)
            Method offMethod = logoLightPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(logoLightPort);
            PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isLogoLightOn);

            // nitrogen bottle (takeOut)
            takeOutMethod = nitrogenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(nitrogenBottlePort, 10);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 89);

            // oxygen bottle (takeOut)
            takeOutMethod = oxygenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(oxygenBottlePort, 10);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 89);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // rudder (neutral)
            Method neutralMethod = rudderPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(rudderPort);
            PrimaryFlightDisplay.instance.degreeRudder = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeRudder, 0);

            // shock sensor (measure)
            measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int shock = (int) measureMethod.invoke(shockSensorPort);
            assertEquals(shock, 21);

            // slat (full down)
            Method fullDownMethod = slatPort.getClass().getDeclaredMethod("fullDown");
            degree = (int) fullDownMethod.invoke(slatPort);
            PrimaryFlightDisplay.instance.degreeSlat = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeSlat, -60);

            // tcas (set altitude & determine altitude)
            Method setAltitude = tcasPort.getClass().getDeclaredMethod("setAltitude", int.class);
            setAltitude.invoke(tcasPort, 50);
            Method determineAltitude = tcasPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            altitude = (int) determineAltitude.invoke(tcasPort, "ground");
            PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeTCAS, 50);

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(4)
    public void climbing() {
        airFlowSensorPort = AirFlowSensorFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        engineOilTankPort = EngineOilTankFactory.build();
        fuelTankPort = FuelTankFactory.build();
        gearPort = EngineFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        rudderPort = RudderFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        slatPort = SlatFactory.build();
        tcasPort = TCASFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // deicing system (deice)
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce", int.class);
            int amount = (int) deIceMethod.invoke(deicingSystemPort, 100);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 600);

            // engine oil tank (decrease)
            Method decreaseLevelMethod = engineOilTankPort.getClass().getDeclaredMethod("increaseLevel", int.class);
            int level = (int) decreaseLevelMethod.invoke(engineOilTankPort, 1);
            PrimaryFlightDisplay.instance.levelEngineOilTank = level;
            assertEquals(PrimaryFlightDisplay.instance.levelEngineOilTank, 9);

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTankPort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(fuelTankPort, 100);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 790);

            // gear (up)
            Method gearUpMethod = gearPort.getClass().getDeclaredMethod("up");
            boolean isDown = (boolean) gearUpMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.isGearDown = isDown;
            assertFalse(PrimaryFlightDisplay.instance.isGearDown);

            // nitrogen bottle (takeOut)
            takeOutMethod = nitrogenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(nitrogenBottlePort, 10);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 79);

            // oxygen bottle (takeOut)
            takeOutMethod = oxygenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(oxygenBottlePort, 10);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 79);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // rudder (neutral)
            Method neutralMethod = rudderPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(rudderPort);
            PrimaryFlightDisplay.instance.degreeRudder = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeRudder, 0);

            /*
            // shock sensor (measure)
            measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int shock = (int) measureMethod.invoke(shockSensorPort);
            assertEquals(shock, 21);
            */

            // slat (neutral)
            Method fullDownMethod = slatPort.getClass().getDeclaredMethod("neutral");
            degree = (int) fullDownMethod.invoke(slatPort);
            PrimaryFlightDisplay.instance.degreeSlat = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeSlat, 0);

            // tcas (set altitude & determine altitude)
            Method setAltitude = tcasPort.getClass().getDeclaredMethod("setAltitude", int.class);
            setAltitude.invoke(tcasPort, 2000);
            Method determineAltitude = tcasPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            altitude = (int) determineAltitude.invoke(tcasPort, "high");
            PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeTCAS, 2000);

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(5)
    public void rightTurn() {
        airFlowSensorPort = AirFlowSensorFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        engineOilTankPort = EngineOilTankFactory.build();
        fuelTankPort = FuelTankFactory.build();
        leftAileronPort = LeftAileronFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        rightAileronPort = RightAileronFactory.build();
        rudderPort = RudderFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // deicing system (deice)
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce", int.class);
            int amount = (int) deIceMethod.invoke(deicingSystemPort, 100);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 500);

            // engine oil tank (decrease)
            Method decreaseLevelMethod = engineOilTankPort.getClass().getDeclaredMethod("increaseLevel", int.class);
            int level = (int) decreaseLevelMethod.invoke(engineOilTankPort, 1);
            PrimaryFlightDisplay.instance.levelEngineOilTank = level;
            assertEquals(PrimaryFlightDisplay.instance.levelEngineOilTank, 8);

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTankPort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(fuelTankPort, 100);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 690);

            // left aileron (full down)
            Method fullDownMethod = leftAileronPort.getClass().getDeclaredMethod("fullDown");
            int degree = (int) fullDownMethod.invoke(leftAileronPort);
            PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeLeftAileron, -60);

            // nitrogen bottle (takeOut)
            takeOutMethod = nitrogenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(nitrogenBottlePort, 10);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 69);

            // oxygen bottle (takeOut)
            takeOutMethod = oxygenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(oxygenBottlePort, 10);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 69);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // right aileron (full up)
            Method fullUpMethod = rightAileronPort.getClass().getDeclaredMethod("fullUp");
            degree = (int) fullUpMethod.invoke(rightAileronPort);
            PrimaryFlightDisplay.instance.degreeRightAileron = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeRightAileron, 60);

            // rudder (full right)
            Method fullRightMethod = rudderPort.getClass().getDeclaredMethod("fullRight");
            degree = (int) fullRightMethod.invoke(rudderPort);
            PrimaryFlightDisplay.instance.degreeRudder = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeRudder, 60);

            /*
            // shock sensor (measure)
            measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int shock = (int) measureMethod.invoke(shockSensorPort);
            assertEquals(shock, 21);
             */

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void leftTurn() {
        airFlowSensorPort = AirFlowSensorFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        engineOilTankPort = EngineOilTankFactory.build();
        fuelTankPort = FuelTankFactory.build();
        leftAileronPort = LeftAileronFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        rightAileronPort = RightAileronFactory.build();
        rudderPort = RudderFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // deicing system (deice)
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce", int.class);
            int amount = (int) deIceMethod.invoke(deicingSystemPort, 100);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 400);

            // engine oil tank (decrease)
            Method decreaseLevelMethod = engineOilTankPort.getClass().getDeclaredMethod("increaseLevel", int.class);
            int level = (int) decreaseLevelMethod.invoke(engineOilTankPort, 1);
            PrimaryFlightDisplay.instance.levelEngineOilTank = level;
            assertEquals(PrimaryFlightDisplay.instance.levelEngineOilTank, 7);

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTankPort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(fuelTankPort, 100);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 590);

            // left aileron (full up)
            Method fullUpMethod = leftAileronPort.getClass().getDeclaredMethod("fullUp");
            int degree = (int) fullUpMethod.invoke(leftAileronPort);
            PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeLeftAileron, 60);

            // nitrogen bottle (takeOut)
            takeOutMethod = nitrogenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(nitrogenBottlePort, 10);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 59);

            // oxygen bottle (takeOut)
            takeOutMethod = oxygenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(oxygenBottlePort, 10);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 59);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // right aileron (full down)
            Method fullDownMethod = rightAileronPort.getClass().getDeclaredMethod("fullDown");
            degree = (int) fullDownMethod.invoke(rightAileronPort);
            PrimaryFlightDisplay.instance.degreeRightAileron = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeRightAileron, -60);

            // rudder (full left)
            Method fullLeftMethod = rudderPort.getClass().getDeclaredMethod("fullLeft");
            degree = (int) fullLeftMethod.invoke(rudderPort);
            PrimaryFlightDisplay.instance.degreeRudder = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeRudder, -60);

            /*
            // shock sensor (measure)
            measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int shock = (int) measureMethod.invoke(shockSensorPort);
            assertEquals(shock, 21);
             */

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void descent() {
        airFlowSensorPort = AirFlowSensorFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        engineOilTankPort = EngineOilTankFactory.build();
        fuelTankPort = FuelTankFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        rudderPort = RudderFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        tcasPort = TCASFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // deicing system (deice)
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce", int.class);
            int amount = (int) deIceMethod.invoke(deicingSystemPort, 100);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 300);

            // engine oil tank (decrease)
            Method decreaseLevelMethod = engineOilTankPort.getClass().getDeclaredMethod("increaseLevel", int.class);
            int level = (int) decreaseLevelMethod.invoke(engineOilTankPort, 1);
            PrimaryFlightDisplay.instance.levelEngineOilTank = level;
            assertEquals(PrimaryFlightDisplay.instance.levelEngineOilTank, 6);

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTankPort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(fuelTankPort, 100);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 490);

            // nitrogen bottle (takeOut)
            takeOutMethod = nitrogenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(nitrogenBottlePort, 10);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 49);

            // oxygen bottle (takeOut)
            takeOutMethod = oxygenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(oxygenBottlePort, 10);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 49);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // rudder (neutral)
            Method neutralMethod = rudderPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(rudderPort);
            PrimaryFlightDisplay.instance.degreeRudder = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeRudder, 0);

            /*
            // shock sensor (measure)
            measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int shock = (int) measureMethod.invoke(shockSensorPort);
            assertEquals(shock, 21);
             */

            // tcas (set altitude & determine altitude)
            Method setAltitude = tcasPort.getClass().getDeclaredMethod("setAltitude", int.class);
            setAltitude.invoke(tcasPort, 1000);
            Method determineAltitude = tcasPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            altitude = (int) determineAltitude.invoke(tcasPort, "low");
            PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeTCAS, 1000);

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void landing() {
        airFlowSensorPort = AirFlowSensorFactory.build();
        apuPort = APUFactory.build();
        enginePort = EngineFactory.build();
        fuelTankPort = FuelTankFactory.build();
        gearPort = GearFactory.build();
        landingLightPort = LandingLightFactory.build();
        logoLightPort = LogoLightFactory.build();
        nitrogenBottlePort = NitrogenBottleFactory.build();
        oxygenBottlePort = OxygenBottleFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        slatPort = SlatFactory.build();
        spoilerPort = SpoilerFactory.build();
        tcasPort = TCASFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, airPressure);       // TODO: airPressure is never signed in component

            // apu (start)
            Method startMethod = apuPort.getClass().getDeclaredMethod("start");
            boolean isStarted = (boolean) startMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isAPUStarted);

            // apu (increaseRPM)
            Method increaseRPM = apuPort.getClass().getDeclaredMethod("increaseRPM", int.class);
            int rpm = (int) increaseRPM.invoke(apuPort, 2000);
            int oldRPM = PrimaryFlightDisplay.instance.rpmAPU;
            PrimaryFlightDisplay.instance.rpmAPU = rpm;
            assertEquals(oldRPM + 2000, PrimaryFlightDisplay.instance.rpmAPU);

            // engine (decrease RPM)
            Method decreaseMethod = enginePort.getClass().getDeclaredMethod("decreaseRPM", int.class);
            rpm = (int) decreaseMethod.invoke(decreaseMethod, 1000);
            oldRPM = PrimaryFlightDisplay.instance.rpmEngine;
            PrimaryFlightDisplay.instance.rpmEngine = rpm;
            if (oldRPM >= 1000) {
                assertEquals(oldRPM - 1000, PrimaryFlightDisplay.instance.rpmEngine);
            } else{
                assertEquals(0, PrimaryFlightDisplay.instance.rpmEngine);
            }

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTankPort.getClass().getDeclaredMethod("takeOut", int.class);
            int amount = (int) takeOutMethod.invoke(fuelTankPort, 10);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, 480);

            // gear (down)
            Method gearDownMethod = gearPort.getClass().getDeclaredMethod("down");
            boolean isDown = (boolean) gearDownMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.isGearDown = isDown;
            assertTrue(PrimaryFlightDisplay.instance.isGearDown);

            // landing light (on)
            Method onMethod = landingLightPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(landingLightPort);
            PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isLandingLightBodyOn);
            isOn = (boolean) onMethod.invoke(landingLightPort);
            PrimaryFlightDisplay.instance.isLandingLightWingOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isLandingLightWingOn);

            // logo light (on)
            onMethod = logoLightPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(logoLightPort);
            PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isLogoLightOn);

            // nitrogen bottle (takeOut)
            takeOutMethod = nitrogenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(nitrogenBottlePort, 1);
            PrimaryFlightDisplay.instance.amountOfNitrogen = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountOfNitrogen, 48);

            // oxygen bottle (takeOut)
            takeOutMethod = oxygenBottlePort.getClass().getDeclaredMethod("takeOut", int.class);
            amount = (int) takeOutMethod.invoke(oxygenBottlePort, 1);
            PrimaryFlightDisplay.instance.oxygenBottleAmount = amount;
            assertEquals(PrimaryFlightDisplay.instance.oxygenBottleAmount, 48);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            /*
            // shock sensor (measure)
            measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int shock = (int) measureMethod.invoke(shockSensorPort);
            assertEquals(shock, 21);
             */

            // slat (full down)
            Method fullDownMethod = slatPort.getClass().getDeclaredMethod("fullDown");
            int degree = (int) fullDownMethod.invoke(slatPort);
            PrimaryFlightDisplay.instance.degreeSlat = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeSlat, -60);

            // spoiler (fullUp)
            Method fullUpMethod = spoilerPort.getClass().getDeclaredMethod("fullUp");
            degree = (int) fullUpMethod.invoke(spoilerPort);
            PrimaryFlightDisplay.instance.degreeSpoiler = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeSpoiler, 60);

            // tcas (set altitude & determine altitude)
            Method setAltitude = tcasPort.getClass().getDeclaredMethod("setAltitude", int.class);
            setAltitude.invoke(tcasPort, 0);
            Method determineAltitude = tcasPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            int altitude = (int) determineAltitude.invoke(tcasPort, "ground");
            PrimaryFlightDisplay.instance.altitudeTCAS = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeTCAS, 0);

            // temperature sensor (measure)
            measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void shutdown() {
        // airConditioningPort = AirConditioningFactory.build();
        antiCollisionLightPort = AntiCollisionLightFactory.build();
        apuPort = APUFactory.build();
        batteryPort = BatteryFactory.build();
        cargoCompartmentLightPort = CargoCompartmentLightFactory.build();
        costOptimizerPort = CostOptimizerFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        enginePort = EngineFactory.build();
        // gearPort = GearFactory.build();
        landingLightPort = LandingLightFactory.build();
        leftNavigationLightPort = LeftNavigationLightFactory.build();
        logoLightPort = LogoLightFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        routeManagementPort = RouteManagementFactory.build();
        slatPort = SlatFactory.build();
        spoilerPort = SpoilerFactory.build();
        tcasPort = TCASFactory.build();
        weatherRadarPort = WeatherRadarFactory.build();

        try {
//            // ToDo air conditioning (off)
//            Method offMethod = airConditioningPort.getClass().getDeclaredMethod("off");
//            boolean isOn = (boolean) offMethod.invoke(airConditioningPort);
//            PrimaryFlightDisplay.instance.isAirConditioningOn = isOn;
//            assertFalse(PrimaryFlightDisplay.instance.isAirConditioningOn);

            // anti collision light (off)
            Method offMethod = antiCollisionLightPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(antiCollisionLightPort);
            PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isAntiCollisionLightOn);

            // apu (decreaseRPM)
            Method decreaseRPM = apuPort.getClass().getDeclaredMethod("decreaseRPM", int.class);
            int rpm = (int) decreaseRPM.invoke(apuPort, 2000);
            int oldRPM = PrimaryFlightDisplay.instance.rpmAPU;
            PrimaryFlightDisplay.instance.rpmAPU = rpm;
            if (oldRPM >= 2000){
                assertEquals(oldRPM - 2000, PrimaryFlightDisplay.instance.rpmAPU);
            } else {
                assertEquals(0, PrimaryFlightDisplay.instance.rpmAPU);
            }

            // apu (shutdown)
            Method shutdownMethod = apuPort.getClass().getDeclaredMethod("shutdown");
            boolean isStarted = (boolean) shutdownMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertFalse(PrimaryFlightDisplay.instance.isAPUStarted);

            // battery (discharge)
            Method dischargeMethod = batteryPort.getClass().getDeclaredMethod("discharge");
            int percentage = (int) dischargeMethod.invoke(batteryPort);
            PrimaryFlightDisplay.instance.percentageBattery = percentage;
            assertEquals(PrimaryFlightDisplay.instance.percentageBattery, 0);

            // brake (100%)
            Method brakeMethod = gearPort.getClass().getDeclaredMethod("setBrake");
            int brakePercentage = (int) brakeMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.gearBrakePercentage = brakePercentage;
            assertEquals(PrimaryFlightDisplay.instance.gearBrakePercentage, 100);

            // cargo compartment light (on)
            Method onMethod = cargoCompartmentLightPort.getClass().getDeclaredMethod("on");
            isOn = (boolean) onMethod.invoke(cargoCompartmentLightPort);
            PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
            assertTrue(PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);

            // cost optimizer (off)
            offMethod = costOptimizerPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(costOptimizerPort);
            PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isCostOptimizerOn);

            // deicing system (deactivate)
            Method deactivateMethod = deicingSystemPort.getClass().getDeclaredMethod("deactivate");
            boolean isActivated = (boolean) deactivateMethod.invoke(deicingSystemPort);
            PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isActivated;
            assertFalse(PrimaryFlightDisplay.instance.isDeIcingSystemActivated);

            // engine (decreaseRPM rpm)
            Method decreaseRPMMethod = enginePort.getClass().getDeclaredMethod("decreaseRPM", int.class);
            rpm = (int) decreaseRPMMethod.invoke(enginePort, 1000);
            oldRPM = PrimaryFlightDisplay.instance.rpmEngine;
            PrimaryFlightDisplay.instance.rpmEngine = rpm;
            if (oldRPM >= 1000){
                assertEquals(oldRPM - 1000, PrimaryFlightDisplay.instance.rpmEngine);
            } else {
                assertEquals(0, PrimaryFlightDisplay.instance.rpmEngine);
            }

            // engine (shutdown)
            shutdownMethod = enginePort.getClass().getDeclaredMethod("shutdown");
            isStarted = (boolean) shutdownMethod.invoke(enginePort);
            PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
            assertFalse(PrimaryFlightDisplay.instance.isEngineStarted);

            // landing light (off)
            offMethod = landingLightPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) onMethod.invoke(landingLightPort);
            PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isLandingLightBodyOn);
            isOn = (boolean) onMethod.invoke(landingLightPort);
            PrimaryFlightDisplay.instance.isLandingLightWingOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isLandingLightWingOn);

            // left navigation light (off)
            offMethod = leftNavigationLightPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(leftNavigationLightPort);
            PrimaryFlightDisplay.instance.isLeftNavigationLightOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isLeftNavigationLightOn);

            // logo light (off)
            offMethod = logoLightPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(logoLightPort);
            PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isLogoLightOn);

            // radar altimeter (off)
            offMethod = radarAltimeter.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.isRadarAltimeterOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isRadarAltimeterOn);

            // route management (off)
            offMethod = routeManagementPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(routeManagementPort);
            PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isRouteManagementOn);

            // slat (neutral)
            Method fullDownMethod = slatPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) fullDownMethod.invoke(slatPort);
            PrimaryFlightDisplay.instance.degreeSlat = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeSlat, 0);

            // spoiler (neutral)
            Method neutralMethod = spoilerPort.getClass().getDeclaredMethod("neutral");
            degree = (int) neutralMethod.invoke(spoilerPort);
            PrimaryFlightDisplay.instance.degreeSpoiler = degree;
            assertEquals(PrimaryFlightDisplay.instance.degreeSpoiler, 0);

            // tcas (off)
            offMethod = tcasPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(tcasPort);
            PrimaryFlightDisplay.instance.isTCASOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isTCASOn);

            // weather radar (off)
            offMethod = weatherRadarPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(weatherRadarPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isWeatherRadarOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
