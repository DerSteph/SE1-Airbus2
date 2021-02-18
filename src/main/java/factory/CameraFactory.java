package factory;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import configuration.Configuration;

public class CameraFactory extends Factory {

//    public static Object build() {
//        Object slatPort = null;
//
//        try {
//            URL[] urls = {new File(Configuration.instance.pathToSlatJavaArchive).toURI().toURL()};
//            URLClassLoader urlClassLoader = new URLClassLoader(urls, SlatFactory.class.getClassLoader());
//            Class slatClass = Class.forName("Slat", true, urlClassLoader);
//            FlightRecorder.instance.insert("slatFactory", "slatClass: " + slatClass.hashCode());
//
//            Object slatInstance = slatClass.getMethod("getInstance").invoke(null);
//            FlightRecorder.instance.insert("SlatFactory", "slatInstance: " + slatInstance.hashCode());
//
//            slatPort = slatClass.getDeclaredField("port").get(slatInstance);
//            FlightRecorder.instance.insert("SlatFactory", "slatPort: " + slatPort.hashCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return slatPort;
//    }

    public static Object build() {
        return Factory.build(Configuration.instance.pathToCameraArchive, "Camera");
    }
}
