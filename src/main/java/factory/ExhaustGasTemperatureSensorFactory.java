package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ExhaustGasTemperatureSensorFactory {
    public static Object build() {
        Object exhaustGasTemperatureSensorPort = null;

        try{
            URL[] urls = {new File(Configuration.instance.pathToExhaustGasTemperatureSensor).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, ExhaustGasTemperatureSensorFactory.class.getClassLoader());
            Class exhaustGasTemperatureSensorClass = Class.forName("ExhaustGasTemperatureSensor", true, urlClassLoader);
            FlightRecorder.instance.insert("ExhaustGasTemperatureSensorFactory", "exhaustGasTemperatureSensor: " + exhaustGasTemperatureSensorClass.hashCode());

            Object exhaustGasTemperatureSensorInstance = exhaustGasTemperatureSensorClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("ExhaustGasTemperatureSensorFactory", "exhaustGasTemperatureSensorInstance: " + exhaustGasTemperatureSensorInstance.hashCode());

            exhaustGasTemperatureSensorPort = exhaustGasTemperatureSensorClass.getDeclaredField("port").get(exhaustGasTemperatureSensorInstance);
            FlightRecorder.instance.insert("ExhaustGasTemperatureSensorFactory", "exhaustGasTemperatureSensorPort: " + exhaustGasTemperatureSensorPort.hashCode());
        }   catch (Exception e){
            e.printStackTrace();
        }
            return exhaustGasTemperatureSensorPort;
    }
}
