package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;


import event.shock_sensor.*;
import event.stalling_sensor.*;
import event.temperature_sensor.*;

import event.air_flow_sensor.AirFlowSensorWingMeasure;
import event.deicing_system.DeIcingSystemActivate;
import event.deicing_system.DeIcingSystemDeIce;
import event.deicing_system.DeIcingSystemDeactivate;
import event.deicing_system.DeIcingSystemRefill;
import event.engine_oil_tank.EngineOilTankDecreaseLevel;
import event.engine_oil_tank.EngineOilTankIncreaseLevel;
import event.fuel_tank.FuelTankRefill;
import event.fuel_tank.FuelTankTakeOut;


import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;

import factory.DeIcingSystemFactory;
import factory.EngineOilTankFactory;
import factory.FuelTankFactory;
import factory.SlatFactory;
import logging.LogEngine;
import recorder.FlightRecorder;


import factory.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;

    private ArrayList<Object> shockSensorPortList;
    private ArrayList<Object> stallingSensorPortList;
    private ArrayList<Object> temperatureSensorPortList;
    private ArrayList<Object> airFlowSensorPortList;

    private ArrayList<Object> deIcingSystemPortList;
    private ArrayList<Object> engineOilTankPortList;
    private ArrayList<Object> fuelTankPortList;

    // Add a new list for each service...

    public Wing() {
        slatPortList = new ArrayList<>();

        shockSensorPortList = new ArrayList<>();
        stallingSensorPortList = new ArrayList<>();
        temperatureSensorPortList = new ArrayList<>();
        airFlowSensorPortList = new ArrayList<>();

        deIcingSystemPortList = new ArrayList<>();
        engineOilTankPortList = new ArrayList<>();
        fuelTankPortList = new ArrayList<>();

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

        for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorWing; i++) {
            airFlowSensorPortList.add(AirFlowSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemWing; i++)
        {
            deIcingSystemPortList.add(DeIcingSystemFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfEngineOilTank; i++)
        {
            engineOilTankPortList.add(EngineOilTankFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfFuelTank; i++)
        {
            fuelTankPortList.add(FuelTankFactory.build());
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

    // --- DeIcingSystem -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DeIcingSystemActivate deIcingSystemActivate){
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemActivate.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + deIcingSystemActivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method activateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("activate");
                LogEngine.instance.write("activateMethod = " + activateMethod);

                boolean active = (boolean) activateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("active = " + active);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = active;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (active): " + active);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemActivate): " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemActivate: " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
    }

    @Subscribe
    public void receive(DeIcingSystemDeactivate deIcingSystemDeactivate){
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemDeactivate.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + deIcingSystemDeactivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deactivateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deactivate");
                LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

                boolean active = (boolean) deactivateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("active = " + active);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = active;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (active): " + active);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemDeactivate): " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemDeactivate: " + PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
    }

    @Subscribe
    public void receive(DeIcingSystemDeIce deIcingSystemDeIce){
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemDeIce.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + deIcingSystemDeIce.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deIce = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deIce");
                LogEngine.instance.write("deIceMethod = " + deIce);

                int amount = (int) deIce.invoke(deIcingSystemPortList.get(i), deIcingSystemDeIce.amount);
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemDeIce): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemDeIce: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
    }

    @Subscribe
    public void receive(DeIcingSystemRefill deIcingSystemRefill){
        LogEngine.instance.write("+ Wing.receive(" + deIcingSystemRefill.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + deIcingSystemRefill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method refill = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("refill");
                LogEngine.instance.write("refillMethod = " + refill);

                int amount = (int) refill.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
                FlightRecorder.instance.insert("Wing", "DeIcingSystem (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemRefill): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemRefill: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
    }
    // --- EngineOilTank -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(EngineOilTankIncreaseLevel engineOilTankIncreaseLevel){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(EngineOilTankDecreaseLevel engineOilTankDecreaseLevel){
        throw new RuntimeException("Not implemented yet.");
    }

    // --- FuelTank -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(FuelTankTakeOut fuelTankTakeOut){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(FuelTankRefill fuelTankRefill){
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

    // --- AirFlowSensor ----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AirFlowSensorWingMeasure airFlowSensorWingMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

}