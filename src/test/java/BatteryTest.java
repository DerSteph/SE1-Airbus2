import factory.BatteryFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BatteryTest
{
    private Object componentPort;

    @BeforeEach
    public void init()
    {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    @Order(1)
    public void factory()
    {
        componentPort = BatteryFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() throws NoSuchMethodException
    {
        componentPort = BatteryFactory.build();

        Method charge = componentPort.getClass().getDeclaredMethod("charge");
        assertNotNull(charge);

        Method discharge = componentPort.getClass().getDeclaredMethod("discharge");
        assertNotNull(discharge);

    }

    @Test
    @Order(3)
    public void charge() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = BatteryFactory.build();

        Method charge = componentPort.getClass().getDeclaredMethod("charge");
        int percentage = (int) charge.invoke(componentPort);
        assertEquals(percentage, 100);

    }

    @Test
    @Order(4)
    public void discharge() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = BatteryFactory.build();

        Method charge = componentPort.getClass().getDeclaredMethod("discharge");
        int percentage = (int) charge.invoke(componentPort);
        assertEquals(percentage, 0);

    }

    @AfterEach
    public void close()
    {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}
