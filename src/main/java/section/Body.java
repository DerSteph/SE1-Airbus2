package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.battery.BatteryCharge;
import event.battery.BatteryDischarge;
import event.deicing_system.DeIcingSystemActivate;
import event.deicing_system.DeIcingSystemDeIce;
import event.deicing_system.DeIcingSystemDeactivate;
import event.deicing_system.DeIcingSystemRefill;
import event.pitot_tube.PitotTubeClean;
import event.pitot_tube.PitotTubeMeasureStaticPressure;
import event.pitot_tube.PitotTubeMeasureTotalPressure;
import event.pitot_tube.PitotTubeMeasureVelocity;
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
    private ArrayList<Object> batteryPortList;
    private ArrayList<Object> deIcingSystemPortList;
    private ArrayList<Object> pitotTubePortList;
    // Add a new list for each service...

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
        batteryPortList = new ArrayList<>();
        deIcingSystemPortList = new ArrayList<>();
        pitotTubePortList = new ArrayList<>();
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
        FlightRecorder.instance.insert("Body", "receive("+ batteryCharge.toString() + ")");
    }

    @Subscribe
    public void receive(BatteryDischarge batteryDischarge){
        FlightRecorder.instance.insert("Body", "receive("+ batteryDischarge.toString() + ")");
    }

    // --- DeIcingSystem -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(DeIcingSystemActivate deIcingSystemActivate){
        FlightRecorder.instance.insert("Body", "receive("+ deIcingSystemActivate.toString() + ")");
    }

    @Subscribe
    public void receive(DeIcingSystemDeactivate deIcingSystemDeactivate){
        FlightRecorder.instance.insert("Body", "receive("+ deIcingSystemDeactivate.toString() + ")");
    }

    @Subscribe
    public void receive(DeIcingSystemDeIce deIcingSystemDeIce){
        FlightRecorder.instance.insert("Body", "receive("+ deIcingSystemDeIce.toString() + ")");
    }

    @Subscribe
    public void receive(DeIcingSystemRefill deIcingSystemRefill){
        FlightRecorder.instance.insert("Body", "receive("+ deIcingSystemRefill.toString() + ")");
    }

    // --- PitotTube -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(PitotTubeClean pitotTubeClean){
        FlightRecorder.instance.insert("Body", "receive("+ pitotTubeClean.toString() + ")");
    }

    @Subscribe
    public void receive(PitotTubeMeasureStaticPressure pitotTubeMeasureStaticPressure){
        FlightRecorder.instance.insert("Body", "receive("+ pitotTubeMeasureStaticPressure.toString() + ")");
    }

    @Subscribe
    public void receive(PitotTubeMeasureTotalPressure pitotTubeMeasureTotalPressure){
        FlightRecorder.instance.insert("Body", "receive("+ pitotTubeMeasureTotalPressure.toString() + ")");
    }

    @Subscribe
    public void receive(PitotTubeMeasureVelocity pitotTubeMeasureVelocity){
        FlightRecorder.instance.insert("Body", "receive("+ pitotTubeMeasureVelocity.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------
}