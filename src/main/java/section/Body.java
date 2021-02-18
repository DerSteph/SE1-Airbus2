package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;

import event.air_flow_sensor.*;
import event.battery.*;
import event.deicing_system.*;
import event.pitot_tube.*;
import event.radar_altimeter.*;
import event.shock_sensor.*;
import event.stalling_sensor.*;
import event.temperature_sensor.*;

import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {

    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> slatPortList;

    private ArrayList<Object> shockSensorPortList;
    private ArrayList<Object> stallingSensorPortList;
    private ArrayList<Object> temperatureSensorPortList;
    private ArrayList<Object> airFlowSensorPortList;

    private ArrayList<Object> batteryPortList;
    private ArrayList<Object> deIcingSystemPortList;
    private ArrayList<Object> pitotTubePortList;
    private ArrayList<Object> radarAltimeterPortList;

    // Add a new list for each service...

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();

        shockSensorPortList = new ArrayList<>();
        stallingSensorPortList = new ArrayList<>();
        temperatureSensorPortList = new ArrayList<>();
        airFlowSensorPortList = new ArrayList<>();

        batteryPortList = new ArrayList<>();
        deIcingSystemPortList = new ArrayList<>();
        pitotTubePortList = new ArrayList<>();
        radarAltimeterPortList = new ArrayList<>();

        // Add a new list for each service...
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfShockSensorBody; i++) {
            shockSensorPortList.add(ShockSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfStallingSensorBody; i++) {
            stallingSensorPortList.add(StallingSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorBody; i++) {
            temperatureSensorPortList.add(TemperatureSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorBody; i++) {
            airFlowSensorPortList.add(AirFlowSensorFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfBattery; i++)
        {
            batteryPortList.add(BatteryFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++)
        {
            deIcingSystemPortList.add(DeIcingSystemFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfPitotTube; i++)
        {
            pitotTubePortList.add(PitotTubeFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRadarAltimeter; i++)
        {
            radarAltimeterPortList.add(RadarAltimeterFactory.build());
        }


        // Add a new iteration for each service...
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method onMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarOff weatherRadarOff) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method offMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarScan weatherRadarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarScan.toString() + ")");
    }

    // --- Battery -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(BatteryCharge batteryCharge){
        LogEngine.instance.write("+ Body.receive(" + batteryCharge.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + batteryCharge.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBattery; i++) {
                Method chargeMethod = batteryPortList.get(i).getClass().getDeclaredMethod("charge");
                LogEngine.instance.write("chargeMethod = " + chargeMethod);

                int charge = (int) chargeMethod.invoke(batteryPortList.get(i));
                LogEngine.instance.write("charge = " + charge);

                PrimaryFlightDisplay.instance.percentageBattery = charge;
                FlightRecorder.instance.insert("Body", "Battery (charge): " + charge);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (BatteryCharge): " + PrimaryFlightDisplay.instance.percentageBattery);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "BatteryCharge: " + PrimaryFlightDisplay.instance.percentageBattery);
    }

    @Subscribe
    public void receive(BatteryDischarge batteryDischarge){
        LogEngine.instance.write("+ Body.receive(" + batteryDischarge.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + batteryDischarge.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfBattery; i++) {
                Method dischargeMethod = batteryPortList.get(i).getClass().getDeclaredMethod("charge");
                LogEngine.instance.write("dischargeMethod = " + dischargeMethod);

                int discharge =(int) dischargeMethod.invoke(batteryPortList.get(i));
                LogEngine.instance.write("discharge = " + discharge);

                PrimaryFlightDisplay.instance.percentageBattery = discharge;
                FlightRecorder.instance.insert("Body", "Battery (discharge): " + discharge);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (BatteryDischarge): " + PrimaryFlightDisplay.instance.percentageBattery);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "BatteryDischarge: " + PrimaryFlightDisplay.instance.percentageBattery);
    }

    // --- DeIcingSystem -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DeIcingSystemActivate deIcingSystemActivate){
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemActivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemActivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method activateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("activate");
                LogEngine.instance.write("activateMethod = " + activateMethod);

                boolean active = (boolean) activateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("active = " + active);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = active;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (active): " + active);

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
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemDeactivate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemDeactivate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deactivateMethod = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deactivate");
                LogEngine.instance.write("deactivateMethod = " + deactivateMethod);

                boolean active = (boolean) deactivateMethod.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("active = " + active);

                PrimaryFlightDisplay.instance.isDeIcingSystemActivated = active;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (active): " + active);

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
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemDeIce.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemDeIce.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method deIce = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("deIce", int.class);
                LogEngine.instance.write("deIceMethod = " + deIce);

                int amount = (int) deIce.invoke(deIcingSystemPortList.get(i), deIcingSystemDeIce.amount);
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (amount): " + amount);

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
        LogEngine.instance.write("+ Body.receive(" + deIcingSystemRefill.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + deIcingSystemRefill.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfDeIcingSystemBody; i++) {
                Method refill = deIcingSystemPortList.get(i).getClass().getDeclaredMethod("refill");
                LogEngine.instance.write("refillMethod = " + refill);

                int amount = (int) refill.invoke(deIcingSystemPortList.get(i));
                LogEngine.instance.write("amount = " + amount);

                PrimaryFlightDisplay.instance.amountDeIcingSystem = amount;
                FlightRecorder.instance.insert("Body", "DeIcingSystem (amount): " + amount);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (DeIcingSystemRefill): " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "DeIcingSystemRefill: " + PrimaryFlightDisplay.instance.amountDeIcingSystem);
    }

    // --- PitotTube -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(PitotTubeClean pitotTubeClean){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PitotTubeMeasureStaticPressure pitotTubeMeasureStaticPressure){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PitotTubeMeasureTotalPressure pitotTubeMeasureTotalPressure){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(PitotTubeMeasureVelocity pitotTubeMeasureVelocity){
        throw new RuntimeException("Not implemented yet.");
    }

    // --- RadarAltimeter -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RadarAltimeterOn radarAltimeterOn){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterOff radarAltimeterOff){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterSend radarAltimeterSend){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterReceive radarAltimeterReceive){
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(RadarAltimeterMeasureAltitude radarAltimeterMeasureAltitude){
        throw new RuntimeException("Not implemented yet.");
    }
    // ----------------------------------------------------------------------------------------------------------------

    // --- ShockSensor ------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(ShockSensorBodyCalibrate shockSensorBodyCalibrate) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Subscribe
    public void receive(ShockSensorBodyMeasure shockSensorBodyMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- StallingSensor ---------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(StallingSensorBodyMeasure stallingSensorBodyMeasure) {
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- TemperatureSensor ------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TemperatureSensorBodyMeasure temperatureSensorBodyMeasure) {
        LogEngine.instance.write("+ Body.receive(" + temperatureSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + temperatureSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfTemperatureSensorBody; i++) {
                Method measureMethod = temperatureSensorPortList.get(i).getClass().getDeclaredMethod("measure");
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measure = (int) measureMethod.invoke(temperatureSensorPortList.get(i));
                LogEngine.instance.write("measure = " + measure);

                PrimaryFlightDisplay.instance.temperatureBody = measure;
                FlightRecorder.instance.insert("Body", "TemperatureSensor (Measure): " + measure);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (TemperatureSensor): " + PrimaryFlightDisplay.instance.temperatureBody);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "TemperatureSensor: " + PrimaryFlightDisplay.instance.temperatureBody);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- AirFlowSensor ----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AirFlowSensorBodyMeasure airFlowSensorBodyMeasure) {
        LogEngine.instance.write("+ Body.receive(" + airFlowSensorBodyMeasure.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + airFlowSensorBodyMeasure.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAirFlowSensorBody; i++) {
                Method measureMethod = airFlowSensorPortList.get(i).getClass().getDeclaredMethod("measure", String.class);
                LogEngine.instance.write("measureMethod = " + measureMethod);

                int measure = (int) measureMethod.invoke(airFlowSensorPortList.get(i), "");
                LogEngine.instance.write("measure = " + measure);

                PrimaryFlightDisplay.instance.airPressure = measure;
                FlightRecorder.instance.insert("Body", "AirFLowSensor (Measure): " + measure);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (AirFLowSensor): " + PrimaryFlightDisplay.instance.airPressure);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "AirFLowSensor: " + PrimaryFlightDisplay.instance.airPressure);
    }

    // ----------------------------------------------------------------------------------------------------------------
}