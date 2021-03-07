import factory.APUFactory;
import factory.EngineFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestEngine {
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
        componentPort = EngineFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = EngineFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method startMethod = componentPort.getClass().getDeclaredMethod("start");
            assertNotNull(startMethod);

            Method increaseRPMMethod = componentPort.getClass().getDeclaredMethod("increaseRPM", int.class);
            assertNotNull(increaseRPMMethod);

            Method decreaseRPMMethod = componentPort.getClass().getDeclaredMethod("decreaseRPM", int.class);
            assertNotNull(decreaseRPMMethod);

            Method shutdownMethod = componentPort.getClass().getDeclaredMethod("shutdown");
            assertNotNull(shutdownMethod);

            Method extinguishFireMethod = componentPort.getClass().getDeclaredMethod("extinguishFire");
            assertNotNull(extinguishFireMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = EngineFactory.build();
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
        componentPort = EngineFactory.build();
        try {
            Method startMethod = componentPort.getClass().getDeclaredMethod("start");
            boolean isOn = (boolean) startMethod.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void increaseRPM() {
        componentPort = EngineFactory.build();
        try {
            Method increaseRPMMethod = componentPort.getClass().getDeclaredMethod("increaseRPM", int.class);
            int rpm = (int) increaseRPMMethod.invoke(componentPort,100);
            assertEquals(100,rpm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void decreaseRPM() {
        componentPort = EngineFactory.build();
        try {
            Method decreaseRPMMethod = componentPort.getClass().getDeclaredMethod("decreaseRPM", int.class);
            int rpm = (int) decreaseRPMMethod.invoke(componentPort,100);
            assertEquals(0,rpm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void shutdown() {
        componentPort = EngineFactory.build();
        try {
            Method shutdownMethod = componentPort.getClass().getDeclaredMethod("shutdown");
            boolean isOn = (boolean) shutdownMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void extinguishFire() {
        componentPort = EngineFactory.build();
        try {
            Method extinguishFireMethod = componentPort.getClass().getDeclaredMethod("extinguishFire");
            extinguishFireMethod.invoke(componentPort);
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