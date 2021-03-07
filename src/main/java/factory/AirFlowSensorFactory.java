package factory;

import configuration.Configuration;

public class AirFlowSensorFactory
{
//        public static Object build() {
//        Object airFlowSensorPort = null;
//
//        try {
//            URL[] urls = {new File(Configuration.instance.pathToAirFlowSensorJavaArchive).toURI().toURL()};
//            URLClassLoader urlClassLoader = new URLClassLoader(urls, AirFlowSensorFactory.class.getClassLoader());
//            Class airFlowSensorClass = Class.forName("AirFlowSensor", true, urlClassLoader);
//            FlightRecorder.instance.insert("AirFlowSensorFactory", "airFlowSensorClass: " + airFlowSensorClass.hashCode());
//
//            Object airFlowSensorInstance = airFlowSensorClass.getMethod("getInstance").invoke(null);
//            FlightRecorder.instance.insert("AirFlowSensorFactory", "airFlowSensorInstance: " + airFlowSensorInstance.hashCode());
//
//            airFlowSensorPort = airFlowSensorClass.getDeclaredField("port").get(airFlowSensorInstance);
//            FlightRecorder.instance.insert("AirFlowSensorFactory", "airFlowSensorPort: " + airFlowSensorPort.hashCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return airFlowSensorPort;
//    }

    public static Object build() {
        return Factory.build(Configuration.instance.pathToAirFlowSensorJavaArchive, "AirFlowSensor");
    }
}
