package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
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
    private ArrayList<Object> leftAileronPortList;
    private ArrayList<Object> rightAileronPortList;
    private ArrayList<Object> spoilerPortList;
    private ArrayList<Object> landingLightPortList;
    private ArrayList<Object> leftNavigationLightPortList;

    public Wing() {
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

    // ----------------------------------------------------------------------------------------------------------------

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
}