package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;

import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.SlatFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import factory.*;
import event.shock_sensor.*;
import event.stalling_sensor.*;
import event.temperature_sensor.*;

import java.io.ObjectInputFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {

    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> slatPortList;
    private ArrayList<Object> shockSensorPortList;
    private ArrayList<Object> stallingSensorPortList;
    // Add a new list for each service...

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
        shockSensorPortList = new ArrayList<>();
        stallingSensorPortList = new ArrayList<>();
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
        throw new RuntimeException("Not implemented yet.");
    }

    // ----------------------------------------------------------------------------------------------------------------
}