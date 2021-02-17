import factory.AntiCollisionLightFactory;
import factory.RightAileronFactory;
import factory.SlatFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSlat {
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
        componentPort = SlatFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = SlatFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            assertNotNull(neutralMethod);

            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            assertNotNull(fullDownMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            assertNotNull(downMethod);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            assertNotNull(upMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void neutral() {
        componentPort = SlatFactory.build();
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
    public void fullDown() {
        componentPort = SlatFactory.build();
        try {
            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            int degree = (int) fullDownMethod.invoke(componentPort);
            assertEquals(-60, degree);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void down() {
        componentPort = SlatFactory.build();
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

    @Test
    @Order(6)
    public void up() {
        componentPort = SlatFactory.build();
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

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}

