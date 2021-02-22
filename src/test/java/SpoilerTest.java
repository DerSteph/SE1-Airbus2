import factory.SpoilerFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpoilerTest {
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
        componentPort = SpoilerFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = SpoilerFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            assertNotNull(neutralMethod);

            Method fullUpMethod = componentPort.getClass().getDeclaredMethod("fullUp");
            assertNotNull(fullUpMethod);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            assertNotNull(upMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            assertNotNull(downMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void neutral() {
        componentPort = SpoilerFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, degree);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void fullUp() {
        componentPort = SpoilerFactory.build();
        try {
            Method fullUpMethod = componentPort.getClass().getDeclaredMethod("fullUp");
            int degree = (int) fullUpMethod.invoke(componentPort);
            assertEquals(60, degree);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void up() {
        componentPort = SpoilerFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, degree);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            degree = (int) upMethod.invoke(componentPort, 5);
            assertEquals(5, degree);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void down() {
        componentPort = SpoilerFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int degree = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, degree);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            degree = (int) downMethod.invoke(componentPort, 5);
            assertEquals(-5, degree);
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

