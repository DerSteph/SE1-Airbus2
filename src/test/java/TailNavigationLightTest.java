import factory.RightNavigationLightFactory;
import factory.TailNavigationLightFactory;
import logging.LogEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TailNavigationLightTest {

    @BeforeEach
    public void initPlane() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    public void factoryTest(){
        assertNotNull(TailNavigationLightFactory.build());
    }

    @Test
    public void methodTest(){
        Object tnl = TailNavigationLightFactory.build();
        try {
            assertEquals("TailNavigationLight1",tnl.getClass().getDeclaredMethod("version").invoke(tnl));

            assertEquals(true,tnl.getClass().getDeclaredMethod("on").invoke(tnl));
            assertEquals(true,tnl.getClass().getDeclaredMethod("isOn").invoke(tnl));
            assertEquals(false,tnl.getClass().getDeclaredMethod("off").invoke(tnl));
            assertEquals(false,tnl.getClass().getDeclaredMethod("isOn").invoke(tnl));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
