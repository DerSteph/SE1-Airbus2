import base.*;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcedureTest {
    private Airplane airplane;
    private Cockpit cockpit;
    private Object componentPort;

    @BeforeEach
    public void init(){
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        airplane = new Airplane();
        airplane.build();

        cockpit = new Cockpit(airplane);
    }

    @Test
    @Order(1)
    public void startup(){

    }

    @Test
    @Order(2)
    public void taxi(){

    }


    @Test
    @Order(3)
    public void takeOff(){

    }


    @Test
    @Order(4)
    public void climbing(){

    }


    @Test
    @Order(5)
    public void rightTurn(){

    }

    @Test
    @Order(6)
    public void leftTurn(){

    }

    @Test
    @Order(7)
    public void descent(){

    }

    @Test
    @Order(8)
    public void landing(){

    }

    @Test
    @Order(9)
    public void shutdown(){

    }
}
