package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class TailNavigationLightFactory {

    public static Object build() {
        Object tailNavigationLightPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToTailNavigationLight).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, TailNavigationLightFactory.class.getClassLoader());
            Class tailNavigationLightClass = Class.forName("TailNavigationLight", true, urlClassLoader);
            FlightRecorder.instance.insert("TailNavigationLightFactory", "tailNavigationLightClass: " + tailNavigationLightClass.hashCode());

            Object tailNavigationInstance = tailNavigationLightClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("TailNavigationLightFactory", "tailNavigationLightInstance: " + tailNavigationInstance.hashCode());

            tailNavigationLightPort = tailNavigationLightClass.getDeclaredField("port").get(tailNavigationInstance);
            FlightRecorder.instance.insert("TailNavigationLightFactory", "tailNavigationLightPort: " + tailNavigationLightPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tailNavigationLightPort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToAirConditioningJavaArchive, "AirConditioning");
    }
    */


}
