package factory;

import configuration.Configuration;

public class ShockSensorFactory extends Factory
{
//        public static Object build() {
//        Object shockSensorPort = null;
//
//        try {
//            URL[] urls = {new File(Configuration.instance.pathToShockSensorJavaArchive).toURI().toURL()};
//            URLClassLoader urlClassLoader = new URLClassLoader(urls, ShockSensorFactory.class.getClassLoader());
//            Class shockSensorClass = Class.forName("ShockSensor", true, urlClassLoader);
//            FlightRecorder.instance.insert("ShockSensorFactory", "shockSensorClass: " + shockSensorClass.hashCode());
//
//            Object shockSensorInstance = shockSensorClass.getMethod("getInstance").invoke(null);
//            FlightRecorder.instance.insert("ShockSensorFactory", "shockSensorInstance: " + shockSensorInstance.hashCode());
//
//            shockSensorPort = shockSensorClass.getDeclaredField("port").get(shockSensorInstance);
//            FlightRecorder.instance.insert("ShockSensorFactory", "shockSensorPort: " + shockSensorPort.hashCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return shockSensorPort;
//    }

    public static Object build() {
        return Factory.build(Configuration.instance.pathToShockSensorJavaArchive, "ShockSensor");
    }

}
