package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class EngineFactory {

    public static Object build() {
        Object enginePort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToEngineJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, EngineFactory.class.getClassLoader());
            Class engineClass = Class.forName("Engine", true, urlClassLoader);
            FlightRecorder.instance.insert("EngineFactory", "engineClass: " + engineClass.hashCode());

            Object engineInstance = engineClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("EngineFactory", "engineInstance: " + engineInstance.hashCode());

            enginePort = engineClass.getDeclaredField("port").get(engineInstance);
            FlightRecorder.instance.insert("EngineFactory", "enginePort: " + enginePort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return enginePort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToEngineJavaArchive, "Engine");
    }
    */


}