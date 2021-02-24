package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.tail_navigation_light.TailNavigationLightOff;
import event.tail_navigation_light.TailNavigationLightOn;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.SlatFactory;
import factory.TailNavigationLightFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {

    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> slatPortList;
    // Add a new list for each service...
    private ArrayList<Object> tailNavigationLightList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        slatPortList = new ArrayList<>();
        // Add a new list for each service...
        tailNavigationLightList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            slatPortList .add(SlatFactory.build());
        }

        // Add a new iteration for each service...

        //tail_navigation_light
        for (int i = 0; i < Configuration.instance.numberOfTailNavigationLight; i++) {
            tailNavigationLightList .add(TailNavigationLightFactory.build());
        }

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

    // ---  TailNavigationLight ---------------------------------------------------------------------------------------

    @Subscribe
    public void receive(TailNavigationLightOn tailNavigationLightOn){
        LogEngine.instance.write("+ Body.receive(" + tailNavigationLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tailNavigationLightOn + ")");
        try {
            for (int i = 0; i < Configuration.instance.numberOfTailNavigationLight; i++) {
                Method onMethod = tailNavigationLightList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(tailNavigationLightList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTailNavigationLightOn= isOn;
                FlightRecorder.instance.insert("Body", "TailNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTailNavigationLightOn): " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTailNavigationLightOn: " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
    }

    @Subscribe
    public void receive(TailNavigationLightOff tailNavigationLightOff){
        LogEngine.instance.write("+ Body.receive(" + tailNavigationLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + tailNavigationLightOff + ")");
        try {
            for (int i = 0; i < Configuration.instance.numberOfTailNavigationLight; i++) {
                Method offMethod = tailNavigationLightList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(tailNavigationLightList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isTailNavigationLightOn= isOn;
                FlightRecorder.instance.insert("Body", "TailNavigationLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isTailNavigationLightOn): " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isTailNavigationLightOn: " + PrimaryFlightDisplay.instance.isTailNavigationLightOn);
    }
    // ----------------------------------------------------------------------------------------------------------------


}