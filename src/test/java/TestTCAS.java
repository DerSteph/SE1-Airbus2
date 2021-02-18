import factory.TCASFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTCAS {
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
        componentPort = TCASFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = TCASFactory.build();
        try
        {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            assertNotNull(connectMethod);

            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            assertNotNull(scanMethod);

            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmMethod);

            Method determineAltitudeMethod = connectMethod.getClass().getDeclaredMethod("determineAltitude", String.class);
            assertNotNull(determineAltitudeMethod);

            Method setAltitudeMethod = connectMethod.getClass().getDeclaredMethod("setAltitude", int.class);
            assertNotNull(setAltitudeMethod);

            Method offMethod = connectMethod.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void on() {
        componentPort = TCASFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean isOn = (boolean) onMethod.invoke(componentPort);
            assertTrue(isOn);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void connect() {
        componentPort = TCASFactory.build();
        try {
            Method connectMethod = componentPort.getClass().getDeclaredMethod("connect", String.class);
            boolean isConnected = (boolean) connectMethod.invoke(componentPort, "75,6");
            assertTrue(isConnected);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void scan() {
        componentPort = TCASFactory.build();
        try {
            Method scanMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            boolean scanned = (boolean) scanMethod.invoke(componentPort, "plane");
            assertTrue(scanned);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void alarm() {
        componentPort = TCASFactory.build();
        try {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            boolean isAlarm = (boolean) alarmMethod.invoke(componentPort);
            assertTrue(isAlarm);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(7)
    public void determineAltitude() {
        componentPort = TCASFactory.build();
        try {
            Method determineAltitudeMethod = componentPort.getClass().getDeclaredMethod("determineAltitude", String.class);
            int altitude = (int) determineAltitudeMethod.invoke(componentPort, "plane");
            assertEquals(0, altitude);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(8)
    public void setAltitude() {
        componentPort = TCASFactory.build();
        try {
            Method setAltitudeMethod = componentPort.getClass().getDeclaredMethod("setAltitude", int.class);
            int altitude = (int) setAltitudeMethod.invoke(componentPort, 5);
            assertEquals(5, altitude);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(9)
    public void off() {
        componentPort = TCASFactory.build();
        try {
            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean isOn = (boolean) offMethod.invoke(componentPort);
            assertFalse(isOn);
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
