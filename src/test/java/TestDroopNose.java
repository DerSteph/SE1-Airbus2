import factory.DroopNoseFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDroopNose {
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
        componentPort = DroopNoseFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = DroopNoseFactory.build();
        try
        {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            assertNotNull(neutralMethod);

            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            assertNotNull(fullDownMethod);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down");
            assertNotNull(downMethod);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up");
            assertNotNull(upMethod);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void neutral() {
        componentPort = DroopNoseFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int isNeutral = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, isNeutral);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void fullDown() {
        componentPort = DroopNoseFactory.build();
        try {
            Method fullDownMethod = componentPort.getClass().getDeclaredMethod("fullDown");
            int isFullDown = (int) fullDownMethod.invoke(componentPort);
            assertEquals(-100, isFullDown);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void down() {
        componentPort = DroopNoseFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int isNeutral = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, isNeutral);

            Method downMethod = componentPort.getClass().getDeclaredMethod("down", int.class);
            int isDown = (int) downMethod.invoke(componentPort, 5);
            assertEquals(-5, isDown);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void up() {
        componentPort = DroopNoseFactory.build();
        try {
            Method neutralMethod = componentPort.getClass().getDeclaredMethod("neutral");
            int isNeutral = (int) neutralMethod.invoke(componentPort);
            assertEquals(0, isNeutral);

            Method upMethod = componentPort.getClass().getDeclaredMethod("up", int.class);
            int isUp = (int) upMethod.invoke(componentPort, 5);
            assertEquals(5, isUp);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void close()
    {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}
