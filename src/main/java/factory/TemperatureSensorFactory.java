package factory;

import configuration.Configuration;

public class TemperatureSensorFactory
{
//        public static Object build() {
//        Object temperatureSensorPort = null;
//
//        try {
//            URL[] urls = {new File(Configuration.instance.pathToTemperatureSensorJavaArchive).toURI().toURL()};
//            URLClassLoader urlClassLoader = new URLClassLoader(urls, TemperatureSensorFactory.class.getClassLoader());
//            Class temperatureSensorClass = Class.forName("TemperatureSensor", true, urlClassLoader);
//            FlightRecorder.instance.insert("TemperatureSensorFactory", "temperatureSensorClass: " + temperatureSensorClass.hashCode());
//
//            Object temperatureSensorInstance = temperatureSensorClass.getMethod("getInstance").invoke(null);
//            FlightRecorder.instance.insert("TemperatureSensorFactory", "temperatureSensorInstance: " + temperatureSensorInstance.hashCode());
//
//            temperatureSensorPort = temperatureSensorClass.getDeclaredField("port").get(temperatureSensorInstance);
//            FlightRecorder.instance.insert("TemperatureSensorFactory", "temperatureSensorPort: " + temperatureSensorPort.hashCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return temperatureSensorPort;
//    }

    public static Object build() {
        return Factory.build(Configuration.instance.pathToTemperatureSensorJavaArchive, "TemperatureSensor");
    }    
}
