package section;

import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;

import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.SlatFactory;

import factory.*;
import event.shock_sensor.*;
import event.stalling_sensor.*;
import event.temperature_sensor.*;

import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;
    private ArrayList<Object> shockSensorPortList;
    private ArrayList<Object> stallingSensorPortList;
    private ArrayList<Object> temperatureSensorPortList;

    // Add a new list for each service...

    public Wing() {
        slatPortList = new ArrayList<>();
        shockSensorPortList = new ArrayList<>();
        stallingSensorPortList = new ArrayList<>();
        temperatureSensorPortList = new ArrayList<>();
        // Add a new list for each service...
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfShockSensorWing; i++) {
            shockSensorPortList.add(ShockSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfStallingSensorWing; i++) {
            stallingSensorPortList.add(StallingSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorWing; i++) {
            temperatureSensorPortList.add(TemperatureSensorFactory.build());
        }

        // Add a new iteration for each service...
    }

    // --- Slat -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatUp slatUp) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SlatDown slatDown) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SlatFullDown slatFullDown) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(SlatNeutral slatNeutral) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- ShockSensor ------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(ShockSensorWingCalibrate shockSensorWingCalibrate) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(ShockSensorWingMeasure shockSensorWingMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- StallingSensor ---------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(StallingSensorWingMeasure stallingSensorWingMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- TemperatureSensor ------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TemperatureSensorWingMeasure temperatureSensorWingMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

}