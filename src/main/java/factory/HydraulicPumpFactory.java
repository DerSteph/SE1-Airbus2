package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class HydraulicPumpFactory {

    public static Object build() {
        Object hydraulicPumpPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToHydraulicPumpJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, HydraulicPumpFactory.class.getClassLoader());
            Class hydraulicPumpClass = Class.forName("HydraulicPump", true, urlClassLoader);
            FlightRecorder.instance.insert("HydraulicPumpFactory", "hydraulicPumpClass: " + hydraulicPumpClass.hashCode());

            Object hydraulicPumpInstance = hydraulicPumpClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("HydraulicPumpFactory", "hydraulicPumpInstance: " + hydraulicPumpInstance.hashCode());

            hydraulicPumpPort = hydraulicPumpClass.getDeclaredField("port").get(hydraulicPumpInstance);
            FlightRecorder.instance.insert("HydraulicPumpFactory", "hydraulicPumpPort: " + hydraulicPumpPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hydraulicPumpPort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToHydraulicPumpJavaArchive, "HydraulicPump");
    }
    */


}