package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class RightNavigationLightFactory {

    public static Object build() {
        Object rightNavigationLightPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToRightNavigationLight).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, RightNavigationLightFactory.class.getClassLoader());
            Class rightNavigationLightClass = Class.forName("RightNavigationLight", true, urlClassLoader);
            FlightRecorder.instance.insert("RightNavigationLightFactory", "rightNavigationLightClass: " + rightNavigationLightClass.hashCode());

            Object rightNavigationInstance = rightNavigationLightClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("RightNavigationLightFactory", "rightNavigationLightInstance: " + rightNavigationInstance.hashCode());

            rightNavigationLightPort = rightNavigationLightClass.getDeclaredField("port").get(rightNavigationInstance);
            FlightRecorder.instance.insert("RightNavigationLightFactory", "rightNavigationLightPort: " + rightNavigationLightPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rightNavigationLightPort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToAirConditioningJavaArchive, "AirConditioning");
    }
    */


}
