import factory.ExhaustGasTemperatureSensorFactory;
import factory.RightNavigationLightFactory;
import logging.LogEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExhaustGasTemperatureSensorTest {
    @BeforeEach
    public void initPlane() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    public void factoryTest(){

        assertNotNull(ExhaustGasTemperatureSensorFactory.build());
    }

    @Test
    public void methodTest(){
        Object egts = ExhaustGasTemperatureSensorFactory.build();


        try{
            assertEquals("ExhaustGasTemperatureSensorV1",egts.getClass().getDeclaredMethod("version").invoke(egts));
            assertTrue(800 <= (int) egts.getClass().getDeclaredMethod("measure").invoke(egts) && (int) egts.getClass().getDeclaredMethod("measure").invoke(egts) <= 1000);
            assertEquals(false, egts.getClass().getDeclaredMethod("alarmMajor").invoke(egts, 1001));
            assertEquals(false, egts.getClass().getDeclaredMethod("alarmCritical").invoke(egts, 1001));

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
