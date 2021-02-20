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
    private Object apuPort;
    // ToDo private Object droopNosePort;
    private Object enginePort;
    private Object gearPort;
    private Object hydraulicPumpPort;
    private Object kitchenPort;
    // ToDo private Object slatPort;
    // ToDo private Object tcasPort;
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
        apuPort = APUFactory.build();
        enginePort = EngineFactory.build();
        gearPort = GearFactory.build();
        hydraulicPumpPort = HydraulicPumpFactory.build();
        kitchenPort = KitchenFactory.build();
        weatherRadarPort = WeatherRadarFactory.build();

        try {
            // ToDo air conditioning (on)
            Method onMethod = airConditioningPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(airConditioningPort);
//            PrimaryFlightDisplay.instance.isAirConditioningOn = isOn;
//            assertTrue(PrimaryFlightDisplay.instance.isAirConditioningOn);

            // apu (start)
            Method startMethod = apuPort.getClass().getDeclaredMethod("start");
            boolean isStarted = (boolean) startMethod.invoke(apuPort);
            PrimaryFlightDisplay.instance.isAPUStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isAPUStarted);

            // brake (100%)
            Method brakeMethod = gearPort.getClass().getDeclaredMethod("setBrake");
            int brakePercentage = (int) brakeMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.gearBrakePercentage = brakePercentage;
            assertEquals(PrimaryFlightDisplay.instance.gearBrakePercentage, 100);

            // engine (start)
            startMethod = enginePort.getClass().getDeclaredMethod("start");
            isStarted = (boolean) startMethod.invoke(enginePort);
            PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
            assertTrue(PrimaryFlightDisplay.instance.isEngineStarted);

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

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(3)
    public void takeOff(){
        enginePort = EngineFactory.build();

        try {
            // engine (increase RPM)
            Method increaseMethod = enginePort.getClass().getDeclaredMethod("increaseRPM");
            int rpm = (int) increaseMethod.invoke(increaseMethod);
            int oldrpm = PrimaryFlightDisplay.instance.rpmEngine;
            PrimaryFlightDisplay.instance.rpmEngine = rpm;
            assertTrue(PrimaryFlightDisplay.instance.rpmEngine > oldrpm);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(4)
    public void climbing(){
        gearPort = EngineFactory.build();

        try{
            // gear (up)
            Method gearUpMethod = gearPort.getClass().getDeclaredMethod("up");
            boolean isDown = (boolean) gearUpMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.isGearDown = isDown;
            assertFalse(PrimaryFlightDisplay.instance.isGearDown);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Test
    @Order(5)
    public void rightTurn(){
        /*try{

        } catch(Exception e){
            System.out.println(e.getMessage());
        }*/
    }

    @Test
    @Order(6)
    public void leftTurn(){
        /*try{

        } catch(Exception e){
            System.out.println(e.getMessage());
        }*/
    }

    @Test
    @Order(7)
    public void descent(){
        /*try{

        } catch(Exception e){
            System.out.println(e.getMessage());
        }*/
    }

    @Test
    @Order(8)
    public void landing(){
        apuPort = APUFactory.build();
        enginePort = EngineFactory.build();
        gearPort = GearFactory.build();

        try {
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

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void shutdown(){
        enginePort = EngineFactory.build();
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

            // brake (100%)
            Method brakeMethod = gearPort.getClass().getDeclaredMethod("setBrake");
            int brakePercentage = (int) brakeMethod.invoke(gearPort);
            PrimaryFlightDisplay.instance.gearBrakePercentage = brakePercentage;
            assertEquals(PrimaryFlightDisplay.instance.gearBrakePercentage, 100);

            // engine (shutdown)
            shutdownMethod = enginePort.getClass().getDeclaredMethod("start");
            isStarted = (boolean) shutdownMethod.invoke(enginePort);
            PrimaryFlightDisplay.instance.isEngineStarted = isStarted;
            assertFalse(PrimaryFlightDisplay.instance.isEngineStarted);

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
