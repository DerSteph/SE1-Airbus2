package factory;

import configuration.Configuration;

public class StallingSensorFactory
{
//        public static Object build() {
//        Object stallingSensorPort = null;
//
//        try {
//            URL[] urls = {new File(Configuration.instance.pathToStallingSensorJavaArchive).toURI().toURL()};
//            URLClassLoader urlClassLoader = new URLClassLoader(urls, StallingSensorFactory.class.getClassLoader());
//            Class stallingSensorClass = Class.forName("StallingSensor", true, urlClassLoader);
//            FlightRecorder.instance.insert("StallingSensorFactory", "stallingSensorClass: " + stallingSensorClass.hashCode());
//
//            Object stallingSensorInstance = stallingSensorClass.getMethod("getInstance").invoke(null);
//            FlightRecorder.instance.insert("StallingSensorFactory", "stallingSensorInstance: " + stallingSensorInstance.hashCode());
//
//            stallingSensorPort = stallingSensorClass.getDeclaredField("port").get(stallingSensorInstance);
//            FlightRecorder.instance.insert("StallingSensorFactory", "stallingSensorPort: " + stallingSensorPort.hashCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return stallingSensorPort;
//    }

    public static Object build() {
        return Factory.build(Configuration.instance.pathToStallingSensorJavaArchive, "StallingSensor");
    }
}
