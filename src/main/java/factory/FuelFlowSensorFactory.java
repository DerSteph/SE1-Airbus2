package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class FuelFlowSensorFactory {
//    public static Object build() {
//        Object fuelFlowSensorPort = null;
//
//        try{
//            URL[] urls = {new File(Configuration.instance.pathToFuelFlowSensor).toURI().toURL()};
//            URLClassLoader urlClassLoader = new URLClassLoader(urls, FuelFlowSensorFactory.class.getClassLoader());
//            Class fuelFlowSensorClass = Class.forName("FuelFlowSensor", true, urlClassLoader);
//            FlightRecorder.instance.insert("FuelFlowSensorFactory", "fuelFlowSensor: " + fuelFlowSensorClass.hashCode());
//
//            Object fuelFlowSensorInstance = fuelFlowSensorClass.getMethod("getInstance").invoke(null);
//            FlightRecorder.instance.insert("FuelFlowSensorFactory", "fuelFlowSensorInstance: " + fuelFlowSensorInstance.hashCode());
//
//            fuelFlowSensorPort = fuelFlowSensorClass.getDeclaredField("port").get(fuelFlowSensorInstance);
//            FlightRecorder.instance.insert("FuelFlowSensorFactory", "fuelFlowSensorPort: " + fuelFlowSensorPort.hashCode());
//        }   catch (Exception e){
//            e.printStackTrace();
//        }
//        return fuelFlowSensorPort;
//    }

    public static Object build() {
        return Factory.build(Configuration.instance.pathToFuelFlowSensor, "FuelFlowSensor");
    }
}