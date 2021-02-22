
import factory.NitrogenBottleFactory;
import factory.OxygenBottleFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OxygenBottleTest {
    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        componentPort = OxygenBottleFactory.build();
    }

    @Test
    @Order(1)
    public void factory() {
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        try
        {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut");
            assertNotNull(takeOutMethod);

            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            assertNotNull(refillMethod);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void takeOut() {
        try {
            Method takeOutMethod = componentPort.getClass().getDeclaredMethod("takeOut");
            int nitrogenBottleAmount = (int) takeOutMethod.invoke(componentPort);
            assertNotNull(nitrogenBottleAmount);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void refill() {
        try {
            Method refillMethod = componentPort.getClass().getDeclaredMethod("refill");
            int nitrogenBottleAmount = (int) refillMethod.invoke(componentPort);
            assertNotNull(nitrogenBottleAmount);
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
