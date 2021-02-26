import factory.GearFactory;
import factory.HydraulicPumpFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHydraulicPump {
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
        componentPort = HydraulicPumpFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = HydraulicPumpFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method compressMethod = componentPort.getClass().getDeclaredMethod("compress");
            assertNotNull(compressMethod);

            Method compressAmountMethod = componentPort.getClass().getDeclaredMethod("compress", int.class);
            assertNotNull(compressAmountMethod);

            Method decompressMethod = componentPort.getClass().getDeclaredMethod("decompress");
            assertNotNull(decompressMethod);

            Method refillOilMethod = componentPort.getClass().getDeclaredMethod("refillOil");
            assertNotNull(refillOilMethod);

            Method refillOilAmountMethod = componentPort.getClass().getDeclaredMethod("refillOil", int.class);
            assertNotNull(refillOilAmountMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = HydraulicPumpFactory.build();
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
    public void compress() {
        componentPort = HydraulicPumpFactory.build();
        try {
            Method compressMethod = componentPort.getClass().getDeclaredMethod("compress");
            int amount = (int) compressMethod.invoke(componentPort);
            assertEquals(1000,amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void compressAmount() {
        componentPort = HydraulicPumpFactory.build();
        try {
            Method compressAmountMethod = componentPort.getClass().getDeclaredMethod("compress", int.class);
            int amount = (int) compressAmountMethod.invoke(componentPort, 500);
            assertEquals(500,amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void decompress() {
        componentPort = HydraulicPumpFactory.build();
        try {
            Method decompressMethod = componentPort.getClass().getDeclaredMethod("decompress");
            int amount = (int) decompressMethod.invoke(componentPort);
            assertEquals(1000,amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void refillOil() {
        componentPort = HydraulicPumpFactory.build();
        try {
            Method refillOilMethod = componentPort.getClass().getDeclaredMethod("refillOil");
            int amount = (int) refillOilMethod.invoke(componentPort);
            assertEquals(1000,amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void refillOilAmount() {
        componentPort = HydraulicPumpFactory.build();
        try {
            Method refillOilAmountMethod = componentPort.getClass().getDeclaredMethod("refillOil", int.class);
            int amount = (int) refillOilAmountMethod.invoke(componentPort, 500);
            assertEquals(500,amount);
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