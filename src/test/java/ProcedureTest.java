import base.*;
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
    private Object airConditioningPort;
    private Object airFlowSensorPort;               // TODO alarm - method
    private Object apuPort;
    private Object batteryPort;
    private Object deicingSystemPort;
    // ToDo private Object droopNosePort;
    private Object enginePort;
    // TODO: private Object engineOilTankPort;   Was für Level mrk?
    private Object fuelTank;                        // TODO: refill(amount) - method
    private Object gearPort;
    private Object hydraulicPumpPort;
    private Object kitchenPort;
    private Object pitotTubePort;
    private Object radarAltimeter;                  // TODO: send - method
    private Object shockSensorPort;
    // ToDo private Object slatPort;
    // TODO: private Object stallingSensorPort;      // TODO: alarm - method
    // ToDo private Object tcasPort;
    private Object temperatureSensorPort;           // TODO: alarm - method
    private Object weatherRadarPort;

    @BeforeEach
    public void init(){
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        airplane = new Airplane();
        airplane.build();

        cockpit = new Cockpit(airplane);
    }

    @Test
    @Order(1)
    public void startup(){
        airConditioningPort = AirConditioningFactory.build();
        airFlowSensorPort = AirFlowSensorFactory.build();
        apuPort = APUFactory.build();
        batteryPort = BatteryFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        enginePort = EngineFactory.build();
        fuelTank = FuelTankFactory.build();
        gearPort = GearFactory.build();
        hydraulicPumpPort = HydraulicPumpFactory.build();
        kitchenPort = KitchenFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        temperatureSensorPort = TemperatureSensorFactory.build();
        weatherRadarPort = WeatherRadarFactory.build();

        try {
            // ToDo air conditioning (on)
            Method onMethod = airConditioningPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(airConditioningPort);
//            PrimaryFlightDisplay.instance.isAirConditioningOn = isOn;
//            assertTrue(PrimaryFlightDisplay.instance.isAirConditioningOn);

            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(airPressure, 0);                                                       // TODO: no value to compare

            // apu (start)
            Method startMethod = apuPort.getClass().getDeclaredMethod("start");
            boolean isStarted = (boolean) startMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isAPUStarted);

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

            // deicing system (activate)
            Method activateMethod = deicingSystemPort.getClass().getDeclaredMethod("activate");
            boolean isActivated = (boolean) activateMethod.invoke(deicingSystemPort);
            PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isActivated;
            assertTrue(PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
            // (deice)
            Method deIceMethod = deicingSystemPort.getClass().getDeclaredMethod("deIce");
            int amount = (int) deIceMethod.invoke(deicingSystemPort);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            //assertTrue(PrimaryFlightDisplay.instance.amountDeIcingSystem, ?);                        TODO: no value to compare

            // engine (start)
            startMethod = enginePort.getClass().getDeclaredMethod("start");
            isStarted = (boolean) startMethod.invoke(enginePort);
            PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isEngineStarted);

            // fuel tank (refill)
            Method refillMethod = fuelTank.getClass().getDeclaredMethod("refill");
            amount = (int) refillMethod.invoke(fuelTank);
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
            PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount = oilAmount;
            assertTrue(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount > 0);
            assertTrue(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount > 0);

            // ToDo kitchen (unlock)
            Method unlockMethod = kitchenPort.getClass().getDeclaredMethod("unlock");
            boolean isLocked = (boolean) unlockMethod.invoke(kitchenPort);
//            PrimaryFlightDisplay.instance.isKitchenLocked = isLocked;
//            assertFalse(PrimaryFlightDisplay.instance.isKitchenLocked);

            // ToDo kitchen (weight > 0)
            Method weightMethod = kitchenPort.getClass().getDeclaredMethod("getTotalWeightTrolleys");
            double trolleyWeight = (double) unlockMethod.invoke(kitchenPort);
//            PrimaryFlightDisplay.instance.trolleyWeight = trolleyWeight;
//            assertTrue(PrimaryFlightDisplay.instance.trolleyWeight > 0);

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

            // shock sensor (calibrated)
            Method calibrateMethod = shockSensorPort.getClass().getDeclaredMethod("calibrate");
            boolean isCalibrated = (boolean) calibrateMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance.isShockSensorBodyCalibrated = isCalibrated;
            PrimaryFlightDisplay.instance.isShockSensorWingCalibrated = isCalibrated;
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorBodyCalibrated);
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorWingCalibrated);

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

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void taxi(){
        apuPort = APUFactory.build();
        kitchenPort = KitchenFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        // radarAltimeter = RadarAltimeterFactory.build();
        shockSensorPort = ShockSensorFactory.build();

        try {
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

            // ToDo kitchen (lock)
            Method unlockMethod = kitchenPort.getClass().getDeclaredMethod("unlock");
            boolean isLocked = (boolean) unlockMethod.invoke(kitchenPort);
//            PrimaryFlightDisplay.instance.isKitchenLocked = isLocked;
//            assertTrue(PrimaryFlightDisplay.instance.isKitchenLocked);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);

            // TODO: radar altimeter (send)
            // (receive)
            Method receiveMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int response = (int) receiveMethod.invoke(radarAltimeter);
            assertEquals(response, 1);

            // shock sensor (alarm - shock detected (body))
            Method alarmMethod = shockSensorPort.getClass().getDeclaredMethod("alarm");
            boolean isShockDetected = (boolean) alarmMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance.isShockSensorBodyShockDetected = isShockDetected;
            assertFalse(PrimaryFlightDisplay.instance.isShockSensorBodyShockDetected);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(3)
    public void takeOff(){
        airFlowSensorPort = AirFlowSensorFactory.build();
        enginePort = EngineFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        // stallingSensorPort = StallingSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(airPressure, 0);                                                       // TODO: no value to compare

            // engine (increase RPM)
            Method increaseMethod = enginePort.getClass().getDeclaredMethod("increaseRPM");
            int rpm = (int) increaseMethod.invoke(increaseMethod);
            int oldrpm = PrimaryFlightDisplay.instance.rpmEngine;
            PrimaryFlightDisplay.instance.rpmEngine = rpm;
            assertTrue(PrimaryFlightDisplay.instance.rpmEngine > oldrpm);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);
            /* (measureStaticPressure)
            Method measureStaticPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureStaticPressure");
            int staticPressure = (int) measureStaticPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = staticPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 10);
            (measureTotalPressure)
            Method measureTotalPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureTotalPressure");
            int totalPressure = (int) measureTotalPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = totalPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 20);
            */

            // TODO: radar altimeter (send)
            // (receive)
            Method receiveMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int response = (int) receiveMethod.invoke(radarAltimeter);
            assertEquals(response, 1);
            // (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);


            // shock sensor (alarm - shock detected (body))
            Method alarmMethod = shockSensorPort.getClass().getDeclaredMethod("alarm");
            boolean isShockDetected = (boolean) alarmMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance.isShockSensorBodyShockDetected = isShockDetected;
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorBodyShockDetected);

            // stalling sensor (measure)
            /*Method measureMethod = stallingSensorPort.getClass().getDeclaredMethod("measure");
            int stalling = (int) measureMethod.invoke(stallingSensorPort);
            PrimaryFlightDisplay.instance. = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(4)
    public void climbing(){
        airFlowSensorPort = AirFlowSensorFactory.build();
        gearPort = EngineFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        // stallingSensorPort = StallingSensorFactory.build();

        try{
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(airPressure, 0);                                                       // TODO: no value to compare

            // gear (up)
            Method gearUpMethod = gearPort.getClass().getDeclaredMethod("up");
            boolean isDown = (boolean) gearUpMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.isGearDown = isDown;
            assertFalse(PrimaryFlightDisplay.instance.isGearDown);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);
            /* (measureStaticPressure)
            Method measureStaticPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureStaticPressure");
            int staticPressure = (int) measureStaticPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = staticPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 10);
            (measureTotalPressure)
            Method measureTotalPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureTotalPressure");
            int totalPressure = (int) measureTotalPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = totalPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 20);
            */

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // shock sensor (measure)                                                                   // TODO: no attribute to compare
            /*measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int isShockDetected = (int) measureMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance. = isShockDetected;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/

            // TODO: stalling sensor (measure)
            /*Method measureMethod = stallingSensorPort.getClass().getDeclaredMethod("measure");
            int stalling = (int) measureMethod.invoke(stallingSensorPort);
            PrimaryFlightDisplay.instance. = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(5)
    public void rightTurn(){
        airFlowSensorPort = AirFlowSensorFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        shockSensorPort = ShockSensorFactory.build();

        try{
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(airPressure, 0);                                                       // TODO: no value to compare

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);
            /* (measureStaticPressure)
            Method measureStaticPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureStaticPressure");
            int staticPressure = (int) measureStaticPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = staticPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 10);
            (measureTotalPressure)
            Method measureTotalPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureTotalPressure");
            int totalPressure = (int) measureTotalPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = totalPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 20);
            */

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // shock sensor (alarm - shock detected (wings))
            Method alarmMethod = shockSensorPort.getClass().getDeclaredMethod("alarm");
            boolean isShockDetected = (boolean) alarmMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance.isShockSensorWingShockDetected = isShockDetected;
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorWingShockDetected);

            // TODO: stalling sensor (measure)
            /*Method measureMethod = stallingSensorPort.getClass().getDeclaredMethod("measure");
            int stalling = (int) measureMethod.invoke(stallingSensorPort);
            PrimaryFlightDisplay.instance. = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void leftTurn(){
        airFlowSensorPort = AirFlowSensorFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        // shockSensorPort = ShockSensorFactory.build();
        // stallingSensorPort = StallingSensorFactory.build();

        try{
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(airPressure, 0);                                                       // TODO: no value to compare

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);
            /* (measureStaticPressure)
            Method measureStaticPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureStaticPressure");
            int staticPressure = (int) measureStaticPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = staticPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 10);
            (measureTotalPressure)
            Method measureTotalPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureTotalPressure");
            int totalPressure = (int) measureTotalPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = totalPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 20);
            */

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // shock sensor (alarm - shock detected (wings))
            Method alarmMethod = shockSensorPort.getClass().getDeclaredMethod("alarm");
            boolean isShockDetected = (boolean) alarmMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance.isShockSensorWingShockDetected = isShockDetected;
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorWingShockDetected);

            // TODO: stalling sensor (measure)
            /*Method measureMethod = stallingSensorPort.getClass().getDeclaredMethod("measure");
            int stalling = (int) measureMethod.invoke(stallingSensorPort);
            PrimaryFlightDisplay.instance. = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void descent(){
        airFlowSensorPort = AirFlowSensorFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        // shockSensorPort = ShockSensorFactory.build();
        // stallingSensorPort = StallingSensorFactory.build();

        try{
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(airPressure, 0);                                                       // TODO: no value to compare

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);
            /* (measureStaticPressure)
            Method measureStaticPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureStaticPressure");
            int staticPressure = (int) measureStaticPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = staticPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 10);
            (measureTotalPressure)
            Method measureTotalPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureTotalPressure");
            int totalPressure = (int) measureTotalPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = totalPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 20);
            */

            // radar altimeter (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // shock sensor (measure)                                                                   // TODO: no attribute to compare
            /*measureMethod = shockSensorPort.getClass().getDeclaredMethod("measure");
            int isShockDetected = (int) measureMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance. = isShockDetected;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/

            // TODO: stalling sensor (measure)
            /*Method measureMethod = stallingSensorPort.getClass().getDeclaredMethod("measure");
            int stalling = (int) measureMethod.invoke(stallingSensorPort);
            PrimaryFlightDisplay.instance. = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void landing(){
        airFlowSensorPort = AirFlowSensorFactory.build();
        apuPort = APUFactory.build();
        enginePort = EngineFactory.build();
        gearPort = GearFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        shockSensorPort = ShockSensorFactory.build();
        // stallingSensorPort = StallingSensorFactory.build();

        try {
            // air flow sensor (measure)
            Method measureMethod = airFlowSensorPort.getClass().getDeclaredMethod("measure");
            int airPressure = (int) measureMethod.invoke(airFlowSensorPort);
            PrimaryFlightDisplay.instance.airPressure = airPressure;
            assertEquals(airPressure, 0);                                                       // TODO: no value to compare


            // apu (start)
            Method startMethod = apuPort.getClass().getDeclaredMethod("start");
            boolean isStarted = (boolean) startMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isAPUStarted);

            // engine (decrease RPM)
            Method decreaseMethod = enginePort.getClass().getDeclaredMethod("decreaseRPM");
            int rpm = (int) decreaseMethod.invoke(decreaseMethod);
            int oldrpm = PrimaryFlightDisplay.instance.rpmEngine;
            PrimaryFlightDisplay.instance.rpmEngine = rpm;
            assertTrue(PrimaryFlightDisplay.instance.rpmEngine < oldrpm);

            // gear (down)
            Method gearDownMethod = gearPort.getClass().getDeclaredMethod("down");
            boolean isDown = (boolean) gearDownMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.isGearDown = isDown;
            assertTrue(PrimaryFlightDisplay.instance.isGearDown);

            // pitot tube (velocity)
            Method measureVelocityMethod = pitotTubePort.getClass().getDeclaredMethod("measureVelocity");
            int velocity = (int) measureVelocityMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.velocity = velocity;
            assertEquals(PrimaryFlightDisplay.instance.velocity, 30);
            /* (measureStaticPressure)
            Method measureStaticPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureStaticPressure");
            int staticPressure = (int) measureStaticPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = staticPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 10);
            (measureTotalPressure)
            Method measureTotalPressureMethod = pitotTubePort.getClass().getDeclaredMethod("measureTotalPressure");
            int totalPressure = (int) measureTotalPressureMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.* = totalPressure;
            assertEquals(PrimaryFlightDisplay.instance.*, 20);
            */

            // TODO: radar altimeter (send)
            // (receive)
            Method receiveMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int response = (int) receiveMethod.invoke(radarAltimeter);
            assertEquals(response, 1);
            // (measureAltitude)
            Method measureAltitudeMethod = radarAltimeter.getClass().getDeclaredMethod("receive");
            int altitude = (int) measureAltitudeMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
            assertEquals(PrimaryFlightDisplay.instance.altitudeRadarAltimeter, 5000);

            // shock sensor (alarm - shock detected (body))
            Method alarmMethod = shockSensorPort.getClass().getDeclaredMethod("alarm");
            boolean isShockDetected = (boolean) alarmMethod.invoke(shockSensorPort);
            PrimaryFlightDisplay.instance.isShockSensorBodyShockDetected = isShockDetected;
            assertTrue(PrimaryFlightDisplay.instance.isShockSensorBodyShockDetected);

            // TODO: stalling sensor (measure)
            /*Method measureMethod = stallingSensorPort.getClass().getDeclaredMethod("measure");
            int stalling = (int) measureMethod.invoke(stallingSensorPort);
            PrimaryFlightDisplay.instance. = airPressure;
            assertEquals(PrimaryFlightDisplay.instance.airPressure, 21);*/
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void shutdown(){
        // airConditioningPort = AirConditioningFactory.build();
        // apuPort = APUFactory.build();
        batteryPort = BatteryFactory.build();
        deicingSystemPort = DeIcingSystemFactory.build();
        enginePort = EngineFactory.build();
        fuelTank = FuelTankFactory.build();
        // gearPort = GearFactory.build();
        pitotTubePort = PitotTubeFactory.build();
        radarAltimeter = RadarAltimeterFactory.build();
        weatherRadarPort = WeatherRadarFactory.build();

        try {
            // ToDo air conditioning (off)
            Method offMethod = airConditioningPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(airConditioningPort);
//            PrimaryFlightDisplay.instance.isAirConditioningOn = isOn;
//            assertFalse(PrimaryFlightDisplay.instance.isAirConditioningOn);

            // apu (shutdown)
            Method shutdownMethod = apuPort.getClass().getDeclaredMethod("shutdown");
            boolean isStarted = (boolean) shutdownMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertFalse(PrimaryFlightDisplay.instance.isAPUStarted);

            // battery (discharge)
            Method dischargeMethod = batteryPort.getClass().getDeclaredMethod("charge");
            int percentage = (int) dischargeMethod.invoke(batteryPort);
            PrimaryFlightDisplay.instance.percentageBattery = percentage;
            assertEquals(PrimaryFlightDisplay.instance.percentageBattery, 0);

            // brake (100%)
            Method brakeMethod = gearPort.getClass().getDeclaredMethod("setBrake");
            int brakePercentage = (int) brakeMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.gearBrakePercentage = brakePercentage;
            assertEquals(PrimaryFlightDisplay.instance.gearBrakePercentage, 100);

            // deicing system (refill)
            Method refillMethod = deicingSystemPort.getClass().getDeclaredMethod("refill");
            int amount = (int) refillMethod.invoke(deicingSystemPort);
            PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
            assertEquals(PrimaryFlightDisplay.instance.amountDeIcingSystem, 1000);
            // (deactivate)
            Method deactivateMethod = deicingSystemPort.getClass().getDeclaredMethod("deactivate");
            boolean isActivated = (boolean) deactivateMethod.invoke(deicingSystemPort);
            PrimaryFlightDisplay.instance.isDeIcingSystemActivated = isActivated;
            assertFalse(PrimaryFlightDisplay.instance.isDeIcingSystemActivated);

            // engine (shutdown)
            shutdownMethod = enginePort.getClass().getDeclaredMethod("start");
            isStarted = (boolean) shutdownMethod.invoke(enginePort);
            PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
            assertFalse(PrimaryFlightDisplay.instance.isEngineStarted);

            // fuel tank (takeOut)
            Method takeOutMethod = fuelTank.getClass().getDeclaredMethod("takeOut");
            amount = (int) takeOutMethod.invoke(fuelTank);
            PrimaryFlightDisplay.instance.amountOfFuel = amount;
            // assertEquals(PrimaryFlightDisplay.instance.amountOfFuel, ?);                         TODO: no value to compare

            // pitot tube - clean
            Method cleanMethod = pitotTubePort.getClass().getDeclaredMethod("clean");
            boolean isCleaned = (boolean) cleanMethod.invoke(pitotTubePort);
            PrimaryFlightDisplay.instance.isPitotTubeCleaned = isCleaned;
            assertTrue(PrimaryFlightDisplay.instance.isPitotTubeCleaned);

            // radar altimeter (off)
            offMethod = radarAltimeter.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(radarAltimeter);
            PrimaryFlightDisplay.instance.isRadarAltimeterOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isRadarAltimeterOn);

            // temperature sensor (measure)
            Method measureMethod = temperatureSensorPort.getClass().getDeclaredMethod("measure");
            int temperature = (int) measureMethod.invoke(temperatureSensorPort);
            PrimaryFlightDisplay.instance.temperatureBody = temperature;
            PrimaryFlightDisplay.instance.temperatureWing = temperature;
            assertEquals(PrimaryFlightDisplay.instance.temperatureBody, 0);
            assertEquals(PrimaryFlightDisplay.instance.temperatureWing, 0);

            // weather radar (off)
            offMethod = weatherRadarPort.getClass().getDeclaredMethod("off");
            isOn = (boolean) offMethod.invoke(weatherRadarPort);
            PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
            assertFalse(PrimaryFlightDisplay.instance.isWeatherRadarOn);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
