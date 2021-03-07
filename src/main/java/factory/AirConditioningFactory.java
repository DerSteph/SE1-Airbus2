package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class AirConditioningFactory {

    public static Object build() {
        Object airConditioningPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToAirConditioningJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, AirConditioningFactory.class.getClassLoader());
            Class airConditioningClass = Class.forName("AirConditioning", true, urlClassLoader);
            FlightRecorder.instance.insert("AirConditioningFactory", "airConditioningClass: " + airConditioningClass.hashCode());

            Object airConditioningInstance = airConditioningClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("AirConditioningFactory", "airConditioningInstance: " + airConditioningInstance.hashCode());

            airConditioningPort = airConditioningClass.getDeclaredField("port").get(airConditioningInstance);
            FlightRecorder.instance.insert("AirConditioningFactory", "airConditioningPort: " + airConditioningPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airConditioningPort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToAirConditioningJavaArchive, "AirConditioning");
    }
    */


}