import factory.DeIcingSystemFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class DeIcingSystemTest
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
        componentPort = DeIcingSystemFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() throws NoSuchMethodException
    {
        componentPort = DeIcingSystemFactory.build();

        Method activate = componentPort.getClass().getDeclaredMethod("activate");
        assertNotNull(activate);

        Method deactivate = componentPort.getClass().getDeclaredMethod("deactivate");
        assertNotNull(deactivate);

        Method deIce = componentPort.getClass().getDeclaredMethod("deIce", int.class);
        assertNotNull(deIce);

        Method refill = componentPort.getClass().getDeclaredMethod("refill");
        assertNotNull(refill);

    }

    @Test
    @Order(3)
    public void activate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = DeIcingSystemFactory.build();

        Method activate = componentPort.getClass().getDeclaredMethod("activate");
        boolean isActive = (boolean) activate.invoke(componentPort);
        assertTrue(isActive);

    }

    @Test
    @Order(4)
    public void deactivate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = DeIcingSystemFactory.build();

        Method deactivate = componentPort.getClass().getDeclaredMethod("deactivate");
        boolean isActive = (boolean) deactivate.invoke(componentPort);
        assertFalse(isActive);

    }

    @Test
    @Order(5)
    public void deIce() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = DeIcingSystemFactory.build();

        Method deIce = componentPort.getClass().getDeclaredMethod("deIce", int.class);
        int amount = (int) deIce.invoke(componentPort, 50);
        assertEquals(amount, 950);

    }

    @Test
    @Order(6)
    public void refill() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        componentPort = DeIcingSystemFactory.build();

        Method refill = componentPort.getClass().getDeclaredMethod("refill");
        int amount = (int) refill.invoke(componentPort);
        assertEquals(amount, 1000);

    }

    @AfterEach
    public void close()
    {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}
