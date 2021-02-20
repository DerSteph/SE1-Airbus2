import factory.EngineFactory;
import factory.GearFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestGear {
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
        componentPort = GearFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = GearFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down");
            assertNotNull(downMethod);

            Method setBrakeMethod = componentPort.getClass().getDeclaredMethod("setBrake");
            assertNotNull(setBrakeMethod);

            Method setBrakeAmountMethod = componentPort.getClass().getDeclaredMethod("setBrake", int.class);
            assertNotNull(setBrakeAmountMethod);

            Method releaseBrakeMethod = componentPort.getClass().getDeclaredMethod("releaseBrake");
            assertNotNull(releaseBrakeMethod);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up");
            assertNotNull(upMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = GearFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String version = (String) versionMethod.invoke(componentPort);
            assertEquals("1",version);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void start() {
        componentPort = GearFactory.build();
        try {
            Method downMethod = componentPort.getClass().getDeclaredMethod("down");
            boolean isDown = (boolean) downMethod.invoke(componentPort);
            assertTrue(isDown);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void setBrake() {
        componentPort = GearFactory.build();
        try {
            Method setBrakeMethod = componentPort.getClass().getDeclaredMethod("setBrake");
            int gearBrakePercentage = (int) setBrakeMethod.invoke(componentPort);
            assertEquals(100,gearBrakePercentage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void setBrakeAmount() {
        componentPort = GearFactory.build();
        try {
            Method setBrakeAmountMethod = componentPort.getClass().getDeclaredMethod("setBrake", int.class);
            int gearBrakePercentage = (int) setBrakeAmountMethod.invoke(componentPort, 69);
            assertEquals(69,gearBrakePercentage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void releaseBrake() {
        componentPort = GearFactory.build();
        try {
            Method releaseBrakeMethod = componentPort.getClass().getDeclaredMethod("releaseBrake");
            int gearBrakePercentage = (int) releaseBrakeMethod.invoke(componentPort);
            assertEquals(0,gearBrakePercentage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void up() {
        componentPort = GearFactory.build();
        try {
            Method downMethod = componentPort.getClass().getDeclaredMethod("up");
            boolean isDown = (boolean) downMethod.invoke(componentPort);
            assertFalse(isDown);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}