import factory.TemperatureSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureSensorTest
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
        componentPort = TemperatureSensorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() throws NoSuchMethodException
    {
        componentPort = TemperatureSensorFactory.build();

        Method measure = componentPort.getClass().getDeclaredMethod("measure");
        assertNotNull(measure);

        Method alarm = componentPort.getClass().getDeclaredMethod("alarm", int.class);
        assertNotNull(alarm);

    }

    @Test
    @Order(3)
    public void measure() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = TemperatureSensorFactory.build();

        Method measure = componentPort.getClass().getDeclaredMethod("measure");
        int airPressure = (int) measure.invoke(componentPort);
        assertEquals(airPressure, 0);

    }

    @Test
    @Order(4)
    public void alarm() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = TemperatureSensorFactory.build();

        Method alarm = componentPort.getClass().getDeclaredMethod("alarm", int.class);
        boolean isActive = (boolean) alarm.invoke(componentPort, 21);
        assertTrue(isActive);

    }

    @AfterEach
    public void close()
    {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}
