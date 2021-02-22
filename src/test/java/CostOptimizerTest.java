import event.route_management.CheckPoint;
import factory.CargoCompartmentLightFactory;
import factory.CostOptimizerFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CostOptimizerTest {
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
        componentPort = CostOptimizerFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = CostOptimizerFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

            Method addMethod = componentPort.getClass().getDeclaredMethod("add", CheckPoint.class);
            assertNotNull(addMethod);

            Method removeMethod = componentPort.getClass().getDeclaredMethod("remove", int.class);
            assertNotNull(removeMethod);

            Method optimizeMethod = componentPort.getClass().getDeclaredMethod("optimize", ArrayList.class);
            assertNotNull(optimizeMethod);

            Method validateMethod = componentPort.getClass().getDeclaredMethod("validate", int.class);
            assertNotNull(validateMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = CostOptimizerFactory.build();
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
        componentPort = CostOptimizerFactory.build();
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
        componentPort = CostOptimizerFactory.build();
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
        componentPort = CostOptimizerFactory.build();
        try {
            add();

            Method removeMethod = componentPort.getClass().getDeclaredMethod("remove", int.class);
            int size = (int) removeMethod.invoke(componentPort, 0);
            assertEquals(0, size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void optimize() {
        componentPort = CostOptimizerFactory.build();
        try {
            Method optimizeMethod = componentPort.getClass().getDeclaredMethod("optimize", ArrayList.class);
            int size = (int) optimizeMethod.invoke(componentPort, new ArrayList<CheckPoint>() {{
                add(new CheckPoint(0, "Mosbach", "49.351978", "9.145870"));
                add(new CheckPoint(1, "New York", "40.712776", "-74.005974"));
            }});
            assertEquals(2, size);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void validate() {
        componentPort = CostOptimizerFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("validate", int.class);
            boolean exists = (boolean) onMethod.invoke(componentPort, 0);
            assertFalse(exists);

            add();

            exists = (boolean) onMethod.invoke(componentPort, 0);
            assertTrue(exists);
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