import factory.RightNavigationLightFactory;
import logging.LogEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RightNavigationLightTest {

    @BeforeEach
    public void initPlane() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    public void factoryTest(){
        assertNotNull(RightNavigationLightFactory.build());
    }

    @Test
    public void methodTest(){
        Object rnl = RightNavigationLightFactory.build();
        try {
            assertEquals("RightNavigationLight1",rnl.getClass().getDeclaredMethod("version").invoke(rnl));
            assertEquals("green", rnl.getClass().getDeclaredMethod("getLightType").invoke(rnl).toString());
            assertEquals("starboard", rnl.getClass().getDeclaredMethod("getPosition").invoke(rnl).toString());

            assertEquals(true,rnl.getClass().getDeclaredMethod("on").invoke(rnl));
            assertEquals(true,rnl.getClass().getDeclaredMethod("isOn").invoke(rnl));
            assertEquals(false,rnl.getClass().getDeclaredMethod("off").invoke(rnl));
            assertEquals(false,rnl.getClass().getDeclaredMethod("isOn").invoke(rnl));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
