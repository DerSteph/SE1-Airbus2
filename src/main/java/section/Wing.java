package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.right_navigation_light.RightNavigationLightOff;
import event.right_navigation_light.RightNavigationLightOn;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import factory.RightNavigationLightFactory;
import factory.SlatFactory;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Wing extends Subscriber {

    private ArrayList<Object> slatPortList;
    // Add a new list for each service...
    private ArrayList<Object> rightNavigationLightPortList;

    public Wing() {
        slatPortList = new ArrayList<>();
        // Add a new list for each service...
        rightNavigationLightPortList = new ArrayList<>();
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
    // ----------------------------------------------------------------------------------------------------------------


}