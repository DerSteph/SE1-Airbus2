package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.droop_nose.DroopNoseDown;
import event.droop_nose.DroopNoseFullDown;
import event.droop_nose.DroopNoseNeutral;
import event.droop_nose.DroopNoseUp;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import event.turbulent_airflow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.turbulent_airflow_sensor.TurbulentAirFlowSensorWingMeasure;
import factory.DroopNoseFactory;
import factory.SlatFactory;
import factory.TurbulentAirFlowSensorFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;
    private ArrayList<Object> droopNosePortList;
    private ArrayList<Object> turbulentAirFlowSensorPortList;

    public Wing() {
        droopNosePortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
        turbulentAirFlowSensorPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
            droopNosePortList.add(DroopNoseFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfTurbulentAirFlowSensorWing; i++)
        {
            turbulentAirFlowSensorPortList.add(TurbulentAirFlowSensorFactory.build());
        }
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
    // --- TurbulentAirFlowSensor -----------------------------------------------------------------------------------------------
    public void receive(TurbulentAirFlowSensorWingMeasure turbulentAirFlowSensorWingMeasure) {
        FlightRecorder.instance.insert("Wing", "receive(" + turbulentAirFlowSensorWingMeasure.toString() + ")");
    }
    // ----------------------------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------------------------
    // --- DroopNose -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DroopNoseNeutral droopNoseNeutral) {
        LogEngine.instance.write("+ Wing.receive(" + droopNoseNeutral.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method neutralMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("neutralMethod = " + neutralMethod);

                int degree = (int) neutralMethod.invoke(droopNosePortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "DroopNose (neutral):" + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }

    @Subscribe
    public void receive(DroopNoseFullDown droopNoseFullDown) {
        LogEngine.instance.write("+ Wing.receive(" + droopNoseFullDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method fullDownMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("fullDownMethod = " + fullDownMethod);

                int degree = (int) fullDownMethod.invoke(droopNosePortList.get(i));
                LogEngine.instance.write("degreeDroopNose = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "DroopNose (degreeDroopNose): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }

    @Subscribe
    public void receive(DroopNoseDown droopNoseDown) {
        LogEngine.instance.write("+ Wing.receive(" + droopNoseDown.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method downMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("down");
                LogEngine.instance.write("downMethod = " + downMethod);

                int degree = (int) downMethod.invoke(droopNosePortList.get(i));
                LogEngine.instance.write("degreeDroopNose = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "DroopNose (degreeDroopNose): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }

    @Subscribe
    public void receive(DroopNoseUp droopNoseUp) {
        LogEngine.instance.write("+ Wing.receive(" + droopNoseUp.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + droopNoseUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDroopNose; i++) {
                Method upMethod = droopNosePortList.get(i).getClass().getDeclaredMethod("up");
                LogEngine.instance.write("upMethod = " + upMethod);

                int degree = (int) upMethod.invoke(droopNosePortList.get(i));
                LogEngine.instance.write("degreeDroopNose = " + degree);

                PrimaryFlightDisplay.instance.degreeDroopNose = degree;
                FlightRecorder.instance.insert("Wing", "DroopNose (degreeDroopNose): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeDroopNose): " + PrimaryFlightDisplay.instance.degreeDroopNose);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeDroopNose: " + PrimaryFlightDisplay.instance.degreeDroopNose);
    }
}