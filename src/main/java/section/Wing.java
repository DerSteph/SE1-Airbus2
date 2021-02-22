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


import event.apu.APUDecreaseRPM;
import event.apu.APUIncreaseRPM;
import event.apu.APUShutdown;
import event.engine.EngineDecreaseRPM;
import event.engine.EngineIncreaseRPM;
import event.engine.EngineShutdown;
import event.engine.EngineStart;
import event.hydraulic_pump.*;
import event.landing_light.LandingLightBodyOn;
import event.landing_light.LandingLightWingOff;
import event.landing_light.LandingLightWingOn;
import event.left_aileron.*;
import event.left_navigation_light.LeftNavigationLightOff;
import event.left_navigation_light.LeftNavigationLightOn;
import event.right_aileron.*;

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

import event.spoiler.SpoilerDown;
import event.spoiler.SpoilerFullUp;
import event.spoiler.SpoilerNeutral;
import event.spoiler.SpoilerUp;
import event.weather_radar.WeatherRadarOn;
import factory.*;
import logging.LogEngine;

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
    private ArrayList<Object> engineList;
    private ArrayList<Object> hydraulicPumpList;

    private ArrayList<Object> leftAileronPortList;
    private ArrayList<Object> rightAileronPortList;
    private ArrayList<Object> spoilerPortList;
    private ArrayList<Object> landingLightPortList;
    private ArrayList<Object> leftNavigationLightPortList;

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

        engineList = new ArrayList<>();
        hydraulicPumpList = new ArrayList<>();

        slatPortList = new ArrayList<>();
        leftAileronPortList = new ArrayList<>();
        rightAileronPortList = new ArrayList<>();
        spoilerPortList = new ArrayList<>();
        landingLightPortList = new ArrayList<>();
        leftNavigationLightPortList = new ArrayList<>();

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
        for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
            engineList.add(SlatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
            hydraulicPumpList.add(SlatFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
            leftAileronPortList.add(LeftAileronFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
            rightAileronPortList.add(RightAileronFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
            spoilerPortList.add(SpoilerFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++) {
            landingLightPortList.add(LandingLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++) {
            leftNavigationLightPortList.add(LeftNavigationLightFactory.build());
        }
    }

    // --- Slat -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatNeutral slatNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + slatNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method method = slatPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    @Subscribe
    public void receive(SlatFullDown slatFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + slatFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method method = slatPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    @Subscribe
    public void receive(SlatUp slatUp) {
        LogEngine.instance.write("+ Wing.receive(" + slatUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method method = slatPortList.get(i).getClass().getDeclaredMethod("up", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(slatPortList.get(i), slatUp.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    @Subscribe
    public void receive(SlatDown slatDown) {
        LogEngine.instance.write("+ Wing.receive(" + slatDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + slatDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method method = slatPortList.get(i).getClass().getDeclaredMethod("down", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(slatPortList.get(i), slatDown.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Wing", "Slat (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSlat): " + PrimaryFlightDisplay.instance.degreeSlat);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSlat: " + PrimaryFlightDisplay.instance.degreeSlat);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LeftAileronNeutral -----------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LeftAileronNeutral leftAileronNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method method = leftAileronPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronFullUp leftAileronFullUp) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronFullUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method method = leftAileronPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronFullDown leftAileronFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method method = leftAileronPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronUp leftAileronUp) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method method = leftAileronPortList.get(i).getClass().getDeclaredMethod("up", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(leftAileronPortList.get(i), leftAileronUp.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
    }

    @Subscribe
    public void receive(LeftAileronDown leftAileronDown) {
        LogEngine.instance.write("+ Wing.receive(" + leftAileronDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method method = leftAileronPortList.get(i).getClass().getDeclaredMethod("down", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(leftAileronPortList.get(i), leftAileronDown.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Wing", "LeftAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeLeftAileron): " + PrimaryFlightDisplay.instance.degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + PrimaryFlightDisplay.instance.degreeLeftAileron);
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
                Method deIce = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deIce", int.class);
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
    public void receive(TemperatureSensorWingMeasure temperatureSensorWingMeasure)
    {
        LogEngine.instance.write("+ Wing.receive(" + temperatureSensorWingMeasure.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + temperatureSensorWingMeasure.toString() + ")");

        try
        {
            for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorWing; i++)
            {
                Method measureMethod = temperatureSensorPortList.get(i).getClass().getDeclaredMethod("measure");
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measure = (int) measureMethod.invoke(temperatureSensorPortList.get(i));
                LogEngine.instance.write("measure = " + measure);

                PrimaryFlightDisplay.instance.temperatureWing = measure;
                FlightRecorder.instance.insert("Wing", "TemperatureSensor (Measure): " + measure);
                LogEngine.instance.write("+");
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (TemperatureSensor): " + PrimaryFlightDisplay.instance.temperatureWing);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "TemperatureSensor: " + PrimaryFlightDisplay.instance.temperatureWing);
    }
    // --- RightAileronNeutral ----------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RightAileronNeutral rightAileronNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method method = rightAileronPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronFullUp rightAileronFullUp) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronFullUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method method = rightAileronPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronFullDown rightAileronFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method method = rightAileronPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronUp rightAileronUp) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method method = rightAileronPortList.get(i).getClass().getDeclaredMethod("up", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rightAileronPortList.get(i), rightAileronUp.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    @Subscribe
    public void receive(RightAileronDown rightAileronDown) {
        LogEngine.instance.write("+ Wing.receive(" + rightAileronDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method method = rightAileronPortList.get(i).getClass().getDeclaredMethod("down", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rightAileronPortList.get(i), rightAileronDown.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Wing", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- Spoiler ----------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SpoilerNeutral spoilerNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method method = spoilerPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(spoilerPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
    }

    @Subscribe
    public void receive(SpoilerFullUp spoilerFullUp) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerFullUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method method = spoilerPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(spoilerPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
    }

    @Subscribe
    public void receive(SpoilerUp spoilerUp) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method method = spoilerPortList.get(i).getClass().getDeclaredMethod("up", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(spoilerPortList.get(i), spoilerUp.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
    }

    @Subscribe
    public void receive(SpoilerDown spoilerDown) {
        LogEngine.instance.write("+ Wing.receive(" + spoilerDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
                Method method = spoilerPortList.get(i).getClass().getDeclaredMethod("down", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(spoilerPortList.get(i), spoilerDown.getDegree());
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSpoiler = degree;
                FlightRecorder.instance.insert("Wing", "Spoiler (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + PrimaryFlightDisplay.instance.degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + PrimaryFlightDisplay.instance.degreeSpoiler);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LandingLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LandingLightWingOn landingLightWingOn) {
        LogEngine.instance.write("+ Wing.receive(" + landingLightWingOn.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + landingLightWingOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++) {
                Method method = landingLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(landingLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightWingOn = isOn;
                FlightRecorder.instance.insert("Wing", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightWingOn): " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightWingOn: " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
    }

    @Subscribe
    public void receive(LandingLightWingOff landingLightWingOff) {
        LogEngine.instance.write("+ Wing.receive(" + landingLightWingOff.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + landingLightWingOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightWing; i++) {
                Method method = landingLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(landingLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightWingOn = isOn;
                FlightRecorder.instance.insert("Wing", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightWingOn): " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightWingOn: " + PrimaryFlightDisplay.instance.isLandingLightWingOn);
    }

    // ----------------------------------------------------------------------------------------------------------------


    // --- AirFlowSensor ----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AirFlowSensorWingMeasure airFlowSensorWingMeasure)
    {
        LogEngine.instance.write("+ Wing.receive(" + airFlowSensorWingMeasure.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + airFlowSensorWingMeasure.toString() + ")");

        try
        {
            for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorWing; i++)
            {
                Method measureMethod = airFlowSensorPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measure = (int) measureMethod.invoke(airFlowSensorPortList.get(i), "");
                LogEngine.instance.write("measure = " + measure);

                PrimaryFlightDisplay.instance.airPressure = measure;
                FlightRecorder.instance.insert("Wing", "AirFLowSensor (Measure): " + measure);
                LogEngine.instance.write("+");
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        LogEngine.instance.write("PrimaryFlightDisplay (AirFLowSensor): " + PrimaryFlightDisplay.instance.airPressure);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "AirFLowSensor: " + PrimaryFlightDisplay.instance.airPressure);
    }
    // --- LeftNavigationLight ----------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LeftNavigationLightOn leftNavigationLightOn) {
        LogEngine.instance.write("+ Wing.receive(" + leftNavigationLightOn.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftNavigationLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++) {
                Method method = leftNavigationLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(leftNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLeftNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Wing", "LandingLight (isOn): " + isOn);


                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLeftNavigationLightOn): " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLeftNavigationLightOn: " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
    }

    @Subscribe
    public void receive(LeftNavigationLightOff leftNavigationLightOff) {
        LogEngine.instance.write("+ Wing.receive(" + leftNavigationLightOff.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + leftNavigationLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftNavigationLight; i++) {
                Method method = leftNavigationLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(leftNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLeftNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Wing", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLeftNavigationLightOn): " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLeftNavigationLightOn: " + PrimaryFlightDisplay.instance.isLeftNavigationLightOn);
    }

    // ----------------------------------------------------------------------------------------------------------------


    // --- Engine -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(EngineStart engineStart) {
        LogEngine.instance.write("+ Wing.receive(" + engineStart.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineStart.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method onMethod = engineList.get(i).getClass().getDeclaredMethod("start");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(engineList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isEngineStarted = isOn;
                FlightRecorder.instance.insert("Wing", "Engine (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isEngineStarted): " + PrimaryFlightDisplay.instance.isEngineStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isEngineStarted: " + PrimaryFlightDisplay.instance.isEngineStarted);
    }

    @Subscribe
    public void receive(EngineShutdown engineShutdown) {
        LogEngine.instance.write("+ Wing.receive(" + engineShutdown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineShutdown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method offMethod = engineList.get(i).getClass().getDeclaredMethod("shutdown");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(engineList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isEngineStarted = isOn;
                FlightRecorder.instance.insert("Wing", "Engine (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isEngineStarted): " + PrimaryFlightDisplay.instance.isEngineStarted);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isEngineStarted: " + PrimaryFlightDisplay.instance.isEngineStarted);
    }

    @Subscribe
    public void receive(EngineIncreaseRPM engineIncreaseRPM) {
        LogEngine.instance.write("+ Wing.receive(" + engineIncreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineIncreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method onMethod = engineList.get(i).getClass().getDeclaredMethod("increaseRPM", int.class);
                LogEngine.instance.write("onMethod = " + onMethod);

                int rpm = (int) onMethod.invoke(engineList.get(i), engineIncreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Wing", "Engine (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmEngine): " + PrimaryFlightDisplay.instance.rpmEngine);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmEngine: " + PrimaryFlightDisplay.instance.rpmEngine);
    }

    @Subscribe
    public void receive(EngineDecreaseRPM engineDecreaseRPM) {
        LogEngine.instance.write("+ Wing.receive(" + engineDecreaseRPM.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + engineDecreaseRPM.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfEngine; i++) {
                Method offMethod = engineList.get(i).getClass().getDeclaredMethod("decreaseRPM", int.class);
                LogEngine.instance.write("offMethod = " + offMethod);

                int rpm = (int) offMethod.invoke(engineList.get(i), engineDecreaseRPM.getValue());
                LogEngine.instance.write("rpm = " + rpm);

                PrimaryFlightDisplay.instance.rpmAPU = rpm;
                FlightRecorder.instance.insert("Wing", "Engine (rpm): " + rpm);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (rpmEngine): " + PrimaryFlightDisplay.instance.rpmEngine);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "rpmEngine: " + PrimaryFlightDisplay.instance.rpmEngine);
    }

    // --- HydraulicPump -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(HydraulicPumpWingCompress hydraulicPumpWingCompress) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingCompress.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingCompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("compress");
                LogEngine.instance.write("onMethod = " + onMethod);

                int compressed = (int) onMethod.invoke(hydraulicPumpList.get(i));
                LogEngine.instance.write("compressed = " + compressed);
                FlightRecorder.instance.insert("Wing", "Gear (compressed): " + compressed);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(HydraulicPumpWingCompressAmount hydraulicPumpWingCompressAmount) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingCompressAmount.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingCompressAmount.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("compress", int.class);
                LogEngine.instance.write("onMethod = " + onMethod);

                int compressed = (int) onMethod.invoke(hydraulicPumpList.get(i), hydraulicPumpWingCompressAmount.getValue());
                LogEngine.instance.write("compressed = " + compressed);
                FlightRecorder.instance.insert("Wing", "Gear (compressed): " + compressed);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(HydraulicPumpWingDecompress hydraulicPumpWingDecompress) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingDecompress.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingDecompress.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method offMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("decompress");
                LogEngine.instance.write("offMethod = " + offMethod);

                int decompressed = (int) offMethod.invoke(hydraulicPumpList.get(i));
                LogEngine.instance.write("decompressed = " + decompressed);
                FlightRecorder.instance.insert("Wing", "Gear (decompressed): " + decompressed);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Subscribe
    public void receive(HydraulicPumpWingRefillOil hydraulicPumpWingRefillOil) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingRefillOil.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingRefillOil.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("refillOil");
                LogEngine.instance.write("onMethod = " + onMethod);

                int hydraulicPumpWingOilAmount = (int) onMethod.invoke(hydraulicPumpList.get(i));
                LogEngine.instance.write("hydraulicPumpWingOilAmount = " + hydraulicPumpWingOilAmount);

                PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount = hydraulicPumpWingOilAmount;
                FlightRecorder.instance.insert("Wing", "Gear (hydraulicPumpWingOilAmount): " + hydraulicPumpWingOilAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpWingOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpWingOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount);
    }

    @Subscribe
    public void receive(HydraulicPumpWingRefillOilAmount hydraulicPumpWingRefillOilAmount) {
        LogEngine.instance.write("+ Wing.receive(" + hydraulicPumpWingRefillOilAmount.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + hydraulicPumpWingRefillOilAmount.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfHydraulicPumpWing; i++) {
                Method onMethod = hydraulicPumpList.get(i).getClass().getDeclaredMethod("refillOil", int.class);
                LogEngine.instance.write("onMethod = " + onMethod);

                int hydraulicPumpWingOilAmount = (int) onMethod.invoke(hydraulicPumpList.get(i), hydraulicPumpWingRefillOilAmount.getValue());
                LogEngine.instance.write("hydraulicPumpWingOilAmount = " + hydraulicPumpWingOilAmount);

                PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount = hydraulicPumpWingOilAmount;
                FlightRecorder.instance.insert("Wing", "Gear (hydraulicPumpWingOilAmount): " + hydraulicPumpWingOilAmount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (hydraulicPumpWingOilAmount): " + PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "hydraulicPumpWingOilAmount: " + PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount);
    }

}