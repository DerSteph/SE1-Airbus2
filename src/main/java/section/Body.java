package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.right_aileron.*;
import event.route_management.*;
import event.rudder.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.RouteManagementFactory;
import factory.RudderFactory;
import factory.SlatFactory;
import factory.WeatherRadarFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.io.ObjectInputFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> rudderPortList;
    private ArrayList<Object> routeManagementPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        rudderPortList = new ArrayList<>();
        routeManagementPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
            rudderPortList.add(RudderFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
            routeManagementPortList.add(RouteManagementFactory.build());
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

    // --- Rudder -----------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RudderNeutral rudderNeutral) {
        LogEngine.instance.write("+ Body.receive(" + rudderNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method onMethod = rudderPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderFullLeft rudderFullLeft) {
        LogEngine.instance.write("+ Body.receive(" + rudderFullLeft.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderFullLeft.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method onMethod = rudderPortList.get(i).getClass().getDeclaredMethod("fullLeft");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderFullRight rudderFullRight) {
        LogEngine.instance.write("+ Body.receive(" + rudderFullRight.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderFullRight.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method onMethod = rudderPortList.get(i).getClass().getDeclaredMethod("fullRight");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderLeft rudderLeft) {
        LogEngine.instance.write("+ Body.receive(" + rudderLeft.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderLeft.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method onMethod = rudderPortList.get(i).getClass().getDeclaredMethod("left");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    @Subscribe
    public void receive(RudderRight rudderRight) {
        LogEngine.instance.write("+ Body.receive(" + rudderRight.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderRight.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method onMethod = rudderPortList.get(i).getClass().getDeclaredMethod("right");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rudderPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRudder = degree;
                FlightRecorder.instance.insert("Body", "Rudder (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + PrimaryFlightDisplay.instance.degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + PrimaryFlightDisplay.instance.degreeRudder);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- RouteManagement --------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RouteManagementOn routeManagementOn) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method onMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementOff routeManagementOff) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method onMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementAdd routeManagementAdd) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementAdd.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementAdd.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method onMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("add");
                LogEngine.instance.write("onMethod = " + onMethod);

                int number = (int) onMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("size = " + number);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = number;
                FlightRecorder.instance.insert("Body", "RouteManagement (size): " + number);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementRemove routeManagementRemove) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementRemove.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementRemove.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method onMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("remove");
                LogEngine.instance.write("onMethod = " + onMethod);

                int number = (int) onMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("size = " + number);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = number;
                FlightRecorder.instance.insert("Body", "RouteManagement (size): " + number);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementSetCostIndex routeManagementSetCostIndex) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementSetCostIndex.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementSetCostIndex.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method onMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("setCostIndex");
                LogEngine.instance.write("onMethod = " + onMethod);

                int costIndex = (int) onMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("costIndex = " + costIndex);

                PrimaryFlightDisplay.instance.indexRouteManagement = costIndex;
                FlightRecorder.instance.insert("Body", "RouteManagement (costIndex): " + costIndex);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexRouteManagement): " + PrimaryFlightDisplay.instance.indexRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", ": " + PrimaryFlightDisplay.instance.indexRouteManagement);
    }

    // ----------------------------------------------------------------------------------------------------------------
}