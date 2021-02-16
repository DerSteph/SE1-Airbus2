package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.left_aileron.*;
import event.right_aileron.*;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import event.weather_radar.WeatherRadarOn;
import factory.LeftAileronFactory;
import factory.RightAileronFactory;
import factory.SlatFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;
    private ArrayList<Object> leftAileronPortList;
    private ArrayList<Object> rightAileronPortList;

    public Wing() {
        slatPortList = new ArrayList<>();
        leftAileronPortList = new ArrayList<>();
        rightAileronPortList = new ArrayList<>();
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
    }

    // --- Slat -------------------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatNeutral slatNeutral) {
        LogEngine.instance.write("+ Body.receive(" + slatNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + slatNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method onMethod = slatPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Body", "Slat (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + slatFullDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + slatFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method onMethod = slatPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Body", "Slat (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + slatUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + slatUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method onMethod = slatPortList.get(i).getClass().getDeclaredMethod("up");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Body", "Slat (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + slatDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + slatDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
                Method onMethod = slatPortList.get(i).getClass().getDeclaredMethod("down");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(slatPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeSlat = degree;
                FlightRecorder.instance.insert("Body", "Slat (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + leftAileronNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + leftAileronNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method onMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Body", "LeftAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + leftAileronFullUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + leftAileronFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method onMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Body", "LeftAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + leftAileronFullDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + leftAileronFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method onMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Body", "LeftAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + leftAileronUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + leftAileronUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method onMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("up");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Body", "LeftAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + leftAileronDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + leftAileronDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
                Method onMethod = leftAileronPortList.get(i).getClass().getDeclaredMethod("down");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(leftAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeLeftAileron = degree;
                FlightRecorder.instance.insert("Body", "LeftAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + rightAileronNeutral.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rightAileronNeutral.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method onMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("neutral");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Body", "RightAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + rightAileronFullUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rightAileronFullUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method onMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("fullUp");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Body", "RightAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + rightAileronFullDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rightAileronFullDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method onMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("fullDown");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Body", "RightAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + rightAileronUp.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rightAileronUp.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method onMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("up");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Body", "RightAileron (degree): " + degree);

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
        LogEngine.instance.write("+ Body.receive(" + rightAileronDown.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + rightAileronDown.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
                Method onMethod = rightAileronPortList.get(i).getClass().getDeclaredMethod("down");
                LogEngine.instance.write("onMethod = " + onMethod);

                int degree = (int) onMethod.invoke(rightAileronPortList.get(i));
                LogEngine.instance.write("degree = " + degree);

                PrimaryFlightDisplay.instance.degreeRightAileron = degree;
                FlightRecorder.instance.insert("Body", "RightAileron (degree): " + degree);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (degreeRightAileron): " + PrimaryFlightDisplay.instance.degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + PrimaryFlightDisplay.instance.degreeRightAileron);
    }

    // ----------------------------------------------------------------------------------------------------------------

}