package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.anti_collision_light.AntiCollisionLightOff;
import event.anti_collision_light.AntiCollisionLightOn;
import event.cargo_compartment_light.CargoCompartmentLightDim;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.*;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.logo_light.LogoLightOff;
import event.logo_light.LogoLightOn;
import event.route_management.*;
import event.rudder.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Body extends Subscriber {

    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> rudderPortList;
    private ArrayList<Object> routeManagementPortList;
    private ArrayList<Object> antiCollisionLightPortList;
    private ArrayList<Object> cargoCompartmentLightPortList;
    private ArrayList<Object> landingLightPortList;
    private ArrayList<Object> logoLightPortList;
    private ArrayList<Object> costOptimizerPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        rudderPortList = new ArrayList<>();
        routeManagementPortList = new ArrayList<>();
        antiCollisionLightPortList = new ArrayList<>();
        cargoCompartmentLightPortList = new ArrayList<>();
        landingLightPortList = new ArrayList<>();
        logoLightPortList = new ArrayList<>();
        costOptimizerPortList = new ArrayList<>();
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

        for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
            antiCollisionLightPortList.add(AntiCollisionLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfCompartmentLight; i++) {
            cargoCompartmentLightPortList.add(CargoCompartmentLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
            landingLightPortList.add(LandingLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
            logoLightPortList.add(LogoLightFactory.build());
        }

        for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
            costOptimizerPortList.add(CostOptimizerFactory.build());
        }
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method method = weatherRadarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(weatherRadarPortList.get(i));
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
                Method method = weatherRadarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + method);

                boolean isOn = (boolean) method.invoke(weatherRadarPortList.get(i));
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

    // --- AntiCollisionLight -----------------------------------------------------------------------------------------

    @Subscribe
    public void receive(AntiCollisionLightOn antiCollisionLightOn) {
        LogEngine.instance.write("+ Body.receive(" + antiCollisionLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + antiCollisionLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
                Method method = antiCollisionLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(antiCollisionLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
                FlightRecorder.instance.insert("Body", "AntiCollisionLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAntiCollisionLightOn): " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAntiCollisionLightOn: " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
    }

    @Subscribe
    public void receive(AntiCollisionLightOff antiCollisionLightOff) {
        LogEngine.instance.write("+ Body.receive(" + antiCollisionLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + antiCollisionLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfAntiCollisionLight; i++) {
                Method method = antiCollisionLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(antiCollisionLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isAntiCollisionLightOn = isOn;
                FlightRecorder.instance.insert("Body", "AntiCollisionLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isAntiCollisionLightOn): " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isAntiCollisionLightOn: " + PrimaryFlightDisplay.instance.isAntiCollisionLightOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- CargoCompartmentLight --------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CargoCompartmentLightOn cargoCompartmentLightOn) {
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCompartmentLight; i++) {
                Method method = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightOff cargoCompartmentLightOff) {
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCompartmentLight; i++) {
                Method method = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightDim cargoCompartmentLightDim) {
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightDim.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- Rudder -----------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RudderNeutral rudderNeutral) {
        LogEngine.instance.write("+ Body.receive(" + rudderNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rudderNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRudder; i++) {
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i));
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
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("fullLeft");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i));
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
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("fullRight");
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i));
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
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("left", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i), rudderLeft.getDegree());
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
                Method method = rudderPortList.get(i).getClass().getDeclaredMethod("right", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int degree = (int) method.invoke(rudderPortList.get(i), rudderRight.getDegree());
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
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(routeManagementPortList.get(i));
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
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(routeManagementPortList.get(i));
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
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("add", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int number = (int) method.invoke(routeManagementPortList.get(i), routeManagementAdd.getCheckPoint());
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
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("remove", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int number = (int) method.invoke(routeManagementPortList.get(i), routeManagementRemove.getCheckPoint());
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
                Method method = routeManagementPortList.get(i).getClass().getDeclaredMethod("setCostIndex", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int costIndex = (int) method.invoke(routeManagementPortList.get(i), routeManagementSetCostIndex.getValue());
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

    // --- LandingLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LandingLightBodyOn landingLightBodyOn) {
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method method = landingLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(landingLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightBodyOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightBodyOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    @Subscribe
    public void receive(LandingLightBodyOff landingLightBodyOff) {
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method method = landingLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(landingLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightBodyOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightBodyOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LandingLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LogoLightOn logoLightOn) {
        LogEngine.instance.write("+ Body.receive(" + logoLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method method = logoLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    @Subscribe
    public void receive(LogoLightOff logoLightOff) {
        LogEngine.instance.write("+ Body.receive(" + logoLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method method = logoLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- CostOptimizer ----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CostOptimizerOn costOptimizerOn) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }

    @Subscribe
    public void receive(CostOptimizerOff costOptimizerOff) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("onMethod = " + method);

                boolean isOn = (boolean) method.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }

    @Subscribe
    public void receive(CostOptimizerAddCheckPoint costOptimizerAddCheckPoint) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerAddCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerAddCheckPoint.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("add", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int size = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerAddCheckPoint.getCheckPoint());
                LogEngine.instance.write("size = " + size);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = size;
                FlightRecorder.instance.insert("Body", "CostOptimizer (size): " + size);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerRemoveCheckPoint costOptimizerRemoveCheckPoint) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerRemoveCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerRemoveCheckPoint.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("remove", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int size = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerRemoveCheckPoint.getCheckPointIndex());
                LogEngine.instance.write("size = " + size);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = size;
                FlightRecorder.instance.insert("Body", "CostOptimizer (size): " + size);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerOptimize costOptimizerOptimize) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOptimize.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOptimize.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("optimize", CheckPoint.class);
                LogEngine.instance.write("onMethod = " + method);

                int size = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerOptimize.getCheckPointList());
                LogEngine.instance.write("size = " + size);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = size;
                FlightRecorder.instance.insert("Body", "CostOptimizer (size): " + size);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerValidate costOptimizerValidate) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerValidate.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerValidate.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method method = costOptimizerPortList.get(i).getClass().getDeclaredMethod("validate", int.class);
                LogEngine.instance.write("onMethod = " + method);

                int index = (int) method.invoke(costOptimizerPortList.get(i), costOptimizerValidate.getCostIndex());
                LogEngine.instance.write("index = " + index);

                PrimaryFlightDisplay.instance.indexCostOptimizer = index;
                FlightRecorder.instance.insert("Body", "CostOptimizer (index): " + index);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + PrimaryFlightDisplay.instance.indexCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + PrimaryFlightDisplay.instance.indexCostOptimizer);
    }

    // ----------------------------------------------------------------------------------------------------------------

}