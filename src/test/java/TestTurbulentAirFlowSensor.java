import factory.DroopNoseFactory;
import factory.TurbulentAirFlowSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTurbulentAirFlowSensor {
    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    @Order(1)
    public void factory() {
        componentPort = TurbulentAirFlowSensorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
            assertNotNull(measureMethod);

            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmMethod);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void measure() {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure");
            int isMeasured = (int) measureMethod.invoke(componentPort);
            assertEquals(100, isMeasured);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void alarm() {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            boolean isALarm = (boolean) alarmMethod.invoke(componentPort);
            assertEquals(true, isALarm);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

}
