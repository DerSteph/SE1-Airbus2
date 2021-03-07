package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class GearFactory {

    public static Object build() {
        Object gearPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToGearJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, GearFactory.class.getClassLoader());
            Class gearClass = Class.forName("Gear", true, urlClassLoader);
            FlightRecorder.instance.insert("GearFactory", "gearClass: " + gearClass.hashCode());

            Object gearInstance = gearClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("GearFactory", "gearInstance: " + gearInstance.hashCode());

            gearPort = gearClass.getDeclaredField("port").get(gearInstance);
            FlightRecorder.instance.insert("GearFactory", "gearPort: " + gearPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gearPort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToGearJavaArchive, "Gear");
    }
    */


}