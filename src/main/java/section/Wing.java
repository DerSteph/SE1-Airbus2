package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.exhaust_gas_temperature_sensor.ExhaustGasTemperatureSensorMeasure;
import event.fuel_flow_sensor.FuelFlowSensorMeasure;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.ExhaustGasTemperatureSensorFactory;
import factory.FuelFlowSensorFactory;
import factory.RightNavigationLightFactory;
import factory.SlatFactory;
import logging.LogEngine;
import org.hsqldb.persist.Log;
import recorder.FlightRecorder;


import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;
    // Add a new list for each service...
    private ArrayList<Object> rightNavigationLightPortList;
    private ArrayList<Object> exhaustGasTemperatureSensorPortList;
    private ArrayList<Object> fuelFlowSensorPortList;

    public Wing() {
        slatPortList = new ArrayList<>();
        // Add a new list for each service...
        rightNavigationLightPortList = new ArrayList<>();
        exhaustGasTemperatureSensorPortList = new ArrayList<>();
        fuelFlowSensorPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList.add(SlatFactory.build());
        }

        // Add a new iteration for each service...
        for (int i = 0; i < Configuration.instance.numberOfRightNavigationLight; i++) {
            rightNavigationLightPortList.add(RightNavigationLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfExhaustGasTemperatureSensor; i++){
            exhaustGasTemperatureSensorPortList.add(ExhaustGasTemperatureSensorFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfFuelFlowSensor; i++){
            fuelFlowSensorPortList.add(FuelFlowSensorFactory.build());
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

    // --- RightNavigationLight ---------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RightNavigationLightOn rightNavigationLightOn) {
        LogEngine.instance.write("+ Wing.receive(" + rightNavigationLightOn.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightNavigationLightOn + ")");
        try {
            for (int i = 0; i < Configuration.instance.numberOfRightNavigationLight; i++) {
                Method onMethod = rightNavigationLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(rightNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRightNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Wing", "RightNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRightNavigationLightOn): " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRightNavigationLightOn: " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
    }

    @Subscribe
    public void receive(RightNavigationLightOff rightNavigationLightOff) {
        LogEngine.instance.write("+ Wing.receive(" + rightNavigationLightOff.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + rightNavigationLightOff + ")");
        try {
            for (int i = 0; i < Configuration.instance.numberOfRightNavigationLight; i++) {
                Method offMethod = rightNavigationLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(rightNavigationLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRightNavigationLightOn = isOn;
                FlightRecorder.instance.insert("Wing", "RightNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (isRightNavigationLightOn): " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRightNavigationLightOn: " + PrimaryFlightDisplay.instance.isRightNavigationLightOn);
    }
    // ---ExhaustGasTemperatureSensor-------------------------------------------------------------------------------------------------------------
        @Subscribe
        public void receive(ExhaustGasTemperatureSensorMeasure exhaustGasTemperatureSensorMeasure){
            LogEngine.instance.write("+ Wing.receive(" + exhaustGasTemperatureSensorMeasure.toString() + ")");
            FlightRecorder.instance.insert("Wing", "receive(" + exhaustGasTemperatureSensorMeasure.toString() + ")");

            try {
                for(int i = 0; i < Configuration.instance.numberOfExhaustGasTemperatureSensor; i++){
                    Method measureMethod = exhaustGasTemperatureSensorPortList.get(i).getClass().getDeclaredMethod("measure");
                    LogEngine.instance.write("measureMethod = " + measureMethod);

                    int measure = (int) measureMethod.invoke(exhaustGasTemperatureSensorPortList.get(i));
                    LogEngine.instance.write("measure = " + measureMethod);

                    PrimaryFlightDisplay.instance.exhaustGasTemperature = measure;
                    FlightRecorder.instance.insert("Wing", "ExhaustGasTemperatureSensor (Measure): " + measure);

                    LogEngine.instance.write("+");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            LogEngine.instance.write("PrimaryFlightDisplay (ExhaustGasTemperatureSensor): " + PrimaryFlightDisplay.instance.exhaustGasTemperature);
            FlightRecorder.instance.insert("PrimaryFlightDisplay", "ExhaustGasTemperatureSensor: " + PrimaryFlightDisplay.instance.exhaustGasTemperature);

        }
    //---FuelFlowSensor------------------------------------------------------------------------------------------------------------------------------------------
    @Subscribe
    public void receive(FuelFlowSensorMeasure fuelFlowSensorMeasure){
        LogEngine.instance.write("+ Wing.receive(" + fuelFlowSensorMeasure.toString() + ")");
        FlightRecorder.instance.insert("Wing", "receive(" + fuelFlowSensorMeasure.toString() + ")");

        try {
            for(int i = 0; i < Configuration.instance.numberOfFuelFlowSensor; i++){
                Method measureMethod = fuelFlowSensorPortList.get(i).getClass().getDeclaredMethod("measure");
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measure = (int) measureMethod.invoke(fuelFlowSensorPortList.get(i));
                LogEngine.instance.write("measure = " + measureMethod);

                PrimaryFlightDisplay.instance.fuelFlow = measure;
                FlightRecorder.instance.insert("Wing", "FuelFlowSensor (Measure): " + measure);

                LogEngine.instance.write("+");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (FuelFlowSensor): " + PrimaryFlightDisplay.instance.fuelFlow);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "FuelFlowSensor: " + PrimaryFlightDisplay.instance.exhaustGasTemperature);

    }
}