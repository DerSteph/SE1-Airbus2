import event.route_management.CheckPoint;
import factory.CostOptimizerFactory;
import factory.RouteManagementFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RouteManagementTest {
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
        componentPort = RouteManagementFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = RouteManagementFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method addMethod = componentPort.getClass().getDeclaredMethod("add", CheckPoint.class);
            assertNotNull(addMethod);

            Method removeMethod = componentPort.getClass().getDeclaredMethod("remove", CheckPoint.class);
            assertNotNull(removeMethod);

            Method optimizeMethod = componentPort.getClass().getDeclaredMethod("setCostIndex", int.class);
            assertNotNull(optimizeMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = RouteManagementFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            assertTrue(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void off() {
        componentPort = RouteManagementFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            assertFalse(isOn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void add() {
        componentPort = RouteManagementFactory.build();
        try {
            Method addMethod = componentPort.getClass().getDeclaredMethod("add", CheckPoint.class);
            int size = (int) addMethod.invoke(componentPort,
                    new CheckPoint(0, "Mosbach", "49.351978", "9.145870"));
            assertEquals(1, size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void remove() {
        componentPort = RouteManagementFactory.build();
        try {
            CheckPoint checkPoint =  new CheckPoint(0, "Mosbach", "49.351978", "9.145870");

            Method addMethod = componentPort.getClass().getDeclaredMethod("add", CheckPoint.class);
            int size = (int) addMethod.invoke(componentPort, checkPoint);
            assertEquals(1, size);

            Method removeMethod = componentPort.getClass().getDeclaredMethod("remove", CheckPoint.class);
            size = (int) removeMethod.invoke(componentPort, checkPoint);
            assertEquals(0, size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void setCostIndex() {
        componentPort = CostOptimizerFactory.build();
        try {
            Method optimizeMethod = componentPort.getClass().getDeclaredMethod("setCostIndex", int.class);
            int index = (int) optimizeMethod.invoke(componentPort, 2);
            assertEquals(2, index);
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