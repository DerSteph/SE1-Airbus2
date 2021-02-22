package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class APUFactory {

    public static Object build() {
        Object apuPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToAPUJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, APUFactory.class.getClassLoader());
            Class apuClass = Class.forName("APU", true, urlClassLoader);
            FlightRecorder.instance.insert("APUFactory", "apuClass: " + apuClass.hashCode());

            Object apuInstance = apuClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("APUFactory", "apuInstance: " + apuInstance.hashCode());

            apuPort = apuClass.getDeclaredField("port").get(apuInstance);
            FlightRecorder.instance.insert("APUFactory", "apuPort: " + apuPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return apuPort;
    }
    /*
    public static Object build() {
        return Factory.build(Configuration.instance.pathToAPUJavaArchive, "APU");
    }
    */


}