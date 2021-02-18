package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class KitchenFactory {

    public static Object build() {
        Object kitchenPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToKitchenJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, KitchenFactory.class.getClassLoader());
            Class kitchenClass = Class.forName("Kitchen", true, urlClassLoader);
            FlightRecorder.instance.insert("KitchenFactory", "kitchenClass: " + kitchenClass.hashCode());

            Object kitchenInstance = kitchenClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("KitchenFactory", "kitchenInstance: " + kitchenInstance.hashCode());

            kitchenPort = kitchenClass.getDeclaredField("port").get(kitchenInstance);
            FlightRecorder.instance.insert("KitchenFactory", "kitchenPort: " + kitchenPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kitchenPort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToKitchenJavaArchive, "Kitchen");
    }
    */


}